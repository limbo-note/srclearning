package selfmade.myhashmap.test;

public class MyInteger {

    int value;

    public MyInteger(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyInteger myInteger = (MyInteger) o;
        return value == myInteger.value;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
