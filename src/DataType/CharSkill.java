/**
 * 
 */
package DataType;

import java.util.List;

/**
 * @author kevin2088
 *
 */
public class CharSkill 
{
	public BattleSkill RuntimeData;
	public BattleChar RuntimeChar;
	
	public String name;
	public String ShowName;
	public String Description;
	
	public int LockLV = 1;
	
	public TargetType TargetType;
	public int TargetCount = 1;
	
	public int CostHP = 0;
	public int CostMP = 50;
	public int CoolDown = 7;
	
	public void Use(List<BattleChar> i_targets)
	{
		if(RuntimeChar != null)
		{
			RuntimeChar.HP -= CostHP;
			RuntimeChar.MP -= CostMP;
		}
	}

}

