@echo off

SET alias=hpcsoa
SET keypass=!!!123abc
SET storepass=%keypass%
SET serverdname="CN=Microsoft HPC WSS Service"
SET brokerdname="CN=Microsoft HPC WSS Client"

SET serverkey="serverkey.jks"
SET servertrust="servertrust.jks"
SET brokerkey="brokerkey.jks"
SET brokertrust="brokertrust.cer"
SET brokerpfx="brokerkey.pfx"

del /Q %serverkey%
del /Q %servertrust%
del /Q %brokerkey%
del /Q %brokertrust%
del /Q %brokerpfx%

echo 1. Creating private key with given alias and password like "myAlias"/"myAliasPassword" in keystore (protected by password for security reasons)
keytool -genkey -alias %alias% -keypass %keypass% -keystore %serverkey% -storepass %storepass% -dname %serverdname% -keyalg RSA -keysize 2048

echo 2. Self-sign our certificate (in production environment this will be done by a company like Verisign)
keytool -selfcert -alias %alias% -keystore %serverkey% -storepass %storepass% -keypass %keypass%

echo 3. Export the public key from our private keystore to file named %brokertrust%
keytool -export -alias %alias% -file %brokertrust% -keystore %serverkey% -storepass %storepass%

echo 4. Creating private key with given alias and password like "myAlias"/"myAliasPassword" in keystore (protected by password for security reasons)
keytool -genkey -alias %alias% -keypass %keypass% -keystore %brokerkey% -storepass %storepass% -dname %brokerdname% -keyalg RSA -keysize 2048

echo 5. Self-sign our certificate (in production environment this will be done by a company like Verisign)
keytool -selfcert -alias %alias% -keystore %brokerkey% -storepass %storepass% -keypass %keypass%

echo 6. Export the public key from our private keystore to file named %servertrust%
keytool -export -alias %alias% -file tmp.rsa -keystore %brokerkey% -storepass %storepass%

echo 7. Import the public key to new keystore
keytool -import -alias %alias% -file tmp.rsa -keystore %servertrust% -storepass %storepass% -noprompt

echo 8. Convert the JKS to a PKCS12 keystore
keytool -importkeystore -srckeystore %brokerkey% -destkeystore %brokerpfx% -srcstoretype JKS -deststoretype PKCS12 -srcstorepass %storepass% -deststorepass %storepass% -srcalias %alias% -destalias %alias% -srckeypass %keypass% -destkeypass %keypass% -noprompt

del /Q tmp.rsa