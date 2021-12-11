/**
 * 
 */
package DataType.Skills;

import java.util.List;

import DataType.BattleChar;
import DataType.CharSkill;

/**
 * @author kevin2088
 *
 */
public class Skill_NormalAttack extends CharSkill
{
	public Skill_NormalAttack()
	{
		name = "Skill_NormalAttack";
		ShowName = "普通攻擊";
		Description = "對目標攻擊1次。";
		LockLV = 1;
		CostHP = 0;
		CostMP = 50;
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
			RuntimeData.BS.ShowMsg(ShowMsg);
		}
	}
}
