CREATE EXTENSION IF NOT EXISTS "uuid-ossp";


CREATE TABLE IF NOT EXISTS T_Person (
    first_name varchar(25) NOT NULL,
    last_name varchar(25) NOT NULL,
    postcode int NOT NULL,
    username varchar(255) PRIMARY KEY,
    password varchar(255) NOT NULL
);


CREATE TABLE IF NOT EXISTS T_Items(
    itemID uuid DEFAULT uuid_generate_v4 () PRIMARY KEY,
    item_name varchar(30),
    price int NOT NULL,
    quantity int NOT NULL
    );

CREATE TYPE status AS ENUM ('processing', 'packed', 'sent');
CREATE TABLE IF NOT EXISTS T_Order(
    orderID uuid DEFAULT uuid_generate_v4 (),
    total_amount int NOT NULL,
    userID varchar (255) NOT NULL,
    current_status status,
    CONSTRAINT order_pk PRIMARY KEY (orderID, userID),
    CONSTRAINT userID FOREIGN KEY (userID) REFERENCES T_Person(username)
);

CREATE TABLE IF NOT EXISTS T_ItemsOrder(
    orderID uuid NOT NULL,
    itemID uuid NOT NULL,
    amount int NOT NULL,
    userID varchar(255) NOT NULL ,
    CONSTRAINT itemsorder_pk PRIMARY KEY (itemID, orderID, userID),
    CONSTRAINT orderid_fk FOREIGN KEY (orderID,userID) REFERENCES T_Order (orderID,userID),
    CONSTRAINT itemid_fk FOREIGN KEY (itemID) REFERENCES T_Items(itemID),
    CHECK ( amount >= 0 )
);

INSERT INTO T_Person (first_name, last_name, postcode, username, password)
VALUES ('Viktor', 'Lindström', 14152, 'viktor', 'groda');