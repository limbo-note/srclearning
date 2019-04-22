package selfmade.myhashmap;

public class MyHashMap<K, V> {

    private static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    private static final int MAX_CAPACITY = 1 << 30;
    private int size;
    private MyNode<K, V>[] table;

    public MyHashMap() {
        this.size = 0;
        table = (MyNode<K, V>[]) new MyNode[DEFAULT_INITIAL_CAPACITY];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public V get(K k) {
        int hash = hash(k);
        int bucket = hash & (capacity() - 1);

        MyNode<K, V> node = table[bucket];

        while (node != null) {
            if (node.key.equals(k))
                return node.value;
            node = node.next;
        }
        return null;
    }

    public V put(K k, V v) {

        int hash = hash(k);
        int bucket = hash & (capacity() - 1);
        MyNode<K, V> node = table[bucket];

        if (node == null) {
            node = new MyNode<K, V>(hash, k, v);
            table[bucket] = node;
            ++size;
        } else {
            while (node.next != null) {
                if (node.key.equals(k)) {
                    break;
                }
                node = node.next;
            }
            if (node.key.equals(k)) node.value = v;
            else {
                node.next = new MyNode<K, V>(hash, k, v);
                node = node.next;
                ++size;
            }
        }
        return node.value;
    }

    public V remove(K k) {

        return null;
    }

    public boolean containsKey(K k) {

        return false;
    }


    public boolean containsValue(V v) {


        return false;
    }

    private int hash(K k) {
        int hash = 0;
        hash = k.hashCode();
        hash = hash ^ (hash >>> 16);
        return hash;
    }

    private final int capacity() {
        return (table != null) ? table.length :
                DEFAULT_INITIAL_CAPACITY;
    }


}
