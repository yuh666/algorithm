package sort.base;

import sort.utils.SortTestHelper;

/**
 * @Author yuh
 * @Date Created in 下午6:30 2018/3/19
 * @Description 希尔排序
 */
public class ShellSort {

    /**
     * 排序方法
     *
     * @param arr
     */
    public static void sort(Comparable[] arr) {
        int length = arr.length;
        int gap = 1;
        while (gap < length / 3) {
            gap = gap * 3 + 1;
        }

        while (gap >= 1) {
            for (int i = gap; i < arr.length; i++) {
                Comparable e = arr[i];
                int j = i;
                for (; j >= gap && arr[j - gap].compareTo(e) > 0; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = e;
            }
            gap = gap / 3;
        }

    }

    public static void main(String[] args) {
        SortTestHelper.getSortTime("sort.base.ShellSort", SortTestHelper.generateIntegerArray(10000, 1, 10000));
    }
}
