// https://www.acmicpc.net/problem/5639
/*
    이진 탐색 트리 - BST 구현
 */


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Solve solve = new Solve();
        solve.run();

    }

}

class Solve {
    int N;

    public void run() {
        Scanner sc = new Scanner(System.in);
        BST btree = new BST();

        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            if (s.equals("")) break;
            btree.insert(Integer.parseInt(s));
        }

        btree.postOrderTraversal();

    }
}

class Node {
    Integer val;
    Node left;
    Node right;

    public Node(Integer val, Node left, Node right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class BST {
    Node root;
    int size;

    public BST() {
        size = 0;
    }

    void insert(int i) {
        Node newNode = new Node(i, null, null);
        if (root == null) {
            root = newNode;
            size++;
        } else {
            Node curr = root;
            while (true) {
                if (i < curr.val) {
                    if (curr.left == null) {
                        curr.left = newNode;
                        size++;
                        return;
                    } else {
                        curr = curr.left;
                    }
                } else if (i > curr.val) {
                    if (curr.right == null) {
                        curr.right = newNode;
                        size++;
                        return;
                    } else {
                        curr = curr.right;
                    }
                } else {
                    curr.val = i;
                }
            }
        }
    }

    public void postOrderTraversal() {
        postOrderTraversal(root);
    }

    private void postOrderTraversal(Node curr) {
        if (curr != null) {
            postOrderTraversal(curr.left);
            postOrderTraversal(curr.right);
            System.out.println(curr.val);
        }
    }
}
