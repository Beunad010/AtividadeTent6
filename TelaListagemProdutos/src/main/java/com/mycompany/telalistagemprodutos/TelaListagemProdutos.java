import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaListagemProdutos extends JFrame {
    private JTable tabela;
    private DefaultTableModel modelo;
    private JButton btnVenderProduto;

    public TelaListagemProdutos() {
        setTitle("Listagem de Produtos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nome");
        modelo.addColumn("Valor");
        modelo.addColumn("Status");

        tabela = new JTable(modelo);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        btnVenderProduto = new JButton("Vender Produto");
        btnVenderProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tabela.getSelectedRow();
                if (row != -1) {
                    int id = (int) tabela.getValueAt(row, 0);
                    ProdutosDAO dao = new ProdutosDAO();
                    dao.venderProduto(id);
                    modelo.setValueAt("Vendido", row, 3); // Atualiza o status na tabela
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um produto para vender.");
                }
            }
        });

        add(btnVenderProduto, BorderLayout.SOUTH);

        carregarProdutos();
    }

    private void carregarProdutos() {
        ProdutosDAO dao = new ProdutosDAO();
        ArrayList<ProdutosDTO> produtos = dao.listarProdutos();

        for (ProdutosDTO produto : produtos) {
            modelo.addRow(new Object[]{produto.getId(), produto.getNome(), produto.getValor(), produto.getStatus()});
        }
    }
}