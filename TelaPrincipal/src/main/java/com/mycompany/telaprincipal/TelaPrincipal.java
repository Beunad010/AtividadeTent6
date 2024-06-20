import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal extends JFrame {
    private JButton btnConsultarVendas;

    public TelaPrincipal() {
        setTitle("Tela Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        btnConsultarVendas = new JButton("Consultar Vendas");
        btnConsultarVendas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaVendas telaVendas = new TelaVendas();
                telaVendas.setVisible(true);
            }
        });

        add(btnConsultarVendas, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        TelaPrincipal tela = new TelaPrincipal();
        tela.setVisible(true);

        TelaListagemProdutos telaListagem = new TelaListagemProdutos();
        telaListagem.setVisible(true);
    }
}