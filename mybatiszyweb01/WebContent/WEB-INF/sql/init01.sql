drop database IF EXISTS zy_shop;
create database zy_shop;
GRANT ALL ON zy_shop.* to 'hwl'@'localhost'  IDENTIFIED BY '391096';
use zy_shop;
create table user(
	id int(11) primary key auto_increment,
	username varchar(50),
	password varchar(50),
	nickname varchar(50),
	type int(5)
);
create table address(
	id int(11)primary key auto_increment,
	name varchar(50),
	phone varchar(50),
	postcode varchar(100),
	user_id int(5),
	foreign key(user_id) references user(id)
);
create table orders(
	id int(11)primary key auto_increment,
	buy_date datetime,
	pay_date datetime,
	confrim_date datetime,
	price double,
	status int(5),
	user_id int(11),
	address_id int(11),
	foreign key(user_id) references user(id),
	foreign key(address_id)references address(id)
);
create table category(
	id int(11)primary key auto_increment,
	name varchar(100)
);
create table product(
	id int(11)primary key auto_increment,
	name varchar(50),
	price double,
	intor text,
	img varchar(100),
	stock int(100),
	category_id int(11),
	status int(2),
	foreign key(category_id) references category(id)
);
create table shop_product(
	id int(11)primary key auto_increment,
	number int (11),
	price double,
	o_id int(11),
	p_id int(11),
	foreign key(o_id) references orders(id),
	foreign key(p_id) references product(id)
);
create table orders_product(
	id int(11)primary key auto_increment,
	orders_id int(11),
	product_id int(11),
	foreign key(orders_id) references orders(id),
	foreign key(product_id) references product(id)
);
insert into user values(null,'zy','150335','张益',1);

