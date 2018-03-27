package sort.heap;

import java.util.Arrays;

/**
 * @Author yuh
 * @Date Created in 下午5:23 2018/3/22
 * @Description 最大索引堆复习
 */
public class MaxIndexHeapReview<T extends Comparable> {

    /**
     * 元素的数量
     */
    private int count;
    /**
     * 元素的数组 索引从1开始
     */
    private T[] data;
    /**
     * 索引的数组 堆结构 存储的是元素的在data数组中的索引
     */
    private int[] indexes;
    /**
     * indexes的反向所索引 仅此而已 在进行索引替换的时候 知道用户索引的indexes数组中的具体位置 将O(n)降低为O(1)
     */
    private int[] reverses;

    /**
     * 是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return count == 1;
    }

    /**
     * 插入一个元素
     *
     * @param e
     * @param index
     */
    public void insert(int index, T e) {
        //索引加1
        index++;
        data[index] = e;
        //维护indexes
        indexes[count + 1] = index;
        //维护reverses
        reverses[index] = count + 1;
        count++;
        //上浮 根据index取得data中的元素进行比较
        shiftUp(index);
    }

    /**
     * 取出并移除最大元素
     *
     * @return
     */
    public T extractMax() {
        T e = data[indexes[1]];
        swapIndex(1, count);
        reverses[indexes[count]] = 1;
        count--;
        shiftDown(1);
        return e;
    }

    /**
     * 取出并且移除最大元素的索引
     *
     * @return
     */
    public int extractMaxIndex() {
        int index = indexes[1];
        swapIndex(1, count);
        reverses[indexes[count]] = 1;
        count--;
        shiftDown(1);
        return ++index;
    }

    /**
     * 替换指定索引位置的元素
     *
     * @param index
     * @param e
     * @return
     */
    public T replaceByIndex(int index, T newE) {
        T e = data[index];
        data[index] = newE;
        int oldIndex = reverses[index];
        shiftUp(oldIndex);
        shiftDown(oldIndex);
        return e;
    }

    /**
     * 根据index下沉
     *
     * @param k
     */
    private void shiftDown(int k) {
        while (2 * k <= count) {
            int j = 2 * k;
            if (j + 1 <= count && data[indexes[j]].compareTo(data[indexes[j + 1]]) < 0) {
                j++;
            }
            if (data[indexes[j]].compareTo(data[indexes[k]]) < 0) {
                break;
            }
            swapIndex(k, j);
            k = j;
        }
    }


    /**
     * 根据index上浮
     *
     * @param index
     */
    private void shiftUp(int index) {
        while (index > 0 && data[indexes[index]].compareTo(data[indexes[index / 2]]) < 0) {
            swapIndex(index, index / 2);
            index /= 2;
        }
    }

    /**
     * 在indexes堆中上浮
     *
     * @param index
     * @param i
     */
    private void swapIndex(int i, int j) {
        int tmp = indexes[i];
        indexes[i] = indexes[j];
        indexes[j] = tmp;

        //维护reverses
        reverses[indexes[i]] = i;
        reverses[indexes[j]] = j;
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
            System.out.print(indexMaxHeap.extractMax()+",");
        }
        //System.out.println(indexMaxHeap.testIndexes());

    }


}
