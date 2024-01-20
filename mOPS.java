import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/**
 * This class contains all the most recurring methods when it comes to working with matrices
 * within the App file (converting the user entires into a matrix, printing entries, printing a matrix)
 * 
 * @author Nadeem Samaali
 * @version 2.1.3 | Code optimization
 */
public class mOPS {

    static DecimalFormat df = new DecimalFormat("0.000");
    static DecimalFormat d0 = new DecimalFormat("0.000000000");
    Scanner input = new Scanner(System.in);
    /**
     * This method inquires the user on the entries to add into each row of the N sized
     * 
     * @param height first coordinate of the matrix size
     * @param length second coordinate of the matrix size
     */
    public static double[][] setMatrix(int height, int length) {
        
        Scanner in = new Scanner(System.in);
        double[][] M = new double[height][length];

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
        return M;
    } 
    /**
     * This method allows for the printing of any nXn matrix | To use for testing purposes
     * 
     * @param M matrix to print
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
                        System.out.print("  " + Double.valueOf(df.format(M[i][j])));
                        char[] E = String.valueOf(M[i][j]).toCharArray();
                        //System.out.print(" E length = " + E.length);
                            for(int x = 0; x<maxNum[j] - E.length; x++) {
                                System.out.print(" ");
                            }
                    }
                    if(M[i][j] < 0) {
                        System.out.print(" " + Double.valueOf(df.format(M[i][j])));
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
     * This method allows for the printing of any nXn matrix in the determinant bracket form
     * 
     * @param M matrix to print
     */
    public static void printDeterminant(double[][] M) {

        int currentNum = 0;
        int length = M[0].length;
        int height = M.length;
        int[] maxNum = new int[length];

        //Check for the number with the biggest amount of figures per column
        for(int a = 0; a<length; a++) {
            for(int b = 0; b<height; b++) {
                char[] E = df.format(M[a][b]).toCharArray();
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
            System.out.print("   ");
                for(int j = 0; j<length; j++) {
                    if(M[i][j] >= 0) {
                        if(j>0)
                            System.out.print("  " + df.format(M[i][j]));
                        else
                            System.out.print("| " + df.format(M[i][j]));
                        char[] E = df.format(M[i][j]).toCharArray();
                        //System.out.print(" E length = " + E.length);
                            for(int x = 0; x<maxNum[j] - E.length; x++) {
                                System.out.print(" ");
                            }
                    }
                    if(M[i][j] < 0) {
                        if(j>0)
                            System.out.print(" " + df.format(M[i][j]));
                        else
                            System.out.print("|" + df.format(M[i][j]));

                        char[] E = df.format(M[i][j]).toCharArray();
                        //System.out.print(" E length = " + E.length);
                        for(int x = 0; x<=maxNum[j] - E.length; x++) {
                            System.out.print(" ");
                        }
                    }
                }
                System.out.print(" |");
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
     * @return determinant
     */    
    public static double getDeterminant(double[][] M)
    {
        int N = M.length;
        //calculating determinant
        double num1 = 1.0;
        int amountOfK = 0;
            for(int v = 0; v<N; v++)
                amountOfK += v;
                        double[] k = new double[amountOfK];
        for(int m = 0; m<amountOfK; m++)
            k[m] = 1.0;
        int num0 = -1;

        double[][] C = new double[N][N];

        System.out.println("\n>> Steps to solution :\n");

        C = M;
        M = mSort(M);
        if(nPermutations(C, M) != 0) {
            System.out.println("\n   Sorted the matrix : \n");
            mOPS.printMatrix(M);
        }

        int kFactor = nPermutations(C, M);

        for(int d = 0; d<N; d++) {  
            /*This loop will multiply each row R>R1 by a factor k, where k is the factor needed to tranforms
            *the entry of the first row into the entry located at [0][0] as well was multiplying the remainer
            *of the entires of this row by that same scalar k
            */
            for(int x = 0; x<N-d; x++) {  
                if(M[x][x] != 0) {
                    for(int y = 1; y < N-(x)-d; y++) {
                        if(M[x+y][x] != 0) {
                            num0 += 1;
                            k[num0] = (M[x][x]/M[x+y][x]);
                                if(k[num0] != 1) {
                                    System.out.print("\n   K = M(" + x + "," + x + ")" + "/M(" + (d+y) + "," + x + ")");
                                    System.out.print(" --> " + df.format(k[num0]) + "*R" + (x+y+1) +"\n\n");
                                }
                                for(int i = 0; i<N; i++) {
                                    M[x+y][i] = k[num0]*M[x+y][i];
                                    if(M[x+y][i] == -0.0)
                                        M[x+y][i] = 0;
                                }
                                mOPS.printDeterminant(M);
                                for(int l = 0; l<N; l++) {
                                    M[x+y][l] = M[x+y][l]-M[x][l];

                                    if(M[x+y][l] == -0.0)
                                        M[x+y][l] = 0;
                                }
                            System.out.println("\n   R" + (x+y+1) + " - R" + (x+1) + " --> R" +(x+y+1)+"\n");
                            mOPS.printDeterminant(M);
                        }                       
                    }
                }  
            }
        }
        C = M;
        M = mSort(M);
        kFactor += nPermutations(C, M);
        System.out.println("\n   Sorted the matrix\n");
        printDeterminant(M);
            for(int n0 = 0; n0<amountOfK; n0++) {
                if(1/k[n0] == 0) 
                    k[n0] = 1.0;
                else
                    k[n0] = k[n0];
            }
        System.out.println();
        System.out.print("   det(M) = ");
            for(int u = 0; u<amountOfK; u++) {
                num1 *=(1/k[u]);
                if(1/k[u] != 1)
                    System.out.print(df.format(1/k[u]) + " * ");
            }
            for(int r = 0; r<N; r++) {
                    System.out.print(df.format(M[r][r]) + " * ");
                    num1 = M[r][r]*num1;
            }
        num1 *= Math.pow(-1,kFactor);
        System.out.print(Math.pow(-1,Double.valueOf(df.format(kFactor))));
        System.out.println("\n\n>> The determinant of this matrix is : " + df.format(num1)); 
        return num1;
    }
    /**
     * Silent version of the getDeterminant method -- Will calculate the determinant of a matrix
     * without printing out all the steps and rather simply return the value of the determinant
     * 
     * @param M
     * @return num1 -- the value of the determinant
     */
    public static double getSilentDeterminant(double[][] M) {
        int N = M.length;
        //calculating determinant
        double num1 = 1.0;
        int amountOfK = 0;
            for(int v = 0; v<N; v++)
                amountOfK += v;
                        double[] k = new double[amountOfK];
        for(int m = 0; m<amountOfK; m++)
            k[m] = 1.0;
        int num0 = -1;

        double[][] C = new double[N][N];

        C = M;
        M = mSort(M);
        int kFactor = nPermutations(C, M);

        for(int d = 0; d<N; d++) {  
            /*This loop will multiply each row R>R1 by a factor k, where k is the factor needed to tranforms
            *the entry of the first row into the entry located at [0][0] as well was multiplying the remainer
            *of the entires of this row by that same scalar k
            */
            for(int x = 0; x<N-d; x++) {  
                if(M[x][x] != 0) {
                    for(int y = 1; y < N-(x)-d; y++) {
                        if(M[x+y][x] != 0) {
                            num0 += 1;
                            k[num0] = (M[x][x]/M[x+y][x]);
                                for(int i = 0; i<N; i++) {
                                    M[x+y][i] = k[num0]*M[x+y][i];
                                    if(M[x+y][i] == -0.0)
                                        M[x+y][i] = 0;
                                }
                                for(int l = 0; l<N; l++) {
                                    M[x+y][l] = M[x+y][l]-M[x][l];

                                    if(M[x+y][l] == -0.0)
                                        M[x+y][l] = 0;
                                }
                        }                       
                    }
                }  
            }
        }
        C = M;
        M = mSort(M);
        kFactor += nPermutations(C, M);
            for(int n0 = 0; n0<amountOfK; n0++) {
                if(1/k[n0] == 0) 
                    k[n0] = 1.0;
                else
                    k[n0] = k[n0];
            }
            for(int u = 0; u<amountOfK; u++) {
                num1 *=(1/k[u]);
            }
            for(int r = 0; r<N; r++) {
                    num1 = M[r][r]*num1;
            }
        num1 *= Math.pow(-1,kFactor);
        return num1;
        
    }
    /**
    * This method will convert the inputted square matrix into its cofactor matrix
    * 
    * @param M the inputted matrix
    */   
    public static double[][] getCofactorMatrix(double[][] M) {

        int N = M.length;

        double[][] C = new double[N][N];
        ArrayList<double[][]> C0 = new ArrayList<>();
        double[][] C2 = new double[N-1][N-1];
 
        int a = 0;
        int b = 0;
        int c = 0;
        //This loop will set up all the sub matrices neccesarry for the calculations
        for(int i = 0; i<N; i++) {
            for(int j = 0; j<N; j++) {
                for(int k = 0; k<N; k++) {
                    for(int l = 0; l<N; l++) {
                        if(k != i && l != j) {
                            C2[a][b] = M[k][l];  
                            b+=1;
                                if(b==N-1) {
                                    a+=1;
                                    b=0;
                                }
                                if(a==N-1) {
                                    a=0;
                                    b=0;
                                }
                        }
                    }
                }
                C0.add(C2);
                C[i][j]=Math.pow(-1,i+j)*getSilentDeterminant(C0.get(c));
                    if(C[i][j] == -0)
                        C[i][j] = 0;
            }
            c += 1;
        }
        return C;
    }
    /**
     * This method will set up the transpose matrix of the cofactor matrix
     * 
     * @param M matrix to transpose
     * @param N size of matrix
     */
    public static double[][] getAdjointMatrix(double M[][])
    {
        M = getCofactorMatrix(M);
        double[][] A = new double[M.length][M.length];

        for(int x = 0; x <M.length; x++) {
            for(int y = 0; y<M.length; y++) {
                A[y][x] = Double.valueOf(df.format(M[x][y]));
            }
        }
        return A;
    }
    /**
    * This method will find the inverse matrix by multiplying the inverse of the matrix determinant
    * by it's adjacent matrix
    * 
    * @param M matrix
    * @param N size of matrix
    * @param det determinant of initial matrix inputted by user
    */
    public static double[][] getInverse(double[][] M) {
        double det = getSilentDeterminant(M);
        if(Double.valueOf(d0.format(det)) == 0)
            throw new IllegalArgumentException("This matrix is not invertible");

        double[][] I = getAdjointMatrix(M);
    
        for(int i = 0; i<M.length; i++) {
            for(int j = 0; j<M.length; j++) {
                if(I[i][j] != 0)
                    I[i][j] = Double.valueOf(df.format((1/det)*I[i][j]));
            }
        }
        return I;
    }
    /**
     * This method will allow for the sorting of the rows of a square matrix in ascending order
     * 
     * @param M matrix to sort
     * @return the sorted matrix
     */
    public static double[][] mSort(double[][] M) {
        double[][] S = new double[M.length][M.length];
        ArrayList<Double>[] rows = new ArrayList[M.length+1];
        ArrayList<Double> list = new ArrayList<>();
        int k0 = 0;
        int z = 0;
            for(int a = 0; a<=M.length; a++)
                rows[a] = new ArrayList<>();
            for(int i = 0; i<M.length; i++) {
                for(int j = 0; j<M.length; j++) {
                    if(M[i][j] == 0)
                        k0 += 1;
                    else
                        break;
                }
                for(int k = 0; k<M.length; k++)
                    rows[k0].add(M[i][k]);
                k0 = 0;
            }
            for(int l = 0; l<=M.length; l++)
                list.addAll(rows[l]);
            for(int x = 0; x<M.length; x++) {
                for(int y = 0; y<M.length; y++) {
                    S[x][y] = list.get(z);
                    z += 1;
                }
            }
        return S;
    }
    /**
     * Method calculating the amount of row permutations done within a matrix by comparing the inital and final matrix
     * @param I Initial matrix
     * @param F Final matrix
     * @return the amount of permutations
     */
    public static Integer nPermutations(double[][] I, double[][] F) {
        int K = 0;
            for (int i = 0; i < I.length; i++) {
                if (!Arrays.equals(I[i], F[i])) {
                    // Find the row in the remaining part of the matrix where the swap is needed
                    for (int j = i + 1; j < I.length; j++) {
                        if (Arrays.equals(I[j], F[i])) {
                            // Swap rows
                            double[] temp = I[i];
                            I[i] = I[j];
                            I[j] = temp;

                            K++;
                            break;
                        }
                    }
                }
            }
        return K;
    }
    /**
     * This method will perform the matrice multiplication between the inverse of the unputed matrix
     * with the B vector 
     * 
     * @param B     solution vector
     * @param M     inputted matrix
     */
    public static double[][] getX(double[][] B, double[][] M)
    {
        double[][] b = new double[M.length][1];
        for(int i = 0; i<M.length; i++)
            b[i][0] = B[0][i];

        double[][] X = mMultiply(getInverse(M), b);
        return X;
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
    }
}
