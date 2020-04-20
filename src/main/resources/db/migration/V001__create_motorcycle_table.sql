CREATE SCHEMA IF NOT EXISTS garage;

CREATE TABLE motorcycles(
    id         UUID          PRIMARY  KEY         NOT NULL,
    make       varchar    NOT NULL,
    model      varchar    NOT NULL,
    year       INT         NOT NULL,
    needFixing BOOLEAN     DEFAULT true
);

CREATE TABLE owners(
    id              UUID         PRIMARY KEY         NOT NULL,
    motorcycle_id   UUID         REFERENCES motorcycles(id),
    firstName       varchar     NOT NULL,
    surname         varchar    NOT NULL,
    telephoneNumber INT         NOT NULL,
    address         varchar    NOT NULL
);
