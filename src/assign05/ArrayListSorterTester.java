package assign05;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ArrayListSorterTester {
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testAscendingOrder() {
        ArrayList<Integer> sortedList = new ArrayList<>();
        int size = 10;

        sortedList.add(1);
        sortedList.add(2);
        sortedList.add(3);
        sortedList.add(4);
        sortedList.add(5);
        sortedList.add(6);
        sortedList.add(7);
        sortedList.add(8);
        sortedList.add(9);
        sortedList.add(10);

        assertEquals(sortedList, ArrayListSorter.generateAscending(size));
    }

    @Test
    public void testAscendingOrderWithInvalidInput() {
        // Exception should be thrown if the input size is less than 2
        assertThrows(IllegalArgumentException.class, () -> {
            ArrayListSorter.generateAscending(1);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ArrayListSorter.generateAscending(0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ArrayListSorter.generateAscending(-1);
        });
    }
    @Test
    public void testDescendingOrd() {
        ArrayList<Integer> reverseOrdList = new ArrayList<>();
        int size = 10;

        reverseOrdList.add(10);
        reverseOrdList.add(9);
        reverseOrdList.add(8);
        reverseOrdList.add(7);
        reverseOrdList.add(6);
        reverseOrdList.add(5);
        reverseOrdList.add(4);
        reverseOrdList.add(3);
        reverseOrdList.add(2);
        reverseOrdList.add(1);

        assertEquals(reverseOrdList, ArrayListSorter.generateDescending(size));
    }
    @Test
    public void testDescendingOrdWithInvalidInput() {
        // Exception should be thrown if the input size is less than 2
        assertThrows(IllegalArgumentException.class, () -> {
            ArrayListSorter.generateDescending(1);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ArrayListSorter.generateDescending(0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ArrayListSorter.generateDescending(-1);
        });
    }
    @Test
    public void testPermutedOrder() {
        // Because the generatePermutedOrder(int size) method initially creates
        // a list from 1 to size,
        // the ordering of the elements in the output ArrayList should not be in
        // ascending order
        ArrayList<Integer> sortedList = new ArrayList<>();
        int size = 10;

        sortedList.add(1);
        sortedList.add(2);
        sortedList.add(3);
        sortedList.add(4);
        sortedList.add(5);
        sortedList.add(6);
        sortedList.add(7);
        sortedList.add(8);
        sortedList.add(9);
        sortedList.add(10);

        assertNotEquals(sortedList, ArrayListSorter.generatePermuted(size));
    }
    @Test
    public void testPermutedOrdWithInvalidInput() {
        // Exception should be thrown if the input size is less than 2
        assertThrows(IllegalArgumentException.class, () -> {
            ArrayListSorter.generatePermuted(1);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ArrayListSorter.generatePermuted(0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ArrayListSorter.generatePermuted(-1);
        });
    }
    @Test
    public void testMergeSortNullArr() {
        ArrayList<Integer> list = null;
        assertThrows(NullPointerException.class, () -> {
            ArrayListSorter.mergesort(list);
        });
    }

    @Test
    public void testMergeSortWithNullElement() {
        // Exception should be thrown if the input array contains a null element

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(null);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);

        assertThrows(NullPointerException.class, () -> {
            ArrayListSorter.mergesort(list);
        });
    }

    @Test
    public void testMergeSortEmptyArr() {
        ArrayList<Integer> list = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> {
            ArrayListSorter.mergesort(list);
        });
    }

    @Test
    public void testMergeSortSingleElement() {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(2));
        assertThrows(IllegalArgumentException.class, () -> {
            ArrayListSorter.mergesort(list);
        });
    }
    @Test
    public void testMergeSortWithSortedList() {
        ArrayList<Integer> unsortedList = ArrayListSorter.generatePermuted(10);
        ArrayList<Integer> sortedList = ArrayListSorter.generateAscending(10);

        ArrayListSorter.mergesort(unsortedList);
        assertEquals(sortedList, unsortedList);
    }
    @Test
    public void testMergeSortWithReverseOrdList() {
        ArrayList<Integer> unsortedList = ArrayListSorter.generateDescending(10);
        ArrayList<Integer> sortedList = ArrayListSorter.generateAscending(10);

        ArrayListSorter.mergesort(unsortedList);
        assertEquals(sortedList, unsortedList);
    }

    @Test
    public void testMergeSortWithPermutedList() {
        ArrayList<Integer> unsortedList = ArrayListSorter.generatePermuted(10);
        ArrayList<Integer> sortedList = ArrayListSorter.generateAscending(10);

        ArrayListSorter.mergesort(unsortedList);
        assertEquals(sortedList, unsortedList);
    }

    @Test
    public void testMergeSortWithCharacterList() {
        ArrayList<String> unsortedList = new ArrayList<>();
        ArrayList<String> sortedList = new ArrayList<>();
        unsortedList.add("f");
        unsortedList.add("e");
        unsortedList.add("d");
        unsortedList.add("c");
        unsortedList.add("b");
        unsortedList.add("a");

        sortedList.add("a");
        sortedList.add("b");
        sortedList.add("c");
        sortedList.add("d");
        sortedList.add("e");
        sortedList.add("f");

        ArrayListSorter.mergesort(unsortedList);
        assertEquals(sortedList, unsortedList);
    }
    @Test
    public void testMergerSortLargeArr() {
        ArrayList<Integer> unsortedList = ArrayListSorter.generatePermuted(100);
        ArrayList<Integer> sortedList = ArrayListSorter.generateAscending(100);

        ArrayListSorter.mergesort(unsortedList);
        assertEquals(sortedList,unsortedList);
    }

    @Test
    public void testQuickSortPermutedList() {
        ArrayList<Integer> unsortedList = ArrayListSorter.generatePermuted(10);
        ArrayList<Integer> sortedList = ArrayListSorter.generateAscending(10);

        // Test with pivot choice 0
        ArrayListSorter.setPivotStrategy(0);

        ArrayListSorter.quicksort(unsortedList);
        assertEquals(sortedList, unsortedList);

        // Test with pivot choice 1
        unsortedList = ArrayListSorter.generatePermuted(10);
        ArrayListSorter.setPivotStrategy(1);

        ArrayListSorter.quicksort(unsortedList);
        assertEquals(sortedList, unsortedList);

        // Test with pivot choice 2
        unsortedList = ArrayListSorter.generatePermuted(10);
        ArrayListSorter.setPivotStrategy(2);

        ArrayListSorter.quicksort(unsortedList);
        assertEquals(sortedList, unsortedList);
    }
    @Test
    public void testQuickSortSortedList() {
        ArrayList<Integer> unsortedList = ArrayListSorter.generateAscending(10);
        ArrayList<Integer> sortedList = ArrayListSorter.generateAscending(10);

        // Test with pivot choice 0
        ArrayListSorter.setPivotStrategy(0);

        ArrayListSorter.quicksort(unsortedList);
        assertEquals(sortedList, unsortedList);

        //Test with pivot choice 1
        unsortedList = ArrayListSorter.generateAscending(10);
        ArrayListSorter.setPivotStrategy(1);

        ArrayListSorter.quicksort(unsortedList);
        assertEquals(sortedList, unsortedList);

        // Test with pivot choice 2
        unsortedList = ArrayListSorter.generateAscending(10);
        ArrayListSorter.setPivotStrategy(2);

        ArrayListSorter.quicksort(unsortedList);
        assertEquals(sortedList, unsortedList);
    }
    @Test
    public void testQuickSortDescendingOrd() {
        ArrayList<Integer> unsortedList = ArrayListSorter.generateDescending(10);
        ArrayList<Integer> sortedList = ArrayListSorter.generateAscending(10);

        // Test with pivot choice 0
        ArrayListSorter.setPivotStrategy(0);

        ArrayListSorter.quicksort(unsortedList);
        assertEquals(sortedList, unsortedList);

        // Test with pivot choice 1
        unsortedList = ArrayListSorter.generateDescending(10);
        ArrayListSorter.setPivotStrategy(1);

        ArrayListSorter.quicksort(unsortedList);
        assertEquals(sortedList, unsortedList);

        // Test with pivot choice 2
        unsortedList = ArrayListSorter.generateDescending(10);
        ArrayListSorter.setPivotStrategy(2);

        ArrayListSorter.quicksort(unsortedList);
        assertEquals(sortedList, unsortedList);
    }
    @Test
    public void testQuickSortCharList() {
        ArrayList<Character> unsortedList = new ArrayList<>();
        ArrayList<Character> sortedList = new ArrayList<>();

        unsortedList.add('f');
        unsortedList.add('e');
        unsortedList.add('d');
        unsortedList.add('c');
        unsortedList.add('b');
        unsortedList.add('a');

        sortedList.add('a');
        sortedList.add('b');
        sortedList.add('c');
        sortedList.add('d');
        sortedList.add('e');
        sortedList.add('f');

        ArrayListSorter.quicksort(unsortedList);
        assertEquals(sortedList, unsortedList);
    }
    @Test
    public void testQuickSortWithValidStringList() {
        ArrayList<String> unsortedList = new ArrayList<>();
        ArrayList<String> sortedList = new ArrayList<>();

        unsortedList.add("f");
        unsortedList.add("e");
        unsortedList.add("d");
        unsortedList.add("c");
        unsortedList.add("b");
        unsortedList.add("a");

        sortedList.add("a");
        sortedList.add("b");
        sortedList.add("c");
        sortedList.add("d");
        sortedList.add("e");
        sortedList.add("f");

        ArrayListSorter.quicksort(unsortedList);
        assertEquals(sortedList, unsortedList);
    }

    @Test
    public void testQuickSortLargeArr() {
        ArrayList<Integer> unsortedList = ArrayListSorter.generatePermuted(100);
        ArrayList<Integer> sortedList = ArrayListSorter.generateAscending(100);

        ArrayListSorter.mergesort(unsortedList);
        assertEquals(sortedList,unsortedList);
    }
}
