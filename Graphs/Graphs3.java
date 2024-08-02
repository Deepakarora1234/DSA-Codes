import java.util.*;
public class Graphs3  // cycle detection for directed and undirected graph and topological sorted oreder
{
    /*CYCLE DETECTION

     * here we will see about cycle detection...ki graph m cycle h ki nai
     * cycle detection ke liye modified dfs ka use karenge ,...lkn directed aur undiirected graphs ke liye alag alag approach hogi..
     * abhi jo humne dfs para tha vo undirected graph ke liye tha...directed ke liye modify karna parega
     * 
     * ab dekhte h undirected dfs vaali approach cycle nikalne ke liye directed graph ke liye fail kaise hogi..
     * 
     * ek undirected graph le lete h...       1---0---2
     *                                             \  /
     *                                               3
     * yaha hune dfs ke liye starting point 0 liya..aur 0 ko starting point humne main function se call lagate time banaya tha to 0 ka parent abhi koi nai h to -1 maan liya..0 se 1,2 3 pe ja sakte h..hum 1 pe gye ...1 ko vis array m true kar diya..aur 1 se ab kahi nai jaa sakte to 0 pe vapas aagya
     * ab 0 se 2 pe gye ..2 ko vis array m true kiya aur dekha ki 2 se 0 pe jaa sakte h lkn 0 to 2 ka parent h aur visited bhi h.. to 2 se 3 pe gye..
     * 3 ko vis array m true kiya..aur 3 ke liye dekha..3 se 2 pe ja sakte h lkn 2   3 ke liye parent bhi h aur visited bhi h.. to 3 se 0  ke liye dekha.. ab 0 visited  to h lkn parent nai h... to ye hamari cycle vaali condition h aur yaha se 3 true return kar ke dega 2 ko aur 2 true dega 0 ko aur 0 true dega main ko..
     * 
     * ab agar yahi parent aur visited vaali approach directed graph ke liye lagaenge to fail ho jaegi lets see how..
     * 
     * ek directed graph le lete h--    1-->0<--2
     *                                      ^   ^
     *                                      |   |
     *                                      3----    yaani arrow 3 se 2 aur 0 pe h..
     * ab yaha 0 se start kiya aur dekha ki 0 se kahi nai jaa sakte h.... to khaali 0 ko vis array m true kar diya
     * 0 ke baad 1 ko start banaya aur dekha ki 1 se khaali 0 pe jjaa sakte h..aur 0 already visited h.. lkn kyu ki 1 ko humne main vaale loop se start bana ke bheja tha to iska koi bhi parent ni h to parent -1 maan liya
     * ab yaani 1 se 0 pe ja sakte h aur 0 visited bhi h aur 1 ka parent bhi nai h.. to ye to cycle vaali condition aagai..aur hume true mil jaega.. lkn agar above  directed graph dekhe to usme cycle hai hi nai aur hume true mil rha h..
     * to undirected dfs vaali approach fail ho jaegi directed graph vaale case m .. to directed graphke liye hum recursive stack naam ka array vaali approach follow karenge
     * 
     * modified dfs approach for directed graph..
     * 
     *                1---> 0<-3  
     *                      |  ^
     *                      |  |
     *                      -->2
     * eg ke liye upar vaala directed grpah le lete h... 0 se start kiya to recursion stack m 0 aagya aur 0 ko vis array m true kar idya..
     * ab agar 0 se 2 pe gye to recursion stack m 2 aagya aur 2 bhi vis array m true ho gya..
     * ab 2 se 3 pe gye recusrion stack m 3 aagya aur vis array m true hp gya ..
     *  3 se 0 pe jaa sakte h lkn 0 already visited h aur hamare recursion stack m h.. to ye h cycle ki condition ..yaha se 3 true retur karega 2 ko .. 2 true return karega 0 ko aur 0 true return karega main ko..
     * aur ye recurison stack implicit vaala h jo ki memory m apne aap banta h isme hum optimized search nai kar paenge as satck m O(n) hota h search ka..
     * to hum recusrion stack naam ka array banaenge visited array se aur usme track rakhenge ki kon kon h recusrive stack m..
     * 
     * following is the approach for cycle detection in directed graph..
     * 
     * graph,  vis array ke saath ek boolean type ka recursion stack arrya bhi hoga paramteres m
     * to shuru m aate hii curr ke liye visited ke saath recursion stack vaale array m bhi curr ke liye true karna parega..
     * fir curr ke saare ke saare neighbours ke liye loop lagaenge
     * aur curr ka aisa neighbour mil gya jo laready recursion stack m exist karta h to yaani cycle mil gai.. ye h cycle exist hone ki condition... to yaha hum true return kar denge
     * aur agar neighbour recursion stack array  m nai h to uske liye else if m vis array agar true nai h to hum  recursive call laga denge vapas dfs ko
     * aur is call ke baad curr ko resursion stack ke liye ttrue kar denge..yaani agar 0 ne call lagai 2 ke liye to yaani 0 recusrtion stck m add ho gya.. to 0 ke liye stack array m  treu kar denge
     * 
     * IMPORTANT STEP:- aur ab jab saare neighbours visit ho gye honge curr ke liye.. yaani agar 2 kee saare neighbours visit ho gye,, to fir vapas jaate jate 2 recusrion stack se hat gya hoga memory vale .. to uske liye hume stack array m bhi false karna parega..
     * 
     * to above directed graph ke liye cycle detetction code goes here:-
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
      

        // graph[0].add(new Edge(0,2));
        

        // graph[1].add(new Edge(1,0));  
       
        // graph[2].add(new Edge(2,3));

        

        // graph[2].add(new Edge(2,3));  // ye topological sort vaala graph ..
       
        //  graph[3].add(new Edge(3,1));
        

        // graph[4].add(new Edge(4,0));
        // graph[4].add(new Edge(4,1));  
       
        // graph[2].add(new Edge(2,3));

        

        // graph[5].add(new Edge(5,0));
        // graph[5].add(new Edge(5,2));

           graph[0].add(new Edge(0,1));
           graph[0].add(new Edge(0,4));   //ye undirected graph ke liye cycle dtection vaale explanation m jo graph h vo hs
        

        graph[1].add(new Edge(1,0));  
        graph[1].add(new Edge(1,2));  
        graph[1].add(new Edge(1,4));  
       
        graph[2].add(new Edge(2,1));
        graph[2].add(new Edge(2,3));

        graph[3].add(new Edge(3,2));

        graph[4].add(new Edge(4,0));
        graph[4].add(new Edge(4,1));
        graph[4].add(new Edge(4,5));

        graph[5].add(new Edge(5,4));

       


    }

    //CYCLE DETECTION FUNCTION   IMPORTANT-- IS FUNCTION KI TIME COMPLEXITY BHI DFS KE EQUAL H YAANI O(E+V)
    public static boolean isCycleDirected(ArrayList<Edge> graph[], boolean vis[], boolean rec[], int curr)
    {
        vis[curr]=true;
        rec[curr]=true; //  vis m true karne ke saath hi recusrion stack m true kar diya curr ko

        // ab curr ke saare neighbours ke liye loop laga lenge

        for(int i=0; i< graph[curr].size(); i++)
        {
            Edge e = graph[curr].get(i);
            if(rec[e.dest])
            {
                return true; // recursion stack arrray m e.dest ka true hona is cycle detection vaali condition

            }
            else if(!vis[e.dest]) // yaani neighbour agar visited nai h to uske liye recursive function call lagange..yaani curr se uske neighbour pe jaa rhe h
            {
                if(isCycleDirected(graph, vis, rec, e.dest))
                {
                    return true;  // yaani agar neighbour unvisited h  to hune uske liye call laga di aur agar vaha se true return hua to hume yaha bhi true return karna h
                }
            }

        }
        rec[curr]=false;  //IMPORTANT STEP
        return false;  // yaani agar upar se cycle detect nai hoti to recusrin stack array m se sab hat te jae aur hume false return ho jae

    }

    /*
     * TOPOLOGICAL SORTING:-
     * topological sorting is possible only for DIRECTED ACYCLIC GRAPHS(DAG)  yaani directed matlab graph directed hona chahiye aur acyclic mat koi cycle nai honi chchiaye us directed graph m
     * 
     * TOPOLOGICAL SORTING means a linear order of vertices such that every directed edge u-->v,  the vertex u comes before v in the order
     * 
     * aasan language m topological sorting ek graph ko linear fashion m likhne ka tareeka taaki har u se v vaale edge ke liye u phle aaye aur v baad m .. for eg:-
     * 
     * ye graph diya h--         a---->b
     *                           |     |
     *                           -->c<--
     *                              |
     *                              -->d
     * is graph m kuch is tarah ke edges h    a-->b, a-->c,  b-->c,   c-->d
     * to agar linear order m likhna h in edges ko .. for eg ek linear order ho gya abcd, ek ho gya cbad, etc etc,,
     * 
     * to topological sort khta h ki a-->b vaale edge ke liye b se phle a aana chchiye..a se c vaale ke liye bhi c se phle a aana chchiye.. b se c vaale keliye c se phle b aana chahiye aur c se d vaale ke liye d se phle c aaana chahiye..
     * to topological sorted order is graph ka hoga = abcd  aur ye linear order of vertices h aur ye batata h ki graph m konsa node phle aata h yaani phle a likha h to hume pata h ki a aayega tab hi hum b  pe pohoch paenge c pe pohonch paenge
     * 
     * 
     * topological sorting ka kaam h ki ye dependency show karta h ek action ki doosre pe.. for eg
     * action 1-buy laptop,  action 2-install OS, action 3- install code editor, action 4-  install java, action 5- write code..
     * ab in saare actions ko graph ki form m likhe to    1
     *                                                    |
     *                                                  --2----
     *                                                  |      |
     *                                                  ->3  4<-
     *                                                    |   |
     *                                                    ->5<- 
     * yaani action 2 ke liye phle action 1 (buy laptop) hona chchiaye..action 4 (install java ) ke liye action 3 ke hone ki jaroorat nai h.. etc... to ye edges is time dependency show kar rhe h...
     * to above graph keliye topological sort ho jaega 1 2 3 4 5   aur 1 2 4 3 5 bhi shi ho sakta h kyu ki 3 (install code editor) aur 4(install java) inme se koi sa bhi phle aa sakta h dono theek honge
     * ye dependency between nodes ke liye we use topological sort
     * 
     * ek aur baat pata chali ki kisi ek graph ke do teen topological sorted orders bhi aasakte h
     * 
     * IMPLEMENTATION :- topological sorting ko hum dfs se implment karte h
     * 
     * topological sort hum modified dfs ko use kar ke karenge...isme vaise to code almost same rahega,,bas ek stack ko use karenge for its lifo property..
     * 
     * lets say ye graph diya h......   5--->0<-----4
     *                                  |           |
     *                                  -->2-->3->1<-
     * iske liye  kahi saare topological sorted orders ho sakte h ... hume khaali ek chahiye.. vo ek ye ho sakta h..5 4 2 3 1 0
     * yaani hume kisi bhi curr node ke liye phle uske neighbours ko call laga ke unhe stack m add karenge fir  curr ko last m add karenge taaki sabse phle curr access kar pae
     * 
     * yaani hum koshish kar rhe h ki 5 ke baad jitne bhi elements aate h vo 5 se phle store ho jae last m 5 store ho..
     * above graph ke liye ek vis array le lenge aur ek stack bana lenge..
     * ab ek loop lagaenge main m 0 se V tak ..  aur jaha vis[i] false hua uske liye topologocal sort function ko  cal karenge..
     * basic dfs ka code vaali cheezon m thora sa modification h..calll laga ke curr ke liye vis ko true karenge.. fir uske neighbours ke liye loop lagaenge aur jo bhi neighbour univisited h uske liye toplogicalsort ko call kar denge aur last m change ye h ki curr ko stack m add kar denge...
     * 
     * pseudocode-- topologicalSort(graph, vis, stack, curr)
     *                  {
     *                      vis[curr]=true;
     *                      for(i=0; i<graph[curr].size(); i++)
     *                  {
     *                          Edge e = graph[curr].get();
     *                      if(!vis[e.dest])
     *                      {
     *                              topologicalSort(graph, vis,stack, e.dest)
     *                      }
     *                  }
     *                  stack.push(curr);
     *                  }
     * dry run kar ke dekhte h ab--
     * phle 0 ke liye call lagyaa..0 ko vis m true kiya.. 0 ka koi neighbour nai h to last step m 0 ko stack m add kar diya..
     * fir 1 ke liye call lagya .. 1 ko vis m true kar diya.. aur 1 ka bhi koi neighbour nai h to last m 1 ko stack m add kar diya..
     * fir 2 ke liye call lagaya.. 2 ka vis true kiya.. 2 ka ek neighbour h 3 aur 3 vis m false h to 3 ke liye call kar idya..
     * 3 ko vis m true.. fir 3 ka neighbour dekha .. 1 h lkn 1 vis m true h to kuch nai kiya.. aur koi neighbour nai h to last m 3 ko stack m add kar diya aur bhar aagye..
     * fir last step m 2 ko add kiya stack m..
     * fir 3 ke liye call nai lagegi ab loop m se kyu ki 3 ke liye vus true h..aur is time stack m down to up 0 1 3 2 aisa kuch h..
     * fir 4 ke liye call lagaii.. 4ko vis m true kiya.. 4 ka ek nieghbour h 0 joki vis m true h.. to chor diya aur last step m 4 ko stack m add kar diya..
     *  fir 5 ke liye call lagai.. 5 ko vis m true kiya aur 5 ke nighbour 2 h jo ki vis m true h to chor diya aur 5 ko stack m add kar diya..
     * ab finally stack m hamare pass bottom to up kuch is tarah h elements.. 0 1 3 2 4 5..
     * ab agar pop kar ke print karae to 5 4 2 3 1 0 milega jo ki one of the topological sorted ordres h 
     * 
     * topological sort ke liye code goes here-
     * 
     * topologicl sort ke liye example jo liya h uska graph banaenge phle..
     * 
     */

    // TOPOLOGICAL SORT---IMPORTANT--TOPOLOGICALSORT FUNCTION KI TIME COMPLEXITY=O(V+E) ..THAT MEAN EQUAL TP DFS
     public static void topSortUtil(ArrayList<Edge> graph[], boolean vis[], Stack<Integer> stack, int curr)
     {
        vis[curr]=true;
        for(int i=0; i<graph[curr].size(); i++)
        {
            Edge e = graph[curr].get(i); 
            if(!vis[e.dest])
            {
                topSortUtil(graph,vis, stack, e.dest);
            }
        }
        stack.push(curr);
     }
     public static void topSort(ArrayList<Edge> graph[],int V)  //  yaha is function m kuch nai kiya bas stack banana aur loop chala ke topSortUtil function ko call lagane vaala kaam aur stack m se elements pop kara ke topological sorted order print karane vaala kaam is functio se kara liyaa..
     {
        boolean vis[] = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<V; i++)
        {
            if(!vis[i])
            {
                topSortUtil(graph, vis, stack, i);
            }
        }
        while(!stack.isEmpty())
        {
            System.out.print(stack.pop()+" ");
        }
     }

     /*
      * CYCLE DETECTION FOR UNDIRECTED GRAPHS:-

      * unidrected graphs ke liye bhi modified dfs lagaenge..
      cycle milne ki condition ye hogi ki kisi curr node ke liye uska koi neighbour node ka vis m already true ho aur vo neighbour curr ka parent na ho to yaani cycle mil gai..
      kisi curr node ke liye 3 tarah ke neighbour exist karte h:-
      1. jiska vis array m true ho aur vo curr ka parent na ho.. yaani cycle vaali condition..is case m true return karana h
      2) jiska vis array m true ho lkn vo curr ka parent ho.. us case m do nothing
      3) jiska vis array m false ho ..is case m is neighour ke liye recursive function call laga do...aur agar isne true return kiya to curr ke liye bhi true return kar denge
        for eg ye ek undirected gra[h le lete h:-     0---1---2--3
                                                       \ /
                                                        4---5
        0 se start karenge.. abhi 0 ka koi parent nai h to -1 le lia... 0 ko visit karaya aur neighbours ke liye call kar diya..
        man lete h 0 se1 pe gye.. 1 ko visit karaya.. 1 ke neighbours ko calll kiyaa
        1 se 2 pe gye.2 ko visist karaya..1 ko 2 ka parent banaya.. aur 2 ke nieghbours ke liye call kar diyaa..
        2 se 3 pe gye aur 3 ko visit karaya ..2 ko 3 ka parent banaya ...ab 3 ke ngihbours nai h to back track karte karte vapas 2 fir vapas 1 pe aagye..
         fir 1 ke doosre neighbour pe gye.. 4 ko visit karaya..1 ko 4 ka parent bana diyaa..
         ab 4  je 3 type ke neighbours h.. ek 0 h jo visited h aur 4 ka parent bhi nai h.yaani cycle vaali condtion..
         ej 1 h jo visisted h aru parent h 4 ka .. do nothing vaali condition
         aur ek 5 h jo visited nai h.. yaani 5 k liye recursive function call vali condtion...lkn kyu ki 4 ka neighbour 0 mil gya h jisme cycle  vaali condtion h to true return kar dega 1 ko.. 1 trure return kar dega 0 ko aru 0 true return kar dega main ko
          
         ab iske code m dfs m graph[] aur vis[] ke saath ek parent vaala parameter aur aajaega..

         cycle detection for undirected graph code goes here:-

      */
     //cycle detection for undirected graph;-   IMPORTANT- TIME COMPLEXITY OF THIS FUNCTION IS O(V+E) ..SAME AS DFS

     public static boolean isCycleUndirected(ArrayList<Edge> graph[], boolean vis[],int curr, int parent)
     {
        vis[curr]=true;
        for(int i=0; i<graph[curr].size(); i++)
        {
            Edge e = graph[curr].get(i);
            if(vis[e.dest]==true && e.dest!=parent)
            {
                return true;
            }
            else if(!vis[e.dest])
            {
                if(isCycleUndirected(graph, vis, e.dest, curr)) 
                {
                    return true;     // yaani agar neighbour ke liye recursive function call lagane pe true mile to curr bhi true return kare
                }
            }
        }
        return false; // yaani upar vaale part se cycle nai mili to matlab cycle ni h aur false return kar diya
     }

    public static void main(String args[])
    {
        // int V=4;
        int V = 6;

        ArrayList<Edge> graph[]=new ArrayList[V]; 
        createGraph(graph);
        // boolean vis[] = new boolean[V];
        // boolean rec[] = new boolean[V];
        // for(int i=0; i<V; i++)  // vahi jaise bfs aur dfs m disconnected vaala concept ko tackle karne ke liye loop lagate h vaise hi kiya h
        // {
        //     if(vis[i]==false)
        //     {
        //         boolean isCycle = isCycleDirected(graph, vis, rec, 0);
        //        if(isCycle)
        //        {
        //         System.out.println( isCycle);  // ye ais is liye kiya h taaki cycle agar do tukro m ho to do baar true print na ho..aur cycle nai h to kuch bhi print nai hoga 
        //        }
               
        //     }
        // }

        //topologicalSort-

        // topSort(graph, V);

        //cycle detection for undirected graph
        boolean vis[] = new boolean[V];

        System.out.println(isCycleUndirected(graph,vis,0, -1));
       
    }
}

//IMPORTANT NOTE..AFTER THIS ..REVISE LEETCODE - Course Schedule QUESTION AND Find Eventual Safe States QUESTION FROM IMPORTANT QUESTIONS
