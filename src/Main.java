import java.util.List;

public class Main {

    public static void main(String[] args) {

        RedBlackTree<Integer> tree = new RedBlackTree<>();
//        tree.addNode(20,36);
//        tree.addNode(3, 41);
//        tree.addNode(29,99);
//        tree.addNode(0,1);
//        tree.addNode(10,15);
//        tree.addNode(41,109);
        tree.addNode(41);
        tree.addNode(38);
        tree.addNode(31);
//        tree.addNode(12);
//        tree.addNode(19);
//        tree.addNode(8);
//        tree.addNode(41);
//        tree.addNode(30);
//        tree.addNode(47);
//        tree.addNode(28);
//        tree.addNode(38);
//        tree.addNode(35);
//        tree.addNode(39);

//        tree.deleteNode(8);
//        tree.deleteNode(12);
//        tree.deleteNode(19);
//        tree.deleteNode(31);
//        tree.deleteNode(38);
//        tree.deleteNode(41);
//        tree.deleteNode(41);

        tree.printTree();

        tree.preOrder(tree.getRoot());
        System.out.println();
        tree.inOrder(tree.getRoot());
        System.out.println();
        tree.postOrder(tree.getRoot());
        System.out.println();

        System.out.println(tree.getSize());
        System.out.println(tree.highOfSubTree(tree.getRoot()));

        List<Node<Integer>> list = tree.getListOfSubTree(tree.root);

        for (Node<Integer> node : list) {
            System.out.print(tree.toStringNode(node) + "  ");
        }

        System.out.println();

//        MaxHeap<Integer> maxHeap = new MaxHeap<>();
//        maxHeap.addMaxHeap(1);
//        maxHeap.addMaxHeap(12);
//        maxHeap.addMaxHeap(16);
//        maxHeap.addMaxHeap(20);
//        maxHeap.addMaxHeap(25);
//        maxHeap.addMaxHeap(27);
//        maxHeap.addMaxHeap(29);
//        maxHeap.addMaxHeap(33);
//        maxHeap.addMaxHeap(43);
//        maxHeap.addMaxHeap(47);
//        maxHeap.addMaxHeap(48);
//        maxHeap.addMaxHeap(58);
//
//        maxHeap.remove(29);
//
//        System.out.println(maxHeap.minimum(0));
//
//        maxHeap.printAll();
//
//
//        MinHeap<Integer> minHeap = new MinHeap<>();
//        minHeap.addMinxHeap(1);
//        minHeap.addMinxHeap(12);
//        minHeap.addMinxHeap(16);
//        minHeap.addMinxHeap(20);
//        minHeap.addMinxHeap(25);
//        minHeap.addMinxHeap(27);
//        minHeap.addMinxHeap(29);
//        minHeap.addMinxHeap(33);
//        minHeap.addMinxHeap(43);
//        minHeap.addMinxHeap(47);
//        minHeap.addMinxHeap(48);
//        minHeap.addMinxHeap(58);
//
//        minHeap.remove(29);
//
//        System.out.println(minHeap.maximum(0));
//
//        minHeap.printAll();






    }

}
