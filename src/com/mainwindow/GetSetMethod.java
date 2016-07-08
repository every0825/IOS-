package com.mainwindow;

import java.io.File;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import com.INI.Items;

public class GetSetMethod
{
	public int count;
	public File filesave;
	public File filepath;
	public String UDID;// = "a2d10a2d2925de6364b4ec997e2538e517c8de35";
	public String AutomationPath;// = "";
	public String pageName;// = "Perfect365";
	public JTextField savePathtextField;
	public JTextField pageNameTextField;
	public JTextField selectTemplateTextField;
	public MainWindow window;
	public JProgressBar progressBar;
	public JComboBox<String> comboBox;
	public String cmd;
	public boolean flag;
	public List<Items> itemslist;

	public List<Items> getItemslist()
	{
		return itemslist;
	}

	public void setItemslist(List<Items> itemslist)
	{
		this.itemslist = itemslist;
	}

	public boolean isFlag()
	{
		return flag;
	}

	public void setFlag(boolean flag)
	{
		this.flag = flag;
	}

	public String getCmd()
	{
		return cmd;
	}

	public void setCmd(String cmd)
	{
		this.cmd = cmd;
	}

	public JComboBox<String> getComboBox()
	{
		return comboBox;
	}

	public void setComboBox(JComboBox<String> comboBox)
	{
		this.comboBox = comboBox;
	}

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}

	public File getFilesave()
	{
		return filesave;
	}

	public void setFilesave(File filesave)
	{
		this.filesave = filesave;
	}

	public File getFilepath()
	{
		return filepath;
	}

	public void setFilepath(File filepath)
	{
		this.filepath = filepath;
	}

	public String getUDID()
	{
		return UDID;
	}

	public void setUDID(String uDID)
	{
		UDID = uDID;
	}

	public String getAutomationPath()
	{
		return AutomationPath;
	}

	public void setAutomationPath(String automationPath)
	{
		AutomationPath = automationPath;
	}

	public String getPageName()
	{
		return pageName;
	}

	public void setPageName(String pageName)
	{
		this.pageName = pageName;
	}

	public JTextField getSavePathtextField()
	{
		return savePathtextField;
	}

	public void setSavePathtextField(JTextField savePathtextField)
	{
		this.savePathtextField = savePathtextField;
	}

	public JTextField getPageNameTextField()
	{
		return pageNameTextField;
	}

	public void setPageNameTextField(JTextField pageNameTextField)
	{
		this.pageNameTextField = pageNameTextField;
	}

	public JTextField getSelectTemplateTextField()
	{
		return selectTemplateTextField;
	}

	public void setSelectTemplateTextField(JTextField selectTemplateTextField)
	{
		this.selectTemplateTextField = selectTemplateTextField;
	}

	public MainWindow getWindow()
	{
		return window;
	}

	public void setWindow(MainWindow window)
	{
		this.window = window;
	}

	public JProgressBar getProgressBar()
	{
		return progressBar;
	}

	public void setProgressBar(JProgressBar progressBar)
	{
		this.progressBar = progressBar;
	}

}
