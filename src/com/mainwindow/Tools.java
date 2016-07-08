package com.mainwindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Tools
{
	public Set<String> getUDID()
	{
		return cmdReturn("idevice_id -l");
	}

	public Set<String> cmdReturn(String cmd)
	{
		Set<String> set = new HashSet<>();
		try
		{
			Process p = Runtime.getRuntime().exec(cmd);

			// 正确输出
			InputStream input = p.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input, "UTF8"));
			String line = "";
			while ((line = reader.readLine()) != null)
			{
				set.add(line);
			}
			// 错误输出
			InputStream errorInput = p.getErrorStream();
			BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorInput, "UTF8"));
			String eline = "";
			while ((eline = errorReader.readLine()) != null)
			{
				set.add(eline);
			}
			return set;
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
