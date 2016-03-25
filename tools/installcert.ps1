#------------------------------------------------------------------------------
# <copyright file="installcert.ps1" company="Microsoft">
#      Copyright (c) Microsoft Corporation.  All rights reserved.
# </copyright>
# <summary>
#      Script to install certificate
# </summary>
#------------------------------------------------------------------------------

function SetBaseAddress([string]$filename, [string]$newBaseAddress) {
  $service = [xml](gc $filename)
  #"BaseAddress={0}" -f $newBaseAddress
  #$service.Save([Console]::Out)

  #  Does this look like a WCF serice config file?
  if ( $service.configuration -eq "")
  {
    "The file {0} does not appear to be a WCF Service Host configuration file." -f $filename
    return
  }

  #  Add an appSettings element if there is not one there already
  $appSettingsElement = ""
  $appSettingsList = $service.configuration.GetElementsByTagName("appSettings")
  if ($appSettingsList.Count -eq 0)
  {
     $appSettingsElement = $service.CreateElement("appSettings")
     $service.configuration.AppendChild($appSettingsElement)
  }
  else
  {
    $appSettingsElement = $appSettingsList.Item(0)
  }

  # Sanity check
  if ( $appSettingsElement -eq "")
  {
     "No appSettings element has been found or put into the XML"
     return
  }

  # Look at the keys in the appSettings element
  # If there are no key elements then the appSettings is a String not an XMLElement
  $oldBaseAddress=""
   
  foreach ($node in $appSettingsElement.GetElementsByTagName("add"))
  {
    if ( ($node.get_Name() -eq "add") -and ($node.GetAttribute("key") -eq "baseAddress") )
    {
       $oldBaseAddress = $node.GetAttribute("value")
       if ($newBaseAddress -ne "")
       {
         $node.SetAttribute("value",$newBaseAddress)
       }
       else
       {
         $discard = $appSettingsElement.RemoveChild($node)
         break;
       }
    }
    #"appSettings {0}: {1}={2}" -f $node.get_Name(), $node.GetAttribute("key"), $node.GetAttribute("value")
  }
   
  # If there is no existing baseAddress in the config file then add the new one
  if ( ($oldBaseAddress -eq "") -and ($newBaseAddress -ne "") )
  { 
    $element = $service.CreateElement("add")
    $element.SetAttribute("key","baseAddress")
    $element.SetAttribute("value",$newBaseAddress)
    $appSettingsElement.AppendChild($element)
  }

  #$service.Save([Console]::Out)
  $service.Save($filename)
}


#
#
# Script to handle the install and uninstall of soa Web Service# #


# Set defaults
$port = 443
$verbose = 0
$install = -1
$url = ""

$appID = "{4dc3e181-e14b-4a21-b022-59fc669b0914}"

# Report Usage
if ($args.count -eq 0)
{
  "Usage: installcert.ps1 { install | uninstall } [-verbose]"
  return
}

# Parse the command line arguments
for ($i=0;$i -lt $args.count; $i++)
{
  if ($args[$i] -eq "install")
  {
     $install = 1
  }
  if ($args[$i] -eq "uninstall")
  {
     $install = 0
  }
  if ($args[$i] -eq "-verbose")
  {
     $verbose = 1
  }
}

# Check user is running with sufficient privilege
$wid=[System.Security.Principal.WindowsIdentity]::GetCurrent()
$prp=new-object System.Security.Principal.WindowsPrincipal($wid)
$adm=[System.Security.Principal.WindowsBuiltInRole]::Administrator
$IsAdmin=$prp.IsInRole($adm)
if ($IsAdmin -eq $false)
{
  "installcert.ps1 must be run by a local Administrator with elevated privilege"
  return
}

# Has install/uninstall been selected?
if ($install -eq -1)
{
  "Please select the install or uninstall option"
  return
}

# If installing on a non-standard url (inc. port)
if ( ( $install -eq 1 ) -and ( $args.count -eq 2) )
{
  $url = $args[1]
  $p = [regex] "(\d+)/"
  $result = $p.match($url)
  if ($result.Success)
  {
    $port = $result.Value
    $port = $port.trimend('/')
  }
  "Installing the soa on port {0} on the URL {1}" -f $port, $url 
}

# Echo selected options

# Uninstall the windows service for the Web Service 
if ($install -eq 0) {
  
  # Remove the https binding
  $cmdoutput = netsh http show sslcert
  $ipPort = $cmdoutput -match "IP:port"
  $httpApps = $cmdoutput -match $appID
  if ($verbose -eq 1)
  {
    $httpApps
    $ipPort
  }

  for ($i = 0; $i -lt $httpApps.get_Length(); $i++)
  {
    "{0}:Binding -*{1}*" -f ($i+1), $ipPort[$i].Substring(30)
  }

  if ($httpApps.get_Length() -ne 0)
  {
    $ipPortChoice = 1
    "Select the port binding you wish to remove (0 to skip)"
    do
    {
      $ipPortChoice = ([int] $host.UI.RawUI.ReadKey("NoEcho,IncludeKeyUp").Character) -48
      $valueOK =  ($ipPortChoice -ge "0") -and ($ipPortChoice -le $httpApps.get_Length())
    } while (!$valueOK)

    if ($ipPortChoice -ne 0)
    {
      $binding = $ipPort[$ipPortChoice-1].Substring(30).Trim()
      "Removing the binding ({0}) between the server https protocol used for the soa service" -f $binding
      netsh.exe http del sslcert ipport=$binding
    }
  }

  return
}

# Read the certificates into an array
$certificates = @()
$certCount = 0
foreach ($cert in (dir cert:\LocalMachine\my)) {
  if ($cert.Extensions | %{$_.EnhancedKeyUsages} | ?{$_.value -eq "1.3.6.1.5.5.7.3.1"}) 
  {
    $subject = "<No Subject Specified>"
    if ($cert.Subject -ne "")
    {
      $subject = $cert.Subject
    }
    $certificates = $certificates + ($subject,$cert.Issuer,$cert.Thumbprint)
    $certCount++
  }
  else
  {
    if ($cert.Extensions | %{$_.EnhancedKeyUsages} | ?{$_.value -eq "1.3.6.1.5.5.7.3.2"}) 
    {
      $subject = "<No Subject Specified>"
      if ($cert.Subject -ne "")
      {
        $subject = $cert.Subject
      }
      $certificates = $certificates + ($subject,$cert.Issuer,$cert.Thumbprint)
      $certCount++
    }
  }
}

if ($certCount -eq 0)
{
  "Install failed, no suitable client or server authentication certificates discovered"
  return
}


# List the certificates
for ($i = 0; $i -lt $certCount; $i++)
{
  "{0}:{1} ({2})" -f ($i+1),$certificates[$i*3],$certificates[1+($i*3)]
}

$certChoice = 1
"Select the certificate you wish to use with the https protocol"
do
{
  $certChoice = ([int] $host.UI.RawUI.ReadKey("NoEcho,IncludeKeyUp").Character) -48
  $valueOK =  ($certChoice -gt "0") -and ($certChoice -le "$certCount") } while (!$valueOK)

"Binding the server https protocol used for the soa service to the specified certificate"
if ($verbose -eq 1)
{
  "Current https certificate configuration"
  netsh http show sslcert
}

"Deleting the current https certificate if it exists"
netsh http delete sslcert ipport=0.0.0.0:$port | out-null

$thumbprint = $certificates[2+($certChoice-1)*3]
"Setting the https certificate to thumbprint:  $thumbprint"
netsh.exe http add sslcert "ipport=0.0.0.0:$port" "certhash=$thumbprint" "appid=$appId"

"Open a firewall exception for the  Web Service"
netsh advfirewall firewall add rule name="SOA Web Service" protocol=TCP dir=in localport="$port" action=allow


