package util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class LimiteCaracteresCampos extends PlainDocument {

	private int tamanhoMax = 15;

	public LimiteCaracteresCampos(int tamanhoMax) {
		this.tamanhoMax = tamanhoMax;
	}

	@Override
	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {

		if (str == null)
			return;

		String stringAntiga = getText(0, getLength());
		int tamanhoNovo = stringAntiga.length() + str.length();

		if (tamanhoNovo <= tamanhoMax) {
			super.insertString(offset, str, attr);
		} else {
			super.insertString(offset, "", attr);
		}
	}

}
