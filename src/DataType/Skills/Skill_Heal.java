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
public class Skill_Heal extends CharSkill
{
	public Skill_Heal()
	{
		name = "Skill_Heal";
		ShowName = "治癒術";
		Description = "治癒目標，回復魔法攻擊力1.0倍的生命。";
		LockLV = 1;
		CostHP = 0;
		CostMP = 50;
		CoolDown = 7;
		TargetType = TargetType.Team;
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
				ShowMsg += "[" + RuntimeChar.Data.Name + "]　使用　[" + ShowName + "]　";
				int _dmg = (RuntimeChar.MAT() * 1);
				ShowMsg += "對　[" + i_targets.get(0).Data.Name + "]　回復　" + _dmg + "　生命\n";
				i_targets.get(0).RegenHP(_dmg);
			}
			RuntimeData.BS.ShowMsg(ShowMsg);
		}
	}
}
