

public class Permutations {


    public static void permute(String str){
        permuteIter(new StringBuilder(str),new StringBuilder());
    }

    private static void permuteIter(StringBuilder str, StringBuilder prefix){
        if(str.length()==1){
            System.out.println(String.format("%s%s",prefix,str));
        }else{
            StringBuilder sb = new StringBuilder("a");
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
    }
}
