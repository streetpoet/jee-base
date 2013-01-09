package interfaces;

import javax.ejb.Remote;

import com.spstudio.love.web.entity.product.Product;
import com.spstudio.love.web.entity.product.ProductCondition;
import com.spstudio.love.web.entity.system.PageObject;

@Remote
public interface IProduct {
	public boolean addProduct(Product product);
	public IQueryResult<Product> queryProducts(ProductCondition condition, PageObject pageObject);
}
