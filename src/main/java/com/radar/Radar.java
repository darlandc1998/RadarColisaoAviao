package com.radar;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.Aviao;
import modelos.Coordenada;
import utils.UtilAviao;
import utils.UtilComponentes;
import utils.UtilFuncoesTransformacao;

public class Radar extends javax.swing.JFrame {

    private Integer idAviaoSelecionado = null;
    private List<Aviao> avioes = new ArrayList<>();

    public Radar() {
        initComponents();
    }

    private void inserirOuEditarAviao() {
        String txtX = jTxtX.getText();
        String txtY = jTxtY.getText();
        String txtRaio = jTxtRaio.getText();
        String txtAngulo = jTxtAngulo.getText();
        String txtVelocidade = jTxtVelocidade.getText();
        String txtDirecao = jTxtDirecao.getText();

        Aviao aviao = new Aviao();
        aviao.setId(this.idAviaoSelecionado != null ? this.idAviaoSelecionado : UtilAviao.getNextId(this.avioes));
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
        jPnRadar.removeAll();

        for (Aviao aviao : this.avioes) {
            if (aviao.isVisualiza()) {
                jPnRadar.add(UtilComponentes.getIconeAviao(aviao));
            }
        }

        jPnRadar.validate();
        jPnRadar.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabAvioes = new javax.swing.JTabbedPane();
        jPnRadar = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListRelatorio = new javax.swing.JList<>();
        jLblRelatorio = new javax.swing.JLabel();
        jPnAvioes = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTbAvioes = new javax.swing.JTable();
        jPnOperacoesAvioes = new javax.swing.JPanel();
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
        jLblDadosAviao = new javax.swing.JLabel();
        JBtnExcluir = new javax.swing.JButton();
        jPnFuncoesTransformacao = new javax.swing.JPanel();
        jPnTranslandar = new javax.swing.JPanel();
        jLblTranslandarX = new javax.swing.JLabel();
        jTxtXTranslandar = new javax.swing.JTextField();
        jLblTranslandarY = new javax.swing.JLabel();
        jTxtYTranslandar = new javax.swing.JTextField();
        jBtnTranslandar = new javax.swing.JButton();
        jLblTranslandar = new javax.swing.JLabel();
        jPnEscalonar = new javax.swing.JPanel();
        jTxtXEscalonar = new javax.swing.JTextField();
        jLblEscalonarX = new javax.swing.JLabel();
        jLblEscalonarY = new javax.swing.JLabel();
        jTxtYEscalonar = new javax.swing.JTextField();
        jBtnEscalonar = new javax.swing.JButton();
        jLblEscalonar = new javax.swing.JLabel();
        jPnRotacionar = new javax.swing.JPanel();
        jLblAnguloRotacionar = new javax.swing.JLabel();
        jTxtAnguloRotacionar = new javax.swing.JTextField();
        jLblCentroRotacao = new javax.swing.JLabel();
        jLblRotacionarX = new javax.swing.JLabel();
        jTxtXRotacionar = new javax.swing.JTextField();
        jTxtYRotacionar = new javax.swing.JTextField();
        jLblRotacionarY = new javax.swing.JLabel();
        jBtnRotacionar = new javax.swing.JButton();
        jLblRotacionar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Radar");
        setPreferredSize(new java.awt.Dimension(1024, 500));
        setResizable(false);

        jPnRadar.setLayout(null);

        jListRelatorio.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Avião adicionado" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jListRelatorio);

        jPnRadar.add(jScrollPane2);
        jScrollPane2.setBounds(0, 369, 1092, 139);

        jLblRelatorio.setText("Relatório");
        jPnRadar.add(jLblRelatorio);
        jLblRelatorio.setBounds(512, 348, 65, 15);

        jTabAvioes.addTab("Radar", jPnRadar);

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

        jPnDadosAviao.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLblX.setText("X:");

        jTxtX.setPreferredSize(new java.awt.Dimension(120, 25));

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

        jLblDadosAviao.setText("Avião");

        JBtnExcluir.setText("Excluir");
        JBtnExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JBtnExcluirMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPnDadosAviaoLayout = new javax.swing.GroupLayout(jPnDadosAviao);
        jPnDadosAviao.setLayout(jPnDadosAviaoLayout);
        jPnDadosAviaoLayout.setHorizontalGroup(
            jPnDadosAviaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnDadosAviaoLayout.createSequentialGroup()
                .addGap(210, 210, 210)
                .addComponent(jLblDadosAviao)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnDadosAviaoLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPnDadosAviaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnDadosAviaoLayout.createSequentialGroup()
                        .addGroup(jPnDadosAviaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPnDadosAviaoLayout.createSequentialGroup()
                                .addComponent(jLblVelocidade)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTxtVelocidade, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPnDadosAviaoLayout.createSequentialGroup()
                                .addComponent(jLblRaio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTxtRaio, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPnDadosAviaoLayout.createSequentialGroup()
                                .addComponent(jLblX)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTxtX, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPnDadosAviaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPnDadosAviaoLayout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(jLblY)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTxtY, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPnDadosAviaoLayout.createSequentialGroup()
                                .addComponent(jLblAngulo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTxtAngulo, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPnDadosAviaoLayout.createSequentialGroup()
                                .addComponent(jLblAngulo1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTxtDirecao, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnDadosAviaoLayout.createSequentialGroup()
                        .addComponent(JBtnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnSalvarAviao, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPnDadosAviaoLayout.setVerticalGroup(
            jPnDadosAviaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnDadosAviaoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLblDadosAviao)
                .addGap(21, 21, 21)
                .addGroup(jPnDadosAviaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPnDadosAviaoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLblY))
                    .addGroup(jPnDadosAviaoLayout.createSequentialGroup()
                        .addGroup(jPnDadosAviaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPnDadosAviaoLayout.createSequentialGroup()
                                .addGroup(jPnDadosAviaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLblX)
                                    .addComponent(jTxtX, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnDadosAviaoLayout.createSequentialGroup()
                                .addComponent(jTxtY, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)))
                        .addGroup(jPnDadosAviaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLblRaio)
                            .addGroup(jPnDadosAviaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTxtRaio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLblAngulo)
                                .addComponent(jTxtAngulo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPnDadosAviaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTxtVelocidade, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLblVelocidade)
                            .addComponent(jLblAngulo1)
                            .addComponent(jTxtDirecao, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPnDadosAviaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnSalvarAviao, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBtnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(137, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPnEntradaDadosLayout = new javax.swing.GroupLayout(jPnEntradaDados);
        jPnEntradaDados.setLayout(jPnEntradaDadosLayout);
        jPnEntradaDadosLayout.setHorizontalGroup(
            jPnEntradaDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnEntradaDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPnDadosAviao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(585, Short.MAX_VALUE))
        );
        jPnEntradaDadosLayout.setVerticalGroup(
            jPnEntradaDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnEntradaDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPnDadosAviao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabOperacoesAvioes.addTab("Entrada de dados", jPnEntradaDados);

        jPnTranslandar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPnTranslandar.setPreferredSize(new java.awt.Dimension(230, 300));

        jLblTranslandarX.setText("X:");

        jTxtXTranslandar.setPreferredSize(new java.awt.Dimension(20, 23));

        jLblTranslandarY.setText("Y:");

        jTxtYTranslandar.setPreferredSize(new java.awt.Dimension(20, 23));

        jBtnTranslandar.setText("Translandar");
        jBtnTranslandar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnTranslandarMouseClicked(evt);
            }
        });

        jLblTranslandar.setText("Translandar");

        javax.swing.GroupLayout jPnTranslandarLayout = new javax.swing.GroupLayout(jPnTranslandar);
        jPnTranslandar.setLayout(jPnTranslandarLayout);
        jPnTranslandarLayout.setHorizontalGroup(
            jPnTranslandarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnTranslandarLayout.createSequentialGroup()
                .addGroup(jPnTranslandarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPnTranslandarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPnTranslandarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPnTranslandarLayout.createSequentialGroup()
                                .addComponent(jLblTranslandarX)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTxtXTranslandar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPnTranslandarLayout.createSequentialGroup()
                                .addComponent(jLblTranslandarY)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTxtYTranslandar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPnTranslandarLayout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jLblTranslandar))
                    .addGroup(jPnTranslandarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jBtnTranslandar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPnTranslandarLayout.setVerticalGroup(
            jPnTranslandarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnTranslandarLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLblTranslandar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPnTranslandarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTxtXTranslandar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLblTranslandarX, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPnTranslandarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTxtYTranslandar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLblTranslandarY, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnTranslandar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPnEscalonar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPnEscalonar.setPreferredSize(new java.awt.Dimension(230, 300));

        jTxtXEscalonar.setPreferredSize(new java.awt.Dimension(20, 23));

        jLblEscalonarX.setText("X:");

        jLblEscalonarY.setText("Y:");

        jTxtYEscalonar.setPreferredSize(new java.awt.Dimension(20, 23));

        jBtnEscalonar.setText("Escalonar");
        jBtnEscalonar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnEscalonarMouseClicked(evt);
            }
        });

        jLblEscalonar.setText("Escalonar");

        javax.swing.GroupLayout jPnEscalonarLayout = new javax.swing.GroupLayout(jPnEscalonar);
        jPnEscalonar.setLayout(jPnEscalonarLayout);
        jPnEscalonarLayout.setHorizontalGroup(
            jPnEscalonarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnEscalonarLayout.createSequentialGroup()
                .addGroup(jPnEscalonarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPnEscalonarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPnEscalonarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLblEscalonarX)
                            .addGroup(jPnEscalonarLayout.createSequentialGroup()
                                .addComponent(jLblEscalonarY)
                                .addGap(1, 1, 1)))
                        .addGroup(jPnEscalonarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPnEscalonarLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jTxtXEscalonar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPnEscalonarLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTxtYEscalonar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPnEscalonarLayout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(jLblEscalonar))
                    .addGroup(jPnEscalonarLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jBtnEscalonar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPnEscalonarLayout.setVerticalGroup(
            jPnEscalonarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnEscalonarLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLblEscalonar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPnEscalonarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTxtXEscalonar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLblEscalonarX, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPnEscalonarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLblEscalonarY, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtYEscalonar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnEscalonar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPnRotacionar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPnRotacionar.setPreferredSize(new java.awt.Dimension(230, 300));

        jLblAnguloRotacionar.setText("Ângulo:");

        jTxtAnguloRotacionar.setPreferredSize(new java.awt.Dimension(20, 23));

        jLblCentroRotacao.setText("Centro de Rotação");

        jLblRotacionarX.setText("X:");

        jTxtXRotacionar.setPreferredSize(new java.awt.Dimension(20, 23));

        jTxtYRotacionar.setPreferredSize(new java.awt.Dimension(20, 23));

        jLblRotacionarY.setText("Y:");

        jBtnRotacionar.setText("Rotacionar");
        jBtnRotacionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnRotacionarMouseClicked(evt);
            }
        });

        jLblRotacionar.setText("Rotacionar");

        javax.swing.GroupLayout jPnRotacionarLayout = new javax.swing.GroupLayout(jPnRotacionar);
        jPnRotacionar.setLayout(jPnRotacionarLayout);
        jPnRotacionarLayout.setHorizontalGroup(
            jPnRotacionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnRotacionarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPnRotacionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPnRotacionarLayout.createSequentialGroup()
                        .addGroup(jPnRotacionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPnRotacionarLayout.createSequentialGroup()
                                .addGroup(jPnRotacionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLblRotacionarX)
                                    .addComponent(jLblRotacionarY))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPnRotacionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPnRotacionarLayout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jTxtXRotacionar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jTxtYRotacionar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPnRotacionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPnRotacionarLayout.createSequentialGroup()
                                    .addComponent(jLblAnguloRotacionar)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTxtAnguloRotacionar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLblCentroRotacao))
                            .addGroup(jPnRotacionarLayout.createSequentialGroup()
                                .addComponent(jBtnRotacionar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)))
                        .addContainerGap(7, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnRotacionarLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLblRotacionar)
                        .addGap(85, 85, 85))))
        );
        jPnRotacionarLayout.setVerticalGroup(
            jPnRotacionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnRotacionarLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLblRotacionar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPnRotacionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblAnguloRotacionar)
                    .addComponent(jTxtAnguloRotacionar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLblCentroRotacao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPnRotacionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtXRotacionar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLblRotacionarX, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPnRotacionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtYRotacionar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLblRotacionarY, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                .addComponent(jBtnRotacionar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
                .addComponent(jPnRotacionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(364, Short.MAX_VALUE))
        );
        jPnFuncoesTransformacaoLayout.setVerticalGroup(
            jPnFuncoesTransformacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnFuncoesTransformacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPnFuncoesTransformacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPnEscalonar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                    .addComponent(jPnTranslandar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                    .addComponent(jPnRotacionar, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPnTranslandar.getAccessibleContext().setAccessibleName("");
        jPnEscalonar.getAccessibleContext().setAccessibleName("");
        jPnRotacionar.getAccessibleContext().setAccessibleName("");

        jTabOperacoesAvioes.addTab("Funções de Transformação", jPnFuncoesTransformacao);

        javax.swing.GroupLayout jPnOperacoesAvioesLayout = new javax.swing.GroupLayout(jPnOperacoesAvioes);
        jPnOperacoesAvioes.setLayout(jPnOperacoesAvioesLayout);
        jPnOperacoesAvioesLayout.setHorizontalGroup(
            jPnOperacoesAvioesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabOperacoesAvioes)
        );
        jPnOperacoesAvioesLayout.setVerticalGroup(
            jPnOperacoesAvioesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabOperacoesAvioes)
        );

        javax.swing.GroupLayout jPnAvioesLayout = new javax.swing.GroupLayout(jPnAvioes);
        jPnAvioes.setLayout(jPnAvioesLayout);
        jPnAvioesLayout.setHorizontalGroup(
            jPnAvioesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPnOperacoesAvioes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPnAvioesLayout.setVerticalGroup(
            jPnAvioesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnAvioesLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPnOperacoesAvioes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabAvioes.addTab("Aviões", jPnAvioes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabAvioes)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabAvioes)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnSalvarAviaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSalvarAviaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnSalvarAviaoActionPerformed

    private void jBtnSalvarAviaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnSalvarAviaoMouseClicked
        inserirOuEditarAviao();
        limparCampos();
        recarregarAvioes();
        recarregarAvioesNoRadar();
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
        UtilFuncoesTransformacao.translandar(getAviaoSelecionado(), getCoordenadaTranslacao());
        limparCamposDadosTranslacao();
        recarregarAvioes();
    }//GEN-LAST:event_jBtnTranslandarMouseClicked

    private void jBtnEscalonarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnEscalonarMouseClicked
        UtilFuncoesTransformacao.escalonar(getAviaoSelecionado(), getCoordenadaEscalonamento());
        limparCamposDadosEscalonamento();
        recarregarAvioes();
    }//GEN-LAST:event_jBtnEscalonarMouseClicked

    private void jBtnRotacionarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnRotacionarMouseClicked
        UtilFuncoesTransformacao.rotacionar(getAviaoSelecionado(), getCoordenadaRotacao());
        limparCamposDadosRotacao();
        recarregarAvioes();
    }//GEN-LAST:event_jBtnRotacionarMouseClicked

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
                if ("Nimbus".equals(info.getName())) {
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
    private javax.swing.JButton jBtnTranslandar;
    private javax.swing.JLabel jLblAngulo;
    private javax.swing.JLabel jLblAngulo1;
    private javax.swing.JLabel jLblAnguloRotacionar;
    private javax.swing.JLabel jLblCentroRotacao;
    private javax.swing.JLabel jLblDadosAviao;
    private javax.swing.JLabel jLblEscalonar;
    private javax.swing.JLabel jLblEscalonarX;
    private javax.swing.JLabel jLblEscalonarY;
    private javax.swing.JLabel jLblRaio;
    private javax.swing.JLabel jLblRelatorio;
    private javax.swing.JLabel jLblRotacionar;
    private javax.swing.JLabel jLblRotacionarX;
    private javax.swing.JLabel jLblRotacionarY;
    private javax.swing.JLabel jLblTranslandar;
    private javax.swing.JLabel jLblTranslandarX;
    private javax.swing.JLabel jLblTranslandarY;
    private javax.swing.JLabel jLblVelocidade;
    private javax.swing.JLabel jLblX;
    private javax.swing.JLabel jLblY;
    private javax.swing.JList<String> jListRelatorio;
    private javax.swing.JPanel jPnAvioes;
    private javax.swing.JPanel jPnDadosAviao;
    private javax.swing.JPanel jPnEntradaDados;
    private javax.swing.JPanel jPnEscalonar;
    private javax.swing.JPanel jPnFuncoesTransformacao;
    private javax.swing.JPanel jPnOperacoesAvioes;
    private javax.swing.JPanel jPnRadar;
    private javax.swing.JPanel jPnRotacionar;
    private javax.swing.JPanel jPnTranslandar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabAvioes;
    private javax.swing.JTabbedPane jTabOperacoesAvioes;
    private javax.swing.JTable jTbAvioes;
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
