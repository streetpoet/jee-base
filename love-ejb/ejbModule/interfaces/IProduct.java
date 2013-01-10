package interfaces;

import javax.ejb.Remote;

import com.spstudio.love.product.entity.IQueryResult;
import com.spstudio.love.product.entity.Product;
import com.spstudio.love.product.entity.ProductCondition;
import com.spstudio.love.system.entity.PageObject;

@Remote
public interface IProduct {
	public boolean addProduct(Product product);
	public IQueryResult<Product> queryProducts(ProductCondition condition, PageObject pageObject);
}
