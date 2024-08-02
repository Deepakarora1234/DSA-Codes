import javax.swing.plaf.synth.SynthSpinnerUI;

public class BackTracking
{ 
    /*
     * backtracking recursion jaisa hi concept hai ...recursion m jaise har last call ke baad base case hit karta tha fir vapas aate jaate the har call ke liye ..yaha 
     * bas vapas aate aate bhi koi operation perform hota hai jise backtracking step bolte hai ..baaki poora concept recursion vaala hai
     * 
     * 3 quesitions se samhenge bakctracking ...1st is N queens problem and its modifications
     * 
     * N-QUEENS:- statement -  we are given a chessboard of size N*N and we are also given N queens ..we need to place thse N queens such that no queen is in a position to attack any other queen 
     * in first modification of this question print all the possible solutions ...in second..print the number of possible solutions and in third ...if any one solution
     * 
     * approach-  approach ye rahegi ki n rows hai to hum har baar ek ek row ke liye saare columns pe ja ke apni queen ko rakhenge fir next row ke liye call kar denge ..aur next row m hum un agah rakhenge queen ko
     *  jaha safe hoga ..agar aisi position mil gai to vaha place kar ke next row ke liye call kar denge aur agar koi position nai mili to matlab pichli queen galat rakhi h to back track kar ke pichli 
     * queen pe jaenge aur vaha se hata ke usi row m doosri possible safe position pe rakhenge..
     * aur rakhte rakhte base case hit ho jaega jab tab solution ko print kara denge ..
     * base case is jab saari rows ho jaengi aur row track karne vaala variable is equal to board.length
     * 
     * ye check karne ke liye ki kisi position pe queen ko rakhna safehai ki nai ..uske liye ek cheez notice karenge ki hum queens upar se neeche fill karte hue chal rhe h..
     * to jo check karna hai vo present row ke upar vaali side check karna hai neeche abhi khaali h...
     * aur presenet row let it be ith row ..to us row ke kisi cell ke liye check karna hai  to 3 jagah karna h ..ek to present cell jis column m hai us col m upar queen nai honi chahiye..us cell ke above left diagonal m nai honi chahiye aur above right  m nai honi chahiye..
     * neeche vaale part m check karne ki jaoorat nai as hum queens upar se neeche fill kar rhe h..aur ek rowm to obviously ek hi queen aayegi
     * 
     * 2nd modification ke liye ek static varibale count le lenge aur jitni baar base case hit karega utni baar print karane ki jagah count ++ kar denge 
     * 3rd modification ke liye hum apna nQueens vaala function boolean type ka banenge aur har baar ek queen place karne ke baad recursive call next row ke liye karenge vo agar true aagai to return kar denge true 
     * aur false aai to fir apna backtracking vaala step karte huee previous queen ko hataenge aur nai postion pe rakhenge..
     * 
     * TIME COMPLEXITY:- time complexity for nqueens  = O(n!) as phli row ke liye n choices hoti hai.. fir next ke liye n-1 fir next ke liye n-2 ...last vaali ke liye 1 choice hoit hai
     * to total n(n-1)(n-2)....1 = n!
     * is ko hum aise bhi represent karte hai :- refference relation is T(n) = time to place nqueens = T1queen * T(n-1) + TisSafe()   yaani ek queen ke liye time multiply kar diya n-1 queens  ke liye  + is safe check karne ka time 
     * aur ek queen ko place karne ke liye O(n) lagta hai to T(n) = n*T(n-1) + TisSafe()
     */
    public static void printArr(char[][]board)
    {
        for(int i=0; i<board.length; i++)
        {
           
            for(int j=0; j<board.length; j++)
            {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    static int count = 0;
    public static boolean isSafe(char[][]board, int row, int col)
    {
        //above in column check
        for(int i = row; i>=0; i--)
        {
            if(board[i][col]=='Q')
            return false;
        }

        // left diagonal
        for(int i=row, j = col; i >= 0 && j >= 0; i--, j--)
        {
             if(board[i][j]=='Q')
            return false;
        }

        //above right diagonal
        for(int i= row, j = col; i>=0 && j<board.length; i--, j++)
        {
             if(board[i][j]=='Q')
            return false;
        }

        return true;
    }


    // public static void nQueens(char[][]board, int row)
    // {
    //     //base
    //     if(row==board.length)
    //     {
    //         //  System.out.println("-------------chessboard----------");
    //         // printArr(board);
    //         count++;
    //         return;
    //     }


    //     //recursive call
    //     for(int j = 0; j<board.length; j++)  // har row ke liye saare coloumns pe jaane vaala loop
    //     {
    //         if(isSafe(board, row, j)==true)
    //         {
    //             board[row][j] = 'Q';    // queen ko place kar ke next row ke liye call kar diyaa
    //             nQueens(board, row+1);
    //             board[row][j] = '.';   // vapas aate time queen ko hatane vaala step ..queen hatani paregi as agar is vaale level pe queen yaha rakhne se aage problem aaye to yaha se hatani paregi.aur agar problem nai bhi aai to bhi all possiblilities ke liye hatani paregi..graph vaale all possible paths m bhi hum vis array m false karte the vapas aate aate taaki saare paths include ho jae ..vaisa sa hi step hai
    //         }
    //     }
    // }
     public static boolean nQueens(char[][]board, int row)
    {
        //base
        if(row==board.length)
        {
            //  System.out.println("-------------chessboard----------");
            // printArr(board);
          
            return true;
        }


        //recursive call
        for(int j = 0; j<board.length; j++)  
        {
            if(isSafe(board, row, j)==true)
            {
                board[row][j] = 'Q';   
                if(nQueens(board, row+1)==true)
                {
                    return true;
                }
                board[row][j] = '.';  
            }
        }
        return false;
    }
    public static void main(String args[])
    {
        int n = 5;
        char board[][] = new char[n][n]; // ye hamara chess board hai 
        //initialize...initially poora chessboard khaali hai to har cell pe '.' ye hai jaise jaise queens fill karenge vaha 'Q' kar denge 
        for(int i=0; i<n; i++)
        {
            for(int j = 0; j<n; j++)
            {
                board[i][j] = '.';
            }
        }
        // nQueens(board, 0);   // nQueens function m apna board aur konsi row se queens bithana shuru karna hai (here 0) vo pass kar diya 
        // System.out.println(count);
        if(nQueens(board,0)==true)
        {
            printArr(board);

        }
        else
        System.out.println("not possible");
        
    }
}