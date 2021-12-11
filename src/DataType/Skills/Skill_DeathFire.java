/**
 * 
 */
package DataType.Skills;

import java.util.List;

import DataType.BattleChar;
import DataType.CharSkill;

/**
 * 絕命火焰
對前方單體發射詛咒火焰一次
有5%機率使目標即死
獲得等級 100
消耗90MP
CD7回合
 * @author kevin2088
 *
 */
public class Skill_DeathFire extends CharSkill
{
	public Skill_DeathFire()
	{
		name = "Skill_DeathFire";
		ShowName = "絕命火焰";
		Description = "對前方單體發射詛咒火焰一次，"
				+ "\n造成魔法攻擊力2.5倍的魔法傷害，"
				+ "\n有5%機率使目標即死。";
		LockLV = 100;
		CostHP = 0;
		CostMP = 360;
		CoolDown = 7;
		TargetType = TargetType.Enemy;
		TargetCount = 1;
	}
	
	@Override
	public void Use(List<BattleChar> i_targets)
	{
		super.Use(i_targets);
		if(i_targets.size() > 0)
		{			
			String ShowMsg = "";
			for(int i = 0 ; i < 1; i++)
			{
				double _fleeRand = 0;
				if(_fleeRand <= RuntimeChar.Hit())
				{
					ShowMsg += "[" + RuntimeChar.Data.Name + "]　使用　[" + ShowName + "]　";
					int _dmg = Math.max((int)(RuntimeChar.MAT() * 2.5) - i_targets.get(0).MDF(),0);
					ShowMsg += "對　[" + i_targets.get(0).Data.Name + "]　造成　" + _dmg + "　傷害\n";
					i_targets.get(0).HP -= _dmg;
					if(Math.random() * 100 <= 5)
					{
						i_targets.get(0).HP = 0;
						ShowMsg += "即死！\n";
						ShowMsg += "[" + i_targets.get(0).Data.Name + "]" + "　的生命變為　0\n";
					}
				}
				else
				{	
					ShowMsg += "[" + RuntimeChar.Data.Name + "]　使用　[" + ShowName + "]　";
					ShowMsg += "對　[" + i_targets.get(0).Data.Name + "]　未命中！ \n";
				}

			}
			System.out.println(RuntimeData.BS == null);
			RuntimeData.BS.ShowMsg(ShowMsg);
		}
	}
}
