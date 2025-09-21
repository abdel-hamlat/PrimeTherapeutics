package specialtyPatientPortal.smokeTestUI;

import org.junit.jupiter.api.*;
import specialtyPatientPortal.PatientPortalBase;
import specialtyPatientPortal.PatientUser;

/**
 * Test Suite: User Login Tests
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserLoginTests {

    private final PatientPortalBase pBase = new PatientPortalBase();
    PatientUser user = new PatientUser();
    String inValidEmail = "Invalid@gmail.com";
    String inValidPassword = "QA1234567";

    @BeforeAll
    void suiteSetup() {
        pBase.start();
        pBase.newPageMaximized();
        pBase.patientNavTo();
        pBase.clickPatientLoginLink();

    }

    @Disabled("Temporarily disabled")
    @Test
    void TestLoginValidCredentials() {
        // Test with valid email and valid password
        pBase.enterEmailAddress(user.getEmail());
        pBase.enterPassword(user.getPassword());
        pBase.clickLoginButton();
        Assertions.assertTrue(pBase.getTitle().contains("Specialty Patient Portal"));
        Assertions.assertEquals("Login Successful", pBase.getSuccessfulLoginHeader());
        Assertions.assertEquals("Congratulations. You have successfully logged in. When you are done click logout below.", pBase.getSuccessfulLoginParagraph());
    }

    @Test
    @Order(2)
    void TestLoginInvalidCredentials() {
        // Test with invalid email and invalid password
        pBase.waitForContentLoad();
        pBase.enterEmailAddress(inValidEmail);
        pBase.enterPassword(inValidPassword);
        pBase.clickLoginButton();
        Assertions.assertEquals("Login Failed. Please remember that passwords are case sensitive.", pBase.getUnsuccessfulLoginError());
    }

    @Test
    @Order(3)
    void TestLoginInvalidEmail() {
        // Test with invalid email and valid password
        pBase.enterEmailAddress(inValidEmail);
        pBase.enterPassword(user.getPassword());
        pBase.clickLoginButton();
        Assertions.assertEquals("Login Failed. Please remember that passwords are case sensitive.", pBase.getUnsuccessfulLoginError());
    }

    @Test
    @Order(4)
    void TestLoginInvalidPassword() {
        // Test with valid email and invalid password
        pBase.enterEmailAddress(user.getEmail());
        pBase.enterPassword(inValidPassword);
        pBase.clickLoginButton();
        Assertions.assertEquals("Login Failed. Please remember that passwords are case sensitive.", pBase.getUnsuccessfulLoginError());
    }

    @AfterAll
    void suiteTearDown() {
        pBase.closeContext();
        pBase.stop();
    }
}
