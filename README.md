# web-test-selenium-mercia

A BDD web-testing framework built with **Selenium WebDriver**, **Cucumber**, and **JUnit** on **Maven**. It targets the demo e-commerce site [https://www.saucedemo.com/](https://www.saucedemo.com/) and covers login, product browsing, cart, and checkout flows.

## Tech stack

| Component | Version | Purpose |
|---|---|---|
| Java | 8 | Language runtime (see `maven-compiler-plugin` config in `pom.xml`) |
| Maven | - | Build and dependency management |
| Selenium Java | 4.15.0 | Browser automation (drivers are resolved automatically by Selenium Manager, no manual ChromeDriver download needed) |
| Cucumber (core/java/junit) | 7.14.1 | BDD Gherkin runner and step glue |
| JUnit | 4.13.2 | Test execution engine, driven by Cucumber's JUnit runner |
| cucumber-reporting | 5.7.7 | HTML test reports |

## Project structure

```
web-test-selenium-mercia/
├── Features/                     # Gherkin feature files (business-readable scenarios)
│   ├── Login.feature
│   ├── Products.feature
│   ├── Cart.feature
│   └── Checkout.feature
├── Drivers/                       # (empty) legacy folder for local driver binaries, unused since Selenium Manager auto-resolves drivers
├── src/test/java/
│   ├── driver/
│   │   └── DriverManager.java     # Singleton enum holding the single shared WebDriver instance for a scenario
│   ├── pageObjects/
│   │   ├── PageContainer.java     # Singleton enum holding one shared instance of every page object
│   │   ├── LoginPage.java
│   │   ├── ProductsPage.java
│   │   ├── ProductDetailPage.java
│   │   ├── CartPage.java
│   │   └── CheckoutPage.java      # Page Object Model classes: locators + actions for each screen
│   ├── stepDefinitions/
│   │   ├── Hooks.java             # @After hook that quits the driver after every scenario
│   │   ├── loginStepDefinition.java
│   │   ├── productStepDefinitions.java
│   │   ├── cartStepDefinitions.java
│   │   └── checkoutStepDefinitions.java   # Glue code mapping Gherkin steps to page object calls
│   ├── testRunner/
│   │   └── TestRun.java           # JUnit/Cucumber entry point (@CucumberOptions: features, glue, plugins)
│   └── utilities/
│       └── commonUtil.java        # Shared helpers, e.g. FluentWait-based waitForElement()
└── pom.xml
```

### How the pieces fit together

1. **Feature files** (`Features/*.feature`) describe scenarios in Gherkin (`Given/When/Then`), tagged with `@tag1`, `@tag2`, etc. for selective execution.
2. **Step definitions** (`stepDefinitions/*.java`) implement each Gherkin step. They don't talk to Selenium directly — they call methods on page objects fetched from `PageContainer.INSTANCE`.
3. **`DriverManager`** is an enum singleton that lazily creates a single `ChromeDriver` instance shared by the whole scenario, because Cucumber-Java instantiates a *new* glue object per step definition class per scenario — a driver field on one step class would otherwise be invisible to another.
4. **`PageContainer`** is the same singleton pattern applied to page objects: it is initialized once per scenario (from `loginStepDefinition`) with the shared driver, so every step definition class sees the same `LoginPage`, `ProductsPage`, `CartPage`, etc.
5. **Page objects** (`pageObjects/*Page.java`) encapsulate locators (`By`) and user actions for one screen, using `commonUtil.waitForElement` for explicit waits.
6. **`Hooks.java`** runs after every scenario (`@After`) and quits the shared driver, so no browser window leaks into the next scenario.
7. **`TestRun.java`** is the JUnit runner that Cucumber uses when you want to execute the whole suite (or a tag subset) in one go; it points at the `Features` folder, wires up the glue packages, and generates an HTML report at `target/cucumber-reports.html`.

## Prerequisites

- JDK 8+ installed and on your `PATH`
- Maven (or use IntelliJ IDEA's bundled Maven)
- Google Chrome installed (the framework drives Chrome via `ChromeDriver`; Selenium 4's built-in Selenium Manager downloads the matching driver automatically — no manual setup required)

## How to run the tests

### Option A — Run a single scenario or feature from IntelliJ IDEA
1. Open the `Features` folder in IntelliJ IDEA (the Cucumber for Java plugin must be installed).
2. Open the desired `.feature` file (e.g. `Login.feature`).
3. Click the green run gutter icon:
   - next to a single `Scenario:` line to run just that scenario, or
   - next to the `Feature:` line to run every scenario in that file.

### Option B — Run the whole suite (or a tag subset) via the JUnit/Cucumber runner
1. Open `src/test/java/testRunner/TestRun.java`.
2. Optionally add a `tags = "@tag1"` (or any other tag expression) to the `@CucumberOptions` annotation to run only matching scenarios.
3. Right-click the file and **Run 'TestRun'** (runs as a JUnit test via the Cucumber JUnit runner).
4. After the run, an HTML report is generated at `target/cucumber-reports.html`.

### Option C — Run from the command line with Maven
From inside `web-test-selenium-mercia/`:

```bash
mvn test
```

This compiles the project and runs `TestRun`, which in turn executes every `.feature` file under `Features/` and writes the HTML report to `target/cucumber-reports.html`.

## Notes

- Chrome runs in a normal (non-headless) window by default, since `DriverManager` creates a plain `new ChromeDriver()`.
- Test data (e.g. `standard_user` / `secret_sauce`) is inline in the feature files, matching SauceDemo's publicly documented demo accounts.
