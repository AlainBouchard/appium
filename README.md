# appium

## What do you need to know to elaborate a QA strategy for an existing Scrum Team?

It is important to understand the current position of the global test strategy of a "product" when working to improve it. **Involving the "product" Scrum Team in the decision** in a key factor to the success.

The questions to ask and the points to investigate in order to have the current situation status are the following:

* What is the current test strategy and what is it?
  * For a Mobile App?
  * For Front End application (or Web)?
  * For the Back End (is it a monolith or a cluster of services/microservices, etc?)
* What is the current product delivery solution or strategy?
  * Is there any CI/CD in the current strategy?
  * If there is any CD, is CD for Continuous Delivery or Continuous Deployment?
  * What is the current testing flow and quality gates?
  * Is there any monitoring tools? (Datadog, SignalFX, Prometheus, etc)
* What are the current pain points?
  * For the internal customers?
  * For the external customers?
  * Is there any satisfaction surveys? (example: [NPS surveys](https://en.wikipedia.org/wiki/Net_promoter_score) or [CSAT serveys](https://en.wikipedia.org/wiki/Customer_satisfaction))
* Technologies used for communication between services:
  * REST/SOAP APIs?
  * GraphQL APIs?
  * Event based/streaming (example: Kafka, RabbitMQ, etc.)
* Is the test terminology defined yet?  What is...
  * [Unit test](https://martinfowler.com/bliki/UnitTest.html)? (both `Solitary` and `Sociable` unit tests)
  * [Component test](https://martinfowler.com/bliki/ComponentTest.html)? (both `In-Process` and `Out of Process` component tests)
  * [Integration tests](https://martinfowler.com/bliki/IntegrationTest.html)? (both `Narrow` and `Broad` integration tests)
  * [Contract testing](https://martinfowler.com/bliki/ContractTest.html)? (both `Consumer` and `Provider` contract tests, for example with [pact.io](https://pact.io/), etc)
  * [End to end tests](https://martinfowler.com/bliki/BroadStackTest.html)? (both UI and API tests)
  * [User Journey tests](https://martinfowler.com/bliki/UserJourneyTest.html)?
  * [Smoke or Sanitary tests](https://en.wikipedia.org/wiki/Smoke_testing_(software)) and Heath tests
  * What is a [Test Double](https://martinfowler.com/bliki/TestDouble.html)?
  * CI and CD? (what is the difference between [Continuous Integration, Continuous Deployment vs Delivery?](https://www.atlassian.com/continuous-delivery/principles/continuous-integration-vs-delivery-vs-deployment))
  * [Blue/Green](https://martinfowler.com/bliki/BlueGreenDeployment.html) deployment or [Canary](https://martinfowler.com/bliki/CanaryRelease.html) releasing?
  * Functional vs Non-Functional testing?
    * Load, Scalability, Stress, Stability/Soak, Robustness/Chaos testing, etc.
    * What is a NFR (Non-Functional Requirements), example: `Given X data with Y concurent users, the response time should be Z ms`

## What do you need to know in order to make a Mobile Application Test Strategy?

* What are the Flag Phone used by the customers? (example: is there any statistics of the most used phones?)
* Is the application a native, a web or an hybrid application?
* What is sharing both the Mobile and the Web applications?
* How confident are you in your software quality?
* Can we use 3PP solutions like [SauceLabs](https://saucelabs.com/platform/mobile-testing), [BrowserStack](https://www.browserstack.com/guide/mobile-testing) and [AWS Device Farm](https://aws.amazon.com/device-farm/)?

## Example of a test strategy

### For a Mobile Application:

1. The Developper update the Mobile App on his local (example: GIT branch)
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
    1. Move the application to Background/Forground
    1. Enable/Disable hardware (WiFi, LTE, GPS, etc)
    1. Run User Journey tests and visual tests (color, rendering, etc)

### For Web Application:

[Shift-Left Approach](https://medium.com/cloudscaleqa/best-practices-for-shift-right-and-shift-left-testing-approaches-in-an-agile-environment-c95dcb1e621e):

1. The Developper update the Front End service on his local (example: GIT branch)
    1. Add/Update Unit tests
    1. Update the Code to make the test pass
    1. The developer run the Unit Tests, UI Unit Tests, Component and maybe a few narrow integration tests (Docker/Mock Server may be used)
    1. GIT push/Open a PR
1. The PR on CI
    1. Run [SonarQube](https://www.sonarqube.org/) (or else) for security and/or test coverage
    1. Run unit and component tests
    1. Run contract tests
    1. Run functional (narrow integration) tests regression using container technologies (example: docker, docker-compose, etc)
    1. Run integration tests regression against Back End services cluster (staging/test cluster?)
1. Merge the PR
    1. Run [SonarQube](https://www.sonarqube.org/) (or else) for security and/or test coverage
    1. Run contract tests
    1. Run functional (narrow integration) tests regression (no integration tests)
    1. Run integration tests regression against Back End services cluster (staging/test cluster?)
    1. Run User Journey Tests
    1. Run a few automated visual tests (example: [Percy.io](https://percy.io/visual-testing), [applitools](https://applitools.com/blog/visual-testing/), etc.)
    1. Define the release as release candidate? (Example Continuous Delivery) or deploy/release on production (Continuous Deployment) according to the product maturity

[Shift Right Approach](https://medium.com/cloudscaleqa/best-practices-for-shift-right-and-shift-left-testing-approaches-in-an-agile-environment-c95dcb1e621e):

1. Monitoring Tools (Datadog with reports and alarms, etc)
1. Non-Functional Load tests (with **realistic trafic only** like 1x or 2x the expected trafic on a  Production Cluster for Tests)
1. User Journey/Eploratory manual/automated functional tests

## References

* Shift Left/Right Approaches: https://medium.com/cloudscaleqa/best-practices-for-shift-right-and-shift-left-testing-approaches-in-an-agile-environment-c95dcb1e621e
* Applitools: https://applitools.com/blog/visual-testing/
* AWS Device Farm: https://aws.amazon.com/device-farm/
* BrowserStack Mobile Testing: https://www.browserstack.com/guide/mobile-testing
* Atlassian: https://www.atlassian.com/continuous-delivery/principles/continuous-integration-vs-delivery-vs-deployment
* Martin Fowler: https://martinfowler.com/
* Percy.io: https://percy.io/visual-testing
* SauceLabs Mobile Testing: https://saucelabs.com/platform/mobile-testing
* SonarQube: https://www.sonarqube.org/