create database pc3r;

create table pc3r.Ressource (
    id int auto_increment,
    idu int,
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
    password char(20),
    primary key(id)
);
    
create table pc3r.Profile (
    id int NOT NULL,
    nom VARCHAR(20),
    prenom VARCHAR(20),
    email VARCHAR(50),
    adresse VARCHAR(100),
    telephone VARCHAR(20),
    primary key(id)
);

create table pc3r.Commande (
    id int not null auto_increment,
    idr int,
    idu int,
    checkin date,
    checkout date,
    create_time datetime,
    status varchar(10),
    primary key(id)
);

