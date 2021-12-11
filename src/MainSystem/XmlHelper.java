package MainSystem;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.SAXException;

/**
 * 
 */

/**
 * XML輔助工具
 * @author kevin2088
 *
 */
public class XmlHelper 
{
	public Document LoadXml(String in_Path)
	{
		DocumentBuilderFactory DocFac = DocumentBuilderFactory.newInstance();
		DocumentBuilder DocBuild = null;
		try 
		{
			DocBuild = DocFac.newDocumentBuilder();
		} 
		catch (ParserConfigurationException e) 
		{
			e.printStackTrace();
		}
		File DataFile = new File("DB.xml");
		try 
		{
			if(DataFile.exists())
			{
				return DocBuild.parse(DataFile);
			}
		} 
		catch (SAXException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return null;
	}

    public static Element LoadXmlChild(Element input, String valName)
    {
        if (input.getElementsByTagName(valName).getLength() > 0 && input.getElementsByTagName(valName).item(0) != null)
        {
            return (Element)input.getElementsByTagName(valName).item(0);
        }
        else
        {
            return null;
        }
    }
    public static String LoadXmlChildValue(Element input, String valName, String nullval)
    {
        if (input.getElementsByTagName(valName).getLength() > 0 && input.getElementsByTagName(valName).item(0) != null)
        {
            return input.getElementsByTagName(valName).item(0).getTextContent();
        }
        else
        {
            return nullval;
        }
    }
    public static int LoadXmlChildValue(Element input, String valName, int nullval)
    {
        if (input.getElementsByTagName(valName).getLength() > 0 && input.getElementsByTagName(valName).item(0) != null)
        {
            return Integer.valueOf(input.getElementsByTagName(valName).item(0).getTextContent());
        }
        else
        {
            return nullval;
        }
    }
    public static float LoadXmlChildValue(Element input, String valName, float nullval)
    {
    	if (input.getElementsByTagName(valName).getLength() > 0 && input.getElementsByTagName(valName).item(0) != null)
        {
            return Float.valueOf((input.getElementsByTagName(valName).item(0).getTextContent()));
        }
        else
        {
            return nullval;
        }
    }
    public String LoadXmlAttibute(NamedNodeMap input, String valName, String nullval)
    {
        if (input.getNamedItem(valName) != null)
        {
            return input.getNamedItem(valName).getNodeValue();
        }
        else
        {
            return nullval;
        }
    }
    public Attr XmlNodeAttr(Document i_doc,String i_name ,String i_val) 
    {
        Attr _attr = i_doc.createAttribute(i_name);
        _attr.setValue(i_val);
        return _attr;
    }
    public static float LoadXmlAttibute(Element input, String valName, float nullval)
    {
        if(input == null)
        {
            return nullval;
        }
        if (input.getAttribute(valName) != null)
        {
            return Float.valueOf(input.getAttribute(valName));
        }
        else
        {
            return nullval;
        }
    }
    public static int LoadXmlAttibute(Element input, String valName, int nullval)
    {
        if (input.getAttribute(valName) != null)
        {
            return Integer.valueOf(input.getAttribute(valName));
        }
        else
        {
            return nullval;
        }
    }
	
}
