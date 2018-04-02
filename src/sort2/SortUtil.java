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
        while(i < gt){
            if(arr[i].compareTo(e) > 0){
                swap(arr,--gt,i);
            }else if(arr[i].compareTo(e) < 0){
                swap(arr,++lt,i++);
            }else{
                i ++;
            }
        }
        swap(arr,l,lt);
        threeWays(arr,l,lt-1);
        threeWays(arr,gt,r);
    }

    private static void swap(Comparable[] arr,int a,int b){
        Comparable tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }


    public static void main(String[] args) {
        Integer[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        //shellSort(arr);
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
