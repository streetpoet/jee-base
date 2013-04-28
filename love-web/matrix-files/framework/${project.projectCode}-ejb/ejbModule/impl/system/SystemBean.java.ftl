package impl.system;

import interfaces.system.ISystem;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.inject.Inject;

import ${project.packageString}.system.bean.UserInfo;
import ${project.packageString}.system.helper.DatabaseHelper;

@Stateless
public class SystemBean implements ISystem {

	@Inject DatabaseHelper helper;
	@Resource EJBContext context;
	
	public static final int ADMIN = 1;
	public static final int MEMBER_ROLE = ADMIN + 1;
	
	public static final int NSV_FAMILY_ID = 2;
	
	private static final String INSERT_USER_SQL = "insert into users(userName, pwd, nickName) values(?, ?, ?)";
	private static final String INSERT_USER_ROLE_REF_SQL = "insert into userRoleRef(userId, roleId) values(?, ?)";
	private static final String INSERt_USER_FAMILY_REF_SQL = "insert into userFamilyRef(userId, familyId) values(?, ?)";
	
	@Override
	public boolean createUser(UserInfo userInfo) {
		Object[] params = new Object[]{userInfo.getLoginId(), userInfo.getPassword(), userInfo.getNickName()};
		int returnKey = helper.doDMLOperationWithGeneratedKeyReturn(INSERT_USER_SQL, params);
		if (returnKey == -1){
			context.setRollbackOnly();
			return false;
		}
		params = new Object[]{returnKey, MEMBER_ROLE};
		int effectRowCount = helper.doDMLOperation(INSERT_USER_ROLE_REF_SQL, params);
		if (effectRowCount != 1){
			context.setRollbackOnly();
			return false;
		}
		
		params = new Object[]{returnKey, NSV_FAMILY_ID};
		effectRowCount = helper.doDMLOperation(INSERt_USER_FAMILY_REF_SQL, params);
		if (effectRowCount != 1){
			context.setRollbackOnly();
			return false;
		}
		
		return true;
	}
}
