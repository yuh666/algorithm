package sort.advance;

import sort.utils.SortTestHelper;

/**
 * @Author yuh
 * @Date Created in 上午6:20 2018/3/20
 * @Description 归并排序复习
 */
public class MergeSortReview {


    /**
     * 自底向上的归并排序
     *
     * @param arr
     */
    public static void sort(Comparable[] arr) {
        for (int i = 1; i < arr.length; i *= 2) {
            for (int j = 0; j + i < arr.length; j += 2 * i) {
                merge(arr, j + i - 1, j, Math.min(j + 2 * i - 1, arr.length - 1));
            }
        }
    }


    /**
     * 排序入口
     *
     * @param arr
     */
    public static void sort1(Comparable[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    /**
     * 归并排序递归入口
     *
     * @param arr
     * @param l
     * @param r
     */
    private static void mergeSort(Comparable[] arr, int l, int r) {
        if (r - l <= 15) {
            insertSortRange(arr, l, r);
            return;
        }

        int mid = (l + r) / 2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        if (arr[mid].compareTo(arr[mid + 1]) > 0) {
            merge(arr, mid, l, r);
        }
    }


    /**
     * 归并方法
     *
     * @param arr
     * @param mid
     * @param l
     * @param r
     */
    private static void merge(Comparable[] arr, int mid, int l, int r) {
        //新建临时数组
        Comparable[] tmp = new Comparable[r - l + 1];
        //从l开始复制数据
        for (int i = l; i <= r; i++) {
            tmp[i - l] = arr[i];
        }
        //设置左右索引 用于在临时的数组中寻址
        int lIndex = l, rIndex = mid + 1;
        for (int i = l; i <= r; i++) {
            if (rIndex > r) {
                arr[i] = tmp[lIndex++ - l];
            } else if (lIndex > mid) {
                arr[i] = tmp[rIndex++ - l];
            } else if (tmp[lIndex - l].compareTo(tmp[rIndex - l]) > 0) {
                arr[i] = tmp[rIndex++ - l];
            } else {
                arr[i] = tmp[lIndex++ - l];
            }
        }
    }


    /**
     * 对指定范围进行插入排序
     *
     * @param arr
     * @param l
     * @param r
     */
    private static void insertSortRange(Comparable[] arr, int l, int r) {
        for (int i = l + 1; i <= r; i++) {
            Comparable e = arr[i];
            int j = i;
            for (; j > l && arr[j].compareTo(arr[j - 1]) < 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = e;
        }
    }


    public static void main(String[] args) {
        SortTestHelper.getSortTime("sort.advance.MergeSortReview", SortTestHelper.generateIntegerArray(50000, 1, 50000));
    }

}
