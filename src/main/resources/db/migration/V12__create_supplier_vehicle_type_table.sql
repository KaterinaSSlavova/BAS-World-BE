create table Supplier(
                      id bigserial primary key,
                      supplier_name varchar(255) not null,
                      picture varchar(255),
                      is_archived boolean not null default false
);

create table Vehicle_Type(
                      id bigserial primary key,
                      vehicle_type_name varchar(255) not null,
                      is_archived boolean not null default false
);