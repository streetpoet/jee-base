package impl;

import interfaces.IGlobal;

import java.util.concurrent.TimeUnit;

import javax.ejb.AccessTimeout;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@AccessTimeout(unit = TimeUnit.SECONDS, value = 10)
@Remote
public class GlobalBean implements IGlobal {

	@Override
	@Lock(LockType.READ)
	public String getSysInfo() {
		return "sys info:" + System.currentTimeMillis();
	}

}
