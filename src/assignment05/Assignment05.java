package assignment05;

import java.util.ArrayList;

/**
 *
 * @author Thu Ha
 *
 */
public class Assignment05 {
    private static int threshold = 5;
    public static void setThreshold(int size) {
        threshold = size;
    }
    /**
     * Sorts the given ArrayList in ascending order using the merge sort algorithm.
     * This method is a public entry point for performing the merge sort.
     *
     * @param arr The ArrayList to be sorted.
     * @param <T> The type of elements in the ArrayList, must implement Comparable.
     */
    public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arr)
    {
        ArrayList<T> storage = new ArrayList<>(arr.size());
        for(int i = 0; i < arr.size(); i++)
            storage.add(null);

        mergesort(arr, storage, 0, arr.size() - 1);
    }


    /**
     * Recursively sorts the subarray of the ArrayList from index 'l' to 'r' (inclusive)
     * in ascending order using the merge sort algorithm.
     *
     * @param arr The ArrayList containing the elements to be sorted.
     * @param storage The temporary copied array.
     * @param start   The left index of the subarray to be sorted.
     * @param end   The right index of the subarray to be sorted.
     * @param <T> The type of elements in the ArrayList, must implement Comparable.
     */
    private static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arr, ArrayList<T> storage, int start, int end)
    {
        if (end - start < threshold)
            insertionSort(arr, start, end);
        else if (start < end) {
            // Find the middle point
            int middle = start + (end - start) / 2;

            // Sort first and second halves
            mergesort(arr, storage, start, middle);
            mergesort(arr, storage, middle + 1, end);

            // Merge the sorted halves
            merge(arr, storage, start, middle + 1, end);
        }
    }

    /**
     * Merges two subarrays of the ArrayList 'arr.'
     * The first subarray is arr[l...m] and the second subarray is arr[m+1...r].
     *
     * @param arr The ArrayList containing the elements to be merged.
     * @param storage the temporary copied array.
     * @param left   The left index of the first subarray.
     * @param middle   The middle index.
     * @param right   The right index of the second subarray.
     * @param <T> The type of elements in the ArrayList, must implement Comparable.
     */
    private static <T extends Comparable<? super T>> void merge(ArrayList<T> arr, ArrayList<T> storage, int left, int middle, int right)
    {
        int leftEnd = middle - 1;
        int tempPos = left;
        int numElements = right - left + 1;

        while(left <= leftEnd && middle <= right)
        {
            if (arr.get(left).compareTo(arr.get(middle)) <= 0)
            {
                storage.set(tempPos, arr.get(left));
                tempPos++;
                left++;
            }
            else
            {
                storage.set(tempPos, arr.get(middle));
                tempPos++;
                middle++;
            }
        }

        // Write while loop for left sub-array
        while (left <= leftEnd)
        {
            storage.set(tempPos, arr.get(left));
            tempPos++;
            left++;
        }

        // Write while loop for right sub-array
        while (middle <= right)
        {
            storage.set(tempPos, arr.get(middle));
            tempPos++;
            middle++;
        }

        // Copy from storage array back to array
        for (int i = 0; i < numElements; i++)
        {
            arr.set(right, storage.get(right));
            right--;
        }
    }
    /**
     * Sorts the given ArrayList in ascending order using the insertion sort algorithm.
     *
     * @param list The ArrayList to be sorted.
     * @param start the start index of the parameter ArrayList
     * @param end the end index of the parameter ArrayList
     * @param <T>  The type of elements in the ArrayList, must implement Comparable.
     */
    private static <T extends Comparable<? super T>> void insertionSort(ArrayList<T> list, int start, int end)
    {
        for (int i = start + 1; i <= end; i++) {
            T current = list.get(i);
            int j = i;
            while (j > start && current.compareTo(list.get(j - 1)) < 0) {
                list.set(j, list.get(j - 1));
                j--;
            }
            list.set(j, current);
        }
    }
}
