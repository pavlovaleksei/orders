# Orders

Тестовый проект с бизней логикой для работы с заказами

Back-end - REST api сервис

Front-end - VUE JS app

Сборка проекта:
1. Для сборки серверной части приложения необходимо выполнить команду в корне приложения mvn clean install
2. Для сборки интерфейсной части необходимо перейти в каталог /Client-UI/orders-ui/ и выполнить команду npm install (npm run serve для запуска)
3. Проициализировать БД MySql файл инициализации находится в корне приложения /SqlInit.sql

Интерфейс будет доступен на 9005 порту, сервер на 9000

Примеры HTTP запросов находятся в каталоге /TestRequests/*

Сборки приложений находятся в каталоге /AppBuilds/
