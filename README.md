# **Документация для пользователя**
## 1. Запуск
После скачивания файлов из git репозитория необходимо упаковать проект. Для этого в директории со скаченными файлами, 
надо запустить консоль и ввести команду `mvn package`, если все пройдет успешно, должна появиться директория `target` с 
скомпилированным и упакованным в jar файл проектом. Для того, чтобы запустить jar, необходимо в данной директории
запустить консоль и ввести команду `java -jar trainingproject-0.0.1-SNAPSHOT.jar`.
## 2. Запрос
Rest сервис размещен на `localhost:8888`, принимает http запросы(методы GET, POST). В POST запросах ожидает JSON строку с фильтром по сущности, которое будет провалидированно.
В данной таблице представлено : маршрут (конечная точка), метод (http запроса), поля фильтров (поле, выделенное жирным, является обязательным), описание:
  * Документы

    | Маршрут | Метод | Поля фильтров | Описание |
    | -------------- | :-------: |--------------- | --------------- |
    | api/docs | GET |  - | Получить список всех документов |
    
  * Страны
    
    | Маршрут | Метод | Поля фильтров | Описание |
    | -------------- | :-------: |--------------- | --------------- |
    | api/countries | GET |  - | Получить список всех стран |
    
 * Офис
 
    | Маршрут |  Метод | Поля фильтров | Описание |
    | -------------- | :-------: | --------------- | --------------- |
    | api/office/list  | POST | **orgId**, name, phone, isActive | Получить список офисов по заданному фильтру в теле запроса |
    | api/office/{**id**}  | GET  | - | Получить офис по заданному идентификатору |
    | api/office/update | POST | **id**, **name**, **address**, phone, isActive | Обновить офис по заданному фильтру в теле запроса |
    | api/office/save  | POST | **orgId**, name, address, phone, isActive | Сохранить офис по заданному фильтру в теле запроса |
     
 * Организация
     
    | Маршрут |  Метод | Поля фильтров | Описание |
    | -------------- | :-------: | --------------- | --------------- | 
    | api/organization/list  | POST | **name**, inn, isActive | Получить список организаций по заданному фильтру в теле запроса |
    | api/organization/{**id**}  | GET  | - | Получить организацию по заданному идентификатору | 
    | api/organization/update | POST | **id**, **name**, **fullName**, **inn**, **kpp**, **address**, phone, isActive | Обновить организацию по заданному фильтру в теле запроса |
    | api/organization/save  | POST | **name**, **fullName**, **inn**, **kpp**, **address**, phone, isActive | Сохранить организацию по заданному фильтру в теле запроса |
     
 * Пользователь
     
    | Маршрут |  Метод | Поля фильтров | Описание |
    | -------------- | :-------: | -------------- | --------------- |
    | api/user/list  | POST | **officeId**, firstName, lastName, middleName, position, docCode, citizenshipCode | Получить список пользователей по заданному фильтру в теле запроса |
    | api/user/{**id**}  | GET  | - | Получить пользователя по заданному идентификатору |
    | api/user/update | POST | **Id**, **firstName**, lastName, middleName, **position**, phone, docName, docNumber, docDate, citizenshipCode , isIdentified | Обновить пользователя по заданному фильтру в теле запроса |
    | api/user/save  | POST | **officeId**, **firstName**, lastName, middleName, **position**, phone, docCode, docName, docNumber, docDate, citizenshipCode , isIdentified  | Сохранить пользователя по заданному фильтру в теле запроса |

## 3. Ответ
Ответ будет в виде JSON строки. В случае успешного обновления\сохранения будет возвращен объект SuccessResult, содержащий строку `"RESULT" = "success"`. 
Все успешные результаты будут обернуты в объект ResponseData. 
В случае какой либо ошибки будет возращен объект ErrorData, содержащий строки `"error" = "Ошибка сервера."` и  `"codeError" = "{codeError}"` ({codeError} - строка с сгенерированным кодом ошибки).
## 4. Примеры использования
* Запрос методом GET:
    * URI:
        ```$xslt
        localhost:8888/api/user/1
        ```
    * Успешный ответ:
        ```$xslt
        {
            "data": {
                "id": 1,
                "firstName": "Илья",
                "lastName": "Климов",
                "middleName": "Андреевич",
                "position": "слесарь",
                "phone": "84394959340",
                "docName": "Удостоверение беженца",
                "docNumber": "49385859384",
                "docDate": "2000-05-04",
                "citizenshipName": "РОССИЯ",
                "citizenshipCode": "643",
                "isIdentified": true
            }
        }
        ```
* Запрос методом POST:
    * URI:
        ```$xslt
        localhost:8888/api/user/save
        ```
        Тело запроса:
        ```$xslt
        {
            "officeId": "1",
            "firstName": "Олег",
            "position": "Водитель"
        }
        ```
    * Успешный ответ:
        ```$xslt
        {
            "data": {
                "RESULT": "success"
            }
        }
        ```
* Запрос методом GET:
    * URI:
        ```$xslt
        localhost:8888/api/user/7
        ```
    * Ответ с ошибкой:
        ```$xslt
        {
            "error": "Ошибка сервера.",
            "codeError": "501597491"
        }
        ```
# **Документация для разработчика**
Данный Rest сервис использует h2 базу данных находящуюся в памяти. Конфигурация dataSource определена в файле application.property.
На старте работы исполняются sql скрипты data.sql и schema.sql находящиеся в пакете resources, которые создают таблицы и заполняют их в базе данных.
## model
В пакете model находятся сущности, которые являются отображением в базе данных.
## filter
В пакете filter находятся фильтры по сущностям с подробностями валидации. Когда приходит запрос в виде JSON строки 
Jackson мапит ее значения в конкретный фильтр сущности и передает в контроллер.
## controller
В пакете controller находятся rest-контроллеры и советы контроллеров. Контроллеры обрабатывают заданные URI принимая как параметр
фильтры сущностей или id, передавая его в слой сервиса для выполнения бизнес-логики и возвращают представления сущностей (если не void), которые потом
будут обработаны советами контроллеров.
Советы контроллеров:
* GlobalControllerExceptionHandler - обрабатывает все исключения, которые возвращают контроллеры. Он генерирует код ошибки,
логирует сообщение ошибки и возвращает объект представление ErrorData который содержит сообщение с кодом ошибки.
* CustomResponseBody - обрабатывает все ответы контроллеров до их преобразования в JSON строку. Если контроллер вернул null,
то возвращает объект представление ResponseResult в который упаковывает SuccessResult. Если контроллер вернул ErrorData, 
то возвращает его же. В остальных случаях обарачивает ответ в ResponseResult.
После советов контроллеров результаты преобразуются в JSON строку, помещаются в тело ответа и отправляются клиенту.
## service
В пакете service находится класс конфигурации для мапера и классы для каждой сущности, в которых должна содержаться бизнес-логика.
В данном случае они получают фильтр по сущности, через слой dao достают эту сущность из базы данных, преобразуют ее в 
представление сущности и возвращают.
## dao
В пакете dao находятся классы, которые посылают запросы, по фильтру сущности, в базу данных 
и возвращают данную сущность(если не void).
## view
В пакете view находятся классы представления сущностей, которые будут преобразованы в JSON строку и отправлены пользователю
в теле ответа.