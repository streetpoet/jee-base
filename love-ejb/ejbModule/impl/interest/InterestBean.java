package impl.interest;

import interfaces.interest.IInterest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.spstudio.love.interest.entity.TechSelectBean;
import com.spstudio.love.interest.helper.TechSelectBeanCondition;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.helper.DatabaseHelper;
import com.spstudio.love.system.interfaces.IQueryResult;

@Stateless
public class InterestBean implements IInterest {

	@Inject DatabaseHelper helper;
	
	private static final String QUERY_LIKED_TECH_LIST_SQL =  "SELECT "
			+"    classify_id "
			+"FROM "
			+"    f3_user_selection us "
			+"where "
			+"    user_id = ? and selected_flag = 1";

	private static final String CREATE_OR_UPDATE_INTEREST_TECH_SQL =  "INSERT INTO "
			+"f3_user_selection(id, user_id, classify_id, selected_flag, create_datetime, update_datetime) "
			+"VALUES( "
			+"ifnull((select b.id from f3_user_selection b where b.user_id = ? and b.classify_id = ?), (select max(c.id) + 1 from f3_user_selection c)),  "
			+"?,  "
			+"?,  "
			+"?, "
			+"now(), "
			+"now())  "
			+"ON DUPLICATE KEY UPDATE selected_flag = ?, update_datetime = now()";

	
	@Override
	public boolean createTechSelectBean(TechSelectBean techSelectBean) {
		Object[] params = new Object[]{
				techSelectBean.getUserId(),
				techSelectBean.getTechClassifyId(),
				techSelectBean.getUserId(),
				techSelectBean.getTechClassifyId(),
				1,
				1
		};
		return helper.doDMLOperation(CREATE_OR_UPDATE_INTEREST_TECH_SQL, params) == 1;
	}

	@Override
	public int deleteTechSelectBean(TechSelectBean techSelectBean) {
		Object[] params = new Object[]{
				techSelectBean.getUserId(), 
				techSelectBean.getTechClassifyId(),
				techSelectBean.getUserId(),
				techSelectBean.getTechClassifyId(),
				0,
				0
				};
		return helper.doDMLOperation(CREATE_OR_UPDATE_INTEREST_TECH_SQL, params);
	}

	@Override
	public int updateTechSelectBean(TechSelectBean techSelectBean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IQueryResult<TechSelectBean> queryTechSelectBean(
			TechSelectBeanCondition condition, PageObject pageObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TechSelectBean loadTechSelectBean(int TechSelectBeanId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TechSelectBean> loadLikedTechSelectBean(int userId) {
		Object[] params = new Object[]{userId};
		List<Object[]> listResult = helper.doQuery(QUERY_LIKED_TECH_LIST_SQL, params);
		if (listResult != null){
			List<TechSelectBean> listReturn = new ArrayList<TechSelectBean>();
			for (Object[] row: listResult){
				TechSelectBean bean = new TechSelectBean();
				bean.setTechClassifyId((Integer)row[0]);
				listReturn.add(bean);
			}
			return listReturn;
		}
		return null;
	}

}
