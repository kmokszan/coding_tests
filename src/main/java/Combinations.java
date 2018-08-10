

import com.google.common.base.Strings;

import java.util.*;
import java.util.stream.Collectors;

public class Combinations {

    /**Task:
     * This method prints out all combinations of all letters in a given string.
     * To implement this method, please collect all combinations in a collection and print them out
     * Assume only ASCII characters are present in a string
     *
     * @param str
     */
    public static void combineWithCollection(String str){
        if(!Strings.isNullOrEmpty(str)){
            combineIter(new StringBuilder(str)).forEach(i-> System.out.println(i));
        }
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
            StringBuilder sb = str;
            sb.setLength(sb.length()-1);
            List<String> previousIter = combineIter(sb);
            a.addAll(previousIter.stream().map(i->lastLetter+i).collect(Collectors.toSet()));
            a.addAll(previousIter);
            return a;
        }
    }

    /**Task:
     * This method prints out all combinations of all letters in a given string.
     * Assume only ASCII characters are present in a string
     *
     * @param str
     */
    public static void combine(String str){
        if(!Strings.isNullOrEmpty(str)){
            combineIter2(new StringBuilder(str),0,new StringBuilder());
        }
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

    private static Map<Integer, List<String>> m = new HashMap<>();
    static {
        m.put(0, Arrays.asList("0"));
        m.put(1, Arrays.asList("1"));
        m.put(2, Arrays.asList("d","e","f"));
        m.put(3, Arrays.asList("g","h","i"));
    }

    /**
     * Task:
     * For a given phoneNumber, this method prints all combinations of associated letters to each number according to existing dictionary
     * If a given list of number includes a null value or an unmapped integer, throw IllegalArgumentException
     * @param phoneNumber
     */
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

    private static List<String> getMappedChars(List<Integer> phoneNumber, int index) {
        Integer number = phoneNumber.get(index);
        if(number==null)throw new IllegalArgumentException(String.format("number at index %d was null",index));
        List<String> result = m.get(number);
        if(result == null) throw new IllegalArgumentException(String.format("number %d at index %d is invalid",number,index));
        return result;
    }


    public static void main(String[] args) {
        combineWithCollection("abc");
        System.out.println("--------------");
        combine("abc");
        System.out.println("--------------");
        try{
            phoneNumberToLetters(Arrays.asList(1,null,3));
        }catch (IllegalArgumentException e){
            System.out.println("expected exception was caught: " + e);
        }
        System.out.println("--------------");
        try{
            phoneNumberToLetters(Arrays.asList(1,-9,3));
        }catch (IllegalArgumentException e){
            System.out.println("expected exception was caught: " + e);
        }
        System.out.println("--------------");
        phoneNumberToLetters(Arrays.asList(1,2,3));
    }
}
