import java.util.ArrayList;

public class MinHeap <E extends Comparable<E>> {

    private final ArrayList<E> minheap;
    private int size;

    public MinHeap() {
        minheap = new ArrayList<>();
        size = 0;
    }

    public void addMinxHeap(E value) {
        minheap.add(value);
        int selected = size++;

        int parent = getParent(selected);

        while (parent > -1 && minheap.get(selected).compareTo(minheap.get(parent)) < 0){
            swap(selected,parent);
            selected = parent;
            parent = getParent(parent);
        }

    }

    public void remove(E value){
        minheap.remove(value);
        size--;
        maxHeapify(size - 1);
    }

    public void maxHeapify(int i) {

        int parent = getParent(i);

        if (parent > -1 && minheap.get(i).compareTo(minheap.get(parent)) < 0){
            swap(i,parent);
        }

        if( i > 0)
            maxHeapify(i -1);

    }

    public E maximum(int i){

        int r = getRight(i);
        int l = getLeft(i);

        if(r == -1 && l == -1)
            return minheap.get(i);
        else if (r == -1)
            return maximum(l);
        else if(l == -1)
            return maximum(r);
        else {

            E max_r = maximum(r);
            E max_l = maximum(l);

            return max_r.compareTo(max_l) > 0 ? max_r : max_l;
        }

    }

    private void swap(int i, int j) {

        if (i > j) {
            int hold = i;
            i = j;
            j = hold;
        }

        E hold = minheap.get(i);
        minheap.remove(i);
        minheap.add(i, minheap.get(j - 1));
        minheap.remove(j);
        minheap.add(j, hold);

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
        System.out.println(minheap.toString());
    }

}
