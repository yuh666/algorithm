package graph.weight.prim;

/**
 * 最小堆复习
 */
public class MinHeapReview<T extends Comparable<T>> {
    /**
     * 元素的数量
     */
    private int count;

    /**
     * 堆得大小
     */
    private int capacity;

    /**
     * 实际存放的数组
     */
    private T[] elements;

    public MinHeapReview(int capacity) {
        //数组索引从1开始
        elements = (T[]) new Comparable[capacity + 1];
        for (int i = elements.length/2; i < elements.length; i++) {
            shiftDown(i);
        }
    }

    public MinHeapReview(T[] arr) {
        //数组索引从1开始
        elements = (T[]) new Comparable[arr.length + 1];
        System.arraycopy(arr, 0, elements, 1, arr.length);
        count = arr.length;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    public void insert(T e) {
        elements[count + 1] = e;
        count++;
        shitUp(count);
    }

    public T extractMin() {
        T e = elements[1];
        swap(1, count);
        count--;
        shiftDown(1);
        return e;
    }

    private void shiftDown(int k) {
        while (2 * k <= count) {
            int j = 2 * k;
            if (j + 1 <= count && elements[j].compareTo(elements[j + 1]) > 0) {
                j++;
            }
            if (elements[k].compareTo(elements[j]) <= 0) {
                break;
            }
            swap(k, j);
            k = j;
        }
    }

    private void shitUp(int k) {
        while (k > 1 && elements[k / 2].compareTo(elements[k]) > 0) {
            swap(k, k / 2);
            k /= 2;
        }
    }

    private void swap(int i, int j) {
        T tmp = elements[i];
        elements[i] = elements[j];
        elements[j] = tmp;
    }

    public static void main(String[] args) {

        MinHeapReview<Integer> minHeap = new MinHeapReview<Integer>(100);
        int N = 100; // 堆中元素个数
        int M = 100; // 堆中元素取值范围[0, M)
        for (int i = 0; i < N; i++) {
            minHeap.insert(new Integer((int) (Math.random() * M)));
        }

        Integer[] arr = new Integer[N];
        // 将minheap中的数据逐渐使用extractMin取出来
        // 取出来的顺序应该是按照从小到大的顺序取出来的
        for (int i = 0; i < N; i++) {
            arr[i] = minHeap.extractMin();
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        // 确保arr数组是从小到大排列的
        for (int i = 1; i < N; i++) {
            assert arr[i - 1] <= arr[i];
        }
    }
}
