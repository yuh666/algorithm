package sort.advance;

import sort.utils.SortTestHelper;

/**
 * @Author yuh
 * @Date Created in 上午10:24 2018/3/20
 * @Description 快速排序
 */
public class QuickSort {


    /**
     * 排序入口
     *
     * @param arr
     */
    public static void sort(Comparable[] arr) {
        //quickSort(arr, 0, arr.length - 1);
        threeWays(arr, 0, arr.length - 1);
    }

    /**
     * 快速排序
     *
     * @param arr
     * @param l
     * @param r
     */
    public static void quickSort(Comparable[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int partition = twoWays(arr, l, r);
        quickSort(arr, l, partition - 1);
        quickSort(arr, partition + 1, r);
    }

    /**
     * 单路切分
     *
     * @param arr
     * @param l
     * @param r
     */
    private static int partition(Comparable[] arr, int l, int r) {
        SortTestHelper.swap(arr, (int) (Math.random() * (r - l + 1) + l), l);
        int i = l + 1, j = l;
        Comparable e = arr[l];
        for (; i <= r; i++) {
            if (arr[i].compareTo(e) < 0) {
                SortTestHelper.swap(arr, ++j, i);
            }
        }
        SortTestHelper.swap(arr, l, j);
        return j;
    }

    /**
     * 双位切分
     *
     * @param arr
     * @param l
     * @param r
     */
    private static int twoWays(Comparable[] arr, int l, int r) {

        SortTestHelper.swap(arr, (int) (Math.random() * (r - l + 1) + l), l);
        Comparable e = arr[l];
        int i = l + 1, j = r;
        while (true) {
            while (i <= r && arr[i].compareTo(e) < 0) {
                i++;
            }
            while (j >= l && arr[j].compareTo(e) > 0) {
                j--;
            }
            if (i > j) {
                break;
            }
            SortTestHelper.swap(arr, i++, j--);
        }
        SortTestHelper.swap(arr, l, j);
        return j;
    }

    /**
     * 三路极速快排
     *
     * @param arr
     */
    public static void threeWays(Comparable[] arr, int l, int r) {

        if (l >= r) {
            return;
        }

        int lt = l, gt = r + 1, i = l + 1;
        SortTestHelper.swap(arr, (int) (Math.random() * (r - l + 1) + l), l);
        Comparable e = arr[l];

        while (i < gt) {
            if (arr[i].compareTo(e) < 0) {
                SortTestHelper.swap(arr, ++lt, i++);
            } else if (arr[i].compareTo(e) > 0) {
                SortTestHelper.swap(arr, --gt, i);
            } else {
                i++;
            }
        }
        SortTestHelper.swap(arr, l, lt);
        threeWays(arr, l, lt - 1);
        threeWays(arr, gt, r);
    }


    public static void main(String[] args) {
        SortTestHelper.getSortTime("sort.advance.QuickSort", SortTestHelper.generateIntegerArray(50000, 1, 50000));
    }
}
