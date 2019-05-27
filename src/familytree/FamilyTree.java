/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package familytree;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author zhuang001
 */
public class FamilyTree {

    /**
     * @param args the command line arguments
     */
    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {
        // TODO code application logic here
        
        for (int i=0;i<10;i++) {
            doTestCase();
        }
    }

    private static void doTestCase() {
        TreeNode root=new TreeNode(0);
        int n=sc.nextInt();
        sc.nextLine();
        for (int i=0;i<n;i++) {
            String sNode=sc.nextLine();
            sNode=sNode.substring(sNode.indexOf(".")+1);
            root.addSNode(sNode);
        }
        System.out.println(root.size()%1000000007L);
    }
}

class TreeNode {
    int code;
    TreeNode parent=null;
    ArrayList<TreeNode> children=new ArrayList();
    
    public TreeNode(int n) {
        this.code=n;
    }
    
    public TreeNode addSNode(String sNode) {
        String[] split=sNode.split("\\.");
        TreeNode current=this;
        
        for (String s:split) {
            int code=Integer.parseInt(s);
            current=current.add(code);
        }
        return current;
    }
    
    public TreeNode add(int c) {
        TreeNode node=this.findChild(c);
        if (node!=null) return node;
        node=new TreeNode(c);
        this.children.add(node);
        node.parent=this;
        return node;
    }
    
    public TreeNode findChild(int code) {
        
        for (TreeNode child:this.children) {
            if (child.code==code)
                return child;
        }
        return null;
    }
    
    public long size() {
        long size=0;
        long maxChildCode=0;
        for (TreeNode child:this.children) {
            if (child.code>maxChildCode) 
                maxChildCode=child.code;
        }
        size=maxChildCode+1;
        for (TreeNode child:this.children) {
            if (!child.children.isEmpty()) {
                size+=child.size()-1;
            }
        }
        return size;
    }
    
}