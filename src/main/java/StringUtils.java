

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StringUtils {
    public static String reverseWords(String input){
        LinkedList<String> b = new LinkedList<>();
        if(input!=null){
            List<String> a = new ArrayList<>();
            for (String s : input.split(" ")) {
                b.addFirst(s);
            }
        }
        return String.join(" ",b);
    }

    public static void main(String[] args) {
        System.out.println(reverseWords("ala ma kota"));
    }
}
