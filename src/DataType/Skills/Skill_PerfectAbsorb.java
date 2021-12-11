/**
 * 
 */
package DataType.Skills;

import java.util.List;

import DataType.BattleChar;
import DataType.CharSkill;

/**
 * 精華吸取
對單體目標吸收5%血量 並回復自身5%MP
獲得等級 20
消耗mp0
CD2回合
 * @author kevin2088
 *
 */
public class Skill_PerfectAbsorb extends CharSkill
{
	public Skill_PerfectAbsorb()
	{
		name = "Skill_PerfectAbsorb";
		ShowName = "精華吸取";
		Description = "對單體目標消耗5%血量，"
				+ "\n並回復自身5%MP。";
		LockLV = 100;
		CostHP = 0;
		CostMP = 0;
		CoolDown = 2;
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
				double _fleeRand = Math.random() * (RuntimeChar.Hit() + i_targets.get(0).Flee());
				if(_fleeRand <= RuntimeChar.Hit())
				{
					ShowMsg += "[" + RuntimeChar.Data.Name + "]　使用　[" + ShowName + "]　";
					int _dmg = (int)(i_targets.get(0).HP * 0.05);
					ShowMsg += "對　[" + i_targets.get(0).Data.Name + "]　造成　" + _dmg + "　傷害\n";
					i_targets.get(0).HP -= _dmg;
					ShowMsg += "\n[" + RuntimeChar.Data.Name + "]" + "　回復　" + (int)(i_targets.get(0).MMP() * 0.05) + "　魔力";
					RuntimeChar.RegenHP((int)(i_targets.get(0).MMP() * 0.05));
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
