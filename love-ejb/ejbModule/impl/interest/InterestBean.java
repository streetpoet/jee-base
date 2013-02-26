package impl.interest;

import interfaces.interest.IInterest;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.spstudio.love.system.helper.DatabaseHelper;

@Stateless
public class InterestBean implements IInterest {

	@Inject DatabaseHelper helper;
	
	private static final String ADD_INTERESTING_TECH_SQL = "insert into f3_user_selection(user_id, class_id, create_datetime) "
														+"  select ?, ?, now() from dual"
														+"  where not exists (select id from f3_user_selection where user_id = ? and class_id = ?);";
	
	@Override
	public boolean addInterest(int userId, int classifyId) {
		Object[] params = new Object[]{
				userId,
				classifyId,
				userId,
				classifyId
		};
		return helper.doDMLOperation(ADD_INTERESTING_TECH_SQL, params) == 1;
	}

}
