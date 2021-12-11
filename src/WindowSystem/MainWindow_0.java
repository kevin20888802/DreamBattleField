package WindowSystem;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import MainSystem.DataSystem;
import MainSystem.MainClass;

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class MainWindow_0 extends JFrame 
{

	public MainClass MainClass;
	
	private JPanel contentPane;
	static DataSystem DS = new DataSystem();
	private JTable table;
	/**
	 * Launch the application.
	 * 視窗啟動主程序
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					MainWindow_0 frame = new MainWindow_0();
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
	 * 視窗產生
	 */
	public MainWindow_0() 
	{
		setResizable(false);
		setTitle("角色編輯器");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// 資料系統初始化
		DS = new DataSystem();
		DS.StartUp();
		
		JButton btnNewButton = new JButton("新增資料");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				DS.AddData();
			}
		});
		btnNewButton.setBounds(10, 516, 94, 36);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("刪除資料");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				DS.DeleteData(table.getSelectedRow());
			}
		});
		btnNewButton_2.setBounds(114, 516, 94, 36);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("儲存檔案");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				DS.SaveDB();
			}
		});
		btnNewButton_1.setBounds(218, 516, 94, 36);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("...");
		lblNewLabel.setBounds(10, 478, 198, 28);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 764, 440);
		contentPane.add(scrollPane);
		
		// 初始化表格
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(DS.DT);
		
		JButton btnNewButton_3 = new JButton("離開");
		btnNewButton_3.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				MainClass.SetupData();
				MainClass.EnterMenu(2);
				MainClass.CurrChar = MainClass.GetRandChar();
			}
		});
		btnNewButton_3.setBounds(680, 516, 94, 36);
		contentPane.add(btnNewButton_3);
	}
}
