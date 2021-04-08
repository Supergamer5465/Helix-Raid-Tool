
package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GUIMain extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					final GUIMain frame = new GUIMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GUIMain() {
		this.setDefaultCloseOperation(3);
		this.setBounds(100, 100, 450, 300);
		this.setResizable(false);
		(this.contentPane = new JPanel()).setBackground(Color.BLACK);
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(this.contentPane);
		final GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 1, 73, 35, 153, 35, 79, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 1, 26, 60, 23, 55, 23, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		this.contentPane.setLayout(gbl_contentPane);
		final Component verticalStrut_2 = Box.createVerticalStrut(20);
		final GridBagConstraints gbc_verticalStrut_2 = new GridBagConstraints();
		gbc_verticalStrut_2.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_2.gridx = 0;
		gbc_verticalStrut_2.gridy = 0;
		this.contentPane.add(verticalStrut_2, gbc_verticalStrut_2);
		final Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		final GridBagConstraints gbc_horizontalStrut_3 = new GridBagConstraints();
		gbc_horizontalStrut_3.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_3.gridx = 0;
		gbc_horizontalStrut_3.gridy = 0;
		this.contentPane.add(horizontalStrut_3, gbc_horizontalStrut_3);
		final Component verticalStrut = Box.createVerticalStrut(20);
		final GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 0;
		gbc_verticalStrut.gridy = 0;
		this.contentPane.add(verticalStrut, gbc_verticalStrut);
		final Component horizontalStrut = Box.createHorizontalStrut(20);
		final GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut.gridx = 0;
		gbc_horizontalStrut.gridy = 0;
		this.contentPane.add(horizontalStrut, gbc_horizontalStrut);
		final Component verticalStrut_3 = Box.createVerticalStrut(20);
		final GridBagConstraints gbc_verticalStrut_3 = new GridBagConstraints();
		gbc_verticalStrut_3.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_3.gridx = 0;
		gbc_verticalStrut_3.gridy = 0;
		this.contentPane.add(verticalStrut_3, gbc_verticalStrut_3);
		final Component verticalStrut_4 = Box.createVerticalStrut(20);
		final GridBagConstraints gbc_verticalStrut_4 = new GridBagConstraints();
		gbc_verticalStrut_4.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_4.gridx = 0;
		gbc_verticalStrut_4.gridy = 0;
		this.contentPane.add(verticalStrut_4, gbc_verticalStrut_4);
		final Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		final GridBagConstraints gbc_horizontalStrut_4 = new GridBagConstraints();
		gbc_horizontalStrut_4.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_4.gridx = 0;
		gbc_horizontalStrut_4.gridy = 0;
		this.contentPane.add(horizontalStrut_4, gbc_horizontalStrut_4);
		final Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		final GridBagConstraints gbc_horizontalStrut_5 = new GridBagConstraints();
		gbc_horizontalStrut_5.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_5.gridx = 0;
		gbc_horizontalStrut_5.gridy = 0;
		this.contentPane.add(horizontalStrut_5, gbc_horizontalStrut_5);
		final JLabel lblNewLabel = new JLabel("Helix NukeBot");
		lblNewLabel.setBackground(Color.DARK_GRAY);
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Verdana", 1, 20));
		final GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 1;
		this.contentPane.add(lblNewLabel, gbc_lblNewLabel);
		final Component horizontalStrut_6 = Box.createHorizontalStrut(20);
		final GridBagConstraints gbc_horizontalStrut_6 = new GridBagConstraints();
		gbc_horizontalStrut_6.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_6.gridx = 0;
		gbc_horizontalStrut_6.gridy = 3;
		this.contentPane.add(horizontalStrut_6, gbc_horizontalStrut_6);
		final JButton btnNewButton = new JButton("Begin");
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setFont(new Font("Verdana", 1, 18));
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				GUIMain.this.setVisible(false);
				new BotFrame().setVisible(true);
			}
		});
		final GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridheight = 3;
		gbc_btnNewButton.gridwidth = 5;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 2;
		this.contentPane.add(btnNewButton, gbc_btnNewButton);
		final Component horizontalStrut_7 = Box.createHorizontalStrut(20);
		final GridBagConstraints gbc_horizontalStrut_7 = new GridBagConstraints();
		gbc_horizontalStrut_7.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut_7.gridx = 6;
		gbc_horizontalStrut_7.gridy = 3;
		this.contentPane.add(horizontalStrut_7, gbc_horizontalStrut_7);
		final JButton btnNewButton_2 = new JButton("About");
		btnNewButton_2.setForeground(Color.RED);
		btnNewButton_2.setBackground(Color.DARK_GRAY);
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				GUIMain.this.setVisible(false);
				new AboutFrame().setVisible(true);
			}
		});
		final GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_2.gridx = 3;
		gbc_btnNewButton_2.gridy = 5;
		this.contentPane.add(btnNewButton_2, gbc_btnNewButton_2);
	}
}
