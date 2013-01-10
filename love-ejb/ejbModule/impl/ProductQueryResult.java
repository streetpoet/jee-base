package impl;

import interfaces.IQueryResult;

import java.util.List;

import com.spstudio.love.product.entity.Product;
import com.spstudio.love.system.entity.PageObject;

public class ProductQueryResult implements IQueryResult<Product> {

	private List<Product> listProduct;
	private PageObject pageObject;
	
	@Override
	public List<Product> getResultData() {
		return listProduct;
	}

	@Override
	public PageObject getPageObject() {
		return pageObject;
	}

	public void setListProduct(List<Product> listProduct) {
		this.listProduct = listProduct;
	}

	public void setPageObject(PageObject pageObject) {
		this.pageObject = pageObject;
	}

}