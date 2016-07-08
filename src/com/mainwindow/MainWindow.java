package com.mainwindow;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;

import com.INI.Items;

public class MainWindow
{
	private Dimension sizeScreen = Toolkit.getDefaultToolkit().getScreenSize();
	private int Width = sizeScreen.width;
	private int Height = sizeScreen.height;
	private JFileChooser jFileChooser = new JFileChooser();
	private JFileChooser jFileChooserpath = new JFileChooser();
	private JFileChooser jFileChoosersave = new JFileChooser();
	private JCheckBox[] chckbxNewCheckBox = new JCheckBox[100];
	private Map<String, String> map = new HashMap<>();
	List<Items> itemslist = new ArrayList<>();
	public JTextPane textPane = new JTextPane();
	Tools tools = new Tools();
	JLabel tipLabel;
	public String fontStyle = "";
	//public boolean flag = false;
	JFrame frame;
	static UseObject useObject = new UseObject();
	static MainWindow window;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					window = new MainWindow();
					useObject.buttionListener.getSetMethod.setWindow(window);
					window.frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public MainWindow()
	{
		initialize();
		DefaultComboBoxModel<String> dComboBoxModel = new DefaultComboBoxModel<>();
		useObject.buttionListener.getSetMethod.getComboBox().setModel(dComboBoxModel);
		Set<String> idList = tools.getUDID();
		if (idList.size() == 0)
		{
			dComboBoxModel.addElement("a2d10a2d2925de6364b4ec997e2538e517c8de35");
			ImageIcon icon = new ImageIcon("images/waring.png");
			icon.setImage(icon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
			JOptionPane.showMessageDialog(null, "未检测到设备", "提示", JOptionPane.WARNING_MESSAGE, icon);
		} else
		{
			Iterator<String> iterator = idList.iterator();
			while (iterator.hasNext())
			{
				dComboBoxModel.addElement(iterator.next());
			}
		}
	}

	private void initialize()
	{
		frame = new JFrame();
		frame.setBounds(Width / 8, Height / 8, 1260, 746);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		useObject.buttionListener.frameWindowListener(frame, chckbxNewCheckBox, map);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		useObject.buttionListener.getSetMethod.setFlag(false);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(320, 6, 934, 578);
		frame.getContentPane().add(scrollPane);
		textPane.setEditable(false);

		scrollPane.setViewportView(textPane);
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(6, 6, 314, 578);
		frame.getContentPane().add(scrollPane_1);

		final JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		scrollPane_1.setViewportView(panel);
		panel.setLayout(null);

		useObject.buttionListener.selectAllListener(useObject.usedButton.selectAllButton, chckbxNewCheckBox);
		useObject.usedButton.selectAllButton.setBounds(6, 638, 117, 34);
		frame.getContentPane().add(useObject.usedButton.selectAllButton);

		useObject.buttionListener.cancelAllListener(useObject.usedButton.cancelAllButton, chckbxNewCheckBox);
		useObject.usedButton.cancelAllButton.setBounds(6, 596, 117, 34);
		frame.getContentPane().add(useObject.usedButton.cancelAllButton);

		useObject.buttionListener.runListener(chckbxNewCheckBox, useObject.usedButton.runButton, map);
		useObject.usedButton.runButton.setBounds(266, 598, 125, 75);
		frame.getContentPane().add(useObject.usedButton.runButton);

		useObject.buttionListener.getSetMethod.setItemslist(itemslist);
		useObject.buttionListener.addListener(useObject.usedButton.addButton, chckbxNewCheckBox, jFileChooser, panel, map);
		useObject.usedButton.addButton.setBounds(135, 596, 117, 34);
		frame.getContentPane().add(useObject.usedButton.addButton);

		useObject.buttionListener.getSetMethod.setPageNameTextField(new JTextField());
		useObject.buttionListener.getSetMethod.getPageNameTextField().setBounds(457, 595, 251, 34);
		useObject.buttionListener.getSetMethod.getPageNameTextField().setText("Perfect365");
		frame.getContentPane().add(useObject.buttionListener.getSetMethod.getPageNameTextField());
		useObject.buttionListener.getSetMethod.getPageNameTextField().setColumns(10);

		JLabel pageNameLabel = new JLabel("程序名：");
		pageNameLabel.setBounds(406, 596, 100, 34);
		frame.getContentPane().add(pageNameLabel);

		useObject.buttionListener.getSetMethod.setSelectTemplateTextField(new JTextField());
		useObject.buttionListener.getSetMethod.getSelectTemplateTextField().setColumns(10);
		useObject.buttionListener.getSetMethod.getSelectTemplateTextField().setBounds(457, 637, 305, 34);
		frame.getContentPane().add(useObject.buttionListener.getSetMethod.getSelectTemplateTextField());

		useObject.buttionListener.getSetMethod.setSavePathtextField(new JTextField());
		useObject.buttionListener.getSetMethod.getSavePathtextField().setColumns(10);
		useObject.buttionListener.getSetMethod.getSavePathtextField().setBounds(880, 637, 260, 34);
		frame.getContentPane().add(useObject.buttionListener.getSetMethod.getSavePathtextField());

		useObject.buttionListener.savePathListener(useObject.usedButton.savePathButton, jFileChoosersave, useObject.buttionListener.getSetMethod.getSavePathtextField());
		useObject.usedButton.savePathButton.setBounds(1138, 637, 117, 34);
		frame.getContentPane().add(useObject.usedButton.savePathButton);

		JLabel udidLabel = new JLabel("UDID:");
		udidLabel.setBounds(708, 604, 61, 16);
		frame.getContentPane().add(udidLabel);

		useObject.buttionListener.deleteListener(useObject.usedButton.deleteButton, chckbxNewCheckBox, panel, map);
		useObject.usedButton.deleteButton.setBounds(135, 638, 117, 34);
		frame.getContentPane().add(useObject.usedButton.deleteButton);

		useObject.buttionListener.getSetMethod.setComboBox(new JComboBox<String>());
		useObject.buttionListener.getSetMethod.getComboBox().setBounds(750, 592, 393, 43);
		frame.getContentPane().add(useObject.buttionListener.getSetMethod.getComboBox());

		useObject.buttionListener.selsetTemplateListener(useObject.usedButton.selectTemplatebutton, jFileChooserpath, useObject.buttionListener.getSetMethod.getSelectTemplateTextField());
		useObject.usedButton.selectTemplatebutton.setBounds(760, 638, 120, 34);
		frame.getContentPane().add(useObject.usedButton.selectTemplatebutton);

		useObject.buttionListener.getSetMethod.setProgressBar(new JProgressBar());
		useObject.buttionListener.getSetMethod.getProgressBar().setBounds(6, 693, 1248, 27);
		frame.getContentPane().add(useObject.buttionListener.getSetMethod.getProgressBar());

		tipLabel = new JLabel();
		tipLabel.setBounds(16, 679, 1238, 27);
		frame.getContentPane().add(tipLabel);

		useObject.buttionListener.refreshListener(useObject.usedButton.refreshButton, useObject.buttionListener.getSetMethod.getComboBox(), tools);
		useObject.usedButton.refreshButton.setBounds(1140, 596, 114, 34);
		frame.getContentPane().add(useObject.usedButton.refreshButton);
		useObject.buttionListener.showInitWindow(chckbxNewCheckBox, map, panel);
	}
}
