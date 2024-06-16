create sequence maker_seq start with 1 increment by 50;

create table maker
(
    id integer not null default nextval('maker_seq') primary key,
    name text not null unique
);

create sequence model_seq start with 1 increment by 50;

create table model
(
    id integer not null default nextval('model_seq') primary key,
    maker_id integer not null references maker (id),
    name text not null,
    unique (maker_id, name)
);

create sequence body_type_seq start with 1 increment by 50;

create table body_type
(
    id integer not null default nextval('body_type_seq') primary key,
    name text not null unique
);

create table car
(
    id text not null primary key,
    model_id integer not null references model (id),
    production_year smallint not null
);

create table car_body_type
(
    car_id text not null references car (id) on delete cascade on update cascade,
    body_type_id integer not null references body_type (id),
    constraint car_body_type_pkey primary key (car_id, body_type_id)
);