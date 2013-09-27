package com.ias.chick;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.AbstractButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import com.ias.chick.level.SavedLoader;
import com.ias.chick.spell.Spell;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

// the launcher
public class StartFrame extends JFrame {

	private JPanel contentPane;
	
	static StartFrame frame;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new StartFrame();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StartFrame() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 200, 253);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnStart = new JButton("New Game");
		btnStart.setBounds(25, 71, 150, 29);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game.main(null);
	
			}
		});
		
		JButton btnLoad = new JButton("Load Game");
		btnLoad.setBounds(25, 131, 150, 29);
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game.main(null);
				SavedLoader.loadLevel("/saves/saved.lev", Game.game.levels);
			}
		});
		
		
		JButton btnInstructions = new JButton("Instructions");
		btnInstructions.setBounds(25, 161, 150, 29);
		btnInstructions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame instructions=new JFrame("Instructions");
				instructions.setResizable(false);
				instructions.setVisible(true);
				instructions.setSize(400, 300);
				
				JTextArea txtInst = new JTextArea();
				txtInst.setEditable(false);
				txtInst.setFont(new Font("Courier",Font.PLAIN,14));
				txtInst.setText("\n  Use WASD or the arrown keys to move\n" +
						"  Use SPACE to attack (when enemys are nerby)\n" +
						"  Use P to pause\n" +
						"  Use F5 to save the game \n" +
						"  Use the LEFT MOUSE BUTTON to cast the current\n" +
						"   spell, switch them with SHIFT or RMB\n");
				instructions.getContentPane().add(txtInst);
			}
		});
		
		
		JButton btnCredits = new JButton("Credits");
		btnCredits.setBounds(25, 191, 150, 29);
		btnCredits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame credits=new JFrame("Credits");
				credits.setResizable(false);
				credits.setVisible(true);
				credits.setSize(400, 200);
				
				JTextArea txtcred = new JTextArea();
				txtcred.setEditable(false);
				txtcred.setFont(new Font("Courier",Font.PLAIN,14));
				txtcred.setText("\n  A game by IA\n" +
						"  With help by SS\n" +
						"  written using Eclipse");
				credits.getContentPane().add(txtcred);
			}
		});
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(6, 10, 188, 50);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(StartFrame.class.getResource("/images/pio.png")));
		
		JButton button = new JButton("Arena");
		button.setBounds(25, 101, 150, 29);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game.main(null);
				Game.game.setLevel(Game.game.getLevel(19));
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnStart);
		contentPane.add(btnLoad);
		contentPane.add(btnInstructions);
		contentPane.add(btnCredits);
		contentPane.add(lblNewLabel);
		contentPane.add(button);
		
	}
}
