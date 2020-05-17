import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        boolean moreStrs = true;
        while (moreStrs) {
            try {
                String str = StdIn.readString();
                rq.enqueue(str);
            } catch (NoSuchElementException e) {
                moreStrs = false;
            }
        }
        Iterator<String> it = rq.iterator();
        int i = 0;
        while (it.hasNext() && i < k) {
            String str = it.next();
            StdOut.println(str);
            i++;
        }
    }
}