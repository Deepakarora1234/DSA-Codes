import java.util.*;
public class Graphs7    //Strongly cnnected components and kosaraju's algorithm
{
    /*
     * Strongly connected components(SCG)...scg ka concept sirf directed graphs ke liye applicable hota h 
     * SCG is a component of a graph in which we can reach every vertex of that component from every other vertex in that component
     * 
     * considerr this directed graph:-     
     * 
     *     1----->0----->3---->4
     *     ^      |
     *     |      *
     *     -------2    
     * in this graph there are 3 SCG ..ek to {0 1 2} vaala component h ..is component ki teeno vertices m se kisi bhi vertex se start kar ke kisi bhi doosri vertex of this component pe jaaya jaa sakta h
     * doosra scg is graph m 3 aur 3rd scg is 4   ye dono components m single single  vertices h to ye bhi scg honge 
     * to is graph m scg 1 ho gya 0 1 2..scg 2 ho gya 3...and scg 3 ho gya 4
     * inko print karne ke liye hum KOSARAJU'S ALGORITHM KA USE KARTE H
     * is algrithm ke kuch specific steps h...hum scg ko find karne ke liye use karte h dfs ka
     * jab hum khaali dfs karte h kisi graph m to ek ek kar ke saari nodes visit hoke print ho jaati h...lkn hume scg chahiye ..to vo hume aise nai milega
     * 
     * KOSARAJUS algorithm khta h ki reverse dfs karo..yaani jis node se humne shuru kiya tha vaha se nai..last vaali node se shuru karo dfs
     * reverse dfs matlab agar upar vaale graph ka exampple le ..to agar 4 se shuru kiya to 4 ko visit kara diya.. ab 4 se kahi nai jaa sakte to ek scg hp gya 4..
     * fir 3 pe aaye ..3 ko visit kara diya...3 se 4 pe jaa sakte h lkn 4 already visit ho chuka h to doosra scg 3 ho gya...
     * fir 2 pe gye...2 ko visit kiya.. 2 se 1 pe gye..1 ko vis kiya.. 1 se 0 pe gye .0 ko vis kiyaa..aur ab 0 se 3 pe jaa sakte hlkn 3 already vis h.. to vapas 1 pe aaye..
     * 1 ke saare neighbours visit ho gya .. to vapas 2 pe aagye aur ek go m humne 210 inko vis kiya to ye poora ek scg ho gya 
     * 
     * 3 steps to implement kosaraju's algorithm;-
     * 
     * 1) get nodes in stack(topological sort)  O(V+E)
     * 
     * 2) transpose the graph  O(V+E)....transpose the graph mean ek directed graph diya to har 2 vertex ke beeech m jo edge h uski direction ulti kar do 
     * 
     * 3) do DFS according to stack nodes on the transpose graph  O(V+E)
     * 
     * 
     * 1st step m saaari nodes ko stack m daalna h just like we did in topologicalsort..yaani graph ki topological sort karna h..
     * to above graph ke liye top sort ho jaega agar 0 se start kare to..0 3 4 2 1 aur jo topological sort ke time hum stack banate h usme ndoes aise store honge----  bottom to top:-  1 2 4 3 0
     * 
     * 2nd step m graph ko transpose kar denge yaani saare edges ki direction reverse ar denge.. ye aise karenge ki saare edges nikal lenge jaise nikalte h phle 0 se V tak loop laga ke fir har index pe jo arraylist h uske size tak loop laga ke....fir create graph ke liye call kar denge aur source ki jagah destination aur destination ki jagah source
     * 
     * 3rd step m stack m se ek ek kar ke node nikalte jaenge aur ye transpose vaale graph m uska dfs karenge aur har node ko vis karate time use printkara denge.. to ek go m jitni nodes print hongi unhe mila ek scg hoga to hume saare scg mil jaaenge...
     * yaani stack se sab se phle 0 niklega..uske liye dfs ko call kiya..sab se phle  visit hua aur print hua.. 0 ke neighours ko call kiya..fir 1 vis hua aur print hua.. fir 1 ke neighbours ko call hua aur 2 vis hoke print hua..2 ke neighbour m 0 h vo already visited h to backtrack kar ke 1 pe aayenge vaha se back tack kar ke 0 pae aayenge to 012 mil jaega aur ye ek scg hoga..
     * fir stack m se 3 niklega uske liye dfs ko call lagegi..3 vis hoke print hoga aur 3 sse hum 0 pe jaa sakte h lkn 0 visited h to vapas aajaeng aur ek dfs ki call m 3 mila to ye 3 doosra scg hua..
     * fir stack se 4 nuklega aur uske liye bhi above steps aur hume milega scg number 3
     * fir stack se 2 niklega lkn uske liye already visited m true h to kuch nai karenge..fir 1 niklega stack m se uske liye bhi vis  ms true h to khaali remove kar denge aur aise karke hume saare scgs mil jaenge
     * 
     * 
     * 
     *  IMPORTANT  in teeno steps ki time complexity O(V+E) hoti h to kosaraju's algorithm ki bhi time complexity O(V+E) hoti h
     * 
     * topologial sort vaala function hum phle likh chuke h.. fir transpose vaala code likh denge vo bhi easy h..aur fir dfs kar denge uske liye bhi function phle likh chuke h to saari cheeze kosaraju's ki hum phle hi kar chuke h
     * 
     * code for kosaraju's algo goes here:-
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
      

        graph[0].add(new Edge(0,2));
        graph[0].add(new Edge(0,3));  

        graph[1].add(new Edge(1,0));    //ye upar vaale example vaala graph h  
        
        graph[2].add(new Edge(2,1));
        
        

        graph[3].add(new Edge(3,4));
      
    }

    //kosaraju's algorithm  O(V+E)
    public static void topSort(ArrayList<Edge> graph[], int curr, boolean vis[], Stack<Integer> s)
     {
        vis[curr]=true;
        for(int i=0; i<graph[curr].size(); i++)
        {
            Edge e = graph[curr].get(i); 
            if(!vis[e.dest])
            {
                topSort(graph,e.dest,vis, s );
            }
        }
        s.push(curr);
     }


     public static void DFS(ArrayList<Edge> transpose[], int curr, boolean vis[])
    {
        
        vis[curr]=true;
        System.out.print(curr+" ");

        for(int i=0; i<transpose[curr].size(); i++) 
        {
            Edge e = transpose[curr].get(i);
            if(vis[e.dest]==false)
            {
                DFS(transpose, e.dest, vis);    

            }
        }
    }


    public static void kosarajuAlgo( ArrayList<Edge> graph[], int V)
    {
        //step 1
        Stack<Integer> s = new Stack<>();
        boolean vis[] = new boolean[V];
        for(int i =0; i<V; i++)
        {
            if(!vis[i])
            {
                topSort(graph, i, vis, s);

            }
        }

        //step 2  transpose vaala naya graph banana h
        ArrayList<Edge> transpose[] = new ArrayList[V];
        for(int i=0; i<transpose.length; i++) 
        {
            vis[i]=false;   // kyu ki humne top sort ke time banaya tha apne vis array ko to is time usme sab true honge to naya transpose vaala garaph banate time hume har index pe vapas false karna parega
           transpose[i] = new ArrayList<Edge>();
        }
        for(int i =0; i<V; i++)
        {
            for(int j =0; j<graph[i].size(); j++)
            {
                Edge e = graph[i].get(j);
                transpose[e.dest].add(new Edge(e.dest, e.src));
            }
        }

        //step 3
        while(!s.isEmpty())
        {
            int curr=s.pop();
            if(!vis[curr])
            {
                DFS(transpose, curr, vis);
                System.out.println();
            }
        }
    }
    
    public static void main(String args[])
    {
        int V = 5;
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);
        kosarajuAlgo(graph, V);
        
    }
}