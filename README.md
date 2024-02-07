# Instructions

## Run a Postgres server

## Run Spring Boot application
```
mvn spring-boot:run
```

## Add these rows to the database
```
INSERT INTO public."subscription" (id, term_on_days, "name", currency, price)
VALUES
    (1, 30, 'Monthly', 'usd', 9.99),
    (2, 365, 'Yearly', 'usd', 90);
```

## App flow - check postman collection out. 
1. Register
2. Authenticate
3. Me
4. StripeGetCreditCardToken [NOT REQUIRED] - Simulates a credit cards's stripe plugin form and returns a token if it's successful
5. Buy - Select a subscription and send creditCardToken (the value is tok_visa)
