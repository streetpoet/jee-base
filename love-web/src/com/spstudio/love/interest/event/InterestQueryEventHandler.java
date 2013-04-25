package com.spstudio.love.interest.event;

import interfaces.interest.IInterest;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import com.spstudio.love.interest.action.InterestAction;
import com.spstudio.love.interest.action.InterestChartStatAction;
import com.spstudio.love.interest.action.InterestClassifyStatAction;
import com.spstudio.love.interest.action.MemberStatAction;
import com.spstudio.love.interest.bean.TechSelectBeanQueryConversation;
import com.spstudio.love.interest.entity.ChartClassifyPercentageBean;
import com.spstudio.love.interest.entity.TechSelectBean;
import com.spstudio.love.interest.helper.TechSelectBeanCondition;
import com.spstudio.love.interest.qualifier.InterestRemoteBean;
import com.spstudio.love.interest.qualifier.TechSelectBeanQualifier;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.bean.UserInfo;
import com.spstudio.love.system.interfaces.IQueryResult;
import com.spstudio.love.system.qualifier.LoveLogged;
import com.spstudio.love.system.qualifier.UserInfoQualifier;

@Dependent
public class InterestQueryEventHandler implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -17892731904445L;
	
	@Inject TechSelectBeanQueryConversation techSelectBeanQueryConversation;
	@Inject @UserInfoQualifier UserInfo userInfo;
	@Inject InterestAction interestAction;
	@Inject InterestClassifyStatAction interestClassifyStatAction;
	@Inject MemberStatAction memberStatAction;
	@Inject InterestChartStatAction interestChartStatAction;
	@Inject @InterestRemoteBean IInterest interestRemoteBean;
	@Inject @LoveLogged Logger log;
	@Inject @TechSelectBeanQualifier TechSelectBean techSelectBean;

	public void queryTechSelectBean(@Observes @InterestQueryEventQualifier InterestQueryEvent event){
		switch (event.getQueryMode()) {
		case LOAD_ALL_RECORD:
			loadAllInterest();
			break;
		case LOAD_SINGLE_RECORD:
			loadSingleInterest();
			break;
		case LOAD_LIKED_TECH_LIST:
			loadLikedTechList();
			break;
		case LOAD_CLASSIFY_STAT_TECH_LIST:
			interestClassifyStatAction.setClassifyStatListBean(interestRemoteBean.loadClassifyStatBean());
			break;
		case LOAD_MEMBER_STAT_TECH_LIST:
			memberStatAction.setMemberStatListBean(interestRemoteBean.loadMemberStatBean());
			break;
		case LOAD_CHART_CLASSIFY_PERCENTAGE:
			List<ChartClassifyPercentageBean> list = interestRemoteBean.loadClassifyPercentageBean();
			int total = 0;
			for (ChartClassifyPercentageBean bean: list){
				total += bean.getSelectedUserCount();
			}
			interestChartStatAction.setChartClassifyPercentageListBean(list);
			interestChartStatAction.setTotalClassifyCount(total);
			break;
		default:
			break;
		}
	}
	
	private void loadLikedTechList(){
		List<TechSelectBean> listBean = interestRemoteBean.loadLikedTechSelectBean(userInfo.getUserId());
		int[] techIds = new int[]{};
		if (listBean != null){
			techIds = new int[listBean.size()];
			for (int i = 0;i < listBean.size(); i ++){
				techIds[i] = listBean.get(i).getTechClassifyId();
			}
		}
		interestAction.setSelectedTechIds(techIds);
	}
	
	private void loadSingleInterest(){
		TechSelectBean techSelectBean = interestRemoteBean.loadTechSelectBean(this.techSelectBean.getId());
		this.techSelectBean.setTechSelectBean(techSelectBean);
	}
	
	private void loadAllInterest(){
		TechSelectBeanCondition techSelectBeanCondition = techSelectBeanQueryConversation.getTechSelectBeanCondition();
		TechSelectBeanCondition tempTechSelectBeanCondition = techSelectBeanCondition.clone();
		PageObject pageObject = techSelectBeanQueryConversation.getPageObject();
		IQueryResult<TechSelectBean> result = interestRemoteBean.queryTechSelectBean(tempTechSelectBeanCondition, pageObject.clone());
		techSelectBeanQueryConversation.setListTechSelectBean(result.getResultData());
		
		// set paging object
		pageObject.setTotalRecordsNumber(result.getPageObject().getTotalRecordsNumber());
		pageObject.setCurrentPageNumber(pageObject.getOffset() / (int)pageObject.getMaxRecordsPerPage() + 1);
		pageObject.setMaxPageNumber((int)pageObject.getTotalRecordsNumber() / pageObject.getMaxRecordsPerPage() + ((int)pageObject.getTotalRecordsNumber() % pageObject.getMaxRecordsPerPage() == 0 ? 0 : 1));

	}
}
