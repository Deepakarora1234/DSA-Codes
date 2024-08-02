import java.util.*;
public class BinaryTrees2 { /*  traversal(tree par travel karna)....to traverse in a bnary tree..we have 4 approaches..preorder traversal, inorder traversal, postorder traversal, and  level order traversal(this is important)
                            preorder means root node at first, inorder means root node in between left and right subtree and postorder means root node after left and right subtre
                                 1
                                / \
                               2    3
                              / \     \
                             4   5      6

                         agar aisa binary tree diya h to preorder will be 1 2 4 5 3 6    hum chahe to jaha null node aayi thi vaha -1 likh sakte the 
                                                         inorder will be  4 2 5 1 3 6
                                                         postorder will be 4 5 2 6 3 1

                         we print data level wise....that is...1
                                                               2 3
                                                               4 5 6 ...these line breaks are important and we use null for these line breaks
                        for level order traversal we use iteration and for this traversal we will use Queues(we use queues to use their first in first out property)

                        pre in and post order traversal use DFS (depth frist search) property and level order traversl use BFS (breadth first search) property
                        this means DFS of a tree are all pre post and in order traversal and BFS of a tree is level order traversal
                        
                        level order traversal time complexity is also O(n) as har node pe queue m ek hi baar gye h
                        */
      static class Node 
      {
        int data;
        Node left; // this is left child node
        Node right; // this is right child node

        Node(int data) 
        {
            this.data = data;
            this.left = null; // initially left and right child are both null
            this.right = null;
            // ye ban gai Node class jo tree ki har ek node ko represent karegi
        }
    }
    static class BinaryTree 
    {
        static int  idx = -1; // we use this static variable idx to to treach different nodes in a tree
        public static Node buildTree(int nodes[]) { // ye function hamari saari nodes ko lega as input..yaani poora array lega nodes vaala and return us the root node
                idx++;  // function ko calla lagte hi idx update ..0th index pe aaye yaani sab se phle root ko create karenge

                if(nodes[idx] == -1) 
                {
                    return null; // -1 yaani us jagah null h koi node nai h 
                }

                Node newNode = new Node(nodes[idx]); // nai node banai ..data vo hoga jo us idx pe nodes array ka elemrnt hoga
               //ab nai node ka left subtree create karenge

                newNode.left = buildTree(nodes); // ye call new node ke left child ko storte karaegi..fir se recursive call lagegi..aisa karte karte saari left child store ho jaenge aur -1 aate hi null aajega fir right node store hoegi agle step se 
                newNode.right = buildTree(nodes);

                return newNode;

        }
    }     
    //preorder ...recursive function banaenge   preOrder technique m O(n) lafta h kyu ki ek ek karke har ek node pe ek baar jaate h

    public static void preOrder(Node root) 
    {
        if(root == null) 
        {
            System.out.print("-1 "); // ye step optional h
            return;
        }

        System.out.print(root.data + " "); // tree ka root print ho gya..ab preorder h to is root ka left subtree chahiye
        preOrder(root.left); // yaha se root ka left subtree 

        preOrder(root.right); // yaha se root ka rght subtree
        

    }

    //inOrder...O(n) time complexity
    public static void inOrder(Node root) 
    {
        if(root == null) 
        {
            return;
        }
        inOrder(root.left); // sab se phle left subtree ke loiye call kar diya
        System.out.print(root.data + " ");//fir root ki value print kar di 
        inOrder(root.right); // fir right subtree ke loiye call kar diuya
    }

    //postOrder  O(n) time complexity
    public static void postOrder(Node root) 
    {
        if(root==null) 
        {
            return;
        }

        postOrder(root.left); // left subtree ke liye call
        postOrder(root.right); // right subtree ke loye call
        System.err.print(root.data + " "); // root ko print kara diya
    }

    //level order traversal
    public static void levelOrder(Node root) 
    {
        if(root==null) 
        {
            return;
        }
        Queue<Node> q = new LinkedList<>(); // queue banai linkedList ko use kar ke
      
        q.add(root);
        q.add(null);
    
        while(!q.isEmpty()) 
        {
            Node currNode = q.remove();
            if (currNode==null) {
                System.out.println();
                if(q.isEmpty() ) 
                {
                    break;
                }
                else
                {
                    q.add(null);
                   
                }
            }
            else {
                System.out.print(currNode.data +" ");
                
                if(currNode.left != null) 
                {
                    q.add(currNode.left);
                   
                    
                }
                
                if(currNode.right!=null) 
                {
                    q.add(currNode.right);
                    
                }
            }

        }
       
    }

    public static void main(String args[]) 
    {
        int nodes[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};  

        BinaryTree tree = new BinaryTree();
        Node root = tree.buildTree(nodes); 
        // preOrder(root);
        // System.out.println();
        // inOrder(root);
        // System.out.println();
        // postOrder(root);
        // System.out.println();
        levelOrder(root);
       
    }
}




