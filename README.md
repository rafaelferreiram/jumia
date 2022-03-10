# Jumia Exercise

This exercise was fully developed in one day with the purpose of attending the requirements set on the document of this exercise.

## Technologies
* Java 11
* Springboot
* Docker
* Swagger
* JUnit


## Installation

Use the maven to install to  build the Jumia Exercisen Project.

```bash
mvn clean install
```

As we have our Dockerfile set up and our pom.xml configured, we can go onto the next step to generate a docker image.

To do so we have to go to our project root and run the following command, which will generate a .jar file inside the Target folder.

```bash
mvn package
```
After running the ‘mvn package’ we can check for our local docker images and see if its there. We can do it by running

```bash
docker images
```
To run it in background mode we just have to add a property in the command. ‘-d’.

```bash
docker run -d -p 8080:8080 rafaelferreiram/jumia-exercise:0.0.1-SNAPSHOT
```

After starting up our Project application we can check our Swagger (API Documentation) :
[http://localhost:8080/jumia/swagger-ui.html](http://localhost:8080/jumia/swagger-ui.html#/)


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
