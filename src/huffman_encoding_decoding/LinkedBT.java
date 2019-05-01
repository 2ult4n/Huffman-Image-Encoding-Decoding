package huffman_encoding_decoding;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Stack;
/* regular implementation of binary trees */
class BTNode implements Serializable {
    public int data;
    public int pixels;
    public BTNode left, right, parent;
 
    public BTNode(int data ,int pixels) {
        this.data = data;
        this.pixels=pixels;
        parent = left = right = null;
    }

	@Override
	public String toString() {
		return " [ pixels = " +pixels  + ", Frqeuncy =" + data+ " ] " ;
	}

}

public class LinkedBT implements Serializable {
    BTNode root, current;
   
    public LinkedBT() {
        root = current = null;
    }
 
    public boolean empty(){
        return root == null;
    }

    public boolean full() {
        return false;
    }
 
    public boolean find(Relative rel){
        switch (rel) {
           case Root:   // Easy case
            current = root;
            return true;
           case Parent:
            if(current == root)
            return false;
            current = current.parent;
            return true;
           case LeftChild:
            if(current.left == null)
                return false;
            current = current.left;
            return true;
           case RightChild:
            if(current.right == null)
                return false;
            current = current.right;
            return true;
           default:
            return false;
        }
    }

    public int retrieve() {
        return current.data;
    }
  
    public void update(int val) {
        current.data = val;
    }
   
    public void deleteSub() {
        if(current == root){
            current = root = null;
        }
        else {
            BTNode p = current;
            find(Relative.Parent);
            if(current.left == p)
                current.left = null;
            else
                current.right = null;
        }
 
       
    }
 
    public boolean isLeaf(BTNode currentNode) {
        if(currentNode == null)
            return false;
        if(currentNode.left == null && currentNode.right == null)
            return true;
        return false;
    }
    
    public int getheight(BTNode root) {
        if (null == root)
            return 0;
        int hLeftSub = getheight(root.left);
        int hRightSub = getheight(root.right);
        return Math.max(hLeftSub, hRightSub) + 1;

    }
 
    public BTNode getRoot() {
        return root;
    }
    
	public void printPreorder(BTNode node) { 
        
		if (node == null) 
            return; 
        System.out.print(node.data + " "); 
        printPreorder(node.left); 
        printPreorder(node.right); 
    }
	
 
}