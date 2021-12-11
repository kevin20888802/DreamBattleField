package WindowSystem;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import MainSystem.MainClass;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow_1 extends JFrame 
{

	public MainClass MainClass;
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow_1 frame = new MainWindow_1();
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
	public MainWindow_1() {
		setResizable(false);
		setTitle("幻想競技場");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 848, 513);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("開始");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				MainClass.EnterMenu(2);
			}
		});
		btnNewButton.setBounds(313, 328, 210, 80);
		contentPane.add(btnNewButton);
		
		JLabel MenuTitle = new JLabel("幻想競技場");
		MenuTitle.setForeground(Color.BLACK);
		MenuTitle.setHorizontalAlignment(SwingConstants.CENTER);
		MenuTitle.setFont(new Font("新細明體", Font.PLAIN, 72));
		MenuTitle.setBounds(10, 10, 812, 192);
		contentPane.add(MenuTitle);
		
		JLabel MenuBG = new JLabel("");
		MenuBG.setBounds(10, 10, 812, 455);
		MenuBG.setIcon(new ImageIcon(MainWindow_1.class.getResource("/img/background/2DBg_Lab_Facilty1.png")));
		contentPane.add(MenuBG);
	}
}
