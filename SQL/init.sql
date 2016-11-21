CREATE DATABASE CarShop;


CREATE TABLE CarShop.Users
(
	id       LONG,
	role_id  LONG,
	login    VARCHAR(255),
	password VARCHAR(255)
);


CREATE TABLE CarShop.Roles
(
	id   LONG,
    role VARCHAR(10)
);


CREATE TABLE CarShop.Orders
(
	id          LONG,
	car_id      LONG,
	customer_id LONG,
	order_date  DATE,
    status      VARCHAR(10)
);


CREATE TABLE CarShop.OrderStatuses
(
	id     LONG,
    status VARCHAR(10)
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
	id      LONG,
	user_id LONG,
	car_id  LONG
);


INSERT INTO CarShop.Roles VALUES
(
	0,
    'user'
);


INSERT INTO CarShop.Roles VALUES
(
	1,
    'admin'
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