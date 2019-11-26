package utils;

import java.util.List;
import javax.swing.DefaultListModel;
import modelos.Aviao;
import modelos.Colisao;

public final class UtilFuncoesColisao {

    public static void calcularRotasDeColisao(Aviao aviao, List<Aviao> avioes, Colisao colisao, DefaultListModel listaRelatorio) {
        listaRelatorio.addElement("");
        listaRelatorio.addElement("");        
        listaRelatorio.addElement("");        
        listaRelatorio.addElement("");        
        listaRelatorio.addElement("------ Avião " + aviao.getModelo() + " ------");

        for (Aviao aviaoX : avioes) {
            if (!aviaoX.getId().equals(aviao.getId())) {
                //CALCULA COEFICIENTE ANGULAR
                Double m1 = Math.tan(Math.toRadians(aviao.getAngulo()));
                Double m2 = Math.tan(Math.toRadians(aviaoX.getAngulo()));

                System.out.println("COEFICIENTE ANGULAR DO AVIÃO 1: " + m1);
                System.out.println("COEFICIENTE ANGULAR DO AVIÃO 2: " + m2);

                if ((m1 >= 0 && m2 < 0) || m1 < 0 && m2 > 0) {
                    //Y1
                    double A1 = -1 * (m1 * aviao.getX());
                    double A2 = aviao.getY();

                    //Y2
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

                    //ENCONTRANDO PONTO DE ENCONTRO EM X
                    double pontoX = somaVar / somaDiv;
                    System.out.println("OS AVIOES SE ENCONTRAM NO PONTO X EM: " + pontoX);

                    //ENCONTRANDO PONTO DE ENCONTRO EM Y
                    double pontoY = (m1 * pontoX) - (m1 * aviao.getX()) + aviao.getY();
                    System.out.println("OS AVIOES SE ENCONTRAM NO PONTO Y EM: " + pontoY);

                    //DISTANCIA ATÉ O PONTO DE ENCONTRO AVIAO 1
                    double distAviao1 = Math.sqrt((Math.pow(pontoX - aviao.getX(), 2)) + Math.pow(pontoY - aviao.getY(), 2));
                    System.out.println("A DISTÂNCIA ATÉ O PONTO DE ENCONTRO DO AVIÃO 1 É : " + distAviao1);

                    //DISTANCIA ATÉ O PONTO DE ENCONTRO AVIAO 2
                    double distAviao2 = Math.sqrt((Math.pow(pontoX - aviaoX.getX(), 2)) + Math.pow(pontoY - aviaoX.getY(), 2));
                    System.out.println("A DISTÂNCIA ATÉ O PONTO DE ENCONTRO DO AVIÃO 2 É : " + distAviao2);

                    //TEMPO QUE O AVIAO 1 IRÁ LEVAR ATÉ O PONTO DE ENCONTRO
                    double tempoAviao1 = (distAviao1 / aviao.getVelocidade()) * 3600;
                    System.out.println("TEMPO QUE O AVIÃO 1 IRÁ LEVAR ATÉ O PONTO DE ENCONTRO: " + tempoAviao1 + " segundos");

                    //TEMPO QUE O AVIAO 2 IRÁ LEVAR ATÉ O PONTO DE ENCONTRO
                    double tempoAviao2 = (distAviao2 / aviaoX.getVelocidade()) * 3600;
                    System.out.println("TEMPO QUE O AVIÃO 2 IRÁ LEVAR ATÉ O PONTO DE ENCONTRO: " + tempoAviao2 + " segundos");

                    //DIFERENÇA DE TEMPO EM QUE OS AVIÕES PASSAM PELO PONTO DE ENCONTRO:
                    double tempoColisao = Math.abs((tempoAviao1 - tempoAviao2));
                    System.out.println("DIFERENÇA DE TEMPO EM QUE OS AVIÕES PASSAM PELO PONTO DE ENCONTRO: " + tempoColisao + " segundos");

                    if (tempoColisao <= colisao.getTempoMinimo()) {
                        listaRelatorio.addElement("Avião " + aviao.getModelo() + " em colisão com " + aviaoX.getModelo() + " em " + tempoColisao + " segundos. ");
                    }

                } else {
                    System.out.println("OS AVIÕES ESTÃO VOANDO EM PARALELO");
                }

            }

        }
        
        listaRelatorio.addElement("------ Análise completa ------");
        listaRelatorio.addElement("");

    }

}
