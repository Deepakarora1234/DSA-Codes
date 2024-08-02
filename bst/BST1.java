import java.util.*;
public class BST1 { /*  BST=binary search tree
                    a) left subtree nodes always smaller than root node\
                    b)rightsubtree node always larger than root node
                    left and right subtree are also BST with no duplicates
                    special property...inorder traversal of bst gives sorted sequence
                    time complexity of search in bst is O(H) .. H is height of tree
                    most problems of BST will be solved using recurison ..dividing into subproblems and making recurive calls on subtrees
                    */

        //building a BST  ..when we build a binary tree ..we are either given a preorder traversal and we take the complete array and our buildtree function returns us the root node
        
        
        static class Node 
        {
            int data;
            Node left;
            Node right;
            Node(int data) 
            {
                this.data = data;
                this.left = null;
                this.right = null;
        
            }

        }
        public static Node insert(Node root, int val) {   //ek node ko insert karne ki time complexity O(n) hi aati h
            if(root==null) 
            {
                root = new Node(val);
                return root;

            }
            if(root.data > val)
            {
                //in this case we have to go tp left subtree
                root.left = insert(root.left, val);

      }
      else 
      {
        root.right = insert(root.right, val);
      }
      return root;
        
    }
    public static void inOrder(Node root) 
    {
        if(root==null) 
        {
            return;
        }
        inOrder(root.left);
        System.out.print(root.data+" ");
        
        inOrder(root.right);
    }

    //search in BST  search in BST O(n)
    public static boolean search(Node root, int key) 
    {
        if(root==null) 
        {
            return false;
        }

        if(root.data >key) { // left subtree
            return search(root.left, key); // left subtree m call laga li agar key mil gya to true return ho jaega
        }
        else if(root.data==key) 
        {
            return true;
        }
        else  { // right subtree
            return search(root.right, key);
        }
    }

    //delete a node...3 cases ..1. no child(leaf node)..we simply replace the node to be deleted by null
                               //2.one child .. we replace the node to be deleted by its child node...garbage collector takes away the deleted node
                               //3.two children .. we replace the node to be deleted by its inorder successor and then deletd the inorder successor node..
                                //     8
                                 //   /  \
                                 //  5    10
                    //              / \     \
                    //             3   6     11
                    //            / \          \
                    //           1   4          14
                    //in the above BST if we have to delete node 5.....then we first find its inorder successor(in this case 6) and put 6in place of 5...and then delete the initial node 6 to remove 6...and this in order successor node will always fall in either of the above two satated cases
                    // that means jab 6 vaali node ko delete karenge to yaa to vo leaf node hogi yaa node with one child hogi
                    
            //IMPORTANT  inorder successor of any node is the leftmost node in the right subtree

     public static Node delete(Node root, int val) { // return type node as har level pe node return hogi
        if(root.data > val) 
        {
            root.left = delete(root.left, val);
            
        }
        else if(root.data < val) 
        {
            root.right = delete(root.right, val);
        } //yaha tak to search vaala part tha

        else { // yaani root.data == val
            //case 1
            if(root.left==null && root.right == null) 
            {
                return null; // null return kiya means node delete kar di...aur root ki value lsot ho gai

            }
            //case 2
            if(root.left == null) 
            {
                return root.right; // agar root.left null h yaani sirf right child h us node ka jo delete karni h...to hum uska right child return kar denge uske parent ko aur root node delete ho jaegi
            }
            else if(root.right == null) 
            {
                return root.left;
            }

            //case 3....iske liye sabse phle inordeer successor ko dhoondna parega...uske liye alag se function banaya
            Node IS = inOrderSuccessor(root.right); //humne root.right daala as parameter kyuki hume right subtree m se left most nofr chahiye jo ki hume inOrderSuccessor ne laake di...us node(in order successor ) koIS naam ki node m store kara liya
            root.data = IS.data; // is step m jo node delete karni h uski jgah uska inorder successor daal diya
            root.right = delete(root.right, IS.data); // is step m duplicate htaya yaani jo in order successor tha usko hataya ...vo in orde successor ka 0 ya 1 child hi ho sakta h...yani 1st ya 2nd case m lie kar jaega to easily delete function use kar sakte h

           }
        return root;
     }
     public static Node inOrderSuccessor(Node root) { // ye jo root [ass ki h vo kabhi null nai ho sakti as humne is function ke parameter m upar pass kiya h root.right  aur ye null nai h kyu ki agar null hota to upar vaale do if cases m aajata..to hume root == null vaala base case yaha banane ki jaroorat nai h
        while(root.left !=null) 
        {
            root = root.left; // hume left most node chahiye hoti h

        }
        return root;

     }




        
    public static void main(String args[]) 
    {
        int values[] = {0,-1};
        Node root = null; 
        for(int i = 0; i <values.length; i++) 
        {
            root = insert(root,values[i]);
           
        }
        inOrder(root);
      
     
        
       
        
        
         //to check whether our bst is correct we try to get the inorder traversal of it..if we get a sorted sequence..that means our bst is correct..

        //  inOrder(root);
        //  System.out.println();

        //  if(search(root, 6)) {
        //     System.out.println("found");

        //  }
        //  else {
        //     System.out.println("not found");
        //  }

        // delete(root,5);
        // inOrder(root);

         

        
    }
}

