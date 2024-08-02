import java.util.*;
public class Graphs4   //Dijkstra's Algorithm
{
    /*
     * shortest path algorithms --these are very important for graphs..
     * for eg we are given a weighted graph ...
     *              1      2     3
     *          0------1------2------3
     *          |             |
     *          ---------------
     *                  4            is weighted graph m agar hume 0 se 2 jaana h to ek rasta h 0-1-2 usme agar weighs(distance) dekhe to 3 lagega aur 0 se 2 ke liye ek 
     *                               direct rasta h usme distance 4 h.. to aise karke hume shortest vaala path nikalna h..ye karne ke liye bohot saare algorithms hote h
     * 
     * Dijkstra's Algorithm:-
     * 
     * is algorithm ka kaam h source se saare vertex ke liye shortest distance calculate kar ke de de'
     * hume ek weighted graph diya hoga aur eksource di hogi.. is algorithm ko use kar ke hume us source se eke saare vertex ke liye shortest distance nikal ke ek distance
     *  naam ke array m store karana h .. 
     * 
     * for eg kisi weighted graph m 0 se leke 5 tak vertex h kisi bhi order m ... aur hume source 0 di h.. to distance array 0 se 5 index tak hoga..
     * 0th index pe store karaenge  source se 0 tak ka distance..source 0 h to 0th index pe zero..
     * 2nd index pe 0 se 2 tak ka distance store karenge,..aise karte kartepoora distance array fill kar ke last m use output kara denge
     * 
     * shortest path algorithm ko seekhne se phle hume ek  RELAXATION  ka concept ana chahiye..
     * 
     * maan lete k weighted graph diya h        S
     *                                       1 / \ 5
     *                                        /   \
     *                                       u_____v   
     *                                          2
     *                          u se v vaala edge direct edge h..
     * 
     * .. yaha S source h ..S se leke u vaala edge direct edge nai h..beech m kuch nodes hongii.. similarly S se v vertex vaala edge bhi direct nai h beech m kuch nodes hongiii..
     * 
     *  ab agar dist[v] yaani v ke liye source se v tak distance array m maan liya 5 store h ..u ke liye dist[u] m 1 h aur u se v vaala edge ka weight 2 h..
     *  to hume ye dekhne parega ki dist[u] + wt. of direct edge from u to v agar dist[v](yaani source se v tak ka jo abhi humne distance maana hua h) se kam h to dist[v]= ko 
     *  change karna parega aur vo ho jaega dist[u]+wt. of edge between u and v ke equalkyu ki hume shortest path chchiaye ...
     * 
     * this concept is called relaxation i.e.
     * 
     *    if(dist[u]+wt.of direct edge between u and v < dist[v])
     * {
     *     dist[v] = dist[u]+wt.cdthis relexation concept is important
     * 
     * }
     * 
     * is relaxtion ki condition ko use kar ke dijkstras algorithm implement karte h that is ki agar source se v node tak ka phle agar distance nikala hua h aur
     *  vo bara h source se u node + wt of edge between u and v to dist[v] update karna paregaa
     * 
     * 
     * Dijkstras algorithm BFS ko use kar ke implement hota h...BFS ko hum queue se implement karte the..dijkstras ko bhi queue se kar sakte h lkn hum queue ki 
     * jagah priority queue se karenge..priority queue se cheeze thori aasan ho jaati as it is more time efficient than simple queue..
     * 
     * PRIORITY QUQUE ko abhi para nai h ,,priority queue hoti to queue hi h lkn usme jaise jaise elements add karte h jaise queue m karte the to chotte elements
     *  ko in built priority milti h aur vo apne aap hi andar hi andar sort hote jaate h..
     * 
     * for eg agar queue.add kar ke hume 4 ,6,3,5 ko add kiya to vo 4,6,3,5 ke order m hi queue m aayenge..
     * lkn agar priority queue m inko add kiya to vo queue m is tarah add honge-3,4,5,6..
     * ye in built priority change bhi kar sakte h hum  isko ascending ki jagah descending bhi bana skate h..
     * 
     * Defining priority queue:-   PriorityQueue<Integer> pq = new PriorityQueue<>();   jaise aur collections framework ko define karte h...shurhu m priorityQueue
     *  ki jagah jhaali Queue bhi likh skate h lkn last vaale m priorityqueue hi likhna paregaa..
     * 
     * aise define karne ke liye java.util.*; lagega..aur aise define karne pe in built priority ascending order ko di jaegi..queue m sab se aage sab se chotta
     *  element aajaegaa..
     * to change the priority...PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());    iske liye java.util.Comparator; ya java.util.*;
     *  import karana paregaa  // aise define karne pe descending order ko priority milegiii
     * 
     * priority queue ko use kar ke hi heaps(min heaps and max heaps ) ko implement karte h
     * 
     * BFS ke andar agar hum queue use kare to time complexity thori jaada lagti h aur priority queue use karne pe kam 
     * dijkstras algorithm ek GREEDY ALGORITHM hota h
     * 
     * Dijkstras algorithm implementation:-
     *                                            2        7        1
     * we are given this weighted graph:-      0------>1------->3------->5
     *                                         |       |1       ^        ^
     *                                         |       *        |2       |5
     *                                         ------->2------->4--------
     *                                             4       3
     * hamare pass ek vis array h aur ek distance array hoga index 0 to 5...vis array m initially sab jagah false h aur distance array m sab jagah infinity h ..bas
     *  source vaale index pe yaani in this case 0 pe 0 h as source(0) se 0 tak ka distance =0;
     * 
     * IMPORTATN STEP:- hum un node ke liye check karenge jiska vis array m false ho aur dist array m distance mininmum ho..
     * 
     * 0 se start kiya .. 0 ko vis m true  kiya.. 0 se 0 ka distance dist array m 0 h..0 min h use rhene diya..ab 0 ke neighbours ke liye distance ko update kara denge....
     * ek nieghbour h 1 ek h 2.. 1 ke liye infiniy se kaat ke dist array m 2 kar diya.. as 0 se 1 ka direct edge se 2 h distance.. 2 ke liye infinity se 4 kar diyaa..
     * ab dekhenge vis array m konsi node false h ..0 ke alava saari node false h abhi aur dist array m 1 ka dist min h to 1 ko le liyaa..
     *  1 ko vis m true kiya..aur 1 ke neighbours ka distance update kar diya dist array m..1 ka ek neighbour 2 h aur ek 3 h..
     * ab 2 ke liye relaxation vaali condition dekhenge.. ki 2 hamara v node ho jaega aur 1 hamara u node h aur source S hamara 0 h
     * is case m S se v yaani 0 se 2 ke liye abhi dist[v] m 4 h aur dist[u]+wt. of direct edge between u and v yaani dist[u] yaani 1 ka dist h 2 and wt. of direct edge
     *  h 1yaani dist[u]+wt=2+1=3 < dst[v]
     * to dist[v] = dist[u]+wt. = 3..yaani dist array m 2 pe 4 se kat ke 3 ho jaegaa,..
     * 1 ka doosra neighbour h 3..uske liye bhi relaxation condition lagegai as abhi dist array m 3 pe infinity h which is greater thann dist[u] (2) + wt. f direct
     *  edge betwen 1 and 3  ( 7) = 9 <infinity.. to dist array m 3 pe 9 aagya..
     * fir dekhenge ki vis array m kon kon false h aur unme se kis ka dist array m samllest dist h ..abhi 2 ka h to 2 ko le lenge vis array m true karenge aur fir
     *  se upar vaala kaam karenge,,
     * 
     * ye karte karte jab saari nodes visit ho chuki hongi us case m dist array m har index pe jo jo value hogi vo source se us node ke liye shortest distance hogaaa
     * 
     * last m final dist array ye nikal ke aayega:-   0 2 3 8 6 9
     * 
     * is approach ko implement karenge using priority queue ..kyu ki hume har baar shortest distance vaala node chchiye dist array m dekh ke.. to uske liye priority 
     * queue ko use karenge usmee nodes daal denge aur hume vo sab se phle vo node dega jiska distance min h as priority queue by defaut smallest ko priority deti h 
     * 
     * hum ek pair class banaenge ..usme hum node ki information store karenge.. pair m hum ek to konsi node ki baat kar rhe h o store karenge aur ek uska source se
     *  distance.. for eg  ek pair ho jaega {0, 0},  ek ho jaega {1,2}..yaani 1 node h jis ki baat kar rhe aur 2 ho gya 0 se  1 distance..
     * jo priority queue banaenge vo isi pair type ki hogi..aur hum apni queue m pairs add karte jaenge.. aur internally hi vo sort kar degi min vaale ko priority de ke
     * PSEUDOCODE:-
     * 
     * while(!pq.isEmpty)
     * {
     *  pair curr = pq.remove();   yaaani ye jo pair mila priority queue se uska distance min hoga..as priority queue se nikala h ..
     * if(!vis[curr.node])  // saara kaam tab hi karenge jab curr vaale pair m jo node h vo vis array m false h
     * {
     *
     * 
     * vis[curr.node] = true;   // jo node mili usko vis  true kiya ru uske neighbours ke distance ko update kar denge uski relaxation
     * 
     * for(int i=0; i<graph[curr.node].size(); i++)
     * {
     *      Edge e = graph[curr.node].get(i);
     *      relaxation m u ho jaegi hamari e.src and v node ho jaegi e.dest;
     * if(dist[v]>dist[u]+e.wt) // yaani relaxation
     * {
     *      dist[v] = dist[u]+e.wt;
     *      pq.add(v, dist[v]);
     *  
     * }
     * }
     * }
     * 
     * }
     * 
     * ye h apna dijkstras ka pseudocode
     * 
     */   
    static class Edge
    {
        int src;
        int dest;
        int wt;

       
        public Edge(int s, int d, int w )  
        {
            this.src=s;
            this.dest=d;
            this.wt = w;

        }

    }
    public static void createGraph(ArrayList<Edge>graph[])
    {
   
        for(int i=0; i<graph.length; i++) 
        {
            graph[i] = new ArrayList<Edge>();
        }
      

        graph[0].add(new Edge(0,1,2));
        graph[0].add(new Edge(0,2,4));      //ye above dijkstras algorithm vaale example vaala grpah h

        graph[1].add(new Edge(1,3,7));  
        graph[1].add(new Edge(1,2,1));

        graph[2].add(new Edge(2,4,3));
      
        graph[3].add(new Edge(3,5,1));

       
        graph[4].add(new Edge(4,3,2));
        graph[4].add(new Edge(4,5,5));
       
      

    }

    //DIJKSTRA'S:-   IMPORTANT--TIME COMPLEXITY FOR THIS ALGORITHM IS O(E + ElogV)   . .. ye ElogV vaaala part priority queue ki vagah se aata h ..ku ki pq m sorting
    // ho rhi h aur merge sort ki bhi time complexity O(nlogn)  hoti thi  ...  E vaaala part tab aata h jab hum saare edges ko ek ek karke travserse karte h 
    public static class Pair implements Comparable<Pair>   // Comparable java m ek interface hota h  Comparable tab use hota h java m jab koi class h aur usme hume sorting 
    //karani h class ki kisi bhi characteristic ke basis pe.. to hum Comparable ko ih=nherit kar lete h .. comparable kisi bhi class ko comparable class bana deta h 
    {
        int node;
        int dist;

        public  Pair(int n, int d)
        {
            this.node = n;
            this.dist = d;
        }
         //logic for comparison.....ho ye raha h ki hume dist ke basis pe sorting karni thi.. to humne Comparable ko implement kar liyaa..
    // compare karne ke liye hamare pass ek comparTo function hona chchiye class ke andar.. jo ki hum override kara rhe h.. ye function public int type ka hoga.. 
    //sattic nai ho sakta varna error aajaega..
    // isme humne parameter daala pair p2...ab agar this vaale pair ka dest jaada hoga pair vaale se .. to positive value return hogi.. equalhoga to 0 mileha.. kam 
    //hoga to negative value retuen hogiii

    // is compare vaale logic ko greedy vaale chapter m parenge vaha har class ko comparable banana parta h
    
    @Override
    public int compareTo(Pair p2)
    {
        return this.dist-p2.dist;   // ye ascending ke liye h jo hume chahiye..
        //return p2.dest-this.dest    ye descending ke liye h agar priority jaada vaael ko deni h to
    }
    }
   

    public static void dijkstra(ArrayList<Edge>graph[], int src,int V)
    {
            PriorityQueue<Pair> pq = new PriorityQueue<>(); // ab ye humne priority queue bana to li lkn ek ques arises ki queueko kaise pata ki sorting pair m dist ke basis
            // pe karni h ..usne node ke basis pe kar di to????... to iske liye pair class m COMPARABLE ko use karte h aur implement karte hand we use override
            int [] dist = new int[V];// ab isko initialize kar dete h infinity taaki har index pe source  ke alava infinity aajae
            for(int i=0; i<V; i++)
            {
                if(i!=src)
                {
                    dist[i]=Integer.MAX_VALUE;   // infinity yaani maximum integer value
                }
            }
            boolean vis[] = new boolean[V];

            // shuru m priority queue m add kar denge shuru vaala pairyaani {0,0} vaala

            pq.add(new Pair(src,0));

            // ab yaha se code bfs jaisa hogaa

            while(!pq.isEmpty())
            {
                Pair curr = pq.remove();  //  yaha se priority queue m s remove ho rha h to yaani ye shortest distance vaala pair mila hoga hume
                if(!vis[curr.node])
                {
                    vis[curr.node]=true;
                    for(int i=0; i<graph[curr.node].size(); i++)
                    {
                        Edge e = graph[curr.node].get(i);
                        // u vaali node hogi is edge m jo src h aur v vaali node hogi is edge m jo dest h ..
                        int u = e.src;
                        int v = e.dest;

                        //relaxation
                        if(dist[v]>dist[u]+e.wt) 
                        {
                            dist[v] = dist[u]+e.wt; // yaha humne neighbour ka distance update kar duya..ab priority queue m bhi add kar denge is neighbour ko node bana
                            // ke uska dist leke pair bana ke
                            pq.add(new Pair(v,dist[v]));
                        }
                    }
                }
            }
            //ans print:-
            for(int i=0; i<V; i++)
            {
                System.out.print(dist[i]+" ");
            }
            System.out.println();

    }

     
    public static void main(String args[])
    {
        int V=6;
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);
        dijkstra(graph, 0, 6);
        
    }
}
//IMPORTANT:- dijkstras algorithm un cases m fail kar jaata h jaha kisi bhi edge ka weight negative ho..aisa agar ek bhi edge hua jiska weight negative h to
// vha dijkstra algorithm koi gurantee nai leta ki ans sahi aayega..
//negative edges vaale case m hum fir bellman ford algorithm ka use karte h.. ye bellman ford negative positive saare type ke cases m applicable h