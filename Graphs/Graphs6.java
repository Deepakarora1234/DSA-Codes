import java.util.*;
public class Graphs6  // mst and prims algorithm
{
    /*
     * MINIMUM SPANNING TREE(MST):-  MST khaali connected, undirected and weighted graphs ke liye hota h..ye teeno condtion satisfy honi chahiye..agar disconnected graph hua to mst nai nikal sakte
     * 
     * MST- MST or minimum weight spanning tree is a subset of the edges of a connected and edge weighted graph that connects all the vertices together, without any cycles and with the minimum possible total edge weight
     * 
     * yaani MST kisi bhi graph ka ek subgraph hota h jisme saare ke saare vertices included hote h, aur ye saari vertices connected hoti h disconnected nai ho saktii lkn edges khaali necessary vaale include hote aur is tarah se hota h ye subgraph ki koi cycles na ho aur saare edges ke weight ka sum minimum ho
     * 
     * total EDGE WEIGHT:- weight of all edges of the graph
     * kisi graph ke multiple spaning trees ho sakte h lkn minimum vahi hota h jiska edge weight minimum hota h 
     * to kuch important properties hoti h mst ki...its a undirected weighted subgraph for a given graph with all vertices included and connected ,  no cycles and minimum edge weight
     * isko tree isi liye bolte h kyu ki grphs m cycle ho sakti h lkn mst m cycles nai hoti trees ki tarah to aise hi tree bol dete h 
     *  
     * example...lets take a graph:-   
     * 
     *               5
     *      ---------------------                   
     *      |    1        3      |
     *      A--------B----------E
     *               | *--------
     *             5 |  10      |4
     *               C----------D
     * 
     * ye graph diya h ..iske MST ke liye hum E se  A vaala edge hata denge aur C se D vaaa=la edge hata denge..to aise hume MSt mil jaega jisme saare vertices honge connected bhi honge aur minimum edge weight hoga
     * mst for bove graph:-      
     *                           A----B---E
     *                               / \
     *                              C   D   with their respective weighted edge  is case m edge weight hoga 13 which is minimum for above graph
     * 
     * 
     * MST implement karte h hum PRIM'S ALGORITHM USE KAR KE
     * 
     * PRIM'S ALGORITHM:-   PRIMS ALGORITHM KE LIYE TIME COMPLEXITY HOTI H O(ElogE)
     * 
     * is algorithm m hum MST set ka use karte h..code m hum koi set nai bana rhe honge ..MST(minimum spanning tree) set bas khaali samaghne ke liye h 
     * 
     * implementation of prim's algorithm:-
     * 
     * we are given his graph:-
     * 
     *           0
     *     10  /|   \15
     *        / |30  \
     *       1  |  -- 2
     *        \ | /
     *     40  \|/50
     *          3
     * 
     * ab iske kahi saare spanning trees ho sakte h...lkn minimu spannning tree vo hoga jisme  0 se 1 connect ho..0 se 2 connect ho aur 0 se 3 connect ho
     * mst set vaali implementation m hamare pass 2 sets honge ek mst set ek non mst set
     * non mst set m hamare pass vo saari vertices h jo abhi MST m include nai hui h yaani shuru m saari vertices non mst set m hongii
     * code ke andar hume set nai banan bas aproach samaghne ke liye imagine kar rhe h 
     * to abhi upar vaale graph ke liye hamare pass saari vertices 0 1 2 3 non mst set m h
     * 
     * doosra set hamare pass mst set h jisme vo vertices h jo mst m include ho chuki h 
     * ab hume kisi vertex se start karna hota h tohumne 0 se start kiya ..0 ko non mst se utha ke mst vaale m daala aur ek cost variable bana liya total edge weight ko track karne ke liye..
     * ab mst set m 0 h to 0  ke liye dekhenge jo bhi non mst set vali vertices h unme se minimum edge weight kiska h ..upar example m 0 se 1 vaala edge ka weight min h 10...to 1 ko utha ke mst set m daal diya aur cost m 10 add kar diya..
     * 
     * ab fir se mst set vaali vertices ke liye dekhenge ki in vertices se non mst set vaali vertices tak jo edges h unme se min edge weight kiska h..
     * 0 se 2 vaale ke liye edge weight 15h aur 1 se 3 vaale ke liye 40 h.. to 0 se 2 vaala lenge aur 2 ko utha ke mst set m dal denge aur cost m 15 add kar denge..
     * fir ab bas 3 bacha h non mst set m..
     * aur 0 1 2 h mst set m..in ke liye 0 se 3 vaala edga ka weight min h to 3 ko uths ke mst set m daala aur 30 add kar diya cost m...
     * 
     * to ye mst bana hamara....      
     * 
     *      0
     *     /|\
     *    1 3 2    with their respective edges... to yaha saari vertices bhi aagai..connected bhi h and total edge weight h 55 which is minimum total edge weight
     * 
     * to hum baar bar minimum edge weight dhoond rhe h ....aisi similar si cheez humne dijkstra's algorithm m bhi ki thi using priority queue...yaha bhi hum vahi karenge
     * 
     * to humara jo non mst set h vo basically ek priority queue hogi jo hume har baar  minimum edge weight vaala vertex de degiii
     * 
     * aur mst set hamara vis array hoga kyu ki ek tarah sse jo node mst set m include ho gai usko hum vis m true kar rhe h  bas
     * is baar bhi priority queue m pair store karaenge..pair m do info hogii ek to hamara vertex  aur us vertex tak jaane ki cost 
     * aur ye jo sorting hogi vo cost ke basis pe hogi...that means jaise humne 0 ko include kar liya tha s set m..
     * to fir hune cost dekhi thi,,1 tak jaanw ki cost 10 thi 2 ki 15 thi aur 3 ki 30 thi to humne 1 vaali min vaali phle le liii
     * 
     * shuru m pq m add kar denge (0,0) vaala pair..shuru kisi bhi node se kar sakte thelkn by convention hum 0 se shuru kiye
     * 
     * hum loop chalaneg jab tak pq khaali nai ho jaati aur har baar pq. remove se pair nikal lenge..ab kisi vertex pe jaane ke liye kahi saare tareeke h aur vo saare pq m add h lkn by default hume min vala milega...lkn bache hue abhi bhi queue m h.. to vo vaale include na ho jae min vaale ke include hone ke baad uske liye hum phle check karenge ki pq.remove kar ke jo pair mila h 
     * usme jo node h uske liye vis true to nai h.. agar true h to usko chor denge
     * 
     * aur curr pair jo pq.remove se mila h..usme curr.node agar vis m false h.. to phle usko true karenge aur fir uske liye cost variable ko update kar denge
     * 
     * hume ye aise bhi implemnt kar sakte h ki cost nai chahiye..konn kon se eges chahiye mst m ...uske liye ek arraylist of edges bana ke cost ko update karne  ki jagah edge ko is arraylist m daal sakte h 
     * ab cost ko update karne ke baad curr ke jitne bhi neighbours honge unko hume priority queue m add kar dena h 
     * to neighbours vaala loop chala lenge
     * 
     * code for prims algorithm goes here:-   O(ElogE)
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
      

        graph[0].add(new Edge(0,1,10));     //ye above example vaala graph h
        graph[0].add(new Edge(0,2,15));
        graph[0].add(new Edge(0,3,30));     

         
        graph[1].add(new Edge(1,0,10));
        graph[1].add(new Edge(1,3,40));


        graph[2].add(new Edge(2,0,15));
        graph[2].add(new Edge(2,3,50));
      
        graph[3].add(new Edge(3,1,40));
        graph[3].add(new Edge(3,2,50)); 
       
    }
    //prim's algorithm:-   O(ElogE)

    public static class Pair implements Comparable<Pair>
    {
        int node; 
        int cost;

        public Pair(int n, int c)
        {
            this.node = n;
            this.cost = c;

        }
        @Override
        public int compareTo(Pair p2)
        {
            return this.cost - p2.cost; // kyu ki hum cost ke basis pe sorting kara rhe h
        }
    }

    public static void primsAlgo(ArrayList<Edge> graph[], int V)
    {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        boolean vis[] = new boolean[V];
        pq.add(new Pair(0,0));
        int mstCost = 0;

        while(!pq.isEmpty())
        {
            Pair curr = pq.remove();//ye vo vaala pair hoga jisme cost min hogi 
            if(!vis[curr.node])
            {
                vis[curr.node]= true;
                mstCost+=curr.cost;
                for(int i = 0; i<graph[curr.node].size(); i++)
                {
                    Edge e = graph[curr.node].get(i);
                    if(!vis[e.dest])
                    {
                        pq.add(new Pair(e.dest, e.wt));
                    }
                }
            }
        }

        System.out.println("min cost of mst:- "+mstCost);

        // worst case m priority queue m hum E edges ko add karenge aur jo sorting hogi fir usme merge srot vaali time complexity yaani ElogE lagegi..thats why prims algo ki time complexity ElogE hoti h 



    }

    public static void main(String args[])
    {
        int V = 4;
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);
        primsAlgo(graph, V);

    }
}