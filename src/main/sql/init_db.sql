--
-- PostgreSQL database 4Motivation Webshop
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET default_tablespace = '';

SET default_with_oids = false;

---
--- drop tables
---

DROP TABLE IF EXISTS users CASCADE ;
DROP TABLE IF EXISTS products CASCADE;
DROP TABLE IF EXISTS orders CASCADE;
DROP TABLE IF EXISTS suppliers CASCADE;
DROP TABLE IF EXISTS categories CASCADE;
DROP TABLE IF EXISTS line_items CASCADE;
DROP TABLE IF EXISTS shipping_descriptions CASCADE;

---
--- create tables
---

CREATE TABLE users (
                          id       SERIAL PRIMARY KEY     NOT NULL,
                          email    VARCHAR(200)           NOT NULL,
                          name     VARCHAR(200)           NOT NULL,
                          password VARCHAR(64)           NOT NULL,
                          order_id INTEGER
                   );

CREATE TABLE orders (
                        id          SERIAL PRIMARY KEY  NOT NULL,
                        shipping_description_id      INTEGER        NOT NULL,
                        user_id     INTEGER              NOT NULL,
                        payment_method VARCHAR (200)    NOT NULL
);

CREATE TABLE shipping_descriptions (
                       id          SERIAL PRIMARY KEY  NOT NULL,
                       country   VARCHAR(200)             NOT NULL,
                       zip_code   VARCHAR (200)         NOT NULL,
                       address       VARCHAR (200)       NOT NULL,
                       first_name  VARCHAR(200)             NOT NULL,
                       last_name VARCHAR(200)              NOT NULL,
                       email VARCHAR(200)               NOT NULL
);

CREATE TABLE products (
                       id          SERIAL PRIMARY KEY NOT NULL,
                       name        VARCHAR(50)           NOT NULL,
                       description VARCHAR                  NOT NULL,
                       default_price    DECIMAL(20,2)     NOT NULL,
                       default_currency   VARCHAR (200)       NOT NULL,
                       category_id INTEGER                 NOT NULL,
                       supplier_id INTEGER                  NOT NULL
);


CREATE TABLE suppliers (
                       id           SERIAL PRIMARY KEY NOT NULL,
                       name         VARCHAR (200)        NOT NULL,
                       description  VARCHAR (500)        NOT NULL

);

CREATE TABLE categories (
                       id       SERIAL PRIMARY KEY NOT NULL,
                       name         VARCHAR (200)   NOT NULL,
                       department VARCHAR (200)    NOT NULL,
                       description VARCHAR (500)   NOT NULL

);

CREATE TABLE line_items (
                    id          SERIAL PRIMARY KEY NOT NULL,
                    quantity    INTEGER,
                    order_id    INTEGER,
                    product_id INTEGER
);


---
--- insert data
---


INSERT INTO suppliers(name, description) VALUES ('Amazon', 'Digital content and services');
INSERT INTO suppliers(name, description) VALUES ('Nike', 'Sport brand');
INSERT INTO suppliers(name, description) VALUES ('Coca-Cola', 'Drink factory');
INSERT INTO suppliers(name, description) VALUES ('Ted', 'TEDx is a program of local, self-organized events that bring people together to share a TED-like experience');
INSERT INTO suppliers(name, description) VALUES ('Witchcraft Brewery', 'The most enchanting beverages from all the realms');
INSERT INTO suppliers(name, description) VALUES ('Absolut', 'Original Sweden alcoholic products');
INSERT INTO suppliers(name, description) VALUES ('Perfect Pet Shop', 'The perfect shop for perfect wannabe pet owners');
INSERT INTO suppliers(name, description) VALUES ('Average Plant Shop', 'Not too meh plants for those with no patience for better creatures');

INSERT INTO categories(name, department, description) VALUES ('Event', 'Talks','TEDx is a grassroots initiative, created in the spirit of TED’s overall mission to research and discover “ideas worth spreading.” TEDx brings the spirit of TED to local communities around the globe through TEDx events. These events are organized by passionate individuals who seek to uncover new ideas and to share the latest research in their local areas that spark conversations in their communities.' );
INSERT INTO categories(name, department, description) VALUES ('Advert', 'Video', 'A motivating video.');
INSERT INTO categories(name, department, description) VALUES ('Elixir', 'Consumable', 'An enchanting drink to enhance your powers.');
INSERT INTO categories(name, department, description) VALUES ('Poster', 'Picture', 'A motivating piece of paper.');
INSERT INTO categories(name, department, description) VALUES ('Pet', 'Pet', 'An actual animal that you need to care for.');
INSERT INTO categories(name, department, description) VALUES ('Plant', 'Plant', 'A literal plant. Not as motivating as a pet, but still dies if you dont take care of it.');

INSERT INTO users(email, name, order_id, password) VALUES ('teszt.tamas@test.com', 'Teszt Tamas', null, 'kiskutya');

INSERT INTO products(name, default_price, default_currency, description,  category_id, supplier_id) VALUES ('Julian Treasure', 8500, 'USD', 'How to speak so that people want to listen.',1 ,4);
INSERT INTO products(name, default_price, default_currency, description,  category_id, supplier_id) VALUES ('Bill Gates', 12500, 'USD', 'The next outbreak? We are not ready.',1 ,4);
INSERT INTO products(name, default_price, default_currency, description,  category_id, supplier_id) VALUES ('Just Do It rights', 479, 'USD', 'Dooooooooo it!.',2 ,2);
INSERT INTO products(name, default_price, default_currency, description,  category_id, supplier_id) VALUES ('Never give up!', 100, 'USD', 'I never lose. I either win or I learn.',2 ,3);
INSERT INTO products(name, default_price, default_currency, description,  category_id, supplier_id) VALUES ('Puppy Poster', 200, 'USD', 'Its not real, but its cute and motivates you.',4 ,4);

INSERT INTO products(name, default_price, default_currency, description,  category_id, supplier_id) VALUES ('Potion of Happiness', 30, 'USD', 'The potion of choice for anyone feeling a bit under the weather.',3 ,6);
INSERT INTO products(name, default_price, default_currency, description,  category_id, supplier_id) VALUES ('Potion of Motivation', 666.69, 'USD', 'Do you feel tired after a long TW week? Grab a bottle!',3 ,5);
INSERT INTO products(name, default_price, default_currency, description,  category_id, supplier_id) VALUES ('Potion of Inspiration', 999.99, 'USD', 'Better than the kiss of any muse.',3 ,5);
INSERT INTO products(name, default_price, default_currency, description,  category_id, supplier_id) VALUES ('Potion of Endurance', 366.99, 'USD', 'The best potion to keep your muscles strong and your heart brave.',3 ,5);
INSERT INTO products(name, default_price, default_currency, description,  category_id, supplier_id) VALUES ('Potion of Vigilance', 449.99, 'USD', 'To keep your eyes peeled even in the greatest exhaustion.',3 ,5);
INSERT INTO products(name, default_price, default_currency, description,  category_id, supplier_id) VALUES ('Cool Talking Parrot', 1000, 'USD', 'Its a pretty cool parrot. You can teach it to swear at you if you dont study.',5 ,7);
INSERT INTO products(name, default_price, default_currency, description,  category_id, supplier_id) VALUES ('Lazy turtle', 300, 'USD', 'Pretty lazy. If you dont do your job, youll end up like it.',5 ,7);
INSERT INTO products(name, default_price, default_currency, description,  category_id, supplier_id) VALUES ('Cactus', 50, 'USD', 'For responsible adults. If you manage to kill this one, there is no hope for you.',6 ,8);
INSERT INTO products(name, default_price, default_currency, description,  category_id, supplier_id) VALUES ('Philodendron', 100, 'USD', 'Green means good job. Brown means bad job.',6 ,8);

---
--- add constraints
---

ALTER TABLE ONLY users
    ADD CONSTRAINT fk_users_order_id FOREIGN KEY (order_id) REFERENCES orders(id);

ALTER TABLE ONLY orders
    ADD CONSTRAINT fk_orders_shipping_description_id FOREIGN KEY (shipping_description_id) REFERENCES shipping_descriptions(id);

ALTER TABLE ONLY orders
    ADD CONSTRAINT fk_orders_user_id FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE ONLY products
    ADD CONSTRAINT fk_products_category_id FOREIGN KEY (category_id) REFERENCES  categories(id);

ALTER TABLE ONLY products
    ADD CONSTRAINT fk_products_supplier_id FOREIGN KEY (supplier_id) REFERENCES  suppliers(id);

ALTER TABLE ONLY line_items
    ADD CONSTRAINT fk_line_items_category_id FOREIGN KEY (order_id) REFERENCES  orders(id);

ALTER TABLE ONLY line_items
    ADD CONSTRAINT fk_line_items_product_id FOREIGN KEY (product_id) REFERENCES  products(id);