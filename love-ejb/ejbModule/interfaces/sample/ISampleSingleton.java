package interfaces.sample;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface ISampleSingleton {
	
	public List<String[]> retrieveSampleTypeList();
	
}
