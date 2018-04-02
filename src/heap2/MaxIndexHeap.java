package heap2;

/**
 * 反向索引版本最大索引堆
 */
public class MaxIndexHeap<T extends Comparable> {
    /**
     * 元素数量
     */
    private int count;
    /**
     * 元素的数组
     */
    private T[] data;
    /**
     * 索引数组 保存的是 和data相关联的索引 是堆结构(根据元素的大小比较的形成的堆结构)
     */
    private int[] index;
    /**
     * index数组的反向索引
     */
    private int[] reverse;

    public MaxIndexHeap(int capacity) {
        data = (T[]) new Comparable[capacity + 1];
        index = new int[capacity + 1];
        reverse = new int[capacity + 1];
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
    public void insert(int idx, T e) {
        idx++;
        data[idx] = e;
        index[count + 1] = idx;
        reverse[idx] = count + 1;
        count++;
        shiftUp(count);

    }

    /**
     * 获取最小元素
     *
     * @return
     */
    public T extractMaxData() {
        T e = data[index[1]];
        swapIndex(1, count);
        count--;
        shiftDown(1);
        return e;
    }

    /**
     * 获取最小的索引
     *
     * @return
     */
    public int extractMaxIndex() {
        int idx = index[1];
        swapIndex(1, count);
        count--;
        shiftDown(1);
        return --idx;
    }

    /**
     * 根据索引替换元素
     *
     * @param idx
     * @param e
     * @return
     */
    public T replaceByIndex(int idx, T e) {
        idx++;
        T oldE = data[idx];
        data[idx] = e;
        int i = reverse[idx];
        shiftDown(i);
        shiftUp(i);
        return oldE;
    }


    /**
     * 插入时候小值上浮
     *
     * @param k
     */
    private void shiftUp(int k) {
        while (k > 1 && data[index[k]].compareTo(data[index[k / 2]]) > 0) {
            swapIndex(k, k / 2);
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
            if (j + 1 <= count && data[index[j]].compareTo(data[index[j + 1]]) < 0) {
                j++;
            }
            if (data[index[j]].compareTo(data[index[k]]) <= 0) {
                break;
            }
            swapIndex(k, j);
            k = j;
        }
    }

    /**
     * 交换索引位置
     *
     * @param i
     * @param j
     */
    private void swapIndex(int i, int j) {
        int tmp = index[i];
        index[i] = index[j];
        index[j] = tmp;

        reverse[index[i]] = i;
        reverse[index[j]] = j;
    }

    public static void main(String[] args) {
        MaxIndexHeap<Integer> maxIndexHeap = new MaxIndexHeap<>(10);
        for (int i = 0; i < 10; i++) {
            maxIndexHeap.insert(i,i);
        }

        maxIndexHeap.replaceByIndex(5,100);

        while (!maxIndexHeap.isEmpty()) {
            System.out.print(maxIndexHeap.extractMaxIndex() + ",");
        }
    }
}
