package com.camp.utils;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;

public class JavassistUtils {
	
	public static void main(String args[])
	{
		ClassPool pool = ClassPool.getDefault();
		try {
			CtClass cc = pool.get("springfox.documentation.service.Documentation");
			
			CtConstructor ctConstructor = new CtConstructor(new CtClass[]{}, cc); 
			ctConstructor.setBody("{}");
		
			cc.addConstructor(ctConstructor); 
			
			/*ResInput obj  = (ResInput) cc.toClass().newInstance();
			
			obj.getName();*/
			
			cc.writeFile("E://");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
