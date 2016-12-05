package problemb;
import static java.lang.Math.*;
import java.math.*;
/**
 *
 * @author cmccloney
 */
public class solution {
    double[] v1, v2, v3, v4, m1, m2, m3, m4;
    double L1, L2, L3, L4;
    int cake;
    
    solution(double x1, double y1, double x2, double y2, double x3, double y3, 
            double x4, double y4, int count){
        v1 = new double[]{x1,y1};
        v2 = new double[]{x2,y2};
        v3 = new double[]{x3,y3};
        v4 = new double[]{x4,y4};
        m1 = new double[]{((x1+x2)/2),((y1+y2)/2)};
        m2 = new double[]{((x2+x3)/2),((y2+y3)/2)};
        m3 = new double[]{((x3+x4)/2),((y3+y4)/2)};
        m4 = new double[]{((x4+x1)/2),((y4+y1)/2)};
        L1 = sqrt(Math.pow((v1[0]-v2[0]),2)+Math.pow((v1[1]-v2[1]),2));
        L2 = sqrt(Math.pow((v2[0]-v3[0]),2)+Math.pow((v2[1]-v3[1]),2));
        L3 = sqrt(Math.pow((v3[0]-v4[0]),2)+Math.pow((v3[1]-v4[1]),2));
        L4 = sqrt(Math.pow((v4[0]-v1[0]),2)+Math.pow((v4[1]-v1[1]),2));
        cake = count + 1;
        solve();
    }
    
    public double areaQuadrilateral(double[] V1, double[] V3, double S1, 
            double S2, double S3, double S4){
        double diagonal = sqrt(Math.pow((V3[1]-V1[1]),2) + 
                Math.pow((V3[0]-V1[0]),2));
        double area = areaTriangle(S1, S2, diagonal) + areaTriangle(S3, S4, diagonal);
        return area;
    }
    
    public double areaTriangle(double S1, double S2, double S3){
        // courtesy of Heron's formula
        double p, area;
        if(S1 != S2){
            p = (S1 + S2 + S3) / 2;
            area = Math.sqrt(p*(p-S1)*(p-S2)*(p-S3));
        }else{
            area = (S1 * S2) / 2;
        }
        return area;
    }
    
    public void solve(){ //This function uses four techniques to cut the quadrilateral in half as evenly as possible,
                         //and then measures the areas of the halves and prints out the correct answer.
        double[][] areas = new double[4][2];
        double area1, area2, length = 0.0;
        int x = 0;
        //Cut from vertex one to vertex three
        if(L1 == L2){
            length = Math.sqrt(Math.pow(L1, 2)+Math.pow(L2, 2));
            area1 = areaTriangle(L1,L2,length);
            area2 = area1;
        }else{
            length = Math.sqrt(Math.pow((v3[0] - v1[0]),2)+Math.pow((v3[1] - v1[1]),2));
            area1 = areaTriangle(L1,L2,length);
            area2 = areaTriangle(L3,L4,length);
        }
        areas[0] = new double[] {area1,area2};
        //Cut from midpoint two to midpoint four
        length = Math.sqrt(Math.pow((m4[1]-m2[1]),2)+Math.pow((m4[0]-m2[0]),2));
        area1 = areaQuadrilateral(v1,m2,L1,(L2 / 2),length,(L4 / 2));
        area2 = areaQuadrilateral(m4,v3,length,(L2 / 2),L3,(L4 / 2));
        areas[1] = new double[] {area1,area2};
        //Cut from vertex two to vertex four
        if(L3 == L4){
            length = Math.sqrt(Math.pow(L3, 2)+Math.pow(L4, 2));
            area1 = areaTriangle(L3,L4,length);
            area2 = area1;
        }else{
            length = Math.sqrt(Math.pow((v4[0] - v2[0]),2)+Math.pow((v4[1] - v2[1]),2));
            area1 = areaTriangle(L1,L2,length);
            area2 = areaTriangle(L3,L4,length);
        }
        length = Math.sqrt(Math.pow(L2,2)+Math.pow(L3,2));
        area1 = areaTriangle(L2,L3,length);
        areas[2] = new double[] {area1,area2};
        //Cut from midpoint one to midpoint three
        length = Math.sqrt(Math.pow((m3[1]-m1[1]),2)+Math.pow((m3[0]-m1[0]),2));
        area1 = areaQuadrilateral(m1,v3,(L1 / 2),L2,(L3 / 2),length);
        area2 = areaQuadrilateral(v1,m3,(L1 / 2),length,(L3 / 2),L4);
        areas[3] = new double[] {area1,area2};
        //Determines which of the calculated slices is the closest to cutting the cake in half
        double difference = abs(areas[0][0] - areas[0][1]);
        for(int i = 1; i < 4; i++){
            if(difference > abs(areas[i][0] - areas[i][1])){
                difference = abs(areas[i][0] - areas[i][1]);
                x = i;
            }
        }
        System.out.printf("Cake " + cake + ": %.3f %.3f\n",areas[x][0],areas[x][1]);
    }
}
