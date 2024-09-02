winpty openssl req -newkey rsa:2048 -x509 -keyout key.pem -out cert.pem -days 365
winpty openssl pkcs12 -export -in cert.pem -inkey key.pem -out certificate.p12 -name "certificate"

winpty openssl req -newkey rsa:2048 -x509 -keyout key.pem -out cert.pem -days 365
winpty openssl pkcs12 -export -in cert.pem -inkey key.pem -out certificate.p12 -name "certificate"

Country Name (2 letter code) [AU]:ru
State or Province Name (full name) [Some-State]:Samara
Locality Name (eg, city) []:Samara
Organization Name (eg, company) [Internet Widgits Pty Ltd]:Org
Organizational Unit Name (eg, section) []:Org
Common Name (e.g. server FQDN or YOUR name) []:Org
Email Address []:m@m.ru

Please enter the following 'extra' attributes
to be sent with your certificate request
A challenge password []:123456
String too short, must be at least 4 bytes long
A challenge password []:123456
An optional company name []:org