package utils;

import modelos.Aviao;
import modelos.Coordenada;

public final class UtilFuncoesTransformacao {

    public static void translandar(Aviao aviao, Coordenada coordenada) {
        aviao.setX(aviao.getX() + coordenada.getX());
        aviao.setY(aviao.getY() + coordenada.getY());
    }

    public static void escalonar(Aviao aviao, Coordenada coordenada) {
        aviao.setX(aviao.getX() * (coordenada.getX() / 100));
        aviao.setY(aviao.getY() * (coordenada.getY() / 100));
    }

    public static void rotacionar(Aviao aviao, Coordenada coordenada) {
        Double xPontoFixo = coordenada.getX() == null ? 0 : coordenada.getX();
        Double yPontoFixo = coordenada.getY() == null ? 0 : coordenada.getY();

        Double xCal = aviao.getX() - xPontoFixo;
        Double yCal = aviao.getY() - yPontoFixo;
        Double anguloCal = Math.toRadians(coordenada.getAngulo());
        
        Double novoX = xCal * Math.cos(anguloCal) - yCal * Math.sin(anguloCal);
        Double novoY = yCal * Math.cos(anguloCal) + xCal * Math.sin(anguloCal);

        novoX += xPontoFixo;
        novoY += yPontoFixo;

        aviao.setX(novoX);
        aviao.setY(novoY);
    }

}
