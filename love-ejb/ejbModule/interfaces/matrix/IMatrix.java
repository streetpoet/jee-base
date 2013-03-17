package interfaces.matrix;

import javax.ejb.Remote;

import com.spstudio.love.matrix.entity.NsvProject;
import com.spstudio.love.matrix.helper.NsvProjectCondition;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.interfaces.IQueryResult;

@Remote
public interface IMatrix {
	
	public boolean createNsvProject(NsvProject nsvProject);
	
	public int deleteNsvProject(NsvProject nsvProject);
	
	public int updateNsvProject(NsvProject nsvProject);

	public IQueryResult<NsvProject> queryNsvProject(NsvProjectCondition condition, PageObject pageObject);
	
	public NsvProject loadNsvProject(int nsvProjectId);

}