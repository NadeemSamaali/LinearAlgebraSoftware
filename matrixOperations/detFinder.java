package matrixOperations;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This class contains all the necessary methods to calculate the determinant of any nxn matrix
 * 
 * @author Nadeem Samaali
 * @version 1.1.2 - Updated comments
 */


public class detFinder 
{
    /*
    //Setting up the scanner
    Scanner input = new Scanner(System.in);
    */
    static DecimalFormat df = new DecimalFormat("0.000");
    static DecimalFormat d0 = new DecimalFormat("0.000000000");

    
    /**
     * This method will allow for the sorting of the rows of a square matrix in ascending order
     * 
     * @param M matrix to sort
     * @param N size of the square matrix
     * @return K amount of rows that were swtiched in the sorting
     * @version 2.0.0 | The sorting algorithm is now based on ArrayLists rather than arrays
     */
    
    public static Integer mSort(double M[][], int N)
    {
        int amountOf0=0;
        ArrayList<Double>[] mLibrary = new ArrayList[N+3];
        ArrayList<Double> mList = new ArrayList<>(); 

        double[][] C = new double[N+1][N+1];

        //Setting up the duplicate of the initial matrix which will come in useful to calculate 
        //the total amount of permutations done by the sorting algorithm
        for(int t = 0; t<=N; t++)
        {
            for(int s = 0; s<=N; s++)
            {
                C[t][s] = M[t][s];
            }
        }

        for(int x = 0; x<N+3; x++)
            mLibrary[x] = new ArrayList<Double>();


        //Finds the amount of leading zeros in each row
        for(int i = 0; i<=N; i++)
        {
            for(int j = 0; j<=N; j++)
            {
                if(M[i][j] == 0)
                {
                    amountOf0 += 1;
                }
                else
                    break; 
            }

            //Adds the entries of the row into an Arraylist corresponding to its amount of leading 0s
            for(int k = 0; k<=N; k++)
            {
                mLibrary[amountOf0].add(M[i][k]);
            }
            amountOf0 = 0;
        }

        //Adding the rows in order of amount of 0s into an Arraylist
        for(int u = 0; u<N+3; u++)
        {
            mList.addAll(mLibrary[u]);
        }

        int c = 0;

        //Converting the Arraylist of sorted entires into the nxn matrix
        for(int a = 0; a<=N; a++)
        {
            for(int b = 0; b<=N; b++)
            {
                M[a][b] = mList.get(c);
                c+=1;
            }
        }

        int K = 0;

        //Calculating the amount of permutations based on the initial and final matrix
        for (int i = 0; i < C.length; i++) {
            if (!Arrays.equals(C[i], M[i])) {
                // Find the row in the remaining part of the matrix where the swap is needed
                for (int j = i + 1; j < C.length; j++) {
                    if (Arrays.equals(C[j], M[i])) {
                        // Swap rows
                        double[] temp = C[i];
                        C[i] = C[j];
                        C[j] = temp;

                        K++;
                        break;
                    }
                }
            }
        }

        //where k is the amount of permuatations done within the matrix
        return K;

    }


    /**
     * This method is an operator that serves part in the process of the reduction and cofactor
     * expansion of a square matrix in order to reduce into an upper triangular matrix.
     * 
     * The the metod will calculate the determinant
     * 
     * @param M matrix to operate
     * @param N size of the matrix
     * @return determinant
     * @version 1.1.0 | Now incorporates the new sorting methid (mSort)
     */    
    public static double getDeterminant(double[][] M, int N)
    {
        //calculating determinant
        double num1 = 1.0;
        int amountOfK = 0;
            for(int v = 0; v<=N; v++)
                amountOfK += v;
                        double[] k = new double[amountOfK];


        for(int m = 0; m<amountOfK; m++)
        {
            k[m] = 1.0;
        }
        int num0 = -1;

        double kFactor = mSort(M,N);

        if(kFactor != 0)
        {
            System.out.println("\n>> Sorted the matrix : ");
            mOPS.printMatrix(M,N);
        }

        System.out.println("\n>> Steps to solution :");

        for(int d = 0; d<=N; d++)
        {  
            /*This loop will multiply each row R>R1 by a factor k, where k is the factor needed to tranforms
            *the entry of the first row into the entry located at [0][0] as well was multiplying the remainer
            *of the entires of this row by that same scalar k
            */
            for(int x = 0; x<=N-d; x++)
            {  
                if(M[x][x] != 0)
                {
                    for(int y = 1; y <= N-(x)-d; y++)
                    {
                        if(M[x+y][x] != 0)
                        {
                            num0 += 1;

                            k[num0] = (M[x][x]/M[x+y][x]);

                            if(k[num0] != 1)
                            {
                                System.out.print("\nK = M(" + x + "," + x + ")" + "/M(" + (d+y) + "," + x + ")");
                                System.out.print(" --> " + df.format(k[num0]) + "*R" + (x+y+1) +"\n");
                            }
                            

                            for(int i = 0; i<=N; i++)
                                M[x+y][i] = k[num0]*M[x+y][i];

                            mOPS.printMatrix(M,N);
    
                            for(int l = 0; l<=N; l++)
                                M[x+y][l] -= M[x][l];


                            System.out.println("\nR" + (x+y+1) + " - R" + (x+1) + " --> R" +(x+y+1) );
                            mOPS.printMatrix(M,N);
                        }                        
                    }
                }   
            }
        }

        kFactor += mSort(M,N);
        System.out.print("\nSorted the matrix : ");
        mOPS.printMatrix(M,N);
        
       
        for(int n0 = 0; n0<amountOfK; n0++)
        {
            //System.out.println("\nRecorded values of k : ");
            if(1/k[n0] == 0) 
                k[n0] = 1.0;
            else
                k[n0] = k[n0];

            //System.out.println(k[n0]);
        }
        
        System.out.println();
        System.out.print("det(M) = ");

        //System.out.println("1/k :");
        for(int u = 0; u<amountOfK; u++)
        {
            num1 *=(1/k[u]);
            if(1/k[u] != 1)
                System.out.print(df.format(1/k[u]) + " * ");
        }
        
        for(int r = 0; r<=N; r++)
            {
                System.out.print(df.format(M[r][r]) + " * ");
                num1 = M[r][r]*num1;
            }
        
        num1 *= Math.pow(-1,Double.valueOf(df.format(kFactor)));

        System.out.print(Math.pow(-1,Double.valueOf(df.format(kFactor))));
        //num1 = num1*negFactor;

        System.out.println("\n\n>> The determinant of this matrix is : " + df.format(num1)); 
        
        return num1;
    }

    /**
     * Silent version of the getDeterminant method -- Will calculate the determinant of a matrix
     * without printing out all the steps and rather simply return the value of the determinant
     * 
     * @param M
     * @param N
     * @return num1 -- the value of the determinant
     * @version 1.1.0 | Now incorporates the new sorting methid (mSort)
     */
    public static double getSilentDeterminant(double[][] M, int N)
    {
        //calculating determinant
        double num1 = 1.0;
        int amountOfK = 0;
            for(int v = 0; v<=N; v++)
                amountOfK += v;
                        double[] k = new double[amountOfK];

        //System.out.println(amountOfK);

        for(int m = 0; m<amountOfK; m++)
        {
            k[m] = 1.0;
        }
        int num0 = -1;

        double kFactor = mSort(M,N);

        for(int d = 0; d<=N; d++)
        {  
            /*This loop will multiply each row R>R1 by a factor k, where k is the factor needed to tranforms
            *the entry of the first row into the entry located at [0][0] as well was multiplying the remainer
            *of the entires of this row by that same scalar k
            */
            for(int x = 0; x<=N-d; x++)
            {  
                if(M[x][x] != 0)
                {
                    for(int y = 1; y <= N-(x)-d; y++)
                    {
                        if(M[x+y][x] != 0)
                        {
                            num0 += 1;

                            k[num0] = (M[x][x]/M[x+y][x]);

                            for(int i = 0; i<=N; i++)
                                M[x+y][i] = k[num0]*M[x+y][i];

    
                            for(int l = 0; l<=N; l++)
                                M[x+y][l] -= M[x][l];


                        }                        
                    }
                }   
            }
        }

        kFactor += mSort(M,N);
        
       
        for(int n0 = 0; n0<amountOfK; n0++)
        {
            //System.out.println("\nRecorded values of k : ");
            if(1/k[n0] == 0) 
                k[n0] = 1.0;
            else
                k[n0] = k[n0];

            //System.out.println(k[n0]);
        }
        

        //System.out.println("1/k :");
        for(int u = 0; u<amountOfK; u++)
        {
            num1 *=(1/k[u]);
        }
        
        for(int r = 0; r<=N; r++)
            {
                num1 = M[r][r]*num1;
            }
        
        num1 *= Math.pow(-1,Double.valueOf(df.format(kFactor)));

        //num1 = num1*negFactor;

        
        return num1;
    }

    public static void main(String[] args)
    {

        Scanner input = new Scanner(System.in);
        String ans;

        System.out.println("\n>> Please insert square matrix size (1,2,3...,n) :\n");
        ans = "";
        ans = input.nextLine();

        //Setting the value n as the size of the matrix
        int n = Integer.valueOf(ans)-1;
        ans = "";

        //Adding the entries of the matrix into an array by splitting
        System.out.println("\n>> Insert the values of the entries sparated with a comma\n   respecting this form : a11,...,a1n,...,am1,...,amn\n");
        ans = input.nextLine();
        String[] entries = ans.split(",");

        //Outputting the matrix
        System.out.print("\n>> Here is the inputted matrix : ");
        mOPS.printEntries(n,entries);
        System.out.println();

        double[][] matrix = new double[n+1][n+1];

        //Set the entires into the designated placements in the matrix Array
        mOPS.setMatrixFromString(matrix,n,entries);

        getDeterminant(matrix, n);
    }
    
    
}