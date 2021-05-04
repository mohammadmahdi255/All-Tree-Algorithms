public class Range<E extends Number & Comparable<E>> implements Comparable<Range<E>> {

    private E low;
    private E high;
    private E max;

    public Range(E low, E high) {
        this.low = low;
        this.high = high;
        max = high;
    }

    public E getLow() {
        return low;
    }

    public void setLow(E low) {
        this.low = low;
    }

    public E getHigh() {
        return high;
    }

    public void setHigh(E high) {
        this.high = high;
    }

    public E getMax() {
        return max;
    }

    public void setMax(E max) {
        this.max = max;
    }

    @Override
    public int compareTo(Range<E> o) {
        return low.compareTo(o.getLow());
    }

    @Override
    public String toString() {
        return "["+ low + "," + high + "]" + "/" + max;
    }
}
