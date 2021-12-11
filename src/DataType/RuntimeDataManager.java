/**
 * 
 */
package DataType;

import java.util.ArrayList;
import java.util.List;

import DataType.Skills.*;
import MainSystem.DataSystem;

/**
 * @author kevin2088
 *
 */
public class RuntimeDataManager 
{
	public DataSystem DS;
	
	public List<BattleChar> Characters;
	public List<CharClass> Classes;
	public List<CharSkill> Skills;
	
	public void Setup()
	{
		SetupCharSkill();
		SetupCharClass();
		SetupChars();
	}
	private void SetupChars()
	{
		Characters = new ArrayList<BattleChar>();
		if(DS != null)
		{
			for(int i = 0 ; i < DS.GetCharCount(); i++)
			{
				BattleChar _tmp = new BattleChar(DS.GetCharData(i) , this);
				_tmp.RDM = this;
				Characters.add(_tmp);
			}
		}
	}	
	private void SetupCharClass()
	{
		Classes = new ArrayList<CharClass>();
		
		// Rune Knight
		CharClass class_RK = new CharClass();
		class_RK.SetupName("rune_knight", "符文騎士");
		class_RK.SetupBase(1150, 50, 150, 5, 45, 30, 140, 25, 0);
		class_RK.SetupRate(200, 5, 2.4, 2.2, 1.7, 1, 1.2, 1.5);
		class_RK.Skills.add(GetSkill("Skill_DoubleAttack"));
		class_RK.Skills.add(GetSkill("Skill_StarburstStream"));
		class_RK.Skills.add(GetSkill("Skill_WaveAttack"));
		class_RK.Skills.add(GetSkill("Skill_SucideAttack"));
		Classes.add(class_RK);
		
		//  Ranger
		CharClass class_RG = new CharClass();
		class_RG.SetupName("ranger", "遊俠");
		class_RG.SetupBase(700, 75, 250, 10, 20, 10, 180, 25, 0);
		class_RG.SetupRate(200, 5, 2.4, 2.2, 1.1, 1, 1.2, 1.5);
		class_RG.Skills.add(GetSkill("Skill_DoubleAttack"));
		class_RG.Skills.add(GetSkill("Skill_PerfectShot"));
		Classes.add(class_RG);
		
		//  magic
		CharClass class_MAG = new CharClass();
		class_MAG.SetupName("magic", "法師");
		class_MAG.SetupBase(700, 75, 20, 250, 20, 10, 180, 25, 0);
		class_MAG.SetupRate(200, 5, 1.2, 1.5, 1.2, 2.5, 1.7, 1.3);
		class_MAG.Skills.add(GetSkill("Skill_BreakLaser"));
		class_MAG.Skills.add(GetSkill("Skill_DeathFire"));
		class_MAG.Skills.add(GetSkill("Skill_Heal"));
		class_MAG.Skills.add(GetSkill("Skill_PerfectAbsorb"));
		Classes.add(class_MAG);
		
	}
	private void SetupCharSkill()
	{
		Skills = new ArrayList<CharSkill>();
		Skills.add(new Skill_BreakLaser());
		Skills.add(new Skill_DeathFire());
		Skills.add(new Skill_DoubleAttack());
		Skills.add(new Skill_Heal());
		Skills.add(new Skill_PerfectAbsorb());
		Skills.add(new Skill_PerfectShot());
		Skills.add(new Skill_StarburstStream());
		Skills.add(new Skill_SucideAttack());
		Skills.add(new Skill_WaveAttack());
	}
	public BattleChar GetChar(int i_index) 
	{
		if((i_index >= 0) & (i_index < Characters.size()))
		{
			System.out.println("選擇角色 : " + Characters.get(i_index).Data.Name);
			return Characters.get(i_index).Clone();
		}
		else
		{
			if(Characters.size() > 0)
			{
				System.out.println("選擇角色 : " + Characters.get(i_index).Data.Name);
				return Characters.get(0).Clone();
			}
			else
			{
				return null;
			}
		}
	}
	public int CharIndexByID(int i_id)
	{
		for(int i = 0 ; i < Characters.size(); i++)
		{
			if(Characters.get(i).Data.ID == i_id)
			{
				return i;
			}
		}
		return -1;
	}
	public CharClass GetClass(int i_index)
	{
		if((i_index >= 0) & (i_index < Classes.size()))
		{
			return Classes.get(i_index);
		}
		else
		{
			if(Classes.size() > 0)
			{
				return Classes.get(0);
			}
			else
			{
				return null;
			}
		}
	}
	public CharSkill GetSkill(String i_name)
	{
		for(int i = 0 ; i < Skills.size(); i++)
		{
			if(Skills.get(i).name == i_name)
			{
				return Skills.get(i);
			}
		}
		return null;
	}
	
}
