package com.INI;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.dtools.ini.BasicIniFile;
import org.dtools.ini.BasicIniSection;
import org.dtools.ini.IniFile;
import org.dtools.ini.IniFileReader;
import org.dtools.ini.IniFileWriter;
import org.dtools.ini.IniItem;
import org.dtools.ini.IniSection;

public class ReadAndWrite
{
	public List<Items> readINI(String pathname, String sectionname)
	{
		List<Items> list = new ArrayList<>();
		IniFile ini = new BasicIniFile(false);
		IniFileReader reader = new IniFileReader(ini, new File(pathname));
		try
		{
			reader.read();
			IniSection iniSection = ini.getSection(sectionname);
			if (iniSection != null)
			{
				for (IniItem item : iniSection.getItems())
				{
					Items items = new Items();
					items.setName(item.getName());
					items.setValue(item.getValue());
					list.add(items);
				}
				return list;
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public void writeINI(String pathname, String sectionname, List<Items> list)
	{
		IniFile ini = new BasicIniFile(false);
		IniFileWriter writer = new IniFileWriter(ini, new File(pathname));
		try
		{
			IniSection iniSection = new BasicIniSection(sectionname);
			ini.addSection(iniSection);
			for (int i = 0; i < list.size(); i++)
			{
				IniItem itemName = new IniItem(list.get(i).getName());
				itemName.setValue(list.get(i).getValue());
				iniSection.addItem(itemName);
			}
			writer.write();
		} catch (IOException e)
		{

			e.printStackTrace();
		}
	}

	public void writePath(String pathname, String sectionsave, String sectionselect, String savepath, String selectpath)
	{
		IniFile ini = new BasicIniFile(false);
		IniFileWriter writer = new IniFileWriter(ini, new File(pathname));
		try
		{
			IniSection iniSection = new BasicIniSection(sectionsave);
			ini.addSection(iniSection);
			if (!savepath.equals(""))
			{
				IniItem itemName = new IniItem(savepath);
				itemName.setValue("false");
				iniSection.addItem(itemName);
			}
			writer.write();
			IniSection iniSections = new BasicIniSection(sectionselect);
			ini.addSection(iniSections);
			if (!selectpath.equals(""))
			{
				IniItem itemNames = new IniItem(selectpath);
				itemNames.setValue("false");
				iniSections.addItem(itemNames);
			}
			writer.write();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public String readPath(String pathname, String sectionname)
	{
		IniFile ini = new BasicIniFile(false);
		File file = new File(pathname);
		if (!file.exists())
		{
			writePath(pathname, "SAVEPATH", "SELECTPATH", "", "");
		}
		IniFileReader reader = new IniFileReader(ini, file);
		try
		{
			reader.read();
			IniSection iniSection = ini.getSection(sectionname);
			if (iniSection != null && !iniSection.isEmpty())
			{
				return iniSection.getItem(0).getName();
			} else
			{
				ImageIcon icon = new ImageIcon("images/waring.png");
				icon.setImage(icon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
				JOptionPane.showMessageDialog(null, "创建path.ini文件且写入［" + sectionname + "］", "提示", JOptionPane.WARNING_MESSAGE, icon);
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return "";
	}

	public void write(List<Items> list)
	{
		writeINI(new File("").getAbsolutePath() + "/INI/case.ini", "CASELIST", list);
	}

	public String getLastString(String resourceString)
	{
		String[] temp = resourceString.split("/");
		return temp[temp.length - 1];
	}
}
