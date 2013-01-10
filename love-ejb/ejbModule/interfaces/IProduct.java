package interfaces;

import javax.ejb.Remote;

import com.spstudio.love.product.entity.Product;
import com.spstudio.love.product.entity.ProductCondition;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.interfaces.IQueryResult;

@Remote
public interface IProduct {
	public boolean addProduct(Product product);
	public IQueryResult<Product> queryProducts(ProductCondition condition, PageObject pageObject);
}
