package WindowSystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import MainSystem.*;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;

public class MainWindow_5 extends JFrame 
{
	
	public static MainWindow_5 instance;
	
	public MainClass MainClass;
	public BattleSystem BS;
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow_5 frame = new MainWindow_5();
					frame.setVisible(true);
					instance = frame;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow_5() 
	{
		KeyAdapter _KeyAdapter = new KeyAdapter() 
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				int i_code = e.getKeyCode();
				System.out.println("按鍵：" + i_code);
				if(i_code == e.VK_ENTER)
				{
					BS.MenuEnter();
				}
				else if(i_code == e.VK_ESCAPE | i_code == e.VK_BACK_SPACE)
				{
					BS.BackMenu();
				}
				else if(i_code == e.VK_UP)
				{
					BS.MenuSwitch(false);
				}
				else if(i_code == e.VK_DOWN)
				{
					BS.MenuSwitch(true);
				}
			}
		};
		
		instance = this;
		
		setTitle("幻想競技場");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.addKeyListener(_KeyAdapter);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 327, 764, 225);
		contentPane.add(panel);
		panel.addKeyListener(_KeyAdapter);
		panel.setLayout(null);
		
		JLabel PlayerIcon = new JLabel("");
		PlayerIcon.setIcon(new ImageIcon(MainWindow_5.class.getResource("/img/icon/kfc0.jpg")));
		PlayerIcon.setBounds(10, 15, 200, 200);
		contentPane.putClientProperty("PlayerIcon", PlayerIcon);
		PlayerIcon.addKeyListener(_KeyAdapter);
		panel.add(PlayerIcon);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(220, 15, 534, 200);
		panel.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		contentPane.putClientProperty("textArea", textArea);
		textArea.addKeyListener(_KeyAdapter);
		
		JLabel EnemyIcon = new JLabel("");
		EnemyIcon.setIcon(new ImageIcon(MainWindow_5.class.getResource("/img/icon/kfc0.jpg")));
		EnemyIcon.setBounds(265, 93, 250, 250);
		contentPane.putClientProperty("EnemyIcon_0", EnemyIcon);
		EnemyIcon.addKeyListener(_KeyAdapter);
		contentPane.add(EnemyIcon);
		
		JLabel EnemyIcon_1 = new JLabel("");
		EnemyIcon_1.setIcon(new ImageIcon(MainWindow_5.class.getResource("/img/icon/kfc0.jpg")));
		EnemyIcon_1.setBounds(104, 60, 250, 250);
		contentPane.putClientProperty("EnemyIcon_1", EnemyIcon_1);
		EnemyIcon_1.addKeyListener(_KeyAdapter);
		contentPane.add(EnemyIcon_1);
		
		JLabel EnemyIcon_2 = new JLabel("");
		EnemyIcon_2.setIcon(new ImageIcon(MainWindow_5.class.getResource("/img/icon/kfc0.jpg")));
		EnemyIcon_2.setBounds(435, 53, 250, 250);
		contentPane.putClientProperty("EnemyIcon_2", EnemyIcon_2);
		EnemyIcon_2.addKeyListener(_KeyAdapter);
		contentPane.add(EnemyIcon_2);
		
		JLabel EnemyIcon_3 = new JLabel("");
		EnemyIcon_3.setIcon(new ImageIcon(MainWindow_5.class.getResource("/img/icon/kfc0.jpg")));
		EnemyIcon_3.setBounds(10, 10, 250, 250);
		contentPane.putClientProperty("EnemyIcon_3", EnemyIcon_3);
		EnemyIcon_3.addKeyListener(_KeyAdapter);
		contentPane.add(EnemyIcon_3);
		
		JLabel EnemyIcon_4 = new JLabel("");
		EnemyIcon_4.setIcon(new ImageIcon(MainWindow_5.class.getResource("/img/icon/kfc0.jpg")));
		EnemyIcon_4.setBounds(525, 10, 250, 250);
		contentPane.putClientProperty("EnemyIcon_4", EnemyIcon_4);
		EnemyIcon_4.addKeyListener(_KeyAdapter);
		contentPane.add(EnemyIcon_4);
		
		JLabel Background = new JLabel("");
		Background.setIcon(new ImageIcon(MainWindow_5.class.getResource("/img/background/2DBg_Forest_Day.jpg")));
		Background.setBounds(0, 0, 784, 562);
		Background.addKeyListener(_KeyAdapter);
		contentPane.add(Background);
		
		this.addKeyListener(_KeyAdapter);
		
		this.addComponentListener(new ComponentAdapter() 
		{
			public void componentShown(ComponentEvent e) 
			{
				if(MainClass != null && MainClass.CurrChar != null)
				{
					BS = new BattleSystem(instance , MainClass);
					BS.StartUp();
				}
				else
				{
					if(MainClass != null)
					{
						MainClass.EnterMenu(2);
					}
				}
			}
		});
	}
	
	public void RefreshScreen()
	{
		JTextArea ConsoleScreen = (JTextArea)contentPane.getClientProperty("textArea");
		ConsoleScreen.setText(BS.ConsoleScreen);
		JLabel PIcon = (JLabel)contentPane.getClientProperty("PlayerIcon");
		PIcon.setIcon(new ImageIcon(MainClass.CurrChar.Data.Img.getImage().getScaledInstance(PIcon.getWidth(), PIcon.getHeight(), Image.SCALE_DEFAULT)));
		JLabel[] EnemyIcons = new JLabel[5];
		for(int i = 0 ; i < EnemyIcons.length; i++)
		{
			EnemyIcons[i] = (JLabel)contentPane.getClientProperty("EnemyIcon_" + i);
		}
		for(int i = 0 ; i < EnemyIcons.length; i++)
		{
			if(i >= BS._Enemy.size())
			{
				EnemyIcons[i].setVisible(false);
			}
			else 
			{
				if(BS._Enemy.get(i).Dead())
				{
					EnemyIcons[i].setVisible(false);
				}
				else
				{
					EnemyIcons[i].setVisible(true);
					if(BS._Enemy.get(i).Data.Img != null)
					{
						EnemyIcons[i].setIcon(new ImageIcon(BS._Enemy.get(i).Data.Img.getImage().getScaledInstance(EnemyIcons[i].getWidth(), EnemyIcons[i].getHeight(), Image.SCALE_DEFAULT)));
					}
				}
			}
		}
		
	}
}
