package sort.advance;

import sort.base.InsertSort;
import sort.utils.SortTestHelper;

import java.util.Arrays;

/**
 * @Author yuh
 * @Date Created in 下午8:07 2018/3/19
 * @Description 归并排序
 */
public class MergeSort {


    /**
     * 自底向上的排序入口
     *
     * @param arr
     */
    public static void sort(Comparable[] arr) {
        for (int i = 1; i < arr.length; i *= 2) {
            for (int j = 0; j + i < arr.length; j += 2 * i) {
                merge(arr, i + j - 1, j, Math.min(j + 2 * i - 1, arr.length - 1));
            }
        }
    }


    /**
     * 入口
     *
     * @param arr
     */
    public static void sort1(Comparable[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }


    /**
     * 归并排序
     *
     * @param arr
     * @param l
     * @param r
     */
    private static void mergeSort(Comparable[] arr, int l, int r) {
        if (r - l < 15) {
            InsertSort.sortRange(arr, l, r);
            return;
        }
        int mid = (r + l) / 2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        if (arr[mid].compareTo(arr[mid + 1]) > 0) {
            merge(arr, mid, l, r);
        }
    }

    /**
     * 归并操作
     *
     * @param arr
     * @param mid
     * @param l
     * @param r
     */
    private static void merge(Comparable[] arr, int mid, int l, int r) {
        /**
         * 开辟数组空间及其耗费时间
         */
        Comparable[] tmp = new Comparable[r - l + 1];
        for (int i = l; i <= r; i++) {
            tmp[i - l] = arr[i];
        }
        int lIndex = l, rIndex = mid + 1;
        for (int i = l; i <= r; i++) {
            if (lIndex > mid) {
                arr[i] = tmp[rIndex++ - l];
            } else if (rIndex > r) {
                arr[i] = tmp[lIndex++ - l];
            } else if (tmp[lIndex - l].compareTo(tmp[rIndex - l]) < 0) {
                arr[i] = tmp[lIndex++ - l];
            } else {
                arr[i] = tmp[rIndex++ - l];
            }
        }
    }

    public static void main(String[] args) {
        SortTestHelper.getSortTime("sort.advance.MergeSort", SortTestHelper.generateIntegerArray(50000, 1, 50000));
    }

}
