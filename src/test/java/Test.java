import java.lang.reflect.Array;
import java.util.ArrayList;

public class Test {
    public static void main(String[]args){
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("123");
        String[] strings1;
        strings1 = (String[])strings.toArray(new String[strings.size()]);
    }
}
