package assign06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinkedListStackTester {
    LinkedListStack<Integer> intList;
    LinkedListStack<String> strList;

    @BeforeEach
    public void setUp() throws Exception {
        intList = new LinkedListStack<>();
        strList = new LinkedListStack<>();
    }

    ///////////   push(E element)   ////////////
    @Test
    public void testPush() {
        // Verify that the initial size of the list is 0
        assertEquals(0, intList.size());

        // Add item to list
        intList.push(1);

        // Check that the list size has incremented
        assertEquals(1, intList.size());
        assertEquals((Integer) 1, intList.peek());

        // Add another item to the list
        intList.push(2);

        // Check that the list size has incremented
        assertEquals(2, intList.size());

        // Check that the first item in the list is also the last item to be added
        assertEquals((Integer) 2, intList.peek());
    }

    /////////////  peek()   ///////////
    @Test
    public void testPeekEmptyStack() {
        // Exception should be thrown if the input index is < 0 or greater than the size of the LinkedList
        assertThrows(NoSuchElementException.class, () -> {
            intList.peek();
        });

        assertThrows(NoSuchElementException.class, () -> {
            strList.peek();
        });
    }

    @Test
    public void testPeek() {
        // Check each element at each index to verify that the correct elements are being returned
        strList.push("1");
        strList.push("2");
        strList.push("3");
        strList.push("4");

        intList.push(10);
        intList.push(20);
        intList.push(30);
        intList.push(40);

        assertEquals((Integer) 40, intList.peek());
        assertEquals("4", strList.peek());
    }

    /////////////   deleteFirst()   ///////////
    @Test
    public void testPopEmptyStack() {
        assertThrows(NoSuchElementException.class, () -> {
            intList.pop();
        });

        assertThrows(NoSuchElementException.class, () -> {
            strList.pop();
        });
    }

    @Test
    public void testPop() {
        // Check each element at each index to veryify that the correct elements are being returned
        strList.push("1");
        strList.push("2");
        strList.push("3");
        strList.push("4");

        intList.push(10);
        intList.push(20);
        intList.push(30);
        intList.push(40);


        // Check the size of the lists
        assertEquals(4, intList.size());
        assertEquals(4, strList.size());

        // Remove the first element in these lists
        assertEquals((Integer) 40, intList.pop());
        assertEquals("4", strList.pop());

        // Check the first element has adjusted
        assertEquals((Integer) 30, intList.peek());
        assertEquals("3", strList.peek());

        // Check that the size of the lists have adjusted
        assertEquals(3, intList.size());
        assertEquals(3, strList.size());
    }

    ////////////    int size()   ////////////
    @Test
    public void testSizeEmptyStack() {
        // An empty list should have a size of 0
        assertEquals(0, intList.size());
        assertEquals(0, strList.size());
    }

    @Test
    public void testSize() {
        // A list with four elements should return 4
        strList.push("1");
        strList.push("2");
        strList.push("3");
        strList.push("4");

        intList.push(10);
        intList.push(20);
        intList.push(30);
        intList.push(40);

        assertEquals(4, intList.size());
        assertEquals(4, strList.size());
    }

    ////////////// isEmpty   //////////////
    @Test
    public void testIsEmptyEmptyStack() {
        // Should return true if the list is empty
        assertTrue(intList.isEmpty());
        assertTrue(strList.isEmpty());
    }

    @Test
    public void testIsEmpty() {
        // Should return false if the list is not empty
        strList.push("1");
        strList.push("2");
        strList.push("3");
        strList.push("4");

        intList.push(10);
        intList.push(20);
        intList.push(30);
        intList.push(40);


        assertFalse(intList.isEmpty());
        assertFalse(strList.isEmpty());
    }

    /////////////  clear()  //////////////
    @Test
    public void testClear() {
        // Clearing the list should report a length of zero
        intList.push(10);
        intList.push(20);
        intList.push(30);
        intList.push(40);

        intList.clear();
        assertEquals(0, intList.size());
    }

    @Test
    public void testClearEmptyStack() {
        // Clearing the list should report a length of zero
        intList.clear();
        assertEquals(0, intList.size());
    }
}
