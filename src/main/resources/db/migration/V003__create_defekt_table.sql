CREATE TABLE defects(
    moto_id       UUID   NOT NULL,
    service_id    int   NOT NULL,
    foreign key (moto_id)
            references motorcycles (id)
            on delete cascade
);