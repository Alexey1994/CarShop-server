SET GLOBAL time_zone = '+2:00';

CREATE DATABASE CarShop;


CREATE TABLE CarShop.Users
(
	id           LONG,
	role_id      LONG,
	login        VARCHAR(255),
	password     VARCHAR(255),
    last_session VARCHAR(255)
);

/*
INSERT INTO CarShop.Users VALUES
(
	0,
    2,
    '',
    '',
    ''
);*/


CREATE TABLE CarShop.Roles
(
	id   LONG,
    role VARCHAR(10)
);


INSERT INTO CarShop.Roles VALUES
(
	0,
    'user'
);


INSERT INTO CarShop.Roles VALUES
(
	2,
    'purchaser'
);


INSERT INTO CarShop.Roles VALUES
(
	1,
    'admin'
);


CREATE TABLE CarShop.Orders
(
	id          LONG,
	car_id      LONG,
	customer_id LONG,
	order_date  DATE,
    status_id   long
);


CREATE TABLE CarShop.OrderStatuses
(
	id     LONG,
    status VARCHAR(10)
);


INSERT INTO CarShop.OrderStatuses VALUES
(
	0,
	'in cart'
);


INSERT INTO CarShop.OrderStatuses VALUES
(
	1,
	'paid'
);


CREATE TABLE CarShop.Sales
(
	id       LONG,
	order_id LONG
);


CREATE TABLE CarShop.Cars
(
	id                  LONG,
	brand_id            LONG,
	model_id            LONG,
    color_id            LONG,
	power               LONG,
    speed               LONG,
    price               LONG,
	year_of_manufacture LONG
);


CREATE TABLE CarShop.Colors
(
	id   LONG,
	name VARCHAR(255)
);


INSERT INTO CarShop.Colors VALUES
(
	0,
    'Любой'
);


CREATE TABLE CarShop.CarBrands
(
	id    LONG,
	brand VARCHAR(255)
);


INSERT INTO CarShop.CarBrands VALUES
(
	0,
    'Любая'
);


CREATE TABLE CarShop.CarModels
(
	id       LONG,
    brand_id LONG,
	model    VARCHAR(255)
);


INSERT INTO CarShop.CarModels VALUES
(
	0,
    0,
    'Любой'
);


CREATE TABLE CarShop.CarImages
(
	id       LONG,
	car_id   LONG,
	image    VARCHAR(255)
);


CREATE TABLE CarShop.Cart
(
	id          LONG,
	customer_id LONG,
	car_id      LONG
);