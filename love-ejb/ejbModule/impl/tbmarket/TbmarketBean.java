package impl.tbmarket;

import interfaces.tbmarket.ITbmarket;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.spstudio.love.tbmarket.entity.TbAcountBean;
import com.spstudio.love.tbmarket.helper.TbAcountBeanCondition;
import com.spstudio.love.tbmarket.helper.TbAcountBeanQueryResult;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.helper.DatabaseHelper;
import com.spstudio.love.system.interfaces.IQueryResult;

@Stateless
public class TbmarketBean implements ITbmarket {

	@Inject DatabaseHelper helper;
	@Resource EJBContext context;
	
	@Override
	public boolean createTbAcountBean(TbAcountBean tbAcountBean) {
		Object[] params = new Object[]{
			//TODO: Add parameters here.
		};
		return helper.doDMLOperation("", params) == 1; //TODO: Replace "" with proper sql.
	}
	
	@Override
	public int deleteTbAcountBean(TbAcountBean tbAcountBean) {
		Object[] params = new Object[]{
			//TODO: Add parameters here.
		};
		return helper.doDMLOperation("", params); //TODO: Replace "" with proper sql.
	}
	
	@Override
	public int updateTbAcountBean(TbAcountBean tbAcountBean) {
		Object[] params = new Object[]{
			//TODO: Add parameters here.
		};
		return helper.doDMLOperation("", params); //TODO: Replace "" with proper sql.
	}

	@Override
	public IQueryResult<TbAcountBean> queryTbAcountBean(TbAcountBeanCondition condition,
			PageObject pageObject) {
		IQueryResult<TbAcountBean> returnResult = new TbAcountBeanQueryResult();
		
		// query total number
		List<Object[]> result = helper.doQuery("", null); //TODO: Replace "" with proper sql.
		if (result != null && result.size() != 0){
			pageObject.setTotalRecordsNumber(0);
		}
		((TbAcountBeanQueryResult)returnResult).setPageObject(pageObject);
		
		// query paging data
		Object[] params = new Object[] {
				pageObject.getOffset(),
				pageObject.getMaxRecordsPerPage()
			};
		result = helper.doQuery("", params); //TODO: Replace "" with proper sql.
		List<TbAcountBean> listTbAcountBean = new ArrayList<TbAcountBean>();
		if (result != null && result.size() != 0){
			TbAcountBean tempTbAcountBean = new TbAcountBean();
			for(Object[] row: result){
				TbAcountBean tbAcountBean = tempTbAcountBean.clone();
//				tbAcountBean.setId((Integer)row[0]);
				listTbAcountBean.add(tbAcountBean);
			}
		}
		((TbAcountBeanQueryResult)returnResult).setListTbAcountBean(listTbAcountBean);
		
		return returnResult;
	}
	
	/**
	 * Return TbAcountBean Object
	 */
	@Override
	public TbAcountBean loadTbAcountBean(int tbAcountBeanId) {
		Object[] params = new Object[]{tbAcountBeanId};
		List<Object[]> result = helper.doQuery("", params);
		if (result != null && result.size() != 0){
			Object[] row = result.get(0);
			TbAcountBean tbAcountBean = new TbAcountBean();
//			tbAcountBean.setId((Integer)row[0]);
			return tbAcountBean;
		}
		return null;
	}
}
