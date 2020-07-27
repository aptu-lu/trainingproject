INSERT INTO Docs (code, name) VALUES (03, 'Свидетельство о рождении');

INSERT INTO Docs (code, name) VALUES (07, 'Военный билет');

INSERT INTO Docs (code, name) VALUES (08, 'Временное удостоверение, выданное взамен военного билета');

INSERT INTO Docs (code, name) VALUES (10, 'Паспорт иностранного гражданина');

INSERT INTO Docs (code, name) VALUES (11, 'Свидетельство о рассмотрении ходатайства о признании лица беженцем на территории Российской Федерации по существу');

INSERT INTO Docs (code, name) VALUES (12, 'Вид на жительство в Российской Федерации');

INSERT INTO Docs (code, name) VALUES (13, 'Удостоверение беженца');

INSERT INTO Docs (code, name) VALUES (15, 'Разрешение на временное проживание в Российской Федерации');

INSERT INTO Docs (code, name) VALUES (18, 'Свидетельство о предоставлении временного убежища на территории Российской Федерации');

INSERT INTO Docs (code, name) VALUES (21, 'Паспорт гражданина Российской Федерации');

INSERT INTO Docs (code, name) VALUES (23, 'Свидетельство о рождении, выданное уполномоченным органом иностранного государства');

INSERT INTO Docs (code, name) VALUES (24, 'Удостоверение личности военнослужащего Российской Федерации');

INSERT INTO Docs (code, name) VALUES (91, 'Иные документы');

INSERT INTO Countries (code, name) VALUES (031, 'АЗЕРБАЙДЖАН');

INSERT INTO Countries (code, name) VALUES (076, 'БРАЗИЛИЯ');

INSERT INTO Countries (code, name) VALUES (124, 'КАНАДА');

INSERT INTO Countries (code, name) VALUES (154, 'КИТАЙ');

INSERT INTO Countries (code, name) VALUES (208, 'ДАНИЯ');

INSERT INTO Countries (code, name) VALUES (250, 'ФРАНЦИЯ');

INSERT INTO Countries (code, name) VALUES (643, 'РОССИЯ');

INSERT INTO Countries (code, name) VALUES (682, 'САУДОВСКАЯ АРАВИЯ');

INSERT INTO Countries (code, name) VALUES (752, 'ШВЕЦИЯ');

INSERT INTO Organization (name, full_name, inn, kpp, address, phone, is_active) VALUES ('ООО "ИМПУЛЬС"', 'Общество с ограниченной ответственностью "Импульс"', '457733210561', '334956843', 'ул. Ленина 15', '84952364343', 1);

INSERT INTO Organization (name, full_name, inn, kpp, address, is_active) VALUES ('ООО "РЕСУРС"', 'Общество с ограниченной ответственностью "Ресурс"', '754839403230', '334433212', 'ул. Мира 7б', 0);

INSERT INTO Office (org_id, name, address, phone, is_active) VALUES (1, 'Победа', 'ул. Ленина 7', '60-40-30', 1);

INSERT INTO Office (org_id, name, address, is_active) VALUES (1, 'Звезда', 'ул. Лунина 7а', 0);

INSERT INTO Office (org_id, name, address) VALUES (2, 'Компас', 'ул. Пушкина 19');

INSERT INTO User (office_id, first_name, last_name, middle_name, position, phone, citizenship_id, is_identified) VALUES (1, 'Илья', 'Климов', 'Андреевич', 'слесарь', '84394959340', 7, 1);

INSERT INTO User (office_id, first_name, last_name, middle_name, position, phone) VALUES (2, 'Андрей', 'Капустин', 'Олегович', 'механик', '83842095858');

INSERT INTO User (office_id, first_name, middle_name, position, citizenship_id, is_identified) VALUES (2, 'Владимир', 'Владимирович', 'строитель', 4, 1);

INSERT INTO User (office_id, first_name, last_name, middle_name, position, phone, citizenship_id, is_identified) VALUES (3, 'Анастасия', 'Харитонова', 'Олеговна', 'менеджер', '85984574637', 2, 0);

INSERT INTO User_Doc (user_id, doc_id, doc_date, doc_number) VALUES (1, 7, '2000-05-04', '49385859384');

INSERT INTO User_Doc (user_id, doc_id, doc_date, doc_number) VALUES (3, 4, '2011-12-07', '98580048659');


