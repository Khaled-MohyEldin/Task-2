public class AppTest {

//    WebDriver driver;
//    @BeforeMethod
//    public void Initialization(){
//        driver = new FirefoxDriver();
//        driver.get("https://rahulshettyacademy.com/angularpractice/");
//        System.out.println();
//    }

//    @Test
//    public void bomBom() {
//    }
//
//    @AfterMethod
//    public void TearDown(){
//        driver.close();
//    }

// Page Object Class for a sample login page
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import java.time.Duration;

    /**
     * Page object representing a login page.
     * This class encapsulates the locators and methods for the login page.
     * It uses WebDriverWait to handle synchronization issues.
     */
//    public class LoginPage {
//
//        private final WebDriver driver;
//        private final WebDriverWait wait;
//
//        // Locators for elements on the login page
//        private final By usernameInput = By.id("username");
//        private final By passwordInput = By.id("password");
//        private final By loginButton = By.id("login-button");
//        private final By successMessage = By.xpath("//div[@id='success-message']");
//
//        /**
//         * Constructor to initialize the WebDriver and WebDriverWait.
//         * @param driver The WebDriver instance to be used.
//         */
//        public LoginPage(WebDriver driver) {
//            this.driver = driver;
//            // Initialize WebDriverWait with a 10-second timeout
//            this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        }
//
//        /**
//         * Fills in the username field after waiting for it to be visible.
//         * @param username The username to enter.
//         */
//        public void enterUsername(String username) {
//            // Wait for the username input field to be visible before interacting with it
//            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
//            usernameField.sendKeys(username);
//        }
//
//        /**
//         * Fills in the password field after waiting for it to be visible.
//         * @param password The password to enter.
//         */
//        public void enterPassword(String password) {
//            // Wait for the password input field to be visible before interacting with it
//            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
//            passwordField.sendKeys(password);
//        }
//
//        /**
//         * Clicks the login button after waiting for it to be clickable.
//         */
//        public void clickLoginButton() {
//            // Wait for the login button to be clickable before clicking
//            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
//            button.click();
//        }
//
//        /**
//         * Returns the success message text after waiting for it to be visible.
//         * @return The text of the success message.
//         */
//        public String getSuccessMessage() {
//            // Wait for the success message to be visible and return its text
//            WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
//            return message.getText();
//        }
//    }


}
