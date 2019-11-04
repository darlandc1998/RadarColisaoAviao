package modelos;

public class Coordenada {

    private Double x;
    private Double y;
    private Double angulo;

    public Coordenada() {
    }

    public Coordenada(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Coordenada(Double x, Double y, Double angulo) {
        this.x = x;
        this.y = y;
        this.angulo = angulo;
    }
    
    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getAngulo() {
        return angulo;
    }

    public void setAngulo(Double angulo) {
        this.angulo = angulo;
    }
    
}
