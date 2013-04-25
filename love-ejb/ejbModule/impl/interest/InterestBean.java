package impl.interest;

import interfaces.interest.IInterest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.spstudio.love.interest.entity.ChartClassifyPercentageBean;
import com.spstudio.love.interest.entity.ClassifyStatBean;
import com.spstudio.love.interest.entity.MemberStatBean;
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
	
	private static final String QUERY_CLASSIFY_STAT_TECH_LIST_SQL =  "select  "
			+"    us.classify_id, c1.class_name, us.user_id, u.nickname "
			+"from "
			+"    f3_user_selection us, "
			+"    f3_class_1 c1, "
			+"    users u "
			+"where "
			+"    us.selected_flag = 1 "
			+"        and c1.id = us.classify_id "
			+"        and u.id = us.user_id "
			+"order by us.classify_id asc , us.update_datetime asc ";
	
	private static final String QUERY_MEMBER_STAT_TECH_LIST_SQL =  "select  "
			+"    u.nickname, c1.class_name "
			+"from "
			+"    f3_class_1 c1, "
			+"    f3_user_selection us, "
			+"    users u "
			+"where "
			+"    u.id = us.user_id "
			+"        and us.selected_flag = 1 "
			+"        and c1.id = us.classify_id "
			+"order by u.nickname asc , c1.class_name asc ";
	
	private static final String QUERY_CHART_CLASSIFY_PERCENTAGE_SQL =  "select  "
			+"    c1.class_name, count(u.id) "
			+"from "
			+"    f3_class_1 c1, "
			+"    f3_user_selection us, "
			+"    users u "
			+"where "
			+"    u.id = us.user_id "
			+"        and us.selected_flag = 1 "
			+"        and c1.id = us.classify_id "
			+"group by c1.class_name "
			+"order by c1.class_name asc ";

	
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

	@Override
	public List<ClassifyStatBean> loadClassifyStatBean() {
		List<Object[]> listResult = helper.doQuery(QUERY_CLASSIFY_STAT_TECH_LIST_SQL, null);
		if (listResult != null){
			List<ClassifyStatBean> returnList = new ArrayList<ClassifyStatBean>();
			ClassifyStatBean bean = null;
			for (Object[] row: listResult){
				if (bean == null || (Integer)row[0] != bean.getTechClassifyId()){
					if (bean != null){
						returnList.add(bean);
					}
					bean = new ClassifyStatBean();
				}
				bean.setTechClassifyId((Integer)row[0]);
				bean.setTechClassifyName((String)row[1]);
				if (bean.getSelectedUserNameList() == null){
					bean.setSelectedUserNameList(new ArrayList<String>());
				}
				bean.getSelectedUserNameList().add((String)row[3]);
			}
			returnList.add(bean);
			return returnList;
		}
		return null;
	}

	@Override
	public List<MemberStatBean> loadMemberStatBean() {
		List<Object[]> listResult = helper.doQuery(QUERY_MEMBER_STAT_TECH_LIST_SQL, null);
		if (listResult != null){
			List<MemberStatBean> returnList = new ArrayList<MemberStatBean>();
			MemberStatBean bean = null;
			for (Object[] row: listResult){
				if (bean == null || !bean.getNickName().equals((String)row[0])){
					if (bean != null){
						returnList.add(bean);
					}
					bean = new MemberStatBean();
				}
				bean.setNickName((String)row[0]);
				if (bean.getTechClassifyName() == null){
					bean.setTechClassifyName(new ArrayList<String>());
				}
				bean.getTechClassifyName().add((String)row[1]);
			}
			returnList.add(bean);
			return returnList;
		}
		return null;
	}

	@Override
	public List<ChartClassifyPercentageBean> loadClassifyPercentageBean() {
		List<Object[]> listResult = helper.doQuery(QUERY_CHART_CLASSIFY_PERCENTAGE_SQL, null);
		if (listResult != null){
			List<ChartClassifyPercentageBean> returnList = new ArrayList<ChartClassifyPercentageBean>();
			ChartClassifyPercentageBean bean = null;
			for (Object[] row: listResult){
				if (bean == null || !bean.getClassifyName().equals((String)row[0])){
					if (bean != null){
						returnList.add(bean);
					}
					bean = new ChartClassifyPercentageBean();
				}
				bean.setClassifyName((String)row[0]);
				bean.setSelectedUserCount((Long)row[1]);
			}
			returnList.add(bean);
			return returnList;
		}
		return null;
	}

}
