package vectorOperations;
import java.util.ArrayList;
import matrixOperations.detFinder;

/**
 * Class containing the method calculating the cross product of two vectors
 * 
 * @author Nadeem Samaali
 * @version 1.0.0 | First release
 */
public class crossProduct {
    
    /**
     * This method calculated the cross product between two vetors 
     * 
     * @param v1 First vector
     * @param v2 Second vector
     * @return v3 -> The cross product between v1 and v2
     */
    public static double[] getCrossProduct(double[] v1, double[] v2) {

        double[][] M = {{1,-1,1},{0,0,0},{0,0,0}};
        ArrayList<Double> C = new ArrayList<>();
        double[][] C0 = new double[2][2];
        double[] v3 = new double[3];

        if(v1.length !=3 || v2.length != 3)
            throw new IllegalArgumentException("Make sure both vectors are 3-dimensional");
        
        M[1] = v1;
        M[2] = v2;

        for(int l = 0; l<3; l++) {
            for(int i = 0; i<3; i++) {
                for(int j = 0; j<3; j++) {
                    if(i != 0 && j != l)
                    {
                        C.add(M[i][j]);
                    }
                }
            }
            int c = 0;
            for(int a = 0; a<2; a++) {
                for(int b = 0; b<2; b++) {
                    C0[a][b] = C.get(c);
                    c++;
                }
            }
            v3[l] = M[0][l]*detFinder.getSilentDeterminant(C0,1);
            C.clear();
        }
        return v3;
    }
}
