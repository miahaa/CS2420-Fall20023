package assign06;

import java.net.URL;
import java.util.NoSuchElementException;

public class WebBrowser {
    Stack<URL> backButton;
    Stack<URL> forwardButton;
    URL currentWebpage;

    /**
     * This constructor creates a new web browser with no previously-visited webpages and no webpages to visit next.
     */
    public WebBrowser()
    {
        backButton = new LinkedListStack<>();
        forwardButton = new LinkedListStack<>();
        currentWebpage = null;
    }

    /**
     * Constructs a new web browser with a preloaded history of visited webpages.
     * The first webpage in the list is the "current" webpage visited, and the remaining webpages
     * are ordered from most recently visited to least recently visited.
     *
     * @param history A list of URL objects representing the browsing history
     */
    public WebBrowser(SinglyLinkedList<URL> history)
    {
        backButton = new LinkedListStack<>();
        forwardButton = new LinkedListStack<>();
        currentWebpage = history.getFirst();

        for (int i = 1; i < history.size(); i++)
            backButton.push(history.get(i));
    }

    /**
     * Simulates visiting a webpage and clears the forward button stack.
     *
     * @param webpage The URL of the webpage to visit
     */
    public void visit(URL webpage)
    {
        if (currentWebpage != null)
            backButton.push(currentWebpage);
        currentWebpage = webpage;
        forwardButton.clear();
    }

    /**
     * Simulates using the back button, returning the URL of the previously visited webpage.
     *
     * @return The URL of the previously visited webpage
     * @throws NoSuchElementException if there is no previously-visited URL
     */
    public URL back() throws NoSuchElementException
    {
        if (backButton.isEmpty())
            throw new NoSuchElementException("There is no such element!");

        forwardButton.push(currentWebpage);
        currentWebpage = backButton.pop();
        return currentWebpage;
    }

    /**
     * Simulates using the forward button, returning the URL of the next visited webpage.
     *
     * @return The URL of the next visited webpage
     * @throws NoSuchElementException if there is no URL to visit next
     */
    public URL forward() throws NoSuchElementException
    {
        if (forwardButton.isEmpty())
            throw new NoSuchElementException("There is no such element!");

        backButton.push(currentWebpage);
        currentWebpage = forwardButton.pop();
        return currentWebpage;
    }

    /**
     * Generates a history of visited URLs, ordered from most recently visited to least recently visited.
     *
     * @return A list of URL objects representing the browsing history
     */
    public SinglyLinkedList<URL> history()
    {
        SinglyLinkedList<URL> historyList = new SinglyLinkedList<>();

        Stack<URL> tempStack = new LinkedListStack<>();
        while (!backButton.isEmpty()) {
            tempStack.push(backButton.pop());
        }

        while (!tempStack.isEmpty()) {
            URL url = tempStack.pop();
            historyList.insertFirst(url);
            backButton.push(url);
        }

        if (currentWebpage != null)
            historyList.insertFirst(currentWebpage);

        return historyList;
    }
}
