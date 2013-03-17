package interfaces.matrix;

import javax.ejb.Remote;

import com.spstudio.love.matrix.entity.MatrixProject;
import com.spstudio.love.matrix.helper.MatrixProjectCondition;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.interfaces.IQueryResult;

@Remote
public interface IMatrix {
	
	public boolean createMatrixProject(MatrixProject matrixProject);
	
	public int deleteMatrixProject(MatrixProject matrixProject);
	
	public int updateMatrixProject(MatrixProject matrixProject);

	public IQueryResult<MatrixProject> queryMatrixProject(MatrixProjectCondition condition, PageObject pageObject);
	
	public MatrixProject loadMatrixProject(int matrixProjectId);

}