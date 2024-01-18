import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/**
 * This class contains all the most recurring methods when it comes to working with matrices
 * within the App file (converting the user entires into a matrix, printing entries, printing a matrix)
 * 
 * @author Nadeem Samaali
 * @version 2.1.0 | Rewrote setMatrix and printMatrix to apply for any sized matrix + Added mMultiply method
 */
public class mOPS {

    static DecimalFormat df = new DecimalFormat("0.000");
    static DecimalFormat d0 = new DecimalFormat("0.000000000");
    Scanner input = new Scanner(System.in);
    /**
     * This method allows for printing the entries of the user
     * 
     * @param N Size of the matrix to be printed
     * @param ansArray Array of the entries entred by the user
     */
    public static void printEntries(int N, String[] ansArray1)
     {
        int sCounter1 = -1;
 
        //Outputting the matrix as well as the data entries
        System.out.print("[");
        for(int a = 0; a<=N; a++) {
            System.out.print("[");
            for(int b = 0; b<N; b++) {
                sCounter1 += 1;
                System.out.print(ansArray1[sCounter1]);
                System.out.print(",");
            }
            sCounter1 += 1;
            System.out.print(ansArray1[sCounter1]);
 
            if(sCounter1 == Math.pow(N+1,2)-1)
                System.out.print("]");
            else
                System.out.print("];");
        }
        System.out.println("]");
    }
    /**
     * This method inquires the user on the entries to add into each row of the N sized
     * 
     * @param M Matrix
     * @param N Matrix size
     * @param in Scanner
     */
    public static void setMatrix(double[][] M, int height, int length, Scanner in) {
        
        String ans = "";
        ArrayList<String[]> E = new ArrayList<>();

        for(int i = 0; i<height; i++) {
            System.out.print("   ");
            ans = in.nextLine(); 
                E.add(ans.split(" "));           
                    if(E.get(i).length != M[0].length) {
                        //System.out.println(E.get(i).length);
                        //System.out.println(M.length);
                        throw new IllegalArgumentException("Make sure the rows are in bound with the matrix size");
                    }
                    else {
                        for(int j = 0; j<length; j++) {
                            M[i][j] = Double.valueOf(E.get(i)[j]);
                        }
                    }
            }
    } 
    /**
     * This method allows for the printing of any nXn matrix | To use for testing purposes
     * 
     * @param M matrix to print
     * @param N size of the metrix
     */
    public static void printMatrix(double[][] M) {

        int currentNum = 0;
        int length = M[0].length;
        int height = M.length;
        int[] maxNum = new int[length];

        //Check for the number with the biggest amount of figures per column
        for(int a = 0; a<length; a++) {
            for(int b = 0; b<height; b++) {
                char[] E = String.valueOf(M[b][a]).toCharArray();
                currentNum = E.length;
                    if(currentNum > maxNum[a]) {
                        maxNum[a] = currentNum;
                            if(M[b][a] < 0)
                                maxNum[a] -= 1;
                    }         
            }
        }
        //Print the matrix in an alligned manner
        for(int i = 0; i<height; i++) {
            System.out.print(" ");
                for(int j = 0; j<length; j++) {
                    if(M[i][j] >= 0) {
                        System.out.print("  " + M[i][j]);
                        char[] E = String.valueOf(M[i][j]).toCharArray();
                        //System.out.print(" E length = " + E.length);
                        for(int x = 0; x<maxNum[j] - E.length; x++) {
                            System.out.print(" ");
                        }
                    }
                    if(M[i][j] < 0) {
                        System.out.print(" " + M[i][j]);
                        char[] E = String.valueOf(M[i][j]).toCharArray();
                        //System.out.print(" E length = " + E.length);
                        for(int x = 0; x<=maxNum[j] - E.length; x++) {
                            System.out.print(" ");
                        }
                    }
                }
                System.out.println();
        }
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
            k[m] = 1.0;
        int num0 = -1;

        double kFactor = mSort(M,N);

        if(kFactor != 0) {
            System.out.println("\n>> Sorted the matrix : ");
            mOPS.printMatrix(M);
        }
        System.out.println("\n>> Steps to solution :");

        for(int d = 0; d<=N; d++) {  
            /*This loop will multiply each row R>R1 by a factor k, where k is the factor needed to tranforms
            *the entry of the first row into the entry located at [0][0] as well was multiplying the remainer
            *of the entires of this row by that same scalar k
            */
            for(int x = 0; x<=N-d; x++) {  
                if(M[x][x] != 0) {
                    for(int y = 1; y <= N-(x)-d; y++) {
                        if(M[x+y][x] != 0) {
                            num0 += 1;
                            k[num0] = (M[x][x]/M[x+y][x]);
                                if(k[num0] != 1) {
                                    System.out.print("\n   K = M(" + x + "," + x + ")" + "/M(" + (d+y) + "," + x + ")");
                                    System.out.print(" --> " + df.format(k[num0]) + "*R" + (x+y+1) +"\n");
                                }
                                for(int i = 0; i<=N; i++) {
                                    M[x+y][i] = Double.valueOf(df.format(k[num0]*M[x+y][i]));
                                    if(M[x+y][i] == -0.0)
                                        M[x+y][i] = 0;
                                }
                                mOPS.printMatrix(M);
                                for(int l = 0; l<=N; l++) {
                                    M[x+y][l] = Double.valueOf(df.format(M[x+y][l]-M[x][l]));

                                    if(M[x+y][l] == -0.0)
                                        M[x+y][l] = 0;
                                }
                            System.out.println("\n   R" + (x+y+1) + " - R" + (x+1) + " --> R" +(x+y+1) );
                            mOPS.printMatrix(M);
                        }                        
                    }
                }   
            }
        }
        kFactor += mSort(M,N);
        System.out.println("\n>> Sorted the matrix :\n");
        mOPS.printMatrix(M);
       
        for(int n0 = 0; n0<amountOfK; n0++) {
            //System.out.println("\nRecorded values of k : ");
            if(1/k[n0] == 0) 
                k[n0] = 1.0;
            else
                k[n0] = k[n0];

            //System.out.println(k[n0]);
        }
        
        System.out.println();
        System.out.print("   det(M) = ");

        //System.out.println("1/k :");
        for(int u = 0; u<amountOfK; u++) {
            num1 *=(1/k[u]);
            if(1/k[u] != 1)
                System.out.print(df.format(1/k[u]) + " * ");
        }
        for(int r = 0; r<=N; r++) {
                System.out.print(df.format(M[r][r]) + " * ");
                num1 = M[r][r]*num1;
        }
        
        num1 *= Math.pow(-1,Double.valueOf(df.format(kFactor)));
        System.out.print(Math.pow(-1,Double.valueOf(df.format(kFactor))));
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
     */
    public static double getSilentDeterminant(double[][] M, int N) {
        //calculating determinant
        double num1 = 1.0;
        int amountOfK = 0;
            for(int v = 0; v<=N; v++)
                amountOfK += v;
                        double[] k = new double[amountOfK];
        for(int m = 0; m<amountOfK; m++)
            k[m] = 1.0;
        int num0 = -1;

        double kFactor = mSort(M,N);

        for(int d = 0; d<=N; d++) {  
            /*This loop will multiply each row R>R1 by a factor k, where k is the factor needed to tranforms
            *the entry of the first row into the entry located at [0][0] as well was multiplying the remainer
            *of the entires of this row by that same scalar k
            */
            for(int x = 0; x<=N-d; x++) {  
                if(M[x][x] != 0) {
                    for(int y = 1; y <= N-(x)-d; y++) {
                        if(M[x+y][x] != 0) {
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

        for(int n0 = 0; n0<amountOfK; n0++) {
            if(1/k[n0] == 0) 
                k[n0] = 1.0;
            else
                k[n0] = k[n0];
        }
        for(int u = 0; u<amountOfK; u++)
            num1 *=(1/k[u]);
        for(int r = 0; r<=N; r++)
            num1 = M[r][r]*num1;
        num1 *= Math.pow(-1,Double.valueOf(df.format(kFactor)));
        
        return num1;
    }
    /**
    * This method will convert the inputted square matrix into its cofactor matrix
    * 
    * @param M the inputted matrix
    * @param N the size of the matrix
    */   
    public static void getCofactorMatrix(double[][] M, int N) {
        double[][][] C2 = new double[N][N][(int) Math.pow(N+1,2)];
        double[][] C3 = new double[N][N];
        double[] det = new double[(int)Math.pow(N+1,2)];
 
        int a = 0;
        int b = 0;
        int c = 0;
        //This loop will set up all the sub matrices neccesarry for the calculations
        for(int i = 0; i<=N; i++) {
            for(int j = 0; j<=N; j++) {
                for(int k = 0; k<=N; k++) {
                    for(int l = 0; l<=N; l++) {
                        if(k != i && l != j) {
                            C2[a][b][c] = M[k][l];
                            b+=1;
 
                            if(b==N) {
                                a+=1;
                                b=0;
                            }
 
                            if(a==N) {
                                a=0;
                                b=0;
                            }
                        }
                    }
                }
                c+=1;
            }
        }
        //This loop will replace each entry of the matrix by the determinant of their respective sub matrices
        for(int z = 0; z<Math.pow(N+1, 2); z++) {
            for(int x = 0; x<N; x++) {
                for(int y = 0; y<N; y++) {
                    C3[x][y] = C2[x][y][z];
                }
            }
            det[z] = getSilentDeterminant(C3, N-1);
        }
        int r = 0;
        for(int p = 0; p<=N; p++) {
            for(int q = 0; q<=N; q++) {
                M[p][q] = Math.pow(-1, p+q)*det[r];
                    if(M[p][q] == -0)
                        M[p][q] = 0;
                    r+=1;
                }
            }
    }
    /**
     * This method will set up the transpose matrix of the cofactor matrix
     * 
     * @param M matrix to transpose
     * @param N size of matrix
     */
    public static void getAdjointMatrix(double M[][], int N)
    {
        double[][] tempM = new double[N+1][N+1];

        for(int i = 0; i<=N; i++) {
            for(int j = 0; j<=N; j++) {
                tempM[i][j] = M[i][j];
            }
        }
        for(int x = 0; x <= N; x++) {
            for(int y = 0; y<=N; y++) {
                M[y][x] = Double.valueOf(df.format(tempM[x][y]));
            }
        }
    }
    /**
    * This method will find the inverse matrix by multiplying the inverse of the matrix determinant
    * by it's adjacent matrix
    * 
    * @param M matrix
    * @param N size of matrix
    * @param det determinant of initial matrix inputted by user
    */
    public static void getInverse(double[][] M, int N, double det) {
        for(int i = 0; i<=N; i++) {
            for(int j = 0; j<=N; j++) {
                if(M[i][j] != 0)
                    M[i][j] = Double.valueOf(df.format((1/det)*M[i][j]));
            }
        }
    }
    /**
     * This method will allow for the sorting of the rows of a square matrix in ascending order
     * 
     * @param M matrix to sort
     * @param N size of the square matrix
     * @return K amount of rows that were swtiched in the sorting
     */
    public static Integer mSort(double M[][], int N)
    {
        int amountOf0=0;
        ArrayList<Double>[] mLibrary = new ArrayList[N+3];
        ArrayList<Double> mList = new ArrayList<>(); 

        double[][] C = new double[N+1][N+1];

        //Setting up the duplicate of the initial matrix which will come in useful to calculate 
        //the total amount of permutations done by the sorting algorithm
        for(int t = 0; t<=N; t++) {
            for(int s = 0; s<=N; s++) {
                C[t][s] = M[t][s];
            }
        }
        for(int x = 0; x<N+3; x++)
            mLibrary[x] = new ArrayList<Double>();
        //Finds the amount of leading zeros in each row
        for(int i = 0; i<=N; i++) {
            for(int j = 0; j<=N; j++) {
                if(M[i][j] == 0)
                    amountOf0 += 1;
                else
                    break; 
            }
            //Adds the entries of the row into an Arraylist corresponding to its amount of leading 0s
            for(int k = 0; k<=N; k++)
                mLibrary[amountOf0].add(M[i][k]);
            amountOf0 = 0;
        }
        //Adding the rows in order of amount of 0s into an Arraylist
        for(int u = 0; u<N+3; u++)
            mList.addAll(mLibrary[u]);
        int c = 0;
        //Converting the Arraylist of sorted entires into the nxn matrix
        for(int a = 0; a<=N; a++) {
            for(int b = 0; b<=N; b++) {
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
     * This method will convert the string input into the nx1 B vector
     * 
     * @param B     solution vector
     * @param ans   string input from user
     * @param N     length of vector
     */
    public static void setB(double[] B, String ans, int N) {
        String[] entries = ans.split(" ");
            if(entries.length != N+1)
                throw new IllegalArgumentException("The vector B is out of bound with the coefficient matrix");
            for(int i = 0; i<=N; i++)
                B[i] = Double.valueOf(entries[i]);
    }
    /**
     * This method will perform the matrice multiplication between the inverse of the unputed matrix
     * with the B vector 
     * 
     * @param B     solution vector
     * @param M     inputted matrix
     * @param N     size of the square matrix + length of the solution vector
     */
    public static void getX(double[] B, double[][] M, int N)
    {
        double[] X = new double[N+1];
        double[][] C = new double[N+1][N+1];

        for(int a = 0; a<=N; a++) {
            for(int b = 0; b<=N; b++)
                C[a][b] = M[a][b];
        }
    
        double det = getSilentDeterminant(C,N);

        if(det != 0) {
            //Find the inverse matrix
            getCofactorMatrix(M, N);
            getAdjointMatrix(M, N);
            getInverse(M,N,det);

            //Matrice multiplication
            for(int i = 0; i<=N; i++) {
                for(int j = 0; j<=N; j++) {
                    X[i] += B[j]*M[i][j];
                }
            }
            System.out.print("\n>> X - The value of the variable vector is : ");
            //Print the X vector
            System.out.print("[");
            for(int k = 0; k<N; k++)
                System.out.print(df.format(X[k])+",");
            System.out.print(df.format(X[N]) + "]\n");
        }
        else
        System.out.println("\n>> ERROR : The matrix is not invertible");
    }
    /**
     * Method multiplying two matrices if the first matrix's length corresponds to the second matrix's height
     * @param m1 first matrix
     * @param m2 second matrix
     * @return the product matrix
     */
    public static double[][] mMultiply(double[][] m1, double[][] m2) {
        if(m1[0].length != m2.length)
            throw new IllegalArgumentException("These two matrices can't be multiplied together ! ");

        double[][] m3 = new double[m1.length][m2[0].length];

        for(int i = 0; i<m1.length; i++) {
            for(int j = 0; j<m2[0].length; j++) {
                double sum = 0;
                for(int a = 0; a<m1[0].length; a++) {
                        sum += m1[i][a]*m2[a][j];
                }
                m3[i][j] = sum;
            }
        }
        return m3;
    }

    public static void main(String[] args) {
        double[][] a = {{1},{4},{9}};
        double[][] b = {{23,4}};

        //System.out.println(a[0].length);
        //System.out.println(b.length);

        printMatrix(mMultiply(a, b));

    }
    

}
