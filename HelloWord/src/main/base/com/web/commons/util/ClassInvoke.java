/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.web.commons.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassInvoke {
	private Class thisClass;
	private Class superClass;

	public ClassInvoke(String className) {
		try {
			this.thisClass = Class.forName(className);
			if (this.thisClass.getSuperclass() != null) {
				this.superClass = this.thisClass.getSuperclass();
			}
		} catch (ClassNotFoundException arg2) {
			arg2.printStackTrace();
		}

	}

	public ClassInvoke(Class thisClass) {
		this.thisClass = thisClass;
		if (this.thisClass.getSuperclass() != null) {
			this.superClass = this.thisClass.getSuperclass();
		}

	}

	public Field getField(String fieldName) {
		fieldName = fieldName.toLowerCase();
		Field[] fields = this.thisClass.getDeclaredFields();
		Field[] arg5 = fields;
		int arg4 = fields.length;

		for (int arg3 = 0; arg3 < arg4; ++arg3) {
			Field fi = arg5[arg3];
			if (fi.getName().toLowerCase().equals(fieldName)) {
				return fi;
			}
		}

		return null;
	}

	public Field[] getFields() {
		Field[] fields = this.thisClass.getDeclaredFields();
		return fields;
	}

	public Method getMethod(String methodName) {
		methodName = methodName.toLowerCase();
		Method[] methods = this.thisClass.getDeclaredMethods();
		Method[] arg5 = methods;
		int arg4 = methods.length;

		for (int arg3 = 0; arg3 < arg4; ++arg3) {
			Method me = arg5[arg3];
			if (me.getName().toLowerCase().equals(methodName)) {
				return me;
			}
		}

		return null;
	}

	public Method[] getMethods() {
		Method[] methods = this.thisClass.getDeclaredMethods();
		return methods;
	}

	public Method getSuperMethod(String methodName) {
		methodName = methodName.toLowerCase();
		Method[] methods = this.getSuperMethods();
		Method[] arg5 = methods;
		int arg4 = methods.length;

		for (int arg3 = 0; arg3 < arg4; ++arg3) {
			Method me = arg5[arg3];
			if (me.getName().toLowerCase().equals(methodName)) {
				return me;
			}
		}

		return null;
	}

	public Method[] getSuperMethods() {
		return this.superClass != null ? this.superClass.getDeclaredMethods() : null;
	}

	public Object getValue(Object obj, String methodName, Object[] params) {
		Method method = this.getMethod(methodName);
		if (method == null) {
			method = this.getSuperMethod(methodName);
		}

		try {
			Object e = method.invoke(obj, params);
			return e;
		} catch (IllegalArgumentException arg5) {
			arg5.printStackTrace();
		} catch (IllegalAccessException arg6) {
			arg6.printStackTrace();
		} catch (InvocationTargetException arg7) {
			arg7.printStackTrace();
		}

		return null;
	}

	public void setValue(Object obj, String methodName, Object[] params) {
		Method method = this.getMethod(methodName);
		if (method == null) {
			method = this.getSuperMethod(methodName);
		}

		try {
			method.invoke(obj, params);
		} catch (IllegalArgumentException arg5) {
			arg5.printStackTrace();
		} catch (IllegalAccessException arg6) {
			arg6.printStackTrace();
		} catch (InvocationTargetException arg7) {
			arg7.printStackTrace();
		}

	}
}