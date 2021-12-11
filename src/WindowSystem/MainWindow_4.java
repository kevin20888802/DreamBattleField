package WindowSystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import MainSystem.MainClass;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow_4 extends JFrame 
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
					MainWindow_4 frame = new MainWindow_4();
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
	public MainWindow_4() 
	{

		setTitle("幻想競技場");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton BackBtn = new JButton("");
		BackBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(MainClass != null && MainClass.CurrChar != null)
				{
					MainClass.SwitchChar(false);
					RefreshShow();
				}
			}
		});
		BackBtn.setForeground(SystemColor.menu);
		BackBtn.setBackground(SystemColor.menu);
		BackBtn.setIcon(new ImageIcon(MainWindow_4.class.getResource("/img/ui/LeftArrow.png")));
		BackBtn.setBounds(10, 177, 100, 100);
		contentPane.add(BackBtn);
		
		JButton NextBtn = new JButton("");
		NextBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(MainClass != null && MainClass.CurrChar != null)
				{
					MainClass.SwitchChar(true);
					RefreshShow();
				}
			}
		});
		NextBtn.setIcon(new ImageIcon(MainWindow_4.class.getResource("/img/ui/RightArrow.png")));
		NextBtn.setForeground(SystemColor.menu);
		NextBtn.setBackground(SystemColor.menu);
		NextBtn.setBounds(682, 177, 100, 100);
		contentPane.add(NextBtn);
		
		JButton btnNewButton_1 = new JButton("返回");
		btnNewButton_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				MainClass.EnterMenu(2);
			}
		});
		btnNewButton_1.setFont(new Font("新細明體", Font.PLAIN, 21));
		btnNewButton_1.setBounds(10, 511, 152, 47);
		contentPane.add(btnNewButton_1);
		
		JButton CurrCharName = new JButton("New button");
		CurrCharName.setEnabled(false);
		CurrCharName.setBounds(146, 25, 501, 47);
		contentPane.putClientProperty("CurrCharName", CurrCharName);
		contentPane.add(CurrCharName);
		
		JLabel CurrCharImg = new JLabel("");
		CurrCharImg.setBounds(136, 12, 528, 528);
		contentPane.putClientProperty("CurrCharImg", CurrCharImg);
		contentPane.add(CurrCharImg);
		
		this.addComponentListener(new ComponentAdapter() 
		{
			public void componentShown(ComponentEvent e) 
			{
				if(MainClass != null && MainClass.CurrChar != null)
				{
					RefreshShow();
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
	
	public void RefreshShow()
	{
		JButton NameDis = (JButton)contentPane.getClientProperty("CurrCharName");
		JLabel ImgDis = (JLabel)contentPane.getClientProperty("CurrCharImg");
		if(NameDis != null)
		{
			if(MainClass != null && MainClass.CurrChar != null)
			{
				NameDis.setText(MainClass.CurrChar.Data.Name + "　-　" + MainClass.CurrChar.ClassData().ShowName + "（Lv."+ MainClass.CurrChar.Data.LV + "）");
			}
			else
			{
				NameDis.setText("無");
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
