/**
 * 
 */
package DataType.Skills;

import java.util.List;

import DataType.BattleChar;
import DataType.CharSkill;

/**
 * 精準射擊
對前方單體造成傷害 必定命中
獲得等級 10
消耗MP 5
CD 1回合
 * @author kevin2088
 *
 */
public class Skill_PerfectShot extends CharSkill
{
	public Skill_PerfectShot()
	{
		name = "Skill_PerfectShot";
		ShowName = "精準射擊";
		Description = "對前方1個敵人造成攻擊力2.0倍的物理傷害"
				+ "\n，必定命中。";
		LockLV = 10;
		CostHP = 0;
		CostMP = 100;
		CoolDown = 5;
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
					int _dmg = Math.max((RuntimeChar.ATK() * 2) - i_targets.get(0).DEF(),0);
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
