package impl.matrix;

import interfaces.matrix.IMatrix;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
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
	@Resource EJBContext context;
	
	private static String moduleQuerySQL =    "select  "
			+"    m.id, m.module_label "
			+"from "
			+"    f4_module m, "
			+"    f4_project_module_ref r "
			+"where "
			+"	m.id = r.module_id "
			+"	and r.project_id = ? ";
	
	private static String functionQuerySQL =  "select  "
			+"    func.id, func.function_label "
			+"from "
			+"    f4_function func, "
			+"    f4_module_function_ref ref "
			+"where "
			+"    ref.module_id = ? "
			+"        and ref.function_id = func.id ";
	
	private static final String moduleUpdateSQL =  "update "
			+"	f4_module "
			+"set "
			+"	module_name = ? "
			+"	,entity_bean_name = ?  "
			+"	,select_bean_name = ? "
			+"where "
			+"	id = ? ";
	
	private static String functionUpdateSQL =  "update "
			+"	f4_function "
			+"set "
			+"	function_name = ? "
			+"where "
			+"	id = ? ";
	
	private static final String projectModuleRefInsertSQL = "insert into f4_project_module_ref(project_id, module_id) values(?, (select id from f4_module where module_label = ?))";

	private static String moduleInsertSQL = "insert into f4_module(module_label, module_name, entity_bean_name, select_bean_name) values(?, '', '', '')";

	private static final String functionInsertSQL = "insert into f4_function(function_label, function_name) values(?, '')";
	
	private static final String moduleFunctionRefInsertSQL = "insert into f4_module_function_ref(module_id, function_id) values(?, (select id from f4_function where function_label = ?))";

	private static final String loadOneModuleSQL =  "select  "
													+"    id, "
													+"    module_label, "
													+"    module_name, "
													+"    entity_bean_name, "
													+"    select_bean_name "
													+"from "
													+"    f4_module "
													+"where "
													+"    id = ? ";
	
	private static final String loadOneFunctionSQL =  "select  "
			+"    id, "
			+"    function_label, "
			+"    function_name "
			+"from "
			+"    f4_function "
			+"where "
			+"    id = ? ";

	private static final String loadOneProjectSQL =  "SELECT  "
			+"    id, project_name, project_code, project_package "
			+"FROM "
			+"    lovedb.f4_project "
			+"where "
			+"    id = ? ";
	
	@Override
	public boolean createMatrixProject(MatrixProject matrixProject) {
		Object[] params = new Object[]{
			matrixProject.getProjectName()
		};
		int effectRowCount = helper.doDMLOperation("insert into f4_project(project_name) values(?)", params);
		return effectRowCount == 1;
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
		List<Object[]> result = helper.doQuery(loadOneProjectSQL, params);
		if (result != null && result.size() != 0){
			Object[] row = result.get(0);
			MatrixProject matrixProject = new MatrixProject();
			matrixProject.setId((Integer)row[0]);
			matrixProject.setProjectName((String)row[1]);
			matrixProject.setProjectCode((String)row[2]);
			matrixProject.setPackageString((String)row[3]);
			return matrixProject;
		}
		return null;
	}

	@Override
	public List<MatrixModule> loadMatrixModuleList(int matrixProjectId) {
		Object[] params = new Object[]{matrixProjectId};
		List<Object[]> result = helper.doQuery(moduleQuerySQL, params);
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
		Object[] params = new Object[]{matrixModuleId};
		List<Object[]> result = helper.doQuery(functionQuerySQL, params);
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

		Object[] params = new Object[]{module.getModuleName(), module.getEntityBeanName(), module.getSelectBeanName(), module.getId()};
		int effectRowCount = helper.doDMLOperation(moduleUpdateSQL, params);
		if (effectRowCount != 1){
			context.setRollbackOnly();
			return false;
		}
		
		params = new Object[]{function.getFunctionName(), function.getId()};
		effectRowCount = helper.doDMLOperation(functionUpdateSQL, params);
		if (effectRowCount != 1){
			context.setRollbackOnly();
			return false;
		}
		return true;
	}

	@Override
	public boolean createMatrixModule(MatrixProject matrixProject, MatrixModule matrixModule) {
		
		Object[] params = new Object[]{matrixModule.getModuleName()};
		int effectRowCount = helper.doDMLOperation(moduleInsertSQL, params);
		if (effectRowCount != 1){
			context.setRollbackOnly();
			return false;
		}
		
		params = new Object[]{matrixProject.getId(), matrixModule.getModuleName()};
		effectRowCount = helper.doDMLOperation(projectModuleRefInsertSQL, params);
		if (effectRowCount != 1){
			context.setRollbackOnly();
			return false;
		}
		return true;
	}

	@Override
	public boolean createMatrixFunction(MatrixModule matrixModule, MatrixFunction matrixFunction) {
		
		Object[] params = new Object[]{matrixFunction.getFunctionName()};
		int effectRowCount = helper.doDMLOperation(functionInsertSQL, params);
		if (effectRowCount != 1){
			context.setRollbackOnly();
			return false;
		}
		
		params = new Object[]{matrixModule.getId(), matrixFunction.getFunctionName()};
		effectRowCount = helper.doDMLOperation(moduleFunctionRefInsertSQL, params);
		if (effectRowCount != 1){
			context.setRollbackOnly();
			return false;
		}
		
		return true;
	}

	@Override
	public MatrixModule loadMatrixModule(int matrixModuleId) {
		Object[] params = new Object[]{matrixModuleId};
		List<Object[]> result = helper.doQuery(loadOneModuleSQL, params);
		
		if (result != null && result.size() != 0){
			for (Object[] row : result) {
				MatrixModule module = new MatrixModule();
				module.setId((Integer)row[0]);
				module.setModuleLabel((String)row[1]);
				module.setModuleName((String)row[2]);
				module.setEntityBeanName((String)row[3]);
				module.setSelectBeanName((String)row[4]);
				return module;
			}
		}
		
		return null;
	}

	@Override
	public MatrixFunction loadMatrixFunction(int matrixFunctionId) {
		
		Object[] params = new Object[]{matrixFunctionId};
		List<Object[]> result = helper.doQuery(loadOneFunctionSQL, params);
		
		if (result != null && result.size() != 0){
			for (Object[] row : result) {
				MatrixFunction function = new MatrixFunction();
				function.setId((Integer)row[0]);
				function.setFunctionLabel((String)row[1]);
				function.setFunctionName((String)row[2]);
				return function;
			}
		}
		
		return null;
	}
}
