package impl;

import interfaces.IGlobal;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.AccessTimeout;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@AccessTimeout(unit = TimeUnit.SECONDS, value = 10)
public class GlobalBean implements IGlobal {
	
	@PostConstruct
	public void postConstruct(){
		System.out.println("GlobalBean#postConstruct");
	}
	
	@PreDestroy
	public void preDestroy(){
		System.out.println("GlobalBean#postConstruct");
	}
	
	@Override
	@Lock(LockType.READ)
	public String getSysInfo() {
		return "sys info:" + System.currentTimeMillis();
	}

}
