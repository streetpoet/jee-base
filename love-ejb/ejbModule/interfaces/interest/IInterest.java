package interfaces.interest;

import javax.ejb.Remote;

@Remote
public interface IInterest {
	public boolean addInterest(int userId, int classifyId);
}
