package interfaces.matrix;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import com.spstudio.love.matrix.entity.MatrixProject;

@Remote
public interface IMatrixSingleton {
	
	public List<MatrixProject> retrieveProjectList();
	public void refreshProjectCache();
	public Map<String, List<String>> retrieveGenerationConfiguration();
}
