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
	
	private static final String LOAD_PRODUCT_SQL = "select id, classifyid, familyId, forUserId, productName, price, buyDate, warrantyEndDate, description from f1_products where id = ?";
	private static final String UPDATE_PRODUCT_SQL = "update f1_products set classifyid = ?, familyId = ?, forUserId = ?, productName = ?, price = ?, buyDate = ?, warrantyEndDate = ?, description = ? where id = ?";
	
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
		return helper.doDMLOperation(ADD_PRODUCT_SQL, params) == 1;
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
				pageObject.getMaxRecordsPerPage()
			};
		result = helper.doQuery(QUERY_PRODUCTS_SQL_SUB_1 + QUERY_PRODUCTS_SQL_SUB_3 + QUERY_PRODUCTS_SQL_SUB_4, params);
		List<Product> listProduct = new ArrayList<Product>();
		if (result != null && result.size() != 0){
			Product p = new Product();
			for(Object[] row: result){
				Product product = p.clone();
				product.setId((Integer)row[0]);
				product.setClassifyName((String)row[1]);
				product.setForUserName((String)row[2]);
				product.setProductName((String)row[3]);
				product.setPrice((Integer)row[4]);
				product.setBuyDate((Date)row[5]);
				product.setWarrantyEndDate((Date)row[6]);
				product.setDescription((String)row[7]);
				listProduct.add(product);
			}
		}
		((ProductQueryResult)returnResult).setListProduct(listProduct);
		
		return returnResult;
	}
	
	/**
	 * Return Product Object, according to product id
	 */
	@Override
	public Product loadProduct(int productId) {
		Object[] params = new Object[]{productId};
		List<Object[]> result = helper.doQuery(LOAD_PRODUCT_SQL, params);
		if (result != null && result.size() != 0){
			Object[] row = result.get(0);
			Product p = new Product();
			p.setId((Integer)row[0]);
			p.setClassifyId((Integer)row[1]);
			p.setFamilyId((Integer)row[2]);
			p.setForUserId((Integer)row[3]);
			p.setProductName((String)row[4]);
			p.setPrice((Integer)row[5]);
			p.setBuyDate((Date)row[6]);
			p.setWarrantyEndDate((Date)row[7]);
			p.setDescription((String)row[8]);
			return p;
		}
		return null;
	}

	@Override
	public int updateProduct(Product product) {
		Object[] params = new Object[]{
				product.getClassifyId(), 
				product.getFamilyId(),
				product.getForUserId(),
				product.getProductName(),
				product.getPrice(),
				product.getBuyDate(),
				product.getWarrantyEndDate(),
				product.getDescription(),
				product.getId()};
		return helper.doDMLOperation(UPDATE_PRODUCT_SQL, params);
	}


}
