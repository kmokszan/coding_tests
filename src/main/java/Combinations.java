

import java.util.*;
import java.util.stream.Collectors;

public class Combinations {

    public static void combine(String str){
        combineIter(new StringBuilder(str)).forEach(i-> System.out.println(i));
    }

    private static List<String> combineIter(StringBuilder str){
        if(str.length()==1){
            List<String> a = new ArrayList<>();
            a.add(str.toString());
            return a;
        }else{
            List<String> a = new ArrayList<>();
            char lastLetter = str.charAt(str.length()-1);
            a.add(lastLetter+"");
            //StringBuilder sb = new StringBuilder(str);
            StringBuilder sb = str;
            sb.setLength(sb.length()-1);
            List<String> previousIter = combineIter(sb);
            a.addAll(previousIter.stream().map(i->lastLetter+i).collect(Collectors.toSet()));
            a.addAll(previousIter);
            return a;
        }
    }

    public static void combine2(String str){
        combineIter2(new StringBuilder(str),0,new StringBuilder());
    }

    private static void combineIter2(StringBuilder in, int start, StringBuilder out){
        if(start>=in.length()) {
            return;
        }else{
            for(int i=start; i<in.length(); i++){
                out.append(in.charAt(i));
                System.out.println(out);
                combineIter2(in,i+1,out);
                out.setLength(out.length()-1);
            }
        }
    }

    public static Map<Integer, List<String>> m = new HashMap<>();
    static {
        m.put(0, Arrays.asList("0"));
        m.put(1, Arrays.asList("1"));
        m.put(2, Arrays.asList("d","e","f"));
        m.put(3, Arrays.asList("g","h","i"));
    }

    public static void phoneNumberToLetters(List<Integer> phoneNumber){
        if(phoneNumber==null) throw new IllegalArgumentException("no phone number given");
        if(phoneNumber.size()>10) throw new IllegalArgumentException("too long number");
        phoneNumberToLettersIter(new StringBuilder(),phoneNumber,0);
    }

    private static void phoneNumberToLettersIter(StringBuilder prefix, List<Integer> phoneNumber,int index){
        if(phoneNumber.size()==(index+1)){
            getMappedChars(phoneNumber, index).forEach(i->System.out.println(String.format("%s%s",prefix, i)));
        }else{
            getMappedChars(phoneNumber, index).forEach(i->{
                prefix.append(i);
                phoneNumberToLettersIter(prefix,phoneNumber,index+1);
                prefix.setLength(prefix.length()-1);
            });
        }
    }

    private static final List<String> EMPTY_LIST = new ArrayList<>();

    private static List<String> getMappedChars(List<Integer> phoneNumber, int index) {
        Integer number = phoneNumber.get(index);
        if(number==null)return EMPTY_LIST;
        return m.get(number) == null ? EMPTY_LIST : m.get(number);
    }


    public static void main(String[] args) {
//        combine("abc");
//        System.out.println("--------------");
//        combine2("abc");
//        System.out.println("--------------");
        phoneNumberToLetters(Arrays.asList(1,2,3));
    }
}
