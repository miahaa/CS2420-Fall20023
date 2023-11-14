package assign04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LargestNumberSolver<T>
{
    /**
     * A generic method that sorts the input array using an insertion sort
     *
     * @param arr: The array to be sorted
     * @param cmp: comparator object
     */
    public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            T key = arr[i];
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && cmp.compare(arr[j],key) > 0) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    /**
     * A method returns the largest number that can be formed by arranging the
     * integers of the given array, in any order.
     * If the array is empty, the largest number that can be formed is 0.
     *
     * @param arr
     * @return: largest number that can be formed by arranging the
     * integers of the given array
     * @return: 0 if the arr is empty
     */

    public static BigInteger findLargestNumber(Integer[] arr) {
        if (arr == null || arr.length == 0) {
            return BigInteger.ZERO;
        }

        //Custom comparison
        Comparator<Integer> cmp = (o1, o2) -> {
            StringBuilder first = new StringBuilder();
            StringBuilder second = new StringBuilder();
            first.append(o1);
            first.append(o2);
            second.append(o2);
            second.append(o1);
            if(Integer.parseInt(String.valueOf(first)) < Integer.parseInt(String.valueOf(second)))
                return 1;
            else if(Integer.parseInt(String.valueOf(first)) > Integer.parseInt(String.valueOf(second)))
                return -1;
            return 0;
        };
        Integer[] copyArr = Arrays.copyOf(arr, arr.length);
        insertionSort(copyArr, cmp);
        StringBuilder bigInteger = new StringBuilder();

        for (Integer integer : copyArr)
            bigInteger.append(integer);

        return new BigInteger(String.valueOf(bigInteger));
    }

    public static int findLargestInt(Integer[] arr) throws OutOfRangeException
        {
            // Copy the original array into a new array
            Integer[] newArr = new Integer[arr.length];
            for(int i = 0; i < arr.length; i++)
                newArr[i] = arr[i];

            BigInteger bigInteger = findLargestNumber(newArr);
            if(BigInteger.valueOf(2147483647).compareTo(bigInteger) < 0
                    || BigInteger.valueOf(-2147483647).compareTo(bigInteger) > 0)
                throw new OutOfRangeException("int");

            return bigInteger.intValue();
        }

    public static long findLargestLong(Integer[] arr) throws OutOfRangeException {
        // Copy the original array into a new array
        Integer[] newArr = new Integer[arr.length];
        for(int i = 0; i < arr.length; i++)
            newArr[i] = arr[i];

        BigInteger bigInteger = findLargestNumber(newArr);
        if(new BigInteger("9223372036854775807").compareTo(bigInteger) < 0
                || new BigInteger("9223372036854775807").negate().compareTo(bigInteger) > 0)
            throw new OutOfRangeException("long");

        return bigInteger.longValue();
    }

//    static class OutOfRangeException extends RuntimeException {
//        public OutOfRangeException(String dataTypeName) {
//            super("The value is too large for the " + dataTypeName + " data type.");
//        }
//    }
    public static BigInteger sum(List<Integer[]> list) {
        BigInteger totalSum = BigInteger.ZERO;
        for (int i = 0; i < list.size(); i++) {
            BigInteger largestNum = findLargestNumber(list.get(i));
            totalSum = totalSum.add(largestNum);
        }
        return totalSum;
    }

    public static Integer[] findKthLargest(List<Integer[]> list, int k)
            throws IllegalArgumentException {
        // throw exception
        if (k < 0 || k >= list.size())
            throw new IllegalArgumentException();
        // Create a new list to store the original order of each array in the list
        Integer[][] result = new Integer[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            Integer[] row = list.get(i);
            result[i] = row;
        }

        insertionSort(result, new sortComparator());
        return result[k];
    }

    static class sortComparator implements Comparator<Integer[]> {
        @Override
        public int compare(Integer[] arr1, Integer[] arr2) {
            // Concatenate arrays as strings and compare based on the concatenated values
            BigInteger o1 = findLargestNumber(arr1);
            BigInteger o2 = findLargestNumber(arr2);
            return o2.compareTo(o1); // Compare in descending order
        }
    }

    public static List<Integer[]> readFile(String filename) {
        List<Integer[]> resultList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(" ");
                Integer[] intArray = new Integer[tokens.length];

                for (int i = 0; i < tokens.length; i++) {
                    intArray[i] = Integer.parseInt(tokens[i]);
                }

                resultList.add(intArray);
            }
        } catch (IOException e) {
            // Handle any IO exception, e.g., file not found
            e.printStackTrace();
        }

        return resultList;
    }
}





