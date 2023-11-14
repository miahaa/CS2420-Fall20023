package lab03;

/**
 * This class contains code for completing the Lab 3: Debugging activities.
 *
 * @author CS 2420 course staff
 * @version 2023-09-08
 */
public class Part1 {

	public static boolean binarySearch(int[] arr, int goal) {
		int low = arr[0], high = arr.length - 1, mid = 0;
		while(low <= high) {
			mid = (low + high) / 2;
			if(goal == mid)
				return true;
			else if(goal < mid)
				high = mid;
			else
				low = mid;
		}
		return false;
	}
}
