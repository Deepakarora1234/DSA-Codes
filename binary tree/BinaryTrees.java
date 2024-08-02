public class BinaryTrees { // building a tree using preorder
    static class Node 
    {
        int data;
        Node left; // this is left child node
        Node right; // this is right child node

        Node(int data) {
            this.data = data;
            this.left = null; // initially left and right child are both null
            this.right = null;
            // ye ban gai Node class jo tree ki har ek node ko represent karegi
        }
    }
    static class BinaryTree 
    {
        static int  idx = -1; // we use this static variable idx to to reach different nodes in a tree
        public static Node buildTree(int nodes[]) { // ye function hamari saari nodes ko lega as input..yaani poora array lega nodes vaala and return us the root node
                idx++;  // function ko call lagte hi idx update ..0th index pe aaye yaani sab se phle root ko create karenge

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

    public static void main(String args[]) 
    {
        int nodes[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};  /*  nodes naam ka array de rakha h ..this is called preorder..we build a tree using this ..-1 reprsents null..and first element is root node
                         preorder use kar ke kaise banate h tree....first element is root node
                        next element is its left child..yaani 1 ka left child h 2...2 ka left child h 4..aur uske baad likha h -1 yaani 4 ka left child nai h koi
                        yaani ab 4 ke right child ko dekhenge usme bhi -1 h yaani 4 ka right child bhi nai h ..that means 4 is a leaf node
                        ab vapas aayenge..next element is 5..yaani 2 ka right child h 5..next is -1..that means 5 ka koi left child nai h..next is again -1..yaani 5 ka koi right child bhi nahi h....
                        ab dobara vapas jayenge..aise karte karte tree banayenge
                        */


        BinaryTree tree = new BinaryTree();
        Node root = tree.buildTree(nodes); // buildTree rootnode return karta h usko root naam ke node m store kara liya
        System.out.println(root.data); // root node m 1 tha to 1 print ho jaega

    }
}


