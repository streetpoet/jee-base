package impl;

import interfaces.IProduct;
import interfaces.IQueryResult;

import java.util.ArrayList;
import java.util.List;

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
	private static final String QUERY_PRODUCTS_SQL_SUB_1 = "select p.id, c.kindName, u.nickname, productName, price, buyDate, warrantyEndDate, description";
	private static final String QUERY_PRODUCTS_SQL_SUB_2 = "select count(0) as count";
	private static final String QUERY_PRODUCTS_SQL_SUB_FROM = " FROM lovedb.f1_products p, f1_classify c, users u where p.classifyId = c.id and p.forUserId = u.id order by buyDate desc limit ?, ?";
	
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
		ProductQueryResult returnResult = new ProductQueryResult();
		Object[] params = new Object[] {
			pageObject.getOffset(),
			pageObject.getMaxRowNumber()
		};
		List<Object[]> result = helper.doQuery(QUERY_PRODUCTS_SQL_SUB_2 + QUERY_PRODUCTS_SQL_SUB_FROM, params);
		List<Product> listProduct = new ArrayList<Product>();
		if (result != null && result.size() != 0){
			for (Object[] row: result){
				Product p = new Product();
				
			}
		}
		returnResult.setListProduct(listProduct);
		return null;
	}


}
