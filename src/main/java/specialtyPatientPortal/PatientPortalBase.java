package specialtyPatientPortal;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;
import primetherapeuticsCommon.PlaywrightFactory;

public class PatientPortalBase extends PlaywrightFactory {

    /**
     * Test: Should navigate to the Google (practice.qabrains.com) home page.
     */
    public void patientNavTo() {
        page.navigate("https://primetherapeutics.specialty-portal.com");
        page.waitForLoadState();
    }

    /**
     * Test: Should click the Patient Login link on the home page.
     */

    public void clickPatientLoginLink() {
        page.click("//a[text()='Patient Login']");
    }

    /**
     * Test: Should enter the provided email address into the email input field.
     *
     * @param text The email address to input.
     */
    public void enterEmailAddress(String text) {
        //        fill() → clear + type (most common)
        //         type() → type with delay (realistic typing)
        page.locator("//*[@id=\"dnn_ctr590_Login_Login_DNN_txtUsername\"]").fill(text);
    }

    /**
     * Test: Should enter the provided password into the password input field.
     *
     * @param text The password to input.
     */
    public void enterPassword(String text) {
        page.locator("//*[@id=\"dnn_ctr590_Login_Login_DNN_txtPassword\"]").fill(text);
    }

    /**
     * Test: Should return the current page title.
     *
     * @return The title of the current page.
     */
    public String getTitle() {
        return page.title();
    }

    /**
     * Test: Should simulate pressing the Enter key.
     */
    public void clickEnterButton() {
        page.keyboard().press("Enter");
    }

    /**
     * Test: Should click the login button on the page.
     */
    public void clickLoginButton() {
        page.locator("//*[@id=\"dnn_ctr590_Login_Login_DNN_cmdLogin\"]").click();
    }

    public void clickLogoutButton() {
        page.locator("").click();
    }

    /**
     * Test: Should return the successful login header title.
     *
     * @return The successful login header title.
     */
    public String getSuccessfulLoginHeader() {
        return page.locator("").textContent();
    }


    /**
     * Test: Should return the successful login paragraph.
     *
     * @return The successful login paragraph.
     */
    public String getSuccessfulLoginParagraph() {
        return page.locator("").textContent();
    }

    /**
     * Test: Should return the login error message.
     *
     * @return The login error message.
     */

    public String getUnsuccessfulLoginError() {
        page.locator("//*[@id=\"dnn_ctr590_ctl02_lblMessage\"]")
                .waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        return page.locator("//*[@id=\"dnn_ctr590_ctl02_lblMessage\"]").innerText();
    }

    public void waitForContentLoad() {
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }
}
