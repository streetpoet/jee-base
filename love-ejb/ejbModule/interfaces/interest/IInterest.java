package interfaces.interest;

import java.util.List;

import javax.ejb.Remote;

import com.spstudio.love.interest.entity.TechSelectBean;
import com.spstudio.love.interest.helper.TechSelectBeanCondition;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.interfaces.IQueryResult;

@Remote
public interface IInterest {
	
	public boolean createTechSelectBean(TechSelectBean techSelectBean);
	
	public int deleteTechSelectBean(TechSelectBean techSelectBean);
	
	public int updateTechSelectBean(TechSelectBean techSelectBean);

	public IQueryResult<TechSelectBean> queryTechSelectBean(TechSelectBeanCondition condition, PageObject pageObject);
	
	public TechSelectBean loadTechSelectBean(int TechSelectBeanId);
	
	public List<TechSelectBean> loadLikedTechSelectBean(int userId);

}