package Practice;

import java.util.Scanner;

class TreeNode {
    char val;
    TreeNode left;
    TreeNode right;

    public TreeNode(char val) {
        this.val = val;
    }
}

public class pra251030_BinaryTree {
    private static int index;// 全局索引用于构建二叉树

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        in.nextLine();
        while (t-- > 0) {
            String preorderStr = in.nextLine();
            index = 0;//重置索引

            //构建二叉树
            TreeNode root = buildTree(preorderStr);
            System.out.println(preorder(root));
            System.out.println(inorder(root));
            System.out.println(postorder(root));

        }
    }

    /*
       根据带空节点的先序遍历序列来构建二叉树
       '#'表示空节点 , 遇到'#' 时返回null即可


--------为什么先序遍历可以单独构建？??---------------------------------------
        先序遍历的顺序是：根 → 左 → 右
        第一个元素一定是根节点，结构清晰
        遇到'#'就知道该子树结束了

    */
    private static TreeNode buildTree(String str) {//构建二叉树的方法
        if (index >= str.length() || str.charAt(index) == '#') {//判断是否越界和空节点
            index++;
            return null;
        }

        //创建当前节点
        TreeNode node = new TreeNode(str.charAt(index));//第一个创建的是根节点
        index++;

        //递归构建左子树和右子树
        //递归顺序与先序遍历一致：根→左→右
        node.left = buildTree(str);
        node.right = buildTree(str);

        return node;
    }

    //先序遍历: 根 --> 左 --> 右
    private static String preorder(TreeNode root) {
        if (root == null) {
            return "";
        }
        return root.val + preorder(root.left) + preorder(root.right);
    }

    //中序遍历 : 左 --> 根 --> 右
    private static String inorder(TreeNode root) {
        if (root == null) {
            return "";
        }
        return inorder(root.left) + root.val + inorder(root.right);
    }

    // 后序遍历 :左 --> 根 --> 右
    private static String postorder(TreeNode root) {
        if (root == null) {
            return "";
        }
        return postorder(root.left) + postorder(root.right) + root.val;
    }

}


