
package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import bot.BotRaidFunc;

public class BotFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	String path;
	private JPanel contentPane;
	private JTextField txtEnterBotToken;
	private JTextField txtEnterServerId;
	private JTextArea MBE;
	Runnable r;
	Thread t1;
	String token;
	String proxies;
	String sID;
	String msgC;
	String MBEl;

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					final BotFrame frame = new BotFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BotFrame() {
		this.setDefaultCloseOperation(3);
		this.setBounds(100, 100, 650, 750);
		(this.contentPane = new JPanel()).setBackground(Color.BLACK);
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(this.contentPane);
		final GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 225, 209 };
		gbl_contentPane.rowHeights = new int[] { 23, 0, 0, 0, 76, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0 };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0,
				1.0, 0.0, Double.MIN_VALUE };
		this.contentPane.setLayout(gbl_contentPane);

		// menu button
		final JButton btnNewButton = new JButton("Menu");
		btnNewButton.setForeground(Color.CYAN);
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				BotFrame.this.setVisible(false);
				new GUIMain().setVisible(true);
			}
		});
		final GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.anchor = 18;
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		this.contentPane.add(btnNewButton, gbc_btnNewButton);

		final JLabel lblNewLabel = new JLabel("Enter Bot Token");
		lblNewLabel.setBackground(Color.DARK_GRAY);
		lblNewLabel.setForeground(Color.CYAN);
		final GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		this.contentPane.add(lblNewLabel, gbc_lblNewLabel);

		// bot token text field
		this.txtEnterBotToken = new JTextField();
		final GridBagConstraints gbc_txtEnterBotToken = new GridBagConstraints();
		gbc_txtEnterBotToken.gridwidth = 2;
		gbc_txtEnterBotToken.insets = new Insets(0, 0, 5, 0);
		gbc_txtEnterBotToken.fill = 2;
		gbc_txtEnterBotToken.gridx = 0;
		gbc_txtEnterBotToken.gridy = 2;
		this.contentPane.add(this.txtEnterBotToken, gbc_txtEnterBotToken);
		this.txtEnterBotToken.setColumns(10);

		final JLabel lblNewLabel_2 = new JLabel("Enter HTTP proxies");
		lblNewLabel_2.setBackground(Color.DARK_GRAY);
		lblNewLabel_2.setForeground(Color.CYAN);
		final GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.gridwidth = 2;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 3;
		this.contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);

		// proxies text area
		final JScrollPane scrollPane = new JScrollPane();
		final GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = 1;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 4;
		this.contentPane.add(scrollPane, gbc_scrollPane);
		final JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		// stop raid button
		final JButton btnNewButton_2 = new JButton("Stop Raid");
		btnNewButton_2.setForeground(Color.CYAN);
		btnNewButton_2.setBackground(Color.DARK_GRAY);
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				for (final Thread t : Thread.getAllStackTraces().keySet()) {
					if (t != Thread.currentThread()) {
						t.interrupt();
					}
				}
			}
		});

		final JLabel lblNewLabel_3 = new JLabel("Message Content");
		lblNewLabel_3.setBackground(Color.DARK_GRAY);
		lblNewLabel_3.setForeground(Color.CYAN);
		final GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.gridwidth = 2;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 5;
		this.contentPane.add(lblNewLabel_3, gbc_lblNewLabel_3);
		final JScrollPane scrollPane_1 = new JScrollPane();
		final GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 2;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.fill = 1;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 6;
		this.contentPane.add(scrollPane_1, gbc_scrollPane_1);

		// message content text area
		final JTextArea msgArea = new JTextArea();
		scrollPane_1.setViewportView(msgArea);
		final JLabel lblNewLabel_4 = new JLabel("Enter Server ID");
		lblNewLabel_4.setBackground(Color.DARK_GRAY);
		lblNewLabel_4.setForeground(Color.CYAN);
		final GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.gridwidth = 2;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 7;
		this.contentPane.add(lblNewLabel_4, gbc_lblNewLabel_4);

		// button to enable mass ban
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Enable Mass Ban");
		rdbtnNewRadioButton.setBackground(Color.BLACK);
		rdbtnNewRadioButton.setForeground(Color.ORANGE);
		GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
		gbc_rdbtnNewRadioButton.gridwidth = 2;
		gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton.gridx = 0;
		gbc_rdbtnNewRadioButton.gridy = 9;
		contentPane.add(rdbtnNewRadioButton, gbc_rdbtnNewRadioButton);

		final JLabel lblNewLabel_5 = new JLabel("Mass Ban Exemption IDs");
		lblNewLabel_5.setBackground(Color.DARK_GRAY);
		lblNewLabel_5.setForeground(Color.CYAN);
		final GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.gridwidth = 2;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 10;
		this.contentPane.add(lblNewLabel_5, gbc_lblNewLabel_5);

		// start raid button
		final JButton btnNewButton_3 = new JButton("Start Raid");
		btnNewButton_3.setForeground(Color.CYAN);
		btnNewButton_3.setBackground(Color.DARK_GRAY);
		btnNewButton_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				BotFrame.this.token = BotFrame.this.txtEnterBotToken.getText();
				BotFrame.this.proxies = textArea.getText();
				BotFrame.this.sID = BotFrame.this.txtEnterServerId.getText();
				BotFrame.this.msgC = msgArea.getText();
				if (rdbtnNewRadioButton.isSelected()) {
					BotFrame.this.MBEl = BotFrame.this.MBE.getText();
				} else {
					BotFrame.this.MBEl = "";
				}
				if (token == null || token == "") {
					System.out.println("Missing Token");
					return;
				}
				if (proxies == null || proxies == "") {
					System.out.println("Missing Proxies");
					return;
				}
				if (sID == null || sID == "") {
					System.out.println("Missing Server ID");
					return;
				}
				if (msgC == null || msgC == "") {
					System.out.println("Missing Message Content");
					return;
				}
				BotFrame.this.r = new BotRaidFunc(BotFrame.this.token, BotFrame.this.proxies, BotFrame.this.sID,
						BotFrame.this.msgC, BotFrame.this.MBEl, rdbtnNewRadioButton.isSelected());
				(BotFrame.this.t1 = new Thread(BotFrame.this.r)).start();
			}
		});

		// server id text field
		this.txtEnterServerId = new JTextField();
		final GridBagConstraints gbc_txtEnterServerId = new GridBagConstraints();
		gbc_txtEnterServerId.gridwidth = 2;
		gbc_txtEnterServerId.insets = new Insets(0, 0, 5, 0);
		gbc_txtEnterServerId.fill = 2;
		gbc_txtEnterServerId.gridx = 0;
		gbc_txtEnterServerId.gridy = 8;
		this.contentPane.add(this.txtEnterServerId, gbc_txtEnterServerId);
		this.txtEnterServerId.setColumns(10);

		// mass ban exemption text area
		final JScrollPane scrollPane_2 = new JScrollPane();
		final GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.gridwidth = 2;
		gbc_scrollPane_2.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_2.fill = 1;
		gbc_scrollPane_2.gridx = 0;
		gbc_scrollPane_2.gridy = 11;
		this.contentPane.add(scrollPane_2, gbc_scrollPane_2);
		scrollPane_2.setViewportView(this.MBE = new JTextArea());

		JLabel lblNewLabel_1 = new JLabel("Output");
		lblNewLabel_1.setForeground(Color.CYAN);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 2;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 12;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);

		JScrollPane scrollPane_3 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_3 = new GridBagConstraints();
		gbc_scrollPane_3.gridwidth = 2;
		gbc_scrollPane_3.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_3.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_3.gridx = 0;
		gbc_scrollPane_3.gridy = 13;
		contentPane.add(scrollPane_3, gbc_scrollPane_3);

		// output box text area
		JTextArea textArea_output = new JTextArea();
		PrintStream ps = new PrintStream(new OutputStreamRedirect(textArea_output));
		System.setOut(ps);
		System.setErr(ps);
		scrollPane_3.setViewportView(textArea_output);
		final GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 14;
		this.contentPane.add(btnNewButton_3, gbc_btnNewButton_1);
		final GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.gridx = 1;
		gbc_btnNewButton_2.gridy = 14;
		this.contentPane.add(btnNewButton_2, gbc_btnNewButton_2);
	}
}
