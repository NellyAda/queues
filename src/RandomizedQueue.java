import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] s;
    private int n;

    // construct an empty randomized queue
    public RandomizedQueue() {
        s = (Item[]) new Object[2];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    private void resize(int capacity) {
        s = java.util.Arrays.copyOf(s, capacity);
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Null argument is not allowed");
        }
        if (n == s.length) {
            resize(2 * s.length);
        }
        s[n++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("There are no elements to dequeue");
        }
        int idx = StdRandom.uniform(size());
        Item item = s[idx];
        s[idx] = s[--n];
        if (n > 0 && n == s.length / 4) {
            resize(s.length / 2);
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("There are no elements to get a sample");
        }
        int idx = StdRandom.uniform(size());
        return s[idx];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {

        private final int[] order = StdRandom.permutation(n);
        private int current = 0;

        @Override
        public boolean hasNext() {
            return current <= order.length - 1;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("There is not a next element");
            }
            Item item = s[current++];
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove operation not supported");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        StdOut.println("New queue created");
        StdOut.printf("Size of the deque: %d\n (should be 0)", rq.size());
        StdOut.printf("Is empty? : %b (should be true)\n", rq.isEmpty());

        try {
            StdOut.println("Try to add a null element");
            rq.enqueue(null);
            StdOut.println("FAIL --- The element was added");
        } catch (IllegalArgumentException e) {
            StdOut.println(e.getMessage());
        }

        try {
            StdOut.println("Try to remove an element from an empty queue");
            rq.dequeue();
            StdOut.println("FAIL --- The element was dequeued");
        } catch (NoSuchElementException e) {
            StdOut.println(e.getMessage());
        }

        StdOut.println("Creates an iterator");

        Iterator<Integer> it = rq.iterator();
        StdOut.printf("Iterator is has next element? %b (should be false)\n", it.hasNext());
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

        StdOut.println("\n-----\nINSERT A NEW ELEMENT\n-----\n");
        rq.enqueue(5);
        Integer s = rq.sample();
        StdOut.printf("Sample obtained %d (should be 5), is the queue empty? %b (should be false)\n", s, rq.isEmpty());

        s = rq.dequeue();
        StdOut.printf("Sample obtained %d (should be 5), is the queue empty? %b (should be true)\n", s, rq.isEmpty());
    }

}