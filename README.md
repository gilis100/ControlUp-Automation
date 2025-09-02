# ControlUp-QA-Automation

## **Overview**

This project implements a QA automation framework using Java, TestNG, Selenium WebDriver, and RestAssured.
It covers both UI tests for the SauceDemo application and API tests for the AirportGap
 service, following the Page Object Model (POM) design pattern.

--- 

## **Tech Stack**

- Java 17+

- Maven

- Selenium WebDriver

- TestNG

- RestAssured
---
## **Project Structure**
```
ControlUp-QA-Automation/
│── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── config/                    
│   │   │   │   ├── Config.java
│   │   │   ├── pages/                       
│   │   │   │   ├── BasePage.java
│   │   │   │   ├── LoginPage.java
│   │   │   │   ├── InventoryPage.java
│   │   │   ├── utils/                       
│   │   │   │   ├── DriverManager.java
│   │   ├── resources/
│   │       ├── base.properties            
│   │
│   ├── test/
│   │   ├── java/
│   │   │   ├── base/                    
│   │   │   │   ├── ApiTestBase.java
│   │   │   ├── ui/tests/            
│   │   │   │   ├── VerifyInventoryItemsTest.java
│   │   │   │   ├── VerifyCartBadgeTest.java
│   │   │   ├── api/tests/                    
│   │   │   │   ├── VerifyAirportCountTest.java
│   │   │   │   ├── VerifySpecificAirportsTest.java
│   │   │   │   ├── VerifyAirportDistanceTest.java
│   │   ├── resources/
│   │       ├── simplelogger.properties
│── pom.xml                                  
│── README.md                               
│── .gitignore
```
---

##  **Test Scenarios**

### **UI Tests**
***VerifyInventoryItemsTest.java***
- Login with valid credentials
- Verify 6 items are displayed on the inventory page

***VerifyCartBadgeTest.java***
- Login → Add first item to cart
- Verify cart badge shows 1

### **API Tests**

***VerifyAirportCountTest.java***
- GET /api/airports
- Verify response contains 30 airports
- Negative: Invalid endpoint (/api/airportzzz) → Expect 404 + error payload

***VerifySpecificAirportsTest.java***
- GET /api/airports
- Verify presence of:
    - Akureyri Airport
    - St. Anthony Airport
    - CFB Bagotville
- Negative: Verify TLV Airport does not appear in response

***VerifyAirportDistanceTest.java***
- POST /api/airports/distance with KIX → NRT
- Verify calculated distance is > 400 km
- Negative #1: Missing parameter ("to") → Expect 422 + errors[]
- Negative #2: Invalid code (XXX → NRT) → Expect 422 + errors[]

## How to Run

### Run with Eclipse
1. Open the project in Eclipse.
2. Right-click on a test → select **Run As → TestNG Test** (for a single test class)  
   or **Run As → TestNG Suite** (to run all tests from `testng.xml`).
