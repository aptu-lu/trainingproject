CREATE TABLE IF NOT EXISTS Organization (
    id         INTEGER               COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT ,
    version    INTEGER DEFAULT 1     COMMENT 'Служебное поле hibernate',
    name       VARCHAR(50) NOT NULL  COMMENT 'Название',
    full_name  VARCHAR(50) NOT NULL  COMMENT 'Полное название',
    inn        VARCHAR(50) NOT NULL  COMMENT 'ИНН',
    kpp        VARCHAR(50) NOT NULL  COMMENT 'КПП',
    address    VARCHAR(50) NOT NULL  COMMENT 'Адрес',
    phone      VARCHAR(50)           COMMENT 'Номер телефона',
    is_active  BOOLEAN               COMMENT 'Действителен ли'
);
COMMENT ON TABLE Organization IS 'Организация';

CREATE TABLE IF NOT EXISTS Office (
    id         INTEGER              COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT ,
    version    INTEGER DEFAULT 1    COMMENT 'Служебное поле hibernate',
    org_id     INTEGER NOT NULL     COMMENT 'Уникальный идентификатор организации',
    name       VARCHAR(50) NOT NULL COMMENT 'Название',
    address    VARCHAR(50) NOT NULL COMMENT 'Адрес',
    phone      VARCHAR(50)          COMMENT 'Номер телефона',
    is_active  BOOLEAN              COMMENT 'Действителен ли'
);
COMMENT ON TABLE Office IS 'Офис';

CREATE TABLE IF NOT EXISTS User (
    id                INTEGER              COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version           INTEGER DEFAULT 1    COMMENT 'Служебное поле hibernate',
    office_id         INTEGER NOT NULL     COMMENT 'Уникальный идентификатор офиса',
    first_name        VARCHAR(50) NOT NULL COMMENT 'Имя',
    last_name         VARCHAR(50)          COMMENT 'Фамилия',
    middle_name       VARCHAR(50)          COMMENT 'Отчество',
    position          VARCHAR(50) NOT NULL COMMENT 'Позиция',
    phone             VARCHAR(50)          COMMENT 'Номер телефона',
    citizenship_id    INTEGER              COMMENT 'Уникальный идентификатор гражданства',
    is_identified     BOOLEAN              COMMENT 'Идентифицированный ли'
);
COMMENT ON TABLE User IS 'Пользователь';

CREATE TABLE IF NOT EXISTS User_Doc (
    user_id    INTEGER NOT NULL     COMMENT 'Уникальный идентификатор пользователя' PRIMARY KEY ,
    version    INTEGER DEFAULT 1    COMMENT 'Служебное поле hibernate',
    doc_id     INTEGER              COMMENT 'Уникальный идентификатор справочника документов',
    doc_date   DATE                 COMMENT 'Дата',
    doc_number VARCHAR(50)          COMMENT 'Номер'
);
COMMENT ON TABLE User_Doc IS 'Документ пользователя';

CREATE TABLE IF NOT EXISTS Docs (
    id         INTEGER               COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT ,
    version    INTEGER DEFAULT 1     COMMENT 'Служебное поле hibernate',
    code       VARCHAR(50) NOT NULL  COMMENT 'Код',
    name       VARCHAR(150) NOT NULL COMMENT 'Название документа'
);
COMMENT ON TABLE Docs IS 'Справочник документов';

CREATE TABLE IF NOT EXISTS Countries (
    id         INTEGER              COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT ,
    version    INTEGER DEFAULT 1    COMMENT 'Служебное поле hibernate',
    code       VARCHAR(50) NOT NULL COMMENT 'Код',
    name       VARCHAR(50) NOT NULL COMMENT 'Название страны'
);
COMMENT ON TABLE Countries IS 'Справочник стран';

CREATE INDEX IX_Office_org_id ON Office (org_id);
ALTER TABLE Office ADD FOREIGN KEY (org_id) REFERENCES Organization(id);

CREATE INDEX IX_User_office_id ON User (office_id);
ALTER TABLE User ADD FOREIGN KEY (office_id) REFERENCES Office(id);

CREATE INDEX IX_User_citizenship_id ON User (citizenship_id);
ALTER TABLE User ADD FOREIGN KEY (citizenship_id) REFERENCES Countries(id);

CREATE INDEX IX_User_Doc_doc_id ON User_Doc (doc_id);
ALTER TABLE User_Doc ADD FOREIGN KEY (doc_id) REFERENCES Docs(id);

CREATE INDEX IX_Organization_name ON Organization (name);
CREATE INDEX IX_Organization_inn ON Organization (inn);
CREATE INDEX IX_Organization_is_active ON Organization (is_active);

CREATE INDEX IX_Office_name ON Office (name);
CREATE INDEX IX_Office_phone ON Office (phone);
CREATE INDEX IX_Office_is_active ON Office (is_active);

CREATE INDEX IX_User_first_name ON User (first_name);
CREATE INDEX IX_User_last_name ON User (last_name);
CREATE INDEX IX_User_middle_name ON User (middle_name);
CREATE INDEX IX_User_position ON User (position);

CREATE INDEX IX_Docs_code ON Docs (code);

CREATE INDEX IX_Countries_code ON Countries (code);
