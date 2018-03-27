package search.binary;

import java.util.LinkedList;

/**
 * @Author yuh
 * @Date Created in 上午7:20 2018/3/24
 * @Description 二分查找树的实现
 */
public class BST<K extends Comparable<K>, V> {

    /**
     * 节点内部类
     */
    private class Node {
        K key;
        V val;
        Node left;
        Node right;

        public Node(K key, V val, Node left, Node right) {
            this.key = key;
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }

        public Node(Node node) {
            this.key = node.key;
            this.val = node.val;
            this.left = node.left;
            this.right = node.right;
        }
    }


    /**
     * 元素数量
     */
    private int count;

    /**
     * 树根
     */
    private Node root;

    /**
     * 构造一课空树
     */
    public BST() {
        this.count = 0;
        this.root = null;
    }

    /**
     * 是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return count == 0;
    }


    /**
     * 插入一个节点
     *
     * @param key
     * @param val
     */
    public void insert(K key, V val) {
        root = insert(root, key, val);
    }

    /**
     * 递归插入一个节点
     *
     * @param node 这是父节点,如果替换的话就是自身节点 这个节点存在的意义就是创建新的节点之后 能和原来的节点建立关系
     * @param key
     * @param val
     * @return
     */
    private Node insert(Node node, K key, V val) {
        if (node == null) {
            count++;
            //如果父节点已经为空 则停止递归 返回一个新节点 接上父节点
            return new Node(key, val);
        }

        if (key.compareTo(node.key) == 0) {
            //相等就替换
            node.val = val;
        } else if (key.compareTo(node.key) < 0) {
            //小于就往左走 设父节点的左孩子
            node.left = insert(node.left, key, val);
        } else {
            node.right = insert(node.right, key, val);
        }

        return node;
    }

    /**
     * 是否包含一个节点
     *
     * @param key
     * @return
     */
    public boolean contains(K key) {
        return contains(root, key);
    }

    /**
     * 递归检查是否包含
     *
     * @param node
     * @param key
     * @return
     */
    private boolean contains(Node node, K key) {
        if (node == null) {
            return false;
        }

        if (key.compareTo(node.key) == 0) {
            return true;
        } else if (key.compareTo(node.key) < 0) {
            return contains(node.left, key);
        } else {
            return contains(node.right, key);
        }

    }


    /**
     * 根据键搜索一个值
     *
     * @param key
     * @return
     */
    public V search(K key) {
        return search(root, key);
    }

    /**
     * 递归检索
     *
     * @param node
     * @param key
     * @return
     */
    private V search(Node node, K key) {

        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) == 0) {
            return node.val;
        } else if (key.compareTo(node.key) < 0) {
            return search(node.left, key);
        } else {
            return search(node.right, key);
        }

    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        preOrder(root);
    }

    /**
     * 递归前序遍历
     *
     * @param node
     */
    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.key + ",");
        preOrder(node.left);
        preOrder(node.right);
    }


    /**
     * 中序遍历
     */
    public void inOrder() {
        inOrder(root);
    }

    /**
     * 递归前序遍历
     *
     * @param node
     */
    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        preOrder(node.left);
        System.out.print(node.key + ",");
        preOrder(node.right);
    }


    /**
     * 后序遍历
     */
    public void postOrder() {
        postOrder(root);
    }

    /**
     * 递归后序遍历
     *
     * @param node
     */
    private void postOrder(Node node) {
        if (node == null) {
            return;
        }
        preOrder(node.right);
        System.out.print(node.key + ",");
        preOrder(node.left);
    }

    /**
     * 广度优先遍历
     */
    public void incrOrder() {
        LinkedList<Node> list = new LinkedList<>();
        list.addLast(root);
        while (!list.isEmpty()) {
            Node node = list.removeFirst();
            if (node != null) {
                System.out.print(node.key + " -> " + node.val + ",");
                if (node.left != null) {
                    list.addLast(node.left);
                }
                if (node.right != null) {
                    list.addLast(node.right);
                }
            }
        }
    }

    /**
     * 寻找最小值
     *
     * @return
     */
    public Node minNode() {
        return minNode(root);
    }

    /**
     * 递归寻找最小值
     *
     * @return
     */
    private Node minNode(Node node) {
        if (node.left == null) {
            return node;
        } else {
            return node.left;
        }
    }

    /**
     * 寻找最大值
     *
     * @return
     */
    public Node maxNode() {
        return maxNode(root);
    }

    /**
     * 递归寻找最大值
     *
     * @return
     */
    private Node maxNode(Node node) {
        if (node.right == null) {
            return node;
        } else {
            return node.right;
        }
    }

    /**
     * 移除最大值
     */
    public void removeMax() {
        root = removeMax(root);
    }

    /**
     * 递归移除最大值
     *
     * @param node
     */
    private Node removeMax(Node node) {
        if (node.right == null) {
            //找到了最大值
            Node left = node.left;
            node.left = null;
            count--;
            return left;
        }
        node.right = removeMax(node.right);
        return node;
    }

    /**
     * 移除最小值
     */
    public void removeMin() {
        root = removeMin(root);
    }

    /**
     * 递归移除最小值
     *
     * @param node
     */
    private Node removeMin(Node node) {
        if (node.left == null) {
            //找到了最大值
            Node right = node.right;
            node.right = null;
            count--;
            return right;
        }
        node.left = removeMax(node.left);
        return node;
    }

    /**
     * 移除任意一个节点
     *
     * @param key
     */
    public void remove(K key) {
        root = remove(root, key);
    }

    /**
     * 递归删除任意一个节点
     *
     * @param node
     * @param key
     * @return
     */
    private Node remove(Node node, K key) {
        //没有了就返回空
        if (node == null) {
            //删除失败 元素不存在
            return null;
        }
        if (node.key.compareTo(key) > 0) {
            //往左子树
            node.left = remove(node.left, key);
        } else if (node.key.compareTo(key) < 0) {
            //往右子树
            node.right = remove(node.right, key);
        } else {
            //找到了
            //左子树不存在的情况
            if (node.left == null) {
                Node right = node.right;
                node.right = null;
                count--;
                return right;
            }
            //右子树不存在的情况
            if (node.right == null) {
                Node left = node.left;
                node.left = null;
                count--;
                return left;
            }

            //两面子树都存的情况 选择右子树的最小节点当做后继者
            //复制一个新的节点
            Node minNode = minNode(node.right);
            Node successor = new Node(minNode);
            count++;
            //删除接任者原来的位置
            successor.right = removeMin(node.right);
            successor.left = node.left;
            //删除原来的node的引用
            node.left = node.right = null;
            count--;
            return successor;
        }

        return node;
    }


    /**
     * 测试二分搜索树
     *
     * @param args
     */
    public static void main(String[] args) {

        int N = 100;

        // 创建一个数组，包含[0...N)的所有元素
        Integer[] arr = new Integer[N];
        for (int i = 0; i < N; i++) {
            arr[i] = new Integer(i);
        }

        // 打乱数组顺序
        for (int i = 0; i < N; i++) {
            int pos = (int) (Math.random() * (i + 1));
            Integer t = arr[pos];
            arr[pos] = arr[i];
            arr[i] = arr[pos];
        }
        // 由于我们实现的二分搜索树不是平衡二叉树，
        // 所以如果按照顺序插入一组数据，我们的二分搜索树会退化成为一个链表
        // 平衡二叉树的实现，我们在这个课程中没有涉及，
        // 有兴趣的同学可以查看资料自学诸如红黑树的实现
        // 以后有机会，我会在别的课程里向大家介绍平衡二叉树的实现的：）


        // 我们测试用的的二分搜索树的键类型为Integer，值类型为String
        // 键值的对应关系为每个整型对应代表这个整型的字符串
        BST<Integer, String> bst = new BST<Integer, String>();
        for (int i = 0; i < N; i++) {
            bst.insert(new Integer(i), Integer.toString(arr[i]));
        }

        //bst.inOrder();

        // 对[0...2*N)的所有整型测试在二分搜索树中查找
        // 若i在[0...N)之间，则能查找到整型所对应的字符串
        // 若i在[N...2*N)之间，则结果为null
        for (int i = 0; i < 2 * N; i++) {
            String res = bst.search(new Integer(i));
            if (i < N) {
                assert res.equals(Integer.toString(arr[i]));
            } else {
                assert res == null;
            }
        }
    }


}
