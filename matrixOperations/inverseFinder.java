package matrixOperations;
import java.util.Scanner;

public class inverseFinder 
{
    /**
     * This method will convert the inputted square matrix into its cofactor matrix
     * 
     * @param M the inputted matrix
     * @param N the size of the matrix
     */   

    public static void getCofactorMatrix(double[][] M, int N)
    {
        double[][][] C2 = new double[N][N][(int) Math.pow(N+1,2)];
        double[][] C3 = new double[N][N];
        double[] det = new double[(int)Math.pow(N+1,2)];

        int a = 0;
        int b = 0;
        int c = 0;


        //This loop will set up all the sub matrices neccesarry for the calculations
        for(int i = 0; i<=N; i++)
        {
            for(int j = 0; j<=N; j++)
            {
                for(int k = 0; k<=N; k++)
                {
                    for(int l = 0; l<=N; l++)
                    {
                        if(k != i && l != j)
                        {
                            C2[a][b][c] = M[k][l];
                            //System.out.println("("+a+";"+b+") " + c + " = " + M[k][l]);
                            b+=1;

                            if(b==N)
                            {
                                a+=1;
                                b=0;
                            }

                            if(a==N)
                            {
                                a=0;
                                b=0;
                            }
                        }
                    }
                }

                c+=1;
            }
        }

    
        for(int z = 0; z<Math.pow(N+1, 2); z++)
        {
            for(int x = 0; x<N; x++)
            {
                for(int y = 0; y<N; y++)
                {
                    C3[x][y] = C2[x][y][z];
                }
            }

            det[z] = detFinder.getDeterminant(C3, N-1);

            //det[z] = detFinder.getDeterminant(C3, (N-1));
            //System.out.println(det[z]);
        }

        int r = 0;

            for(int p = 0; p<=N; p++)
            {
                for(int q = 0; q<=N; q++)
                {
                    M[p][q] = Math.pow(-1, p+q)*det[r];

                    if(M[p][q] == -0)
                        M[p][q] = 0;

                    //System.out.println(M[p][q]);
                    r+=1;
                }
            }
    
        //System.out.print("\nThe cofactor matrix of M is : ");
        //mOPS.printMatrix(M,N);
    }

    /**
     * This method will set up the transpose matrix of the cofactor matrix
     * 
     * @param M matrix to transpose
     * @param N size of matrix
     */
    public static void getAdjacentMatrix(double M[][], int N)
    {
        double[][] tempM = new double[N+1][N+1];

        for(int i = 0; i<=N; i++)
        {
            for(int j = 0; j<=N; j++)
            {
                tempM[i][j] = M[i][j];
            }
        }

        for(int x = 0; x <= N; x++)
        {
            for(int y = 0; y<=N; y++)
            {
                M[y][x] = tempM[x][y];
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

    public static void getInverse(double[][] M, int N, double det)
    {
        for(int i = 0; i<=N; i++)
        {
            for(int j = 0; j<=N; j++)
            {
                if(M[i][j] != 0)
                    M[i][j] *= (1/det);
            }
        }
    }

    public static void main(String[] args)
    {
        System.out.println("\n>> Please enter the square matrix size (1,2,...,n) : \n");
        System.out.print("U : ");
        Scanner in = new Scanner(System.in);
        String ans = in.nextLine();

        int n = Integer.valueOf(ans)-1;

        double[][] m = new double[n+1][n+1];
        double[][] d = new double[n+1][n+1];

        System.out.println("\n>> Insert the values of the entries sparated with a comma\n   respecting this form : a11,...,a1n,...,am1,...,amn\n");
        System.out.print("U : ");
        ans = in.nextLine();
        String[] entries = ans.split(",");

        mOPS.setMatrixFromString(m, n, entries);
        mOPS.setMatrixFromString(d, n, entries);
        double detM = detFinder.getSilentDeterminant(d,n);

        if(detM != 0)
        {
            getCofactorMatrix(m, n);
            getAdjacentMatrix(m,n);
            getInverse(m, n, detM);
            System.out.print("\nThe inverse of the inputed matrix is : ");
            mOPS.printMatrix(m,n);
        }
            
        else
        {
            System.out.println("\nERROR : This matrix is not invertible !");
        }
        
        

    }
}
