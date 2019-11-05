package utils;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import modelos.Aviao;


public final class UtilComponentes {
    
    //Quando for usar alterar esse caminho conforme o do pc utilizado
    private static final String URL_IMAGEM = "C:\\Users\\lab202a\\Documents\\NetBeansProjects\\RadarColisaoAviao\\src\\main\\java\\imagens\\airplane.png";
    

    public static JLabel getIconeAviao(Aviao aviao){
        ImageIcon icone = new ImageIcon(URL_IMAGEM);        
        JLabel iconeAviao = new JLabel(icone);
        iconeAviao.setBounds(aviao.getX().intValue(), aviao.getY().intValue(), 24, 24); // Localização do avião nos eixos x e y;        
        return iconeAviao;
    }
    
}
