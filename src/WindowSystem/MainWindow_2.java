package WindowSystem;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import MainSystem.MainClass;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow_2 extends JFrame 
{

	public MainClass MainClass;
	
	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					MainWindow_2 frame = new MainWindow_2();
					frame.setVisible(true);

				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public MainWindow_2() 
	{
		setResizable(false);
		setTitle("幻想競技場");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("開始");
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				MainClass.EnterMenu(5);
			}
		});
		btnNewButton.setFont(new Font("新細明體", Font.PLAIN, 21));
		btnNewButton.setBounds(35, 35, 152, 47);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("退出");
		btnNewButton_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				System.exit(0);
			}
		});
		btnNewButton_1.setFont(new Font("新細明體", Font.PLAIN, 21));
		btnNewButton_1.setBounds(35, 448, 152, 47);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("選擇角色");
		btnNewButton_1_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				MainClass.EnterMenu(4);
			}
		});
		btnNewButton_1_1.setFont(new Font("新細明體", Font.PLAIN, 21));
		btnNewButton_1_1.setBounds(137, 123, 152, 47);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("目前狀態");
		btnNewButton_1_2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				MainClass.EnterMenu(3);
			}
		});
		btnNewButton_1_2.setFont(new Font("新細明體", Font.PLAIN, 21));
		btnNewButton_1_2.setBounds(185, 241, 152, 47);
		contentPane.add(btnNewButton_1_2);
		
		JButton btnNewButton_1_3 = new JButton("角色編輯器");
		btnNewButton_1_3.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				MainClass.EnterMenu(0);
			}
		});
		btnNewButton_1_3.setFont(new Font("新細明體", Font.PLAIN, 21));
		btnNewButton_1_3.setBounds(137, 353, 152, 47);
		contentPane.add(btnNewButton_1_3);
		
		JLabel MenuSideKnob = new JLabel("");
		MenuSideKnob.setIcon(new ImageIcon(MainWindow_2.class.getResource("/img/ui/Knob2.png")));
		MenuSideKnob.setBounds(-262, 38, 500, 500);
		contentPane.add(MenuSideKnob);
		
		JLabel CurrCharLabel = new JLabel("目前角色：");
		CurrCharLabel.setForeground(Color.BLACK);
		CurrCharLabel.setBackground(Color.WHITE);
		CurrCharLabel.setFont(new Font("新細明體", Font.PLAIN, 32));
		CurrCharLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		CurrCharLabel.setBounds(259, 465, 500, 73);
		contentPane.putClientProperty("CurrCharLabel", CurrCharLabel);
		contentPane.add(CurrCharLabel);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setEnabled(false);
		btnNewButton_2.setBounds(248, 465, 528, 73);
		contentPane.add(btnNewButton_2);
		
		JLabel CurrCharImg = new JLabel("");
		CurrCharImg.setBounds(248, 10, 528, 528);
		contentPane.putClientProperty("CurrCharImg", CurrCharImg);
		contentPane.add(CurrCharImg);
		addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowOpened(WindowEvent e) 
			{
				RefreshShow();
			}
		});
		
		this.addComponentListener(new ComponentAdapter() 
		{
			   public void componentShown(ComponentEvent e) 
			   {
			      /* code run when component shown */
				  RefreshShow();
			   }
		});
	}
	
	
	public void RefreshShow()
	{
		JLabel NameDis = (JLabel)contentPane.getClientProperty("CurrCharLabel");
		JLabel ImgDis = (JLabel)contentPane.getClientProperty("CurrCharImg");
		if(NameDis != null)
		{
			if(MainClass != null && MainClass.CurrChar != null)
			{
				NameDis.setText("已選擇角色：" + MainClass.CurrChar.Data.Name);
			}
			else
			{
				NameDis.setText("已選擇角色：" + "無");
			}
		}
		if(ImgDis != null)
		{
			if(MainClass != null && MainClass.CurrChar != null && MainClass.CurrChar.Data.Img != null)
			{
				ImgDis.setIcon(new ImageIcon(MainClass.CurrChar.Data.Img.getImage().getScaledInstance(ImgDis.getWidth(), ImgDis.getHeight(), Image.SCALE_DEFAULT)));
			}
			else
			{
				ImgDis.setIcon(null);
			}
		}
	}
}
