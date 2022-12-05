**INPARCEL SPRING BOOT APPLICATION 1.6.1**

Technologies:
- JAVA 17
- SPRING BOOT 2.7.2
- MySQL
- Hibernate
- Paypal API


Endpoints:

SHIPMENT
- POST /shipment <- saves parcel in db and sends notification
- GET /parcels/[:id] <- returns parcel by given id

BARCODE
- GET /barcode/[:id]/label <- generates label for parcel with given id.

![](warehouse-web-app/z_img/parcels/parcel_label.png)


ROUTE

- POST /route <- save route
- GET /route/by-parcel/[:id] <- Returns a route history of parcel by given parcel ID
- GET /route/by-username/[:username]/username <- returns a route history for parcel by username

REROUTE

- POST /reroute <- updates parcel with given id and reroute token
- POST /reroute/information <- sends request and rerouting information
- GET /token/[:value] <- returns reroute token with given token value
- GET /token/[:value]/parcel/[:parcelId] <- returns token by token value and parcel id
- GET /valid/token/[:value]/parcel/[:parcelId] <- returns boolean value if token is valid

PAYMENT
- POST /payment/pay <- sends payment request to Paypal
- GET /payment/pay/success <- returns if payment was success
- GET /payment/pay/cancel <- returns if payment failed


AUTH

- POST /auth/login -> login to application
- GET /auth/current-user -> returns currently logged in user
- POST /auth/signup -> signup with email and password
- POST /auth/logout -> logout
- GET /auth/[:username] -> returns user by given username




This project is used for GUI application as a backend server that communicates with database.
Check [GUI project](https://gitlab.com/sebastiansoja/warehouse-web-app-fr) and Check [MG project](https://gitlab.com/sebastiansoja/warehouse-web-app-mg).

