# **Документация для пользователя**
## 1. Конечные точки 
    * Документы
    | Маршрут | Метод | Описание |
    |----------------|:---------:|----------------:|
    | api/docs | GET | Получить список всех документов |
    * Страны
    | Маршрут | Метод | Описание |
    |----------------|:---------:|----------------:|
    | api/countries | GET | Получить список всех стран |
    * Офис
    | Маршрут |  Метод | Описание |
    |------------------|:---------:|----------------:|
    | api/office/list  | POST | Получить список офисов по заданному фильтру в теле запроса |
    | api/office/{id}  | GET  | Получить офис по заданному идентификатору |
    | api/office/update| POST | Обновить офис по заданному фильтру в теле запроса |
    | api/office/save  | POST | Сохранить офис по заданному фильтру в теле запроса |
    * Организация
    | Маршрут |  Метод | Описание |
    |------------------|:---------:|----------------:|
    | api/organization/list  | POST | Получить список организаций по заданному фильтру в теле запроса |
    | api/organization/{id}  | GET  | Получить организацию по заданному идентификатору |
    | api/organization/update| POST | Обновить организацию по заданному фильтру в теле запроса |
    | api/organization/save  | POST | Сохранить организацию по заданному фильтру в теле запроса |
    * Пользователь
    | Маршрут |  Метод | Описание |
    |------------------|:---------:|----------------:|
    | api/user/list  | POST | Получить список пользователей по заданному фильтру в теле запроса |
    | api/user/{id}  | GET  | Получить пользователя по заданному идентификатору |
    | api/user/update| POST | Обновить пользователя по заданному фильтру в теле запроса |
    | api/user/save  | POST | Сохранить пользователя по заданному фильтру в теле запроса |