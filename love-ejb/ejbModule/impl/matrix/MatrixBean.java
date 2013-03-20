package impl.matrix;

import interfaces.matrix.IMatrix;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

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
}
