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

import freemarker.template.Configuration;
import freemarker.template.Template;

public class MatrixFreeMarkerUtil {

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
				if (readMethod == null){
					continue;
				}
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

	public static String convertFtlString(ConfigBean cb, String textWithFtlSymbol) {
		try {
			Configuration cfg = new Configuration();
			cfg.setTemplateLoader(new StringTemplateLoader(textWithFtlSymbol));
			cfg.setDefaultEncoding("UTF-8");
			Template template = cfg.getTemplate("");
			Map<Object, Object> root = new HashMap<Object, Object>();
			assembleFreeMarkerRootObject(root, cb);
			StringWriter writer = new StringWriter();
			template.process(root, writer);
			return writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void assembleFreeMarkerRootObject(Map<Object, Object> root, ConfigBean cb){
		root.put(ConfigBean.MODULE_BEAN_VAR, cb.getMatrixModule());
		root.put(ConfigBean.FUNCTION_BEAN_VAR, cb.getMatrixFunction());
		root.put(ConfigBean.PROJECT_BEAN_VAR, cb.getMatrixProject());
	}
	
}
