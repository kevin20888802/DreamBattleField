/**
 * 
 */
package DataType.Skills;

import java.util.List;

import DataType.BattleChar;
import DataType.CharSkill;

/**
 * 海浪擊打
對前方單體連擊3次 並回復自身100HP （可修改回復量）
獲得等級 60
消耗40MP
CD2回合
 * @author kevin2088
 *
 */
public class Skill_WaveAttack extends CharSkill
{
	public Skill_WaveAttack()
	{
		name = "Skill_WaveAttack";
		ShowName = "海浪擊打";
		Description = "對前方單體連擊3次攻擊力1.0倍的物理傷害，"
				+ "\n並回復自身100HP。";
		LockLV = 60;
		CostHP = 0;
		CostMP = 40;
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
			for(int i = 0 ; i < 3; i++)
			{
				double _fleeRand = Math.random() * (RuntimeChar.Hit() + i_targets.get(0).Flee());
				if(_fleeRand <= RuntimeChar.Hit())
				{
					ShowMsg += "[" + RuntimeChar.Data.Name + "]　使用　[" + ShowName + "]　";
					int _dmg = Math.max((RuntimeChar.ATK() * 1) - i_targets.get(0).DEF(),0);
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
			ShowMsg += "[" + RuntimeChar.Data.Name + "]" + "　回復　" + 100 + "　生命\n";
			RuntimeChar.RegenHP(100);
			System.out.println(RuntimeData.BS == null);
			RuntimeData.BS.ShowMsg(ShowMsg);
		}
	}
}
