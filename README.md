# StackOverFlow
Rest API для поиска вопросов на Stack Overflow.
Используется Java 11, SpringBoot. Для тестов JUnit 5 и WireMock. Для уменьшения бойлер-плейт кода подключен Lombok.
Для запуска приложения - gradle clean build - сборка jar-файла с одновременным запуском всех тестов. Затем docker-compose -up. 
По умолчанию приложение стартует на 8088. Проверить работу API можно {host}/swagger-ui/
Документация swagger - {host}/v3/api-docs, java - в папке docs.
