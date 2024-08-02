import java.util.*;
public class Graphs1   //contents:- about graph, ways to create a graph, BFS,DFS
{
    /*
     * graph is a network of nodes
     * every node of graph is called vertex and every link(connection) between 2 nodes is called edge
     * applications of graphs :- google maps, social network, deleivery network etc
     *                  a   b---c      h       this is an example of graph
     *                   \ /     \   /
     *                    d        e
     *                              \
     *                               g
     * 
     * in above graph  each node is called a vertex  
     * edge-  connection between two nodes or vertices..
     * edges  based o direction edges are of two types  1) uni-directional:- x--->y   x node se y node ka connection ..y se x pe vapas nai aa sakte
     *                                                    2) bi-directional/undirectional:-   x-->y  yaha x se y aur y se x dono jagah jaa sakte h
     *                                                                                         <--                      
     *           bi dIrectional edge ko aise bhiu represent karte h.... x--y   agar do vertex ke beech ke edge m koi direction nai dikhai..to matlab bi directional h
     * based on these types of edges ..graphs are named as 1) directed graph:- only contains uni directional edges
     *                                      2) undIrected graphs: contains undirected edges (edges m direction specify nai hoti)
     * 
     * based on weight ..edges are of 2 types  1) weighted:- if any number associated with edge..then it is weighted edge..that number can be time, distance or any other quantity
     *  graph with weighted edges is called weighted graph
     *                                          2) unweighted:- no wieght associated...graph can be unweighted directed or unweighted undirected or weighted directed or weighted unndirected
     * neighbour;- kisi ek vertex se hum kitni vertex pe pohonch skate h unko u vertex ke neighbours bolte h
     *  
     * Storing a graph:-   yaani code m graph ko represent kaise karenge...by following ways:-
     * 1) Adjacency List    this method is most widely used....other methods are also  used
     * 2) adjacency matrix
     * 3) edge list
     * 4) 2d matrix(implicit graph)
     * 
     * Adjacency list:- list of lists...following is the explanation of implementation of graph using adjacency list:-
     * 
     *          0   3
     *           \ / \
     *            2---1  this is undirected un weighted graph....no. of vertices (V) = 4   no. of edges(E)=4   4 vertices h to o se leke 3 tak nnumbering kar denge har vertex ke liye
     *  list of list banani h.. to vertex wise store karaenge
     * 0--> {0,2}   0 vaali vertex ke liye 0 source h aur 2 destination.. to list ke har index pe hum ek vertex  ke liye saari possible destination store karaenge edge wise
     * 1--> {1,2}, {1,3}  1 source h aur 2 and 3 destination.. jis vertex ki baat ka rhe h uske liye source hamseha vahi hoga
     * 2--> {2,0},{2,1},{2,3} 
     * 3--> {3,1},{3,2}
     *                                        0     1
     * to kuch aise store hoga list m.....    _     _     _ _ _ _  ye hamare pass ek V size ki list h
     *                                        |     |     |
     *                                      {0,2}  {1,2}
     *                                             {1,3}  and so on
     * this adjacency list can be formed using Arraylist of arraylist,  hashmap, array of arraylist......we will use array of Arraylist
     * ab array  ke har index pe arraylist store h..aur Arraylist m edges store karaenge..that means
     * ArrayList<Edge> graph[V]....ye h hamari adjacency list...graph naam ke array m ArrayList of edge store h..
     * array bhi ban jaega ArrayList bhi ban jaegi...edge ko banane ke loye static class banaenge
     * edge class m do information store karaenge is case m ...ek int source aur ek int destination....agar weighted graph hota to weight bhi store karate lkn is example m weighted nai h to khaali source aur destination store karai h
     * 
     * implementation 2:- adjacency matrix:-
     * 
     * here we make a 2D matrix of sixe V*V
     * at every (i,j)  we store either 0 aur 1(if weighted  we store weight of the edge instead of 1)
     * that means.. for every (i,j) if there is a connection (edge) that exists between i and j than we store 1 else 0
     * for above graph example.. adjacency matrix implementation will look like this:-
     *          (0)  (1)  (2)  (3)
     *      (0)  0    0    1    0
     *      (1)  0    0    1    1
     *      (2)  1    1    0    1
     *      (3)  0    1    1    0     since there are no self edges ...there is 0 at (0,0), (1,1), (2,2), (3,3)
     * 
     * agar weighted graph hota to 1 ki jagah edge ka weight store kar dete
     * 
     * this implementation is not good due to the following reasons:-
     * 
     * 
     * 
     *
     * 1) space:- whe we used adjacency list.. no extra space was used but here ..hum jaha edges nai h vaha 0 store kara rahe h vo extra space h..to is implmentation m extra space use ho rhi h  space used-O(V^2)
     * 2) neighbour ko find out karne vaale operation m O(V) time lagega aur adjacency list vaale m O(x) ka time lagta h jaha x is the no. neighbours
     * 
     * O(V) aise lagega yaha kyu ki hume  agar 2 vaali vertex ke nieghbour chahiye.. to 2 ke liye poori ek row pe traverse karna parega aur dekhna parega ki 0 hai ki 1 ...to aise V baar dekhna parega..O(V)...not so optimized
     * 
     * implementation 3:- edge list:-
     * yaha hum saare edges ki list bana lete h
     * for above example following will be the edge list
     * 
     * Edges={ {0,2}, {1,2}, {1,3}, {2,3} }   if no. of edges =E  then size of Edge list = E
     * Edge list can be implemented in no . of ways for eg: using ArrayList, LinkedList etc..  usually ArrayList ki form me implement karte h
     * edge list implementation is used in those algorithms or ques where we need to sort the edges..
     * for eg in the case of minimum spanning tree(MST) we sort edges ..aur agar edges ki list ho to ye sorting aasan ho jaegii
     * sort karne ke alag alag criteria ho sakte h.. for eg sort on the basis of weight .. to edges ke saath edge ka weight store karaenge edge list m aur weight ke basis pe store kar denge
     * 
     * implmentation 4- implicit graph
     * implicit means internal
     * is implementation m hume 2D matrix diya hota h hume use hi graph maan lena hota h
     * aur kisi cell pe h hum 2D matrix ke vaha se hum uske neighbours nikal sakte h (i,j) ki values m +-1 karke
     * 
     * 
     * Graph Traversals:  1) Breadth first traversal(BFS)   2)Depth First Traversal(DFS)
     * 
     * 1)BFS:-   BFS says go to immediate neighbour first
     * for eg  we are given a graph like this:-   1-----3
     *                                           /      |  \
     *                                          0       |    5----6
     *                                           \      |   /
     *                                            2-----4
     * 
     *              unlike binary tree where we have a root node to start our traversal...we dont have anything for graph so we need a starting point..so by convention we take 0th index vertex to be starting point
     * 0 se start kiya yaani 0 visit kiya print kara diya..ab 0 ke nighbours h 1 and 2..lets say 1 visit kar ke print kara diya..ab 1 se aage jaane se phle BFS ki property stisfy karani paregi that go to immediate neighbour first..yaani 0 ke saare neighbour pe jaana parega.. to 1 ke baad 2 visit kar ke print kiya
     * 2 ke baad 3 visit kiya aur print kiya aur 3 ke nighbours pe jaaene se phle 2 visit ho chuka h to uske neghbour pe jaana parega  2 ke nieghbours pe gye property BFS ki satisfy karane ke liye aur 4 visit kiya
     * uske baad 5 visit kiya fir 6..
     * 
     * that means BFS sequence for above graph :- 0 1 2 3 4 5 6
     * 
     * trees m para tha ki level order traversal is breadth first traversal...
     * to graph vala BFS bhi ek tarah se indirect level order traversal h...
     * like this    0|1 2 | 3 4 |5|6
     * 
     * Trees ke level order traversal ke liye queue use ki thi ..graphs ke liye bhi queue use karenge
     * ek visited naam ka Array bhi banaenge jo ye track karega ki kon kon si node visit ho chuki h
     * 
     * that means     Visited ka size total vertices ke equal hoag..for above eg visited= 0 1 2 3 4 5 6   ye 0 se 6 index h jinpe initially false h
     * ye visited naam ka array boolean type ka hoga aur shuru m saare index pe false hoga..fir jaise jaise har node pe jaenge to us index ke liye true karte jaenge
     * 
     * queue m starting node daal denge aur ab 3 steps m BFS implement karenge:-
     * ek ek kar ke queue se node nikalenge..usko print karaenge ..uske repsective index pe visited naam ke array m false se true karenge aur us node ke children ko queue m add kar denge..
     * ye saare steps tab hi karenge jab visited vaale array m removed node pe false ho..true hua to khaali remove kar denge
     * aur ye steps tab tak follow karenge jab tak queue empty na ho jae
     * yaani these 3 steps
     * 1) print
     * 2)visited[curr]=true;   currnode vo h jo abhi queue m se nikali h aur jiska check kar rhe h
     * 3)add curr node neighbours in queue
     * 
     * 
     * 
     * 
     * 
     * 
     *  following is the code to implement graph using adjacency list (array of arraylist)
     */
    static class Edge
    {
        int src;
        int dest;

        //int wt;  agar weighted graph hota to edge class m weight property bhi hoti

        public Edge(int s, int d )  
        {
            this.src=s;
            this.dest=d;

            //this.wt = wt;   for weighted graph


        }

    }

    //create graph
    public static void createGraph(ArrayList<Edge>graph[])
    {
        //abhi phle graph naam ke array ke har index pe null h..null pe direct data store nai kara sakte...loop laga ke phle har index pe empty arraylist bnaenge

        for(int i=0; i<graph.length; i++) 
        {
            graph[i] = new ArrayList<Edge>();
        }
        //ab add karna shuru karenge har index ki arraylist m us vertex ko source maan ke saari destination edge class ko use kar ke add karenge

        graph[0].add(new Edge(0,1));
        graph[0].add(new Edge(0,2));  // weighted graph ke liye src aur dest ke saath wt bhi daalna parta

        graph[1].add(new Edge(1,0));   //ye graph is time BFS vaale example ka h
        graph[1].add(new Edge(1,3));

        graph[2].add(new Edge(2,0));
        graph[2].add(new Edge(2,4));
        

        graph[3].add(new Edge(3,1));
        graph[3].add(new Edge(3,4));
        graph[3].add(new Edge(3,5));

        graph[4].add(new Edge(4,2));
        graph[4].add(new Edge(4,3));
        graph[4].add(new Edge(4,5));

        graph[5].add(new Edge(5,3));
        graph[5].add(new Edge(5,4));
        graph[5].add(new Edge(5,6));

        graph[6].add(new Edge(6,5));


    }

    //BFS  ..hum disconnected components vaala graph agar diya hoga to uske hisaaab se likhenge code BFS ka ..vo code normal graph ke liye bhi kaam karega

    //important:-  BFS ki time complexity O(V+E)
    public static void BFS(ArrayList<Edge> graph[],int V,boolean vis[], int start)   // agar disconnected vaala case hua to visited array main m define hoga to parameters m visited array aur starting point dono aayenge
    {
        Queue<Integer> q = new LinkedList<>();
      
        q.add(start);  // ye starting point add karna hota h hume shuru m queue m ,,aur ye statring point koi bhi ho sakta h by convention hum 0 lete h    aur disconnected vaale case m jo starting point main function se milega function call m usko add karenge is step m queue m 

        while(!q.isEmpty())
        {
            int curr = q.remove();
            if(vis[curr]==false)
            {
                System.out.print(curr+" ");

                vis[curr]=true;

                for(int i=0; i<graph[curr].size(); i++)   //is step m curr ke saare neighbours ko queue m add kara diya
                {
                    Edge e=graph[curr].get(i);
                    q.add(e.dest);
                }
            }
        }


    }
    /*  graph jaroori nai ki single components vaala ho..disconnected components bhi ho sakte h .. like this
                            0----1----2  ye component 1 h
                              3----4      ye component 2 h...ye dono ek hi graph ke components h alag alag graph nai h

                              aise case m agar hum khaali ek starting point le eg agar 0 liya to 0 se khaali 1 aur 2  pe jaa sakte h aur agar 4 liya to khaali 3 pe jaa sakte h

                              to aise case ke liye hum loop chalaenge aur visited array m jaha false h usi ko statring point maan lenge..aise disconnected components vaale case m hume visited array main function m hi banana parega aur ye vaala loop bhi main function m hi chalaenge
                              aur BFS function se BFS kara denge 
                              upar vaale BGS ke function m hi kuch changes karne parenge disconnected components ke liye
      
                              aur jo changes karenge code m vo fir agar normal graph bhi hoga to bhi same answer dega to hum change kar ke hi likhenge BFS ka code
    */


    /*
     * DFS:-   DFS time complexity also O(V+E) like BFS
     * DFS says keep going to the 1st neighbour
     * humne trees m para tha ki post order pre order in order traversal depth first traversal hote h..similar si approach lagegi graph ke case m bhi
     * trees ki tarah DFS in graph recursion se implement karenge... eg BFS vaale graph ka hi le lete h
     * 0 se start kiya ..0 ko print karaya.0 ke nighbour pe gye yaani 1 pe..1 ko print karaya..aur fir 1 ke nighbour pe guye..1 ke ek nieghbour 0 h vo already aa chuka h.. to 3 pe gye
     * 3 ko print karaya.... 3 ke nighbour pe gye ek neighbour 1 h vo already aa chuka h to 4 pe gye 4 ko rpint karaya..
     * 4 ke ek neighbour 2 h 2 ko print karaya.. 2 ke saare neighbour aa chuke h to vapas 4 pe aaye aur 4 ka next neighbour 5 usko print karaya
     * 5 ke ek nieghbour 3 h vo aa chuka h to next neighbour 6 h usko print karaya ..6 ke ek hi neighbour h 5 vo aachuka h..vapas 5 pe jeange fir aise karte karte vapas 0 tak aajaeng aur DFS sequence mil jaega
     * 
     * to DFS sequence ye mili:- 0 1 3 4 2 5 6    aur kon kon already aa chuka h ye BFS ki tarah visited array se check kar lenge 
     * 
     * DFS ke liye recusrive function likhenge... void DFS(graph[], curr,vis[])     ek parameter hamara graph..ek parameter curr jo curr node h jo queue m se nikalete the hum aur ek visited vaala array
     *  recursive function m 1) curr ko print karanege
     *                      2) vis[curr] ko true kar denge
     *                      3) aur fir  curr ke neighbourske liye DFS function ko call laga denge..neighbour svahi looop laga ke nikalenge...like this:-
     *                          for(int i=0; i<graph[curr].size(); i++)
     *                          {
     *                              Edge e = graph[curr].get(i);
     *                                  DFS(graph, e.dest, vis);
     *                          } 
     * graphs m visited use karte h trees m nai karte kyu ki trees heirerchial hote h aur graphs m cycle ho sakti h ek vertex se ghoom ke vapas usi pe aasakte h trees m nai hota ye 
     * DFS code:-
     */
    public static void DFS(ArrayList<Edge> graph[], int curr, boolean vis[])
    {
        System.out.print(curr+" ");

        vis[curr]=true;

        for(int i=0; i<graph[curr].size(); i++) 
        {
            Edge e = graph[curr].get(i);
            if(vis[e.dest]==false)
            {
                DFS(graph, e.dest, vis);    // yani agar neighbour visit nai hua tabhi uske liye DFS ko call karenge varan nai karenge

            }
        }
    }

    public static void main(String args[]) 
    {
        int V=7;
        
        ArrayList<Edge> graph[]=new ArrayList[V];   //graph naam ka array banaya h aur is array ka type h ArrayList<Edge>

        //print neighbours of vertex 2
        createGraph(graph);

      

        // for(int i=0; i<graph[2].size(); i++)     ye loop chala ke kisi bhi vertex ke liye uske saare neighbours nikal sakte h
        // {
        //     Edge e = graph[2].get(i);
        //     System.out.println(e.dest);
        // }

       

        /*
         * agar disconnected components diye hue h to main function m hi visited array ko define karenge BFS function m nai karenge aur 
         * main function se hi loop chalaenge ..jaha visited array m false mila usko as a starting point BFS m bhej denge aur BFS us vaale component ke liye saara kuch print kara ke visited vaale array m true update kar ke de dega ..aise kar ke seperate seperate components ke liye BFS execute ho jaega...code as folllows:-
         * 
         * */

          boolean vis[] = new boolean[V];
        //   for(int i = 0; i<vis.length; i++)
        //   {
        //     if(vis[i]==false) {
        //     BFS(graph, V, vis, i);     // disconnected vaale case m BFS ke parameters m visited array bhi hoga aur starting point jo queue m shuru m add hota h vo bhi hume batana parega aur vo i hoga jis index pe vis[i] false h;  
        //   }
        //   }
        for(int i=0; i<vis.length; i++)   //bfs ki trah yaha bhi ho sakta h disconnected components ho graph ke us case m phle ki tarah loop lagaenge aur jis index pe visited array m false milega use curr maan ke dfs ko call laga denge
        {
            if(vis[i]==false)
            {
                DFS(graph, i, vis);
            }
        }
        
         
    }
}
//revise leetcode word ladder problem

// X8IZo7d74QzYrhbk
// aroradeepak0817
//mongodb+srv://aroradeepak0817:X8IZo7d74QzYrhbk@mer-booking-app-db.kfqgdyn.mongodb.net/?retryWrites=true&w=majority

//mongodb+srv://aroradeepak0817:<password>@mer-booking-app-db.kfqgdyn.mongodb.net/