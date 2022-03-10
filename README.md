# Jumia Exercise

This exercise was fully developed in one day with the purpose of attending the requirements set on the document of this exercise.

## Installation

Use the maven to install build the Jumia Exercise.

```bash
mvn clean install
```

## Usage

The API was developed and documented with [Swagger](http://localhost:8181/jumia/swagger-ui.html#/) that can be accessed by clicking on the link.

The API is based on 4 endpoints: 

### GET/customer/
 - is responsible for listing and filtering the customers by Country and State.

```bash
curl --location --request GET 'localhost:8181/jumia/customer/?country=mozambique&state=not-valid'

curl --location --request GET 'localhost:8181/jumia/customer/?country=mozambique&state=valid'
```

### GET/customer/categorize/country
 - is responsible for listing and categorizing the customers by Country.

```bash
curl --location --request GET 'localhost:8181/jumia/customer/categorize/country'
```

### GET/customer/categorize/country-code
 - is responsible for listing and categorizing the customers by Country Code.

```bash
curl --location --request GET 'localhost:8181/jumia/customer/categorize/country-code'
```

### GET/customer/categorize/valid
 - is responsible for listing and categorizing the customers by Valid or Not-Valid numbers according to the Regex.

```bash
curl --location --request GET 'localhost:8181/jumia/customer/categorize/valid'
```
