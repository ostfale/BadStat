package de.ostfale.jbad.badstat.parser.api;


import org.htmlunit.html.HtmlPage;

public interface ICookieHandler {

    HtmlPage loadWebsite(String url);
}
