package assign05;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class represents an ArrayList Sorter.
 *
 * @author Khoa Minh Ngo and Thu Ha
 * @version 2023-10-04
 */
public class ArrayListSorter
{
    private static int threshold = 5;
    private static int pivotStrategy = 0;

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
        // Throw an exception if the input array is null
        if (arr == null)
            throw new NullPointerException("Input array is null");

        // Throw an exception if the input array has less than two elements
        if (arr.size() < 2)
            throw new IllegalArgumentException("Input array contains less than 2 elements");

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

    /**
     * Sorts the given ArrayList in ascending order using the quicksort algorithm.
     * This method is a public entry point for performing the quicksort.
     *
     * @param array The ArrayList to be sorted.
     * @param <T> The type of elements in the ArrayList, must implement Comparable.
     */
    public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> array)
    {
        quicksort(array, 0, array.size() - 1);
    }


    /**
     * Picks the middle element as the pivot for the quicksort.
     * The pivot is moved to the second-to-last position in the subarray.
     *
     * @param array The ArrayList containing the elements to be sorted.
     * @param lower The lower index of the subarray.
     * @param upper The upper index of the subarray.
     * @param <T> The type of elements in the ArrayList, must implement Comparable.
     */
    private static <T extends Comparable<? super T>> void pickMiddlePivot(ArrayList<T> array, int lower, int upper)
    {
        int middle = lower + (upper - lower) / 2;
        if (array.get(upper).compareTo(array.get(middle)) < 0) {
            swap(array, middle, upper);
        }

        // Swap the pivot point to the end
        swap(array, middle, upper - 1);
    }

    /**
     * Picks a random element as the pivot for the quicksort.
     * The pivot is moved to the second-to-last position in the subarray.
     *
     * @param array The ArrayList containing the elements to be sorted.
     * @param lower The lower index of the subarray.
     * @param upper The upper index of the subarray.
     * @param <T> The type of elements in the ArrayList, must implement Comparable.
     */
    private static <T extends Comparable<? super T>> void pickFirstPivot(ArrayList<T> array, int lower, int upper)
    {
        int first = lower;
        if (array.get(upper).compareTo(array.get(first)) < 0) {
            swap(array, first, upper);
        }

        // Swap the pivot point to the end
        swap(array, first, upper - 1);
    }

    /**
     * Picks the median of three elements as the pivot for the quicksort.
     * The pivot is moved to the second-to-last position in the subarray.
     *
     * @param array The ArrayList containing the elements to be sorted.
     * @param lower The lower index of the subarray.
     * @param upper The upper index of the subarray.
     * @param <T> The type of elements in the ArrayList, must implement Comparable.
     */
    private static <T extends Comparable<? super T>> void pickMedianOfThreePivot(ArrayList<T> array, int lower, int upper)
    {
        int middle = lower + (upper - lower) / 2;
        if (array.get(middle).compareTo(array.get(lower)) < 0) {
            swap(array, lower, middle);
        }
        if (array.get(upper).compareTo(array.get(lower)) < 0) {
            swap(array, lower, upper);
        }
        if (array.get(upper).compareTo(array.get(middle)) < 0) {
            swap(array, middle, upper);
        }

        // Swap the pivot point to the end
        swap(array, middle, upper - 1);
    }

    /**
     * Sorts a subarray in ascending order using the quicksort algorithm.
     *
     * @param array The ArrayList containing the elements to be sorted.
     * @param lower The lower index of the subarray.
     * @param upper The upper index of the subarray.
     * @param <T> The type of elements in the ArrayList, must implement Comparable.
     */
    private static <T extends Comparable<? super T>> void quicksort(ArrayList<T> array, int lower, int upper) {
        // Cutoff to perform an insertion sort
        if (upper - lower < threshold)
            insertionSort(array, lower, upper);

        else{

            // Choose the middle element as the pivot
            if (pivotStrategy == 0){
                pickMiddlePivot(array, lower, upper);
            }

                // Choose random element as the pivot
            else if (pivotStrategy == 1) {
                int middle = (lower + upper) / 2;
                pickFirstPivot(array, lower, upper);
            }

                // Choose the median of the left, middle and right
            else if (pivotStrategy == 2){
                pickMedianOfThreePivot(array, lower, upper);
            }

            // Set the pivot
            T pivot = array.get(upper - 1);

            // Begin partitioning
            int i = lower - 1;
            int j = upper - 1;

            // Start a loop that will continue until 'i' and 'j' cross each other
            while(true) {
                // Move 'i' to the right until we find an element greater than or equal to the pivot
                while (i < upper - 1 && array.get(++i).compareTo(pivot) < 0)
                    ; // Keep moving 'i' to the right

                // Move 'j' to the left until we find an element less than or equal to the pivot
                while (j > lower && pivot.compareTo(array.get(--j)) < 0)
                    ;

                // If 'i' and 'j' have crossed each other or are equal, we're done partitioning
                if (i >= j) {
                    break; // Exit the loop
                }

                // Swap the elements at positions 'i' and 'j' to put them in the correct order
                swap(array, i, j);
            }

            // Restore the pivot
            swap(array, i, upper - 1);

            quicksort(array, lower, i - 1);
            quicksort(array, i + 1, upper);
        }
    }

    /**
     * Helper method that swaps two elements with given indexes in an ArrayList
     *
     * @param <T> Generic object that implements Comparable
     * @param array Input ArrayList containing elements to be swapped
     * @param index1 Index of the first element to be swapped
     * @param index2 Index of the second element to be swapped
     */
    private static <T extends Comparable<? super T>> void swap(ArrayList<T> array, int index1, int index2) {
        T temp = array.get(index1);
        array.set(index1, array.get(index2));
        array.set(index2, temp);
    }

    /**
     * Generates a list of a specified length, containing integers in sorted
     * order (1 to n in ascending order)
     *
     * @param size The number of elements to include in the generated ArrayList
     * @return Returns an ArrayList<Integer> object containing the specified
     *         number of elements in sorted order
     */
    public static ArrayList<Integer> generateAscending(int size) {
        // Throw exception if size is less than 2
        if (size < 2) {
            throw new IllegalArgumentException("Invalid Size: input is less than 2");
        }
        ArrayList<Integer> sortedList = new ArrayList<>();

        // Add elements from 1 to size to ArrayList (Smallest to biggest)
        for (int i = 1; i <= size; i++)
            sortedList.add(i);

        return sortedList;
    }

    /**
     * Generates a list of a specified length, containing integers from 1 to n
     * in permuted order
     *
     * @param size The number of elements to include in the generated ArrayList
     * @return Returns an ArrayList<Integer> object containing the specified
     *         number of random elements
     */
    public static ArrayList<Integer> generatePermuted(int size) {
        // Throw exception if size is less than 2
        if (size < 2) {
            throw new IllegalArgumentException("Invalid Size: input is less than 2");
        }

        ArrayList<Integer> permutedList = new ArrayList<>();

        // Add elements from 1 to size to ArrayList
        for (int i = 1; i <= size; i++)
            permutedList.add(i);

        // Shuffle the ArrayList
        Collections.shuffle(permutedList);

        return permutedList;
    }

    /**
     * Generates a list of a specified length, containing integers in reverse
     * sorted order (1 to n in descending order)
     *
     * @param size The number of elements to include in the generated ArrayList
     * @return Returns an ArrayList<Integer> object containing the specified
     *         number of elements in reverse-sorted order
     */
    public static ArrayList<Integer> generateDescending(int size) {
        // Throw exception if size is less than 2
        if (size < 2) {
            throw new IllegalArgumentException("Invalid Size: input is less than 2");
        }

        ArrayList<Integer> sortedList = new ArrayList<>();

        // Add elements from size to 1 to ArrayList (Biggest to smallest)
        for (int i = size; i >= 1; i--)
            sortedList.add(i);

        return sortedList;
    }

    /**
     * Sets the pivot strategy for the quicksort algorithm.
     *
     * @param strategy An integer representing the pivot selection strategy:
     *                     0 for middle pivot, 1 for random pivot, 2 for median of three pivot.
     */
    public static void setPivotStrategy(int strategy){
        pivotStrategy = strategy;
    }
}