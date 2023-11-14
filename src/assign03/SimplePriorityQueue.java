package assign03;

import java.util.*;

public class SimplePriorityQueue<E> implements PriorityQueue<E> {
    private Object[] priorityQueue;
    private Comparator<? super E> comparator;
    private int size;

    public SimplePriorityQueue() {
        this.priorityQueue = new Object[50];
        this.comparator = null;
        this.size = 0;
    }

    public SimplePriorityQueue(Comparator<? super E> cmp) {
        this.priorityQueue = new Object[50];
        this.comparator = cmp;
        this.size = 0;
    }

    /**
     * Retrieves, but does not remove, the maximum element in this priority
     * queue.
     *
     * @return the maximum element
     * @throws NoSuchElementException if the priority queue is empty
     */

    @SuppressWarnings("unchecked")
    public E findMax() throws NoSuchElementException {
        if(isEmpty())
            throw new NoSuchElementException("The queue is empty");
        return (E) priorityQueue[size - 1];
    }

    /**
     * Retrieves and removes the maximum element in this priority queue.
     *
     * @return the maximum element
     * @throws NoSuchElementException if the priority queue is empty
     */
    @Override
    @SuppressWarnings("unchecked")
    public E deleteMax() throws NoSuchElementException {
        if(isEmpty())
            throw new NoSuchElementException("The queue is empty");

        if(size == 1) {
            E maxElement = (E) priorityQueue[0];
            priorityQueue[0] = null;
            size = 0;
            return maxElement;
        }

        E maxElement = (E) priorityQueue[size - 1];
        size--;
        priorityQueue[size - 1] = null;
        //size--;
        return maxElement;
    }


    @SuppressWarnings("unchecked")
    private int binarySearch(E item) {
        int left = 0;
        int right = size - 1;
        int mid = left + (right - left) / 2;

        while (left <= right) {
            if(compare(item, (E)priorityQueue[mid]) >= 1) {
                if (mid == right)
                    return mid + 1;
                else {
                    left = mid + 1;
                }
            }
            else if(compare(item, (E)priorityQueue[mid]) == 0) {
                return mid;
            }
            else {
                if(mid == left)
                    return mid - 1;
                else {
                    right = mid - 1;
                }
            }
            mid = (left + right) / 2; //update mid value
        }
        return -1;
    }

    //priority queue is comparing using the natural ordering
    @SuppressWarnings("unchecked")
    private int compare(E o1, E o2) {
        if(comparator != null) {
            return comparator.compare(o1, o2);
        }
        else {
            return ((Comparable<? super E>)o1).compareTo(o2);
        }
    }

    /**
     * Inserts the specified element into this priority queue.
     *
     * @param item - the element to insert
     */
    @Override
    public void insert(E item) {
        if(size == priorityQueue.length) {
            resizeArray();
        }

        if(size == 0) {
            priorityQueue[size] = item;
            size++;
            return;
        }
        int insertIndex = binarySearch(item);

        if(insertIndex < 0) {
            insertIndex = -(insertIndex + 1);
        }

        for(int i = size; i > insertIndex; i--) {
            priorityQueue[i] = priorityQueue[i - 1];
        }

        priorityQueue[insertIndex] = item;
        size++;
    }

    /**
     * Inserts the specified elements into this priority queue.
     *
     * @param coll - the collection of elements to insert
     */
    @Override
    public void insertAll(Collection<? extends E> coll)
    {
        int newSize = size + coll.size();
        if (newSize > priorityQueue.length) {
            resizeArray();
        }

        for (E item : coll) {
            int insertIndex = binarySearch(item);
            if (insertIndex < 0) {
                insertIndex = -(insertIndex + 1);
            }

            for (int i = size; i > insertIndex; i--) {
                priorityQueue[i] = priorityQueue[i - 1];
            }

            priorityQueue[insertIndex] = item;
            size++;
        }
    }

    @Override
    public boolean contains(E item) {
        return binarySearch(item) >= 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        priorityQueue = new Object[10];
        size = 0;
    }

    private void resizeArray() {
        int newSize = priorityQueue.length * 2;
        Object[] newQueue = new Object[newSize];
//        System.arraycopy(priorityQueue, 0, newQueue, 0, newSize);
        for(int i = 0; i < size; i++) {
            newQueue[i] = priorityQueue[i];
        }
        priorityQueue = newQueue;
    }
}
