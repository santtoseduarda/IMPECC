package visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MensagemViewOp extends JDialog {

	private static final long serialVersionUID = 1L;
	private int resposta;

	public MensagemViewOp(String pergunta, String titulo) {
		setTitle(titulo);
		setSize(350, 200);
		setModal(true);

		JPanel painel = new JPanel();
		painel.setLayout(new BorderLayout());
		getContentPane().add(painel);

/////////////configuração da fonte
		Font fontRegular = null;
		Font fontBold = null;

		BufferedInputStream fontRegulaFile = null;
		BufferedInputStream fontBoldFile = null;

		try {
			fontRegulaFile = new BufferedInputStream(new FileInputStream("src/fontes/Carlito-Regular.TTF"));
			fontBoldFile = new BufferedInputStream(new FileInputStream("src/fontes/Carlito-Bold.TTF"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			fontRegular = Font.createFont(Font.TRUETYPE_FONT, fontRegulaFile);
			fontBold = Font.createFont(Font.TRUETYPE_FONT, fontBoldFile);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}

		JLabel lablPergunta = new JLabel(pergunta, SwingConstants.CENTER);
		painel.add(lablPergunta, BorderLayout.CENTER);

		JPanel painelSul = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

		JButton btSim = new JButton("Sim");
		btSim.setPreferredSize(new Dimension(100, 30));
		btSim.setBackground(Color.RED);
		btSim.setForeground(new Color(255, 0, 0));
		btSim.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		btSim.setForeground(new Color(255, 255, 255));

		btSim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resposta = 1;
				setVisible(false);
			}
		});

		JButton btNao = new JButton("Não");
		btNao.setPreferredSize(new Dimension(100, 30));
		btNao.setPreferredSize(new Dimension(100, 30));
		btNao.setForeground(Color.BLACK);
		btNao.setForeground(new Color(255, 0, 0));
		btNao.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		btNao.setBackground(new Color(255, 255, 255));
		
		btNao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resposta = 0;
				setVisible(false);
			}
		});
		painelSul.add(btNao);
		painelSul.add(btSim);

		painel.add(painelSul, BorderLayout.SOUTH);

		setLocationRelativeTo(null);
		setVisible(true);

	}

	public int getResposta() {
		return resposta;
	}

}
