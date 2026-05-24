ALTER TABLE product_depot
    ADD COLUMN supplier_id BIGINT
        REFERENCES supplier(id)
            ON DELETE SET NULL
            ON UPDATE CASCADE;