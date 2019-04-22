package selfmade.myhashmap;

public class MyNode<K, V> implements MyEntry<K, V> {

    final int hash;
    final K key;
    V value;
    MyNode<K, V> next;

    public MyNode(int hash, K key, V value) {
        this.hash = hash;
        this.key = key;
        this.value = value;
    }


    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }
}
