package interfaces.interest;

import java.util.List;
import javax.ejb.Remote;
import com.spstudio.love.interest.entity.TechTypeBean;

@Remote
public interface IInterestSingleton {
	
	public List<TechTypeBean> retrieveTechTypeBeanList();
	
}
