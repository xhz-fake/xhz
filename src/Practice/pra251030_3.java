package Practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class TreeNode3 {
    char val;
    TreeNode3 left;
    TreeNode3 right;


    public TreeNode3(char val) {
        this.val = val;
    }
}

public class pra251030_3 {
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
            TreeNode3 root = buildTree(preorderStr);
            String levelOrder = levelOrderTraversal(root);
            System.out.println(levelOrder);
        }
    }

    /*
     * 根据带空节点的先序遍历序列来构建二叉树
     * '#'表示空节点 , 遇到'#' 时返回null即可
     */
    private static TreeNode3 buildTree(String str) {//构建二叉树的方法
        if (index >= str.length() || str.charAt(index) == '0') {//判断是否越界和空节点
            index++;
            return null;
        }

        //创建当前节点
        TreeNode3 node = new TreeNode3(str.charAt(index));//第一个创建的是根节点
        index++;

        //不断递归构建左子树和右子树
        //递归顺序与先序遍历一致：根→左→右
        node.left = buildTree(str);
        node.right = buildTree(str);

        return node;
    }

    private static String levelOrderTraversal(TreeNode3 root) {
        if (root == null) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        Queue<TreeNode3> queue =new LinkedList<>();

        //根节点入队
        queue.offer(root);

        while(!queue.isEmpty()){
            //队首元素出队
            TreeNode3 current = queue.poll();

            //访问(不断增加字符串链)前节点
            result.append(current.val);

            //左子节点入队,(如果存在)
            if(current.left != null){
                queue.offer(current.left);
            }

            //右子节点入队,(如果存在)
            if(current.right != null){
                queue.offer(current.right);//用offer 队列满了时会返回false而不会抛出错误
            }

        }
        return result.toString();
    }

//    //先序遍历 : 根 --> 左 --> 右
//    private static String preorder(TreeNode3 root) {
//        if (root == null) {
//            return "";
//        }
//        if (root.left == null && root.right == null) {
//            listLeaves.add(root.val);
//        }
//        return root.val + preorder(root.left) + preorder(root.right);
//    }


}


