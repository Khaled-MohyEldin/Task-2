# AppStore Test Automation 🚀

This project automates testing for the AppStore mobile application using **Appium**, **Java**, and **TestNG**.
It supports Android devices and is designed for scalable, maintainable test execution.

## 📦 Tech Stack

- **Java**
- **Appium 2.x**
- **TestNG**
- **Maven**
- **Android Emulator / Real Device**

## 🧪 Features

- Start and stop Appium server programmatically
- Dynamic driver configuration via `.properties` file
- Page Object Model (POM) structure
- Reusable utility classes (e.g., `PropertyReader`)
- Clean logging and log filtering support
- Environment-ready for CI/CD integration

## 🚀 Getting Started

### Prerequisites

- Java 11+
- Node.js & Appium installed globally (`npm install -g appium`)
- Appium UiAutomator driver Installed 
- Android SDK & emulator setup
- Maven installed

### Setup

1. Clone the repo:
   ```bash
   git clone https://github.com/Khaled-MohyEldin/AppStore-Test-Automation.git
   
2. Install dependencies:
  ``bash
   mvn clean install

3. Configure GlobalData.properties in src/test/resources:

    in properties file 
    ipAddress=127.0.0.1
    port=4723
    driver=uiautomator2

4. Run tests:
   mvn test -PRegression
   mvn test -PSmoke
   
5. See Reults in Allure Reports
   allure serve allure-results

