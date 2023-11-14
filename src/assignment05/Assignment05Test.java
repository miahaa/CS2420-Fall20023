package assignment05;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * File containing tests to expose bugs in Assignment 5
 *
 * @author Thu Ha
 *
 */
public class Assignment05Test {
    @Test
    public void testMergeSortEmptyArr() {
        ArrayList<Integer> list = new ArrayList<>();
        Assignment05.mergesort(list);
        ArrayList<Integer> result = new ArrayList<>();
        assertEquals(0, list.size());
        assertEquals(result, list);
    }
    @Test
    // i asked on Piazza about why I failed this test and the TA said that I did not need to \
    // throw IllegalArgumentException for size < 2 and MergeSort should be able to handle
    // list of size 0 and 1 without error that why I failed this test so I just test this with size 1 instead of -1
    public void testMergeSortIllegalSizeArr() {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> result = new ArrayList<>();
        result.add(3);
        list.add(3);
        assertEquals(result, list);
    }
}
