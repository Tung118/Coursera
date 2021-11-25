import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;
public class RandomizedQueue<Item> implements Iterable<Item> {

    private static final int INIT_CAPACITY = 8;
    private Item[] items;
    private int n;
    private int first;
    private int last;

    public RandomizedQueue() {
        items = (Item[]) new Object[INIT_CAPACITY];
        n = 0;
        first = 0;
        last = 0;
    }


    public boolean isEmpty() {
        return n==0;
    }


    public int size() {
        return n;
    }

    private void resize(int capacity) {
        assert capacity >= n;
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = items[(first + i) % items.length];
        }
        items = copy;
        first = 0;
        last  = n;
    }

    public void enqueue(Item item) {
        if(item == null) throw new IllegalArgumentException();
        if (n == items.length) resize(2*items.length);
        items[last++] = item;
        if (last == items.length) last = 0;
        n++;
    }


    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = items[first];
        items[first] = null;
        n--;
        first++;
        if (first == items.length) first = 0;

        if (n > 0 && n == items.length/4) resize(items.length/2);
        return item;
    }


    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        return items[StdRandom.uniform(first,last)];
    }


    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;

        public boolean hasNext() {
            return i < n;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = items[(i + first) % items.length];
            i++;
            return item;
        }
    }
    public static void main(String[] args) {

    }
}