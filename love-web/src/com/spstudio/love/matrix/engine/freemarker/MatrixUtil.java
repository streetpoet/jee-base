package com.spstudio.love.matrix.engine.freemarker;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.spstudio.love.matrix.engine.ConfigBean;
import com.spstudio.love.matrix.entity.MatrixModule;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class MatrixUtil {

	public static Map<Object, Object> convertBean(Object bean)
			throws IntrospectionException, IllegalAccessException,
			InvocationTargetException {

		Class<?> type = bean.getClass();
		Map<Object, Object> returnMap = new HashMap<Object, Object>();
		BeanInfo beanInfo = Introspector.getBeanInfo(type);

		PropertyDescriptor[] propertyDescriptors = beanInfo
				.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			if (!propertyName.equals("class")) {
				Method readMethod = descriptor.getReadMethod();
				Object result = readMethod.invoke(bean, new Object[0]);
				if (result != null) {
					returnMap.put(propertyName, result);
				} else {
					returnMap.put(propertyName, "");
				}
			}
		}
		return returnMap;
	}

	public static String convertFtlString(MatrixModule mb, String stringWithFtlSymbol) {
		try {
			Configuration cfg = new Configuration();
			cfg.setTemplateLoader(new StringTemplateLoader(stringWithFtlSymbol));
			cfg.setDefaultEncoding("UTF-8");
			Template template = cfg.getTemplate("");
			Map<Object, Object> root = new HashMap<Object, Object>();
			root.put(ConfigBean.MODULE_BEAN_VAR, mb);
			StringWriter writer = new StringWriter();
			template.process(root, writer);
			return writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
