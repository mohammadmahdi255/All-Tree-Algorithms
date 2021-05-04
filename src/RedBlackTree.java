public class RedBlackTree<E extends Comparable<E>> extends BinaryTree<E> {

    public RedBlackTree() {
        super();
        Null.setColor("BLACK");
    }

    @Override
    public void addNode(E value) {

        Node<E> insert;
        Node<E> search = root;
        Node<E> parent = Null;

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
        insertFixUp(insert);

    }

    protected void insertFixUp(Node<E> insert) {

        while (insert.getParent().getColor().equals("RED")) {

            if (insert.getAncle().getColor().equals("RED")) {

                // case 1 of fix up

                insert.getAncle().setColor("BLACK");
                insert.getParent().setColor("BLACK");
                insert.getGrandPa().setColor("RED");
                insert = insert.getGrandPa();
                continue;

            }

            if (insert.getParent().equals(insert.getGrandPa().getLeft())) {

                if (insert.getParent().getRight().equals(insert)) {

                    // case 2 of fix up

                    insert = insert.getParent();
                    leftRotate(insert);

                } else {

                    // case 3 of fix up

                    insert.getParent().setColor("BLACk");
                    insert.getGrandPa().setColor("RED");
                    rightRotate(insert.getGrandPa());

                }

            } else {

                if (insert.getParent().getLeft().equals(insert)) {

                    // case 2 of fix up

                    insert = insert.getParent();
                    rightRotate(insert);


                } else {

                    // case 3 of fix up

                    insert.getParent().setColor("BLACk");
                    insert.getGrandPa().setColor("RED");
                    leftRotate(insert.getGrandPa());

                }

            }

        }

        root.setColor("BLACk");

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

                if (fixup.equals(Null))
                    fixup.setParent(replace);

                if (!delete.equals(replace.getParent())) {
                    transplant(replace, replace.getRight());
                    replace.setRight(delete.getRight());
                    replace.getRight().setParent(replace);
                }

                transplant(delete, replace);
                replace.setLeft(delete.getLeft());
                replace.getLeft().setParent(replace);
                replace.setColor(delete.getColor());

            }

            if (originalColor.equals("BLACK"))
                deleteFixUp(fixup);

            size--;
            return true;

        }

    }

    protected void deleteFixUp(Node<E> fixup) {

        while (!fixup.equals(root) && fixup.getColor().equals("BLACK")) {

            Node<E> brother = fixup.getBrother();

            if (brother.getColor().equals("RED")) {

                // case 1 of delete fix up

                brother.setColor("BLACk");
                fixup.getParent().setColor("RED");

                if (brother.equals(brother.getParent().getRight()))
                    leftRotate(fixup.getParent());
                else
                    rightRotate(fixup.getParent());

                brother = fixup.getBrother();

            }

            if (brother.getRight().getColor().equals("BLACK") && brother.getLeft().getColor().equals("BLACK")) {

                // case 2 of delete fix up

                brother.setColor("RED");
                fixup = fixup.getParent();

            } else if (brother.equals(brother.getParent().getRight())) {

                if (brother.getRight().getColor().equals("BLACK")) {

                    // case 3 of delete fix up

                    brother.getLeft().setColor("BLACK");
                    brother.setColor("RED");

                    rightRotate(brother);

                } else {

                    // case 4 of

                    brother.getRight().setColor("BLACK");
                    brother.setColor(brother.getParent().getColor());
                    brother.getParent().setColor("BLACK");

                    leftRotate(fixup.getParent());

                    fixup = root;

                }

            } else {

                if (brother.getLeft().getColor().equals("BLACK")) {

                    // case 3 of delete fix up

                    brother.getRight().setColor("BLACK");
                    brother.setColor("RED");

                    leftRotate(brother);

                } else {

                    // case 4 of

                    brother.getLeft().setColor("BLACK");
                    brother.setColor(brother.getParent().getColor());
                    brother.getParent().setColor("BLACK");

                    rightRotate(fixup.getParent());

                    fixup = root;

                }

            }

        }

        Null.setParent(null);
        fixup.setColor("BLACK");

    }

    @Override
    protected String toStringNode(Node<E> node) {
        return super.toStringNode(node) + "-" + node.getColor();
    }

}
