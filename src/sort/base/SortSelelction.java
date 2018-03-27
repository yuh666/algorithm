package sort.base;

import sort.utils.SortTestHelper;

/**
 * @Author yuh
 * @Date Created in 下午3:10 2018/3/19
 * @Description 选择排序
 */
public class SortSelelction {

    /**
     * 排序方法
     *
     * @param arr
     */
    public static <T extends Comparable<T>> void sort(T[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min].compareTo(arr[j]) > 0) {
                    min = j;
                }
            }
            if (min != i) {
                SortTestHelper.swap(arr, i, min);
            }
        }
    }

    public static void main(String[] args) {
        SortTestHelper.getSortTime("sort.base.SortSelelction", SortTestHelper.generateIntegerArray(10000, 1, 10000));
    }
}
