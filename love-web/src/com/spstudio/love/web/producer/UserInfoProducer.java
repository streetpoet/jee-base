package com.spstudio.love.web.producer;

import java.util.List;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import com.spstudio.love.web.helper.DatabaseHelper;
import com.spstudio.love.web.modules.main.UserInfo;
import com.spstudio.love.web.qualifiers.LoveTrace;
import com.spstudio.love.web.qualifiers.UserInfoQualifier;

public class UserInfoProducer {
	
	@Inject
	private DatabaseHelper dbHelper;
	
	@Inject
	private java.security.Principal principal;

	private final String Query_User_Family_Info = 
		"select users.nickName, family.familyName, family.description "
		+ " from users, family, userFamilyRef "
		+ " where users.id = userFamilyRef.userId and userFamilyRef.familyId = family.id and users.username = ?";
	
	@SuppressWarnings("unused")
	@Produces
	@UserInfoQualifier
	@LoveTrace
	private UserInfo produceUserInfo(){
		UserInfo userInfo = new UserInfo();
		userInfo.setUserId(principal.getName());
		List<Object[]> list = dbHelper.doQuery(Query_User_Family_Info, new Object[]{principal.getName()});
		if (list != null && list.size() != 0){
			Object[] data = list.get(0);
			userInfo.setNickName((String)data[0]);
			userInfo.setFamilyName((String)data[1]);
			userInfo.setDescription((String)data[2]);
		}
		return userInfo;
	}
}
