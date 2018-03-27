package sort.base;

import sort.utils.SortTestHelper;

/**
 * @Author yuh
 * @Date Created in 下午6:30 2018/3/19
 * @Description 插入排序
 */
public class InsertSort {

    /**
     * 排序方法
     *
     * @param arr
     */
    public static void sort(Comparable[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Comparable e = arr[i];
            int j = i;
            for (; j > 0 && arr[j - 1].compareTo(e) > 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = e;
        }
    }

    public static void sortRange(Comparable[] arr, int l, int r) {
        for (int i = l + 1; i <= r; i++) {
            Comparable e = arr[i];
            int j = i;
            for (; j > l && arr[j - 1].compareTo(e) > 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = e;
        }
    }

    public static void main(String[] args) {
        SortTestHelper.getSortTime("sort.base.InsertSort", SortTestHelper.generateIntegerArray(10000, 1, 10000));
    }
}
