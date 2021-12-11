/**
 * 
 */
package DataType;
import java.util.ArrayList;
import java.util.List;

import MainSystem.*;
/**
 * 戰鬥角色的運行物件。
 * 
 * @author kevin2088
 *
 */
public class BattleChar 
{
	public BattleSystem BS;
	public RuntimeDataManager RDM;
	public CharData Data;
	public CharClass ClassData()
	{
		if(RDM != null)
		{
			return RDM.GetClass(Data.ClassNum);
		}
		else
		{
			return null;
		}
	}
	public List<BattleSkill> Skills = new ArrayList<BattleSkill>(); 
	
	public int HP = 1;
	public int MP = 1;
	
	public int MHP()
	{
		return (int)(ClassData().BaseHP + ((Data.VIT * Data.LV * ClassData().VITRate) * 0.8) + ((Data.STR * Data.LV * ClassData().STRRate) * 0.1) + ((Data.DEX * Data.LV * ClassData().DEXRate) * 0.1));
	}
	public int MMP()
	{
		return (int)(ClassData().BaseMP + ((Data.INT * Data.LV * ClassData().INTRate) * 0.8) + ((Data.VIT * Data.LV * ClassData().VITRate) * 0.2)) ;
	}
	public int ATK()
	{
		return (int)(ClassData().BaseATK + ((Data.STR * Data.LV * ClassData().STRRate) * 0.75) + ((Data.DEX * Data.LV * ClassData().DEXRate) * 0.2) + ((Data.LUK * Data.LV * ClassData().LUKRate) * 0.05));
	}
	public int MAT()
	{
		return (int)(ClassData().BaseMAT + ((Data.INT * Data.LV * ClassData().INTRate) * 0.95) + ((Data.LUK * Data.LV * ClassData().LUKRate) * 0.05));
	}
	public int DEF()
	{
		return (int)(ClassData().BaseDEF + ((Data.VIT * Data.LV * ClassData().VITRate) * 0.7) + ((Data.DEX * Data.LV * ClassData().DEXRate) * 0.3));
	}
	public int MDF()
	{
		return (int)(ClassData().BaseMDF + ((Data.INT * Data.LV * ClassData().INTRate) * 0.7) + ((Data.VIT * Data.LV * ClassData().VITRate) * 0.3));
	}
	public double CRIChance()
	{
		return 1 - (1 / (Data.LUK * 0.4));
	}
	public boolean CRIResult()
	{
		return Math.random() <= CRIChance();
	}
	public double Flee()
	{
		return (Data.AGI * Data.LV * ClassData().AGIRate) * 0.7 + (Data.LUK * Data.LV * ClassData().LUKRate) * 0.3;
	}
	public double Hit()
	{
		return (Data.DEX * Data.LV * ClassData().DEXRate) * 0.95 + (Data.LUK * Data.LV * ClassData().LUKRate) * 0.05;
	}
	public double CDReduce()
	{
		double _tmp = (Data.DEX * Data.LV * ClassData().DEXRate);
		if(_tmp <= 0.1)
		{
			_tmp = 0.1;
		}
		_tmp = _tmp / 102400.0;
		if(_tmp >= 0.99)
		{
			_tmp = 0.99;
		}
		return 1 - _tmp;
	}
	
	public boolean Dead()
	{
		return (HP <= 0);
	}
	
	public BattleChar(CharData i_data , RuntimeDataManager i_rdm)
	{
		Data = i_data;
		Data.LVUpCheck();
		RDM = i_rdm;
		FullRegen();
	}
	
	public void FullRegen()
	{
		HP = MHP();
		MP = MMP();
	}
	
	public void RegenHP(int i_amount)
	{
		HP = HP + i_amount;
		if(HP > MHP())
		{
			HP = MHP();
		}
	}
	
	public void RegenMP(int i_amount)
	{
		MP = MP + i_amount;
		if(MP > MMP())
		{
			MP = MMP();
		}
	}
	
	public int CanUseSkill(BattleSkill i_skill)
	{
		int _tmp = 0;
		if(MP < i_skill.Data.CostMP)
		{
			_tmp = 1;
		}
		else if(HP < i_skill.Data.CostHP)
		{
			_tmp = 2;
		}
		else if(i_skill.CurrCD != 0)
		{
			_tmp = 3;
		}
		return _tmp;
	}
	
	public void RefreshSkills()
	{
		Skills = new ArrayList<BattleSkill>();
		for(int i = 0; i < ClassData().Skills.size(); i++)
		{
			if(Data.LV >= ClassData().Skills.get(i).LockLV)
			{
				Skills.add(new BattleSkill(ClassData().Skills.get(i) , this, BS));
			}
		}
	}
	
	public BattleChar Clone() 
	{
		BattleChar _tmp = new BattleChar(Data , RDM);
		return _tmp;
	}
}
