package impl.matrix;

import interfaces.matrix.IMatrix;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.spstudio.love.matrix.entity.NsvProject;
import com.spstudio.love.matrix.helper.NsvProjectCondition;
import com.spstudio.love.matrix.helper.NsvProjectQueryResult;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.helper.DatabaseHelper;
import com.spstudio.love.system.interfaces.IQueryResult;

@Stateless
public class MatrixBean implements IMatrix {

	@Inject DatabaseHelper helper;
	
	@Override
	public boolean createNsvProject(NsvProject nsvProject) {
		Object[] params = new Object[]{
			//TODO: Add parameters here.
		};
		return helper.doDMLOperation("", params) == 1; //TODO: Replace "" with proper sql.
	}
	
	@Override
	public int deleteNsvProject(NsvProject nsvProject) {
		Object[] params = new Object[]{
			//TODO: Add parameters here.
		};
		return helper.doDMLOperation("", params); //TODO: Replace "" with proper sql.
	}
	
	@Override
	public int updateNsvProject(NsvProject nsvProject) {
		Object[] params = new Object[]{
			//TODO: Add parameters here.
		};
		return helper.doDMLOperation("", params); //TODO: Replace "" with proper sql.
	}

	@Override
	public IQueryResult<NsvProject> queryNsvProject(NsvProjectCondition condition,
			PageObject pageObject) {
		IQueryResult<NsvProject> returnResult = new NsvProjectQueryResult();
		
		// query total number
		List<Object[]> result = helper.doQuery("", null); //TODO: Replace "" with proper sql.
		if (result != null && result.size() != 0){
			pageObject.setTotalRecordsNumber(0);
		}
		((NsvProjectQueryResult)returnResult).setPageObject(pageObject);
		
		// query paging data
		Object[] params = new Object[] {
				pageObject.getOffset(),
				pageObject.getMaxRecordsPerPage()
			};
		result = helper.doQuery("", params); //TODO: Replace "" with proper sql.
		List<NsvProject> listNsvProject = new ArrayList<NsvProject>();
		if (result != null && result.size() != 0){
			NsvProject tempNsvProject = new NsvProject();
			for(Object[] row: result){
				NsvProject nsvProject = tempNsvProject.clone();
//				nsvProject.setId((Integer)row[0]);
				listNsvProject.add(nsvProject);
			}
		}
		((NsvProjectQueryResult)returnResult).setListNsvProject(listNsvProject);
		
		return returnResult;
	}
	
	/**
	 * Return NsvProject Object
	 */
	@Override
	public NsvProject loadNsvProject(int nsvProjectId) {
		Object[] params = new Object[]{nsvProjectId};
		List<Object[]> result = helper.doQuery("", params);
		if (result != null && result.size() != 0){
			Object[] row = result.get(0);
			NsvProject nsvProject = new NsvProject();
//			nsvProject.setId((Integer)row[0]);
			return nsvProject;
		}
		return null;
	}
}
