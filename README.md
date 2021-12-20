# testing-vision

## What do you need to know to elaborate a QA strategy for an existing Scrum Team?

It is important to understand the current position of the global test strategy of a "product" when working to improve it. **Involving the "product" Scrum Team in the decisions** in a key factor to the success.

The questions to ask and the points to investigate in order to have the current situation status are the following:

* What is the current test strategy?
  * For a Mobile App?
  * For Front End application (or Web)?
  * For the Back End (is it a monolith or a cluster of services/microservices, etc.?)
* What is the current product delivery solution or strategy?
  * Is there any CI/CD in the current strategy?
  * If there is any CD, is CD for Continuous Delivery or Continuous Deployment?
  * What is the current testing flow and quality gates?
  * Are there any monitoring tools? (Datadog, SignalFX, Prometheus, etc.)
* What are the current pain points?
  * For the internal customers?
  * For the external customers?
  * Are there any satisfaction surveys? (example: [NPS surveys](https://en.wikipedia.org/wiki/Net_promoter_score) or [CSAT surveys](https://en.wikipedia.org/wiki/Customer_satisfaction))
* Technologies used for communication between services:
  * REST/SOAP APIs?
  * GraphQL APIs?
  * Event based/streaming (example: Kafka, RabbitMQ, etc.)
* Is the test terminology defined yet?  What is...
  * [Unit test](https://martinfowler.com/bliki/UnitTest.html)? (both `Solitary` and `Sociable` unit tests)
  * [Component test](https://martinfowler.com/bliki/ComponentTest.html)? (both `In-Process` and `Out of Process` component tests)
  * [Integration tests](https://martinfowler.com/bliki/IntegrationTest.html)? (both `Narrow` and `Broad` integration tests)
  * [Contract testing](https://martinfowler.com/bliki/ContractTest.html)? (both `Consumer` and `Provider` contract tests, for example with [pact.io](https://pact.io/), etc.)
  * [End to end tests](https://martinfowler.com/bliki/BroadStackTest.html)? (both UI and API tests)
  * [User Journey tests](https://martinfowler.com/bliki/UserJourneyTest.html)?
  * [Smoke or Sanitary tests](https://en.wikipedia.org/wiki/Smoke_testing_(software)) and Heath tests
  * What is a [Test Double](https://martinfowler.com/bliki/TestDouble.html)?
  * CI and CD? (what is the difference between [Continuous Integration, Continuous Deployment vs Delivery?](https://www.atlassian.com/continuous-delivery/principles/continuous-integration-vs-delivery-vs-deployment))
  * [Blue/Green](https://martinfowler.com/bliki/BlueGreenDeployment.html) deployment or [Canary](https://martinfowler.com/bliki/CanaryRelease.html) releasing?
  * Functional vs Non-Functional testing?
    * Load, Scalability, Stress, Stability/Soak, Robustness/Chaos testing, etc.
    * What is a NFR (Non-Functional Requirements), example: `Given X data with Y concurrent users, the response time should be Z ms`

## What do you need to know in order to make a Mobile Application Test Strategy?

* What are the Flag Phones used by the customers? (Example: is there any statistics of the most used phones four our application?)
* Is the application a native, a web or an hybrid application?
* What is sharing both the Mobile and the Web applications?
* How confident are you in your software quality?
* Can we use 3PP solutions like [SauceLabs](https://saucelabs.com/platform/mobile-testing), [BrowserStack](https://www.browserstack.com/guide/mobile-testing) and [AWS Device Farm](https://aws.amazon.com/device-farm/)?

## Example of a test strategy

This section is an example; there is no "one t-shirt fits all" solution. Some points may be missing while others may not be needed. Product and Scrum Team maturity may also affect the whole testing flow in order to either deliver faster, or with more validation.

### [Shift-Left Approach](https://medium.com/cloudscaleqa/best-practices-for-shift-right-and-shift-left-testing-approaches-in-an-agile-environment-c95dcb1e621e)

#### For a Mobile Application

1. The Developer updates the Mobile App on his local (example: GIT branch)
    1. Add/Update Unit tests
    1. Update the Code to make the test pass
    1. The developer run the Unit Tests, Component and maybe a few narrow integration tests (Docker/Mock server may be used)
    1. The developer run automated UI tests using the Phone Emulators (Flag Phones only)
    1. GIT push/Open a PR
1. The PR on CI
    1. Run [SonarQube](https://www.sonarqube.org/) (or else) for security and/or test coverage
    1. Run unit and component tests
    1. Run contract tests
    1. Run functional (narrow integration) tests regression on `a few Flag Phones` (Android/iOS) Emulators with various screen sizes, etc.
    1. Run integration tests regression against Back End services cluster (staging/test cluster?)
1. Merge the PR
    1. Run [SonarQube](https://www.sonarqube.org/) (or else) for security and/or test coverage
    1. Run unit and component tests
    1. Run contract tests
    1. Run functional (narrow integration) tests regression on `all Flag Phones` (Android/iOS) Emulators with various screen sizes, etc.
    1. Run integration tests regression against Back End services cluster (staging/test cluster?)
    1. Run User Journey Tests on real Flag Phones
    1. Run a few automated visual tests (example: [Percy.io](https://percy.io/visual-testing), [applitools](https://applitools.com/blog/visual-testing/), etc.)
    1. Define the release as release candidate? (Example Continuous Delivery)
1. Real Phone manual tests:
    1. Lock/Unlock screen with the application
    1. Move the application to Background/Foreground
    1. Enable/Disable hardware (WiFi, LTE, GPS, etc)
    1. Moving from protrait to landscape back and forth
    1. Run User Journey tests and visual tests (color, rendering, etc.)

#### For Web and Back-End services

1. The Developer update the Front-End services on his local (example: GIT branch)
    1. Add/Update Unit tests
    1. Update the Code to make the test pass
    1. The developer run the Unit Tests, UI Unit Tests, Component and maybe a few narrow integration tests (Docker/Mock Server may be used)
    1. GIT push/Open a PR
1. The PR on CI
    1. Run [SonarQube](https://www.sonarqube.org/) (or else) for security and/or test coverage
    1. Run unit and component tests
    1. Run contract tests
    1. Run functional (narrow integration) tests regression using container technologies (example: docker, docker-compose, etc.)
    1. Run integration tests regression against Back End services cluster (staging/test cluster?)
1. Merge the PR
    1. Run [SonarQube](https://www.sonarqube.org/) (or else) for security and/or test coverage
    1. Run contract tests
    1. Run functional (narrow integration) tests regression (no integration tests)
    1. Run integration tests regression against Back End services cluster (staging/test cluster?)
    1. Run User Journey Tests
    1. Run a few automated visual tests (example: [Percy.io](https://percy.io/visual-testing), [applitools](https://applitools.com/blog/visual-testing/), etc.)
    1. Define the release as a `Release Candidate`? (Example: Continuous Delivery vs deploy and promote release on production with Continuous Deployment; it depends of the product maturity)

### [Shift-Right Approach](https://medium.com/cloudscaleqa/best-practices-for-shift-right-and-shift-left-testing-approaches-in-an-agile-environment-c95dcb1e621e)

1. Monitoring Tools (Datadog with reports and alarms, etc.)
1. Non-Functional Load tests (with **realistic traffic only** like 1x or 2x the expected traffic on a  Production Cluster for Tests)
1. User Journey Automated tests (example: cronjob)
1. Exploratory Manual tests

## Installing Appium tests for the Mobile App

This project contains one test only and the test **scenario is only an example of one use case**, and therefore the application edge cases (example: promotion popups, etc.) **were not** handled.

A [video can be download](https://drive.google.com/file/d/1xcEKmPijsjm9Fbr1PEWboRSj8nVD-W8g/view?usp=sharing) as a SauceLabs test run example.

### Technology stack selection (ADR)

The proper way to select a tool and a technology stack for a given project is to create an [ADR (or an Architecture Decision Record)](https://adr.github.io/) that will require a problem statement, a few suggested options, a pros/cons analysis and a decision record.

*Author's note: I have no strong opinion on the available technologies: every problem has its solution, and of course, taking the Scrum Team concerns and suggestions is a key factor in a solution adoption.*

#### UI Testing (Mobile App)

It takes time to create an ADR since available options may require spikes so that Appium and Java were used for this project. Appium is a well known and integrated solution for mobile application testing since tests can be written once and then can run on many targets (examples: Android, iOS, browsers). Java has been used for this project since Hopper is using Scala and JVM; it is complicated to find Software Developers in Tests with Scala experience, so Java seems to be a good mitigation solution.

#### API Testing (Back End)

The ADR for API testing would contain technologies like Java, Python and JavaScript. Java with [Rest-Assured](https://rest-assured.io/) (or [Unirest](https://www.baeldung.com/unirest)) would make a great job, however writing tests with Python (using PyTest, Tox, requests, etc) or JavaScript are faster (no build time) and take less VM resource since no JVM to run. The ADR would consider the above criteria and the Scrum Team preferences. There is currently no API tests in this project.

#### Browser testing (UI)

The ADR for UI only testing would consider the test development speed, cost (paid plans), and Scrum Team language preference. Tools like Cypress.io, Puppeteer (not a testing tool but an automation tool), WebDriver.io (selenium), Nightwatch, etc can be evaluated. If the goal is to build a QA Team then JavaScript may be a better choice than Java since JavaScript is very easy to learn (fast learning curve), many Software Developers in Tests already use JavaScript and many tools are available for JavaScript.

### Run on local

1. Download or Clone the GIT repo [https://github.com/AlainBouchard/appium]
1. Java JDK should be already installed for your OS (Example: This project is built on [OpenJDK: jdk-11.0.8.10-hotspot](https://www.openlogic.com/openjdk-downloads)
    1. Make sure `JAVA_HOME` environment variable is set
    1. Copy&Paste the `JAVE_HOME` location to a notepad for future reference (Example: `JAVA_HOME=C:\Program Files\OpenJDK\jdk-11.0.8.10-hotspot\`)
1. Download and Install Android Studio from [https://developer.android.com/studio]
    1. From "more actions" -> SDK Manager -> Copy&Paste the "Android SDK Location" on a notepad
    1. Example: `C:\Users\abouc\AppData\Local\Android\Sdk`
    1. From "SDK Platforms" Tab -> Install "Android 9 (Pie)" for this project (Note: other versions haven't been tried)
    1. From "SDK Tools" Tab -> Select the following (see below) -> Click "OK" Button -> Follow installation steps if needed
        1. Android Emulator
        1. Android SDK Platform-tools
            1. Intel x86 Emulator Accelerator HAXM installer
    1. From "more actions" -> Select "AVD Manager" -> Click "Create virtual device"
       1. Select an Android phone (Example: `Pixel 3`) -> Click "Next" Button
       1. Click on wanted version (Example `Pie`) -> Download (if needed) -> Follow installation steps
          1. Once downloaded -> Click "Next" Button -> Select Name (Example: Appium or keep the suggested one)
             1. Example name: `Pixel 3 API 28`
          1. Try by clicking "Play" button

1. Download and Install Appium Server GUI
    1. Download from GITHUB release page [https://github.com/appium/appium-desktop/releases/tag/v1.22.0]
        1. Start the application
    1. Click "Edit Configuration"
    1. Copy both `JAVA_HOME` (if required) and Android SDK paths from previously copy&pasted paths:
        1. `C:\Users\abouc\AppData\Local\Android\Sdk`
        1. `C:\Program Files\OpenJDK\jdk-11.0.8.10-hotspot\`
    1. Start the Server using the default IP and PORT (Example: `http://0.0.0.0:4723`)
    1. Note: you may need to manually install [Appium Inspector](https://github.com/appium/appium-inspector) from v. 1.22.0.

1. From prompt: run `./gradlew test --info --rerun-tasks`

### Run on SauceLabs

1. Download or Clone the GITHUB repo [https://github.com/AlainBouchard/appium]
1. Create an account on [SauceLabs](https://saucelabs.com/platform/mobile-testing) for device testing (A trial account is fine for this example)
    1. Copy the `Username`, the `Access Key` and the `Url` to a notepad for future reference
    1. Set or Export the following environment variables:

   ```text
      HOPPER_PLATFORM_NAME=Android
      HOPPER_DEVICE_NAME=Google Pixel 3 GoogleAPI Emulator
      HOPPER_PLATFORM_VERSION=9
      HOPPER_APK_FILENAME=https://github.com/AlainBouchard/appium/raw/master/apk/hopper_4.81.1-116270.apk
      HOPPER_APPIUM_URL=<SAUCELABS_URL>
      HOPPER_SAUCELABS_APPIUM_VERSION=1.20.2
      HOPPER_SAUCELABS_USERNAME=<SAUCELABS_USERNAME>
      HOPPER_SAUCELABS_KEY=<SAUCELABS_ACCESSKEY>
    ```

1. from prompt: run `./gradlew test --info --rerun-tasks`
    1. A [video can be download](https://drive.google.com/file/d/1xcEKmPijsjm9Fbr1PEWboRSj8nVD-W8g/view?usp=sharing) as a SauceLabs test run example.

### Run using Dockerfile and SauceLabs

1. Same as for [Run on SauceLabs](#run-on-saucelabs)
2. Create a file with the variable contents (example: `docker-saucelabs.env`)
3. run `docker build -t hopper-android-tests:local .`
4. run `docker run --env-file=docker-saucelabs.env hopper-android-tests:local`

## Installing Cypress.io for browsers testing (non-mobile apps)

Cypress is a JavaScript solution to test the web version of the application. It is not possible to test with mobiles or mobile emulators. It is a JavaScript solution to test with browsers and many screen resolutions (viewports), for computers (examples: windows, macs, etc.), android and iPhones. Using Cypress is only an example and may not be needed since Appium/WebDriver/Selenium is also an option for browser testing.

A [video can be downloaded](https://drive.google.com/file/d/1javElFI_EGsQ5zxA74syDNFCqAWduxOO/view?usp=sharing) as a Cypress test run example.

### In order to run Cypress from the terminal:

1. Make sure that nodejs is installed or it can be download from [https://nodejs.dev/download/] (Example: the project has been developed using nodejs 14)
1. From this project -> got to `cypress.io` directory
1. Run `npm ci` from the command line in order to install the npm packages
1. To run tests:
    1. Using the Cypress test runner: `npx cypress open` and select the test to run
    1. Using the Cypress CLI: `npm test` or `npm run test:record`
        1. The `test:record` will send the test data, screenshots, videos, etc. to the Cypress Dashboard
        1. The project dashboard on cypress.io can be accessed using the following [secret](https://onetimesecret.com/secret/hxiv62r74hnakoamlumfixjhgto0sp1) (contact me on my [LinkedIn](https://www.linkedin.com/in/alainbouchard/) for temporary access after the December 3rd, 2021)
    1. Using Docker:
        1. Build the image `docker build -t hopper-cypress:local .`
        1. Run the tests `docker run hopper-cypress:local`
        1. The [COMMIT_* environment variables](https://docs.cypress.io/guides/continuous-integration/introduction#Git-information) can be set in the CI in order to send the Git branch information to Cypress.io

## References

* ADR from GitHub: [https://adr.github.io/]
* AppliTools: [https://applitools.com/blog/visual-testing/]
* AWS Device Farm: [https://aws.amazon.com/device-farm/]
* BrowserStack Mobile Testing: [https://www.browserstack.com/guide/mobile-testing]
* Atlassian: [https://www.atlassian.com/continuous-delivery/principles/continuous-integration-vs-delivery-vs-deployment]
* Martin Fowler: [https://martinfowler.com/]
* Percy.io: [https://percy.io/visual-testing]
* SauceLabs Mobile Testing: [https://saucelabs.com/platform/mobile-testing]
* Shift Left/Right Approaches: [https://medium.com/cloudscaleqa/best-practices-for-shift-right-and-shift-left-testing-approaches-in-an-agile-environment-c95dcb1e621e]
* SonarQube: [https://www.sonarqube.org/]
