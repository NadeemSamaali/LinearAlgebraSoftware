package operations;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/**
 * This class contains all the most recurring methods when it comes to working with matrices
 * within the App file (converting the user entires into a matrix, printing entries, printing a matrix)
 * 
 * @author Nadeem Samaali
 * @version 2.2.1 | Added new throw statements
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

    public static double[][] copyMatrix(double[][] M) {
        double[][] A = new double[M.length][M[0].length];
        for(int i = 0; i<M.length; i++) {
            for(int j = 0; j<M[0].length; j++) {
                A[i][j] = M[i][j];
            }
        }
        return A;
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
                char[] E = df.format(M[b][a]).toCharArray();
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
                        System.out.print("  " + df.format(M[i][j]));
                        char[] E = df.format(M[i][j]).toCharArray();
                        //System.out.print(" E length = " + E.length);
                            for(int x = 0; x<maxNum[j] - E.length; x++) {
                                System.out.print(" ");
                            }
                    }
                    if(M[i][j] < 0) {
                        System.out.print(" " + df.format(M[i][j]));
                        char[] E = df.format(M[i][j]).toCharArray();
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
                char[] E = df.format(M[b][a]).toCharArray();
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
                        if(Double.valueOf(df.format(M[i][j])) == -0)
                            M[i][j] = 0;
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
     * This methods desplays the steps taken to simplify the matrix into uppertriangular form
     * @param M The initial matrix
     */
    public static double[][] determinantSteps(double[][] A) {
        
        if(A.length != A[0].length)
            throw new IllegalArgumentException("Determinants can only be calculated in square matrices");

        double[][] M = copyMatrix(A);

        double permutations = 0;
        ArrayList<Double> k = new ArrayList<>();
        double[][] C = new double[M.length][M.length];

        if(needsSort(M)) {
            C=copyMatrix(M);
            M = mSort(M);
            permutations += nPermutations(C, M);
            System.out.println("\n   Sorted the matrix\n");
            printDeterminant(M);
        }
        
        for(int i = 0; i<M.length; i++) {
            for(int j = i; j<M.length; j++) {
                if(M[i][j] != 0) {
                    for(int a = i+1; a<M.length; a++) {
                        if(M[a][j] != 0) {
                            k.add(M[i][j] / M[a][j]);

                            for(int b = 0; b<M.length; b++)
                                M[a][b] *= k.get(k.size()-1);
                            System.out.println("\n   " + k.get(k.size()-1) + " * R" + (a+1) + "\n");
                            printDeterminant(M);
                            for(int c = 0; c<M.length; c++)
                                M[a][c] -= M[i][c];
                            System.out.println("\n   R"+(a+1)+" - R"+(i+1)+" --> R" + (a+1) + "\n"); 
                            printDeterminant(M);
                        } 
                    }
                }
                if(needsSort(M)) {
                    C = copyMatrix(M);
                    M = mSort(M);
                    permutations += nPermutations(C, M);
                    System.out.println("\n   Sorted the matrix\n");
                    printDeterminant(M);
                } 
                break;
            }
        }

        System.out.print("\n   |M| = ");
        for(int x = 0; x<k.size(); x++) {
            if(k.get(x) != 1)
                System.out.print("("+df.format(1/k.get(x))+")");
        }
        for(int y = 0; y<M.length; y++) {
            if(M[y][y] != 1)
                System.out.print("("+df.format(M[y][y])+")");
        }
        if(Double.valueOf(df.format(Math.pow(-1,permutations))) != 1)
            System.out.print("("+df.format(Math.pow(-1,permutations))+")");

        System.out.println();


        return M;
    }
    /**
     * Method returning the determinant of a matrix
     * @param M initial matrix
     * @return determinant
     */
    public static double getDeterminant(double[][] A) {
        double[][] M = copyMatrix(A);

        if(A.length != A[0].length)
            throw new IllegalArgumentException("Determinants can only be calculated in square matrices");

        double determinant = 1, permutations = 0;
        ArrayList<Double> k = new ArrayList<>();
        double[][] C = new double[M.length][M.length];

        if(needsSort(M)) {
            C=M;
            M = mSort(M);
            permutations += nPermutations(C, M);
        }
        for(int i = 0; i<M.length; i++) {
            for(int j = i; j<M.length; j++) {
                if(M[i][j] != 0) {
                    for(int a = i+1; a<M.length; a++) {
                        if(M[a][j] != 0) {
                            k.add(M[i][j] / M[a][j]);
                            for(int b = 0; b<M.length; b++)
                                M[a][b] *= k.get(k.size()-1);
                            for(int c = 0; c<M.length; c++)
                                M[a][c] -= M[i][c];
                        } 
                    }
                }
                if(needsSort(M)) {
                    C = copyMatrix(M);
                    M = mSort(M);
                    permutations += nPermutations(C, M);
                } 
                break;
            }
        }

        for(int x = 0; x<k.size(); x++)
            determinant *= 1/k.get(x);
        for(int y =0; y<M.length; y++)
            determinant *= M[y][y];
        determinant *= Math.pow(-1, permutations);
        return determinant;
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
                //printDeterminant(C0.get(c));
                //System.out.println();
                //System.out.println(getDeterminant(C0.get(c)));
                //System.out.println();

                
                C[i][j]=Math.pow(-1,i+j)*getDeterminant(C0.get(c));
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
                A[y][x] = M[x][y];
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

        double det = getDeterminant(M);
        if(Double.valueOf(d0.format(det)) == 0)
            throw new IllegalArgumentException("This matrix is not invertible");

        double[][] I = getAdjointMatrix(M);
    
        for(int i = 0; i<M.length; i++) {
            for(int j = 0; j<M.length; j++) {
                if(I[i][j] != 0)
                    I[i][j] = (1/det)*I[i][j];
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
     * Checks if a matrix needs to be sorted
     * @param M matrix to check
     * @return true if the matrix needs to be sorted
     */
    public static boolean needsSort(double[][] M) {
        double[][] S = mSort(M);

        for(int i = 0; i<M.length; i++) {
            if(!Arrays.equals(S[i],M[i]))
            return true;
        }
        return false;
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
        double[][] M = {{0,0,1,2},{1,2,3,4},{0,0,0,1},{0,1,2,3}};
        double[][] M1 = new double[M.length][M.length];
        

        printMatrix(mSort(M));


        //System.out.println(getDeterminant(M));

        //System.out.println(getDeterminant(getInverse(M)));
    }
}
