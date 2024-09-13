/*
public class FontesCod {


APARTIR DAQUI
    
	Font fontRegular = null;
	Font fontBold = null;
	
	BufferedInputStream fontRegulaFile = null;
	BufferedInputStream fontBoldFile = null;

	try {
		fontRegulaFile = new BufferedInputStream( new FileInputStream("src/fontes/Carlito-Regular.TTF"));
		fontBoldFile = new BufferedInputStream( new FileInputStream("src/fontes/Carlito-Bold.TTF"));
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    try {
		fontRegular = Font.createFont(Font.TRUETYPE_FONT, fontRegulaFile);
		fontBold= Font.createFont(Font.TRUETYPE_FONT, fontBoldFile);

	} catch (FontFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	NO LUGAR QUE TEM A FONTE DO ECLIPSE MESMO, SUBSTITUIR POR ESSA
	(fontRegular.deriveFont(Font.PLAIN, 24));
	
	
	
}
*/

import java.awt.Font;
