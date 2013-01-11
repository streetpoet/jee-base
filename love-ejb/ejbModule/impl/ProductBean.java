package impl;

import interfaces.IProduct;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.spstudio.love.product.entity.Product;
import com.spstudio.love.product.helper.ProductCondition;
import com.spstudio.love.product.helper.ProductQueryResult;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.helper.DatabaseHelper;
import com.spstudio.love.system.interfaces.IQueryResult;

@Stateless
public class ProductBean implements IProduct {

	@Inject DatabaseHelper helper;
	
	private static final String ADD_PRODUCT_SQL = "insert into f1_products" +
			"(classifyid, familyId, forUserId, productName, price, buyDate, warrantyEndDate, description) " +
			"values(?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String QUERY_PRODUCTS_SQL_SUB_1 = "select p.id, c.kindName, u.nickname, productName, price, buyDate, warrantyEndDate, description";
	private static final String QUERY_PRODUCTS_SQL_SUB_2 = "select count(0) as count";
	private static final String QUERY_PRODUCTS_SQL_SUB_3 = " FROM lovedb.f1_products p, f1_classify c, users u where p.classifyId = c.id and p.forUserId = u.id order by buyDate desc";
	private static final String QUERY_PRODUCTS_SQL_SUB_4 = " limit ?, ?";
	
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
		IQueryResult<Product> returnResult = new ProductQueryResult();
		
		// query total number
		List<Object[]> result = helper.doQuery(QUERY_PRODUCTS_SQL_SUB_2 + QUERY_PRODUCTS_SQL_SUB_3, null);
		if (result != null && result.size() != 0){
			pageObject.setTotalRecordsNumber((Long)result.get(0)[0]);
		}
		((ProductQueryResult)returnResult).setPageObject(pageObject);
		
		// query paging product data
		Object[] params = new Object[] {
				pageObject.getOffset(),
				pageObject.getRecordCountPerFetch()
			};
		result = helper.doQuery(QUERY_PRODUCTS_SQL_SUB_1 + QUERY_PRODUCTS_SQL_SUB_3 + QUERY_PRODUCTS_SQL_SUB_4, params);
		List<Product> listProduct = new ArrayList<Product>();
		if (result != null && result.size() != 0){
			Product p = new Product();
			for(Object[] row: result){
				Product product = p.clone();
				product.setProductName((String)row[1]);
				product.setBuyDate((Date)row[5]);
				product.setPrice((Integer)row[4]);
				product.setWarrantyEndDate((Date)row[6]);
				listProduct.add(product);
			}
		}
		((ProductQueryResult)returnResult).setListProduct(listProduct);
		
		return returnResult;
	}


}
