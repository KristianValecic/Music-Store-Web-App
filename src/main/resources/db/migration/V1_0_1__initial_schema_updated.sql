CREATE SCHEMA IF NOT EXISTS musicstoreschema;

CREATE TABLE if not exists musicstoreschema.person (
    idperson serial primary key  ,
    firstname varchar(255) NOT NULL ,
    lastname varchar(255) NOT NULL ,
    email varchar(255) NOT NULL ,
    adress varchar(255) not null ,
    password TEXT not null
);

Create table if not exists musicstoreschema.mediacategory(
     idcategory serial PRIMARY KEY,
     mediacategoryname varchar(255) NOT NULL
);
Create table if not exists musicstoreschema.genre(
     idgenre serial PRIMARY KEY,
     genrename varchar(255) NOT NULL
);

CREATE TABLE if not exists musicstoreschema.artist (
     idartist serial primary key  ,
     artistname varchar(255) NOT NULL
);

Create table if not exists musicstoreschema.album (
     idalbum serial primary key,
     albumname varchar(255) NOT NULL,
     genretype varchar(255) NULL,
     image BYTEA not NULL,
     artistid int references musicstoreschema.artist(idartist) not null
--      genreid int references musicstoreschema.genre(idgenre) null
);

Create table if not exists musicstoreschema.loginhistory (
     idloginhistory serial primary key,
     ipadress varchar(255) NOT NULL,
    timeoflogin timestamp  NOT NULL ,
    personid int references musicstoreschema.person(idperson) not null
);

Create table if not exists musicstoreschema.item (
    iditem serial primary key,
    mediatype varchar(255) NOT NULL,
    amountinstock serial NOT NULL,
    price MONEY NOT NULL,
    albumid int references musicstoreschema.album(idalbum) not null
);


CREATE TABLE if not exists musicstoreschema.shoppingcart (
    idcart serial primary key,
    creationtime timestamp not null,
    personid int references musicstoreschema.person(idperson) not null
);
CREATE TABLE if not exists musicstoreschema.shoppingcartitem (
    idcartitem serial primary key,
    itemamount integer not null,
    totalprice money not null,
    itemid int references musicstoreschema.item(iditem) not null,
    shoppingcartid int references musicstoreschema.shoppingcart(idcart)  not null
);

-- CREATE TABLE if not exists musicstoreschema.itempurchase (
--      iditempurchase serial primary key,
--      amount integer not null,
--      purchaseid int references musicstoreschema.purchase(idpurchase) not null,
--      itemid int references musicstoreschema.item(iditem) not null
-- );

CREATE TABLE if not exists musicstoreschema.purchase (
     idpurchase serial primary key  ,
     timeofpurchase timestamp NOT NULL ,
     paymentmethod varchar(255) NOT NULL ,
     shoppingcartid int references musicstoreschema.shoppingcart(idcart) not null,
     personid int references musicstoreschema.person(idperson) not null
);
insert into musicstoreschema.person (firstname, lastname, email, adress, password)
values ('testni', 'korisnik', 'e@mail.com', 'ulica 21', '123');

insert into musicstoreschema.mediacategory (mediacategoryname)
values ('CD'), ('VINYL'), ('CASSETTE');

insert into musicstoreschema.genre (genrename)
values ('PUNK'), ('METAL'), ('POP'), ('ROCK'), ('RAP'), ('FUNK')
