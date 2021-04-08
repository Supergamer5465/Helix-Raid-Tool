
package gui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class AboutFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					final AboutFrame frame = new AboutFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AboutFrame() {
		this.setDefaultCloseOperation(3);
		this.setBounds(100, 100, 450, 300);
		(this.contentPane = new JPanel()).setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(this.contentPane);
		final GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 424, 0 };
		gbl_contentPane.rowHeights = new int[] { 23, 168, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		this.contentPane.setLayout(gbl_contentPane);
		final JButton btnNewButton = new JButton("Menu");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				AboutFrame.this.setVisible(false);
				new GUIMain().setVisible(true);
			}
		});
		final GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = 18;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		this.contentPane.add(btnNewButton, gbc_btnNewButton);
		final JScrollPane scrollPane = new JScrollPane();
		final GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = 1;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		this.contentPane.add(scrollPane, gbc_scrollPane);
		final JTextArea txtrHelixNukebotCreated = new JTextArea();
		txtrHelixNukebotCreated.setText(
				"Helix NukeBot\r\n\r\nCreated by Supergamer5465\r\nwith support from Swish and Loko\r\n\r\nFull usage guide at https://github.com/Supergamer5465/Helix-Raid-Tool/");
		scrollPane.setViewportView(txtrHelixNukebotCreated);
	}
}
