import java.util.*;
public class Graphs9
{
    /*
     * ARTICULATION POINTS:-
     * 
     * Articulation point is that vertex in an unidrected graph if removing it (and edges through it) disconnects the grah yani graph ke connected components ka number bar jaega 
     * 
     * it is similar to the oncept of bridges
     * for example we are gven a graph
     * 
     *     1---0---3----4
     *     |   |
     *     2----
     * 
     * in the above graph  0 and 3 are articulation points
     * 
     * articulation points ke liye hum tarjans algorithm ka use karenge aur usme apply karenge ANCESTORS  ka concept
     * 
     * Ancestor-  a node A that was discovered before curr node in DFS,  is the ancestor of curr node 
     * yaani curr node se phle..aur ye phle ho sakte h directly phle ho ya fir bhot saare nodes phle ho.. to agar curr node se phle humne kisi nde ko trvaerse kar liya h to vo node is curr node ka ancestor hogii
     * bridges ki tarah yha bhi ek discovery time array banaenge aur ek lowest discovery time aala array banaenge
     * 
     * yaani kisi node ke liye doosri node pe jaane ka rasta h aur dono m se jiska discovery time chotta hoga vo doosre node ka ancestor hoga 
     * 
     * hum DFS ka use karenge isi liye articulation points find karne ke liye jo function likhenge uskki time complexity O(V+E) h
     * 
     * koi node articulation point sirf 2 hi condition m hoti h 
     * 
     * 1) node corner node ho yaani uske liye parent ki value -1 ho(yaani dfs ka starting point ho ) aur uske number of disconnected childre ki value is > 1 to ye node articulation point hoti 
     * disconnected children matlab agar ek koi node h A aur uske 2 chldren h B and C.. aur B and C m na to koi direct connection h aur na hi kooi indirect connecion h bas ek hi connection h parent node A ke through.. to us case m A ke dono children aredisconnected children 
     * 
     * 2) agar node corner node nai h to ....to 2 condition hoti h 
     * 
     * 1.  ki koi do node h u and v....       ----u----v---
     *                                           /      \  
     * 
     * aur u se v jaane ka bas ekhi rasta h u ke through .. yaani bridge vaali condition ki  u se v bas ek hi rasta h u ke through aur koi back edge na ho to us case m u ek articulation point hoga 
     * 
     * aur agar koi back edge hua yaani u ke left side vaale graph se koi edge connect hota ho v ke right side vaale graph se us case m us back edge ke through v pe jaaya jaa sakta h ..us case m u ko lagega ki v uska children h lkn v ko us back edge ke through koi aur node phle visit kar chuki hogi to v hoga u ka ancestor.. to is case m u is not an articulation point 
     * 
     * 2.  ki u node se aage jaa rhe h aur ek cycle form ho rhi h aur vo cycle u se shuru ho rhi h to us case m u node ek articulation point hoga 
     *    
     *      ---u----v-----N1---
     *         |          |
     *         ---------  N2    to is case m agar u ko hata diya aur u se nikalne vaale edges ko hata diya to graph ke do tukre ho jaenge 
     * jab bridge hota h u se v ke beech m us case m dt[u]< low[v]....
     * 
     * aur jab cycle hoti h u se shuru... to dekhte h dt[] aur low[] m kya relation hoyta h...upar jo cycle dikhai h humne usme u se aage jaenge v pe .. ve se N1 pe(yaani v se u jaa sakte h lkn parent h u to ignore kar diya ) fir N2 pe..aur N2 se sirf u pe jaa sakte h lkn u already visited h..yaani ye vo condition aagai jaha sirf hum apna low ko update karte h..yaani low[N2] = min(low[N2], dt[u])...(is vaali condition m humne bridges ke time para tha ki neighbour ka dt[] lete h comparison ke liye )
     * fir back track kar ke vapas N1 pe ayenge aur ab low[N1] = min(low[n1], low[N2]) yaani low[n1] = low[n2] ho jaega..fir backtrack kar ke v pe aayenge fir se low[v] update karenge aur vo n1 aur n2 vaala ho jaega...yaani is poori cycle m  ko chor ke saari nodes ka low[]  u ke dt[] ke equal ho gya...that is the condition of cycle...
     * 
     * to upar vaali dono contdition bridge aur cycle vaale hum ek hi relation se recognise kar sakte h...dt[u] <= low[v]   agar less than h to yaani bridge ..equal h to cycle ..dono hi condition m u articulation point hoga 
     * 
     * 
     *                  
     * kisi ek node ke liye uske neighbours ko dekhe to 3 conditions hoti h 
     * 1. ki vo neighbour uska parent ho    yaani is case m articulation point ho bhi sakta h ani bhi ho sakta to hum is case ko ignore kar dete h 
     * 
     * 2. ki vo neighbour already visit ho chuka ho ....yaani kisi aur node ke through ye node visit ho chukki h ..this is called backedge ..yaani is time neighbour hamare curr ka children nai h balki ancestor h ..yaani is case m curr node articulation point ho hi nai sakti ..bas bridge ki tarah low [curr] ko update kar ke low[curr]=min(low[curr], dt[neighbour])     IMPORTANT  ye vahi vaala step h jaise bridge m tha jisme low ko update karne ke liye neighbour ka dt []  use karte h 
     * 
     * 3. vo neighbour visit nai hua ho...isi ko child node bolte h..aur aise dekh ke jitne bhi children aayenge curr ke vo saare disconnected children hi honge..kyu ki saare connected children 2nd step discard kar dega  ..yaani yaha ho sakta h curr node articulation point ho 
     * is case m neighbour ke liye call kar denge dfs ko ..fir bactrack kar ke vapas aayenge to low[curr] = min(low[curr], low[neighbour])...aur fir articulation point vaali condition check kar lenge 
     * 
     * 
     * 
     * code for tarjans algo to find articulation points goes here:-   O(V+E)
     * 
     * 
     */

     static class Edge
     {
         int src;
         int dest;
         
 
        
         public Edge(int s, int d )  
         {
             this.src=s;
             this.dest=d;
             
 
         }
 
     }
     public static void createGraph(ArrayList<Edge>graph[])
     {
    
         
 
         for(int i=0; i<graph.length; i++) 
         {
             graph[i] = new ArrayList<Edge>();
         }
       
         graph[0].add(new Edge(0,1));
         graph[0].add(new Edge(0,2));
         graph[0].add(new Edge(0,3));  
 
         graph[1].add(new Edge(1,0));    //ye upar vaale example vaala graph h  
         graph[1].add(new Edge(1,2));
 
         graph[2].add(new Edge(2,0));
         graph[2].add(new Edge(2,1));
         
         graph[3].add(new Edge(3,0));
         graph[3].add(new Edge(3,4));
         
         graph[4].add(new Edge(4,3));
        
       
     } 
     // articulation points O(V+E)

     public static void dfs(ArrayList<Edge> graph[], int curr, int par, int dt[], int low[], boolean vis[],  int time, boolean ap[])
     {
        vis[curr] = true;
        dt[curr] = low[curr]=++time;
        int children = 0;

        for(int i=0; i<graph[curr].size(); i++)
        {
            Edge e = graph[curr].get(i);
            int neigh = e.dest;
            if(par == neigh)   //kuvh na karne vaala case
            {
                continue;
            }
            else if(vis[neigh])  // khaalo low ko update karne vaala case jisme articulation point nai ho sakta
            {
                low[curr] = Math.min(low[curr], dt[neigh]);
            }

            else{   // jis case m articulation point vaali condition check karte h 
                dfs(graph, neigh, curr, dt, low, vis, time,ap);
                low[curr] = Math.min(low[curr], low[neigh]);

                //check condition of articulation poin
                if(dt[curr]<=low[neigh] && par !=-1)   // yaani ye vo vaali condition h jisme curr corner node nai h .isme hum bridge aur cycle vaali condition dekhte h 
                {
                    ap[curr]=true;
                }
                children ++;   // children ko update is else vali condition m karaya h kyu ki hume us case m jisme parent -1 hota h us case m articulation point ke liye vo children chahiye hota h jo disconnected ho..agr unka number is greater than 1..then jis node se dfs start kiya h vo bhi ek articulation point h 


            }


        }
        if (par == -1 && children >1)
        {
            ap[curr] = true;
        }
     }
     public static void getAP(ArrayList<Edge> graph[], int V)
     {
        int dt[] = new int[V];
        int low[] = new int[V];
        int time = 0;
        boolean vis[] = new boolean[V];
        boolean ap[] = new boolean[V];   // ye articulation points track karegi ki konsa node aarticulation point h konsa nai h 

        for (int i=0; i<V; i++)
        {
            if(!vis[i])
            {
                dfs(graph, i, -1, dt, low, vis, time, ap );
            }
        }

        //printing artiulation points

        for(int i=0; i<V; i++)
        {
            if(ap[i])
            {
                System.out.print(i+" ");
            }
        }
        System.out.println();
     }
    public static void main(String args[])
    {
        int V = 5;
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);
        getAP(graph, V);
        
    }
}


// IN TARJANS ALGO ...WHY LOW[CURR] = MIN(LOW[CURR], DT[NEIGH])  WHEN NEIGH IS ALREADY VISITED 

/*
 * AGAR US CASE M NEIGHBUR KA DISCOVERY TIME KI JAGAH LOWEST DISCOVERY TIME LE LIYA TO JO HUM BRIDGES KI AUR ARTICULATION POINT KI CONDITIONS LAGATE H VO JAHA ACTUALLY SATISFY HONI CHAHIYE AUR HUME BRIDGE/ARTICULATION PPOINT MILNA CHAHIYE VO NAI MILTA..
 * ISI LIYE YE GALAT H AUR HUM US CASE CURR KA LOW COMPARE KARTE H NEIGHBOUR KE DISCOVERY TIME SE NAAKI LOWEST DISCOVERY TIME SE
 */