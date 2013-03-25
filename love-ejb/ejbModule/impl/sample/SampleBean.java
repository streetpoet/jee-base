package impl.sample;

import interfaces.sample.ISample;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.spstudio.love.sample.entity.Entity;
import com.spstudio.love.sample.helper.EntityCondition;
import com.spstudio.love.sample.helper.EntityQueryResult;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.helper.DatabaseHelper;
import com.spstudio.love.system.interfaces.IQueryResult;

@Stateless
public class SampleBean implements ISample {

	@Inject DatabaseHelper helper;
	
	@Override
	public boolean createEntity(Entity entity) {
		Object[] params = new Object[]{
			//TODO: Add parameters here.
		};
		return helper.doDMLOperation("", params) == 1; //TODO: Replace "" with proper sql.
	}
	
	@Override
	public int deleteEntity(Entity entity) {
		Object[] params = new Object[]{
			//TODO: Add parameters here.
		};
		return helper.doDMLOperation("", params); //TODO: Replace "" with proper sql.
	}
	
	@Override
	public int updateEntity(Entity entity) {
		Object[] params = new Object[]{
			//TODO: Add parameters here.
		};
		return helper.doDMLOperation("", params); //TODO: Replace "" with proper sql.
	}

	@Override
	public IQueryResult<Entity> queryEntity(EntityCondition condition,
			PageObject pageObject) {
		IQueryResult<Entity> returnResult = new EntityQueryResult();
		
		// query total number
		List<Object[]> result = helper.doQuery("", null); //TODO: Replace "" with proper sql.
		if (result != null && result.size() != 0){
			pageObject.setTotalRecordsNumber(0);
		}
		((EntityQueryResult)returnResult).setPageObject(pageObject);
		
		// query paging data
		Object[] params = new Object[] {
				pageObject.getOffset(),
				pageObject.getMaxRecordsPerPage()
			};
		result = helper.doQuery("", params); //TODO: Replace "" with proper sql.
		List<Entity> listEntity = new ArrayList<Entity>();
		if (result != null && result.size() != 0){
			Entity tempEntity = new Entity();
			for(Object[] row: result){
				Entity entity = tempEntity.clone();
//				entity.setId((Integer)row[0]);
				listEntity.add(entity);
			}
		}
		((EntityQueryResult)returnResult).setListEntity(listEntity);
		
		return returnResult;
	}
	
	/**
	 * Return Entity Object
	 */
	@Override
	public Entity loadEntity(int entityId) {
		Object[] params = new Object[]{entityId};
		List<Object[]> result = helper.doQuery("", params);
		if (result != null && result.size() != 0){
			Object[] row = result.get(0);
			Entity entity = new Entity();
//			entity.setId((Integer)row[0]);
			return entity;
		}
		return null;
	}
}
