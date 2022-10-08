
CREATE TABLE IF NOT EXISTS ROUTE (
id varchar(255) NOT NULL,
created datetime DEFAULT NULL,
depot_id bigint DEFAULT NULL,
parcel_id varchar(255) DEFAULT NULL,
supplier_id bigint DEFAULT NULL,
 user_id int DEFAULT NULL
);
CREATE TABLE IF NOT EXISTS USERS (
id int NOT NULL,
email varchar(255) NOT NULL,
first_name varchar(255) DEFAULT NULL,
last_name varchar(255) DEFAULT NULL,
password varchar(255) NOT NULL,
role varchar(255) DEFAULT NULL,
username varchar(255) NOT NULL,
depot_id bigint DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS PAYMENT (
id bigint NOT NULL,
amount double NOT NULL,
parcel_status int DEFAULT NULL,
payment_pass int DEFAULT NULL,
payment_url varchar(255) DEFAULT NULL,
paypal_id varchar(255) DEFAULT NULL,
parcel_id varchar(255) DEFAULT NULL
);
CREATE TABLE REROUTE_TOKEN (
id bigint NOT NULL,
created_date datetime DEFAULT NULL,
timeout datetime DEFAULT NULL,
token bigint DEFAULT NULL,
parcel_id bigint DEFAULT NULL
);