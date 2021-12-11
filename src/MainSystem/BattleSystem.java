/**
 * 
 */
package MainSystem;

import java.util.ArrayList;
import java.util.List;

import DataType.*;
import DataType.Skills.*;
import WindowSystem.MainWindow_5;

/**
 * @author kevin2088
 *
 */
public class BattleSystem 
{

	public MainWindow_5 BattleWindow;
	public MainClass MainClass;
	
	public int ActionTeam = 0;
	
	public BattleChar _player;
	public List<String> _targets = new ArrayList<String>();
	public List<BattleChar> _targetObj()
	{
		List<BattleChar> _tmp = new ArrayList<BattleChar>();
		switch(NowSkill.Data.TargetType)
		{
			case Enemy:
				for(int i = 0 ; i < _targets.size(); i++)
				{
					_tmp.add(_Enemy.get(Integer.valueOf(_targets.get(i))));
				}
				break;
			case Team:
				/*for(int i = 0 ; i < _targets.size(); i++)
				{
					_tmp.add(_Team.get(Integer.valueOf(_targets.get(i))));
				}*/
				_tmp.add(_player);
				break;
			case Self:
				_tmp.add(_player);
				break;
		}
		return _tmp;
	}
	public List<BattleChar> _Enemy = new ArrayList<BattleChar>();
	
	public BattleSkill NowSkill;
	public int MaxEnemys = 5;
	public String ConsoleScreen = "";
	
	public class ConsoleMenu
	{
		public String name;
		public String[] MenuContents;
		public boolean canSelect = true;
		
		public ConsoleMenu(String i_name , boolean i_se)
		{
			name = i_name;
			canSelect = i_se;
		}
	}
	
	public List<ConsoleMenu> AllMenus = new ArrayList<ConsoleMenu>();
	public ConsoleMenu NowMenu;
	
	public int MenuSelect = 0;
	
	public BattleSystem(MainWindow_5 i_win , MainClass i_main)
	{
		BattleWindow = i_win;
		MainClass = i_main;
	}
	
	public void StartUp()
	{
		SetTeams();
		SetupMenus();
		String _appearText = "";
		for(int i = 0 ; i < _Enemy.size(); i++)
		{
			_appearText += _Enemy.get(i).Data.Name + "　出現了！\n";
		}
		ShowMsg(_appearText);
	}
	
	public void SetTeams()
	{
		_player = MainClass.CurrChar;
		_player.BS = this;
		_player.FullRegen();
		_player.RefreshSkills();
		_Enemy = new ArrayList<BattleChar>();
		int _randCount = (int)(Math.random() * MaxEnemys + 1);
		for(int i = 0 ; i < _randCount ; i++)
		{
			BattleChar _thisEnemy = MainClass.GetRandChar();
			_thisEnemy.BS = this;
			_thisEnemy.FullRegen();
			_thisEnemy.RefreshSkills();
			_Enemy.add(_thisEnemy);
		}
		
	}
	
	public void SetupMenus()
	{
		AllMenus = new ArrayList<ConsoleMenu>();
		String[] _menuCont_PMenu = {"攻擊" , "技能" , "狀態" , "敵人狀態" , "逃跑"};
		ConsoleMenu PMenu = new ConsoleMenu("PlayerAction" , true);
		PMenu.MenuContents = _menuCont_PMenu.clone();
		AllMenus.add(PMenu);

		ConsoleMenu SkillMenu = new ConsoleMenu("Skill" , true);
		String[] _menuCont_skill = {""};		
		SkillMenu.MenuContents = _menuCont_skill.clone();
		AllMenus.add(SkillMenu);
		
		ConsoleMenu TargetMenu = new ConsoleMenu("Target" , true);
		String[] _menuCont_target = {""};		
		TargetMenu.MenuContents = _menuCont_target.clone();
		AllMenus.add(TargetMenu);
		
		ConsoleMenu MsgMenu = new ConsoleMenu("Msg" , false);
		String[] _menuCont_msg = {""};		
		MsgMenu.MenuContents = _menuCont_msg.clone();
		AllMenus.add(MsgMenu);
		
		ConsoleMenu ResultMenu = new ConsoleMenu("Result" , false);
		String[] _menuCont_result = {""};		
		ResultMenu.MenuContents = _menuCont_result.clone();
		AllMenus.add(ResultMenu);
	}
	
	public void ShowMenu()
	{
		ConsoleScreen = "";
		for(int i = 0; i < NowMenu.MenuContents.length; i++)
		{
			ConsoleScreen += NowMenu.MenuContents[i];
			if(NowMenu.canSelect && i == MenuSelect)
			{
				ConsoleScreen += "　　　＜————";
			}
			ConsoleScreen += "\n";
		}
		BattleWindow.RefreshScreen();
	}
	
	public void EnterMenu(String i_name)
	{
		MenuSelect = 0;
		for(int i = 0 ; i < AllMenus.size() ; i++)
		{
			if(AllMenus.get(i).name == i_name)
			{
				NowMenu = AllMenus.get(i);
				ShowMenu();
			}
		}
	}
	public void SetMenuText(String i_name , String[] i_txt)
	{
		for(int i = 0 ; i < AllMenus.size() ; i++)
		{
			if(AllMenus.get(i).name == i_name)
			{
				AllMenus.get(i).MenuContents = i_txt;
			}
		}
	}
	public void MenuSwitch(boolean i_next)
	{
		int _addTmp = 1;
		if(!i_next)
		{
			_addTmp = -1;
		}
		MenuSelect += _addTmp;
		if(MenuSelect >= NowMenu.MenuContents.length)
		{
			MenuSelect = 0;
		}
		if(MenuSelect < 0)
		{
			MenuSelect = NowMenu.MenuContents.length - 1;
		}
		ShowMenu();
	}
	public void MenuEnter()
	{
		if(NowMenu != null)
		{
			switch(NowMenu.name)
			{
				case "PlayerAction":
					switch(MenuSelect)
					{
						case 0: // 攻擊
							NowSkill = new BattleSkill(new Skill_NormalAttack(), _player , this);
							_targets = new ArrayList<String>();
							SelectTargets();
							break;
						case 1: // 技能
							RefreshSkillMenu();
							EnterMenu("Skill");
							break;
						case 2: // 狀態
							String _TeamStatusShow = "";
							_TeamStatusShow += "["  + _player.Data.Name + "]　—　生命：" + _player.HP + "/" + _player.MHP() + "　　魔力：" + _player.MP + "/" + _player.MMP() + "（LV." + _player.Data.LV + "）";
							ShowMsg(_TeamStatusShow);
							break;
						case 3: // 敵人狀態
							String enemy_list = "";
							for(int i = 0 ; i < _Enemy.size(); i++)
							{
								enemy_list += "[" +  _Enemy.get(i).Data.Name + "]　—　生命：" + _Enemy.get(i).HP + "/" + _Enemy.get(i).MHP() + "（LV." + _Enemy.get(i).Data.LV + "）";
								if(_Enemy.get(i).Dead())
								{
									enemy_list += "　—　死亡";
								}
								for(int j = 0 ; j < _targets.size(); j++)
								{
									if(_targets.get(j).equals(String.valueOf(i)))
									{
										enemy_list += "　—　已選擇";
									}
								}
								if(i < _Enemy.size() - 1)
								{
									enemy_list += "\n";
								}
							}
							ShowMsg(enemy_list);
							break;
						case 4: // 逃跑
							double runChance = Math.random();
							double _EnemyTotalHit = 0;
							for(int i = 0 ; i < _Enemy.size(); i++)
							{
								if(!_Enemy.get(i).Dead())
								{
									_EnemyTotalHit += _Enemy.get(i).Hit();
								}
							}
							runChance *= (_player.Flee() + _EnemyTotalHit);
							if(runChance <= _player.Flee())
							{
								String[] _resultText = new String[2];
								_resultText[0] = "逃跑成功！";
								_resultText[1] = "[" + _player.Data.Name + "]　獲得勝利！";
								SetMenuText("Result", _resultText);
								EnterMenu("Result");
							}
							else
							{
								ShowMsg("逃跑失敗！");
								EnemyTurn();
							}
							break;
					}
					break;
				case "Skill":
					int _skillStatus = _player.CanUseSkill(_player.Skills.get(MenuSelect));
					if(_skillStatus == 0)
					{
						_targets.clear();
						NowSkill = _player.Skills.get(MenuSelect);
						SelectTargets();
					}
					else if(_skillStatus == 1)
					{
						ShowMsg("魔力不足！");
					}
					else if(_skillStatus == 2)
					{
						ShowMsg("生命不足！");
					}
					else if(_skillStatus == 3)
					{
						ShowMsg("技能冷卻中！");
					}
					break;
				case "Target":
					boolean _canSelect = true;
					for(int i = 0 ; i < _targets.size(); i++)
					{
						if(_targets.get(i).equals(String.valueOf(MenuSelect)))
						{
							_canSelect = false;
						}
					}
					switch(NowSkill.Data.TargetType)
					{
						case Enemy:
							if(_Enemy.get(MenuSelect).Dead())
							{
								_canSelect = false;
							}
						break;
						case Team:
							/*if(_Team.get(MenuSelect).Dead())
							{
								_canSelect = false;
							}*/
							if(_player.Dead())
							{
								_canSelect = false;
							}
						break;
						case Self:
							if(_player.Dead())
							{
								_canSelect = false;
							}
						break;
					}

					if(_canSelect)
					{
						_targets.add(String.valueOf(MenuSelect));
					}
					int DeadCount = 0;
					for(int i = 0 ; i < _Enemy.size(); i++)
					{
						if(_Enemy.get(i).Dead())
						{
							DeadCount += 1;
						}
					}
					if(_targets.size() == NowSkill.Data.TargetCount | _targets.size() == (_Enemy.size() - DeadCount))
					{
						ShowMsg("...");
						System.out.println(NowSkill.CharData.Data.Name);
						NowSkill.Use(_targetObj());
						ActionTeam = 1;
					}
					else
					{
						SelectTargets();
					}
					break;
				case "Appear":
					EnterMenu("PlayerAction");
					break;
				case "Msg":
					if(!ResultCheck())
					{
						if(ActionTeam == 0)
						{
							EnterMenu("PlayerAction");
						}
						else
						{
							EnemyTurn();
						}
					}
					break;
				case "Result":
					MainClass.EnterMenu(2);
					break;
			}
		}
	}
	
	public void BackMenu()
	{
		switch(NowMenu.name)
		{
			case "Target":
				if(_targets.size() <= 0)
				{
					EnterMenu("PlayerAction");
				}
				else
				{
					_targets.remove(_targets.size() - 1);
					SelectTargets();
				}
				break;
			case "Skill":
				EnterMenu("PlayerAction");
				break;
		}
	}
	
	public boolean ResultCheck()
	{
		boolean Win = true;
		boolean Lose = (_player.Dead());
		
		for(int i = 0; i < _Enemy.size(); i++)
		{
			if(!_Enemy.get(i).Dead())
			{
				Win = false;
			}
		}
		
		String[] _resultText = new String[1];
		if(Win)
		{
			_resultText[0] = "[" + _player.Data.Name + "]　獲得勝利！";
			SetMenuText("Result", _resultText);
			EnterMenu("Result");
			return true;
		}
		if(Lose)
		{
			_resultText[0] = "戰鬥失敗！";
			SetMenuText("Result", _resultText);
			EnterMenu("Result");
			return true;
		}
		return false;
	}
	
	public void SelectTargets()
	{
		String[] TextCont = new String[0];
		switch(NowSkill.Data.TargetType)
		{
			case Enemy:
				String enemy_list = "";
				for(int i = 0 ; i < _Enemy.size(); i++)
				{
					enemy_list += "[" +  _Enemy.get(i).Data.Name + "]　—　生命：" + _Enemy.get(i).HP + "/" + _Enemy.get(i).MHP() + "（LV." + _Enemy.get(i).Data.LV + "）";
					if(_Enemy.get(i).Dead())
					{
						enemy_list += "　—　死亡";
					}
					for(int j = 0 ; j < _targets.size(); j++)
					{
						if(_targets.get(j).equals(String.valueOf(i)))
						{
							enemy_list += "　—　已選擇";
						}
					}
					if(i < _Enemy.size() - 1)
					{
						enemy_list += "\n";
					}
				}
				TextCont = enemy_list.split("\n");
				break;
			case Team:
				String Team_list = "";
				//for(int i = 0 ; i < _Team.size(); i++)
				//{
					//enemy_list += "[" +  _Enemy.get(i).Data.Name + "]　—　HP:" + _Enemy.get(i).HP + "/" + _Enemy.get(i).MHP() + "（LV." + _Enemy.get(i).Data.LV + "）";
					Team_list += "[" +  _player.Data.Name + "]　—　HP:" + _player.HP + "/" + _player.MHP() + "（LV." + _player.Data.LV + "）";
					if(_player.Dead())
					{
						Team_list += "　—　死亡";
					}
					/*for(int j = 0 ; j < _targets.size(); j++)
					{
						if(_targets.get(j).equals(String.valueOf(i)))
						{
							enemy_list += "　—　已選擇";
						}
					}*/
					/*if(i < _Enemy.size() - 1)
					{
						enemy_list += "\n";
					}*/
				//}
				TextCont = Team_list.split("\n");
				break;
			case Self:
				String Self_list = "";
				Self_list += "[" +  _player.Data.Name + "]　—　HP:" + _player.HP + "/" + _player.MHP() + "（LV." + _player.Data.LV + "）";
				if(_player.Dead())
				{
					Self_list += "　—　死亡";
				}
				TextCont = Self_list.split("\n");
				break;
		}
		SetMenuText("Target" , TextCont);
		EnterMenu("Target");
	}
	
	int EnemyTurnIndex = 0;
	public void EnemyTurn()
	{
			if(EnemyTurnIndex < _Enemy.size() && !_Enemy.get(EnemyTurnIndex).Dead())
			{
				BattleSkill _theSkill = new BattleSkill(new Skill_NormalAttack(), _Enemy.get(EnemyTurnIndex) , this);
				int _randIndex = (int)(Math.random() * (_Enemy.get(EnemyTurnIndex).Skills.size() - 1 - (-1)) + -1);
				if(_randIndex != -1)
				{
					BattleSkill _selectedSkill = _Enemy.get(EnemyTurnIndex).Skills.get(_randIndex);
					if(_Enemy.get(EnemyTurnIndex).CanUseSkill(_selectedSkill) == 0)
					{
						_theSkill = _selectedSkill;
					}
				}
				List<BattleChar> _theTargets = new ArrayList<BattleChar>();
				switch(_theSkill.Data.TargetType)
				{
					case Enemy:
						_theTargets.add(_player);
					break;
					case Team:
						boolean[] _added = new boolean[5];
						for(int i = 0 ; i < 5; i++) 
						{
							int _rand = 0;
							do
							{
								_rand = (int) Math.floor(Math.random() * 5.0);
							}while(_added[_rand] == true);
							if(_rand < _Enemy.size())
							{
								_theTargets.add(_Enemy.get(_rand));		
							}
							_added[_rand] = true;
						}
					break;
					case Self:
						_theTargets.add(_Enemy.get(EnemyTurnIndex));
					break;
				}
				
				_theSkill.Use(_theTargets);
			}
			EnemyTurnIndex += 1;
			if(EnemyTurnIndex >= _Enemy.size())
			{
				ActionTeam = 0;
				EnemyTurnIndex = 0;
			}
			else
			{
				while(!(EnemyTurnIndex >= _Enemy.size()) && _Enemy.get(EnemyTurnIndex).Dead())
				{
					EnemyTurnIndex += 1;
				}
			}
	}
	
	public void SkillCDRun()
	{
		for(int i = 0; i < _player.Skills.size(); i++)
		{
			if(_player.Skills.get(i).CurrCD > 0)
			{
				_player.Skills.get(i).CurrCD -= 1;
			}
		}
		for(int j = 0 ; j < _Enemy.size(); j++)
		{
			for(int i = 0; i < _Enemy.get(j).Skills.size(); i++)
			{
				if(_Enemy.get(j).Skills.get(i).CurrCD > 0)
				{
					_Enemy.get(j).Skills.get(i).CurrCD -= 1;
				}
			}
		}
	}
	
	public void RefreshSkillMenu()
	{
		String skill_list = "";
		for(int i = 0; i < _player.Skills.size(); i++)
		{
			BattleSkill _theSkill = _player.Skills.get(i);
			skill_list += _theSkill.Data.ShowName + "　—　" + "消耗魔力：" + _theSkill.Data.CostMP;
			if(_theSkill.Data.CostHP > 0)
			{
				skill_list += "　消耗生命：" + _theSkill.Data.CostHP;
			}
			if(_theSkill.CurrCD > 0)
			{
				skill_list += "　※冷卻中（" + _theSkill.CurrCD + "/" + _theSkill.MaxCD() + "）";
			}
			skill_list += "\n";
		}
		String[] TextCont = skill_list.split("\n");
		SetMenuText("Skill" , TextCont);
	}
	
	public void ShowMsg(String i_text)
	{
		String[] TextCont = i_text.split("\n");
		SetMenuText("Msg" , TextCont);
		EnterMenu("Msg");
	}
}
