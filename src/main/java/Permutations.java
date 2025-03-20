import com.google.common.base.Strings;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Permutations {


    public static void permute(String str){
        if(!Strings.isNullOrEmpty(str)){
            permuteIter(new StringBuilder(str),new StringBuilder());
        }
    }

    static class Str {
        String prefix;
        String value;

        public Str(String prefix, String value) {
            this.prefix = prefix;
            this.value = value;
        }
    }

    public static void permuteWithQueue(String str){
        var a = new Str("",str);
        Queue<Str> q = new LinkedList<>();
        q.add(a);
        StringBuilder prefix = new StringBuilder();
        var charArray = str.toCharArray();
        while(!q.isEmpty()){
            var x = q.poll();
            if(x.value.length()==1){
                System.out.println(String.format("%s%s",x.prefix,x.value));
            }else{
                List<Str> prefixWithStringList = IntStream.range(0,x.value.length()).mapToObj(y->{
                    var newValue = new StringBuilder(x.value);
                    newValue.deleteCharAt(y);
                    var newPrefix = new StringBuilder(x.prefix).append(charArray[y]);
                    return new Str(newPrefix.toString(),newValue.toString());
                }).collect(Collectors.toList());
                q.addAll(prefixWithStringList);
            }
        }
    }

    private static void permuteIter(StringBuilder str, StringBuilder prefix){
        if(str.length()==1){
            System.out.println(String.format("%s%s",prefix,str));
        }else{
            char[] strArray = str.toString().toCharArray();
            for(int i=0; i<strArray.length; i++){
                StringBuilder newString = new StringBuilder(str);
                newString.deleteCharAt(i);
                StringBuilder newPrefix = new StringBuilder(prefix);
                permuteIter(newString, newPrefix.append(strArray[i]));
            }
        }
    }

    public static void main(String[] args) {
        permute("abc");
        System.out.println("---");
        permuteWithQueue("abc");
    }
}
