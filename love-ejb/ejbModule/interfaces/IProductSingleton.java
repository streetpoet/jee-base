package interfaces;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface IProductSingleton {
	public static final int INDEX_CLASSIFY_ID = 0;
	public static final int INDEX_CLASSIFY_NAME = 1;
	
	public List<String[]> retrieveProductClassify();
}
