package Practice;

import java.util.ArrayList;
import java.util.Scanner;

class TreeNode2 {
    char val;
    TreeNode2 left;
    TreeNode2 right;


    public TreeNode2(char val) {
        this.val = val;
    }
}

public class pra251030_2 {
    private static int index;// 全局索引用于构建二叉树
    private static ArrayList<Character> listLeaves = new ArrayList<>();
    private static ArrayList<Character> listLeavesFather = new ArrayList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        in.nextLine();
        while (t-- > 0) {
            String preorderStr = in.nextLine();
            index = 0;//重置索引
            listLeavesFather.clear();//清空上一个树叶子爸爸的缓存
            listLeaves.clear();//清空上一个树叶子的缓存

            //构建二叉树
            TreeNode2 root = buildTree(preorderStr);
            findLeaveAndFathers(root,'0');

            for (char c : listLeaves) {
                System.out.print(c + " ");
            }
            System.out.println();

            for (char c : listLeavesFather) {
                System.out.print(c + " ");
            }
            System.out.println();

        }
    }

    /*
     * 根据带空节点的先序遍历序列来构建二叉树
     * '#'表示空节点 , 遇到'#' 时返回null即可
     */
    private static TreeNode2 buildTree(String str) {//构建二叉树的方法
        if (index >= str.length() || str.charAt(index) == '0') {//判断是否越界和空节点
            index++;
            return null;
        }

        //创建当前节点
        TreeNode2 node = new TreeNode2(str.charAt(index));//第一个创建的是根节点
        index++;

        //不断递归构建左子树和右子树
        //递归顺序与先序遍历一致：根→左→右
        node.left = buildTree(str);
        node.right = buildTree(str);

        return node;
    }

    private static void findLeaveAndFathers(TreeNode2 node, char parentVal) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            listLeaves.add(node.val);
            listLeavesFather.add(parentVal);
        }

        //分别递归遍历左右树, 当前节点做为父亲
        findLeaveAndFathers(node.left, node.val);
        findLeaveAndFathers(node.right, node.val);

    }


//    //先序遍历 : 根 --> 左 --> 右
//    private static String preorder(TreeNode2 root) {
//        if (root == null) {
//            return "";
//        }
//        if (root.left == null && root.right == null) {
//            listLeaves.add(root.val);
//        }
//        return root.val + preorder(root.left) + preorder(root.right);
//    }


}


