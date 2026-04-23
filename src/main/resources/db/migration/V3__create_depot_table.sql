create table Depot(
    id bigserial primary key,
    depot_name varchar(255) not null,
    location varchar(255) not null
);

create table Product_Depot(
    product_id bigint not null,
    depot_id bigint not null,
    is_available boolean not null default true,
    stock_quantity bigint not null check(stock_quantity>=0),
    primary key(product_id, depot_id),
    constraint fk_product_productDepot foreign key (product_id) references Product(id),
    constraint fk_depot_productDepot foreign key (depot_id) references Depot(id)
);