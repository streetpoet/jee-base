package com.spstudio.love.web.producer.common;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import com.spstudio.love.web.helper.DatabaseHelper;
import com.spstudio.love.web.modules.main.UserInfo;
import com.spstudio.love.web.qualifiers.FamilyMembers;

public class FamilyMembersProducer {
	
	@Inject DatabaseHelper helper;
	@Inject UserInfo userInfo;
	
	private String QUERY_SQL = "select u.id, u.nickName " +
			"from users u, userFamilyRef r " +
			"where r.userId = u.id and r.familyId = " +
			"(select ra.familyId from userFamilyRef ra where ra.userId = ?)";
	
	@SuppressWarnings("unused")
	@Produces
	@FamilyMembers
	private List<UserInfo> produce(){
		
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
