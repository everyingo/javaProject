package com.mine.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class DataUtil {

	/**
	 * 深度复制，复制整个对象图
	 * @param sra
	 * @return
	 */
	public static Serializable deepCopy(Serializable sra){
		
		try {
			ByteArrayOutputStream baos=new ByteArrayOutputStream();
			ObjectOutputStream oos=new ObjectOutputStream(baos);
			oos.writeObject(sra);
			oos.close();
			baos.close();
			
			byte[] bytes=baos.toByteArray();
			ByteArrayInputStream bais=new ByteArrayInputStream(bytes);
			ObjectInputStream ois=new ObjectInputStream(bais);
			Serializable copy=(Serializable) ois.readObject();
			ois.close();
			bais.close();
			return copy;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
