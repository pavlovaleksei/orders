DROP DATABASE if exists Orders;

CREATE DATABASE Orders;

USE Orders;

CREATE TABLE orders (
                        id int NOT NULL AUTO_INCREMENT UNIQUE,
                        customername varchar(255),
                        customeraddr varchar(255),
                        ordersum double DEFAULT 0,
                        createdate TIMESTAMP,
                        PRIMARY KEY (id)
) DEFAULT CHARSET=utf8;

INSERT INTO Orders.orders (customername, customeraddr, ordersum, createdate) VALUES ('Иванов Иван Иванович', 'Москва, Ленина 1', 12000, DEFAULT);
INSERT INTO Orders.orders (customername, customeraddr, ordersum, createdate) VALUES ('Петров Петр Петрович', 'Саратов, Московская 1', 15000, DEFAULT);

CREATE TABLE details (
                         detailid int NOT NULL AUTO_INCREMENT UNIQUE,
                         productserialnum varchar(255),
                         productname varchar(255),
                         count int DEFAULT 0,
                         orderid int,
                         PRIMARY KEY (detailid),
                         FOREIGN KEY (orderid) REFERENCES orders(id)
) DEFAULT CHARSET=utf8;

INSERT INTO Orders.details (productserialnum, productname, count, orderid) VALUES ('1SONY11', 'Телевизор', 5, 1);
INSERT INTO Orders.details (productserialnum, productname, count, orderid) VALUES ('1LIBHER12', 'Холодильник', 7, 1);
INSERT INTO Orders.details (productserialnum, productname, count, orderid) VALUES ('1APPLE13', 'Телефон', 4, 1);
INSERT INTO Orders.details (productserialnum, productname, count, orderid) VALUES ('2CAT1', 'Кошка', 27, 2);
INSERT INTO Orders.details (productserialnum, productname, count, orderid) VALUES ('2DOG2', 'Собака', 13, 2);

SELECT * FROM orders o INNER JOIN details d ON d.orderid = o.id WHERE o.id = 1;