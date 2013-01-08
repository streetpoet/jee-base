package com.spstudio.love.web.producer.common;

import java.util.List;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import com.spstudio.love.web.helper.DatabaseHelper;
import com.spstudio.love.web.modules.main.UserInfo;
import com.spstudio.love.web.qualifiers.system.LoveTrace;
import com.spstudio.love.web.qualifiers.system.UserInfoQualifier;

public class UserInfoProducer {
	
	@Inject DatabaseHelper dbHelper;
	@Inject java.security.Principal principal;

	private final String Query_User_Family_Info = 
		"select users.nickName, family.familyName, family.description, users.id, family.id "
		+ " from users, family, userFamilyRef "
		+ " where users.id = userFamilyRef.userId and userFamilyRef.familyId = family.id and users.username = ?";
	
	@SuppressWarnings("unused")
	@Produces
	@UserInfoQualifier
	@LoveTrace
	private UserInfo produceUserInfo(){
		UserInfo userInfo = new UserInfo();
		List<Object[]> list = dbHelper.doQuery(Query_User_Family_Info, new Object[]{principal.getName()});
		if (list != null && list.size() != 0){
			Object[] data = list.get(0);
			userInfo.setNickName((String)data[0]);
			userInfo.setFamilyName((String)data[1]);
			userInfo.setDescription((String)data[2]);
			userInfo.setUserId((Integer)data[3]);
			userInfo.setFamilyId((Integer)data[4]);
		}
		return userInfo;
	}
}
