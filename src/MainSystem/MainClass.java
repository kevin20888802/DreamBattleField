package MainSystem;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import DataType.*;
import WindowSystem.*;


/**
 * 
 */

/**
 * @author kevin2088
 *
 */
public class MainClass 
{
	List<JFrame> WindowList = new ArrayList<JFrame>();
	public static MainClass instance;
	
	private DataSystem DS;
	private RuntimeDataManager RDM;
	public BattleChar CurrChar;
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		instance = new MainClass();
		instance.StartUp();
	}
	
	public void StartUp()
	{
		SetupData();
		SetupMenus();
		EnterMenu(1);
		CurrChar = GetRandChar();
	}
	
	public void SetupData()
	{
		DS = new DataSystem();
		DS.StartUp();
		RDM = new RuntimeDataManager();
		RDM.DS = DS;
		RDM.Setup();
	}
	
	public void SwitchChar(Boolean i_next)
	{
		int _addInd = 0;
		if(i_next)
		{
			_addInd = 1;
		}
		else
		{
			_addInd = -1;
		}
		int _currIndex = RDM.CharIndexByID(CurrChar.Data.ID) + _addInd;
		if(_currIndex < 0)
		{
			_currIndex = RDM.Characters.size() - 1;
		}
		if(_currIndex >= RDM.Characters.size())
		{
			_currIndex = 0;
		}
		CurrChar = RDM.GetChar(_currIndex);
	}

	public BattleChar GetRandChar()
	{
		return RDM.GetChar((int)(Math.random() * RDM.Characters.size()));
	}
	
	public void SetupMenus()
	{
		WindowList = new ArrayList<JFrame>();
		MainWindow_0 win_0 = new MainWindow_0();
		win_0.MainClass = instance;
		WindowList.add(win_0);
		MainWindow_1 win_1 = new MainWindow_1();
		win_1.MainClass = instance;
		WindowList.add(win_1);
		MainWindow_2 win_2 = new MainWindow_2();
		win_2.MainClass = instance;
		WindowList.add(win_2);
		MainWindow_3 win_3 = new MainWindow_3();
		win_3.MainClass = instance;
		WindowList.add(win_3);
		MainWindow_4 win_4 = new MainWindow_4();
		win_4.MainClass = instance;
		WindowList.add(win_4);
		MainWindow_5 win_5 = new MainWindow_5();
		win_5.MainClass = instance;
		WindowList.add(win_5);
	}
	
	public void EnterMenu(int i_index)
	{
		for(int i = 0 ; i < WindowList.size(); i++)
		{
			WindowList.get(i).setVisible(false);
		}
		WindowList.get(i_index).setVisible(true);
	}
}
