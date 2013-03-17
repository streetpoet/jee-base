package com.spstudio.love.matrix.producer;

import interfaces.matrix.IMatrix;

import javax.ejb.EJB;
import javax.enterprise.inject.Produces;
import javax.naming.NamingException;

import com.spstudio.love.matrix.qualifier.MatrixRemoteBean;
import com.spstudio.love.system.LoveDaemon;

public class MatrixRemoteBeanProducer {
	
	@EJB
	LoveDaemon loveDaemon;
	
	@Produces
	@MatrixRemoteBean
	IMatrix produce(){
		IMatrix matrixs = null;
 		try {
 			matrixs = (IMatrix)loveDaemon.getInitialContext().lookup("MatrixBean/remote");
 		} catch (NamingException e) {
			e.printStackTrace();
		}
 		return matrixs;
	}
}
