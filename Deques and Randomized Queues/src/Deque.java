import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int n;
    private class   Node {
       Item item;
       Node next;
       Node pre;
       Node(Item item,Node next,Node pre) {
           this.item = item;
           this.next = next;
           this.pre = pre;
       }
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        n = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if(item == null) {
            throw new IllegalArgumentException();
        }
        if(n==0) {
           first = new Node(item,null,null);
           last = first;
        }
        else {
           Node oldFirst = first;
           first = new Node(item,oldFirst,null);
           oldFirst.pre = first;
        }
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if(item == null) {
            throw new IllegalArgumentException();
        }
        if(n==0) {
            first = new Node(item,null,null);
            last = first;
        } else {
            Node oldLast = last;
            last = new Node(item, null,oldLast);
            oldLast.next = last;

        }
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (n == 0) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        n--;
        if (n==0) last = null;
        else first.pre = null;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (n == 0) throw new NoSuchElementException();
        Item item = last.item;
        last = last.pre;
        n--;
        if (n==0) first = null;
        else last.next = null;
        return item ;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new LinkedIterator(first);
    }
    private class LinkedIterator implements Iterator<Item> {
        private Node current;

        public LinkedIterator(Node first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    // unit testing (required)
    public static void main(String[] args) {

    }

}