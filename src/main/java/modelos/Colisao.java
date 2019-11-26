package modelos;


public class Colisao {
  
    private Double disMinimaAeroporto;
    private Double disMinimaAvioes;
    private Integer tempoMinimo;
    
    public Colisao(){
        this.disMinimaAeroporto = 0d;
        this.disMinimaAvioes = 0d;
        this.tempoMinimo = 0;
    }

    public Double getDisMinimaAeroporto() {
        return disMinimaAeroporto;
    }

    public void setDisMinimaAeroporto(Double disMinimaAeroporto) {
        this.disMinimaAeroporto = disMinimaAeroporto;
    }

    public Double getDisMinimaAvioes() {
        return disMinimaAvioes;
    }

    public void setDisMinimaAvioes(Double disMinimaAvioes) {
        this.disMinimaAvioes = disMinimaAvioes;
    }

    public Integer getTempoMinimo() {
        return tempoMinimo;
    }

    public void setTempoMinimo(Integer tempoMinimo) {
        this.tempoMinimo = tempoMinimo;
    }
    
    
}
