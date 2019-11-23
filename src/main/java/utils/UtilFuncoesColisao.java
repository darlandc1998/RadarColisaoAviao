package utils;

import modelos.Aviao;


public class UtilFuncoesColisao {

    public static void main (String args[]){        
        Aviao aviao = new Aviao();
        aviao.setX(4.0);
        aviao.setY(5.0);
        aviao.setAngulo(70.0);
        
        Aviao aviaoX = new Aviao();
        aviaoX.setX(30.0);
        aviaoX.setY(-8.0);
        aviaoX.setAngulo(125.0);
        
        //Y1
            Double m1 = Math.tan(Math.toRadians(aviao.getAngulo()));
            double A1 = -1 * (m1 * aviao.getX());
            double A2 = aviao.getY();
            
            //Y2
            Double m2 = Math.tan(Math.toRadians(aviaoX.getAngulo()));
            double B1 = -1 * (m2 * aviaoX.getX());
            double B2 = aviaoX.getY();
            
            //CALCULO
            double var1 = A1 + A2;
            double var2 = B1 + B2;
            double somaVar = (var2 - var1);
            double somaDiv = (m1 - m2);
            
            System.out.println("VAR1 " + var1);
            System.out.println("VAR2 " + var2);
            System.out.println("SOMAVAR " + somaVar);
            System.out.println("SOMADIV " + somaDiv);
            
            double miaKhalifa =  somaVar / somaDiv;
            
            System.out.println("RESULTADO -> " + miaKhalifa);
    }
    
}
