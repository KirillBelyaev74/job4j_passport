create database job4j_passport;

create table passport(
    id serial primary key,
    name varchar(50) not null,
    surname varchar(50) not null,
    middle_name varchar(50) not null,
    series int not null,
    number int not null,
    created timestamp not null,
    finished timestamp not null,
    constraint usn unique(series, number)
);
