public class AugmentRedBlackTree<E extends Comparable<E>> extends RedBlackTree<E> {

    public AugmentRedBlackTree() {
        super();
        Null.setSize(0);
    }

    public Node<E> select(Node<E> search,int n){

        if(n > search.getSize() || n < 1)
            return Null;

        if(n == search.getLeft().getSize() + 1){
            return search;
        } else if(n <= search.getLeft().getSize()) {
            return select(search.getLeft(), n);
        } else {
            return select(search.getRight(), n - search.getLeft().getSize() - 1);
        }

    }

    public int rank(E value){
        return rank(find(value));
    }

    public int rank(Node<E> search){

        int r = search.getLeft().getSize() + 1;

        while (!search.equals(root)) {

            if (search.getParent().getRight().equals(search)) {
                r += search.getBrother().getSize() + 1;
            }

            search = search.getParent();

        }

        return r;

    }

    @Override
    public void addNode(E value) {
        Node<E> insert;
        Node<E> search = root;
        Node<E> parent = Null;

        while (!search.equals(Null)) {
            parent = search;
            parent.setSize(parent.getSize() + 1);
            if (search.getValue().compareTo(value) > 0) {
                search = search.getLeft();
            } else {
                search = search.getRight();
            }
        }

        if (parent.equals(Null)) {
            insert = root = new Node<>(value, Null, Null, Null, "RED", 1);
        } else if (parent.getValue().compareTo(value) > 0) {
            insert = new Node<>(value, parent, Null, Null, "RED", 1);
            parent.setLeft(insert);
        } else {
            insert = new Node<>(value, parent, Null, Null, "RED", 1);
            parent.setRight(insert);
        }

        size++;
        insertFixUp(insert);
    }

    @Override
    public boolean deleteNode(Node<E> delete) {
        if (delete.equals(Null)) {
            return false;
        } else {

            Node<E> replace, fixup;
            String originalColor = delete.getColor();

            if (delete.getRight().equals(Null)) {
                fixup = delete.getLeft();
                transplant(delete, fixup);
            } else if (delete.getLeft().equals(Null)) {
                fixup = delete.getRight();
                transplant(delete, fixup);
            } else {
                replace = minNode(delete.getRight());
                originalColor = replace.getColor();
                fixup = replace.getRight();

                if (!delete.equals(replace.getParent())) {
                    transplant(replace, replace.getRight());
                    replace.setRight(delete.getRight());
                    replace.getRight().setParent(replace);
                }

                replace.setLeft(delete.getLeft());
                replace.getLeft().setParent(replace);
                replace.setColor(delete.getColor());
                replace.setSize(replace.getLeft().getSize() + replace.getRight().getSize() + 1);
                transplant(delete, replace);

            }

            if (originalColor.equals("BLACK"))
                deleteFixUp(fixup);

            size--;
            return true;

        }
    }

    @Override
    public void transplant(Node<E> target, Node<E> replace) {

        if (target.getParent() != null) {
            if (target.getParent().equals(Null))
                root = replace;
            else if (target.equals(target.getParent().getLeft()))
                target.getParent().setLeft(replace);
            else
                target.getParent().setRight(replace);

            replace.setParent(target.getParent());
            replace = replace.getParent();

            while (!replace.equals(Null)){
                replace.setSize(replace.getRight().getSize() + replace.getLeft().getSize() + 1);
                replace = replace.getParent();
            }

            replace.setParent(null);

        }

    }

    private void setSizes(Node<E> node) {

        if(node.equals(Null))
            return;

        setSizes(node.getLeft());
        setSizes(node.getRight());
        node.setSize(node.getRight().getSize() + node.getLeft().getSize() + 1);

    }

    @Override
    public void preOrder(Node<E> search) {

        if (!search.equals(Null)) {
            System.out.print(search.getValue() + "/" + search.getSize() + "-" + search.getColor() + " ");
            preOrder(search.getLeft());
            preOrder(search.getRight());
        }

    }

    @Override
    public void inOrder(Node<E> search) {

        if (!search.equals(Null)) {
            inOrder(search.getLeft());
            System.out.print(search.getValue() + "/" + search.getSize() + "-" + search.getColor() + " ");
            inOrder(search.getRight());
        }

    }

    @Override
    protected String toStringNode(Node<E> node) {
        return node.getValue() + "/" + node.getSize() + "-" + node.getColor();
    }

}
