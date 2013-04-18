package com.spstudio.love.interest.bean;

import interfaces.interest.IInterestSingleton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import com.spstudio.love.interest.entity.TechTypeBean;
import com.spstudio.love.interest.qualifier.InterestSingleRemoteBean;

@Dependent
public class TechTypeBeanHtmlSelectionBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -162771330630669L;
	
	@Inject @InterestSingleRemoteBean IInterestSingleton interestSingleton;
	private List<SelectItem> techTypeBeanList;

	public List<SelectItem> getTechTypeBeanList() {
		if (techTypeBeanList != null){
			return techTypeBeanList;
		}
		List<TechTypeBean> list = interestSingleton.retrieveTechTypeBeanList();
		techTypeBeanList = new ArrayList<SelectItem>();
		if (list != null && list.size() != 0){
			for (TechTypeBean bean: list){
				techTypeBeanList.add(new SelectItem(bean.getId(), bean.getLabel()));
			}
		}
		return techTypeBeanList;
	}
}
