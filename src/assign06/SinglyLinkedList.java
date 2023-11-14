package assign06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class implements the functions of a singly linked list.
 *
 * @author Khoa Minh Ngo and Thu Ha
 * @version 2023-10-17
 *
 * @param <E> - the type of elements contained in the linked list
 */
public class SinglyLinkedList<E> implements List<E>{
    private Node head;  // The first node in the list
    private int size;   // The number of elements in the list

    /**
     * Nested class for the node of the linked list
     */
    private class Node {
        E data;
        Node next;

        /**
         * This constructor creates a new node.
         */
        Node(E element) {
            data = element;
            next = null;
        }
    }

    /**
     * This constructor creates a new singly linked list with no element in it.
     */
    public SinglyLinkedList(){
        size = 0;
        head = null;
    }

    /**
     * Inserts an element at the beginning of the list.
     * O(1) for a singly-linked list.
     *
     * @param element - the element to add
     */
    @Override
    public void insertFirst(E element) {
        Node newNode = new Node(element);
        newNode.next = head;
        head = newNode;
        size++;
    }

    /**
     * Inserts an element at a specific position in the list.
     * O(N) for a singly-linked list.
     *
     * @param index - the specified position
     * @param element - the element to add
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index > size())
     */
    @Override
    public void insert(int index, E element) throws IndexOutOfBoundsException {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Out of bound error!");

        if (index == 0)
        {
            insertFirst(element);
            return;
        }

        Node newNode = new Node(element);
        Node current = head;
        for(int i = 0; i < index - 1; i++)
            current = current.next;

        newNode.next = current.next;
        current.next = newNode;
        size++;
    }

    /**
     * Gets the first element in the list.
     * O(1) for a singly-linked list.
     *
     * @return the first element in the list
     * @throws NoSuchElementException if the list is empty
     */
    @Override
    public E getFirst() throws NoSuchElementException {
        if (head == null)
            throw new NoSuchElementException("There is no such element!");
        return head.data;
    }

    /**
     * Gets the element at a specific position in the list.
     * O(N) for a singly-linked list.
     *
     * @param index - the specified position
     * @return the element at the position
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
     */
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index out of bounds!");

        Node current = head;
        for (int i = 0; i < index; i++)
            current = current.next;
        return current.data;
    }

    /**
     * Deletes and returns the first element from the list.
     * O(1) for a singly-linked list.
     *
     * @return the first element
     * @throws NoSuchElementException if the list is empty
     */
    @Override
    public E deleteFirst() throws NoSuchElementException {
        if(head == null)
            throw new NoSuchElementException("There is no such element!");

        Node firstNode = head;
        head = head.next;
        size--;
        return firstNode.data;
    }

    /**
     * Deletes and returns the element at a specific position in the list.
     * O(N) for a singly-linked list.
     *
     * @param index - the specified position
     * @return the element at the position
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
     */
    @Override
    public E delete(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index out of bounds!");

        if (index == 0)
            return deleteFirst();

        Node current = head;
        for (int i = 0; i < index - 1; i++)
            current = current.next;

        Node removedElement = current.next;
        current.next = current.next.next;
        size--;
        return removedElement.data;
    }

    /**
     * Determines the index of the first occurrence of the specified element in the list,
     * or -1 if this list does not contain the element.
     * O(N) for a singly-linked list.
     *
     * @param element - the element to search for
     * @return the index of the first occurrence; -1 if the element is not found
     */
    @Override
    public int indexOf(E element) {
        Node current = head;
        for (int i = 0; i < size; i++)
        {
            if (element.equals(current.data))
                return i;
            current = current.next;
        }

        return -1;
    }

    /**
     * O(1) for a singly-linked list.
     *
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * O(1) for a singly-linked list.
     *
     * @return true if this collection contains no elements; false, otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Removes all of the elements from this list.
     * O(1) for a singly-linked list.
     */
    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * Generates an array containing all of the elements in this list in proper sequence
     * (from first element to last element).
     * O(N) for a singly-linked list.
     *
     * @return an array containing all of the elements in this list, in order
     */
    @Override
    public Object[] toArray() {
        if (size == 0)
            return new Object[]{};

        Object[] linkedList = new Object[size];
        Node current = head;
        for(int i = 0; i < size; i++)
        {
            linkedList[i] = current.data;
            current = current.next;
        }

        return linkedList;
    }

    /**
     * @return an iterator over the elements in this list in proper sequence (from first
     * element to last element)
     */
    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator<>();
    }

    /**
     * This class represents an iterator for a singly-linked list, allowing traversal
     * of elements from the first element to the last element.
     *
     * @param <E> The type of elements contained in the list
     */
    public class LinkedListIterator<E> implements Iterator<E>
    {   private Node current;
        private Node previous;
        private Node nextNode;

        /**
         * Constructs a new iterator for the singly-linked list.
         * The iterator starts at the beginning of the list.
         */
        public LinkedListIterator()
        {
            nextNode = head;
            previous = null;
            current = null;
        }

        /**
         * Checks if there are more elements to iterate over in the list.
         *
         * @return true if there is another element to iterate over, false otherwise
         */
        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        /**
         * Retrieves the next element in the list and advances the iterator.
         *
         * @return the next element in the list
         * @throws NoSuchElementException if there are no more elements to iterate over
         */
        @Override
        @SuppressWarnings("unchecked")
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            E element = (E) nextNode.data;
            if (current != null)
                previous = current;
            current = nextNode;
            nextNode = nextNode.next;
            return element;
        }

        /**
         * Removes the element currently pointed to by the iterator.
         *
         * @throws IllegalStateException if the list is empty or if 'remove' is called
         *                               without calling 'next' first
         */
        @Override
        public void remove() throws IllegalStateException{
            if (size == 0)
                throw new IllegalStateException("Cannot remove an element.");

            if (current == null)
                throw new IllegalStateException("Cannot remove an element without calling next() first.");

            if (previous == null) {
                current = null;
                nextNode = head.next;
                head = head.next;
                size--;
                return;
            }

            previous.next = nextNode;
            current = null;
            size--;
        }
    }
}

