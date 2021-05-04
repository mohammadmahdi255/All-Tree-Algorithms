public class Node<E extends Comparable<E>> {

    private E value;

    private Node<E> parent;
    private Node<E> left;
    private Node<E> right;

    private String color;

    private Integer size;

    public Node(E value, Node<E> parent, Node<E> left, Node<E> right, String color, Integer size) {
        this.value = value;
        this.parent = parent;
        this.left = left;
        this.right = right;
        this.color = color;
        this.size = size;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public Node<E> getParent() {
        return parent;
    }

    public void setParent(Node<E> parent) {
        this.parent = parent;
    }

    public Node<E> getLeft() {
        return left;
    }

    public void setLeft(Node<E> left) {
        this.left = left;
    }

    public Node<E> getRight() {
        return right;
    }

    public void setRight(Node<E> right) {
        this.right = right;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public boolean isLeaf(){
        return parent == null || left.equals(right);
    }

    public Node<E> getBrother(){
        return parent == null ? this : parent.getRight().equals(this) ? parent.getLeft() : parent.getRight();
    }

    public Node<E> getGrandPa(){
        return parent.getParent() == null ? parent : parent.getParent();
    }

    public Node<E> getAncle(){
        return parent.getBrother() == null ? parent : parent.getBrother();
    }

}
