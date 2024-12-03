package visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MensagemView extends JDialog {

	private static final long serialVersionUID = 1L;

	public MensagemView(String mensagem, String titulo, int tipo) {
		setTitle(titulo);
		setSize(600, 200);
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

		JLabel lblMensagem = new JLabel(mensagem, SwingConstants.CENTER);
		painel.add(lblMensagem, BorderLayout.CENTER);
		
		switch (tipo) {
		case 0: // erro -> icone de atenção
	        ImageIcon icon = new ImageIcon("src/img/atencao.png");
	        Image image = icon.getImage();
	        Image resizedImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
	        lblMensagem.setIcon(new ImageIcon(resizedImage));
	        break;
		case 1: // mensagem de sucesso
			 ImageIcon iconSucesso = new ImageIcon("src/img/sucesso.png");
		     Image imageSucesso = iconSucesso.getImage();
		     Image resizedImageSucesso = imageSucesso.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		     lblMensagem.setIcon(new ImageIcon(resizedImageSucesso));
		     break;
		}
        
		JPanel painelSul = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
		painelSul.add(new JPanel());
		JButton btOk = new JButton("Ok");
		
		btOk.setPreferredSize(new Dimension(100, 30));
		btOk.setForeground(Color.RED);
		btOk.setForeground(new Color(255, 0, 0));
		btOk.setFont(fontBold.deriveFont(Font.PLAIN, 20));
		btOk.setBackground(new Color(255, 255, 255));
		
		
		btOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		painelSul.add(btOk);
		painelSul.add(new JPanel());
		painel.add(painelSul, BorderLayout.SOUTH);

		setLocationRelativeTo(null);
		setVisible(true);

	}
}