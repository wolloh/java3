package HashSetcustom;

import java.util.NoSuchElementException;

public class HashSet<T> {
   // private static final Integer INITIAL_CAPACITY = 1 << 4; // 16
    /**
     * custom buckets for  hashcodes
     */
    private Node<T>[] buckets;
    /**
     * size of
     */
    private int size;

    /**
     * constructor creating a hashset of specific count of buckets
     * @param capacity count of buckets
     */
    public HashSet(final int capacity) {
        if(capacity<=0){
            throw new IllegalArgumentException("size must be more than 0");
        }
        this.buckets = new Node[capacity];
        this.size = 0;
    }

    /**
     * constructor without parameters that create a 16 buckets
     */
    public HashSet() {
        this(16);
    }

    /**
     * method adding object to the hashset if there is no equal objecl already
     * @param t object
     */
    public void add(T t) {
        int index = hashCode(t) % buckets.length;

        Node bucket = buckets[index];

        Node<T> newNode = new Node<>(t);

        if (bucket == null) {
            buckets[index] = newNode;
            size++;
            return;
        }

        while (bucket.next != null) {
            if (bucket.data.equals(t)) {
                return;
            }
            bucket = bucket.next;
        }

        // Add only if the last element has no value added
        if (!bucket.data.equals(t)) {
            bucket.next = newNode;
            size++;
        }
    }
    public Boolean find(T t){
        Boolean flag=false;
        int index=hashCode(t)%buckets.length;
        Node bucket=buckets[index];
        if(bucket!=null) {
            while (bucket.next != null || !flag) {
                if(bucket.data.equals(t))
                    flag=true;
            }
        }
        return flag;
    }
    /**
     * this method calculate count of non empty buckets
     * @return return int of non empty buckets
     */
    public int size() {
        return size;
    }
    public int bucketcount(){return buckets.length;}
    /**
     * convert all objects from hashset to string
     * @return string of objects
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("[");
        for (Node node: buckets) {
            while (node != null) {
                sb.append(node);
                node = node.next;
            }
        }
        sb.append("]");
        return sb.toString();
    }


    /**
     * calculating hashcode for the object
     * @param t object
     * @return return index for the bucket
     */
    private int hashCode(T t) {
        return t == null ? 0 : t.hashCode();
    }

    /**
     * this method removing object from hashset if he exist
     * @param t object
     * @return return deleted node
     */
    public T remove(T t)throws NoSuchElementException {
        int index = hashCode(t) % buckets.length;

        Node bucket = buckets[index];

        if (bucket == null) {
            throw new NoSuchElementException("No Element Found"); //Cannot match
        }

        if (bucket.data.equals(t)) {
            buckets[index] = bucket.next;
            size--;
            return t;
        }

        Node prev = bucket;

        while (bucket != null) {
            if (bucket.data.equals(t)) {
                prev.next = bucket.next;
                size--;
                return t;
            }
            prev = bucket;
            bucket = bucket.next;
        }
        throw new NoSuchElementException("There is no element");
    }

}
