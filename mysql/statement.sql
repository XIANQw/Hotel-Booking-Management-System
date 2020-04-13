create database pc3r;

create table pc3r.Ressource (
    id int auto_increment,
    type varchar(10),
    price float,
    number int,
    street varchar(50),
    postal int,
    city varchar(20),
    persons int,
    smoker varchar(5),
    primary key(id));

create table pc3r.User (
    id int auto_increment,
    username char(20),
    password char(20)
);

create table user_ressource (
    id int auto_increment, 
    idu int,
    idr int,
    primary key(id));

CREATE TABLE profile (
    Id int NOT NULL,
    nom VARCHAR(20),
    prenom VARCHAR(20),
    email VARCHAR(50) ,
    adresse VARCHAR(100) ,
    telephone int ,
    primary key(id)
);
