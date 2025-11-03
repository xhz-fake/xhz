
package Practice;

import java.util.ArrayList;
import java.util.Scanner;

class TreeNode4 {
    char val;
    TreeNode4 left;
    TreeNode4 right;

    public TreeNode4(char val) {
        this.val = val;
    }
}

public class pra251030_4 {
    private static int index;// 全局索引用于构建二叉树
    private static ArrayList<Character> listLeaves = new ArrayList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        in.nextLine();
        while (t-- > 0) {
            String preorderStr = in.nextLine();
            index = 0;//重置索引
            listLeaves.clear();//清空上一个树叶子的缓存

            //构建二叉树
            TreeNode4 root = buildTree(preorderStr);

            int height = getTreeHeight(root);
            System.out.println(height);
        }
    }

    /*
     * 根据带空节点的先序遍历序列来构建二叉树
     * '#'表示空节点 , 遇到'#' 时返回null即可
     */
    private static TreeNode4 buildTree(String str) {//构建二叉树的方法
        if (index >= str.length() || str.charAt(index) == '0') {//判断是否越界和空节点
            index++;
            return null;
        }

        //创建当前节点
        TreeNode4 node = new TreeNode4(str.charAt(index));//第一个创建的是根节点
        index++;

        //不断递归构建左子树和右子树
        //递归顺序与先序遍历一致：根→左→右
        node.left = buildTree(str);
        node.right = buildTree(str);

        return node;
    }


    /**
     * 计算二叉树的高度（深度）
     * 递归方法：树的高度 = 1 + max(左子树高度, 右子树高度)
     * 空树高度为0
     */
    private static int getTreeHeight(TreeNode4 root) {
        if (root == null) {
            return 0;
        }

        //递归计算左右树的最大高度
        int leftHeight = getTreeHeight(root.left);
        int rightHeight = getTreeHeight(root.right);

        return Math.max(rightHeight, leftHeight) + 1; // 当前树的高度

        /*
        递归分解：
        将大问题分解为小问题：求整棵树的高度 → 求左右子树的高度
        每个节点只关心自己的高度，由子节点的高度决定
        */
    }

//    //先序遍历 : 根 --> 左 --> 右
//    private static String preorder(TreeNode4 root) {
//        if (root == null) {
//            return "";
//        }
//        if (root.left == null && root.right == null) {
//            listLeaves.add(root.val);
//        }
//        return root.val + preorder(root.left) + preorder(root.right);
//    }


}


