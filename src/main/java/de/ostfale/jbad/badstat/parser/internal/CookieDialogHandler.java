package de.ostfale.jbad.badstat.parser.internal;

import org.htmlunit.Page;
import org.htmlunit.WebClient;
import org.htmlunit.html.HtmlButton;
import org.htmlunit.html.HtmlPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CookieDialogHandler {
    private static final Logger log = LoggerFactory.getLogger(CookieDialogHandler.class);
    private static final String COOKIE_WALL = "cookiewall";
    private static final String COOKIE_BUTTON_XPATH = "/html/body/div/div/div/main/form/div[1]/button[1]";

    public Page loadWebsite(String url) {
        WebClient webClient = ConfiguredWebClient.getWebClient();
        HtmlPage page = null;
        try {
            page = webClient.getPage(url);
            log.debug("HTMLUnit :: Check site for cookie wall...");
            if (page.getUrl().toExternalForm().contains(COOKIE_WALL)) {
                log.debug("HTMLUnit :: Found cookie wall in {}", url);
                acceptCookieWall(webClient, url);
                return webClient.getPage(url);
            }
        } catch (Exception e) {
            log.error("Could not load page: {} because {}", url, e.getMessage());
            throw new RuntimeException(e);
        }
        return page;
    }

    private void acceptCookieWall(WebClient webClient, String url) throws IOException {
        HtmlPage cookieDialog = webClient.getPage(url);
        var button = cookieDialog.getByXPath(COOKIE_BUTTON_XPATH);
        HtmlButton acceptButton = (HtmlButton) button.getFirst();

        if (acceptButton != null) {
            log.debug("Cookie Wall :: Accept cookie wall");
            acceptButton.click();
        }
    }
}
