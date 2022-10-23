CREATE TABLE IF NOT EXISTS PAYMENT
(
    id            bigint NOT NULL,
    amount        double NOT NULL,
    parcel_status varchar(255) DEFAULT NULL,
    payment_url   varchar(255) DEFAULT NULL,
    payment_id     varchar(255) DEFAULT NULL,
    parcel_id     varchar(255) DEFAULT NULL
);

INSERT INTO PAYMENT(ID, AMOUNT, PARCEL_STATUS, payment_url, payment_id, parcel_id) VALUES (1, 1, 'PAID', 'test.pl',
                                                                                          'paypal', '10001');