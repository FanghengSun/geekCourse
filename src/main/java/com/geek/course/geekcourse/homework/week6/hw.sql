-- contact
create table contact
(
    id            bigint auto_increment,
    user_id       bigint       not null,
    first_name    varchar(50)  not null,
    last_name     varchar(50)  not null,
    email_address varchar(100) null,
    phone_number  bigint       null,
    constraint contact_id_uindex
        unique (id)
);

alter table contact
    add primary key (id);

--user
create table user
(
    id                 bigint auto_increment,
    user_oid           varchar(36)      not null,
    activated          bit default b'0' not null,
    created_date       datetime         null,
    last_modified_date datetime         null,
    constraint user_id_uindex
        unique (id)
);

create index user_oid_index
    on user (user_oid);

alter table user
    add primary key (id);

--item
create table item
(
    id                 bigint auto_increment,
    item_oid           varchar(36)      not null,
    seller_oid         varchar(36)      not null,
    price              int              not null,
    name               varchar(100)     not null,
    item_info          varchar(200)     null,
    is_enabled         bit default b'0' null,
    is_deleted         bit default b'0' null,
    created_date       datetime         null,
    last_modified_date datetime         null,
    created_by         varchar(36)      null,
    last_modified_by   varchar(36)      null,
    constraint item_id_uindex
        unique (id)
);

alter table item
    add primary key (id);

create index item_oid_seller_oid_index
    on `item` (item_oid, seller_oid);

--order
create table `order`
(
    id            bigint auto_increment,
    user_oid      varchar(36)      not null,
    item_oid      varchar(36)      not null,
    order_status  int              not null,
    is_enabled    bit default b'0' not null,
    is_deleted    bit default b'0' not null,
    modified_date datetime         null,
    created_date  datetime         null,
    constraint order_id_uindex
        unique (id)
);

create index order_user_oid_item_oid_index
    on `order` (user_oid, item_oid);