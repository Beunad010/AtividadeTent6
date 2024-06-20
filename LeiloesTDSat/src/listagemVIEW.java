import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class listagemVIEW extends javax.swing.JFrame {

    private javax.swing.JButton btnVender;
    private javax.swing.JTextField id_produto_venda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabela_produtos;

    public listagemVIEW() {
        initComponents();
        listarProdutos();
    }

    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabela_produtos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        id_produto_venda = new javax.swing.JTextField();
        btnVender = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabela_produtos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "ID", "Nome", "Valor", "Status"
            }
        ));
        jScrollPane1.setViewportView(tabela_produtos);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); 
        jLabel1.setText("Produtos Cadastrados");

        jLabel2.setText("ID do Produto:");

        btnVender.setText("Vender");
        btnVender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVenderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(227, 227, 227))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(id_produto_venda, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnVender)
                        .addGap(93, 93, 93))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(id_produto_venda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVender))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        pack();
    }

    private void btnVenderActionPerformed(java.awt.event.ActionEvent evt) {
        String id = id_produto_venda.getText();
        ProdutosDAO produtosdao = new ProdutosDAO();
        boolean success = produtosdao.venderProduto(Integer.parseInt(id));

        if (success) {
            JOptionPane.showMessageDialog(this, "Produto vendido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao vender produto.");
        }

        listarProdutos();
    }

    public void listarProdutos() {
        try {
            ProdutosDAO produtosdao = new ProdutosDAO();
            DefaultTableModel model = (DefaultTableModel) tabela_produtos.getModel();
            model.setNumRows(0);

            ArrayList<ProdutosDTO> listagem = produtosdao.listarProdutos();

            for (int num = 0; num < listagem.size(); num++) {
                model.addRow(new Object[]{
                    listagem.get(num).getId(),
                    listagem.get(num).getNome(),
                    listagem.get(num).getValor(),
                    listagem.get(num).getStatus()
                });
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "listarProdutos: " + erro.getMessage());
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new listagemVIEW().setVisible(true);
            }
        });
    }
}