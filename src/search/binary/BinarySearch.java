package search.binary;

/**
 * @Author yuh
 * @Date Created in 下午9:04 2018/3/23
 * @Description 二分查找的while版本和递归版本
 */
public class BinarySearch {

    /**
     * 普通版本入口
     *
     * @param arr
     * @param target
     * @param <T>
     * @return
     */
    public static <T extends Comparable> int search1(T[] arr, T target) {
        int l = 0, r = arr.length - 1, mid;
        while (l <= r) {
            mid = l + (r - l) / 2;
            if (arr[mid].compareTo(target) == 0) {
                return mid;
            } else if (arr[mid].compareTo(target) < 0) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 递归版本入口
     *
     * @param arr
     * @param target
     * @param <T>
     * @return
     */
    public static <T extends Comparable> int search2(T[] arr, T target) {
        return _search2(arr, target, 0, arr.length - 1);
    }

    /**
     * 递归函数
     *
     * @param arr
     * @param target
     * @param l
     * @param r
     * @param <T>
     * @return
     */
    private static <T extends Comparable> int _search2(T[] arr, T target, int l, int r) {
        if (l > r) {
            return -1;
        }
        int mid = l + (r - l) / 2;
        if (arr[mid].compareTo(target) == 0) {
            return mid;
        } else if (arr[mid].compareTo(target) < 0) {
            return _search2(arr, target, mid + 1, r);
        } else {
            return _search2(arr, target, l, mid - 1);
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4, 5, 6};
        System.out.println(search2(arr, 3));
    }
}
