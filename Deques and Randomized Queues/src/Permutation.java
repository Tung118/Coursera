import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        int n = 0;
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            n++;
            if (randomizedQueue.size() < k) {
                randomizedQueue.enqueue(s);
            } else {
                int index = StdRandom.uniform(n) + 1;
                if (index <= k) {
                    randomizedQueue.dequeue();
                    randomizedQueue.enqueue(s);
                }
            }
        }
        for (int i = 0; i < k; i++) StdOut.println(randomizedQueue.dequeue());
    }
}