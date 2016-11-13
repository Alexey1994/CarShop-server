CREATE DATABASE CarShop;


CREATE TABLE CarShop.Users
(
	id       LONG,
	role     VARCHAR(10),
	login    VARCHAR(255),
	password VARCHAR(255)
);


CREATE TABLE CarShop.Orders
(
	id          LONG,
	car_id      LONG,
	customer_id LONG,
	order_date  DATE,
    status      VARCHAR(10)
);


CREATE TABLE CarShop.Sales
(
	id       LONG,
	order_id LONG
);


CREATE TABLE CarShop.Cars
(
	id                  LONG,
	type_id             LONG,
	model_id            LONG,
    color_id            LONG,
	power               LONG,
    speed               LONG,
	year_of_manufacture LONG,
	image               VARCHAR(255)
);


CREATE TABLE CarShop.Colors
(
	id   LONG,
	name VARCHAR(255)
);


CREATE TABLE CarShop.CarTypes
(
	id   LONG,
	type VARCHAR(255)
);


CREATE TABLE CarShop.CarModels
(
	id    LONG,
	model VARCHAR(255)
);


CREATE TABLE CarShop.Cart
(
	id      LONG,
	user_id LONG,
	car_id  LONG
);