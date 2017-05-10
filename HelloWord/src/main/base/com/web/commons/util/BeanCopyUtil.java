package com.web.commons.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class BeanCopyUtil
{
  public static Object CopyBeanToBean(Object source, Object des)
  {
    try
    {
      Method[] sourceMethod = source.getClass().getMethods();
      Method[] desMethod = des.getClass().getMethods();

      for (int i = 0; i < sourceMethod.length; i++) {
        String sourceMethodName = sourceMethod[i].getName();
        String sourceMethodFix = sourceMethodName.substring(3, 
          sourceMethodName.length());

        if (sourceMethodName.startsWith("get")) {
          for (int j = 0; j < desMethod.length; j++) {
            String desMethodName = desMethod[j].getName();
            String desMethodFix = desMethodName.substring(3, 
              desMethodName.length());
            if ((desMethodName.startsWith("set")) && 
              (desMethodFix.equals(sourceMethodFix))) {
              Object[] objs1 = new Object[0];
              Object[] objs2 = new Object[1];

              objs2[0] = sourceMethod[i]
                .invoke(source, objs1);

              if (objs2[0] != null) {
                desMethod[j].invoke(des, objs2);
              }
            }
          }
        }
      }

      return des;
    } catch (Exception e) {
      e.printStackTrace();
    }return null;
  }
public static HashMap CopyBeanToMap(Object source) {
	return CopyBeanToMap(source, true);
}

/**
 * 
 * @param source对象数据
 * @param toLower是否区分大小写
 * @return
 */
public static HashMap CopyBeanToMap(Object source, boolean toLower) {
	HashMap map = new HashMap();
	try {
		Method[] sourceMethod = source.getClass().getMethods();
		String sourceMethodName = "";
		String sourceMethodFix = "";
		for (int i = 0; i < sourceMethod.length; ++i) {
			sourceMethodName = sourceMethod[i].getName();
			sourceMethodFix = sourceMethodName.substring(3,
					sourceMethodName.length());
			String propertyName = sourceMethodFix.toLowerCase();

			if (propertyName.equalsIgnoreCase("class"))
				continue;
			if (sourceMethodName.startsWith("get")) {
				Object obj = sourceMethod[i].invoke(source, new Object[0]);

				if (!(toLower)) {
					ClassInvoke ci = new ClassInvoke(source.getClass());
					Field fd = ci.getField(propertyName);
					if (fd != null)
						propertyName = fd.getName();
				}
				map.put(propertyName, (obj != null) ? obj.toString() : "");
			}
		}
	} catch (IllegalArgumentException e) {
		e.printStackTrace();
		return null;
	} catch (IllegalAccessException e) {
		e.printStackTrace();
		return null;
	} catch (InvocationTargetException e) {
		e.printStackTrace();
		return null;
	}
	return map;
  }

 
}