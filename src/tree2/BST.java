package tree2;

import java.util.LinkedList;

/**
 * 二分搜索树
 */
public class BST<K extends Comparable<K>, V> {

    /**
     * 节点内部类
     */
    class Node {
        K key;
        V val;
        Node left;
        Node right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }

        public Node(K key, V val, Node left, Node right) {
            this.key = key;
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public Node(Node anotherNode) {
            this.key = anotherNode.key;
            this.val = anotherNode.val;
            this.left = anotherNode.left;
            this.right = anotherNode.right;
        }


    }

    /**
     * 元素的数量
     */
    private int count;
    /**
     * 根节点
     */
    private Node root;

    public BST() {
        count = 0;
        root = null;
    }

    /**
     * 插入一个元素
     *
     * @param key
     * @param val
     */
    public void insert(K key, V val) {
        root = insert(root, key, val);
    }

    /**
     * 插入元素
     *
     * @param node
     * @param key
     * @param val
     * @return
     */
    private Node insert(Node node, K key, V val) {
        if (node == null) {
            count++;
            return new Node(key, val);
        }

        if (key.compareTo(node.key) > 0) {
            node.right = insert(node.right, key, val);
        } else if (key.compareTo(node.key) < 0) {
            node.left = insert(node.left, key, val);
        } else {
            node.val = val;
        }
        return node;
    }

    /**
     * 是否包含key
     *
     * @param key
     * @return
     */
    public boolean contains(K key) {
        return contains(root, key);
    }

    /**
     * 递归差查找是否包含key
     *
     * @param node
     * @param key
     * @return
     */
    private boolean contains(Node node, K key) {
        if (node == null) {
            return false;
        }
        if (key.compareTo(node.key) > 0) {
            return contains(node.right, key);
        } else if (key.compareTo(node.key) < 0) {
            return contains(node.left, key);
        } else {
            return true;
        }
    }

    /**
     * 根据key查找一个val
     *
     * @param key
     * @return
     */
    public V search(K key) {
        return search(root, key);
    }

    /**
     * 递归根据key查找一个val
     *
     * @param node
     * @param key
     * @return
     */
    private V search(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) > 0) {
            return search(node.right, key);
        } else if (key.compareTo(node.key) < 0) {
            return search(node.left, key);
        } else {
            return node.val;
        }
    }

    /**
     * 深度优先遍历 前序遍历
     */
    public void preOrder() {
        preOrder(root);
    }

    /**
     * 深度优先遍历 前序遍历
     *
     * @param node
     */
    private void preOrder(Node node) {
        if(node == null){
            return;
        }
        System.out.print(node.key + ",");
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 深度优先遍历 中序遍历
     */
    public void inOrder() {
        inOrder(root);
    }

    /**
     * 深度优先遍历 中序遍历
     *
     * @param node
     */
    private void inOrder(Node node) {
        if(node == null){
            return;
        }
        inOrder(node.left);
        System.out.print(node.key + ",");
        inOrder(node.right);
    }

    /**
     * 深度优先遍历 后序遍历
     */
    public void postOrder() {
        postOrder(root);
    }

    /**
     * 深度优先遍历 后序遍历
     *
     * @param node
     */
    private void postOrder(Node node) {
        if(node == null){
            return;
        }
        preOrder(node.left);
        preOrder(node.right);
        System.out.print(node.key + ",");
    }

    /**
     * 广度优先遍历
     */
    public void widthOrder() {
        LinkedList<Node> nodes = new LinkedList<>();
        if (root != null) {
            nodes.addLast(root);
            while (!nodes.isEmpty()) {
                Node node = nodes.removeFirst();
                System.out.print(node.key + ",");
                if (node != null) {
                    if (node.left != null) {
                        nodes.addLast(node.left);
                    }
                    if (node.right != null) {
                        nodes.addLast(node.right);
                    }
                }
            }
        }
    }

    /**
     * 移除以node为根的最小值
     *
     * @param node
     */
    public void removeMin(Node node) {
        node = removeMinAux(node);
    }

    /**
     * 递归移除以node为根的最小值
     *
     * @param node
     */
    private Node removeMinAux(Node node) {
        if (node.left == null) {
            //最小值已经找到
            Node right = node.right;
            node.right = null;
            count--;
            return right;
        }
        node.left = removeMinAux(node.left);
        return node;
    }

    /**
     * 获取以node为根的最小值
     *
     * @param node
     */
    public Node getMin(Node node) {
        if (node.left == null) {
            return node;
        }
        return getMin(node.left);
    }

    /**
     * 获取以node为根的最大值
     *
     * @param node
     */
    public Node getMax(Node node) {
        if (node.right == null) {
            return node;
        }
        return getMax(node.right);
    }

    /**
     * 移除以node为根的最大值
     *
     * @param node
     */
    public void removeMax(Node node) {
        node = removeMaxAux(node);
    }

    /**
     * 递归移除以node为根的最小值
     *
     * @param node
     */
    private Node removeMaxAux(Node node) {
        if (node.right == null) {
            //最大值已经找到
            Node left = node.left;
            node.left = null;
            count--;
            return left;
        }
        node.right = removeMinAux(node.right);
        return node;
    }

    /**
     * 移除以node为根的key
     *
     * @param node
     * @param key
     */
    public void remove(Node node, K key) {
        node = removeAux(node, key);
    }

    /**
     * 递归移除以node为根的key
     *
     * @param node
     * @param key
     * @return
     */
    private Node removeAux(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            node.left = removeAux(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            node.right = removeAux(node.right, key);
        } else {
            //命中了
            if (node.right == null) {
                //最小值已经找到
                Node right = node.right;
                node.right = null;
                count--;
                return right;
            } else if (node.right == null) {
                //最大值已经找到
                Node left = node.left;
                node.left = null;
                count--;
                return left;
            } else {
                //都不为空 返回右面最小的那个
                Node min = getMin(node);
                Node successor = new Node(min);
                successor.right = removeMaxAux(min);
                successor.left = node.left;
                node.left = node.right = null;
                return successor;
            }
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

        BST<Integer, String> bst = new BST<Integer, String>();
        for (int i = 0; i < N; i++) {
            bst.insert(arr[i], Integer.toString(arr[i]));
        }

        /*for (int i = 0; i < 2 * N; i++) {
            String res = bst.search(new Integer(i));
            if (i < N) {
                assert res.equals(Integer.toString(arr[i]));
            } else {
                assert res == null;
            }
        }*/


        bst.inOrder();
    }

}
