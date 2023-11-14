package assign06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class WebBrowserTester {
    private WebBrowser webBrowser;
    @BeforeEach
    public void setUp() throws Exception {
        webBrowser = new WebBrowser();
    }

    @Test
    public void testURL() throws MalformedURLException {
        URL link = new URL("https://a");
        assertEquals("https://a", link.toString());  // true
        assertFalse("https://b".equals(link.toString()));  // false
        assertEquals(new URL("https://a"), link);    // unexpectedly true
    }
    @Test
    public void testConstructorWithNoHistory() {
        assertNull(webBrowser.currentWebpage);
        assertTrue(webBrowser.backButton.isEmpty());
        assertTrue(webBrowser.forwardButton.isEmpty());
    }

    @Test
    public void testConstructorWithHistory() throws MalformedURLException {
        SinglyLinkedList<URL> historyList = new SinglyLinkedList<>();
        URL url1 = new URL("https://example1.com");
        URL url2 = new URL("https://example2.com");
        URL url3 = new URL("https://example3.com");
        historyList.insertFirst(url1);
        historyList.insertFirst(url2);
        historyList.insertFirst(url3);

        WebBrowser webBrowserWithHistory = new WebBrowser(historyList);
        assertEquals(url3, webBrowserWithHistory.back());
        assertEquals(url2, webBrowserWithHistory.back());
        assertEquals(url1, webBrowserWithHistory.back());
        assertNull(webBrowserWithHistory.back());
    }

    @Test
    public void testVisit() throws MalformedURLException {
        URL url = new URL("https://example.com/page1");
        webBrowser.visit(url);

        assertEquals(url, webBrowser.currentWebpage);
        assertTrue(webBrowser.backButton.isEmpty());
        assertTrue(webBrowser.forwardButton.isEmpty());
    }

    @Test
    public void testBack() throws MalformedURLException {
        URL url1 = new URL("https://example1.com");
        URL url2 = new URL("https://example2.com");
        webBrowser.visit(url1);
        webBrowser.visit(url2);

        URL previousUrl = webBrowser.back();
        assertEquals(url1, previousUrl);
        assertEquals(url1, webBrowser.currentWebpage);
        assertTrue(webBrowser.backButton.isEmpty());
    }

    @Test
    public void testForward() throws MalformedURLException {
        URL url1 = new URL("https://example.com/page1");
        URL url2 = new URL("https://example.com/page2");
        webBrowser.visit(url1);
        webBrowser.visit(url2);
        webBrowser.back();

        URL nextUrl = webBrowser.forward();
        assertEquals(url2, nextUrl);
        assertEquals(url2, webBrowser.currentWebpage);
        assertTrue(webBrowser.forwardButton.isEmpty());
    }

    @Test
    public void testBackWhenEmpty() {
        assertThrows(NoSuchElementException.class, () -> {
            webBrowser.back();});
    }

    @Test
    public void testForwardWhenEmpty() {
        assertThrows(NoSuchElementException.class, () -> {
            webBrowser.forward();});
    }

    @Test
    public void testHistory() throws MalformedURLException {
        URL url1 = new URL("https://example.com/page1");
        URL url2 = new URL("https://example.com/page2");
        webBrowser.visit(url1);
        webBrowser.visit(url2);

        SinglyLinkedList<URL> historyList = webBrowser.history();
        assertEquals(url1, historyList.get(0));
        assertEquals(url2, historyList.get(1));
    }

}
