package com.ucloudlink.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializeTool
{
	public static String object2String(Object obj)
	{
		String objBody = null;
		ByteArrayOutputStream baops = null;
		ObjectOutputStream oos = null;

		try
		{
			baops = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baops);
			oos.writeObject(obj);
			byte[] bytes = baops.toByteArray();
			objBody = new String(bytes);
		} catch (IOException e)
		{
			//LogUtil.debug(e);
		} finally
		{
			try
			{
				if (oos != null)
					oos.close();
				if (baops != null)
					baops.close();
			} catch (IOException e)
			{
				//LogUtil.debug(e);
			}
		}
		return objBody;
	}

	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T getObjectFromString 
					(String objBody, Class<T> clazz)

	{
		byte[] bytes = objBody.getBytes();
		ObjectInputStream ois = null;
		T obj = null;
		try
		{
			ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
			obj = (T) ois.readObject();
		} catch (IOException e)
		{
			//LogUtil.debug(e);
		} catch (ClassNotFoundException e)
		{
			//LogUtil.debug(e);
		} finally
		{

			try
			{
				if (ois != null)
					ois.close();
			} catch (IOException e)
			{
				//LogUtil.debug(e);
			}
		}

		return obj;
	}
	
	
	/**
	 * 反序列化
	 * 
	 * @param bytes
	 * @return
	 */
	public static <T> T unserialize(byte[] bytes, Class<T> entityClass)
	{
		ByteArrayInputStream bais = null;
		try
		{
			bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (T) ois.readObject();
		} catch (Exception e)
		{
			System.out.println(e);
		}
		return null;
	}

	/**
	 * 序列化
	 * 
	 * @param object
	 * @return
	 */
	public static byte[] serialize(Object object)
	{
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try
		{

			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			byte[] bytes = baos.toByteArray();
			return bytes;
		} catch (Exception e)
		{
			System.out.println(e);
		}
		return null;
	}

}
