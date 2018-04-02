package heap2;

import java.util.Arrays;
import java.util.List;

/**
 * 最小堆
 */
public class MaxHeap<T extends Comparable> {
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

    public MaxHeap(int capacity) {
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
    public T extractMax() {
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
        while (k > 1 && data[k].compareTo(data[k / 2]) > 0) {
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
            if (j + 1 <= count && data[j].compareTo(data[j + 1]) < 0) {
                j++;
            }
            if (data[j].compareTo(data[k]) <= 0) {
                break;
            }
            swap(k, j);
            k = j;
        }
    }

    /**
     * 批量添加 添加的都是子类 保证拥有T的方法
     *
     * @param list
     */
    public void insert(List<? extends T> list) {
        for (T t : list) {
            insert(t);
        }
    }

    /**
     * 获取所有 取出之后以T的姿态显示
     *
     * @return
     */
    public List<? super T> getAll() {
        return Arrays.asList(data);
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
        MaxHeap<Integer> minHeap = new MaxHeap<>(10);
        for (int i = 0; i < 10; i++) {
            minHeap.insert(i);
        }
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.extractMax() + ",");
        }
    }
}
