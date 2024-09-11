package visao;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class TelaInicial extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial frame = new TelaInicial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaInicial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 905, 571);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(161, 0, 29));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][][][grow]", "[grow][][][][][][][][][][][][][][][grow]"));
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(new ImageIcon("src/img/image 6.png").getImage().getScaledInstance(459, 237, Image.SCALE_DEFAULT)));
		contentPane.add(lblLogo, "cell 1 0");
		
		JLabel lblLinha1 = new JLabel("");
		lblLinha1.setIcon(new ImageIcon(new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(569, 2, Image.SCALE_DEFAULT)));
		contentPane.add(lblLinha1, "cell 1 2");
		
		JLabel lblCarrinho = new JLabel("");
		lblCarrinho.setIcon(new ImageIcon(new ImageIcon("src/img/carrinho.png").getImage().getScaledInstance(86, 86, Image.SCALE_DEFAULT)));
		contentPane.add(lblCarrinho, "cell 1 3");
		
		JLabel lblLinha2 = new JLabel("");
		lblLinha2.setIcon(new ImageIcon(new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(569, 2, Image.SCALE_DEFAULT)));
		contentPane.add(lblLinha2, "cell 1 4");
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(new ImageIcon("src/img/caixa.png").getImage().getScaledInstance(86, 78, Image.SCALE_DEFAULT)));
		contentPane.add(lblNewLabel, "cell 1 5");
		
		JLabel lblLinha3 = new JLabel("");
		lblLinha3.setIcon(new ImageIcon(new ImageIcon("src/img/Line7.png").getImage().getScaledInstance(569, 2, Image.SCALE_DEFAULT)));
		contentPane.add(lblLinha3, "cell 1 6");
	}

}
