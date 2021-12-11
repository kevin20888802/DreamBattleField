/**
 * 
 */
package DataType;

import javax.swing.ImageIcon;

/**
 * 角色的固定資料。
 * 
 * @author kevin2088
 *
 */
public class CharData 
{
	public int ID;
	public String Name;
	public int LV;
	public int EXP;
	public int STR;
	public int AGI;
	public int VIT;
	public int INT;
	public int DEX;
	public int LUK;
	public int ClassNum;
	public ImageIcon Img;
	
	public CharData()
	{
		LVUpCheck();
		StatCheck();
	}
	
	public int LVUpExp()
	{
		return LV * 100;
	}
	
	public void LVUpCheck()
	{
		while(EXP >= LVUpExp())
		{
			LV += 1;
			EXP -= LVUpExp();
		}
	}
	public void StatCheck()
	{
		if(STR <= 0)
		{
			STR = 1;
		}
		if(AGI <= 0)
		{
			AGI = 1;
		}
		if(VIT <= 0)
		{
			VIT = 1;
		}
		if(INT <= 0)
		{
			INT = 1;
		}
		if(DEX <= 0)
		{
			DEX = 1;
		}
		if(LUK <= 0)
		{
			LUK = 1;
		}
	}
}
