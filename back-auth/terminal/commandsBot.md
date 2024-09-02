Чтобы сгенерировать самоподписанный сертификат и сохранить его в формате PKCS#12 с помощью OpenSSL, выполните следующие шаги:
1. Сначала создайте приватный ключ:
   openssl genpkey -algorithm RSA -out private.key -pkeyopt rsa_keygen_bits:2048
2. Создайте запрос на сертификат (CSR):
   openssl req -new -key private.key -out request.csr
В процессе вам будет предложено ввести информацию, такую как страна, штат, организация и т.д.
3. Создайте самоподписанный сертификат:
   winpty openssl x509 -req -in request.csr -signkey private.key -out certificate.crt -days 365
Здесь -days 365 определяет срок действия сертификата (в днях).
4. Соберите все в один файл PKCS#12:
   winpty openssl pkcs12 -export -out certificate.p12 -inkey private.key -in certificate.crt
При выполнении этой команды вас попросят ввести пароль для защиты файла PKCS#12.
Теперь у вас есть самоподписанный сертификат в формате PKCS#12 (certificate.p12).

Из стековерфлоу
https://stackoverflow.com/questions/32100271/how-to-turn-off-all-ssl-checks-on-postman-for-a-specific-site
keytool -genkeypair -alias tomcat -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore keystore.p12 -validity 3650