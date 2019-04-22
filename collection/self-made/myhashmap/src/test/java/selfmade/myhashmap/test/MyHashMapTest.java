package selfmade.myhashmap.test;

import org.junit.Test;
import selfmade.myhashmap.MyHashMap;

import static org.junit.Assert.*;

public class MyHashMapTest {

    public static void main(String[] args) {


        MyHashMap myMap = new MyHashMap<Integer, Integer>();
        for (int i = 0; i < 100; i++) {
            myMap.put(new MyInteger(i), i);
            System.out.println(myMap.get(new MyInteger(i)));
            System.out.println("size:" + myMap.size());
        }

    }
}