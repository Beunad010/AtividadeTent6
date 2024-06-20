import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TelaVendas extends JFrame {
    private JTable tabela;
    private DefaultTableModel modelo;

    public TelaVendas() {
        setTitle("Produtos Vendidos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nome");
        modelo.addColumn("Valor");
        modelo.addColumn("Status");

        tabela = new JTable(modelo);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        carregarProdutosVendidos();
    }

    private void carregarProdutosVendidos() {
        ProdutosDAO dao = new ProdutosDAO();
        ArrayList<ProdutosDTO> vendidos = dao.listarProdutosVendidos();

        for (ProdutosDTO produto : vendidos) {
            modelo.addRow(new Object[]{produto.getId(), produto.getNome(), produto.getValor(), produto.getStatus()});
        }
    }
}