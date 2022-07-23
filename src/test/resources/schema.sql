CREATE TABLE IF NOT EXISTS PARCEL (
    `id` VARCHAR(255) NOT NULL,
    `first_name` VARCHAR(255) NULL DEFAULT NULL,
    `last_name` VARCHAR(255) NULL DEFAULT NULL,
    `parcel_type` INT NULL DEFAULT NULL,
    `payment_status` INT NULL DEFAULT NULL,
    `recipient_city` VARCHAR(255) NULL DEFAULT NULL,
    `recipient_email` VARCHAR(255) NULL DEFAULT NULL,
    `recipient_first_name` VARCHAR(255) NULL DEFAULT NULL,
    `recipient_last_name` VARCHAR(255) NULL DEFAULT NULL,
    `recipient_postal_code` VARCHAR(255) NULL DEFAULT NULL,
    `recipient_street` VARCHAR(255) NULL DEFAULT NULL,
    `recipient_telephone` VARCHAR(255) NULL DEFAULT NULL,
    `sender_email` VARCHAR(255) NULL DEFAULT NULL,
    `sender_telephone` VARCHAR(255) NULL DEFAULT NULL,
    `price` DOUBLE NOT NULL,
    `parcel_status` INT NULL DEFAULT NULL,
    `parcel_size` VARCHAR(255) NULL DEFAULT NULL
);
CREATE TABLE IF NOT EXISTS ROUTE (
`id` varchar(255) NOT NULL,
`created` datetime DEFAULT NULL,
`depot_id` bigint DEFAULT NULL,
`parcel_id` varchar(255) DEFAULT NULL,
`supplier_id` bigint DEFAULT NULL,
 `user_id` int DEFAULT NULL
);
CREATE TABLE IF NOT EXISTS USERS (
`id` int NOT NULL,
`email` varchar(255) NOT NULL,
`first_name` varchar(255) DEFAULT NULL,
`last_name` varchar(255) DEFAULT NULL,
`password` varchar(255) NOT NULL,
`role` varchar(255) DEFAULT NULL,
`username` varchar(255) NOT NULL,
`depot_id` bigint DEFAULT NULL
);
CREATE TABLE IF NOT EXISTS DEPOT (
`id` bigint NOT NULL AUTO_INCREMENT,
`city` varchar(255) DEFAULT NULL,
`country` varchar(255) DEFAULT NULL,
`depot_code` varchar(255) DEFAULT NULL,
`street` varchar(255) DEFAULT NULL
);
CREATE TABLE IF NOT EXISTS PAYMENT (
`id` bigint NOT NULL,
`amount` double NOT NULL,
`parcel_status` int DEFAULT NULL,
`payment_pass` int DEFAULT NULL,
`payment_url` varchar(255) DEFAULT NULL,
`paypal_id` varchar(255) DEFAULT NULL,
`parcel_id` varchar(255) DEFAULT NULL
);
