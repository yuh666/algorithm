package sort.heap;

import sort.utils.SortTestHelper;

/**
 * @Author yuh
 * @Date Created in 上午7:18 2018/3/21
 * @Description 堆排序复习
 */
public class HeapSortReview {

    /**
     * 数组原地下沉
     *
     * @param k
     * @param n
     */
    private static void shitDown(Comparable[] arr, int k, int n) {
        while (2 * k + 1 <= n) {
            int j = 2 * k + 1;
            if (j + 1 <= n && arr[j].compareTo(arr[j + 1]) < 0) {
                j++;
            }
            if (arr[k].compareTo(arr[j]) > 0) {
                break;
            }
            SortTestHelper.swap(arr, k, j);
            k = j;
        }
    }

    /**
     * 上浮操作
     * @param arr
     * @param k
     */
    private static void shitUp(Comparable[] arr, int k) {
        while (k > 0 && arr[k].compareTo(arr[k / 2]) > 0) {
            SortTestHelper.swap(arr, k, k / 2);
            k /= 2;
        }
    }

    /**
     * 排序入口
     *
     * @param arr
     */
    public static void sort(Comparable[] arr) {
        int n = arr.length;
        for (int i = (n - 1) / 2; i >= 0; i--) {
            shitDown(arr, i, n - 1);
        }
        for (int i = n - 1; i > 0; i--) {
            SortTestHelper.swap(arr, 0, i);
            shitDown(arr, 0, i - 1);
        }
    }


    public static void main(String[] args) {
        SortTestHelper.getSortTime("sort.heap.HeapSortReview", SortTestHelper.generateIntegerArray(50000, 1, 50000));
    }
}
