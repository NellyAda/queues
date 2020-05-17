import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size;

    private class Node {
        private Item item;
        private Node previous;
        private Node next;
    }

    // construct an empty deque
    public Deque() {

    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Null argument is not allowed");
        }
        Node node = new Node();
        node.item = item;
        node.next = first;
        first = node;
        if (size == 0) {
            last = node;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Null argument is not allowed");
        }
        Node node = new Node();
        node.item = item;
        node.previous = last;
        last = node;
        if (size == 0) {
            first = node;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("There are no elements to remove");
        }
        Node node = first;
        first = node.next;

        if (size == 1) {
            last = first;
        } else {
            node.next = null;
        }
        size--;
        return node.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("There are no elements to remove");
        }
        Node node = last;
        last = node.previous;

        if (size == 1) {
            first = last;
        } else {
            node.previous = null;
        }
        size--;
        return node.item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ItemIterator();
    }

    private class ItemIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("There is not a next element");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove operation not supported");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {

        Deque<Integer> d = new Deque<>();
        StdOut.println("New queue created");
        StdOut.printf("Size of the deque: %d\n (should be 0)", d.size());
        StdOut.printf("Is empty? : %b (should be true)\n", d.isEmpty());
        try {
            StdOut.println("Try to add a null element in the begining");
            d.addFirst(null);
            StdOut.println("FAIL --- The element was added");
        } catch (IllegalArgumentException e) {
            StdOut.println(e.getMessage());
        }
        try {
            StdOut.println("Try to add a null element in the end");
            d.addLast(null);
            StdOut.println("FAIL --- The element was added");
        } catch (IllegalArgumentException e) {
            StdOut.println(e.getMessage());
        }
        try {
            StdOut.println("Try to remove in the begining");
            d.removeFirst();
            StdOut.println("FAIL --- The element was removed");
        } catch (NoSuchElementException e) {
            StdOut.println(e.getMessage());
        }
        try {
            StdOut.println("Try to remove in the end");
            d.removeLast();
            StdOut.println("FAIL --- The element was removed");
        } catch (NoSuchElementException e) {
            StdOut.println(e.getMessage());
        }

        StdOut.println("Creates an iterator");

        Iterator<Integer> it = d.iterator();
        StdOut.printf("Iterator is has next element? %b\n (should be false)", it.hasNext());
        try {
            StdOut.println("Try to remove an element with an iterator");
            it.remove();
            StdOut.println("FAIL --- The operation should not be supported");
        } catch (UnsupportedOperationException e) {
            StdOut.println(e.getMessage());
        }
        try {
            StdOut.println("Try to get the next element with an iterator");
            it.next();
            StdOut.println("FAIL --- It should not be able to get the next element");
        } catch (NoSuchElementException e) {
            StdOut.println(e.getMessage());
        }

        // With one element and first
        StdOut.println("\n-----\nINSERTA NEW ELEMENT AND WORK FROM THE FRONT\n-----\n");

        d.addFirst(5);
        StdOut.printf("Size of the deque: %d\n (should be 1)", d.size());
        StdOut.printf("Is empty? : %b (should be false)\n", d.isEmpty());
        StdOut.println("Creates an iterator");

        it = d.iterator();
        StdOut.printf("Iterator is has next element? %b\n (should be true)", it.hasNext());
        try {
            StdOut.println("Try to get the next element with an iterator");
            Integer i = it.next();
            StdOut.printf("the next element is: %d\n", i);
        } catch (NoSuchElementException e) {
            StdOut.println("FAIL --- " + e.getMessage());
        }

        try {
            StdOut.println("Try to remove in the begining");
            Integer i = d.removeFirst();
            StdOut.printf("The element %d was removed (should be 5)", i);
        } catch (NoSuchElementException e) {
            StdOut.println("FAIL --- " + e.getMessage());
        }

        // With one element and last
        StdOut.println("\n-----\nINSERT A NEW ELEMENT AND WORK FROM BEHIND\n-----\n");

        d.addLast(5);
        StdOut.printf("Size of the deque: %d\n (should be 1)", d.size());
        StdOut.printf("Is empty? : %b (should be false)\n", d.isEmpty());
        StdOut.println("Creates an iterator");

        try {
            StdOut.println("Try to remove in the end");
            Integer i = d.removeLast();
            StdOut.printf("The element %d was removed (should be 5)", i);
        } catch (NoSuchElementException e) {
            StdOut.println("FAIL --- " + e.getMessage());
        }

        // With one element and last
        StdOut.println("\n-----\nINSERTAMOS DOS ELEMENTOS\n-----\n");
        d.addFirst(1);
        d.addLast(2);
        StdOut.printf("Size of the deque: %d\n (should be 2)", d.size());

    }

}