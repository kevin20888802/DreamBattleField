package MainSystem;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import DataType.CharData;

/**
 * 備註
 * 
 * Icon :
 * 類型 png
 * 尺寸 768x768 
 * 
 */

/**
 * 資料庫系統
 * 
 * @author kevin2088 
 */
public class DataSystem 
{
	
	// 輔助工具
	static XmlHelper XmlHelper;
	static StringHelper StringHelper;
	
	// 資料庫
	// 資料庫 - 執行時物件
	public DefaultTableModel DT;
	// 資料庫 - 檔案
	Document TheDataBase;
	String DBPath = "DB.xml";
	
	// 資料庫 - 欄位
	String[] DataCols = {"ID","Name","LV","EXP","STR","AGI","VIT","INT","DEX","LUK","Class","Icon"};
	
	// 是否生成預設資料
	boolean GenerateDefault = true;
	
	/**
	 * 資料系統啟動程序
	 */
	public void StartUp()
	{
		XmlHelper = new XmlHelper();
		StringHelper = new StringHelper();
		ResetDT();
		
		// 啟動檢查有無檔案
		// 有檔案就讀取
		if(Files.exists(Paths.get(DBPath)))
		{
			LoadDB();
		}
		else
		{
			SaveDB();
		}
	}
	
	// 運行相關
	public CharData GetCharData(int i_id)
	{
		for(int i = 0 ; i < DT.getRowCount(); i++)
		{
			if(Integer.valueOf(DT.getValueAt(i, 0).toString()) == i_id)
			{
				System.out.println("已讀取角色資料 : " + DT.getValueAt(i, 1).toString());
				CharData _tmp = new CharData();
				_tmp.ID = Integer.valueOf(DT.getValueAt(i, 0).toString());
				_tmp.Name = DT.getValueAt(i, 1).toString();
				_tmp.LV = Integer.valueOf(DT.getValueAt(i, 2).toString());
				_tmp.EXP = Integer.valueOf(DT.getValueAt(i, 3).toString());
				_tmp.STR = Integer.valueOf(DT.getValueAt(i, 4).toString());
				_tmp.AGI = Integer.valueOf(DT.getValueAt(i, 5).toString());
				_tmp.VIT = Integer.valueOf(DT.getValueAt(i, 6).toString());
				_tmp.INT = Integer.valueOf(DT.getValueAt(i, 7).toString());
				_tmp.DEX = Integer.valueOf(DT.getValueAt(i, 8).toString());
				_tmp.LUK = Integer.valueOf(DT.getValueAt(i, 9).toString());
				_tmp.ClassNum = Integer.valueOf(DT.getValueAt(i, 10).toString());
				ClassLoader classLoader = getClass().getClassLoader();
				File ImgFile = new File(DT.getValueAt(i, 11).toString());
				System.out.println(System.getProperty("user.dir"));
				File LocalImgFile = new File(System.getProperty("user.dir") + "\\img\\icon\\" + DT.getValueAt(i, 11).toString());
				System.out.println(LocalImgFile.getAbsolutePath());
				if(classLoader.getResource("img/icon/" + DT.getValueAt(i, 11).toString()) != null)
				{
					File file = new File(classLoader.getResource("img/icon/" + DT.getValueAt(i, 11).toString()).getFile());
					_tmp.Img = new ImageIcon(file.getAbsolutePath());
				}
				if(LocalImgFile.exists())
				{
					_tmp.Img = new ImageIcon(LocalImgFile.getAbsolutePath());
				}
				else if(ImgFile.exists())
				{
					_tmp.Img = new ImageIcon(ImgFile.getAbsolutePath());
				}
				return _tmp;
			}
		}
		return null;
	}
	public int GetCharCount()
	{
		return DT.getRowCount();
	}
	
	// 儲存讀取相關
	
	/**
	 * 新增一筆資料。
	 */
	public void AddData()
	{
		if(DT != null)
		{
			int _currMaxID = 0;
			for(int i = 0 ; i < DT.getRowCount(); i++)
			{
				if(Integer.valueOf(DT.getValueAt(i, 0).toString()) >= _currMaxID)
				{
					_currMaxID = Integer.valueOf(DT.getValueAt(i, 0).toString()) + 1;
				}
			}
			DT.addRow(new Object[] {_currMaxID , "名稱" ,"1","0","0","0","0","0","0","0","0",""});
		}
	}
	/**
	 * 刪除指定行的資料。
	 * @param i_index 第幾行
	 */
	public void DeleteData(int i_index)
	{
		if(DT != null && (i_index < DT.getRowCount() & i_index >= 0))
		{
			DT.removeRow(i_index);
		}
	}	
	/**
	 * 重置資料庫執行物件。
	 */
	public void ResetDT()
	{
		DT = new DefaultTableModel();
		for(int i = 0 ; i < DataCols.length; i++)
		{
			DT.addColumn(DataCols[i]);
		}
	}
	/**
	 * 資料庫檔案儲存。
	 */
	public void SaveDB()
	{
		try 
		{
			CheckFixDB();
			if(Files.exists(Paths.get(DBPath)))
			{
				Files.delete(Paths.get(DBPath));
			}
			Files.createFile(Paths.get(DBPath));
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            TheDataBase = documentBuilder.newDocument();
 
            // root element
            Element rootNode = TheDataBase.createElement("Dataroot");
            TheDataBase.appendChild(rootNode);
            for(int i = 0 ; i < DT.getRowCount(); i++)
            {
	            Element _currData = TheDataBase.createElement("Data_" + i);
	            // ID
	            _currData.setAttributeNode(XmlHelper.XmlNodeAttr(TheDataBase, "item_id", DT.getValueAt(i, 0).toString()));
	            // Name
	            _currData.setAttributeNode(XmlHelper.XmlNodeAttr(TheDataBase, "item_Name", DT.getValueAt(i, 1).toString()));
	            // LV
	            _currData.setAttributeNode(XmlHelper.XmlNodeAttr(TheDataBase, "LV", DT.getValueAt(i, 2).toString()));
	            // EXP
	            _currData.setAttributeNode(XmlHelper.XmlNodeAttr(TheDataBase, "EXP", DT.getValueAt(i, 3).toString()));
	            // STR
	            _currData.setAttributeNode(XmlHelper.XmlNodeAttr(TheDataBase, "STR", DT.getValueAt(i, 4).toString()));
	            // AGI
	            _currData.setAttributeNode(XmlHelper.XmlNodeAttr(TheDataBase, "AGI", DT.getValueAt(i, 5).toString()));
	            // VIT
	            _currData.setAttributeNode(XmlHelper.XmlNodeAttr(TheDataBase, "VIT", DT.getValueAt(i, 6).toString()));
	            // INT
	            _currData.setAttributeNode(XmlHelper.XmlNodeAttr(TheDataBase, "INT", DT.getValueAt(i, 7).toString()));
	            // DEX
	            _currData.setAttributeNode(XmlHelper.XmlNodeAttr(TheDataBase, "DEX", DT.getValueAt(i, 8).toString()));
	            // LUK
	            _currData.setAttributeNode(XmlHelper.XmlNodeAttr(TheDataBase, "LUK", DT.getValueAt(i, 9).toString()));
	            // class
	            _currData.setAttributeNode(XmlHelper.XmlNodeAttr(TheDataBase, "class", DT.getValueAt(i, 10).toString()));
	            // icon
	            _currData.setAttributeNode(XmlHelper.XmlNodeAttr(TheDataBase, "item_img", DT.getValueAt(i, 11).toString()));
	            
	            rootNode.appendChild(_currData);
            }
            
            // 轉變格式後寫入檔案
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            try 
            {
            	FileOutputStream FOS = new FileOutputStream(DBPath);
				tr.transform(new DOMSource(TheDataBase), new StreamResult(FOS));
				FOS.close();
            } 
            catch (TransformerException e) 
            {
				e.printStackTrace();
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 讀取資料庫檔案。
	 */
	public void LoadDB()
	{
		ResetDT();
		TheDataBase = XmlHelper.LoadXml(DBPath);
		if(TheDataBase.hasChildNodes())
		{
			NodeList _AllDatas;
			_AllDatas = TheDataBase.getChildNodes().item(0).getChildNodes();
			for(int i = 0 ; i < _AllDatas.getLength(); i++)
			{
				Node _thisData = _AllDatas.item(i);
				if(_thisData.getNodeName().startsWith("Data_"))
				{					
					NamedNodeMap _allAttrs = _thisData.getAttributes();
					String curr_id = XmlHelper.LoadXmlAttibute(_allAttrs,"item_id","0");
					String curr_name = XmlHelper.LoadXmlAttibute(_allAttrs,"item_Name","");
					String curr_lv = XmlHelper.LoadXmlAttibute(_allAttrs,"LV","0");
					String curr_exp = XmlHelper.LoadXmlAttibute(_allAttrs,"EXP","0");
					String curr_str = XmlHelper.LoadXmlAttibute(_allAttrs,"STR","0");
					String curr_agi = XmlHelper.LoadXmlAttibute(_allAttrs,"AGI","0");
					String curr_vit = XmlHelper.LoadXmlAttibute(_allAttrs,"VIT","0");
					String curr_int = XmlHelper.LoadXmlAttibute(_allAttrs,"INT","0");
					String curr_dex = XmlHelper.LoadXmlAttibute(_allAttrs,"DEX","0");
					String curr_luk = XmlHelper.LoadXmlAttibute(_allAttrs,"LUK","0");
					String curr_class = XmlHelper.LoadXmlAttibute(_allAttrs,"class","0");
					String curr_img = XmlHelper.LoadXmlAttibute(_allAttrs,"item_img","0");
					DT.addRow(new Object[] {curr_id,curr_name,curr_lv,curr_exp,curr_str,curr_agi,curr_vit,curr_int,curr_dex,curr_luk,curr_class,curr_img});
				}
			}
		}
		CheckFixDB();
	}
	/**
	 * 資料庫錯誤資料修正。
	 */
	public void CheckFixDB()
	{
		if(DT != null)
		{
			for(int j = 0 ; j < DT.getRowCount(); j++)
			{
				int[] IntOnly = {0,2,3,4,5,6,7,8};
				for(int k = 0 ; k < IntOnly.length; k++)
				{
					if(!StringHelper.IsInt(DT.getValueAt(j, IntOnly[k]).toString()))
					{
						DT.setValueAt(0, j, IntOnly[k]);
					}
				}
			}
		}
	}
}
