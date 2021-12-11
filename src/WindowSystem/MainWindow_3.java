package WindowSystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DataType.BattleChar;
import DataType.BattleSkill;
import DataType.CharSkill;
import MainSystem.MainClass;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.SpringLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JScrollPane;
import javax.swing.JLayeredPane;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class MainWindow_3 extends JFrame 
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
					MainWindow_3 frame = new MainWindow_3();
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
	public MainWindow_3() 
	{
		setTitle("幻想競技場");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("返回");
		btnNewButton_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				MainClass.EnterMenu(2);
			}
		});
		btnNewButton_1.setFont(new Font("新細明體", Font.PLAIN, 21));
		btnNewButton_1.setBounds(25, 512, 152, 47);
		contentPane.add(btnNewButton_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 282, 492);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 21, 272, 470);
		panel.add(tabbedPane);
		
		JLayeredPane layeredPane = new JLayeredPane();
		tabbedPane.addTab("狀態", null, layeredPane, null);
		layeredPane.setLayout(null);
		
		JButton Show_Name_Value = new JButton("名稱");
		Show_Name_Value.setForeground(Color.BLACK);
		Show_Name_Value.setEnabled(false);
		Show_Name_Value.setBackground(Color.WHITE);
		Show_Name_Value.setBounds(80, 10, 182, 44);
		contentPane.putClientProperty("Show_Name_Value", Show_Name_Value);
		layeredPane.add(Show_Name_Value);
		
		JButton Show_Name_Title = new JButton("名稱");
		Show_Name_Title.setBounds(0, 10, 70, 44);
		Show_Name_Title.setForeground(Color.BLACK);
		Show_Name_Title.setEnabled(false);
		Show_Name_Title.setBackground(Color.WHITE);
		layeredPane.add(Show_Name_Title);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 144, 262, 108);
		panel_1.setLayout(null);
		layeredPane.add(panel_1);
		
		JButton Stat_Title = new JButton("STR");
		Stat_Title.setForeground(Color.BLACK);
		Stat_Title.setEnabled(false);
		Stat_Title.setBackground(Color.WHITE);
		Stat_Title.setBounds(10, 10, 57, 23);
		panel_1.add(Stat_Title);
		
		JButton Stat_Value = new JButton("120");
		Stat_Value.setForeground(Color.BLACK);
		Stat_Value.setEnabled(false);
		Stat_Value.setBackground(Color.WHITE);
		Stat_Value.setBounds(72, 10, 57, 23);
		contentPane.putClientProperty("Stat_Value", Stat_Value);
		panel_1.add(Stat_Value);
		
		JButton Stat_Title_1 = new JButton("AGI");
		Stat_Title_1.setForeground(Color.BLACK);
		Stat_Title_1.setEnabled(false);
		Stat_Title_1.setBackground(Color.WHITE);
		Stat_Title_1.setBounds(10, 42, 57, 23);
		panel_1.add(Stat_Title_1);
		
		JButton Stat_Value_1 = new JButton("120");
		Stat_Value_1.setForeground(Color.BLACK);
		Stat_Value_1.setEnabled(false);
		Stat_Value_1.setBackground(Color.WHITE);
		Stat_Value_1.setBounds(72, 42, 57, 23);
		contentPane.putClientProperty("Stat_Value_1", Stat_Value_1);
		panel_1.add(Stat_Value_1);
		
		JButton Stat_Title_2 = new JButton("VIT");
		Stat_Title_2.setForeground(Color.BLACK);
		Stat_Title_2.setEnabled(false);
		Stat_Title_2.setBackground(Color.WHITE);
		Stat_Title_2.setBounds(10, 75, 57, 23);
		panel_1.add(Stat_Title_2);
		
		JButton Stat_Value_2 = new JButton("120");
		Stat_Value_2.setForeground(Color.BLACK);
		Stat_Value_2.setEnabled(false);
		Stat_Value_2.setBackground(Color.WHITE);
		Stat_Value_2.setBounds(72, 75, 57, 23);
		contentPane.putClientProperty("Stat_Value_2", Stat_Value_2);
		panel_1.add(Stat_Value_2);
		
		JButton Stat_Title_3 = new JButton("INT");
		Stat_Title_3.setForeground(Color.BLACK);
		Stat_Title_3.setEnabled(false);
		Stat_Title_3.setBackground(Color.WHITE);
		Stat_Title_3.setBounds(139, 10, 57, 23);
		panel_1.add(Stat_Title_3);
		
		JButton Stat_Value_3 = new JButton("120");
		Stat_Value_3.setForeground(Color.BLACK);
		Stat_Value_3.setEnabled(false);
		Stat_Value_3.setBackground(Color.WHITE);
		Stat_Value_3.setBounds(201, 10, 57, 23);
		contentPane.putClientProperty("Stat_Value_3", Stat_Value_3);
		panel_1.add(Stat_Value_3);
		
		JButton Stat_Title_4 = new JButton("DEX");
		Stat_Title_4.setForeground(Color.BLACK);
		Stat_Title_4.setEnabled(false);
		Stat_Title_4.setBackground(Color.WHITE);
		Stat_Title_4.setBounds(139, 42, 57, 23);
		panel_1.add(Stat_Title_4);
		
		JButton Stat_Value_4 = new JButton("120");
		Stat_Value_4.setForeground(Color.BLACK);
		Stat_Value_4.setEnabled(false);
		Stat_Value_4.setBackground(Color.WHITE);
		Stat_Value_4.setBounds(201, 42, 57, 23);
		contentPane.putClientProperty("Stat_Value_4", Stat_Value_4);
		panel_1.add(Stat_Value_4);
		
		JButton Stat_Title_5 = new JButton("LUK");
		Stat_Title_5.setForeground(Color.BLACK);
		Stat_Title_5.setEnabled(false);
		Stat_Title_5.setBackground(Color.WHITE);
		Stat_Title_5.setBounds(139, 75, 57, 23);
		panel_1.add(Stat_Title_5);
		
		JButton Stat_Value_5 = new JButton("120");
		Stat_Value_5.setForeground(Color.BLACK);
		Stat_Value_5.setEnabled(false);
		Stat_Value_5.setBackground(Color.WHITE);
		Stat_Value_5.setBounds(201, 75, 57, 23);
		contentPane.putClientProperty("Stat_Value_5", Stat_Value_5);
		panel_1.add(Stat_Value_5);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(0, 97, 262, 48);
		layeredPane.add(panel_2);
		
		JLabel EXP_Value = new JLabel("00000/00000");
		EXP_Value.setHorizontalAlignment(SwingConstants.CENTER);
		EXP_Value.setBounds(79, 13, 172, 22);
		contentPane.putClientProperty("EXP_Value", EXP_Value);
		panel_2.add(EXP_Value);
		
		JButton LV_Value = new JButton("185");
		LV_Value.setForeground(Color.BLACK);
		LV_Value.setEnabled(false);
		LV_Value.setBackground(Color.WHITE);
		LV_Value.setBounds(10, 10, 59, 28);
		contentPane.putClientProperty("LV_Value", LV_Value);
		panel_2.add(LV_Value);
		
		JProgressBar EXP_Bar = new JProgressBar();
		EXP_Bar.setValue(50);
		EXP_Bar.setBounds(80, 10, 172, 28);
		contentPane.putClientProperty("EXP_Bar", EXP_Bar);
		panel_2.add(EXP_Bar);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 252, 262, 189);
		layeredPane.add(panel_3);
		panel_3.setLayout(null);
		
		JButton Status_Title = new JButton("最大生命");
		Status_Title.setBounds(10, 10, 92, 23);
		Status_Title.setForeground(Color.BLACK);
		Status_Title.setEnabled(false);
		Status_Title.setBackground(Color.WHITE);
		panel_3.add(Status_Title);
		
		JButton Status_Value = new JButton("120");
		Status_Value.setBounds(101, 10, 151, 23);
		Status_Value.setForeground(Color.BLACK);
		Status_Value.setEnabled(false);
		Status_Value.setBackground(Color.WHITE);
		contentPane.putClientProperty("Status_Value", Status_Value);
		panel_3.add(Status_Value);
		
		JButton Status_Value_1 = new JButton("120");
		Status_Value_1.setForeground(Color.BLACK);
		Status_Value_1.setEnabled(false);
		Status_Value_1.setBackground(Color.WHITE);
		Status_Value_1.setBounds(101, 34, 151, 23);
		contentPane.putClientProperty("Status_Value_1", Status_Value_1);
		panel_3.add(Status_Value_1);
		
		JButton Status_Title_1 = new JButton("最大魔力");
		Status_Title_1.setForeground(Color.BLACK);
		Status_Title_1.setEnabled(false);
		Status_Title_1.setBackground(Color.WHITE);
		Status_Title_1.setBounds(10, 34, 92, 23);
		panel_3.add(Status_Title_1);
		
		JButton Status_Title_2 = new JButton("攻擊力");
		Status_Title_2.setForeground(Color.BLACK);
		Status_Title_2.setEnabled(false);
		Status_Title_2.setBackground(Color.WHITE);
		Status_Title_2.setBounds(10, 56, 92, 23);
		panel_3.add(Status_Title_2);
		
		JButton Status_Value_2 = new JButton("120");
		Status_Value_2.setForeground(Color.BLACK);
		Status_Value_2.setEnabled(false);
		Status_Value_2.setBackground(Color.WHITE);
		Status_Value_2.setBounds(101, 56, 151, 23);
		contentPane.putClientProperty("Status_Value_2", Status_Value_2);
		panel_3.add(Status_Value_2);
		
		JButton Status_Title_3 = new JButton("魔法攻擊");
		Status_Title_3.setForeground(Color.BLACK);
		Status_Title_3.setEnabled(false);
		Status_Title_3.setBackground(Color.WHITE);
		Status_Title_3.setBounds(10, 78, 92, 23);
		panel_3.add(Status_Title_3);
		
		JButton Status_Value_3 = new JButton("120");
		Status_Value_3.setForeground(Color.BLACK);
		Status_Value_3.setEnabled(false);
		Status_Value_3.setBackground(Color.WHITE);
		Status_Value_3.setBounds(101, 78, 151, 23);
		contentPane.putClientProperty("Status_Value_3", Status_Value_3);
		panel_3.add(Status_Value_3);
		
		JButton Status_Title_4 = new JButton("物理防禦");
		Status_Title_4.setForeground(Color.BLACK);
		Status_Title_4.setEnabled(false);
		Status_Title_4.setBackground(Color.WHITE);
		Status_Title_4.setBounds(10, 100, 92, 23);
		panel_3.add(Status_Title_4);
		
		JButton Status_Value_4 = new JButton("120");
		Status_Value_4.setForeground(Color.BLACK);
		Status_Value_4.setEnabled(false);
		Status_Value_4.setBackground(Color.WHITE);
		Status_Value_4.setBounds(101, 100, 151, 23);
		contentPane.putClientProperty("Status_Value_4", Status_Value_4);
		panel_3.add(Status_Value_4);
		
		JButton Status_Title_5 = new JButton("魔法防禦");
		Status_Title_5.setForeground(Color.BLACK);
		Status_Title_5.setEnabled(false);
		Status_Title_5.setBackground(Color.WHITE);
		Status_Title_5.setBounds(10, 123, 92, 23);
		panel_3.add(Status_Title_5);
		
		JButton Status_Value_5 = new JButton("120");
		Status_Value_5.setForeground(Color.BLACK);
		Status_Value_5.setEnabled(false);
		Status_Value_5.setBackground(Color.WHITE);
		Status_Value_5.setBounds(101, 123, 151, 23);
		contentPane.putClientProperty("Status_Value_5", Status_Value_5);
		panel_3.add(Status_Value_5);
		
		JButton Status_Title_6 = new JButton("冷卻減少");
		Status_Title_6.setForeground(Color.BLACK);
		Status_Title_6.setEnabled(false);
		Status_Title_6.setBackground(Color.WHITE);
		Status_Title_6.setBounds(10, 145, 92, 23);
		panel_3.add(Status_Title_6);
		
		JButton Status_Value_6 = new JButton("120");
		Status_Value_6.setForeground(Color.BLACK);
		Status_Value_6.setEnabled(false);
		Status_Value_6.setBackground(Color.WHITE);
		Status_Value_6.setBounds(101, 145, 151, 23);
		contentPane.putClientProperty("Status_Value_6", Status_Value_6);
		panel_3.add(Status_Value_6);
		
		JButton Show_Class_Title = new JButton("職業");
		Show_Class_Title.setForeground(Color.BLACK);
		Show_Class_Title.setEnabled(false);
		Show_Class_Title.setBackground(Color.WHITE);
		Show_Class_Title.setBounds(0, 64, 70, 23);
		layeredPane.add(Show_Class_Title);
		
		JButton Show_Class_Value = new JButton("名稱");
		Show_Class_Value.setForeground(Color.BLACK);
		Show_Class_Value.setEnabled(false);
		Show_Class_Value.setBackground(Color.WHITE);
		Show_Class_Value.setBounds(80, 64, 182, 23);
		contentPane.putClientProperty("Show_Class_Value", Show_Class_Value);
		layeredPane.add(Show_Class_Value);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		tabbedPane.addTab("技能", null, layeredPane_1, null);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(0, 0, 267, 431);
		contentPane.putClientProperty("textArea", textArea);
		layeredPane_1.add(textArea);
		
		JLabel CurrCharImg = new JLabel("");
		CurrCharImg.setBounds(235, 10, 549, 549);
		contentPane.putClientProperty("CurrCharImg", CurrCharImg);
		contentPane.add(CurrCharImg);
		
		this.addComponentListener(new ComponentAdapter() 
		{
			   public void componentShown(ComponentEvent e) 
			   {
			      /* code run when component shown */
				   UpdateShowData();
			   }
		});
	}
	
	public void UpdateShowData()
	{
		if(MainClass != null && MainClass.CurrChar != null)
		{
			BattleChar _theChar = MainClass.CurrChar;
			_theChar.Data.LVUpCheck();
			JButton Show_Name = (JButton)contentPane.getClientProperty("Show_Name_Value");
			JButton Show_Class = (JButton)contentPane.getClientProperty("Show_Class_Value");
			JButton Show_LV = (JButton)contentPane.getClientProperty("LV_Value");
			JLabel Show_EXP_Text = (JLabel)contentPane.getClientProperty("EXP_Value");
			JLabel Char_Img = (JLabel)contentPane.getClientProperty("CurrCharImg");
			JProgressBar Show_EXP_Bar = (JProgressBar)contentPane.getClientProperty("EXP_Bar");
			JButton Show_Stat_0 = (JButton)contentPane.getClientProperty("Stat_Value");
			JButton Show_Stat_1 = (JButton)contentPane.getClientProperty("Stat_Value_1");
			JButton Show_Stat_2 = (JButton)contentPane.getClientProperty("Stat_Value_2");
			JButton Show_Stat_3 = (JButton)contentPane.getClientProperty("Stat_Value_3");
			JButton Show_Stat_4 = (JButton)contentPane.getClientProperty("Stat_Value_4");
			JButton Show_Stat_5 = (JButton)contentPane.getClientProperty("Stat_Value_5");
			JButton Show_Status_0 = (JButton)contentPane.getClientProperty("Status_Value");
			JButton Show_Status_1 = (JButton)contentPane.getClientProperty("Status_Value_1");
			JButton Show_Status_2 = (JButton)contentPane.getClientProperty("Status_Value_2");
			JButton Show_Status_3 = (JButton)contentPane.getClientProperty("Status_Value_3");
			JButton Show_Status_4 = (JButton)contentPane.getClientProperty("Status_Value_4");
			JButton Show_Status_5 = (JButton)contentPane.getClientProperty("Status_Value_5");
			JButton Show_Status_6 = (JButton)contentPane.getClientProperty("Status_Value_6");
			JTextArea SkillList = (JTextArea)contentPane.getClientProperty("textArea");
			if(MainClass != null && _theChar != null && _theChar.Data.Img != null)
			{
				Char_Img.setIcon(new ImageIcon(MainClass.CurrChar.Data.Img.getImage().getScaledInstance(Char_Img.getWidth(), Char_Img.getHeight(), Image.SCALE_DEFAULT)));
			}
			else
			{
				Char_Img.setIcon(null);
			}
			
			Show_Name.setText(_theChar.Data.Name);
			Show_Class.setText(_theChar.ClassData().ShowName);
			Show_LV.setText(String.valueOf(_theChar.Data.LV));
			Show_EXP_Text.setText(String.valueOf(_theChar.Data.EXP) + "/" + _theChar.Data.LVUpExp());
			Show_EXP_Bar.setValue((int)(((double)_theChar.Data.EXP / (double)_theChar.Data.LVUpExp()) * 100) );
			System.out.println(Show_EXP_Bar.getValue());
			Show_Stat_0.setText(String.valueOf(_theChar.Data.STR));
			Show_Stat_1.setText(String.valueOf(_theChar.Data.AGI));
			Show_Stat_2.setText(String.valueOf(_theChar.Data.VIT));
			Show_Stat_3.setText(String.valueOf(_theChar.Data.INT));
			Show_Stat_4.setText(String.valueOf(_theChar.Data.DEX));
			Show_Stat_5.setText(String.valueOf(_theChar.Data.LUK));
			Show_Status_0.setText(String.valueOf(_theChar.MHP()));
			Show_Status_1.setText(String.valueOf(_theChar.MMP()));
			Show_Status_2.setText(String.valueOf(_theChar.ATK()));
			Show_Status_3.setText(String.valueOf(_theChar.MAT()));
			Show_Status_4.setText(String.valueOf(_theChar.DEF()));
			Show_Status_5.setText(String.valueOf(_theChar.MDF()));
			Show_Status_6.setText(String.valueOf(_theChar.CDReduce()));
			
			System.out.println("技能數：" + _theChar.ClassData().Skills.size());
			_theChar.RefreshSkills();
			String _tmp = "";
			for(int i = 0 ; i < _theChar.Skills.size() ; i++)
			{
				BattleSkill _runtimeSkill = _theChar.Skills.get(i);
				CharSkill _skillData = _runtimeSkill.Data;
				_tmp += "———————————————————————————————————\n";
				_tmp += "[" + _skillData.ShowName + "]\n"
						+ _skillData.Description
						+ "\n\n"
						+ "消耗魔力：" + _skillData.CostMP;
				if(_skillData.CostHP > 0)
				{
					_tmp +=  "　　　　消耗生命：" + _skillData.CostHP ;
				}
				_tmp += "\n" + "冷卻回合數：" + _runtimeSkill.MaxCD();
				_tmp += "\n———————————————————————————————————";
				//System.out.println(_tmp);
			}
			SkillList.setText(_tmp);
		}
		else
		{
			if(MainClass != null)
			{
				MainClass.EnterMenu(2);
			}
		}
	}
}
