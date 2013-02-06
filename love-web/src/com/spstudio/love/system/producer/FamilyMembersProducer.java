package com.spstudio.love.system.producer;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import com.spstudio.love.system.bean.UserInfo;
import com.spstudio.love.system.helper.DatabaseHelper;
import com.spstudio.love.system.qualifier.FamilyMembers;
import com.spstudio.love.system.qualifier.UserInfoQualifier;

public class FamilyMembersProducer {
	
	@Inject DatabaseHelper helper;
	@Inject @UserInfoQualifier UserInfo userInfo;
	
	private String QUERY_SQL = "select u.id, u.nickName " +
			"from users u, userFamilyRef r " +
			"where r.userId = u.id and r.familyId = " +
			"(select ra.familyId from userFamilyRef ra where ra.userId = ?)";
	
	@Produces
	@FamilyMembers
	List<UserInfo> produce(){
		
		Object[] params = new Object[1];
		params[0] = userInfo.getUserId();
		List<Object[]> result = helper.doQuery(QUERY_SQL, params);
		List<UserInfo> returnList = new ArrayList<UserInfo>();
		
		if (result != null && result.size() != 0){
			for (Object[] array: result){
				UserInfo user = new UserInfo();
				user.setUserId((Integer)array[0]);
				user.setNickName((String)array[1]);
				returnList.add(user);
			}
			return returnList;
		}
		return returnList;
	}

}
