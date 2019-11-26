package utils;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import modelos.Aviao;

public final class UtilComponentes {

    public static final Integer WIDTH_PANEL = 420;
    public static final Integer HEIGHT_PANEL = 340;

    //Quando for usar alterar esse caminho conforme o do pc utilizado
    private static final String URL_IMAGEM = "/home/darlan/Documentos/Projetos/Java/Radar Colisao Avião/src/main/java/imagens/airplane.png";
    private static final String URL_RADAR = "/home/darlan/Documentos/Projetos/Java/Radar Colisao Avião/src/main/java/imagens/radar2.gif";
    
    public static JLabel getRadar(){
        ImageIcon icone = new ImageIcon(URL_RADAR);
        JLabel radar = new JLabel(icone);
        radar.setBounds(0, 0, 420, 340);
        return radar;
    }
    
    public static JLabel getIconeAviao(Aviao aviao) {
        ImageIcon icone = new ImageIcon(URL_IMAGEM) {
            
            @Override
            public synchronized void paintIcon(Component c, Graphics g, int x, int y) {
                //Parte responsavel por rotacionar os aviões;
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                g2.translate(getIconWidth() / 2, getIconHeight() / 2);
                g2.rotate(Math.toRadians(aviao.getAngulo()));
                g2.translate(-getIconWidth() / 2, -getIconHeight() / 2);
                super.paintIcon(c, g, x, y);
            }

        };

        JLabel iconeAviao = new JLabel(icone);
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
