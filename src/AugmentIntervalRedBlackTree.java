
public class AugmentIntervalRedBlackTree<E extends Number & Comparable<E>> extends RedBlackTree<Range<E>> {

    public AugmentIntervalRedBlackTree() {
        super();
        Null.setValue(new Range<>(null,null));
    }


    public boolean isOverlap(Range<E> i, Range<E> j) {
        return i.getHigh().compareTo(j.getLow()) >= 0 && j.getHigh().compareTo(i.getLow()) >= 0;
    }

    public void addNode(E low, E high){
        addNode(new Range<>(low, high));
    }

    @Override
    public void addNode(Range<E> value) {

        Node<Range<E>> insert;
        Node<Range<E>> search = root;
        Node<Range<E>> parent = Null;

        while (!search.equals(Null)) {
            parent = search;
            if (search.getValue().compareTo(value) > 0) {
                search = search.getLeft();
            } else {
                search = search.getRight();
            }
        }

        if (parent.equals(Null)) {
            insert = root = new Node<>(value, Null, Null, Null, "RED", null);
        } else if (parent.getValue().compareTo(value) > 0) {
            insert = new Node<>(value, parent, Null, Null, "RED", null);
            parent.setLeft(insert);
        } else {
            insert = new Node<>(value, parent, Null, Null, "RED", null);
            parent.setRight(insert);
        }

        size++;
        fixMax(insert);
        insertFixUp(insert);

    }

    @Override
    public Node<Range<E>> find(Range<E> range) {

        Node<Range<E>> search = root;
        while (!search.equals(Null) && !isOverlap(search.getValue(),range)) {
            if(!search.getLeft().equals(Null) && search.getValue().getMax().compareTo(range.getLow()) >= 0)
                search = search.getLeft();
            else
                search = search.getRight();

        }

        return search;

    }

    @Override
    public void leftRotate(Node<Range<E>> node) {
        super.leftRotate(node);
        fixMax(node);
    }

    @Override
    public void rightRotate(Node<Range<E>> node) {
        super.rightRotate(node);
        fixMax(node);
    }

    private void fixMax(Node<Range<E>> node){

        if(node.equals(Null) ||
                (!node.equals(root) &&
                        node.getParent().getLeft().equals(node) &&
                        node.getParent().getValue().getMax().compareTo(node.getValue().getHigh()) >= 0))
            return;

        node.getValue().setMax(max(node.getValue().getHigh(),
                node.getRight().getValue().getHigh(),
                node.getLeft().getValue().getHigh()));
        node.getRight().getValue().setMax(node.getValue().getMax());
        fixMax(node.getParent());

    }

    @Override
    protected String toStringNode(Node<Range<E>> node) {
        return node.getValue().toString()  + "-" + node.getColor() ;
    }

    private E max(E a, E b, E c){

        if(a == null)
            return null;

        if(b == null)
            b = a;

        if(c == null)
            c = a;

        if(a.compareTo(b) >= 0){

            if(a.compareTo(c) > 0){
                return a;
            } else {
                return c;
            }

        } else {

            if(b.compareTo(c) > 0){
                return b;
            } else {
                return c;
            }

        }

    }

}
