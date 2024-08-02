import java.util.*;
public class Greedy
{
    /*
     * greedy algorithms is the problem solving technique where we make the locally optimum choice at each stage and hope to achieve a global optimum
     * 
     */


     // class for question 3:-
     static class Job{
        int deadLine;
        int profit;
        int id;
        public Job(int i, int d, int p)
        {
            id = i;
            deadLine = d;
            profit  = p;


        }
     }
    public static void main(String args[])
    {
        /*
         * question 1: Activity selection
         * 
         * statement- you are given n activites with their  start and end times . select the max number of activities that can be performed 
         * by a single person , assuming that a person can only work on a single activity at a time .
         * activities are sorted according to end time.
         * 
         * eg   start={10,12,20}
         *      end = {20, 25,30}
         *      ans = 2   A0 and A2
         * 
         * approach :- greedy approach aise lagaenge ki phle  end time ko sort  kar lenge if t is not sorted ..here it is sorted..
         * now sab se kam time vaali acitvity A0 ko to include karna hi h sorting ke baad as usme sab se kam time lag rha h to vo kar ke hi baaki jaada activities perform ho sakti h..
         * to sort kar ke 1st act ko include kar liya aur ab end time ke according baki ki activities dekhenge ki  overlap to nai kar rhi ..if not then count++;
         * 
         * to summarise approach:-
         * 1)  sort on the basis of  end time
         * 2) select 1st activity(after sorting) as it takes less time 
         * 3) if next activity is non overlapping( non overlapping means start time of this activity >= last selcted activity's end time)  then count++
         * 
         * 
         * NOTE:-  when we say ki hume sort karna h end time ke basis pe that doesnt mean ki srf arrays.sort kar idya.usse end time vaala array sort ho jaega lkn ye order loose ho jaega ki 
         * end time vaale array m 0th index pe jo act thi uska start time start vaale array ke 0th index vaala tha lkn humne agar sirf end time sort kar diye to pata nai rhega ki us act ka start time kya tha...to poora galat ho jaega
         */

         int start[] = {1,3,0,5,8,5};
         int end[] = {2,4,6,7,9,9};

         //here end times are already sorted so no need to sort
         // but if start = {0,1,3,5,5,8}   and end ={6,2,4,7,9,9}
         //ab ye end time ke basis pe sort nai h .. to hume sort karn parega ..we do it in the following way:-

         //sorting on the basis of end time

         /*
          * int [][] activities = new int[start.length][3];      // humne ek 2d array banaya aur har row ke liye 3 coloumns..ek col m store karenge index ,ek m start time, ek m end time,,,to ab jab end time ke basis pe  sort bhi kare agar to actuall order loose nai hoga as humne original index bhi store kar liya h
          for(int i=0; i<start.length; i++)
          {
            activities[i][0] = i;   // phle col m index store kiya
            activities[i][1] = start[i];  // start time store
            activities[i][2] = end[i];    // end time store
          }

          IMPORTANT STEP;-  sorting on the basis of end time....ab jo ek col m end time store kiya h 2d array ke uskko sort karna h end time ke basis pe..
          yaani abhi tak jo arrays.sort karte the vo 1d array ke liye hota tha..ab 2d ke liye karenge using Comparator

        Arrays.sort(activities, Comparator.comparingDouble(o ->o[2]));     // Arrays.sort me doosra parameter h Comparator.comparingDouble(o ->o[2])....itne aprt ko java ke andar LAMBDA FUNCTION bolte h ..lambda function basicaly is a short form of big comparator function...comparators hume help karte h alag alag basis pe sort karne m
        o->o[2]  se ya pata chal rha h teesre col ke basis pe sorting ho rhi h  


        ab neeche algorithm m bas thore change aayenge:-
        int count = 1;
        int prevSelect = 0;
       System.out.println(0);
        for(int i=1; i<start.length; i++)
        {
           if(activities[i][1] >= activities[prevSelect][2])
           {
            count++; 
            prevSelect = activities[i][0];
            System.out.println(activities[i][0]);
           }
        }
        System.out.println(count);


          */

          //code for when it is already sorted on the basis of end time:-
          
        //  int count =1;
        //  int prevSelect = 0;
        //  System.out.println(0);
        //  for(int i=1; i<end.length; i++)
        //  {
        //     if(start[i]>=end[prevSelect])
        //     {
        //         count++;
        //         prevSelect = i;
        //         System.out.println(i);
        //     }
        //  }
        //  System.out.println(count);



        //question 2:  fractional knapsack 

        /*
         * Statement:- given the weights and values of N items , put these in a knapsack of capacity W to get the maximum total value in the knapsack
         * 
         * means total weight W se jaada nai hona chahiye and hume sack m max value milni chchaiye...
         * is ques m jo items h vo divisible h ...yaani 10 weight h agar kisi item ka aur value 100 h to agar us item kahumne 5 wieght liya to value 50 ho jaegiii
         * 
         * eg: value = {60,100,120}    weight={10,20,30}    W = 50
         * to is eg m ans 240 aayega ...10 aur 20 vaala poore poore items le lenge aur 30 vaale ka kjhaali 20 weight hi le paemge to uski value 80 
         * and total value 60+100+80 = 240
         * 
         * 
         * approach ..hume aise items lene h jinka wieght kam ho aur aur value jaada h..
         * to greedy isme aise lagega ki har item ke liye corrresponding value by weight ratio nikal lenge..
         * aur pichle ques ki tarah us rati ke basis pe sort kar lenge ...to hume jaada v/w ratio vaale items bhi mil jaenge aur unke respective original weights ka order bhi loose nai hoga aur hume ans mil jaega using greedy 
         * 
         * code goes here:-
         * 
         */
        int val[] = {60, 100, 120};
        int weight[] = {10,20,30};
        int W = 50;

        // 2d array to sort on the basis of ratio 

        // double ratio[][] = new double[val.length][2];
        // // har row ke liye do col...ek col m orginal index aur ek col m ratio..taaki jab fir ratio sort kare to hume hamare original index bhi pata ho jisse unka origina weight nikal le

        // for(int i=0; i<val.length; i++)
        // {
        //     ratio[i][0] = i;
        //     ratio[i][1] = val[i]/(double)weight[i];
        // }
        // Arrays.sort(ratio, Comparator.comparingDouble(o->o[1])); // 1st col m ratio stored h aur uske basis pe sorting hui h ....aur vo bhi ascending order  m
        // int value =0;

        // for(int i=val.length-1; i>=0; i--)
        // { 
        //     // ye loop ulta  isi liye chalaya h as v/w ratio jo sort kare h vo ascending m h aur hume jaada bare v/w  chahhieye to last se shuru kiya taaki kam weight and jaada value mile
        //     if(W>=weight[(int)ratio[i][0]])
        //     {
        //         value+=val[(int)ratio[i][0]];
        //         W-=weight[(int)ratio[i][0]];    // yaani is item ko poora le sakte h to poora le liya aur W m hi chnage kar diya aur value m us item ki value add kar di


        //     }
        //     else
        //     {  //include fractional item
        //         value+=ratio[i][1]*W;  // yaani ye item poora nai le sakte ..to jitna W bacha h yaani wieght bacha h use ratio se multiply kiya is item ke aur hume value mil gai...iske baad break kar jaenge as poora W utilise kar liyaa
        //         break;
        //     }

        // }
        // System.out.println(value);


        //question3:-  job sequencing problem

        /*
         * given an array of jobs where every job has a deadline and profit if the job is finished before the deadline . it is also given that every job takes a single unit of time ,  so the
         * minimum possible ddeadline for anuy job is 1. Maximize the total profit if only one job can be scheduled at a time.
         * eg:-
         * Job A = 4,20
         * Job B = 1,10
         * Job C = 1,40
         * Job D = 1,30
         * 
         * question ka matlab ye h ki koi bhi job  tak finish ho jaani chahiye jab tak clock pe uska deadline time hit na kare..
         * upar example ke liye total profit ke liye hum job c karenge fir job a karenge..
         * 
         * approach ..hume vo job phle karnui h jisme profit jaada ho...o profit ke basis pe sort kar ke phle max profit vaali job karenge...
         * fir dekhhenge ki next max profit vaali job kar sake h deadline ke according ki nai and so on..
         * 
         * jaise upar eg ke liye phle job c kari as max profit aur time lag gya 1 unit,,,,ab next max profit vaali job d H LKN USKI DEAD LINE 1 H AUR 1 UNIT KA Ttime to hume already lag gya..to ye nai kar paenge,,
         * fir job A  ka h next max profit ..ye kar lenge hum as iska deadline 4 unit h aur hume abhi 1 unit time laga h..aur harjob ko karne m ek hi unit time lagta h to ye deadline se phle ho jaegii..
         * and so on aur hume ans mil jaega ki job c fir job a
         * 
         * iske liye class bana ke usme store karaenge job index profit aur dead line aur yaha kisi class ke objects ke basis pe sort karna seekhenge using comparator
         * 
         * code for question 3;-
         */

         int jobsInfo[][] = {{4,20}, {1,10}, {1,40}, {1,30}};
         ArrayList<Job> jobs = new ArrayList<>();
         for(int i=0; i<jobsInfo.length; i++)
         {
            jobs.add(new Job(i, jobsInfo[i][0], jobsInfo[i][1]));

         }

         //sorting on the basis of deadline..that means object ki property ko sort karna h using comparator

         //arraylist h to we will use collections.sort

        //  Collections.sort(jobs, (a,b) -> a.profit-b.profit);   a aur b do objects h aur unke profit ke basis pe agar ascending order m sort karna h to aise karenge
        //hume descending order m sort karna h ..

        Collections.sort(jobs,(a,b)-> b.profit - a.profit);  //(a,b)-> b.profit - a.profit  this is lambda function
        
        ArrayList<Integer> seq = new ArrayList<>();
        int currTime = 0;
        int ans = 0;


        for(int i=0; i<jobs.size();i++)
        {
             Job curr = jobs.get(i);

            if(curr.deadLine > currTime)
            {
               
                ans+=curr.profit;
                currTime++;
                seq.add(curr.id);


            }

        }
        System.out.println(ans);
        System.out.println(seq);


        





    


    }
}