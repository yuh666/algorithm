package sort.heap;

import sort.utils.SortTestHelper;

/**
 * @Author yuh
 * @Date Created in 下午3:42 2018/3/20
 * @Description 堆实现优先队列
 */
public class PriorityQueue<T extends Comparable> {

    private T[] items;
    private int count;

    public PriorityQueue(int capacity) {
        items = (T[]) new Comparable[capacity];
        count = 0;
    }


    public PriorityQueue(T[] arr) {
        items = (T[]) new Comparable[arr.length + 1];
        System.arraycopy(arr, 0, items, 1, arr.length);
        for (int i = items.length / 2; i >= 1; i--) {
            shiftDown(i);
        }
        count = arr.length;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public void offer(T item) {
        //扩容
        if (count + 1 >= items.length) {
            changeCapacity(2 * items.length);
        }
        items[count + 1] = item;
        count++;
        shiftUp(count);
    }

    private void changeCapacity(int i) {
        T[] newArr = (T[]) new Comparable[i];
        System.arraycopy(items, 1, newArr, 1, count);
        items = null;
        items = newArr;
    }

    public T take() {
        T item = items[1];
        items[1] = items[count];
        items[count] = null;
        count--;
        shiftDown(1);
        //缩减
        if (count < items.length / 2) {
            changeCapacity(items.length / 2);
        }
        return item;
    }

    private void shiftDown(int k) {
        while (2 * k <= count) {
            int j = 2 * k;
            if (j + 1 <= count && items[j].compareTo(items[j + 1]) < 0) {
                j++;
            }
            if (items[k].compareTo(items[j]) > 0) {
                break;
            }
            SortTestHelper.swap(items, k, j);
            k = j;
        }
    }

    private void shiftUp(int count) {
        while (count > 1 && items[count].compareTo(items[count / 2]) > 0) {
            SortTestHelper.swap(items, count, count / 2);
            count /= 2;
        }
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(5);
        Integer[] array = SortTestHelper.generateIntegerArray(10, 1, 100);
        for (Integer i : array) {
            queue.offer(i);
        }
        while (!queue.isEmpty()) {
            System.out.println(queue.take());
        }
    }

}
