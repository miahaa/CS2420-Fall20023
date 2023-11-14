package assign06;

import java.util.NoSuchElementException;

public class LinkedListStack<E> implements Stack<E> {
    private SinglyLinkedList<E> linkedList;

    public LinkedListStack() {
        linkedList = new SinglyLinkedList<>();
    }

    /**
     * Removes all of the elements from the stack.
     */
    @Override
    public void clear() { linkedList.clear();}

    /**
     * @return true if the stack contains no elements; false, otherwise.
     */
    @Override
    public boolean isEmpty() { return linkedList.isEmpty();}

    /**
     * Returns, but does not remove, the element at the top of the stack.
     *
     * @return the element at the top of the stack
     * @throws NoSuchElementException if the stack is empty
     */
    @Override
    public E peek() throws NoSuchElementException {
        if(isEmpty())
            throw new NoSuchElementException();
        return linkedList.getFirst();
    }

    /**
     * Returns and removes the item at the top of the stack.
     *
     * @return the element at the top of the stack
     * @throws NoSuchElementException if the stack is empty
     */
    @Override
    public E pop() throws NoSuchElementException {
        if(isEmpty())
            throw new NoSuchElementException();
        return linkedList.deleteFirst();
    }

    /**
     * Adds a given element to the stack, putting it at the top of the stack.
     *
     * @param element - the element to be added
     */
    @Override
    public void push(E element) {linkedList.insertFirst(element); }

    /**
     * @return the number of elements in the stack
     */
    @Override
    public int size() {
        return linkedList.size();
    }
}