package interfaces.sample;

import javax.ejb.Remote;

import com.spstudio.love.sample.entity.Entity;
import com.spstudio.love.sample.helper.EntityCondition;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.interfaces.IQueryResult;

@Remote
public interface ISample {
	
	public boolean createEntity(Entity entity);
	
	public int deleteEntity(Entity entity);
	
	public int updateEntity(Entity entity);

	public IQueryResult<Entity> queryEntity(EntityCondition condition, PageObject pageObject);
	
	public Entity loadEntity(int entityId);

}