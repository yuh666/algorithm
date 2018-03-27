package search.binary;

import java.util.LinkedList;

/**
 * @param <K>
 * @param <V> 二叉搜索树复习
 * @author yuh
 */
public class BSTReview<K extends Comparable<K>, V> {

    /**
     * 节点
     */
    private class Node {
        K key;
        V val;
        Node left;
        Node right;

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
     * 元素的数量
     */
    private int count;

    /**
     * 根节点
     */
    private Node root;

    /**
     * 是否为空
     * @return
     */
    public boolean isEmpty(){
        return count == 0;
    }


    /**
     * 插入一个节点
     * @param key
     * @param val
     */
    public void insert(K key,V val){
        root = insert(root,key,val);
    }

    /**
     * 递归找到合适的节点插入
     * @param node
     * @param key
     * @param val
     * @return
     */
    private Node insert(Node node,K key,V val){
        //插入只有两种情况 新增和替换
        if(node == null){
            count ++;
            return new Node(key,val);
        }
        if(node.key.compareTo(key) > 0){
            //走左面
            node.left = insert(node.left,key,val);
        }else if(node.key.compareTo(key) < 0){
            //走右面
            node.right = insert(node.right,key,val);
        }else{
            node.val =val;
        }
        //返回的是父节点 真正新增的情况是在node为空的情况下进行的
        return node;
    }

    /**
     * 查找元素
     * @param key
     * @return
     */
    public V search(K key){
        return search(root,key);
    }

    /**
     * 递归查找元素
     * @param node
     * @param key
     * @return
     */
    private V search(Node node,K key){
        if(node == null){
            return null;
        }

        if(node.key.compareTo(key) > 0){
            return search(node.left,key);
        }else if(node.key.compareTo(key) < 0){
            return search(node.right,key);
        }else{
            return node.val;
        }
    }


    /**
     * 包含元素
     * @param key
     * @return
     */
    public boolean contains(K key){
        return contains(root,key);
    }

    /**
     * 递归查找元素
     * @param node
     * @param key
     * @return
     */
    private boolean contains(Node node,K key){
        if(node == null){
            return false;
        }

        if(node.key.compareTo(key) > 0){
            return contains(node.left,key);
        }else if(node.key.compareTo(key) < 0){
            return contains(node.right,key);
        }else{
            return true;
        }
    }

    /**
     * 获取键最大的节点
     * @return
     */
    public Node getMax(){
        return getMax(root);
    }

    /**
     * 指定区域获取最大值
     * @param node
     * @return
     */
    private Node getMax(Node node){
        if(node.right == null){
           return node;
        }else{
            return getMax(node.right);
        }
    }

    /**
     * 获取键最小的节点
     * @return
     */
    public Node getMin(){
        return getMin(root);
    }

    /**
     * 指定区域获取最大值
     * @param node
     * @return
     */
    private Node getMin(Node node){
        if(node.left == null){
            return node;
        }else{
            return getMin(node.left);
        }
    }

    /**
     * 移除最小的键的元素
     */
    public void removeMin(){
        root = removeMin(root);
    }

    /**
     * 递归删除最小的节点
     * @param node
     * @return
     */
    private Node removeMin(Node node){
        if(node.left == null){
            //找到最小的节点
            Node right = node.right;
            node.right = null;
            count --;
            return right;
        }
        node.left = removeMin(node.left);
        return node;
    }


    /**
     * 移除最大的键的元素
     */
    public void removeMax(){
        root = removeMax(root);
    }

    /**
     * 递归删除最小的节点
     * @param node
     * @return
     */
    private Node removeMax(Node node){
        if(node.right == null){
            //找到最小的节点
            Node left = node.right;
            node.left = null;
            count --;
            return left;
        }
        node.right = removeMax(node.right);
        return node;
    }

    /**
     * 中序遍历
     */
    public void inOrder(){
        inOrder(root);
    }

    /**
     * 递归中序遍历
     * @param node
     */
    private void inOrder(Node node){
        inOrder(node.left);
        System.out.print(node.key+",");
        inOrder(node.right);
    }

    /**
     * 前序遍历
     */
    public void preOrder(){
        preOrder(root);
    }

    /**
     * 递归前序遍历
     * @param node
     */
    private void preOrder(Node node){
        System.out.print(node.key+",");
        preOrder(node.left);
        preOrder(node.right);
    }


    /**
     * 后序遍历
     */
    public void postOrder(){
        postOrder(root);
    }

    /**
     * 递归后序遍历
     * @param node
     */
    private void postOrder(Node node){
        postOrder(node.right);
        System.out.print(node.key+",");
        postOrder(node.left);
    }


    /**
     * 广度优先遍历
     */
    public void incrOrder(){
        LinkedList<Node> list = new LinkedList<>();
        list.addLast(root);
        while(!list.isEmpty()){
            Node node = list.removeFirst();
            if(node != null){
                System.out.print(node.key+",");
                if(node.left != null){
                    list.addLast(node.left);
                }
                if(node.right != null){
                    list.addLast(node.right);
                }
            }
        }
    }


    /**
     * 移除一个元素
     * @param key
     */
    public void remove(K key){
        root = remove(root,key);
    }

    /**
     * 递归移除一个元素
     * @param node
     * @param key
     * @return
     */
    private Node remove(Node node,K key){
        if(node == null){
            return null;
        }

        if(node.key.compareTo(key) > 0){
            node.left = remove(node.left,key);
        }else if(node.key.compareTo(key) < 0){
            node.right = remove(node.right,key);

        }else{
            //没有左子树的情况
            if(node.left == null){
                Node right = node.right;
                count --;
                node.right = null;
                return right;
            }

            //没有右子树的情况
            if(node.right == null){
                Node left = node.left;
                count --;
                node.left = null;
                return left;
            }

            //左右子树都存在的情况 取得右子树的最小值当做接任者
            Node newNode = new Node(getMin(node.right));
            count ++;
            //在原来的位置删除
            newNode.right = removeMin(newNode.right);
            newNode.left = node.left;
            node.left = node.right = null;
            count --;
            return newNode;
        }

        return node;
    }



















}
