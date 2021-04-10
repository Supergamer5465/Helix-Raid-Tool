package gui;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;

public class OutputStreamRedirect extends OutputStream {
	private JTextArea textArea;

	public OutputStreamRedirect(JTextArea textArea) {
		this.textArea = textArea;
	}

	@Override
	public void write(int b) throws IOException {
		// append output to text area
		textArea.append(String.valueOf((char) b));
		// scroll text area to bottom
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}
}