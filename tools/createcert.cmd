@REM To create a self-signed certificate.
@REM   1) Run this script on the computer where you want to create cert.
@REM   2) Make sure you have makecert.exe (part of windows SDK).
@REM   3) Edit the certificate begin and expiration dates if necessary.


makecert.exe -r -pe -n "CN=%COMPUTERNAME%" -b 09/01/2017 -e 09/01/2011 -eku 1.3.6.1.5.5.7.3.1 -ss my -sky exchange -sp "Microsoft RSA SChannel Cryptographic Provider" -sy 12 -sr LocalMachine

