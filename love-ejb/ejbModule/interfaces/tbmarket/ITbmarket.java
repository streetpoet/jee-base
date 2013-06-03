package interfaces.tbmarket;

import javax.ejb.Remote;

import com.spstudio.love.tbmarket.entity.TbAcountBean;
import com.spstudio.love.tbmarket.helper.TbAcountBeanCondition;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.interfaces.IQueryResult;

@Remote
public interface ITbmarket {
	
	public boolean createTbAcountBean(TbAcountBean tbAcountBean);
	
	public int deleteTbAcountBean(TbAcountBean tbAcountBean);
	
	public int updateTbAcountBean(TbAcountBean tbAcountBean);

	public IQueryResult<TbAcountBean> queryTbAcountBean(TbAcountBeanCondition condition, PageObject pageObject);
	
	public TbAcountBean loadTbAcountBean(int tbAcountBeanId);

}