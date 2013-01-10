package interfaces;

import java.util.List;

import com.spstudio.love.system.entity.PageObject;

public interface IQueryResult<K> {
	public List<K> getResultData();
	public PageObject getPageObject();
}
