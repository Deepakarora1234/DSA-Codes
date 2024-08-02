import java.util.*;
public class Graphs8
{
    /*
     * Bridge in graphs
     * 
     * bridge is an edge whose deletion increases th grpah's number of connected components
     * 
     * for eg ek graph diya h:-
     * 
     *    1-----0-----3-----4
     *    |     |
     *    2------
     * 
     * ab is graph m agar 1 se 2 vaala edge delete kar diya to humare connected components increase nai honge as 
     * abhi connected components ek h poora graph yaani aur 1 se 2 vaala edge delete karne pe bhi ek hi conneced component rahega ..1 se 0 0 se 3 3 se 4 aur 0 se 2 ...aise kar ke graph poora connected rahega no. of connected components 1 rahenge
     * 
     * ab agar 1 se 0 vaala edge delete kar de tab bhi conneted components same rahenge yaani 1 poora graph ..1 2 0 3 4 vaale path se sab connected rahenge
     * 
     * lkn agar 0 se 3 vaala edge remove kar diya to 2 connected components mil jaenge ..ek ho jaega 1 2 0 vaala component aur ek 3 4 vaala component ...to yaani 0 se 3 vaala edge ek bridge h..
     * 
     * aise hi 3 se 4 vaala edge bhi ek bridge hoga..yaani ek graph m multiple bridges ho sakte h
     * 
     * hum bridges TARJAN'S ALGORITHM ko use kar ke find karte h..is algorithm ke aur bhi bohot use h like finding articulation points, strongly connected components, topological sort ....to iska concept apne aap m important h bohot
     * tarjans algorithm ko use karne keliye hum ek discovery time ka concept use karte h 
     * hum 2 arrays banate h ..ek hota discovery time array jiska size V ke equalhota  aur ek lowest discoevrytime ka use karte h to uske liye low naam ka array bana lete h of size V jisme hum store karate h lowest discovery time of all neighbours
     * lets take a graph:-
     * 
     *   1-------0------3-------5
     *   |       |      |       |
     *   2-------       4--------    to is graph m ek hi bridge h vo h 0 se 3 vaala edge 
     * 
     * tarjans algorithm dfs ke upar based h..
     * 
     * hum apne dodno array bananenge.. ek discoverytime vaala dt[] aur ek lowest discovery time of all neighbours vaala low[]
     * ab dfs agar humne 0 vaali node se shuru kiya bh time t me.. to 0 se 1 pe jaenge t+1 m..1 se 2 pe jaane ke liye ek aur baar +1 ho jaega.. fir backtract kar ke 0 pe aayenge fir 3 pe jaane m fir se ek baar +1..aise karte karte har baar ek node pe jaane m plus one ho jaega time
     * to ek node pe hum phli baar kis time pe phohnch rhe h vo uska discovery time hota h 
     * 
     * doosra hum banaenge low[] jisme kisi node ke liye vo node khud aur uske neighbours ko mila ke lowest discovery time lenge.. for eg
     * agar 1 vaale vertex ki baat kar rhe h .. aur 1 ke neighbours h 0 aur 2... to 1 ke liye 1 ka 0 k aur 2 ka discoverytime dekhenge..inme se jiska lowest hoga usko 1 vaale ke index pe low[] m store karenge
     * 
     * ab ye dekhna h ki ye dono array ko bana ke hum kar kya rahe h..
     *  for eg kisi graph m jo bridge h vo u vaali aur v vaali vertex ke beech ka edge h yaani agar is edge ko hataya to graph break ho jagea 2 seperate connected components m..
     * for eg......
     * 
     *     ------ u-----v-----
     *            |     |      yaani use peeche bhi nodes hongi kuch aur v ke aage bhi nodes hongi kuch..lkn bridge hamra u se v ke beech m ..yaani u vaala graoh aur v vaala graph ke beech m ek ye u se v vaala edge jo h vahi ek link aur koi link nai h..aur koi link hota to fir ye bridge nai hota..
     * 
     * ab un do arrays ko dekh ke pata kaise chalega ki ye bridge h...
     * humne agar agar u vaali vertex ke left se shuru kiya .. to agar v pe jaana h to ek hi tareeka h ki hume u vaali vertex se hoke hi jaana parega as there is bridge between u and v aur vo bridge cross karna hi parega..similarly agar v vaale graph ki kisi node se start kiya to u pe jaane ke loye v vaali vertex se hoke hi jaana parega..
     * 
     * to agar hum apna dt[]  aur low[] fill kar le to bridge between u and va ka pata aise chalega ki low[v] > dt[u]
     * yaani hum kisi tarah se u pe phonche to uska koi discovery time hoga dt[u]...aur low[v] hume bataega ki v aur v ke vo saare neighbours jinka vis false tha phle..unke liye minimum discovery time..
     * 
     * yaani ek tarah se low[v] jo h vo v vertex ar uske right m jitne bhi neighbours h un me se min distcovery time h..
     * aur agar bridge h between u and v.. to ye min time bhi dt[u] se to jaada hi hogaa kyu ki in vaali node pe jaane ke liye u se cross karna hi parega as bridge and u ka kuch time hoga dt[u] aur u se agle pe jaane ke liye time ++ ho jaega..
     * 
     * IMPORTANT: to bridge detection between u and v is dt[u]< low[v]
     * 
     * yaani bridge tab aayega jab u se v jaane ka sirf ek hi tareeka ho through that bridge
     * 
     * to implement karne ke liye hum agar 0 se start karenge to 3 cheeze track karenge .. ek to har vertex ka parent,  ek dt[] aur low[]..
     * shuru m jab hum kisi node pe jaate h to uske liye hamara discoverytme aur lowest discovery time same hota h 
     * jaise shuru m 0 pe gye to 0 aur uske neighbours m se to hume khaali 0 ka time pata h as low[] m kisi node ke liye us node ke neighbours ke saath vo node khud bhi incldue htoi h...to 0 ke liye lowest time discovery time ke equal hoga
     * to initially hum dt[curr]=low[curr]=++time   yaani time vo h jo hume diya h 0 pe jaane se phle..
     * 
     * agar 0 se start kiya to uske liye dt[] aur low[] equal ho jaenge ++ time ke ..yaani time 0 se shuru kiya tha to ye ho jaenge 1 ..aur 0 ka parent h -1..0 ko vis m true kar diya aur neighbours ke liye call laga di..
     * 
     * 1 pe aaye 1 ko vis m true kiya ...1 ke liye parent h 0...aur b bhi 1 ke liye dt[] aur curr[] ho jaenege ++time yaani 2 ke equal..aur neighbours ke liye call lagai..
     * 
     * ek neighbour 0 h lkn vo parent h 1 ka to is case m kuch nai karenge...
     * 
     * doosra neighbour 2 h jo ki vis m false h to iske liye call laga di..
     * 
     * 2 ke liye call lagai aur vis m true kiya...2 ka parent 1 h aur dt[] and low[] ho jaenge ==time yaani 3 ke equa  aur neighbours ke liye call kiya..
     * 
     * 2 ka ek neighbour 1 h lkn vo parent h to kuch ni kiya continue kiya..
     * 
     * doosra neighbour h 0 aur 0 vis m true h... to is case m ab kar denge low[] ko update..
     * 
     * yaani 2 ke liye low[] ho jaega low[curr(yaani 2)]=min(low[curr],dt[neighbour])...yaani 2 ke liye low[] ho jaega 1 ke equal.. IMPORTANT  YE JO COMPARE KIYA H USME MIN OF LOW[curr] AND DT[neighbour] m se min liya h ..low[neighbour] lyu nai liya vo articulation points ke time samghanege
     * 
     * ab 2 ke saare neighbours hogye to backtrack kar ke aaye 1 pe to 1 ke liye 2 neighbour thajo ab visist ho gya uar parent bhi nai h to iske liye bhi low[] update karna parega ...yaani ab low[curr] = min(low[curr],low[neighbour])....yaha neighbour ke liye bhi low[neighbour] aayeaga dt[neighbour] nai...IMPORTANT
     * to 1 ke liye low[] ho jaega 2 ki jagah 1..aise karte karte saare nodes ke liye karenge
     * 
     * ab bridge dekhna h.. to agar jaise jab hum 2 pe aaye the to 2 ke liye 0 ek ais node tha jo already visited tha..to aise case m 2 aur 0 vaala edge kabhi bhi bridge nai ho sakta as 2 ke liye 0 vis h already yaani 0 pe hum kisi aur edge se jaa sakte h(1 se 0  vaalese)...to ye vaale a\case m edge bridge nai ho sakta
     * 
     * 1 ke liye 0 parent tha...is case m 1 aur 0 vaala edge bridge ho bhi sakta h aur nai bhi ho sakta to hum check nai karenge..
     * 
     * 
     * yaani bridge ki condtion hume tab check karni h jab hamara neighbour unvisited hota h aur hum uske liye call karte h fir vapas aake low[curr] = min(low[curr],low[neighbour]) karte h uske baad bridge aali condtion dekhni h 
     * bridge ki condtion..dt[u]<low[v] aur yaha pe u hoga source yaani curr   and v hoga neighbour
     * 
     * to yaani aise implement hoga tarjans algo for finding bridge:-
     * 
     * vis[curr] = true;
     * dt[curr]=low[curr]==++time;
     * for int i=0 to neighbours
     * Edge e
     * 
     * 3 condtions possible for the neighbour
     * 1) where neighbour is parent---continue karenge is case m
     * 
     * 2) !vis[e.dest]:-   yaani neighbour unvisited h to phle call lagaenge neighbour ke liye
     *    call for e.dest
     *    aur call ke baad vapas aane ke baadd low[] update karenge 
     *    low[curr] = min(low[curr], low[e.dest])
     *    fir bridge ki condition check karenge
     *    if(dt[curr]<low[e.dest])
     *     print(curr+e.dest)
     * 
     * 3) vis[e.dest]    yaani neighbour parent nai h aur vis h jaise 2 ke liye 0 tha above...to yaha bridge nai ho sakta yaha check nai karenge bas low[] update kar denga
     *   yaha khaali low ko update karenge aur is baar dhyaan rakhna h low ko update m neighbour ke liye dt[] use karenhe..
     *   low[curr] = min(low[curr], dt[e.dest])   IMPORTANT
     * 
     * 
     * ye 3 steps m perform hoga 
     * code for bridg detection using tarjans algo goes here:-
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
        graph[3].add(new Edge(3,5));

        graph[4].add(new Edge(4,3));
        graph[4].add(new Edge(4,5));

        graph[5].add(new Edge(5,3));
        graph[5].add(new Edge(5,4));

      
    } 
    //tarjans algo for getting bridges:-

    public static void DFS(ArrayList<Edge> graph[], int curr, boolean vis[], int dt[], int low[], int time, int par)
    {
        vis[curr] = true;
        dt[curr] = low[curr] = ++time;
        for(int i=0; i<graph[curr].size(); i++)
        {
            Edge e = graph[curr].get(i);
            if(e.dest == par)
            {
                continue;   // ye break continue vaala continue h :)
            }
            else if(!vis[e.dest])
            {
                DFS(graph, e.dest, vis, dt,low,time,curr);
                low[curr] = Math.min(low[curr], low[e.dest]); // low ko update vaala step

                if(dt[curr]<low[e.dest]) // bridge detection vaali condition
                {
                    System.out.println(curr+"---"+e.dest);
                }
                
            }
            else
            {
                low[curr] = Math.min(low[curr], dt[e.dest]);  // ye vo vaala case h jisme neighbour already visited hota h..humne upar par liya ki is case m bridge ni ho sakta aur khaali low ko update karte h is case m aur ume bhi neighbour ke liye dt[] use karte h low[] use nai karte 
            }

        }
    }

    public static void getBridge( ArrayList<Edge> graph[] , int V)
    {
        int dt[] = new int[V];
        int low[] = new int[V];
        int time = 0;
        boolean vis[] = new boolean[V];

        for(int i=0; i<V; i++)
        {
            if(!vis[i])
            {
                DFS(graph, i, vis,dt, low,time, -1 );
            }
        }
    }
    public static void main(String args[])
    {
        int V = 6;
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);
        getBridge(graph, V);
    }
}