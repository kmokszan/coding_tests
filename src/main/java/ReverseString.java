import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

public class ReverseString {
    public static void main(String[] args) {
        System.out.println(reverse("abc"));
    }

    static String reverse(String str){
        if(str==null || "".equals(str)){
            return str;
        }
        char[] arr = str.toCharArray();
        int len = arr.length;
        for(int i=0; i<len / 2; i++){
            char tmp = arr[i];
            arr[i] = arr[len - i - 1];
            arr[len - i - 1] = tmp;
        }
        return new String(arr);
    }
}
