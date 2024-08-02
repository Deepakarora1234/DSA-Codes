import java.util.*;
public class Graphs5 // bellmanFord algorithm
{
    /*
     * BELLMAN FORD ALGORITHM:-   time complexity:-  O(V*E)   jo ki dijkstra ki time complexity O(E+ElogV) se jaada h
     * iska bhi kaam is to get shortest distance from source to all the vertices
     * dijkstras jaha edges ka weight negative ho jaata h vaha fail ho jaata h us case m bellman ford use karte h hum
     * bellman ford is dynamic programming ke concept pe kaam karti h
     * bellman ford ki time complexity dijkstras se jaada hoti h isi liye positive weight vaale case m hum dijkstras use karenge aur jaha kisi bhi edge ka weight negative aagya vaha bellman ford use karenge
     *                          2          -1
     * given graph:-       0-------->1<----------4
     *                     |         |-4         ^
     *                     |         |           |4
     *                     --------->->2---------->3
     *                          4           2
     * bellman ford m hum do loop lagate h .outer loop chalta h V-1 times where V is the no. of vertices
     * ye outer loop isi liye v-1 times chalta h kyu ki kisi bhi ek graph m agar source node se destination node  jaana h to sbse lambe path m  beech m V-1 nodes aasakti h(source included)
     * aur is loop m hum relaxation perform karte h..
     * yaani V-1 times hum har edge ke liye relaxatoin perform karenge( for all edges (u,v), if dist[u]+wt(u,v) < dist[v],,then dist[v] =  dist[u]+wt(u,v))
     *  to ek dist array banaya V size ka aur usme source (here 0) pe 0 h baaki saare index pe distance abhi infinity h..
     *  ab hume outer loop V-1 times chalana h aur har iteration m saare edges ke liye relaxation oerform karna h..yaani total 4 iteration hongiii
     * 
     * 1st iteration:-
     * 
     * 0(source ) se sart kiya .. 0 ke liye abhi 0 h u and 1 h v.. relaxation perform kiya aur dist arrray m 1 pe store ho gya 2
     * aur ab 0 u h aur 2 v h... relaxation perform kiya aur dist array m 2 pe aagya 4
     * 
     * ab 1 pe aaye.. 1 abhi u h aur 2 v..relaxation perform kiya aur 2 ke liye dist array m agya -2
     * 
     * fir 2 pe aaye   2 ke liye u h 2 and v h 3.. relaxation perform kiya aur dist array m 3 pe aagya 0
     * 
     * fir 3 pe aaye aur above steps ke hisaab se dist array m 4 pe aagya 4
     *  
     * fir 4 pe aaye aur 1 ke liye check kiya to relaxation vaali condition nai aai aur 1 ke liye dist array m 2 ka 2 hi rha,..
     *  to 1st iteration ke baad dist array kuch aisa dikhega..[0,2,-2,0,4]
     * 
     * fir aise hi baaki ke 3 iterations m same oerations perform honge...aur is case m coinicdentally hume har teraion m dit=st array ki same value mi rhi h..
     * 
     * iteration 4 ke baad hume jo dist array mila vahi hamara ans h aur uski index pe jo values h vo source se us index ka shortest path h
     * 
     * outer loop to dekh liya kaise lagate h ..inner edges vaale loop ke liye ya to hum ek edge list bana sakte h saare edges ki using array list, ya jaise abhi tak graph ke liye saare edges nikalte h vaise kar sakte h.. doosre vaale tareeke m inner loop m loop ke andar loop lagega lkn uska matlab ye nai h ki time complexity n^3 ho gya.. vo O(E*V) hi rhti h 
     * to saare edges hum iase nikalenege:-     for(int i=0; i< V; i++)
     *                                              for (int j=0; j<graph[i].size(); j++)
     *                                                  Edge e = graph[i].get(j);    aise hume saare edge mil jaenge..
     * 
     * ye jo edges calculate karne ke liye inner loop m do loop lagae h loop ke andar loop.. uski complexity mila ke O(E) aati h..
     * aur outer loop V-1 times lag rha .. isi liye complexity O(V-1*E)h.. yaani simplify hoke O(V*E) ho jaegiii
     * 
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
      

        graph[0].add(new Edge(0,1,2));     //ye above example vaala graph h
        graph[0].add(new Edge(0,2,4));     

         
        graph[1].add(new Edge(1,2,-4));

        graph[2].add(new Edge(2,3,2));
      
        graph[3].add(new Edge(3,4,4));

       
        graph[4].add(new Edge(4,1,-1)); // is -1 ko -10 kar diya to negative wt cycle vaali condtiotn aajaegi us case m shortest dist is not possible
     
       
      

    }
    //BELLMANFORD:-    TIME COMPLEXITY FOR BELLMANFORD:- O(V*E)  WHICH IS GREATER THAN DIJKSTRAS ALGORITHM
    public static void bellmanFord(ArrayList<Edge> graph[], int V, int src)
    {
        int dist[] = new int[V];
        for(int i=0; i<V; i++)
        {
            if(i!=src)
            {
                dist[i] = Integer.MAX_VALUE;
            }
        }
        for(int k=0; k<V-1; k++)  // is outer loop ki O(V)
        {
            // aur ye andar vaale dono loops ki mila ke O(E) hoti h kyu ki no. of edges usually ek graph ke liye no. of erteces se jaada hi hote h...  aise nai samaghna ki 3 loops lagae h to inki n^3 ho gai hogi poore ki
            for(int i = 0; i<V; i++)
            {
                for(int j=0; j<graph[i].size(); j++)
                {
                    Edge e = graph[i].get(j);
                    int u = e.src;
                    int v = e.dest;
                    if(dist[u]!=Integer.MAX_VALUE && dist[v]> dist[u]+e.wt)    // relaxation perforem karenge lkn relaxation m ek extra condtion lagai h  ye condtiotion isi liye lagai h kyu ki java m agar max integer value m kuch aur add kiya to value negatiive yaani chotti ho jaati h jab ki real m infinity m kuch add karo to aur bari value yaani infinity hi rhta h,,,, aur isi condtion ki vagah se hame startig point ,milega sb se phle ..kyuki src vaale index pe hi infinity nai h abs
                    {
                        dist[v] = dist[u] + e.wt;
                    }

                    
                }
            }
        }
        //agar ye V-1 vaale loop ke andar vaale code ko ek baar aur likhe to usse hum negative wt cycle detect kar sakte h jiske baare m neeche baat ki h 

        // detect -ve wt cycle
        for(int i = 0; i<V; i++)
        {
            for(int j=0; j<graph[i].size(); j++)
            {
                Edge e = graph[i].get(j);
                int u = e.src;
                int v = e.dest;
                if(dist[u]!=Integer.MAX_VALUE && dist[v]> dist[u]+e.wt)    // relaxation perforem karenge lkn relaxation m ek extra condtion lagai h  ye condtiotion isi liye lagai h kyu ki java m agar max integer value m kuch aur add kiya to value negatiive yaani chotti ho jaati h jab ki real m infinity m kuch add karo to aur bari value yaani infinity hi rhta h,,,, aur isi condtion ki vagah se hame startig point ,milega sb se phle ..kyuki src vaale index pe hi infinity nai h abs
                {
                    System.out.println("negative wt. cycle");  // yaani ek bhi baar agar ab relaxation vaali condition true ho gai to negative wt cycle h graph m and we can not find the shortest path
                }

                
            }
        }




        for(int i=0; i<V; i++)
        {
            System.out.print(dist[i]+" ");
        }
        System.out.println();
    }
    public static void main(String args[] )
    {
        int V=5;
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);
        bellmanFord(graph, V, 1);

        
    }
}
// IMPORTANT:-   bellman ford bhi ek case m aake fail ho jaata h usko discuuss kar lete h:-

/*
 * bellman ford algorithm does not work for NEGATIVE WEIGHT CYCLES
 * NEGATIVE WEIGHT CYCLE...yani koi graph diya h .. edges are like this ..  A se B with wt a  B se C with wt b and C se A with wt c
 * 
 * to agar a+b+c <0..then this is a negative wt cycle
 * negative wt cycle vaale case m bellmanford work nai karta infact agar negative wt cycle h to aise graph m shortest dist from source nai nikalte hum kyu ki sense nai banta
 * agar bellman ford work karta h sahi se kisi graph m to V-1 iterations baad hume shortest distance mil chuka hota h..yaani V-1 ke baaad agar hum fir se ek baar relaxation karne ki  koshish kare har node pe to nai hoga relaxation kyu ki hume mila hua h shortest dist..
 * lkn negative wt cycles vaale graph m V-1 ke baad bhi relaxation vaali condtion milti rahegi aur distance aur negative(yaani chotte hote rhengee khatam hi nai hoga process) hote rhenge relaxation ke baad
 * to ye negative wt cycle ko detect karne ke liye hume simple V-1 iterations ke baad ek baar aur check kar lena h ki agar relaxation perform ho rha h to negative wt cycle aagai h aur ab shortest ist calculate karna is not possible
 * 
 * 
 * REVISE LEETCODE CHEAPEST FLIGHTS WITHIN k STOPS QUESTION
 */