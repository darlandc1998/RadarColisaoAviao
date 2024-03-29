package com.radar;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.Aviao;
import modelos.Colisao;
import modelos.Coordenada;
import utils.UtilAviao;
import utils.UtilComponentes;
import utils.UtilFuncoesColisao;
import utils.UtilFuncoesTransformacao;

public class Radar extends javax.swing.JFrame {

    private Integer idAviaoSelecionado = null;
    private final List<Aviao> avioes = new ArrayList<>();
    private final Colisao colisao = new Colisao();
    private final DefaultListModel modelRelatorio = new DefaultListModel();

    public Radar() {
        initComponents();
        recarregarAvioesNoRadar();

        jListRelatorio.setModel(modelRelatorio);
    }

    private void inserirOuEditarAviao() {
        String txtX = jTxtX.getText();
        String txtY = jTxtY.getText();
        String txtRaio = jTxtRaio.getText();
        String txtAngulo = jTxtAngulo.getText();
        String txtVelocidade = jTxtVelocidade.getText();
        String txtDirecao = jTxtDirecao.getText();

        this.idAviaoSelecionado = this.idAviaoSelecionado != null ? this.idAviaoSelecionado : UtilAviao.getNextId(this.avioes);

        Aviao aviao = new Aviao();
        aviao.setId(this.idAviaoSelecionado);
        aviao.setModelo("Avião " + aviao.getId());
        aviao.setX(Double.parseDouble(txtX));
        aviao.setY(Double.parseDouble(txtY));
        aviao.setRaio(Double.parseDouble(txtRaio));
        aviao.setAngulo(Double.parseDouble(txtAngulo));
        aviao.setVelocidade(Double.parseDouble(txtVelocidade));
        aviao.setDirecao(Double.parseDouble(txtDirecao));

        int index = this.avioes.indexOf(aviao);

        if (index > -1) {
            this.avioes.set(index, aviao);
        } else {
            this.avioes.add(aviao);
        }
    }

    private void inserirOuEditarDadosColisao() {

        double disMinimaAeroporto = 0d;
        double disMinimaAvioes = 0d;
        Integer tempoMinimo = 0;

        if (!jTxDistanciaMinAeroPorto.getText().isEmpty()) {
            disMinimaAeroporto = Double.parseDouble(jTxDistanciaMinAeroPorto.getText());
        }

        if (!jTxDistanciaMinAvioes.getText().isEmpty()) {
            disMinimaAvioes = Double.parseDouble(jTxDistanciaMinAvioes.getText());
        }

        if (!jTxTempoMinimo.getText().isEmpty()) {
            tempoMinimo = Integer.parseInt(jTxTempoMinimo.getText());
        }

        this.colisao.setDisMinimaAeroporto(disMinimaAeroporto);
        this.colisao.setDisMinimaAvioes(disMinimaAvioes);
        this.colisao.setTempoMinimo(tempoMinimo);
    }

    private void excluirAviao(Integer idAviao) {
        this.avioes.remove(new Aviao(idAviao));
    }

    private void carregarListaDeAvioes() {
        DefaultTableModel model = (DefaultTableModel) jTbAvioes.getModel();

        for (Aviao aviao : this.avioes) {
            model.addRow(new Object[]{aviao.isVisualiza(), String.valueOf(aviao.getId()), aviao.getModelo(), String.valueOf(aviao.getX()), String.valueOf(aviao.getY()), String.valueOf(aviao.getRaio()), String.valueOf(aviao.getAngulo()), String.valueOf(aviao.getVelocidade()), String.valueOf(aviao.getDirecao())});
        }
    }

    private void limparCamposDadosAviao() {
        this.idAviaoSelecionado = null;

        jTxtX.setText(null);
        jTxtY.setText(null);
        jTxtRaio.setText(null);
        jTxtAngulo.setText(null);
        jTxtVelocidade.setText(null);
        jTxtDirecao.setText(null);
    }

    private void limparCamposDadosTranslacao() {
        jTxtXTranslandar.setText(null);
        jTxtYTranslandar.setText(null);
    }

    private void limparCamposDadosEscalonamento() {
        jTxtXEscalonar.setText(null);
        jTxtYEscalonar.setText(null);
    }

    private void limparCamposDadosRotacao() {
        jTxtXRotacionar.setText(null);
        jTxtYRotacionar.setText(null);
    }

    private void carregarCamposDadosAviao(Aviao aviao) {
        this.idAviaoSelecionado = aviao.getId();

        jTxtX.setText(String.valueOf(aviao.getX()));
        jTxtY.setText(String.valueOf(aviao.getY()));
        jTxtRaio.setText(String.valueOf(aviao.getRaio()));
        jTxtAngulo.setText(String.valueOf(aviao.getAngulo()));
        jTxtVelocidade.setText(String.valueOf(aviao.getVelocidade()));
        jTxtDirecao.setText(String.valueOf(aviao.getDirecao()));
    }

    private void limparTabelaAvioes() {
        DefaultTableModel model = (DefaultTableModel) jTbAvioes.getModel();

        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }

    }

    private void recarregarAvioes() {
        limparTabelaAvioes();
        carregarListaDeAvioes();
    }

    private void limparCampos() {
        limparCamposDadosAviao();
        limparCamposDadosTranslacao();
        limparCamposDadosEscalonamento();
        limparCamposDadosRotacao();
    }

    private Aviao getAviaoSelecionado() {
        int index = this.avioes.indexOf(new Aviao(this.idAviaoSelecionado));

        if (index > -1) {
            return this.avioes.get(index);
        }

        return null;
    }

    private Coordenada getCoordenadaTranslacao() {
        String txtX = jTxtXTranslandar.getText();
        String txtY = jTxtYTranslandar.getText();
        return new Coordenada(Double.valueOf(txtX), Double.valueOf(txtY));
    }

    private Coordenada getCoordenadaEscalonamento() {
        String txtX = jTxtXEscalonar.getText();
        String txtY = jTxtYEscalonar.getText();
        return new Coordenada(Double.parseDouble(txtX), Double.parseDouble(txtY));
    }

    private Coordenada getCoordenadaRotacao() {
        String txtX = jTxtXRotacionar.getText();
        String txtY = jTxtYRotacionar.getText();
        String angulo = jTxtAnguloRotacionar.getText();
        return new Coordenada(Double.parseDouble(txtX), Double.parseDouble(txtY), Double.parseDouble(angulo));
    }

    private void recarregarAvioesNoRadar() {
        jPnRadarAvioes.removeAll();

        for (Aviao aviao : this.avioes) {
            if (aviao.isVisualiza()) {
                jPnRadarAvioes.add(UtilComponentes.getIconeAviao(aviao));
            }
        }

        jPnRadarAvioes.add(UtilComponentes.getRadar());

        jPnRadarAvioes.validate();
        jPnRadarAvioes.repaint();
    }

    private void gerarRelatorio() {
        Aviao aviao = getAviaoSelecionado();

        if (aviao == null) {
            return;
        }

        UtilFuncoesColisao.calcularRotasDeColisao(aviao, this.avioes, this.colisao, modelRelatorio);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPnRadar = new javax.swing.JPanel();
        jPnRadarAvioes = new javax.swing.JPanel();
        jTabOperacoesAvioes = new javax.swing.JTabbedPane();
        jPnEntradaDados = new javax.swing.JPanel();
        jPnDadosAviao = new javax.swing.JPanel();
        jLblX = new javax.swing.JLabel();
        jTxtX = new javax.swing.JTextField();
        jLblY = new javax.swing.JLabel();
        jTxtY = new javax.swing.JTextField();
        jLblRaio = new javax.swing.JLabel();
        jTxtRaio = new javax.swing.JTextField();
        jLblAngulo = new javax.swing.JLabel();
        jTxtAngulo = new javax.swing.JTextField();
        jLblVelocidade = new javax.swing.JLabel();
        jTxtVelocidade = new javax.swing.JTextField();
        jLblAngulo1 = new javax.swing.JLabel();
        jTxtDirecao = new javax.swing.JTextField();
        jBtnSalvarAviao = new javax.swing.JButton();
        JBtnExcluir = new javax.swing.JButton();
        jPnFuncoesTransformacao = new javax.swing.JPanel();
        jPnTranslandar = new javax.swing.JPanel();
        jLblTranslandarX = new javax.swing.JLabel();
        jTxtXTranslandar = new javax.swing.JTextField();
        jLblTranslandarY = new javax.swing.JLabel();
        jTxtYTranslandar = new javax.swing.JTextField();
        jBtnTranslandar = new javax.swing.JButton();
        jPnEscalonar = new javax.swing.JPanel();
        jTxtXEscalonar = new javax.swing.JTextField();
        jLblEscalonarX = new javax.swing.JLabel();
        jLblEscalonarY = new javax.swing.JLabel();
        jTxtYEscalonar = new javax.swing.JTextField();
        jBtnEscalonar = new javax.swing.JButton();
        jPnRotacionar = new javax.swing.JPanel();
        jLblAnguloRotacionar = new javax.swing.JLabel();
        jTxtAnguloRotacionar = new javax.swing.JTextField();
        jLblCentroRotacao = new javax.swing.JLabel();
        jLblRotacionarX = new javax.swing.JLabel();
        jTxtXRotacionar = new javax.swing.JTextField();
        jTxtYRotacionar = new javax.swing.JTextField();
        jLblRotacionarY = new javax.swing.JLabel();
        jBtnRotacionar = new javax.swing.JButton();
        jPnFuncoesRatreamento = new javax.swing.JPanel();
        jPnDistanciaMinAeroPorto = new javax.swing.JPanel();
        jTxDistanciaMinAeroPorto = new javax.swing.JTextField();
        jBtnSalvarDistanciaMinAeroPorto = new javax.swing.JButton();
        jPnDistanciaMinAvioes = new javax.swing.JPanel();
        jTxDistanciaMinAvioes = new javax.swing.JTextField();
        jBtnSalvarDistanciaMinAvioes = new javax.swing.JButton();
        jPnTempoMinimo = new javax.swing.JPanel();
        jTxTempoMinimo = new javax.swing.JTextField();
        jBtnSalvarTempoMinimo = new javax.swing.JButton();
        jPnAvioes = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTbAvioes = new javax.swing.JTable();
        jPnRelatorio = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListRelatorio = new javax.swing.JList<String>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Radar");
        setResizable(false);

        jPnRadar.setLayout(null);

        jPnRadarAvioes.setBackground(new java.awt.Color(0, 0, 0));
        jPnRadarAvioes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPnRadarAvioes.setMinimumSize(new java.awt.Dimension(420, 420));
        jPnRadarAvioes.setLayout(null);
        jPnRadar.add(jPnRadarAvioes);
        jPnRadarAvioes.setBounds(10, 10, 420, 340);

        jPnDadosAviao.setBorder(javax.swing.BorderFactory.createTitledBorder("Adicionar avião"));

        jLblX.setText("X:");

        jTxtX.setPreferredSize(new java.awt.Dimension(120, 25));
        jTxtX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtXActionPerformed(evt);
            }
        });
        jTxtX.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTxtXKeyTyped(evt);
            }
        });

        jLblY.setText("Y:");

        jTxtY.setPreferredSize(new java.awt.Dimension(120, 25));

        jLblRaio.setText("Raio:");

        jTxtRaio.setPreferredSize(new java.awt.Dimension(120, 25));

        jLblAngulo.setText("Ângulo:");

        jTxtAngulo.setPreferredSize(new java.awt.Dimension(120, 25));

        jLblVelocidade.setText("Velocidade:");

        jTxtVelocidade.setPreferredSize(new java.awt.Dimension(120, 25));

        jLblAngulo1.setText("Direção");

        jTxtDirecao.setPreferredSize(new java.awt.Dimension(120, 25));

        jBtnSalvarAviao.setText("Salvar");
        jBtnSalvarAviao.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jBtnSalvarAviao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnSalvarAviaoMouseClicked(evt);
            }
        });
        jBtnSalvarAviao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSalvarAviaoActionPerformed(evt);
            }
        });

        JBtnExcluir.setText("Excluir");
        JBtnExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        JBtnExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JBtnExcluirMouseClicked(evt);
            }
        });
        JBtnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBtnExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPnDadosAviaoLayout = new javax.swing.GroupLayout(jPnDadosAviao);
        jPnDadosAviao.setLayout(jPnDadosAviaoLayout);
        jPnDadosAviaoLayout.setHorizontalGroup(
            jPnDadosAviaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnDadosAviaoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPnDadosAviaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPnDadosAviaoLayout.createSequentialGroup()
                        .addGroup(jPnDadosAviaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPnDadosAviaoLayout.createSequentialGroup()
                                .addComponent(jLblRaio)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPnDadosAviaoLayout.createSequentialGroup()
                                .addComponent(jLblX)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTxtX, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPnDadosAviaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnDadosAviaoLayout.createSequentialGroup()
                                .addComponent(jLblY)
                                .addGap(27, 27, 27))
                            .addComponent(jLblAngulo1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(28, 28, 28)
                        .addGroup(jPnDadosAviaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTxtY, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTxtAngulo, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTxtDirecao, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPnDadosAviaoLayout.createSequentialGroup()
                        .addGroup(jPnDadosAviaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTxtRaio, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPnDadosAviaoLayout.createSequentialGroup()
                                .addComponent(jLblVelocidade)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTxtVelocidade, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(jLblAngulo)
                        .addGap(142, 142, 142))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnDadosAviaoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(JBtnExcluir)
                        .addGap(8, 8, 8)
                        .addComponent(jBtnSalvarAviao)
                        .addContainerGap())))
        );
        jPnDadosAviaoLayout.setVerticalGroup(
            jPnDadosAviaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnDadosAviaoLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPnDadosAviaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblX)
                    .addComponent(jTxtX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLblY)
                    .addComponent(jTxtY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPnDadosAviaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblRaio)
                    .addComponent(jTxtRaio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLblAngulo)
                    .addComponent(jTxtAngulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPnDadosAviaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblVelocidade)
                    .addComponent(jTxtVelocidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLblAngulo1)
                    .addComponent(jTxtDirecao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 37, Short.MAX_VALUE)
                .addGroup(jPnDadosAviaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JBtnExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnSalvarAviao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPnEntradaDadosLayout = new javax.swing.GroupLayout(jPnEntradaDados);
        jPnEntradaDados.setLayout(jPnEntradaDadosLayout);
        jPnEntradaDadosLayout.setHorizontalGroup(
            jPnEntradaDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnEntradaDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPnDadosAviao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(836, Short.MAX_VALUE))
        );
        jPnEntradaDadosLayout.setVerticalGroup(
            jPnEntradaDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnEntradaDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPnDadosAviao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabOperacoesAvioes.addTab("Entrada de dados", jPnEntradaDados);

        jPnTranslandar.setBorder(javax.swing.BorderFactory.createTitledBorder("Translandar"));
        jPnTranslandar.setPreferredSize(new java.awt.Dimension(230, 300));

        jLblTranslandarX.setText("X:");

        jTxtXTranslandar.setPreferredSize(new java.awt.Dimension(20, 23));

        jLblTranslandarY.setText("Y:");

        jTxtYTranslandar.setPreferredSize(new java.awt.Dimension(20, 23));

        jBtnTranslandar.setText("Translandar");
        jBtnTranslandar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jBtnTranslandar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnTranslandarMouseClicked(evt);
            }
        });
        jBtnTranslandar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnTranslandarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPnTranslandarLayout = new javax.swing.GroupLayout(jPnTranslandar);
        jPnTranslandar.setLayout(jPnTranslandarLayout);
        jPnTranslandarLayout.setHorizontalGroup(
            jPnTranslandarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnTranslandarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPnTranslandarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPnTranslandarLayout.createSequentialGroup()
                        .addComponent(jLblTranslandarY)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTxtYTranslandar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPnTranslandarLayout.createSequentialGroup()
                        .addComponent(jLblTranslandarX)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTxtXTranslandar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jBtnTranslandar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPnTranslandarLayout.setVerticalGroup(
            jPnTranslandarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnTranslandarLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPnTranslandarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtXTranslandar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLblTranslandarX, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPnTranslandarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblTranslandarY, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtYTranslandar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnTranslandar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPnEscalonar.setBorder(javax.swing.BorderFactory.createTitledBorder("Escalonar"));
        jPnEscalonar.setPreferredSize(new java.awt.Dimension(230, 300));

        jTxtXEscalonar.setPreferredSize(new java.awt.Dimension(20, 23));

        jLblEscalonarX.setText("X:");

        jLblEscalonarY.setText("Y:");

        jTxtYEscalonar.setPreferredSize(new java.awt.Dimension(20, 23));

        jBtnEscalonar.setText("Escalonar");
        jBtnEscalonar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jBtnEscalonar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnEscalonarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPnEscalonarLayout = new javax.swing.GroupLayout(jPnEscalonar);
        jPnEscalonar.setLayout(jPnEscalonarLayout);
        jPnEscalonarLayout.setHorizontalGroup(
            jPnEscalonarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnEscalonarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPnEscalonarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPnEscalonarLayout.createSequentialGroup()
                        .addGroup(jPnEscalonarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLblEscalonarX)
                            .addGroup(jPnEscalonarLayout.createSequentialGroup()
                                .addComponent(jLblEscalonarY)
                                .addGap(1, 1, 1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPnEscalonarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTxtXEscalonar, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                            .addComponent(jTxtYEscalonar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jBtnEscalonar, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPnEscalonarLayout.setVerticalGroup(
            jPnEscalonarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnEscalonarLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPnEscalonarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtXEscalonar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLblEscalonarX, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPnEscalonarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtYEscalonar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLblEscalonarY, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnEscalonar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPnRotacionar.setBorder(javax.swing.BorderFactory.createTitledBorder("Rotacionar"));
        jPnRotacionar.setPreferredSize(new java.awt.Dimension(230, 300));

        jLblAnguloRotacionar.setText("Ângulo:");

        jTxtAnguloRotacionar.setPreferredSize(new java.awt.Dimension(20, 23));

        jLblCentroRotacao.setText("Centro de Rotação");

        jLblRotacionarX.setText("X:");

        jTxtXRotacionar.setPreferredSize(new java.awt.Dimension(20, 23));

        jTxtYRotacionar.setPreferredSize(new java.awt.Dimension(20, 23));

        jLblRotacionarY.setText("Y:");

        jBtnRotacionar.setText("Rotacionar");
        jBtnRotacionar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jBtnRotacionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnRotacionarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPnRotacionarLayout = new javax.swing.GroupLayout(jPnRotacionar);
        jPnRotacionar.setLayout(jPnRotacionarLayout);
        jPnRotacionarLayout.setHorizontalGroup(
            jPnRotacionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnRotacionarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPnRotacionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPnRotacionarLayout.createSequentialGroup()
                        .addComponent(jLblCentroRotacao)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnRotacionarLayout.createSequentialGroup()
                        .addGap(0, 4, Short.MAX_VALUE)
                        .addGroup(jPnRotacionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnRotacionarLayout.createSequentialGroup()
                                .addComponent(jLblRotacionarY)
                                .addGap(18, 18, 18)
                                .addComponent(jTxtYRotacionar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnRotacionarLayout.createSequentialGroup()
                                .addComponent(jLblRotacionarX)
                                .addGap(18, 18, 18)
                                .addComponent(jTxtXRotacionar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnRotacionarLayout.createSequentialGroup()
                                .addComponent(jLblAnguloRotacionar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTxtAnguloRotacionar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(25, 25, 25))
                    .addGroup(jPnRotacionarLayout.createSequentialGroup()
                        .addComponent(jBtnRotacionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPnRotacionarLayout.setVerticalGroup(
            jPnRotacionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnRotacionarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPnRotacionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtAnguloRotacionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLblAnguloRotacionar))
                .addGap(18, 18, 18)
                .addComponent(jLblCentroRotacao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPnRotacionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtXRotacionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLblRotacionarX, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPnRotacionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtYRotacionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLblRotacionarY, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jBtnRotacionar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPnFuncoesTransformacaoLayout = new javax.swing.GroupLayout(jPnFuncoesTransformacao);
        jPnFuncoesTransformacao.setLayout(jPnFuncoesTransformacaoLayout);
        jPnFuncoesTransformacaoLayout.setHorizontalGroup(
            jPnFuncoesTransformacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnFuncoesTransformacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPnTranslandar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPnEscalonar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPnRotacionar, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(445, Short.MAX_VALUE))
        );
        jPnFuncoesTransformacaoLayout.setVerticalGroup(
            jPnFuncoesTransformacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnFuncoesTransformacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPnFuncoesTransformacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPnEscalonar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addComponent(jPnRotacionar, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addComponent(jPnTranslandar, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPnTranslandar.getAccessibleContext().setAccessibleName("");
        jPnEscalonar.getAccessibleContext().setAccessibleName("");
        jPnRotacionar.getAccessibleContext().setAccessibleName("");

        jTabOperacoesAvioes.addTab("Funções de Transformação", jPnFuncoesTransformacao);

        jPnDistanciaMinAeroPorto.setBorder(javax.swing.BorderFactory.createTitledBorder("Distancia Mínima do Aeroporto"));
        jPnDistanciaMinAeroPorto.setPreferredSize(new java.awt.Dimension(230, 300));

        jTxDistanciaMinAeroPorto.setPreferredSize(new java.awt.Dimension(20, 23));

        jBtnSalvarDistanciaMinAeroPorto.setText("Salvar");
        jBtnSalvarDistanciaMinAeroPorto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jBtnSalvarDistanciaMinAeroPorto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnSalvarDistanciaMinAeroPortoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPnDistanciaMinAeroPortoLayout = new javax.swing.GroupLayout(jPnDistanciaMinAeroPorto);
        jPnDistanciaMinAeroPorto.setLayout(jPnDistanciaMinAeroPortoLayout);
        jPnDistanciaMinAeroPortoLayout.setHorizontalGroup(
            jPnDistanciaMinAeroPortoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnDistanciaMinAeroPortoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPnDistanciaMinAeroPortoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPnDistanciaMinAeroPortoLayout.createSequentialGroup()
                        .addComponent(jBtnSalvarDistanciaMinAeroPorto, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 14, Short.MAX_VALUE))
                    .addComponent(jTxDistanciaMinAeroPorto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPnDistanciaMinAeroPortoLayout.setVerticalGroup(
            jPnDistanciaMinAeroPortoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnDistanciaMinAeroPortoLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jTxDistanciaMinAeroPorto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnSalvarDistanciaMinAeroPorto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPnDistanciaMinAvioes.setBorder(javax.swing.BorderFactory.createTitledBorder("Distancia Mínima dos Aviões"));
        jPnDistanciaMinAvioes.setPreferredSize(new java.awt.Dimension(230, 300));

        jTxDistanciaMinAvioes.setPreferredSize(new java.awt.Dimension(20, 23));

        jBtnSalvarDistanciaMinAvioes.setText("Salvar");
        jBtnSalvarDistanciaMinAvioes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jBtnSalvarDistanciaMinAvioes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnSalvarDistanciaMinAvioesMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPnDistanciaMinAvioesLayout = new javax.swing.GroupLayout(jPnDistanciaMinAvioes);
        jPnDistanciaMinAvioes.setLayout(jPnDistanciaMinAvioesLayout);
        jPnDistanciaMinAvioesLayout.setHorizontalGroup(
            jPnDistanciaMinAvioesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnDistanciaMinAvioesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPnDistanciaMinAvioesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTxDistanciaMinAvioes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPnDistanciaMinAvioesLayout.createSequentialGroup()
                        .addComponent(jBtnSalvarDistanciaMinAvioes, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 11, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPnDistanciaMinAvioesLayout.setVerticalGroup(
            jPnDistanciaMinAvioesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnDistanciaMinAvioesLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jTxDistanciaMinAvioes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                .addComponent(jBtnSalvarDistanciaMinAvioes, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPnTempoMinimo.setBorder(javax.swing.BorderFactory.createTitledBorder("Tempo Mínimo"));
        jPnTempoMinimo.setPreferredSize(new java.awt.Dimension(230, 300));

        jTxTempoMinimo.setPreferredSize(new java.awt.Dimension(20, 23));

        jBtnSalvarTempoMinimo.setText("Salvar");
        jBtnSalvarTempoMinimo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jBtnSalvarTempoMinimo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnSalvarTempoMinimoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPnTempoMinimoLayout = new javax.swing.GroupLayout(jPnTempoMinimo);
        jPnTempoMinimo.setLayout(jPnTempoMinimoLayout);
        jPnTempoMinimoLayout.setHorizontalGroup(
            jPnTempoMinimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnTempoMinimoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPnTempoMinimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTxTempoMinimo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnSalvarTempoMinimo, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPnTempoMinimoLayout.setVerticalGroup(
            jPnTempoMinimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnTempoMinimoLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jTxTempoMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnSalvarTempoMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPnFuncoesRatreamentoLayout = new javax.swing.GroupLayout(jPnFuncoesRatreamento);
        jPnFuncoesRatreamento.setLayout(jPnFuncoesRatreamentoLayout);
        jPnFuncoesRatreamentoLayout.setHorizontalGroup(
            jPnFuncoesRatreamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnFuncoesRatreamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPnDistanciaMinAeroPorto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPnDistanciaMinAvioes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPnTempoMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(445, Short.MAX_VALUE))
        );
        jPnFuncoesRatreamentoLayout.setVerticalGroup(
            jPnFuncoesRatreamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnFuncoesRatreamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPnFuncoesRatreamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPnDistanciaMinAvioes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addComponent(jPnTempoMinimo, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addComponent(jPnDistanciaMinAeroPorto, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabOperacoesAvioes.addTab("Funções de Rastreamento", jPnFuncoesRatreamento);

        jPnRadar.add(jTabOperacoesAvioes);
        jTabOperacoesAvioes.setBounds(0, 370, 1180, 260);

        jPnAvioes.setBorder(javax.swing.BorderFactory.createTitledBorder("Aviões"));

        jTbAvioes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Visualizar", "Código", "Modelo", "X", "Y", "Raio", "Ângulo", "Velocidade", "Direção"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTbAvioes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTbAvioesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTbAvioes);
        if (jTbAvioes.getColumnModel().getColumnCount() > 0) {
            jTbAvioes.getColumnModel().getColumn(0).setResizable(false);
            jTbAvioes.getColumnModel().getColumn(1).setResizable(false);
            jTbAvioes.getColumnModel().getColumn(2).setResizable(false);
            jTbAvioes.getColumnModel().getColumn(3).setResizable(false);
            jTbAvioes.getColumnModel().getColumn(4).setResizable(false);
            jTbAvioes.getColumnModel().getColumn(5).setResizable(false);
            jTbAvioes.getColumnModel().getColumn(6).setResizable(false);
            jTbAvioes.getColumnModel().getColumn(7).setResizable(false);
            jTbAvioes.getColumnModel().getColumn(8).setResizable(false);
        }

        javax.swing.GroupLayout jPnAvioesLayout = new javax.swing.GroupLayout(jPnAvioes);
        jPnAvioes.setLayout(jPnAvioesLayout);
        jPnAvioesLayout.setHorizontalGroup(
            jPnAvioesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnAvioesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPnAvioesLayout.setVerticalGroup(
            jPnAvioesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnAvioesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jPnRadar.add(jPnAvioes);
        jPnAvioes.setBounds(490, 0, 680, 180);

        jPnRelatorio.setBorder(javax.swing.BorderFactory.createTitledBorder("Relatório"));

        jListRelatorio.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Avião adicionado" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jListRelatorio);

        javax.swing.GroupLayout jPnRelatorioLayout = new javax.swing.GroupLayout(jPnRelatorio);
        jPnRelatorio.setLayout(jPnRelatorioLayout);
        jPnRelatorioLayout.setHorizontalGroup(
            jPnRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnRelatorioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPnRelatorioLayout.setVerticalGroup(
            jPnRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnRelatorioLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPnRadar.add(jPnRelatorio);
        jPnRelatorio.setBounds(490, 190, 680, 160);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPnRadar, javax.swing.GroupLayout.DEFAULT_SIZE, 1180, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPnRadar, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnSalvarAviaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSalvarAviaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnSalvarAviaoActionPerformed

    private void jBtnSalvarAviaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnSalvarAviaoMouseClicked

        if ((!jTxtX.getText().isEmpty()) && (!jTxtY.getText().isEmpty())
                && (!jTxtRaio.getText().isEmpty()) && (!jTxtAngulo.getText().isEmpty())
                && (!jTxtVelocidade.getText().isEmpty()) && (!jTxtDirecao.getText().isEmpty())) {

            inserirOuEditarAviao();
            inserirOuEditarDadosColisao();
            gerarRelatorio();
            limparCampos();
            recarregarAvioes();
            recarregarAvioesNoRadar();

        } else {
            JOptionPane.showMessageDialog(null, "Informe todos os dados");
        }

    }//GEN-LAST:event_jBtnSalvarAviaoMouseClicked

    private void jTbAvioesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTbAvioesMouseClicked
        if (evt.getClickCount() == 2) {
            int rowSelected = jTbAvioes.getSelectedRow();
            if (rowSelected > -1) {
                carregarCamposDadosAviao(this.avioes.get(rowSelected));
            }
        }
    }//GEN-LAST:event_jTbAvioesMouseClicked

    private void JBtnExcluirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JBtnExcluirMouseClicked

        if (this.idAviaoSelecionado == null) {
            JOptionPane.showMessageDialog(null, "Selecione um avião para excluir");
            return;
        }

        excluirAviao(this.idAviaoSelecionado);

        limparCampos();
        recarregarAvioes();
        recarregarAvioesNoRadar();
    }//GEN-LAST:event_JBtnExcluirMouseClicked

    private void jBtnTranslandarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnTranslandarMouseClicked

        if (this.idAviaoSelecionado != null) {

            if ((!jTxtXTranslandar.getText().isEmpty()) && (!jTxtYTranslandar.getText().isEmpty())) {
                UtilFuncoesTransformacao.translandar(getAviaoSelecionado(), getCoordenadaTranslacao());
                limparCamposDadosTranslacao();
                recarregarAvioes();
                recarregarAvioesNoRadar();
            } else {
                JOptionPane.showMessageDialog(null, "Informe todos os dados");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Selecione um avião para translandar");
        }

    }//GEN-LAST:event_jBtnTranslandarMouseClicked

    private void jBtnEscalonarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnEscalonarMouseClicked

        if (this.idAviaoSelecionado != null) {

            if ((!jTxtXEscalonar.getText().isEmpty()) && (!jTxtYEscalonar.getText().isEmpty())) {
                UtilFuncoesTransformacao.escalonar(getAviaoSelecionado(), getCoordenadaEscalonamento());
                limparCamposDadosEscalonamento();
                recarregarAvioes();
                recarregarAvioesNoRadar();
            } else {
                JOptionPane.showMessageDialog(null, "Informe todos os dados");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Selecione um avião para escalonar");
        }


    }//GEN-LAST:event_jBtnEscalonarMouseClicked

    private void jBtnRotacionarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnRotacionarMouseClicked

        if (this.idAviaoSelecionado != null) {

            if ((!jTxtXRotacionar.getText().isEmpty()) && (!jTxtYRotacionar.getText().isEmpty())) {
                UtilFuncoesTransformacao.rotacionar(getAviaoSelecionado(), getCoordenadaRotacao());
                limparCamposDadosRotacao();
                recarregarAvioes();
                recarregarAvioesNoRadar();
            } else {
                JOptionPane.showMessageDialog(null, "Informe todos os dados");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Selecione um avião para rotacionar");
        }

    }//GEN-LAST:event_jBtnRotacionarMouseClicked

    private void jTxtXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtXActionPerformed

    private void JBtnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBtnExcluirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JBtnExcluirActionPerformed

    private void jBtnSalvarDistanciaMinAeroPortoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnSalvarDistanciaMinAeroPortoMouseClicked
        inserirOuEditarDadosColisao();
    }//GEN-LAST:event_jBtnSalvarDistanciaMinAeroPortoMouseClicked

    private void jBtnSalvarDistanciaMinAvioesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnSalvarDistanciaMinAvioesMouseClicked
        inserirOuEditarDadosColisao();
    }//GEN-LAST:event_jBtnSalvarDistanciaMinAvioesMouseClicked

    private void jBtnSalvarTempoMinimoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnSalvarTempoMinimoMouseClicked
        inserirOuEditarDadosColisao();
    }//GEN-LAST:event_jBtnSalvarTempoMinimoMouseClicked

    private void jBtnTranslandarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnTranslandarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnTranslandarActionPerformed

    private void jTxtXKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtXKeyTyped
        String caracteres = "0123456789";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_jTxtXKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Radar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Radar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Radar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Radar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Radar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBtnExcluir;
    private javax.swing.JButton jBtnEscalonar;
    private javax.swing.JButton jBtnRotacionar;
    private javax.swing.JButton jBtnSalvarAviao;
    private javax.swing.JButton jBtnSalvarDistanciaMinAeroPorto;
    private javax.swing.JButton jBtnSalvarDistanciaMinAvioes;
    private javax.swing.JButton jBtnSalvarTempoMinimo;
    private javax.swing.JButton jBtnTranslandar;
    private javax.swing.JLabel jLblAngulo;
    private javax.swing.JLabel jLblAngulo1;
    private javax.swing.JLabel jLblAnguloRotacionar;
    private javax.swing.JLabel jLblCentroRotacao;
    private javax.swing.JLabel jLblEscalonarX;
    private javax.swing.JLabel jLblEscalonarY;
    private javax.swing.JLabel jLblRaio;
    private javax.swing.JLabel jLblRotacionarX;
    private javax.swing.JLabel jLblRotacionarY;
    private javax.swing.JLabel jLblTranslandarX;
    private javax.swing.JLabel jLblTranslandarY;
    private javax.swing.JLabel jLblVelocidade;
    private javax.swing.JLabel jLblX;
    private javax.swing.JLabel jLblY;
    private javax.swing.JList<String> jListRelatorio;
    private javax.swing.JPanel jPnAvioes;
    private javax.swing.JPanel jPnDadosAviao;
    private javax.swing.JPanel jPnDistanciaMinAeroPorto;
    private javax.swing.JPanel jPnDistanciaMinAvioes;
    private javax.swing.JPanel jPnEntradaDados;
    private javax.swing.JPanel jPnEscalonar;
    private javax.swing.JPanel jPnFuncoesRatreamento;
    private javax.swing.JPanel jPnFuncoesTransformacao;
    private javax.swing.JPanel jPnRadar;
    private javax.swing.JPanel jPnRadarAvioes;
    private javax.swing.JPanel jPnRelatorio;
    private javax.swing.JPanel jPnRotacionar;
    private javax.swing.JPanel jPnTempoMinimo;
    private javax.swing.JPanel jPnTranslandar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabOperacoesAvioes;
    private javax.swing.JTable jTbAvioes;
    private javax.swing.JTextField jTxDistanciaMinAeroPorto;
    private javax.swing.JTextField jTxDistanciaMinAvioes;
    private javax.swing.JTextField jTxTempoMinimo;
    private javax.swing.JTextField jTxtAngulo;
    private javax.swing.JTextField jTxtAnguloRotacionar;
    private javax.swing.JTextField jTxtDirecao;
    private javax.swing.JTextField jTxtRaio;
    private javax.swing.JTextField jTxtVelocidade;
    private javax.swing.JTextField jTxtX;
    private javax.swing.JTextField jTxtXEscalonar;
    private javax.swing.JTextField jTxtXRotacionar;
    private javax.swing.JTextField jTxtXTranslandar;
    private javax.swing.JTextField jTxtY;
    private javax.swing.JTextField jTxtYEscalonar;
    private javax.swing.JTextField jTxtYRotacionar;
    private javax.swing.JTextField jTxtYTranslandar;
    // End of variables declaration//GEN-END:variables
}
