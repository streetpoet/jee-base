package impl.interest;

import interfaces.interest.IInterest;

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
	
	private static final String ADD_INTERESTING_TECH_SQL = "insert into f3_user_selection(user_id, class_id, create_datetime) "
														+"  select ?, ?, now() from dual"
														+"  where not exists (select id from f3_user_selection where user_id = ? and class_id = ?);";

	@Override
	public boolean createTechSelectBean(TechSelectBean techSelectBean) {
		Object[] params = new Object[]{
				techSelectBean.getUserId(),
				techSelectBean.getTechClassifyId(),
				techSelectBean.getUserId(),
				techSelectBean.getTechClassifyId(),
		};
		return helper.doDMLOperation(ADD_INTERESTING_TECH_SQL, params) == 1;
	}

	@Override
	public int deleteTechSelectBean(TechSelectBean techSelectBean) {
		// TODO Auto-generated method stub
		return 0;
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

}
