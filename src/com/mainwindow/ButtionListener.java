package com.mainwindow;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.INI.Items;
import com.INI.ReadAndWrite;

public class ButtionListener
{
	GetSetMethod getSetMethod = new GetSetMethod();
	ReadAndWrite readAndWrite = new ReadAndWrite();

	public void frameWindowListener(JFrame frame, final JCheckBox[] checkBoxs, final Map<String, String> map)
	{
		frame.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				List<String> list = new ArrayList<>();
				List<String> listname = new ArrayList<>();
				list.add("");
				listname.add("");
				new Thread(new Excmd("killall instruments", list, listname, getSetMethod.getWindow())).start();

				getSetMethod.getItemslist().removeAll(getSetMethod.getItemslist());
				getSetMethod.setItemslist(getSetMethod.getItemslist());
				for (int i = 0; i < getSetMethod.getCount(); i++)
				{
					Items items = new Items();
					items.setName(map.get(checkBoxs[i].getText()));
					if (checkBoxs[i].isSelected())
					{
						items.setValue("true");
					} else
					{
						items.setValue("false");
					}
					getSetMethod.getItemslist().add(items);
				}
				readAndWrite.write(getSetMethod.getItemslist());
				System.exit(0);
			}
		});
	}

	public void selectAllListener(JButton button, final JCheckBox[] jcheckBox)
	{
		button.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				for (int i = 0; i < getSetMethod.getCount(); i++)
				{
					jcheckBox[i].setSelected(true);
				}
			}
		});
	}

	public void cancelAllListener(JButton button, final JCheckBox[] jcheckBox)
	{
		button.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				for (int i = 0; i < getSetMethod.getCount(); i++)
				{
					jcheckBox[i].setSelected(false);
				}
			}
		});
	}

	public void savePathListener(JButton button, final JFileChooser jfilechooser, final JTextField textField)
	{
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				jfilechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int state = jfilechooser.showOpenDialog(null);
				if (state == 1)
				{
					return;
				}
				getSetMethod.setFilesave(jfilechooser.getSelectedFile());
				textField.setText(getSetMethod.getFilesave().getPath());
				if (getSetMethod.getFilepath() != null && getSetMethod.getFilesave() != null)
				{
					readAndWrite.writePath(new File("").getAbsolutePath() + "/INI/path.ini", "SAVEPATH", "SELECTPATH", getSetMethod.getFilesave().getPath(), getSetMethod.getFilepath().getPath());
				}
			}
		});
	}

	public void selsetTemplateListener(JButton button, final JFileChooser jfilechooser, final JTextField textField)
	{
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				jfilechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int state = jfilechooser.showOpenDialog(null);
				if (state == 1)
				{
					return;
				}
				getSetMethod.setFilepath(jfilechooser.getSelectedFile());
				textField.setText(getSetMethod.getFilepath().getPath());
				if (getSetMethod.getFilepath() != null && getSetMethod.getFilesave() != null)
				{
					readAndWrite.writePath(new File("").getAbsolutePath() + "/INI/path.ini", "SAVEPATH", "SELECTPATH", getSetMethod.getFilesave().getPath(), getSetMethod.getFilepath().getPath());
				}
			}
		});
	}

	public void refreshListener(JButton button, final JComboBox<String> comboBox, final Tools tools)
	{
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Set<String> idSet = tools.getUDID();
				DefaultComboBoxModel<String> dComboBoxModel = new DefaultComboBoxModel<>();
				comboBox.setModel(dComboBoxModel);
				Iterator<String> iterator = idSet.iterator();
				while (iterator.hasNext())
				{
					dComboBoxModel.addElement(iterator.next());
				}
			}
		});
	}

	public void deleteListener(JButton button, final JCheckBox[] checkBox, final JPanel panel, final Map<String, String> map)
	{
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				List<String> integers = new ArrayList<>();
				getSetMethod.getItemslist().removeAll(getSetMethod.getItemslist());
				getSetMethod.setItemslist(getSetMethod.getItemslist());
				for (int i = 0; i < getSetMethod.getCount(); i++)
				{
					if (!checkBox[i].isSelected())
					{
						integers.add(checkBox[i].getText());
						Items items = new Items();
						items.setName(map.get(checkBox[i].getText()));
						items.setValue("false");
						getSetMethod.getItemslist().add(items);
					}
				}
				readAndWrite.write(getSetMethod.getItemslist());
				for (int i = 0; i < getSetMethod.getCount(); i++)
				{
					panel.remove(checkBox[i]);
				}
				for (int i = 0; i < integers.size(); i++)
				{
					checkBox[i] = new JCheckBox(integers.get(i));
					checkBox[i].setBounds(6, 1 + i * 23, 300, 23);
					panel.add(checkBox[i]);
				}
				panel.repaint();
				getSetMethod.setCount(integers.size());
			}
		});
	}

	public void addListener(JButton button, final JCheckBox[] checkBoxs, final JFileChooser chooser, final JPanel panel, final Map<String, String> map)
	{
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// add
				int c = getSetMethod.getCount();
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int state = chooser.showOpenDialog(null);
				if (state == 1)
				{
					return;
				}
				checkBoxs[c] = new JCheckBox(chooser.getSelectedFile().getName());
				checkBoxs[c].setBounds(6, 1 + c * 23, 300, 23);
				map.put(chooser.getSelectedFile().getName(), chooser.getSelectedFile().getAbsolutePath());
				panel.add(checkBoxs[c]);
				panel.repaint();
				c++;
				getSetMethod.setCount(c);
				List<Items> list2 = readAndWrite.readINI(new File("").getAbsolutePath() + "/INI/case.ini", "CASELIST");
				if (list2 != null)
				{
					getSetMethod.setItemslist(list2);
				}
				for (int i = 0; i < map.size(); i++)
				{
					Items items = new Items();
					items.setName(map.get(chooser.getSelectedFile().getName()));
					items.setValue("false");
					getSetMethod.getItemslist().add(items);
				}
				readAndWrite.write(getSetMethod.getItemslist());
			}
		});
	}

	public void runListener(final JCheckBox[] checkBox, final JButton button, final Map<String, String> map)
	{
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// run
				getSetMethod.setUDID(getSetMethod.getComboBox().getItemAt(getSetMethod.getComboBox().getSelectedIndex()).toString());
				getSetMethod.setAutomationPath(getSetMethod.getSelectTemplateTextField().getText());
				getSetMethod.setPageName(getSetMethod.getPageNameTextField().getText());
				if (getSetMethod.getUDID().trim().length() != 0 && getSetMethod.getAutomationPath().trim().length() != 0 && getSetMethod.getPageName().trim().length() != 0 && getSetMethod.getSavePathtextField().getText().trim().length() != 0)
				{
					getSetMethod.setCmd("instruments -w " + getSetMethod.getUDID() + "  -t  " + getSetMethod.getAutomationPath() + " " + getSetMethod.getPageName() + " -e UIASCRIPT");
					// System.out.println("instruments -w " +
					// getSetMethod.getUDID() + " -t " +
					// getSetMethod.getAutomationPath() + " " +
					// getSetMethod.getPageName() + " -e UIASCRIPT");
				} else
				{
					ImageIcon icon = new ImageIcon("images/waring.png");
					icon.setImage(icon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
					JOptionPane.showMessageDialog(null, "请填写完整参数信息", "提示", JOptionPane.WARNING_MESSAGE, icon);
					return;
				}
				List<String> list = new ArrayList<>();
				List<String> listname = new ArrayList<>();
				for (int i = 0; i < getSetMethod.getCount(); i++)
				{
					if (checkBox[i].isSelected())
					{
						list.add(map.get(checkBox[i].getText()));
						listname.add(checkBox[i].getText());
					}
				}
				if (button.getText().equals("Run"))
				{
					button.setText("Stop");
					new Thread(new Excmd(getSetMethod.getCmd(), list, listname, getSetMethod.getWindow())).start();
					getSetMethod.getProgressBar().setValue(0);
				} else
				{
					new Excmd(getSetMethod.getWindow()).execCmd("killall instruments");
					getSetMethod.setFlag(true);
				}

			}
		});
	}

	public void showInitWindow(JCheckBox[] checkBoxs, Map<String, String> map, JPanel panel)
	{
		List<Items> list2 = readAndWrite.readINI(new File("").getAbsolutePath() + "/INI/case.ini", "CASELIST");
		int c = getSetMethod.getCount();
		if (list2 != null)
		{
			for (int i = 0; i < list2.size(); i++)
			{
				checkBoxs[c] = new JCheckBox(readAndWrite.getLastString(list2.get(i).getName()));
				checkBoxs[c].setBounds(6, 1 + c * 23, 300, 23);
				map.put(readAndWrite.getLastString(list2.get(i).getName()), list2.get(i).getName());
				if (list2.get(i).getValue().equals("true"))
				{
					checkBoxs[c].setSelected(true);
				}
				panel.add(checkBoxs[c]);
				panel.repaint();
				c++;
				getSetMethod.setCount(c);
			}
		} else
		{
			ImageIcon icon = new ImageIcon("images/waring.png");
			icon.setImage(icon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
			JOptionPane.showMessageDialog(null, "请保证当前目录下存在case.ini文件且首行内容为［CASELIST］", "提示", JOptionPane.WARNING_MESSAGE, icon);
		}
		getSetMethod.getSavePathtextField().setText(readAndWrite.readPath(new File("").getAbsolutePath() + "/INI/path.ini", "SAVEPATH"));
		getSetMethod.getSelectTemplateTextField().setText(readAndWrite.readPath(new File("").getAbsolutePath() + "/INI/path.ini", "SELECTPATH"));
	}
}
