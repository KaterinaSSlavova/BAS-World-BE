-- 1. PRODUCT TABLE CHANGES

-- Rename brand column to brand_id and make it NOT NULL
ALTER TABLE product
    RENAME COLUMN brand TO brand_id;

ALTER TABLE product
    ALTER COLUMN brand_id SET NOT NULL;

-- Remove price column
ALTER TABLE product
    DROP COLUMN price;


-- 2. PRODUCT_DEPOT TABLE CHANGES

-- Add cost_price and sale_price with temporary default (20)
ALTER TABLE product_depot
    ADD COLUMN cost_price NUMERIC(10, 2) DEFAULT 20 NOT NULL,
    ADD COLUMN sale_price NUMERIC(10, 2) DEFAULT 20 NOT NULL;

ALTER TABLE product_depot
    ALTER COLUMN cost_price DROP DEFAULT,
    ALTER COLUMN sale_price DROP DEFAULT;