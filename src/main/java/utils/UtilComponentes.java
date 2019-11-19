package utils;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import modelos.Aviao;

public final class UtilComponentes {

    public static final Integer WIDTH_PANEL = 470;
    public static final Integer HEIGHT_PANEL = 340;

    //Quando for usar alterar esse caminho conforme o do pc utilizado
    private static final String URL_IMAGEM = "/home/darlan/Documentos/Projetos/Java/Radar Colisao Avião/src/main/java/imagens/airplane.png";

    public static JLabel getIconeAviao(Aviao aviao) {
        ImageIcon icone = new ImageIcon(URL_IMAGEM);
        
        JLabel iconeAviao = new JLabel(icone){            
            
            @Override
            protected void paintComponent(Graphics g) {
                /*Graphics2D gx = (Graphics2D) g;
                gx.rotate(0.1);*/ //Rotaciona a imagem (Ficou a bela de uma bosta)
                super.paintComponent(g);
            }
            
        };
        
        iconeAviao.setBounds(getXCoordinate(aviao), getYCoordinate(aviao), 24, 24); // Localização do avião nos eixos x e y;             
        
        return iconeAviao;
    }

    private static int getXCoordinate(Aviao aviao) {
        int boundX = WIDTH_PANEL / 2;
        boundX += aviao.getX();
        return boundX;
    }

    private static int getYCoordinate(Aviao aviao) {
        int boundY = HEIGHT_PANEL / 2;
        boundY += (aviao.getY() * -1);
        return boundY;
    }

}
