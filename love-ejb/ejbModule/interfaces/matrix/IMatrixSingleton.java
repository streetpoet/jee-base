package interfaces.matrix;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface IMatrixSingleton {
	
	public List<String[]> retrieveNsvProjectArray();
	
}
