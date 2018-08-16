import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.stream.Stream;

public class Sorting {

    /**
     * Implement a stable version of selection sort using java.util.LinkedList instead of arrays
     * @param data
     */
    public static void stableSelectionSort(LinkedList<Integer> data){
        stableSelectionSortIter(data,0);
    }

    private static void stableSelectionSortIter(LinkedList<Integer> data, int start){
        if(data!=null && data.size() > start){
            Integer minVal = findAndDeleteMinimum(data,start+1);
            if(minVal !=null){
                data.add(start,minVal);
            }
            stableSelectionSortIter(data,start+1);
        }
    }

    private static Integer findAndDeleteMinimum(LinkedList<Integer> data, int start) {
        Integer result = null;
        if(data!=null && data.size() > start){
            ListIterator<Integer> li = data.listIterator(start);
            result = li.next();
            Integer resultIndex = li.previousIndex();
            while(li.hasNext()){
                Integer currentVal = li.next();
                if(currentVal < result){
                    result = currentVal;
                    resultIndex = li.previousIndex();
                }
            }
            if(result!=null){
                ListIterator<Integer> li2 = data.listIterator(resultIndex);
                li2.next();
                li2.remove();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>(Arrays.asList(4,1,3));
        assert findAndDeleteMinimum(linkedList,0) == 1;
        assert linkedList.size() == 2;

        linkedList = new LinkedList<>(Arrays.asList(4,1,3));
        stableSelectionSort(linkedList);
        int[] actual = linkedList.stream().mapToInt(Integer::intValue).toArray();
        int[] expected = Stream.of(1,3,4).mapToInt(Integer::intValue).toArray();
        assert Arrays.equals(actual, expected);

        linkedList = new LinkedList<>(Arrays.asList(1,1,1));
        assert findAndDeleteMinimum(linkedList,0) == 1;
        assert linkedList.size() == 2;

        linkedList = new LinkedList<>(Arrays.asList(1,1,1));
        stableSelectionSort(linkedList);
        actual = linkedList.stream().mapToInt(Integer::intValue).toArray();
        expected = Stream.of(1,1,1).mapToInt(Integer::intValue).toArray();
        assert Arrays.equals(actual,expected);
    }
}
