ALTER TABLE product
    ADD COLUMN vehicle_type_id BIGINT
        REFERENCES vehicle_type(id)
            ON DELETE SET NULL
            ON UPDATE CASCADE;