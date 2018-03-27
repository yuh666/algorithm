package sort.heap;

import sort.utils.SortTestHelper;

import java.util.Arrays;

/**
 * @Author yuh
 * @Date Created in 上午7:14 2018/3/22
 * @Description 最大索引堆
 */
public class IndexMaxHeap<T extends Comparable> {

    /**
     * 元素计数
     */
    private int count;

    /**
     * 存元素的数组 次数组非堆结构
     */
    private T[] data;

    /**
     * 索引数组 堆结构 元素是data数组对应的索引
     */
    private int[] indexes;

    /**
     * indexes反向索引数组 非堆结构 元素是indexes的索引 本身的索引是indexes的元素
     */
    private int[] reverses;

    /**
     * 最大容量
     */
    private int capacity;

    /**
     * 构造几个数组实例
     *
     * @param capacity
     */
    public IndexMaxHeap(int capacity) {
        this.capacity = capacity;
        //数组所用从1开始
        data = (T[]) new Comparable[capacity + 1];
        indexes = new int[capacity + 1];
        reverses = new int[capacity + 1];
        //将reverses里面的元素全都设置为0 因为indexes0位置不存储元素 可以判断是否包含此索引
        for (int i = 0; i < reverses.length; i++) {
            reverses[i] = 0;
        }
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
     * @param index
     * @param e
     */
    public void insert(int index, T e) {
        //index加上一 从1开始 但是对调用者显示为0
        index++;
        data[index] = e;
        indexes[count + 1] = index;
        reverses[index] = count + 1;
        count++;
        //indexes数组尾部插入进行上浮操作
        shiftUp(count);
    }

    /**
     * 通过索引获取值
     *
     * @param index
     * @return
     */
    public T getDataByIndex(int index) {
        return data[++index];
    }

    /**
     * 通过索引替换
     *
     * @param index
     * @param newE
     * @return
     */
    public T replaceByIndex(int index, T newE) {
        T oldE = data[index];
        data[index] = newE;
        int oldIndexPos = reverses[index];
        shiftUp(oldIndexPos);
        shiftDown(oldIndexPos);
        return oldE;
    }


    /**
     * 取出一个最大值 并移除
     *
     * @return
     */
    public T extractMax() {
        T e = data[indexes[1]];
        //与最后一个位置进行交换
        swapIndex(1, count);
        count--;
        //下沉
        shiftDown(1);
        //维护反向索引
        reverses[indexes[1]] = 1;
        return e;
    }

    /**
     * 取出最大索引 并移除
     *
     * @return
     */
    public int extractMaxIndex() {
        int maxIndex = indexes[1] - 1;
        swapIndex(1, count);
        count --;
        reverses[indexes[1]] = 1;
        shiftDown(1);
        return maxIndex;
    }

    /**
     * 交换索引位置 并维护反向索引数组
     *
     * @param i
     * @param j
     */
    private void swapIndex(int i, int j) {
        int tmp = indexes[i];
        indexes[i] = indexes[j];
        indexes[j] = tmp;

        //维护反向索引
        reverses[indexes[i]] = i;
        reverses[indexes[j]] = j;
    }

    /**
     * 上浮索引 根据实际的元素的大小
     *
     * @param count
     */
    private void shiftUp(int count) {
        while (count > 1 && data[indexes[count]].compareTo(data[indexes[count / 2]]) > 0) {
            swapIndex(count, count / 2);
            count /= 2;
        }
    }

    /**
     * 下沉索引
     *
     * @param k
     */
    private void shiftDown(int k) {
        while (2 * k <= count) {
            int j = 2 * k;
            if (j + 1 <= count && data[indexes[j]].compareTo(data[indexes[j + 1]]) < 0) {
                j++;
            }
            if (data[indexes[k]].compareTo(data[indexes[j]]) > 0) {
                break;
            }
            swapIndex(k, j);
            k = j;
        }
    }


    // 测试索引堆中的索引数组index和反向数组reverse
    // 注意:这个测试在向堆中插入元素以后, 不进行extract操作有效
    public boolean testIndexes() {

        int[] copyIndexes = new int[count + 1];
        int[] copyReverseIndexes = new int[count + 1];

        for (int i = 0; i <= count; i++) {
            copyIndexes[i] = indexes[i];
            copyReverseIndexes[i] = reverses[i];
        }

        copyIndexes[0] = 0;
        copyReverseIndexes[0] = 0;
        Arrays.sort(copyIndexes);
        Arrays.sort(copyReverseIndexes);

        // 在对索引堆中的索引和反向索引进行排序后,
        // 两个数组都应该正好是1...count这count个索引
        boolean res = true;
        for (int i = 1; i <= count; i++) {
            if (copyIndexes[i - 1] + 1 != copyIndexes[i] ||
                    copyReverseIndexes[i - 1] + 1 != copyReverseIndexes[i]) {
                res = false;
                break;
            }
        }

        if (!res) {
            System.out.println("Error!");
            return false;
        }

        return true;
    }


    public static void main(String[] args) {
        int N = 10;
        IndexMaxHeap<Integer> indexMaxHeap = new IndexMaxHeap<Integer>(N);
        for (int i = 0; i < N; i++) {
            indexMaxHeap.insert(i, (int) (Math.random() * N));
        }
        while(!indexMaxHeap.isEmpty()){
            System.out.print(indexMaxHeap.extractMaxIndex()+",");
        }
//        System.out.println(indexMaxHeap.testIndexes());

    }


}

