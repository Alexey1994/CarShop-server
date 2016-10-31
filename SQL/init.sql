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
	order_date  DATE
);


CREATE TABLE CarShop.Sales
(
	id       LONG,
	order_id LONG
);


CREATE TABLE CarShop.Cars
(
	id                  LONG,
	color_id            LONG,
	type_id             LONG,
	model_id            LONG,
	power               LONG,
	year_of_manufacture INT,
	speed               LONG
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