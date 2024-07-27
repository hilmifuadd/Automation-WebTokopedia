# Automation-Web-Tokopedia
## Web Test Automation
This project automates the price comparison of products between Tokopedia and Bukalapak using Serenity BDD, Selenium, and Cucumber BDD. It scrapes the product details and displays the result in ascending order of price.

## Prerequisites

- Java Development Kit (JDK) 1.8 or higher
- Apache Maven
- Google Chrome Browser
- ChromeDriver compatible with your Chrome browser version

## Setup

### 1. Clone the Repository

```sh
git clone <repository-url>
cd Automation-Web-Tokopedia
```

### 2. Install Dependencies
Run the following command to download the necessary dependencies:
```sh
mvn clean install
```

### 3. Configure ChromeDriver
Ensure ChromeDriver is installed and is compatible with your installed version of Chrome. Download ChromeDriver from here if necessary.

Set the webdriver.chrome.driver system property to the path of the ChromeDriver executable. This can be done in the serenity.properties file:
```
webdriver.chrome.driver=/path/to/chromedriver
```

### 4. Update serenity.properties
Ensure the serenity.properties file contains the following configurations:
````
serenity.take.screenshots=AFTER_EACH_STEP
ignore.failures.in.stories=true

webdriver.driver=chrome
chrome.switches=--incognito,--start-maximized
webdriver.base.url1=https://www.tokopedia.com/
webdriver.base.url2=https://www.bukalapak.com/
webdriver.timeouts.implicitlywait=10000

story.timeout.in.secs=600
serenity.maintain.session=true
serenity.step.delay=1000
````
### 5. Running The Test
```sh
mvn clean verify
```

### Test Results
The test results and reports will be generated in the target/site/serenity directory. Open the index.html file in a browser to view the detailed test reports.

### Troubleshooting
1. ChromeDriver Version Mismatch
   If you encounter a session not created error related to the ChromeDriver version, ensure that your ChromeDriver version matches your Chrome browser version. Update the ChromeDriver if necessary.

2. Missing Elements
   If the tests fail due to missing elements, ensure that the XPath and CSS selectors in the Page Object classes match the actual HTML structure of the web pages.

3. Network Issues
   Ensure you have a stable internet connection as the tests involve accessing live web pages.

Contribution
Feel free to contribute to this project by submitting pull requests. For major changes, please open an issue first to discuss what you would like to change.

License
This project is licensed under the MIT License.
````vbnet
This `README.md` file provides a detailed guide for setting up, configuring, and running the test automation project. It also includes troubleshooting tips and information on the project structure.
````