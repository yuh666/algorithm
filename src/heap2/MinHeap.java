package heap2;

/**
 * 最小堆
 */
public class MinHeap<T extends Comparable> {
    /**
     * 元素数量
     */
    private int count;
    /**
     * 元素数组
     */
    private T[] data;
    /**
     * 元素数量阈值
     */
    private int capacity;

    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.data = (T[]) new Comparable[capacity + 1];
    }

    /**
     * 是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * 插入一个元素
     *
     * @param e
     */
    private void insert(T e) {
        data[++count] = e;
        shiftUp(count);
    }

    /**
     * 抽取最小值
     *
     * @return
     */
    public T extractMin() {
        T e = data[1];
        swap(1, count);
        count--;
        shiftDown(1);
        return e;
    }

    /**
     * 插入时候小值上浮
     *
     * @param k
     */
    private void shiftUp(int k) {
        while (k > 1 && data[k].compareTo(data[k / 2]) < 0) {
            swap(k, k / 2);
            k /= 2;
        }
    }

    /**
     * 取出数据后要进行下沉
     *
     * @param k
     */
    private void shiftDown(int k) {
        while (2 * k <= count) {
            int j = 2 * k;
            if (j + 1 <= count && data[j].compareTo(data[j + 1]) > 0) {
                j++;
            }
            if (data[j].compareTo(data[k]) >= 0) {
                break;
            }
            swap(k, j);
            k = j;
        }
    }

    /**
     * 交换元素
     *
     * @param i
     * @param j
     */
    private void swap(int i, int j) {
        T tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    public static void main(String[] args) {
        MinHeap<Integer> minHeap = new MinHeap<>(10);
        for (int i = 0; i < 10; i++) {
            minHeap.insert(i);
        }
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.extractMin() + ",");
        }
    }
}
