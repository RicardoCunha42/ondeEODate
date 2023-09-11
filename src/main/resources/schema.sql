-- bar
create table IF NOT EXISTS bares(
    id serial primary key,
    nome varchar(255) not null,
    gasto varchar(255) not null,
    insta varchar(255) not null,
    obs varchar(255)
);

-- cafe
create table IF NOT EXISTS cafes (
    id serial primary key,
    nome varchar(255) not null,
    gasto varchar(255) not null,
    insta varchar(255) not null,
    obs varchar(255)
);