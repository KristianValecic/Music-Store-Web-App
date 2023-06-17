CREATE SCHEMA IF NOT EXISTS musicstoreschema;

Create table if not exists musicstoreschema.roles(
     idrole serial PRIMARY KEY,
     rolename varchar(255) NOT NULL
);
CREATE TABLE if not exists musicstoreschema.person (
    idperson serial primary key  ,
    firstname varchar(255) NOT NULL ,
    lastname varchar(255) NOT NULL ,
    email varchar(255) NOT NULL ,
    adress varchar(255) not null ,
    roleid int references musicstoreschema.roles(idrole) not null,
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
    ispurchased BOOLEAN not null,
    personid int references musicstoreschema.person(idperson) not null
);
CREATE TABLE if not exists musicstoreschema.shoppingcartitem (
    idcartitem serial primary key,
    itemamount integer not null,
    totalprice money not null,
    itemid int references musicstoreschema.item(iditem) not null,
    shoppingcartid int references musicstoreschema.shoppingcart(idcart)  not null
);
CREATE TABLE if not exists musicstoreschema.shoppingcartitems (
      idshoppingcartitemslist serial primary key,
      cartid int references musicstoreschema.shoppingcart(idcart) not null,
      shoppingcartitemid int references musicstoreschema.shoppingcartitem(idcartitem) not null
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

insert into musicstoreschema.roles (rolename)
-- values ('ADMIN_ROLE'), ('USER_ROLE');
SELECT 'ADMIN_ROLE'
    WHERE NOT EXISTS (
    SELECT 1 FROM musicstoreschema.roles
    WHERE rolename = 'ADMIN_ROLE'
    )
union SELECT 'USER_ROLE'
    WHERE NOT EXISTS (
    SELECT 1 FROM musicstoreschema.roles
    WHERE rolename = 'USER_ROLE'
    );
-- insert into musicstoreschema.person (firstname, lastname, email, adress, roleid, password)
-- values ('admin', 'admin', 'ad@min.com', 'adinska', 1, '$2a$12$5oA3YL6tSEGsnxYqvsZ.QuC7wKAIBQ1vuE8uiCSfLZniZ71q6wP4C'), ('testni', 'korisnik', 'e@mail.com', 'ulica 21', 2, '$2a$12$5oA3YL6tSEGsnxYqvsZ.QuC7wKAIBQ1vuE8uiCSfLZniZ71q6wP4C');

INSERT INTO musicstoreschema.person (firstname, lastname, email, adress, roleid, password)
SELECT 'admin', 'admin', 'ad@min.com', 'adinska', 1, '$2a$12$5oA3YL6tSEGsnxYqvsZ.QuC7wKAIBQ1vuE8uiCSfLZniZ71q6wP4C'
    WHERE NOT EXISTS (
    SELECT 1 FROM musicstoreschema.person
    WHERE email = 'ad@min.com'
    );
INSERT INTO musicstoreschema.person (firstname, lastname, email, adress, roleid, password)
SELECT 'testni', 'korisnik', 'e@mail.com', 'ulica 21', 2, '$2a$12$5oA3YL6tSEGsnxYqvsZ.QuC7wKAIBQ1vuE8uiCSfLZniZ71q6wP4C'
    WHERE NOT EXISTS (
    SELECT 1 FROM musicstoreschema.person
    WHERE email = 'e@mail.com'
    );

insert into musicstoreschema.mediacategory (mediacategoryname)
-- values ('CD'), ('VINYL'), ('CASSETTE');
select 'CD'
    where not exists (
        select 1 from musicstoreschema.mediacategory
        where mediacategoryname = 'CD'
        )
UNION
SELECT 'VINYL'
WHERE NOT EXISTS (
        SELECT 1 FROM musicstoreschema.mediacategory WHERE mediacategoryname = 'VINYL'
)
UNION
SELECT 'CASSETTE'
WHERE NOT EXISTS (
        SELECT 1 FROM musicstoreschema.mediacategory WHERE mediacategoryname = 'CASSETTE'
);


insert into musicstoreschema.genre (genrename)
-- values ('PUNK'), ('METAL'), ('POP'), ('ROCK'), ('RAP'), ('FUNK')
select 'PUNK'
where not exists (
        select 1 from musicstoreschema.genre
        where genrename = 'PUNK'
    )
UNION
SELECT 'METAL'
WHERE NOT EXISTS (
        SELECT 1 FROM musicstoreschema.genre WHERE genrename = 'METAL'
    )
UNION
SELECT 'POP'
WHERE NOT EXISTS (
        SELECT 1 FROM musicstoreschema.genre WHERE genrename = 'POP'
    )
UNION
SELECT 'ROCK'
WHERE NOT EXISTS (
        SELECT 1 FROM musicstoreschema.genre WHERE genrename = 'ROCK'
    )
UNION
SELECT 'RAP'
WHERE NOT EXISTS (
        SELECT 1 FROM musicstoreschema.genre WHERE genrename = 'RAP'
    )
UNION
SELECT 'FUNK'
WHERE NOT EXISTS (
        SELECT 1 FROM musicstoreschema.genre WHERE genrename = 'FUNK'
    );