ALTER TABLE Brand
    ADD COLUMN picture varchar(255),
    ADD COLUMN is_archived boolean not null default false;