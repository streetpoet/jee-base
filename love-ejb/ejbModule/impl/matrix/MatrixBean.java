package impl.matrix;

import interfaces.matrix.IMatrix;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.spstudio.love.matrix.entity.MatrixFunction;
import com.spstudio.love.matrix.entity.MatrixModule;
import com.spstudio.love.matrix.entity.MatrixProject;
import com.spstudio.love.matrix.helper.MatrixProjectCondition;
import com.spstudio.love.matrix.helper.MatrixProjectQueryResult;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.helper.DatabaseHelper;
import com.spstudio.love.system.interfaces.IQueryResult;

@Stateless
public class MatrixBean implements IMatrix {

	@Inject DatabaseHelper helper;
	
	@Override
	public boolean createMatrixProject(MatrixProject matrixProject) {
		Object[] params = new Object[]{
			//TODO: Add parameters here.
		};
		return true; //helper.doDMLOperation("", params) == 1; //TODO: Replace "" with proper sql.
	}
	
	@Override
	public int deleteMatrixProject(MatrixProject matrixProject) {
		Object[] params = new Object[]{
			//TODO: Add parameters here.
		};
		return helper.doDMLOperation("", params); //TODO: Replace "" with proper sql.
	}
	
	@Override
	public int updateMatrixProject(MatrixProject matrixProject) {
		Object[] params = new Object[]{
			//TODO: Add parameters here.
		};
		return helper.doDMLOperation("", params); //TODO: Replace "" with proper sql.
	}

	@Override
	public IQueryResult<MatrixProject> queryMatrixProject(MatrixProjectCondition condition,
			PageObject pageObject) {
		IQueryResult<MatrixProject> returnResult = new MatrixProjectQueryResult();
		
		// query total number
		List<Object[]> result = helper.doQuery("", null); //TODO: Replace "" with proper sql.
		if (result != null && result.size() != 0){
			pageObject.setTotalRecordsNumber(0);
		}
		((MatrixProjectQueryResult)returnResult).setPageObject(pageObject);
		
		// query paging data
		Object[] params = new Object[] {
				pageObject.getOffset(),
				pageObject.getMaxRecordsPerPage()
			};
		result = helper.doQuery("", params); //TODO: Replace "" with proper sql.
		List<MatrixProject> listMatrixProject = new ArrayList<MatrixProject>();
		if (result != null && result.size() != 0){
			MatrixProject tempMatrixProject = new MatrixProject();
			for(Object[] row: result){
				MatrixProject matrixProject = tempMatrixProject.clone();
//				matrixProject.setId((Integer)row[0]);
				listMatrixProject.add(matrixProject);
			}
		}
		((MatrixProjectQueryResult)returnResult).setListMatrixProject(listMatrixProject);
		
		return returnResult;
	}
	
	/**
	 * Return MatrixProject Object
	 */
	@Override
	public MatrixProject loadMatrixProject(int matrixProjectId) {
		Object[] params = new Object[]{matrixProjectId};
		List<Object[]> result = helper.doQuery("", params);
		if (result != null && result.size() != 0){
			Object[] row = result.get(0);
			MatrixProject matrixProject = new MatrixProject();
//			matrixProject.setId((Integer)row[0]);
			return matrixProject;
		}
		return null;
	}

	@Override
	public List<MatrixModule> loadMatrixModuleList(int matrixProjectId) {
		String querySQL =    "select  "
							+"    m.id, m.module_name "
							+"from "
							+"    f4_module m, "
							+"    f4_project_module_ref r "
							+"where "
							+"	m.id = r.module_id "
							+"	and r.project_id = ? ";
		Object[] params = new Object[]{matrixProjectId};
		List<Object[]> result = helper.doQuery(querySQL, params);
		if (result != null && result.size() != 0){
			List<MatrixModule> returnList = new ArrayList<MatrixModule>();
			for (Object[] row : result) {
				MatrixModule mm = new MatrixModule();
				mm.setId((Integer)row[0]);
				mm.setModuleName((String)row[1]);
				returnList.add(mm);
			}
			return returnList;
		}
		return null;
	}

	@Override
	public List<MatrixFunction> loadMatrixFunctionList(int matrixModuleId) {
		String querySQL =  "select  "
						+"    func.id, func.function_name "
						+"from "
						+"    f4_function func, "
						+"    f4_module_function_ref ref "
						+"where "
						+"    ref.module_id = ? "
						+"        and ref.function_id = func.id ";
		Object[] params = new Object[]{matrixModuleId};
		List<Object[]> result = helper.doQuery(querySQL, params);
		if (result != null && result.size() != 0){
			List<MatrixFunction> returnList = new ArrayList<MatrixFunction>();
			for (Object[] row : result) {
				MatrixFunction mf = new MatrixFunction();
				mf.setId((Integer)row[0]);
				mf.setFunctionName((String)row[1]);
				returnList.add(mf);
			}
			return returnList;
		}
		return null;
	}

	@Override
	public boolean updateSolution(MatrixProject project, MatrixModule module, MatrixFunction function) {

		return false;
	}
}
