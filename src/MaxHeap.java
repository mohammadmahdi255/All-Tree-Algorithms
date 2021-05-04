import java.util.ArrayList;

public class MaxHeap<E extends Comparable<E>> {


    private final ArrayList<E> maxheap;
    private int size;

    public MaxHeap() {
        maxheap = new ArrayList<>();
        size = 0;
    }

    public void addMaxHeap(E value) {
        maxheap.add(value);
        int selected = size++;

        int parent = getParent(selected);

        while (parent > -1 && maxheap.get(selected).compareTo(maxheap.get(parent)) > 0){
            swap(selected,parent);
            selected = parent;
            parent = getParent(parent);
        }

    }

    public void remove(E value){
        maxheap.remove(value);
        size--;
        maxHeapify(size - 1);
    }

    public void maxHeapify(int i) {

        int parent = getParent(i);

        if (parent > -1 && maxheap.get(i).compareTo(maxheap.get(parent)) > 0){
            swap(i,parent);
        }

        if( i > 0)
            maxHeapify(i -1);

    }

    public E minimum(int i){

        int r = getRight(i);
        int l = getLeft(i);

        if(r == -1 && l == -1)
            return maxheap.get(i);
        else if (r == -1)
            return minimum(l);
        else if(l == -1)
            return minimum(r);
        else {

            E min_r = minimum(r);
            E min_l = minimum(l);

            return min_r.compareTo(min_l) > 0 ? min_l : min_r;
        }

    }

    private void swap(int i, int j) {


        if (i > j) {
            int hold = i;
            i = j;
            j = hold;
        }

        E hold = maxheap.get(i);
        maxheap.remove(i);
        maxheap.add(i, maxheap.get(j - 1));
        maxheap.remove(j);
        maxheap.add(j, hold);

    }

    private int getParent(int i) {
        return Math.floorDiv((i - 1), 2) < size && Math.floorDiv((i - 1), 2) > -1 ? Math.floorDiv((i - 1), 2) : -1;
    }

    private int getLeft(int i) {
        return (2 * i + 1) < size && (2 * i + 1) > -1 ? (2 * i + 1) : -1;
    }

    private int getRight(int i) {
        return 2 * (i + 1) < size && 2 * (i + 1) > -1 ? 2 * (i + 1) : -1;
    }

    public void printAll(){
        System.out.println(maxheap.toString());
    }

}
