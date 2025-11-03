package Practice;

import java.util.Scanner;

class TreeNode5 {
    int val;
    TreeNode5 left;
    TreeNode5 right;

    public TreeNode5(int val) {
        this.val = val;
    }
}

public class pra251030_5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt(); // 读取二叉树个数

        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt(); // 数组长度
            int[] arr = new int[n];

            // 读取数组数据
            for (int j = 0; j < n; j++) {
                arr[j] = scanner.nextInt();
            }

            // 构建二叉树
            TreeNode5 root = buildTreeFromArray(arr, 0);

            // 先序遍历并输出结果
            StringBuilder result = new StringBuilder();
            preorderTraversal(root, result);
            System.out.println(result.toString());
        }

        scanner.close();
    }

    /**
     * 根据数组构建二叉树
     * @param arr 数组表示的二叉树
     * @param index 当前节点在数组中的索引
     * @return 构建的二叉树节点
     */
    private static TreeNode5 buildTreeFromArray(int[] arr, int index) {
        // 边界条件：索引越界或节点值为0（表示空节点）
        if (index >= arr.length || arr[index] == 0) {
            return null;
        }

        // 创建当前节点
        TreeNode5 node = new TreeNode5(arr[index]);

        // 递归构建左子树和右子树
        // 左子节点索引：2*index + 1
        node.left = buildTreeFromArray(arr, 2 * index + 1);
        // 右子节点索引：2*index + 2
        node.right = buildTreeFromArray(arr, 2 * index + 2);

        return node;
    }

    /**
     * 先序遍历二叉树
     * @param root 二叉树根节点
     * @param result 用于存储遍历结果的StringBuilder
     */
    private static void preorderTraversal(TreeNode5 root, StringBuilder result) {
        if (root == null) {
            return;
        }

        // 访问根节点
        result.append(root.val).append(" ");

        // 遍历左子树
        preorderTraversal(root.left, result);

        // 遍历右子树
        preorderTraversal(root.right, result);
    }
}