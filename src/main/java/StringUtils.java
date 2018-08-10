

import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StringUtils {
    public static String reverseWords(String input){
        String result = null;
        if(!Strings.isNullOrEmpty(input)){
            LinkedList<String> b = new LinkedList<>();
            for (String s : input.split(" ")) {
                b.addFirst(s);
            }
            result = String.join(" ",b);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(reverseWords("ala ma kota"));
    }
}
