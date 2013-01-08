package interfaces;

import javax.ejb.Remote;

import com.spstudio.love.web.entity.product.Product;

@Remote
public interface IProduct {
	public boolean addProduct(Product p);
}
