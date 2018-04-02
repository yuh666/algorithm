package sort2;

import java.util.Arrays;

/**
 * @Author yuh
 * @Date Created in 上午7:47 2018/4/2
 * @Description 排序工具类
 */
public class SortUtil {

    /**
     * 希尔排序
     * 是插入排序的一种增强
     * * @param arr
     */
    public static void shellSort(Comparable[] arr) {
        int gap = 1, n = arr.length;
        while (gap < n / 3) {
            gap = gap * 3 + 1;
        }

        while (gap >= 1) {
            for (int i = gap; i < n; i++) {
                int j = i;
                Comparable e = arr[i];
                for (; j >= gap && e.compareTo(arr[j - gap]) < 0; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = e;
            }
            gap /= 3;
        }
    }

    /**
     * 快速排序 三路
     *
     * @param arr
     */
    public static void quickSort(Comparable[] arr) {
        threeWays(arr, 0, arr.length - 1);
    }

    /**
     * 三路切分
     *
     * @param arr
     * @param l
     * @param r
     */
    private static void threeWays(Comparable[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int lt = l, i = l + 1, gt = r + 1;
        Comparable e = arr[l];
        while (i < gt) {
            if (arr[i].compareTo(e) > 0) {
                swap(arr, --gt, i);
            } else if (arr[i].compareTo(e) < 0) {
                swap(arr, ++lt, i++);
            } else {
                i++;
            }
        }
        swap(arr, l, lt);
        threeWays(arr, l, lt - 1);
        threeWays(arr, gt, r);
    }

    /**
     * 归并排序
     *
     * @param arr
     */
    public static void mergeSort(Comparable[] arr) {
        Comparable[] aux = new Comparable[arr.length];
        mergeSortAux(arr, aux, 0, arr.length - 1);
    }

    /**
     * 排序辅助类
     *
     * @param arr
     * @param aux
     * @param l
     * @param r
     */
    private static void mergeSortAux(Comparable[] arr, Comparable[] aux, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = (r - l) / 2 + l;
        mergeSortAux(arr, aux, l, mid);
        mergeSortAux(arr, aux, mid + 1, r);
        merge(arr, aux, l, r, mid);
    }

    /**
     * 归并过程
     *
     * @param arr
     * @param aux
     * @param l
     * @param r
     * @param mid
     */
    private static void merge(Comparable[] arr, Comparable[] aux, int l, int r, int mid) {
        System.arraycopy(arr, 0, aux, 0, arr.length);
        int i = l, j = mid + 1;
        for (int k = l; k <= r; k++) {
            if (j > r) {
                arr[k] = aux[i++];
            } else if (i > mid) {
                arr[k] = aux[j++];
            } else if (aux[i].compareTo(aux[j]) < 0) {
                arr[k] = aux[i++];
            } else {
                arr[k] = arr[j++];
            }
        }
    }

    private static void swap(Comparable[] arr, int a, int b) {
        Comparable tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }


    public static void main(String[] args) {
        Integer[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        //shellSort(arr);
        //quickSort(arr);
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
