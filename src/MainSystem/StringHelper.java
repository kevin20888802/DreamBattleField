package MainSystem;
/**
 * 
 */

/**
 * 字串輔助工具
 * @author kevin2088
 *
 */
public class StringHelper 
{
	public boolean IsInt(String i_s)
	{
		for(int i = 0; i < i_s.length(); i++)
		{
			if(!Character.isDigit(i_s.charAt(i)))
			{
				return false;
			}
		}
		if(i_s.length() > 0)
		{
			return true;
		}
		return false;
	}
}
