package interfaces.tbmarket;

import java.util.List;
import javax.ejb.Remote;
import com.spstudio.love.tbmarket.entity.TbAcountTypeBean;

@Remote
public interface ITbmarketSingleton {
	
	public List<TbAcountTypeBean> retrieveTbAcountTypeBeanList();
	
}
