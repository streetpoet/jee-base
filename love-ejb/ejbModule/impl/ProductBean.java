package impl;

import interfaces.IProduct;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.spstudio.love.web.entity.product.Product;
import com.spstudio.love.web.helper.DatabaseHelper;

@Stateless
public class ProductBean implements IProduct {

	@Inject DatabaseHelper helper;
	
	private static final String ADD_PRODUCT_SQL = "insert into f1_products" +
			"(classifyid, familyId, forUserId, productName, price, buyDate, warrantyEndDate, description) " +
			"values(?, ?, ?, ?, ?, ?, ?, ?)";
	
	
	@Override
	public boolean addProduct(Product p) {
		Object[] params = new Object[]{
			p.getClassifyId(),
			p.getFamilyId(),
			p.getForUserId(),
			p.getProductName(),
			p.getPrice(),
			p.getBuyDate(),
			p.getWarrantyEndDate(),
			p.getDescription()
		};
		return helper.doDMLOperation(ADD_PRODUCT_SQL, params);
	}

}
