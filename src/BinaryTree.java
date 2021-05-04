import java.util.LinkedList;
import java.util.List;

public class BinaryTree<E extends Comparable<E>> {

    protected Node<E> root;
    protected final Node<E> Null;
    protected int size;

    public BinaryTree() {
        Null = new Node<>(null, null, null, null, null, null);
        Null.setRight(Null);
        Null.setLeft(Null);
        root = Null;
        size = 0;
    }

    public Node<E> find(E value) {

        Node<E> search = root;
        while (!search.equals(Null) && search.getValue() != null) {
            if (search.getValue().compareTo(value) == 0) {
                return search;
            } else if (search.getValue().compareTo(value) > 0) {
                search = search.getLeft();
            } else {
                search = search.getRight();
            }
        }

        return search;

    }

    public void addNode(E value) {

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
            root = new Node<>(value, Null, Null, Null, null, null);
        } else if (parent.getValue().compareTo(value) > 0) {
            parent.setLeft(new Node<>(value, parent, Null, Null, null, null));
        } else {
            parent.setRight(new Node<>(value, parent, Null, Null, null, null));
        }

        size++;

    }

    public boolean deleteNode(E value) {
        return deleteNode(find(value));
    }

    public boolean deleteNode(Node<E> delete) {

        if (delete.equals(Null)) {
            return false;
        } else {

            if (delete.getRight().equals(Null)) {
                transplant(delete, delete.getLeft());
            } else if (delete.getLeft().equals(Null)) {
                transplant(delete, delete.getRight());
            } else {
                Node<E> replace = minNode(delete.getRight());

                if (!delete.equals(replace.getParent())) {
                    transplant(replace, replace.getRight());
                    replace.setRight(delete.getRight());
                    replace.getRight().setParent(replace);
                }

                transplant(delete, replace);
                replace.setLeft(delete.getLeft());
                replace.getLeft().setParent(replace);

            }

            size--;
            return true;

        }

    }

    public void transplant(Node<E> target, Node<E> replace) {

        if (!target.equals(Null)) {
            if (target.getParent().equals(Null))
                root = replace;
            else if (target.equals(target.getParent().getLeft()))
                target.getParent().setLeft(replace);
            else
                target.getParent().setRight(replace);

            replace.setParent(target.getParent());
        }

    }

    public Node<E> minNode(E value) {
        return minNode(find(value));
    }

    public Node<E> minNode(Node<E> search) {
        while (!search.getLeft().equals(Null))
            search = search.getLeft();
        return search;
    }

    public Node<E> maxNode(E value) {
        return maxNode(find(value));
    }

    public Node<E> maxNode(Node<E> search) {
        while (!search.getRight().equals(Null))
            search = search.getRight();
        return search;
    }

    public Node<E> predecessor(E value) {
        return predecessor(find(value));
    }

    public Node<E> predecessor(Node<E> search) {

        if (search.getLeft().equals(Null)) {

            while (search.getParent().getLeft().equals(search)) {
                search = search.getParent();
            }

            return search.getParent();

        } else {
            return maxNode(search.getLeft());
        }

    }

    public Node<E> successor(E value) {
        return successor(find(value));
    }

    public Node<E> successor(Node<E> search) {

        if (search.getRight().equals(Null)) {

            while (search.getParent().getRight().equals(search)) {
                search = search.getParent();
            }

            return search.getParent();

        } else {
            return minNode(search.getRight());
        }

    }

    public Node<E> getRoot() {
        return root;
    }

    public int getSize() {
        return size;
    }

    public void leftRotate(Node<E> node) {

        if (!node.equals(Null)) {
            Node<E> beta = node.getRight();

            if (!beta.equals(Null)) {
                transplant(node, beta);

                beta = beta.getLeft();
                if (beta.equals(Null)) {
                    node.setParent(node.getRight());
                    node.getParent().setLeft(node);
                } else
                    transplant(beta, node);

                node.setRight(beta);

            }
        }

    }

    public void rightRotate(Node<E> node) {

        if (!node.equals(Null)) {
            Node<E> beta = node.getLeft();

            if (!beta.equals(Null)) {

                transplant(node, beta);

                beta = beta.getRight();
                if (beta.equals(Null)) {
                    node.setParent(node.getLeft());
                    node.getParent().setRight(node);
                } else
                    transplant(beta, node);

                node.setLeft(beta);

            }
        }

    }

    public void preOrder(Node<E> search) {

        if (!search.equals(Null)) {
            System.out.print(toStringNode(search) + " ");
            preOrder(search.getLeft());
            preOrder(search.getRight());
        }

    }

    public void inOrder(Node<E> search) {

        if (!search.equals(Null)) {
            inOrder(search.getLeft());
            System.out.print(toStringNode(search) + " ");
            inOrder(search.getRight());
        }

    }

    public void postOrder(Node<E> search) {

        if (!search.equals(Null)) {
            postOrder(search.getLeft());
            postOrder(search.getRight());
            System.out.print(toStringNode(search) + " ");
        }

    }

    public void printTree(){
        int h = highOfSubTree(root);
        String [][] print = new String[h+1][(int) Math.pow(2,h+1) - 1];
        printSubTree(root, print,0,((int) Math.pow(2,h)) - 1);
        for (int i = 0; i < h + 1 ; i++) {
            for (int j = 0; j < (int) Math.pow(2,h+1) - 1; j++) {
                if(print[i][j] != null)
                    System.out.print(print[i][j] + " ");
                else
                    System.out.print("          ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printSubTree(Node<E> node ,String[][] print, int row, int column) {

        if (node.equals(Null))
            return;

        print[row][column] = toStringNode(node);
        printSubTree(node.getLeft(), print, row + 1, column - 1);
        printSubTree(node.getRight(), print, row + 1, column + 1);


    }

    public List<Node<E>> getListOfSubTree(Node<E> node){

        LinkedList<Node<E>> list = new LinkedList<>();
        int index = 0 , high = highOfSubTree(node);
        list.addFirst(node);

        while (index < Math.pow(2,high + 1) - 1){

            node = list.get(index);

            list.addLast(node.getLeft());
            list.addLast(node.getRight());

            index++;

        }


        return list.subList(0,(int) Math.pow(2,high + 1) - 1);

    }

    public int sizeOfSubTree(Node<E> node) {
        if (node.equals(Null))
            return 0;

        return sizeOfSubTree(node.getLeft()) + sizeOfSubTree(node.getRight()) + 1;
    }

    public int highOfSubTree(Node<E> node){

        if(node.equals(Null))
            return -1;

        int hLeft = highOfSubTree(node.getLeft());
        int hRight = highOfSubTree(node.getRight());

        return hLeft > hRight ? hLeft + 1 : hRight + 1;

    }

    protected String toStringNode(Node<E> node) {
        return String.valueOf(node.getValue());
    }

}
