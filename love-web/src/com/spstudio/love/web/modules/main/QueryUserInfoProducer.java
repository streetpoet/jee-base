package com.spstudio.love.web.modules.main;

import java.util.List;

import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

import com.spstudio.love.web.common.DatabaseHelper;
import com.spstudio.love.web.qualifiers.QueryUserInfoQualifier;

public class QueryUserInfoProducer {

	private final String Query_User_Family_Info = 
		" select users.nickName, family.familyName, family.description "
		+ " from users, family, userFamilyRef "
		+ " where users.id = userFamilyRef.userId and userFamilyRef.familyId = family.id and users.username = ?";
	
	@SuppressWarnings("unused")
	@Produces
	@QueryUserInfoQualifier
	private UserInfo produceUserInfo(){
		UserInfo userInfo = new UserInfo();
		String userId = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
		userInfo.setUserId(userId);
		List<Object[]> list = new DatabaseHelper().doQuery(Query_User_Family_Info, new Object[]{userId});
		if (list != null && list.size() != 0){
			Object[] data = list.get(0);
			userInfo.setNickName((String)data[0]);
			userInfo.setFamilyName((String)data[1]);
			userInfo.setDescription((String)data[2]);
		}
		return userInfo;
	}
}
