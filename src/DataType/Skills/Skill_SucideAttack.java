/**
 * 
 */
package DataType.Skills;

import java.util.List;

import DataType.BattleChar;
import DataType.CharSkill;

/**
 * 捨身撞擊
對前方單體造成5%生命值傷害
獲得等級 20
消耗HP10%
CD4回合
 * @author kevin2088
 *
 */
public class Skill_SucideAttack extends CharSkill
{
	public Skill_SucideAttack()
	{
		name = "Skill_SucideAttack";
		ShowName = "捨身撞擊";
		Description = "對前方單體造成5%生命值物理傷害。";
		LockLV = 20;
		CostHP = 4000;
		CostMP = 0;
		CoolDown = 4;
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
			for(int i = 0 ; i < 2; i++)
			{
				double _fleeRand = Math.random() * (RuntimeChar.Hit() + i_targets.get(0).Flee());
				if(_fleeRand <= RuntimeChar.Hit())
				{
					ShowMsg += "[" + RuntimeChar.Data.Name + "]　使用　[" + ShowName + "]　";
					int _dmg = Math.max((int)(i_targets.get(0).HP * 0.05 * 1) - i_targets.get(0).DEF(),0);
					if(RuntimeChar.CRIResult() == true)
					{
						_dmg *= 2;
						ShowMsg += "";
					}
					ShowMsg += "對　[" + i_targets.get(0).Data.Name + "]　造成　" + _dmg + "　傷害\n";
					i_targets.get(0).HP -= _dmg;
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
