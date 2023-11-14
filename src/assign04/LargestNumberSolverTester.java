package assign04;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

public class LargestNumberSolverTester {
    @Before
    public void setUp() throws Exception {
    }

    //JUnit insertionSort tests
    @Test
    public void testSortEmptyInput() {
        Integer[] arr = {};
        Integer[] expected = {};

        LargestNumberSolver.insertionSort(arr, Comparator.naturalOrder());

        assertArrayEquals(expected, arr);
    }

    @Test
    public void testInsertionSort() {
        Integer[] arr = {66, 11, 72, 23, 99, 38};
        Integer[] expected = {11, 23, 38, 66, 72, 99};

        LargestNumberSolver.insertionSort(arr, Comparator.naturalOrder());

        assertArrayEquals(expected, arr);
    }

    @Test
    public void testInsertionSortWithReverseSortedArray() {
        Integer[] arr = {99, 72, 66, 38, 23, 11};
        Integer[] expected = {11, 23, 38, 66, 72, 99};

        LargestNumberSolver.insertionSort(arr, Comparator.naturalOrder());

        assertArrayEquals(expected, arr);
    }

    //JUnit findLargestNumber tests
    @Test
    public void testFindLargestNumberEmptyArray() {
        Integer[] emptyArray = new Integer[]{};
        assertEquals(BigInteger.ZERO, LargestNumberSolver.findLargestNumber(emptyArray));
    }

    @Test
    public void testFindLargestNumberOneElementArr() {
        Integer[] singleElementArr = new Integer[]{6};
        assertEquals(BigInteger.valueOf(6), LargestNumberSolver.findLargestNumber(singleElementArr));
    }

    @Test
    public void testFindLargestNumber() {
        Integer[] arr = new Integer[]{11, 67, 79, 7, 22, 13};
        assertEquals(new BigInteger("79767221311"), LargestNumberSolver.findLargestNumber(arr));
    }

    @Test
    public void testFindLargestNumberZeroArr() {
        Integer[] allZerosArray = new Integer[]{0, 0, 0, 0};
        assertEquals(BigInteger.valueOf(0), LargestNumberSolver.findLargestNumber(allZerosArray));
    }


    //Junit findLargestInt and Long tests

    @Test
    public void testFindLargestIntWithEmptyArray() {
        Integer[] emptyArray = new Integer[0];
        int result = LargestNumberSolver.findLargestInt(emptyArray);
        assertEquals(BigInteger.ZERO.intValue(), result);
    }


    @Test
    public void testFindLargestInt() throws OutOfRangeException {
        Integer[] arr = {5, 12, 3, 9};
        int result = LargestNumberSolver.findLargestInt(arr);
        assertEquals(95312, result);
    }

    @Test
    public void testFindLargestIntWithOutOfRangeValues() {
        Integer[] outOfRangeArray = {10, 47, 623, 200, 3000};
        assertThrows(OutOfRangeException.class, () -> {
            LargestNumberSolver.findLargestInt(outOfRangeArray);
        });
    }

    @Test
    public void testFindLargestLongWithEmptyArray() throws OutOfRangeException {
        Integer[] emptyArray = new Integer[0];
        long result = LargestNumberSolver.findLargestLong(emptyArray);
        assertEquals(0, result);
    }

    @Test
    public void testFindLargestLong() throws OutOfRangeException {
        Integer[] arr = {5, 12, 3, 9};
        long result = LargestNumberSolver.findLargestLong(arr);
        assertEquals(95312L, result);
    }

    @Test
    public void testFindLargestLongWithOutOfRangeValues() {
        Integer[] outOfRangeArray = {10, 47, 623, 200, 3000};
        assertThrows(OutOfRangeException.class, () -> {
            LargestNumberSolver.findLargestInt(outOfRangeArray);
        });
    }

    //JUnit findSum tests
    @Test
    public void testSumEmptyList() {
        List<Integer[]> emptyList = new ArrayList<>();
        BigInteger sum = LargestNumberSolver.sum(emptyList);
        assertEquals(BigInteger.ZERO, sum);
    }

    @Test
    public void testSumSingleArray() {
        Integer[] arr = {88, 51};
        List<Integer[]> list = new ArrayList<>();
        list.add(arr);
        assertEquals(new BigInteger("8851"), LargestNumberSolver.sum(list));
    }

    @Test
    public void testSumSingleArray2() {
        Integer[] arr = {7, 42, 97};
        List<Integer[]> list = new ArrayList<>();
        list.add(arr);
        assertEquals(new BigInteger("97742"), LargestNumberSolver.sum(list));
    }

    @Test
    public void testSum() {
        List<Integer[]> list = new ArrayList<>();
        Integer[] arr1 = {88, 51};
        Integer[] arr2 = {7, 42, 97};
        list.add(arr1);
        list.add(arr2);
        assertEquals(new BigInteger("106593"), LargestNumberSolver.sum(list));
    }

    //Junit findKthLargest tests
    @Test
    public void testFindKthLargestOverall() {
        // Create a list of arrays for testing
        List<Integer[]> list = new ArrayList<>();
        list.add(new Integer[]{7, 42, 97});
        list.add(new Integer[]{88, 51});
        list.add(new Integer[]{1, 2, 3});
        list.add(new Integer[]{9, 8, 7, 6});

        // Test k = 0 (largest overall)
        Integer[] result = LargestNumberSolver.findKthLargest(list, 0);
        Integer[] expected = new Integer[]{7, 42, 97};
        assertArrayEquals(expected, result);
    }

    @Test
    public void testFindKthLargest() {
        // Create a list of arrays for testing
        List<Integer[]> list = new ArrayList<>();
        list.add(new Integer[]{7, 42, 97});
        list.add(new Integer[]{88, 51});
        list.add(new Integer[]{1, 2, 3});
        list.add(new Integer[]{9, 8, 7, 6});
        //k = 1
        Integer[] result = LargestNumberSolver.findKthLargest(list, 1);
        Integer[] expected = new Integer[]{9, 8, 7, 6};
        assertArrayEquals(expected, result);
    }

    @Test
    //Return smallest overall
    public void testFindKthLargestSmallestOverall() {
        // Create a list of arrays for testing
        List<Integer[]> list = new ArrayList<>();
        list.add(new Integer[]{7, 42, 97});
        list.add(new Integer[]{88, 51});
        list.add(new Integer[]{1, 2, 3});
        list.add(new Integer[]{9, 8, 7, 6});
        //k = 3
        Integer[] result = LargestNumberSolver.findKthLargest(list, 3);
        Integer[] expected = new Integer[]{1, 2, 3};
        assertArrayEquals(expected, result);
    }

    @Test
    public void testFindKthLargestWithInvalidK() {
        // Create a list of arrays
        List<Integer[]> list = new ArrayList<>();
        list.add(new Integer[]{11, 67, 79, 7, 22, 13});

        // Test with k=-1 (invalid k)
        assertThrows(IllegalArgumentException.class, () -> {
            LargestNumberSolver.findKthLargest(list, -1);
        });

        // Test with k=1 (k exceeds list size)
        assertThrows(IllegalArgumentException.class, () -> {
            LargestNumberSolver.findKthLargest(list, 1);
        });

        // Test with k=0 for an empty list
        list.clear();
        assertThrows(IllegalArgumentException.class, () -> {
            LargestNumberSolver.findKthLargest(list, 0);
        });
    }

    @Test
    public void testFindLargestInt2() throws OutOfRangeException {
        Integer[] arr = new Integer[]{14748, 3647, 2};
        assertThrows(OutOfRangeException.class, () -> {
            LargestNumberSolver.findLargestInt(arr);
        });
    }

    @Test
    public void testFindLargestLong2() throws OutOfRangeException {
        Integer[] arr = new Integer[]{14748, 3647, 2};
        assertThrows(OutOfRangeException.class, () -> {
            LargestNumberSolver.findLargestInt(arr);
        });
    }

    @Test
    public void testReadFile() throws IOException {
        List<Integer[]> list = LargestNumberSolver.readFile("integers.txt");
        assertArrayEquals(new Integer[]{410, 21, 93, 80, 69, 379, 20, 60, 432, 13, 72, 62, 70, 83, 9, 3, 14, 11, 62, 55, 34, 83, 80, 99, 56, 25, 79, 51, 51, 70, 79, 20, 34, 67, 40, 51, 41, 94, 89, 116, 874, 554, 137, 371, 17, 77, 97, 58, 83, 97, 26, 17, 54, 96, 33}, list.get(0));
    }

    @Test
    public void testReadFileEmptyFile() throws IOException {
        List<Integer[]> list = LargestNumberSolver.readFile("emptyFile.txt");
        assertTrue(list.isEmpty());
    }

    @Test
    public void testReadFileNonExistentFile() {
        List<Integer[]> list = LargestNumberSolver.readFile("file.txt");
        assertTrue(list.isEmpty());
    }
}




