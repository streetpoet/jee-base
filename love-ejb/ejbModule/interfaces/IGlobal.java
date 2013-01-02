package interfaces;

import javax.ejb.Remote;

@Remote
public interface IGlobal {
	public String getSysInfo();
}
