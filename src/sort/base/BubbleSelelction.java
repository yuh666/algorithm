package sort.base;

import sort.utils.SortTestHelper;

/**
 * @Author yuh
 * @Date Created in 下午3:10 2018/3/19
 * @Description 选择排序
 */
public class BubbleSelelction {

    /**
     * 排序方法
     *
     * @param arr
     */
    public static <T extends Comparable<T>> void sort(T[] arr) {
        boolean swap = false;
        for (int i = 0; i < arr.length - 1; i++) {
            swap = false;
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                     SortTestHelper.swap(arr, j, j + 1);
                    swap = true;
                }
            }
            if (!swap) {
                break;
            }
        }
    }


    public static void main(String[] args) {
        SortTestHelper.getSortTime("sort.base.BubbleSelelction", SortTestHelper.generateIntegerArray(10000, 1, 10000));
    }
}
