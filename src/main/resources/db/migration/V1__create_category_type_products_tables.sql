CREATE TABLE Category (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    parent_category_id BIGSERIAL,
    CONSTRAINT fk_parent_category
        FOREIGN KEY (parent_category_id)
        REFERENCES Category(id)
);

CREATE TABLE Type (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    parent_type_id BIGSERIAL,
    CONSTRAINT fk_parent_type
        FOREIGN KEY (parent_type_id)
        REFERENCES Type(id)
);

CREATE TABLE Product (
    id BIGSERIAL PRIMARY KEY,
    sku VARCHAR(100) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(4000),
    brand VARCHAR(100),
    price DECIMAL(19, 2),
    status VARCHAR(50),
    type_id BIGSERIAL,
    category_id BIGSERIAL,
    CONSTRAINT fk_product_category
        FOREIGN KEY (category_id)
        REFERENCES Category(id),
    CONSTRAINT fk_product_type
        FOREIGN KEY (type_id)
        REFERENCES Type(id)
);