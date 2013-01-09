package impl;

import interfaces.IProduct;
import interfaces.IQueryResult;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.spstudio.love.web.entity.product.Product;
import com.spstudio.love.web.entity.product.ProductCondition;
import com.spstudio.love.web.entity.system.PageObject;
import com.spstudio.love.web.helper.DatabaseHelper;

@Stateless
public class ProductBean implements IProduct {

	@Inject DatabaseHelper helper;
	
	private static final String ADD_PRODUCT_SQL = "insert into f1_products" +
			"(classifyid, familyId, forUserId, productName, price, buyDate, warrantyEndDate, description) " +
			"values(?, ?, ?, ?, ?, ?, ?, ?)";
	
	
	@Override
	public boolean addProduct(Product product) {
		Object[] params = new Object[]{
			product.getClassifyId(),
			product.getFamilyId(),
			product.getForUserId(),
			product.getProductName(),
			product.getPrice(),
			product.getBuyDate(),
			product.getWarrantyEndDate(),
			product.getDescription()
		};
		return helper.doDMLOperation(ADD_PRODUCT_SQL, params);
	}


	@Override
	public IQueryResult<Product> queryProducts(ProductCondition condition,
			PageObject pageObject) {
		
		return null;
	}


}
