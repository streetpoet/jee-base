package com.spstudio.love.matrix.producer;

import interfaces.matrix.IMatrixSingleton;

import javax.ejb.EJB;
import javax.enterprise.inject.Produces;
import javax.naming.NamingException;

import com.spstudio.love.matrix.qualifier.MatrixSingleRemoteBean;
import com.spstudio.love.system.LoveDaemon;

public class MatrixSingleBeanProducer {
	
	@EJB
	LoveDaemon loveDaemon;
	
	@Produces
	@MatrixSingleRemoteBean
	IMatrixSingleton produce(){
		IMatrixSingleton matrixs = null;
 		try {
 			matrixs = (IMatrixSingleton)loveDaemon.getInitialContext().lookup("MatrixSingletonBean/remote");
 		} catch (NamingException e) {
			e.printStackTrace();
		}
 		return matrixs;
	}
}
