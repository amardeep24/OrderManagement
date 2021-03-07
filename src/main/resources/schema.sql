DROP ALL OBJECTS;

CREATE TABLE customer (
  customer_id VARCHAR(250)  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  address VARCHAR(250) DEFAULT NULL,
  phone INT NOT NULL,
  email VARCHAR NOT NULL
);


CREATE TABLE product (
  product_id INT AUTO_INCREMENT  PRIMARY KEY,
  product_name VARCHAR(250) NOT NULL,
  description VARCHAR(250) NOT NULL,
  manufacturer VARCHAR(250) NOT NULL
);


CREATE TABLE orders (
  order_id INT AUTO_INCREMENT  PRIMARY KEY,
  order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  customer_id VARCHAR(250) NOT NULL,
  product_id INT NOT NULL,
  order_total NUMERIC NOT NULL,
  quantity INT NOT NULL,
  foreign key (customer_id) references customer(customer_id),
  foreign key (product_id) references product(product_id)
);


CREATE TABLE inventory (
  inventory_id INT AUTO_INCREMENT  PRIMARY KEY,
  product_id INT NOT NULL,
  quantity INT NOT NULL,
  price NUMERIC NOT NULL,
  foreign key (product_id) references product(product_id)
);
