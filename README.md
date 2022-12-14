
# ClusteredData Warehouse

This app is to accept forex transaction details of currencies and persist them into DB.

One can perform the following operations:
* Create new forex transactions
* Get all created forex transactions 
* Get a forex transaction using the transaction id.

##  To run the project locally

* Run this maven wrapper command

```sh
./mvnw clean install 
```

* Make sure you have docker installed on your machine. Then run
```sh
docker compose up
```

## After running the project, ensure to run
```sh
docker compose down
```
* This is to remove the containers that were created

## Project Documentation

### Technology Used
* SPRINGBOOT
* MYSQL
* DOCKER, DOCKER COMPOSE

## Project Packages
### Controller
* POST - /api/v1/transaction/save - is used to create a new forex transaction.
* GET - /api/v1/transaction/{id} - is used to get a forex transaction using the id.
* GET - /api/v1/transaction - used to get all transactions.

### Exception
* The CustomExceptionHandler class handles all exceptions

### Logging
* Logging was done using spring's lombok's @Slf4j logging facade.

### Model

* id - unique id for each transaction saved in the database : Long
* senderCurrencyISO - Sender's Currency ISO : String
* recipientCurrencyISO - Recipient's Currency ISO : String
* createdAt - Timestamp of when the record was inserted: Timestamp
* amount - Amount of the transaction: BigDecimal

### Repository
* Spring data JPA

### Service
* Service Implementation which contains the business logic.

### Request body
```json
{
  "recipientCurrencyISO": "USD",
  
  "senderCurrencyISO": "GBP",
  
  "amount": 100.00
}
```

### Success Response
```json
{
  "statusCode": 201,
  "success": true,
  "body": {
    "transactionId": "31b559b9-a4af-4f4c-a07c-e85ce3ee44d3",
    "recipientCurrencyISO": "USD",
    "senderCurrencyISO": "CAD",
    "amount": 100.00,
    "createdAt": "2022-12-01T15:20:03.925+00:00"
  }
}
```

### Failed Response
```json
{
  "statusCode": 400,
  "success": false,
  "message": "Currency can only be letters",
  "timestamp": "2022-12-01T15:24:31.544+00:00"
}
```

TEST
- Unit test in the test folder covers:
* FOREX Transaction Controller
