package sort.utils;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Author yuh
 * @Date Created in 下午2:34 2018/3/19
 * @Description 测试算法辅助类
 */
public class SortTestHelper {

    /**
     * 生成指定长度的数组 []
     *
     * @param length 长度
     * @param rangeL 最小值
     * @param rangeR 最大值
     * @return
     */
    public static Integer[] generateIntegerArray(int length, int rangeL, int rangeR) {
        Integer[] arr = new Integer[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * (rangeR - rangeL + 1) + rangeL);
        }
        return arr;
    }

    /**
     * 检测一个数组是否有序
     *
     * @param arr
     * @return
     */
    public static boolean isSorted(Comparable[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].compareTo(arr[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 测试一次排序所需要的时间
     *
     * @param sortClassName
     * @param arr
     */
    public static void getSortTime(String sortClassName, Comparable[] arr) {
        try {
            Class<?> aClass = Class.forName(sortClassName);
            Method sort = aClass.getMethod("sort", new Class[]{Comparable[].class});
            System.out.println(Arrays.toString(arr));
            long start = System.currentTimeMillis();
            sort.invoke(null, new Object[]{arr});
            long end = System.currentTimeMillis();
            System.out.println(aClass.getSimpleName() + " -> " + arr.length + " -> " + (end - start));
            assert isSorted(arr);
            System.out.println(Arrays.toString(arr));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 交换数组中的位置
     *
     * @param arr
     * @param a
     * @param b
     */
    public static void swap(Comparable[] arr, int a, int b) {
        Comparable tmp = null;
        tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }


    public static void main(String[] args) {
        Integer[] intArr = new Integer[]{1, 2};
        Double[] doubleArr = new Double[]{1D, 2D};
        System.out.println(intArr.getClass());
        System.out.println(doubleArr.getClass());
        assert 1 == 2;
    }


}
