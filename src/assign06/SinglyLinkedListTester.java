package assign06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
/**
 * This class contains unit tests for the SinglyLinkedList class.
 *
 * @author Khoa Minh Ngo and Thu Ha
 * @version 10/18/2023
 */
public class SinglyLinkedListTester {
    SinglyLinkedList<Integer> intList;
    SinglyLinkedList<String> strList;

    @BeforeEach
    public void setUp() throws Exception {
        intList = new SinglyLinkedList<>();
        strList = new SinglyLinkedList<>();
    }

    ///////////   insertFirst(E element)   ////////////
    @Test
    public void testInsertFirst() {
        // Verify that the initial size of the list is 0
        assertEquals(0, intList.size());

        // Add item to list
        intList.insertFirst(1);

        // Check that the list size has incremented
        assertEquals(1, intList.size());
        assertEquals((Integer) 1, intList.get(0));

        // Add another item to the list
        intList.insertFirst(2);

        // Check that the list size has incremented
        assertEquals(2, intList.size());

        // Check that the first item in the list is also the last item to be added
        assertEquals((Integer) 2, intList.get(0));
    }

    ///////////   insert(int index, E element)    ////////////
    @Test
    public void testInsertValidIntegerInput() {
        intList.insert(0, 1);
        intList.insert(1, 2);
        intList.insert(2, 3);
        intList.insert(3, 4);

        // Check that the added items are in the correct places in the list
        assertEquals((Integer) 1, intList.get(0));
        assertEquals((Integer) 2, intList.get(1));
        assertEquals((Integer) 3, intList.get(2));
        assertEquals((Integer) 4, intList.get(3));

        assertEquals(4, intList.size());

        // Insert an item into an occupied position
        intList.insert(0, 10);

        // Check that the length of the linked list has incremented
        assertEquals(5, intList.size());

        // Check the new positions of the items
        assertEquals((Integer) 10, intList.get(0));
        assertEquals((Integer) 1, intList.get(1));
        assertEquals((Integer) 2, intList.get(2));
        assertEquals((Integer) 3, intList.get(3));
        assertEquals((Integer) 4, intList.get(4));
    }

    @Test
    public void testInsertValidStringInput() {
        strList.insert(0, "1");
        strList.insert(1, "2");
        strList.insert(2, "3");
        strList.insert(3, "4");

        // Check that the added items are in the correct places in the list
        assertEquals("1", strList.get(0));
        assertEquals("2", strList.get(1));
        assertEquals("3", strList.get(2));
        assertEquals("4", strList.get(3));

        assertEquals(4, strList.size());

        strList.insert(0, "10");

        // Check that the length of the linked list has incremented
        assertEquals(5, strList.size());

        // Check the new positions of the items
        assertEquals("10", strList.get(0));
        assertEquals("1", strList.get(1));
        assertEquals("2", strList.get(2));
        assertEquals("3", strList.get(3));
        assertEquals("4", strList.get(4));
    }

    @Test
    public void testInsertInvalidIndex() {
        // Exception should be thrown if the input index is < 0 or greater than the size of the LinkedList
        assertThrows(IndexOutOfBoundsException.class, () -> {
            intList.insert(-1, 1234);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            intList.insert(400, 1234);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            strList.insert(-1, "1234");
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            strList.insert(400, "1234");
        });
    }

    /////////////  getFirst()   ///////////
    @Test
    public void testGetFirstEmptyList() {
        // Exception should be thrown if the input index is < 0 or greater than the size of the LinkedList
        assertThrows(NoSuchElementException.class, () -> {
            intList.getFirst();
        });

        assertThrows(NoSuchElementException.class, () -> {
            strList.getFirst();
        });
    }

    @Test
    public void testGetFirst() {
        // Check each element at each index to verify that the correct elements are being returned
        strList.insertFirst("1");
        strList.insertFirst("2");
        strList.insertFirst("3");
        strList.insertFirst("4");

        intList.insertFirst(10);
        intList.insertFirst(20);
        intList.insertFirst(30);
        intList.insertFirst(40);

        assertEquals((Integer) 40, intList.getFirst());
        assertEquals("4", strList.getFirst());
    }

    /////////////  get(int index)   ///////////
    @Test
    public void testGetEmptyList() {
        // Exception should be thrown if the input index is < 0 or greater than the size of the LinkedList
        assertThrows(IndexOutOfBoundsException.class, () -> {
            intList.get(-1);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            strList.get(12);
        });
    }

    @Test
    public void testGet() {
        // Check each element at each index to verify that the correct elements are being returned
        strList.insertFirst("1");
        strList.insertFirst("2");
        strList.insertFirst("3");
        strList.insertFirst("4");

        intList.insertFirst(10);
        intList.insertFirst(20);
        intList.insertFirst(30);
        intList.insertFirst(40);

        assertEquals((Integer) 40, intList.get(0));
        assertEquals("4", strList.get(0));
        assertEquals((Integer) 10, intList.get(3));
        assertEquals("1", strList.get(3));
        assertEquals((Integer) 30, intList.get(1));
        assertEquals("3", strList.get(1));
        assertEquals((Integer) 20, intList.get(2));
        assertEquals("2", strList.get(2));
    }

    /////////////   deleteFirst()   ///////////
    @Test
    public void testDeleteFirstEmptyList() {
        assertThrows(NoSuchElementException.class, () -> {
            intList.deleteFirst();
        });

        assertThrows(NoSuchElementException.class, () -> {
            strList.deleteFirst();
        });
    }

    @Test
    public void testDeleteFirst() {
        // Check each element at each index to verify that the correct elements are being returned
        strList.insertFirst("1");
        strList.insertFirst("2");
        strList.insertFirst("3");
        strList.insertFirst("4");

        intList.insertFirst(10);
        intList.insertFirst(20);
        intList.insertFirst(30);
        intList.insertFirst(40);


        // Check the size of the lists
        assertEquals(4, intList.size());
        assertEquals(4, strList.size());

        // Remove the first element in these lists
        assertEquals((Integer) 40, intList.deleteFirst());
        assertEquals("4", strList.deleteFirst());

        // Check the first element has adjusted
        assertEquals((Integer) 30, intList.getFirst());
        assertEquals("3", strList.getFirst());

        // Check that the size of the lists have adjusted
        assertEquals(3, intList.size());
        assertEquals(3, strList.size());
    }

    /////////////   delete(int index)   ///////////
    @Test
    public void testDeleteOutOfBounds() {
        // Exception should be thrown if the input index is < 0 or greater than the size of the LinkedList
        assertThrows(IndexOutOfBoundsException.class, () -> {
            intList.delete(-1);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            strList.delete(100);
        });
    }

    @Test
    public void testDelete() {
        strList.insertFirst("1");
        strList.insertFirst("2");
        strList.insertFirst("3");
        strList.insertFirst("4");

        intList.insertFirst(10);
        intList.insertFirst(20);
        intList.insertFirst(30);
        intList.insertFirst(40);

        // Check the size of the lists
        assertEquals(4, intList.size());
        assertEquals(4, strList.size());

        // Remove the first item in the lists
        assertEquals((Integer) 40, intList.delete(0));
        assertEquals("4", strList.delete(0));

        // Re-check the size
        assertEquals(3, intList.size());
        assertEquals(3, strList.size());

        // Verify the beginning of the list has adjusted correctly
        assertEquals((Integer)30, intList.getFirst());
        assertEquals("3", strList.getFirst());
    }

    ////////////   indexOf(E element)  ///////////
    @Test
    public void testIndexOfEmptyList() {
        // Should return a -1 since the item being searched for does not exist in the list
        assertEquals(-1, intList.indexOf(1));
        assertEquals(-1, strList.indexOf("thu"));
    }

    @Test
    public void testIndexOfNotFound() {
        // Should return a -1 since the item being searched for does not exist in the list
        strList.insertFirst("1");
        strList.insertFirst("2");
        strList.insertFirst("3");
        strList.insertFirst("4");

        intList.insertFirst(10);
        intList.insertFirst(20);
        intList.insertFirst(30);
        intList.insertFirst(40);

        assertEquals(-1, intList.indexOf(123));
        assertEquals(-1, strList.indexOf("thu"));
    }

    @Test
    public void testIndexOfItem() {
        strList.insertFirst("1");
        strList.insertFirst("2");
        strList.insertFirst("3");
        strList.insertFirst("4");

        intList.insertFirst(10);
        intList.insertFirst(20);
        intList.insertFirst(30);
        intList.insertFirst(40);

        assertEquals(0, intList.indexOf(40));
        assertEquals(0, strList.indexOf("4"));
    }
    ////////////    int size()   ////////////
    @Test
    public void testSizeEmptyList() {
        // An empty list should have a size of 0
        assertEquals(0, intList.size());
        assertEquals(0, strList.size());
    }

    @Test
    public void testSize() {
        // A list with four elements should return 4
        strList.insertFirst("1");
        strList.insertFirst("2");
        strList.insertFirst("3");
        strList.insertFirst("4");

        intList.insertFirst(10);
        intList.insertFirst(20);
        intList.insertFirst(30);
        intList.insertFirst(40);

        assertEquals(4, intList.size());
        assertEquals(4, strList.size());
    }

    ////////////// isEmpty   //////////////
    @Test
    public void testIsEmptyWithEmptyList() {
        // Should return true if the list is empty
        assertTrue(intList.isEmpty());
        assertTrue(strList.isEmpty());
    }

    @Test
    public void testIsEmpty() {
        // Should return false if the list is not empty
        strList.insertFirst("1");
        strList.insertFirst("2");
        strList.insertFirst("3");
        strList.insertFirst("4");

        intList.insertFirst(10);
        intList.insertFirst(20);
        intList.insertFirst(30);
        intList.insertFirst(40);

        assertFalse(intList.isEmpty());
        assertFalse(strList.isEmpty());
    }

    /////////////  clear()  //////////////
    @Test
    public void testClear() {
        // Clearing the list should report a length of zero
        intList.insertFirst(10);
        intList.insertFirst(20);
        intList.insertFirst(30);
        intList.insertFirst(40);

        assertEquals(4, intList.toArray().length);
        intList.clear();
        assertEquals(0, intList.size());
    }

    @Test
    public void testClearEmptyList() {
        // Clearing the list should report a length of zero
        intList.clear();
        assertEquals(0, intList.toArray().length);
    }

    ///////////// toArray() //////////////
    @Test
    public void testToArray() {
        intList.insertFirst(10);
        intList.insertFirst(10);
        intList.insertFirst(10);
        intList.insertFirst(10);

        assertEquals(4, intList.toArray().length);
    }

    @Test
    public void testToArrayEmptyListLength() {
        assertEquals(0, intList.toArray().length);
    }

    @Test
    public void testToArrayIntegerInput() {
        // The most recently added element should be the first element in the array
        intList.insertFirst(10);
        intList.insertFirst(20);
        intList.insertFirst(30);
        intList.insertFirst(40);

        Object[] arr = intList.toArray();

        assertEquals(40, arr[0]);
        assertEquals(30, arr[1]);
        assertEquals(20, arr[2]);
        assertEquals(10, arr[3]);
    }
    @Test
    public void testToArrayOrderWithValidStringInput() {
        // The most recently added element should be the first element in the array
        strList.insertFirst("1");
        strList.insertFirst("2");
        strList.insertFirst("3");
        strList.insertFirst("4");

        Object[] arr = strList.toArray();

        assertEquals("4", arr[0]);
        assertEquals("3", arr[1]);
        assertEquals("2", arr[2]);
        assertEquals("1", arr[3]);
    }

    ///////////// testIterator() //////////////

    @Test
    public void iterator()
    {
        intList.insertFirst(10);
        intList.insertFirst(20);
        intList.insertFirst(30);
        intList.insertFirst(40);

        Iterator<Integer> result = intList.iterator();
        while (result.hasNext())
        {
            System.out.println(result.next());
        }
        assertThrows(NoSuchElementException.class, () -> {
            result.next();
        });
    }
    @Test
    public void testIteratorRemove()
    {
        intList.insertFirst(10);
        intList.insertFirst(20);
        intList.insertFirst(30);
        intList.insertFirst(40);

        Iterator<Integer> result = intList.iterator();
        result.next();
        result.next();
        Integer val = result.next();
        assertEquals(val, 20);
        result.remove();
        assertTrue(3 == intList.size());

    }

    /**
     * test iterator has next and check value
     */
    @Test
    public void testIterator()
    {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.insertFirst(1);
        list.insertFirst(2);
        list.insertFirst(3);

        Iterator<Integer> iter = list.iterator();
        assertTrue(iter.hasNext());
        assertEquals(Integer.valueOf(3), iter.next());
        assertTrue(iter.hasNext());
        assertEquals(Integer.valueOf(2), iter.next());
        assertTrue(iter.hasNext());
        assertEquals(Integer.valueOf(1), iter.next());
        assertFalse(iter.hasNext());
    }

    /**
     * test remove if they throw error
     */
    @Test
    public void removeIterator()
    {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
        list.insertFirst(1);
        list.insert(1, 2);
        list.insert(2, 3);
        list.insert(3, 4);

        Iterator<Integer> iterator = list.iterator();
        iterator.next(); // move to first element 1
        iterator.next(); // move to second element 2
        iterator.remove(); // remove second element 2

        Integer[] expected = { 1, 3, 4 };
        assertArrayEquals(expected, list.toArray());
        iterator.next();
        iterator.remove();
        Integer[] expected1 = { 1, 4 };
        assertArrayEquals(expected1, list.toArray());
    }

    /**
     * test more on remove and next still works
     */
    @Test
    public void removeIteratorFirstAndOnlyElement()
    {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
        list.insertFirst(1);
        list.insertFirst(5);
        Iterator<Integer> iterator = list.iterator();
        iterator.next(); // move to first element 1
        iterator.remove(); // remove second element 2
        iterator.next();
        iterator.remove();
        assertTrue(list.isEmpty());

    }
}