package interfaces.tbmarket;

import java.util.List;

import javax.ejb.Remote;

import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.interfaces.IQueryResult;
import com.spstudio.love.tbmarket.entity.TbAcountBean;
import com.spstudio.love.tbmarket.entity.TrafficInfoBean;
import com.spstudio.love.tbmarket.helper.TbAcountBeanCondition;

@Remote
public interface ITbmarket {
	
	public boolean createTbAcountBean(TbAcountBean tbAcountBean);
	
	public int deleteTbAcountBean(TbAcountBean tbAcountBean);
	
	public int updateTbAcountBean(TbAcountBean tbAcountBean);

	public IQueryResult<TbAcountBean> queryTbAcountBean(TbAcountBeanCondition condition, PageObject pageObject);
	
	public TbAcountBean loadTbAcountBean(int tbAcountBeanId);

	public List<TrafficInfoBean> getTrafficRequestInfo();
}