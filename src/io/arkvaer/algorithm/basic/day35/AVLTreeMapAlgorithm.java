package io.arkvaer.algorithm.basic.day35;


import java.util.Objects;

public class AVLTreeMapAlgorithm {

    public static class AVLNode<K extends Comparable<K>, V> {
        public K key;
        public V value;
        public AVLNode<K, V> left;
        public AVLNode<K, V> right;
        public int height;
    }

    public static class AVLTreeMap<K extends Comparable<K>, V> {
        private AVLNode<K, V> root;
        private int size;

        public AVLTreeMap() {
        }


        /**
         * 左旋
         *
         * @param cur 当前要进行左旋的节点
         * @return 左旋后的头节点
         */
        private AVLNode<K, V> leftRotate(AVLNode<K, V> cur) {
            AVLNode<K, V> right = cur.right;
            cur.right = right.left;
            right.left = cur;
            cur.height = Math.max((cur.left != null ? cur.left.height : 0), (cur.right != null ? cur.right.height : 0));
            right.height = Math.max(right.left.height, (right.right != null ? right.right.height : 0));
            return right;
        }

        /**
         * 右旋
         *
         * @param cur 当前要进行右旋的节点
         * @return 右旋后的头节点
         */
        private AVLNode<K, V> rightRotate(AVLNode<K, V> cur) {
            AVLNode<K, V> left = cur.left;
            cur.left = left.right;
            left.right = cur;
            cur.height = Math.max((cur.left != null ? cur.left.height : 0), (cur.right != null ? cur.right.height : 0)) + 1;
            left.height = Math.max((left.left != null ? left.left.height : 0), left.right.height) + 1;
            return left;
        }

        public AVLNode<K, V> maintain() {}
    }

}
