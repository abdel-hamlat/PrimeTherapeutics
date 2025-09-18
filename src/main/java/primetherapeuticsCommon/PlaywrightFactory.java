package primetherapeuticsCommon;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.util.Arrays;

public class PlaywrightFactory {
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    public void start() {
        // Detect CI (Jenkins sets these). Locally these are null.
        boolean isCI = System.getenv("JENKINS_HOME") != null
                || System.getenv("BUILD_ID") != null
                || System.getenv("CI") != null;

        // Default: Jenkins=headless true, Local=headless false
        String defaultHeadless = isCI ? "true" : "false";
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", defaultHeadless));

        BrowserType.LaunchOptions launch = new BrowserType.LaunchOptions().setHeadless(headless);
        if (!headless) {
            launch.setArgs(Arrays.asList("--start-maximized"));
        }

        playwright = Playwright.create();
        browser = playwright.chromium().launch(launch);
    }

    public void newPageMaximized() {
        context = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
        page = context.newPage();
    }

    public void waitForElementVisible(String selector) {
        page.locator(selector).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public Page page() {
        return page;
    } // optional getter if you need it

    public void closeContext() {
        if (context != null) context.close();
    }

    public void stop() {
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}