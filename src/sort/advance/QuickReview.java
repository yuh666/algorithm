package sort.advance;

import sort.utils.SortTestHelper;

/**
 * @Author yuh
 * @Date Created in 上午6:34 2018/3/21
 * @Description 快速排序复习
 */
public class QuickReview {


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
     */
    private static void quickSort(Comparable[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int index = twoWays(arr, l, r);
        quickSort(arr, l, index - 1);
        quickSort(arr, index + 1, r);
    }


    /**
     * 双路切分
     *
     * @param arr
     * @param l
     * @param r
     * @return
     */
    private static int twoWays(Comparable[] arr, int l, int r) {
        SortTestHelper.swap(arr, l, (int) (Math.random() * (r - l + 1) + l));
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

        SortTestHelper.swap(arr, j, l);
        return j;

    }


    /**
     * 双路切分
     *
     * @param arr
     * @param l
     * @param r
     * @return
     */
    private static void threeWays(Comparable[] arr, int l, int r) {
        if(l >= r){
            return;
        }
        SortTestHelper.swap(arr, l, (int) (Math.random() * (r - l + 1) + l));
        Comparable e = arr[l];
        int i = l + 1, lt = l, gt = r + 1;

        while (i < gt) {
            if (arr[i].compareTo(e) > 0) {
                SortTestHelper.swap(arr, --gt, i);
            } else if (arr[i].compareTo(e) < 0) {
                SortTestHelper.swap(arr, ++lt, i++);
            } else {
                i++;
            }
        }

        SortTestHelper.swap(arr,l,lt);

        threeWays(arr,l,lt-1);
        threeWays(arr,gt,r);
    }


    /**
     * 单路切分
     *
     * @param arr
     * @param l
     * @param r
     * @return
     */
    private static int oneWay(Comparable[] arr, int l, int r) {
        SortTestHelper.swap(arr, l, (int) (Math.random() * (r - l + 1)) + l);
        Comparable e = arr[l];
        int lt = l;
        for (int i = l + 1; i <= r; i++) {
            if (arr[i].compareTo(e) < 0) {
                SortTestHelper.swap(arr, i, ++lt);
            }
        }
        SortTestHelper.swap(arr, l, lt);
        return lt;
    }

    public static void main(String[] args) {
        SortTestHelper.getSortTime("sort.advance.QuickReview", SortTestHelper.generateIntegerArray(50000, 1, 50000));
    }
}
