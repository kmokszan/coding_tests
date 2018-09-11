import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class MaxHeap<T extends Comparable> {

    private int capacity = 10;
    private int size = 0;
    private T[] array = (T[]) new Comparable[capacity];

    private void ensureCapacity(){
        if(size<=capacity){
            array = Arrays.copyOf(array,2*capacity);
            capacity = 2*capacity;
        }
    }

    public void add(T item){
        if(item!=null){
            ensureCapacity();
            array[size]=item;
            size++;
            heapifyUp();
        }
    }

    public T poll(){
        T result;
        if(size>0){
            result = array[0];
            swap(0,size-1);
            size--;
            heapifyDown(0);
        }else{
            result = null;
        }
        return result;
    }

    private void heapifyDown(int i) {
        int leftChildIndex = 2*i + 1;
        int rightChildIndex = leftChildIndex + 1;
        if(leftChildIndex >= size){
            return;
        }
        int smallestIndex = i;
        if(getItem(i).compareTo(getItem(leftChildIndex)) < 0){
            smallestIndex = leftChildIndex;
        }
        if(rightChildIndex < size && getItem(smallestIndex).compareTo(getItem(rightChildIndex)) < 0){
            smallestIndex = rightChildIndex;
        }
        if(smallestIndex!=i){
            swap(smallestIndex,i);
            heapifyDown(smallestIndex);
        }
    }

    private void heapifyUp() {
        int i = size - 1;
        while(hasParent(i) && getItem(i).compareTo(getParent(i)) > 0){
            swapWithParent(i);
            i = getParentIndex(i);
        }
    }

    private void swapWithParent(int i) {
        int pi = getParentIndex(i);
        if(pi>=0){
            swap(i,pi);
        }
    }

    private void swap(int i, int k){
        T tmp = array[i];
        array[i] = array[k];
        array[k] = tmp;
    }

    private int getParentIndex(int i){
        return i%2==1? (i-1)/2 : (i-2)/2;
    }

    private boolean hasParent(int i) {
        return getParentIndex(i) >= 0;
    }

    private T getParent(int i){
        return array[getParentIndex(i)];
    }

    private T getItem(int i){
        return array[i];
    }

    public static void main(String[] args) {
        MaxHeap<Integer> mh = new MaxHeap<>();
        Integer[] input = new Integer[]{5,3,4,8,2,14,1,-9,-1,4,null};
        Arrays.stream(input).forEach(i->mh.add(i));
        List<Integer> actual = new ArrayList<>();
        Integer i = mh.poll();
        while (i!=null){
            actual.add(i);
            i = mh.poll();
        }
        actual.stream().forEach(System.out::println);
        Integer[] expected = new Integer[]{14,8,5,4,4,3,2,1,-1,-9};
        assert Arrays.equals(actual.toArray(),expected);
    }

}
