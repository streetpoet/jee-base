package interfaces.matrix;

import java.util.List;

import javax.ejb.Remote;

import com.spstudio.love.matrix.entity.MatrixProject;

@Remote
public interface IMatrixSingleton {
	
	public List<MatrixProject> retrieveProjectList();
	
}
