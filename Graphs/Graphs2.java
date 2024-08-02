import java.util.*;
public class Graphs2
{
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

    /*
     * Question :-All paths question...we are given a start and target and we need to clculate all possible path from stat to target
     * 
     * for eg ..for the given graph, we are given start 0 and target 5 and we need to print all possible paths
     * possible paths for above question are:- 0--1--3---5, 0--1--3--4--5, 0--2--4--5, 0--2--4--3--5
     * this question is simply a question of DFS or BFS with some little changes
     * 
     * hum DfS ko use kar ke ye question karenge...vis array ki help se 0 se start kar ke har vertex pe jaenge aur jause hi vertex target ke equal hogi to path ko print kra denge...but
     * ek important step ye h ki ek baar node pe vis kar ne ke baad jab hum vis array m usko true karte h to uske baad backtrack karte time use false karna parega taaki agar doosre possible path me vo vertex ho agar to vo bhi include ho jae..
     * agar ye false karne vaala step nai karenge to ek vertex ek hi baar visit hogi aur sirf ek hi paath m include hogi aur agar doosre path m aani hogi to nai aa paegi
     * 
     * code for all path ques goes here:- 
     * 
     * 
     * IMPORTANT:-  allPaths function jo likha h humne uski time complexity O(V^V) hoti h jabki DFS ki O(V+E) hoti h ..yaani allPaths function not very optimized
     */
    

    public static void createGraph(ArrayList<Edge>graph[])
    {
    //      1-----3
    //     /      | \
    //    0       |   5--6
    //    \       | /
    //      2-----4

        

        for(int i=0; i<graph.length; i++) 
        {
            graph[i] = new ArrayList<Edge>();
        }
      

        graph[0].add(new Edge(0,1));
        graph[0].add(new Edge(0,2));  

        graph[1].add(new Edge(1,0));  
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
    public static void allPaths(ArrayList<Edge>graph[], int start, int target, String path,boolean vis[])
    {
        if(start==target)
        {
            System.out.println(path);
            return;
        }
       
       for(int i=0; i<graph[start].size(); i++)
       {
        Edge e = graph[start].get(i);
        if(vis[e.dest]==false)
        {
            vis[start]=true;
            allPaths(graph, e.dest, target,path+Integer.toString(e.dest), vis );
            vis[start]=false;

        }
        
       }
    }
    public static void main(String args[])
    {
        int V=7;

        ArrayList<Edge> graph[]=new ArrayList[V]; 
        createGraph(graph);
        boolean vis[] = new boolean[V];
        String path="";
        int start = 0;
        int target = 5;
        allPaths(graph, 0,5,path+Integer.toString(start),vis);
        
        
    }
}

// IMPORTANT:- REVISE LEETCODE ROTTING ORANGES QUESTION AND Number of Islands QUESTION FROM IMPQUESTIONS 