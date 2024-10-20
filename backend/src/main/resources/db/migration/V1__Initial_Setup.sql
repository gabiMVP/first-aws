CREATE TABLE customer(
    id Bigserial Primary key,
    name text NOT NULL,
    email text NOT NULL ,
    age int NOT NULL
);

Alter table customer
Add Constraint customer_mail_unique UNIQUE(email);


CREATE SEQUENCE customer_id_sequence START 1;
ALTER TABLE customer ALTER COLUMN id SET DEFAULT nextval('customer_id_sequence');