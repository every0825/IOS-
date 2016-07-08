package com.mainwindow;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;

public class Excmd implements Runnable
{

	String cm = "";
	List<String> lis;
	List<String> name;
	MainWindow mainWindow;
	String flag = "true";
	Process p = null;

	public Excmd(String cm, List<String> lis, List<String> name, MainWindow mainWindow)
	{
		this.cm = cm;
		this.lis = lis;
		this.name = name;
		this.mainWindow = mainWindow;
	}

	public Excmd(MainWindow maindow)
	{
		this.mainWindow = maindow;
	}

	@Override
	public void run()
	{

		MainWindow.useObject.buttionListener.getSetMethod.getProgressBar().setMinimum(0);
		MainWindow.useObject.buttionListener.getSetMethod.getProgressBar().setMaximum(lis.size());
		for (int i = 0; i < lis.size(); i++)
		{
			if (MainWindow.useObject.buttionListener.getSetMethod.isFlag())
			{
				MainWindow.useObject.buttionListener.getSetMethod.setFlag(false);
				MainWindow.useObject.buttionListener.getSetMethod.getProgressBar().setValue(lis.size());
				break;
			}
			mainWindow.tipLabel.setText("正在执行：" + lis.get(i));
			execCmd(cm + " " + lis.get(i));
			mainWindow.tipLabel.setText("正在保存...");
			if (MainWindow.useObject.buttionListener.getSetMethod.getSavePathtextField().getText() != null || MainWindow.useObject.buttionListener.getSetMethod.getSavePathtextField().getText().trim().length() != 0)
			{
				execCmd("mkdir " + MainWindow.useObject.buttionListener.getSetMethod.getSavePathtextField().getText() + "/" + name.get(i).replace(".js", ""));
				execCmd("mv instrumentscli0.trace " + MainWindow.useObject.buttionListener.getSetMethod.getSavePathtextField().getText() + "/" + name.get(i).replace(".js", "") + "/" + name.get(i).replace(".js", "") + ".trace");
			} else
			{
				execCmd("mkdir " + name.get(i).replace(".js", ""));
				execCmd("mv instrumentscli0.trace " + name.get(i).replace(".js", "") + "/" + name.get(i).replace(".js", "") + ".trace");
			}
			MainWindow.useObject.buttionListener.getSetMethod.getProgressBar().setValue(i + 1);
		}
		mainWindow.tipLabel.setText("已完成");
		MainWindow.useObject.usedButton.runButton.setText("Run");
	}

	public void execCmd(String cmd)
	{
		if (mainWindow == null)
		{
			return;
		}
		Style def = mainWindow.textPane.getStyledDocument().addStyle(null, null);
		StyleConstants.setFontFamily(def, "Consolas");
		StyleConstants.setFontSize(def, 14);
		Style normal = mainWindow.textPane.addStyle("normal", def);
		Style s = mainWindow.textPane.addStyle("red", normal);
		StyleConstants.setForeground(s, Color.RED);
		mainWindow.textPane.setParagraphAttributes(normal, true);

		try
		{
			p = Runtime.getRuntime().exec(cmd);
			// 正确输出
			InputStream input = p.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input, "UTF8"));
			String line = "";
			while ((line = reader.readLine()) != null)
			{
				if (!new String(line.getBytes(), "UTF-8").equals(""))
				{
					try
					{
						mainWindow.textPane.getDocument().insertString(mainWindow.textPane.getDocument().getLength(), line + "\r\n", mainWindow.textPane.getStyle("normal"));
						mainWindow.textPane.setCaretPosition(mainWindow.textPane.getDocument().getLength());
					} catch (BadLocationException e)
					{
						e.printStackTrace();
					}
				}
			}
			// 错误输出
			InputStream errorInput = p.getErrorStream();
			BufferedReader errorReader;
			try
			{
				errorReader = new BufferedReader(new InputStreamReader(errorInput, "UTF8"));
				String eline = "";
				while ((eline = errorReader.readLine()) != null)
				{
					if (!new String(eline.getBytes(), "UTF-8").equals(""))
					{
						mainWindow.textPane.getDocument().insertString(mainWindow.textPane.getDocument().getLength(), eline + "\r\n", mainWindow.textPane.getStyle("red"));
						mainWindow.textPane.setCaretPosition(mainWindow.textPane.getDocument().getLength());
					}
				}
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
