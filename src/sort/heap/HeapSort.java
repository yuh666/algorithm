package sort.heap;

import sort.utils.SortTestHelper;

import java.util.Arrays;

/**
 * @Author yuh
 * @Date Created in 下午7:01 2018/3/20
 * @Description
 */
public class HeapSort {


    /**
     * 原地堆排序
     * @param arr
     */
    public static void sort(Comparable[] arr){
        for (int i = (arr.length-1)/2; i >= 0 ; i--) {
            shiftDown(arr,i,arr.length-1);
        }
        //System.out.println(Arrays.toString(arr));
        for (int i = arr.length-1; i > 0 ; i--) {
            SortTestHelper.swap(arr,i,0);
            shiftDown(arr,0,i-1);
        }
    }

    private static void shiftDown(Comparable[] arr, int k, int n) {
        while (2 * k + 1 <= n) {
            int j = 2 * k + 1;
            if (j + 1 <= n && arr[j].compareTo(arr[j + 1]) < 0) {
                j++;
            }
            if (arr[j].compareTo(arr[k]) < 0) {
                break;
            }
            SortTestHelper.swap(arr, k, j);
            k = j;
        }
    }


    public static void sort2(Comparable[] arr) {
        PriorityQueue<Comparable> queue = new PriorityQueue<Comparable>(10000);
        for (Comparable comparable : arr) {
            queue.offer(comparable);
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            arr[i] = queue.take();
        }
    }

    public static void sort1(Comparable[] arr) {
        PriorityQueue<Comparable> queue = new PriorityQueue<Comparable>(arr);
        for (int i = arr.length - 1; i >= 0; i--) {
            arr[i] = queue.take();
        }
    }

    public static void main(String[] args) {
        SortTestHelper.getSortTime("sort.heap.HeapSort", SortTestHelper.generateIntegerArray(50000, 1, 50000));
    }

}
