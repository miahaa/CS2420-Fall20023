package assign03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class SimplePriorityQueueTester {
    private SimplePriorityQueue<Object> emptyQueue;
    private SimplePriorityQueue<Object> verySmallQueue;
    private SimplePriorityQueue<Object> stringQueue;
    private SimplePriorityQueue<Integer> cmpQueue;
    private SimplePriorityQueue<String> cmpStringQueue;

    @BeforeEach
    public void setUp() throws Exception{
        emptyQueue = new SimplePriorityQueue<>();
        verySmallQueue = new SimplePriorityQueue<>();
        stringQueue = new SimplePriorityQueue<>();
        cmpQueue = new SimplePriorityQueue<>(new customComparator());
        cmpStringQueue = new SimplePriorityQueue<>(new customStringComparator());
    }

    private static class customComparator implements Comparator<Integer> {
        public int compare(Integer lhs, Integer rhs) {
            return lhs - rhs;
        }
    }

    private static class customStringComparator implements Comparator<String> {
        public int compare(String lhs, String rhs) {
             return lhs.length() - rhs.length();
        }
    }

    @Test
    public void testSizeEmptyQueue() {
        assertTrue(emptyQueue.isEmpty());
        assertEquals(0, emptyQueue.size());
    }
    @Test
    public void testFindMaxEmptyQueue() {
        assertThrows(NoSuchElementException.class, () -> { emptyQueue.findMax(); });
    }

    @Test
    public void testDeleteMaxEmptyQueue() {
        assertThrows(NoSuchElementException.class, () -> { emptyQueue.findMax(); });
    }

    @Test
    public void testContainEmptyQueue() {
        assertFalse(emptyQueue.contains(42));
    }

    @Test
    public void testContains() {
        emptyQueue.insert(66);
        emptyQueue.insert(10);
        emptyQueue.insert(99);
        assertTrue(emptyQueue.contains(10));
        assertFalse(emptyQueue.contains(5));
    }

    @Test
    public void testFindMaxWithOneElement() {
        verySmallQueue.insert(20);
        assertEquals(20, verySmallQueue.findMax());
    }

    @Test
    public void testFindMaxWithComparator() {
        cmpQueue.insert(10);
        cmpQueue.insert(7);
        cmpQueue.insert(12);
        cmpQueue.insert(23);

        assertEquals(23, (int)cmpQueue.findMax());
    }

    @Test
    public void testFindMaxWithStringComparator() {
        cmpStringQueue.insert("thu");
        cmpStringQueue.insert("ngo");
        cmpStringQueue.insert("banana");
        cmpStringQueue.insert("apple");

        assertEquals("banana", cmpStringQueue.findMax());
    }

    @Test
    public void testDeleteMaxSingleElementQueue() {
        emptyQueue.insert(66);
        emptyQueue.deleteMax();
        assertTrue(emptyQueue.isEmpty());
    }

    @Test
    public void testDeleteMax() {
        verySmallQueue.insert(999);
        verySmallQueue.insert(23);
        verySmallQueue.insert(12);

        Object deletedElement = verySmallQueue.deleteMax();
        assertEquals(999, deletedElement);
        assertEquals(2, verySmallQueue.size());
    }

    @Test
    public void testDeleteMaxWithComparator() {
        cmpQueue.insert(10);
        cmpQueue.insert(7);
        cmpQueue.insert(12);
        cmpQueue.insert(23);

        assertEquals(23, (int)cmpQueue.deleteMax());
    }

    @Test
    public void testDeleteMaxWithStringComparator() {
        cmpStringQueue.insert("thu");
        cmpStringQueue.insert("ngo");
        cmpStringQueue.insert("banana");
        cmpStringQueue.insert("apple");

        assertEquals("banana", cmpStringQueue.deleteMax());
    }

    @Test
    public void testInsertAndFindMAxEmptyQueue() {
        emptyQueue.insert(1);
        emptyQueue.insert(2);
        emptyQueue.insert(3);
        assertEquals(3, emptyQueue.findMax());
    }

    @Test
    public void testInsertSameElements() {
        emptyQueue.insert(66);
        emptyQueue.insert(66);
        assertEquals(2, emptyQueue.size());
        assertEquals(66, emptyQueue.findMax());
    }

    @Test
    public void testInsertManyElements() {
        int n = 666;
        for (int i = n; i > 0; i--) {
            emptyQueue.insert(i);
        }
        assertFalse(emptyQueue.isEmpty());
        assertEquals(n, emptyQueue.size());
        assertEquals(n, emptyQueue.findMax());
    }

    @Test
    public void testInsertAllEmptyCollection() {
        ArrayList<Object> coll = new ArrayList<>();
        emptyQueue.insertAll(coll);
        assertTrue(emptyQueue.isEmpty());
        assertEquals(0, emptyQueue.size());
    }

    @Test
    public void testInsertAll() {
        ArrayList<Integer> coll = new ArrayList<>();
        coll.add(10);
        coll.add(7);
        coll.add(12);
        coll.add(23);
        coll.add(30);

        verySmallQueue.insertAll(coll);

        assertFalse(verySmallQueue.isEmpty());
        assertEquals(5, verySmallQueue.size());
        assertEquals(30, verySmallQueue.findMax());
    }

    @Test
    public void testInsertAllSameElements() {
        List<String> coll = new ArrayList<>();
        coll.add("thu");
        coll.add("thu");
        coll.add("ab");
        coll.add("a");

        verySmallQueue.insertAll(coll);

        assertFalse(verySmallQueue.isEmpty());
        assertEquals("thu", verySmallQueue.deleteMax());
        assertEquals(3, verySmallQueue.size());
    }

    @Test
    public void testInsertAllWithStringQueue() {
        ArrayList<String> coll = new ArrayList<>();
        coll.add("thu");
        coll.add("ha");
        coll.add("apple");
        coll.add("banana");

        stringQueue.insertAll(coll);

        assertFalse(stringQueue.isEmpty());
        assertEquals(4, stringQueue.size());
        assertEquals("thu", stringQueue.deleteMax());
    }

    @Test
    void testInsertWithComparator() {
        cmpQueue.insert(66);
        cmpQueue.insert(10);
        cmpQueue.insert(99);
        assertEquals(99, (int)cmpQueue.findMax());
        assertFalse(cmpQueue.isEmpty());
        assertEquals(3, cmpQueue.size());
    }

    @Test
    public void testInsertWithStringComparator() {
        cmpStringQueue.insert("thu");
        cmpStringQueue.insert("ngo");
        cmpStringQueue.insert("banana");
        cmpStringQueue.insert("apple");

        assertEquals("banana", cmpStringQueue.findMax());
        assertFalse(cmpStringQueue.isEmpty());
        assertEquals(4, cmpStringQueue.size());
    }

    @Test
    void testInsertAllWithComparator() {
        List<Integer> list = new ArrayList<>();
        list.add(66);
        list.add(49);
        list.add(-12);
        list.add(23);

        cmpQueue.insertAll(list);
        assertFalse(cmpQueue.isEmpty());
        assertEquals(4, cmpQueue.size());
        assertEquals(66, (int)cmpQueue.findMax());
    }

    @Test
    public void testInsertAllWithStringComparator() {
        List<String> strList = new ArrayList<>();
        strList.add("thu");
        strList.add("ngo");
        strList.add("banana");
        strList.add("apple");

        cmpStringQueue.insertAll(strList);
        assertFalse(cmpStringQueue.isEmpty());
        assertEquals(4, cmpStringQueue.size());
        assertEquals("banana", cmpStringQueue.deleteMax());
    }

    @Test
    void testInsertAllWithLargerCollection() {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            list.add(i);
        }
        emptyQueue.insertAll(list);
        assertFalse(emptyQueue.isEmpty());
        assertEquals(100, emptyQueue.size());
        assertEquals(100, emptyQueue.findMax());
    }

    @Test
    void testClear() {
        emptyQueue.insert("thu");
        emptyQueue.insert("khoa");
        emptyQueue.insert("ha");

        emptyQueue.clear();

        assertTrue(emptyQueue.isEmpty());
        assertEquals(0, emptyQueue.size());
    }

    @Test
    void testClearWithComparator() {
        cmpQueue.insert(66);
        cmpQueue.insert(10);
        cmpQueue.insert(99);
        cmpQueue.clear();
        assertTrue(cmpQueue.isEmpty());
        assertEquals(0, cmpQueue.size());
    }

    @Test
    void testClearWithStringComparator() {
        cmpStringQueue.insert("thu");
        cmpStringQueue.insert("ha");
        cmpStringQueue.insert("cherry");
        cmpStringQueue.clear();
        assertTrue(cmpStringQueue.isEmpty());
        assertEquals(0, cmpQueue.size());
    }
}
