/**
 * 
 */
package DataType.Skills;

import java.util.List;

import DataType.BattleChar;
import DataType.CharSkill;

/**
 * 破壞死光
對前方5個敵人發射雷射光，造成魔法攻擊力4倍的魔法傷害。
獲得等級 120
消耗 100MP
CD8回合
 * @author kevin2088
 *
 */
public class Skill_BreakLaser extends CharSkill
{
	public Skill_BreakLaser()
	{
		name = "Skill_BreakLaser";
		ShowName = "破壞死光";
		Description = "對前方5個敵人發射雷射光，"
				+ "\n造成魔法攻擊力4倍的魔法傷害。";
		LockLV = 120;
		CostHP = 0;
		CostMP = 400;
		CoolDown = 8;
		TargetType = TargetType.Enemy;
		TargetCount = 5;
	}
	
	@Override
	public void Use(List<BattleChar> i_targets)
	{
		super.Use(i_targets);
		if(i_targets.size() > 0)
		{
			String ShowMsg = "";
			for(int i = 0 ; i < i_targets.size(); i++)
			{
				double _fleeRand = 0;
				if(_fleeRand <= RuntimeChar.Hit())
				{
					ShowMsg += "[" + RuntimeChar.Data.Name + "]　使用　[" + ShowName + "]　";
					int _dmg = Math.max((int)(RuntimeChar.MAT() * 4) - i_targets.get(0).MDF(),0);
					ShowMsg += "對　[" + i_targets.get(i).Data.Name + "]　造成　" + _dmg + "　傷害\n";
					i_targets.get(i).HP -= _dmg;
				}
				else
				{	
					ShowMsg += "[" + RuntimeChar.Data.Name + "]　使用　[" + ShowName + "]　";
					ShowMsg += "對　[" + i_targets.get(i).Data.Name + "]　未命中！ \n";
				}

			}
			//System.out.println(RuntimeData.BS == null);
			RuntimeData.BS.ShowMsg(ShowMsg);
		}
	}
}
