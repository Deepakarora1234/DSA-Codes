import java.util.*;

public class BinaryTrees3 
{
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

   /*  question to calculate count of nodes
    1st approach: ...my approach

     static int idx = 0; 
     public static int countNodes(Node root) {
        
         if(root == null) {
           return 0;
         }
         idx++;
         countNodes(root.left);
         countNodes(root.right);
        return idx;
        */

     

     //2nd approach:  time complexity O(n)

    public static int countNodes(Node root) 
    {
        if(root == null) 
        {
            return 0;
        }
        int leftNodes = countNodes(root.left); // left subtree ki nodes calculate karne ke liye recursive call
        int rightNodes = countNodes(root.right); // right subtree ki nodes calculate karne ke liye recursive call
        return leftNodes + rightNodes + 1; // plus 1 isiliye kiy kyu ki root node ka count bhi add kar diya
    }

    /*question 2..sum of nodes

    1st approach my approach
      static int sum = 0;
     public static int sumNodes(Node root) {
         if(root == null) {
           return 0;
         }
       
         sumNodes(root.left);
        
         sumNodes(root.right);
         sum+=root.data;
         return sum;
     }
     */

    /*2nd approach   O(n)
     public static int sumNodes(Node root) {
         if(root==null) {
             return 0;
         }
         int leftSum = sumNodes(root.left);
         int rightSum = sumNodes(root.right);
         return leftSum+rightSum+root.data;
     }
     */

    /*question3 ..height of tree

    1st approach   my approach
         static int height = 0;
     public static int heightNodes(Node root) {
         if(root==null) {

             return 0;
         }
        

         height++;
         if(root.left==null) {
             return  heightNodes(root.right);
         }
         else{
             heightNodes(root.left);
             return height;
          
         }
        
      }
      */

    /*  2nd aproach O(n)
     public static int heightNodes(Node root) {
         if(root == null) {
             return 0;
         }
         int leftHeight = heightNodes(root.left);
         int rightHeight = heightNodes(root.right);
         int myHeight  = Math.max(leftHeight, rightHeight) + 1; // important...left subtree aur right subtree dono ki hieght nikal ke dekh liya jiski jaada h usme 1 add kar diya(initial root vaale ki height) ..ye ho jaegi poore tree ki hieght.

         return myHeight;
     }
     */

    /*question4...diameter of a tree(diameter of a tree means no. of nodes in the longest path between any 2 nodes)
    agar do nodes li aur unke beech m sab se jaada nodes ho to is path ko tree ka diameter bolenge..
    2 approaches to slove this ques...

    1st approach..O(n^2) time.....isme 3 cases h..1. ya to diamtere left subtree m lie kare..2. ya right subtree m loe kare..ya fir dono subtrees ki max height nikal ke
     root vaala 1 add kare to vo diamtere hoga jo root se cross hoga

     public static int diameter(Node root) { //phle ye calculate karna ha ki left and right subtree se max diameter kya laa sakte h

         if(root == null) {
             return 0;
         }

         int leftDia = diameter(root.left);
         int rightDia = diameter(root.right);
         //ab vo case ke liye dekhenge jisme diamtere root node se pass hoti h.us m left and right subtree ki height add kar ke root vaali plus 1 karenge to aajaegi
         int dia3 = heightNodes(root.left) + heightNodes(root.right) +1;
         return Math.max(dia3, Math.max(rightDia, leftDia));

         //O(n^2) aise aai ki har node pe phle ek baar gye to linear aai time complexity lkn fir height calculate karne ke loiye dobara har ek node pe gye 
         to additiona n time aur aagya ..hence O(n^2)

     }

     //2nd approach...time complexity linear i.e. O(n) isme hum height aur diameter ek saath calculate karenge taaki time linear lage
     static class TreeInfo {
         int ht;
         int diam; // ek class bana li usme har node ke liye height aur diamter ek saath store karate gye
         TreeInfo(int ht, int diam) {
             this.ht = ht;
             this.diam = diam;
         }
     }
     public static TreeInfo diameter2(Node root) {  // return type TreeInfo kyu ki ur node ko apni height aur diameter ek saath return karani h
             if(root==null) {
                return new TreeInfo(0,0);
             }

         TreeInfo left = diameter2(root.left);
         TreeInfo right = diameter2(root.right); //left and right tree ki informsation le aaye

         int myHeight  = Math.max(left.ht, right.ht) + 1;
         int leftDia = left.diam;
         int rightDia = right.diam;
         int dia3 = left.ht + right.ht + 1;
         int mydiam = Math.max(dia3, Math.max(leftDia, rightDia));
             TreeInfo myInfo = new TreeInfo(myHeight, mydiam);
             return myInfo;

     }  
     */
    //QUESTION 3...orint the sum of all kth level nodes of a binary tree
    static int i=1;
    static int sum = 0;
    public static void ksum(Node root, int k) 
    {
        if(root == null) 
        {
            return;
        }
        if(k==1) 
        {
            System.out.println(root.data);
        }
        Queue<Node> q = new  LinkedList<>();
        q.add(root);
        q.add(null);
        while(!q.isEmpty()) 
        {
            Node currNode = q.remove();
            if(currNode==null) {
                i++;
                if(i==k) 
                {
                    while(!q.isEmpty()) 
                    {
                        Node sumNode = q.remove();
                        sum+=sumNode.data;
                       
                        
                    }
                    System.out.println(sum);
                        
                    break;
                }
                    else {
                        if(q.isEmpty()) 
                        {
                            break;

                        }
                        else
                        {
                            q.add(null);
                        }
                    }

                }
                else{
                    if(currNode.left!=null) 
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
/*stem.out.println("no.of nodes: " + countNodes(root));
 System.out.println("height of tree: " + heightNodes(root));
System.out.println("diameter: " + diameter(root));
 System.out.println("sum of nodes: " + sumNodes(root));
System.out.println(diameter2(root).diam);
*/
int k = 3;
ksum(root, k);


    }
}