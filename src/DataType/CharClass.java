/**
 * 
 */
package DataType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kevin2088
 *
 */
public class CharClass 
{
	
	public CharClass()
	{
		Skills = new ArrayList<CharSkill>();
	}
	
	public String name;
	public String ShowName;
	
	public void SetupName(String i_name , String i_show)
	{
		name = i_name;
		ShowName = i_show;
	}
	
	public double HPRate;
	public double MPRate;
	public double STRRate;
	public double AGIRate;
	public double VITRate;
	public double INTRate;
	public double DEXRate;
	public double LUKRate;
	
	public void SetupRate(double i_hp , double i_mp , double i_str , double i_agi , double i_vit, double i_int , double i_dex , double i_luk)
	{
		HPRate = i_hp; MPRate = i_mp; STRRate = i_str; AGIRate = i_agi; VITRate = i_vit; INTRate = i_int; DEXRate = i_dex; LUKRate = i_luk;
	}
	
	public int BaseHP;
	public int BaseMP;
	public int BaseATK;
	public int BaseMAT;
	public int BaseDEF;
	public int BaseMDF;
	public int BaseASP;
	public int BaseSPD;
	public int BaseFlee;
	
	public void SetupBase(int i_hp , int i_mp , int i_atk , int i_mat , int i_def, int i_mdf , int i_asp , int i_spd , int i_flee)
	{
		BaseHP = i_hp; BaseMP = i_mp; BaseATK = i_atk; BaseMAT = i_mat; BaseDEF = i_def; BaseMDF = i_mdf; BaseASP = i_asp; BaseSPD = i_spd; BaseFlee = i_flee;
	}

	public List<CharSkill> Skills;
}
