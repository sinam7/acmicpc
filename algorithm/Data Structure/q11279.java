// https://www.acmicpc.net/problem/11279
/*
    Max Heap 구현하기
 */

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Heap<Integer> priorityQueue = new Heap<>();

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            int cmd = sc.nextInt();
            if (cmd == 0) {
                Integer result = priorityQueue.removeMax();
                if (result == null) System.out.println(0);
                else System.out.println(result);
            } else priorityQueue.insert(cmd);
        }

        sc.close();

    }

}

class Heap<E extends Comparable<E>> implements PriorityQueue<E> {

    private final int DEFAULT_CAPACITY = 10;

    private int size;
    private int capacity = DEFAULT_CAPACITY;

    E[] data;

    @SuppressWarnings("unchecked")
    public Heap() {
        size = 0;
        data = (E[]) new Comparable[DEFAULT_CAPACITY];
    }

    @Override
    public void insert(E e) {
        if (size + 1 > capacity) doubling();
        int idx = size++;
        data[idx] = e;
        siftup(idx);
    }

    @SuppressWarnings("unchecked")
    private void doubling() {
        capacity *= 2;
        E[] newData = (E[]) new Comparable[capacity];
        if (size >= 0) System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    @Override
    public E removeMax() {
        if (size == 0) return null;

        E out = data[0];
        swap(0, size - 1);
        size--;
        siftdown();

        return out;
    }


    // data 배열의 0번 인덱스부터 활용함.
    private int parent(int idx) {
        return (idx - 1) / 2;
    }

    private int leftChild(int idx) {
        return idx * 2 + 1;
    }

    private int rightChild(int idx) {
        return idx * 2 + 2;
    }

    private boolean isLeaf(int idx) {
        return idx * 2 + 1 >= size && idx < size;
    }

    // iterative method
    private void siftdown() {
        if (size == 0) return;

        int i = 0;
        while (!isLeaf(i)) {
            int largest = i;
            if (leftChild(i) < size && data[largest].compareTo(data[leftChild(i)]) < 0)
                largest = leftChild(i);
            if (rightChild(i) < size && data[largest].compareTo(data[rightChild(i)]) < 0)
                largest = rightChild(i);

            if (i != largest) {
                swap(i, largest);
                i = largest;
            } else {
                break;
            }
        }
    }

    // recursive method
    private void siftup(int i) {
        if (size == 0) return;
        int parent = parent(i);
        if (data[parent].compareTo(data[i]) < 0) {
            swap(i, parent);
            siftup(parent);
        }
    }

    private void swap(int i, int j) {
        E temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

}

interface PriorityQueue<E> {
    void insert(E e);
    E removeMax();
}