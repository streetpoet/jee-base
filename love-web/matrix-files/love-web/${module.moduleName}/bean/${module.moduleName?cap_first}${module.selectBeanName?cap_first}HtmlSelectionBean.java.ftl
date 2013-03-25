package com.spstudio.love.${module.moduleName}.bean;

import interfaces.${module.moduleName}.I${module.moduleName?cap_first}Singleton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import com.spstudio.love.${module.moduleName}.entity.${module.moduleName?cap_first}Project;
import com.spstudio.love.${module.moduleName}.qualifier.${module.moduleName?cap_first}SingleRemoteBean;

@Dependent
public class ${module.moduleName?cap_first}${module.selectBeanName?cap_first}HtmlSelectionBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -${uid()}L;
	
	@Inject @${module.moduleName?cap_first}SingleRemoteBean I${module.moduleName?cap_first}Singleton ${module.moduleName}Singleton;
	private List<SelectItem> ${module.selectBeanName}List;

	public List<SelectItem> get${module.selectBeanName?cap_first}List() {
		if (${module.selectBeanName}List != null){
			return ${module.selectBeanName}List;
		}
		List<${module.selectBeanName?cap_first}> list = ${module.moduleName}Singleton.retrieveProjectList();
		${module.selectBeanName}List = new ArrayList<SelectItem>();
		if (list != null && list.size() != 0){
			for (${module.selectBeanName?cap_first} bean: list){
				${module.selectBeanName}List.add(new SelectItem(bean.getId(), bean.getLabel()));
			}
		}
		return ${module.selectBeanName}List;
	}
}
