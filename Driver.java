package problemb;
import java.util.Scanner;
import java.math.*;
/**
 *
 * @author cmccloney
 */
public class Driver {

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        double w1, w2, w3, w4, z1, z2, z3, z4;
        double[] vectors;
        int val = 0, count = 0;
        System.out.println("Enter all zeros to exit the program.");
        System.out.println("Enter the four vertices (eg, x1 y1 x2 ...) clockwise from the bottom left:");
        while(val == 0){
            w1 = read.nextDouble();
            z1 = read.nextDouble();
            w2 = read.nextDouble();
            z2 = read.nextDouble();
            w3 = read.nextDouble();
            z3 = read.nextDouble();
            w4 = read.nextDouble();
            z4 = read.nextDouble();
            vectors = new double[]{w1,w2,w3,w4,z1,z2,z3,z4};
            val = -1;
            for(int i = 0; i < vectors.length; i++){
                if(vectors[i] != 0){
                    val = 0;
                }
            }
            if(val == 0){
                solution s = new solution(w1, z1, w2, z2, w3, z3, w4, z4, count);
            }
            count++;
        }
    }
}
