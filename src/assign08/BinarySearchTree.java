package assign08;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Represents a generic binary search tree.
 *
 * @implNote for every node
 * - its value is greater than all values in its left subtree
 * - its value is less than all values in its right subtree
 *
 * @param <Type> - type for node value
 *
 * @author Khoa Minh Ngo and Thu Ha
 * @version 2023-10-30
 */
public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type>
{
    private Node root;
    private int size;
    private class Node {
        public Type value;
        public Node leftChild;
        public Node rightChild;

        /**
         * Construct a node with a given value.
         * @param value - value of node
         */
        public Node(Type value) {
            this.value = value;
            this.leftChild = null;
            this.rightChild = null;
        }
    }

    /**
     * Construct an empty binary search tree.
     */
    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Ensures that this set contains the specified item.
     *
     * @param item - the item whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     *         the input item was actually inserted); otherwise, returns false
     */
    @Override
    public boolean add(Type item) {
        if (item == null)
        {
            throw new NullPointerException();
        }

        // when root is null
        if (root == null)
        {
            root = new Node(item);
            size++;
            return true;
        }

        Node curr = root;
        while (true)
        {
            if (curr.value.compareTo(item) > 0)
            {
                if (curr.leftChild != null)
                    curr = curr.leftChild;
                else
                {
                    curr.leftChild = new Node(item);
                    size++;
                    return true;
                }
            }

            else if (curr.value.compareTo(item) < 0)
            {
                if (curr.rightChild != null)
                    curr = curr.rightChild;
                else
                {
                    curr.rightChild = new Node(item);
                    size++;
                    return true;
                }
            }

            else
                return false;
        }
    }

    /**
     * Ensures that this set contains all items in the specified collection.
     *
     * @param items - the collection of items whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     *         any item in the input collection was actually inserted); otherwise,
     *         returns false
     */
    @Override
    public boolean addAll(Collection<? extends Type> items) {
        boolean isAdded = false;
        for (Type object : items)
        {
            if (object == null)
                throw new NullPointerException();

            if (add(object))
                isAdded = true;
        }

        return isAdded;
    }

    /**
     * Removes all items from this set. The set will be empty after this method
     * call.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Determines if there is an item in this set that is equal to the specified
     * item.
     *
     * @param item - the item sought in this set
     * @return true if there is an item in this set that is equal to the input item;
     *         otherwise, returns false
     */
    @Override
    public boolean contains(Type item) {
        if (item == null)
            throw new NullPointerException();

        Node curr = root;
        while (curr != null)
        {
            if (curr.value.compareTo(item) > 0)
                curr = curr.leftChild;

            else if (curr.value.compareTo(item) < 0)
                curr = curr.rightChild;

            else
                return true;
        }

        return false;
    }

    /**
     * Determines if for each item in the specified collection, there is an item in
     * this set that is equal to it.
     *
     * @param items - the collection of items sought in this set
     * @return true if for each item in the specified collection, there is an item
     *         in this set that is equal to it; otherwise, returns false
     */
    @Override
    public boolean containsAll(Collection<? extends Type> items) {
        for (Type object : items) {
            if (object == null)
                throw new NullPointerException();

            if (!contains(object))
                return false;
        }
        return true;
    }

    /**
     * Returns the first (i.e., smallest) item in this set.
     *
     * @throws NoSuchElementException if the set is empty
     */
    @Override
    public Type first() throws NoSuchElementException {
        if (root == null)
            throw new NoSuchElementException("The set is empty.");
        Node curr = root;
        while (curr.leftChild != null)
        {
            curr = curr.leftChild;
        }

        return curr.value;
    }

    /**
     * Returns true if this set contains no items.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the last (i.e., largest) item in this set.
     *
     * @throws NoSuchElementException if the set is empty
     */
    @Override
    public Type last() throws NoSuchElementException {
        if (root == null)
            throw new NoSuchElementException("The set is empty.");

        Node curr = root;
        while (curr.rightChild != null)
        {
            curr = curr.rightChild;
        }

        return curr.value;
    }

    /**
     * Ensures that this set does not contain the specified item.
     *
     * @param item - the item whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     *         the input item was actually removed); otherwise, returns false
     */
    @Override
    public boolean remove(Type item) {
        Node curr = root;
        Node prev = null;

        // Search for the item to delete.
        while (curr != null && curr.value.compareTo(item) != 0) {
            prev = curr;
            if (item.compareTo(curr.value) < 0)
                curr = curr.leftChild;
            else
                curr = curr.rightChild;
        }

        // item not found.
        if (curr == null) {
            return false;
        }

        // Handle the case where the node to delete has at most one child.
        if (curr.leftChild == null || curr.rightChild == null) {

            // newCurr will replace the node to be deleted.
            Node newCurr;

            // if the left child does not exist.
            if (curr.leftChild == null)
                newCurr = curr.rightChild;
            else
                newCurr = curr.leftChild;

            // check if the node to be deleted is the root.
            if (prev == null)
            {
                root = newCurr;
                size--;
                return true;
            }

            // Update the parent of the node to delete.
            if (curr == prev.leftChild)
                prev.leftChild = newCurr;
            else
                prev.rightChild = newCurr;
        }

        // Handle the case where the node to delete has two children.
        else {
            Node p = null;
            Node temp;

            // Find the inorder successor.
            temp = curr.rightChild;
            while (temp.leftChild != null) {
                p = temp;
                temp = temp.leftChild;
            }

            // Update links to remove the inorder successor.
            if (p != null)
                p.leftChild = temp.rightChild;

            else
                curr.rightChild = temp.rightChild;

            // Replace the value of the node to delete with the value of the inorder successor.
            curr.value = temp.value;
        }

        size--;
        return true;
    }

    /**
     * Ensures that this set does not contain any of the items in the specified
     * collection.
     *
     * @param items - the collection of items whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     *         any item in the input collection was actually removed); otherwise,
     *         returns false
     */
    @Override
    public boolean removeAll(Collection<? extends Type> items) {
        boolean changed = false;
        for (Type object : items) {
            if (object == null)
                throw new NullPointerException();

            if (remove(object))
                changed = true;
        }

        return changed;
    }

    /**
     * Returns the number of items in this set.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns an ArrayList containing all the items in this set, in sorted
     * order.
     */
    @Override
    public ArrayList<Type> toArrayList() {
        ArrayList<Type> bst = new ArrayList<>();
        if (root == null)
            return bst;

        inOrder(root, bst);
        return bst;
    }

    /**
     * Performs an in-order traversal of the binary search tree (BST) starting from the given node.
     *
     * @param root - the starting node for the traversal.
     * @param bst - the ArrayList to store values from the traversal in sorted order.
     */
    private void inOrder(Node root, ArrayList<Type> bst)
    {
        if (root.leftChild != null)
            inOrder(root.leftChild, bst);
        bst.add(root.value);
        if (root.rightChild != null)
            inOrder(root.rightChild, bst);
    }

    /**
     * Builds a dot file from this binary search tree.
     *
     * @param filename
     *            The path + filename where the dot file will be created.
     */
    public void generateDotFromBST(String filename) {
        PrintWriter out = null;

        // Make sure the input file exists
        try {
            out = new PrintWriter(filename);
        } catch (IOException e) {
            System.out.println(e);
        }

        // Open the graph
        out.println("digraph G {");
        out.println("node [shape=circle, color=black]");

        ArrayList<String> nodeToNodeList = new ArrayList<String>();

        buildDotFromNodes(root, nodeToNodeList);

        for (String s : nodeToNodeList) {
            out.println(s);
        }

        // Close the graph
        out.println("}");
        out.close();
    }

    /**
     * Helper method for the generateDotFromBST method that recursively builds a
     * list of nodes and their connected nodes for building the dot file.
     *
     * @param currentNode
     *            The current node being logged in the list.
     * @param nodeToNodeList
     *            List of nodes and their connections in this binary search
     *            tree.
     */
    private void buildDotFromNodes(Node currentNode, List<String> nodeToNodeList) {
        if (currentNode.leftChild != null) {
            buildDotFromNodes(currentNode.leftChild, nodeToNodeList);
            styleDotEdge(currentNode.leftChild, nodeToNodeList);
            nodeToNodeList.add("\t" + currentNode.value + "->" + currentNode.leftChild.value);
        }

        if (currentNode.rightChild != null) {
            buildDotFromNodes(currentNode.rightChild, nodeToNodeList);
            styleDotEdge(currentNode.rightChild, nodeToNodeList);
            nodeToNodeList.add("\t" + currentNode.value + "->" + currentNode.rightChild.value);
        }

    }

    /**
     * Helper method for the buildDotFromNodes method that colors the edges of
     * dot graph blue if it points to a left child or red if it points to a
     * right child.
     *
     * @param currentNode
     *            The current node being pointed to in the dot graph.
     * @param nodeToNodeList
     *            The list of node connections in the dot graph.
     */
    private void styleDotEdge(Node currentNode, List<String> nodeToNodeList) {
        if (currentNode.leftChild != null) {
            nodeToNodeList.add("edge [dir=right color=\"blue\"]");
        } else if (currentNode.rightChild != null) {
            nodeToNodeList.add("edge [dir=right color=\"red\"]");
        } else {
            nodeToNodeList.add("edge [dir=right color=\"black\"]");
        }

    }
}
