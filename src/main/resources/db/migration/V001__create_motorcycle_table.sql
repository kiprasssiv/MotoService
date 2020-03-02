CREATE SCHEMA IF NOT EXISTS garage;

CREATE TABLE motorcycles(
    id         UUID          PRIMARY  KEY         NOT NULL,
    make       CHAR(30)    NOT NULL,
    model      CHAR(30)    NOT NULL,
    year       INT         NOT NULL,
    needFixing BOOLEAN     DEFAULT true
);

CREATE TABLE owners(
    id              UUID         PRIMARY KEY         NOT NULL,
    motorcycle_id   UUID         REFERENCES motorcycles(id),
    firstName       CHAR(30)    NOT NULL,
    surname         CHAR(30)    NOT NULL,
    telephoneNumber INT         NOT NULL,
    address         CHAR(50)    NOT NULL
);