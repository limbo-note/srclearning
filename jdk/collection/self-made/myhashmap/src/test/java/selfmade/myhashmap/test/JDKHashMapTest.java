package selfmade.myhashmap.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class JDKHashMapTest {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {


        HashMap jdkMap = new HashMap<Integer, Integer>();
        Method method = jdkMap.getClass().getDeclaredMethod("capacity");
        method.setAccessible(true);
        for (int i = 0; i < 100; i++) {
            jdkMap.put(new MyInteger(i), i);
            System.out.println("size:" + jdkMap.size());
            System.out.println("capacity:" + method.invoke(jdkMap, null));
        }
        System.out.println("size:" + jdkMap.size());

    }
}
