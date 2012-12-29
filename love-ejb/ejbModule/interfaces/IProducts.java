package interfaces;

import javax.ejb.Remote;

@Remote
public interface IProducts {
	public void queryProducts(int kindId, int maxCount);
}
