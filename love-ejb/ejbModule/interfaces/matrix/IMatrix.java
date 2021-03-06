package interfaces.matrix;

import java.util.List;

import javax.ejb.Remote;

import com.spstudio.love.matrix.entity.MatrixFunction;
import com.spstudio.love.matrix.entity.MatrixModule;
import com.spstudio.love.matrix.entity.MatrixProject;
import com.spstudio.love.matrix.helper.MatrixProjectCondition;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.interfaces.IQueryResult;

@Remote
public interface IMatrix {
	
	public boolean createMatrixProject(MatrixProject matrixProject);
	
	public boolean createMatrixModule(MatrixProject matrixProject, MatrixModule matrixModule);
	
	public boolean createMatrixFunction(MatrixModule matrixModule, MatrixFunction matrixFunction);
	
	public int deleteMatrixProject(MatrixProject matrixProject);
	
	public int updateMatrixProject(MatrixProject matrixProject);

	public IQueryResult<MatrixProject> queryMatrixProject(MatrixProjectCondition condition, PageObject pageObject);
	
	public MatrixProject loadMatrixProject(int matrixProjectId);
	
	public MatrixModule loadMatrixModule(int matrixModuleId);
	
	public MatrixFunction loadMatrixFunction(int matrixFunctionId);

	public List<MatrixModule> loadMatrixModuleList(int matrixProjectId);
	
	public List<MatrixFunction> loadMatrixFunctionList(int matrixModuleId);
	
	public boolean updateSolution(MatrixProject project, MatrixModule module, MatrixFunction function);
}