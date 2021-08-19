# Features
The ui-tests are written using Cucumber which is a automation tool for writing your acceptance tests in a Gherkin syntax (Given, When, Then). The test scenarios can be found in the /src/test/java/features.

# Step Definitions
The implementation of the steps in the feature file scenarios is done using Selenium Webdriver. 

# Browser Support
The tests are possible to run on the following browsers: Chrome, Firefox, Safari, Microsoft Edge, Internet Explorer, PhantomJS (headless browser), Chrome Mobile Emulated (emulated mobile in chrome desktop browser) and Chrome Android (Chrome on a real Android device or Android emulator). 

# Setup
- Download maven from https://maven.apache.org/download.cgi or by running "brew install maven" if using Homebrew.
- To run on Android, device or Emulator ADB (Android Debug Bridge) is required. Download Android SDK from https://developer.android.com/studio/index.html or by running "brew install android-sdk" if using Homebrew.
- To run in Safari, enable Remote Automation in the Develop menu.
- To run in Edge, download webdriver from https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/#downloads and place it in your path

# Run
Execute all test with: mvn test

Execute only a specific feature file: mvn test -Dcucumber.options="src/test/java/Features/FeatureName.feature"

Execute only a specific scenario: mvn test -Dcucumber.options="--name 'ScenarioName'"

Execute on a specific browser: mvn test -DbrowserName=Browser  
Available options are CHROME, FIREFOX, IE, EDGE, SAFARI, CHROME_MOBILE_EMULATED, CHROME_ANDROID and PHANTOMJS


Execute against a specific main URL: mvn test -Durl=url

Set timeout (seconds) to wait on different elements in the tests (default is 10 seconds): mvn test Dtimeout=timeout

# Report
The result from the test execution is saved to a test report under /target/cucumber-html-report


