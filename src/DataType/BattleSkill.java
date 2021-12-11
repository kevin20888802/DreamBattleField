/**
 * 
 */
package DataType;

import java.util.List;

import MainSystem.BattleSystem;

/**
 * @author kevin2088
 *
 */
public class BattleSkill 
{
	public BattleSystem BS;
	public CharSkill Data;
	public BattleChar CharData;
	public int MaxCD()
	{
		int _baseCD = Data.CoolDown;
		if(CharData != null)
		{
			_baseCD *= CharData.CDReduce();
		}
		return _baseCD;
	}
	public int CurrCD = 0;
	
	public BattleSkill(CharSkill i_data , BattleChar i_user , BattleSystem i_bs)
	{
		Data = i_data;
		BS = i_bs;
		Data.RuntimeData = this;
		CharData = i_user;
		Data.RuntimeChar = CharData;
	}
	
	public void Use(List<BattleChar> i_targets)
	{
		Data.RuntimeData = this;
		Data.RuntimeChar = CharData;
		CurrCD = MaxCD();
		Data.Use(i_targets);
	}
	
	public BattleSkill Clone()
	{
		return new BattleSkill(this.Data,this.Data.RuntimeChar,this.BS);
	}
}
