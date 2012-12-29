package impl;

import interfaces.IProducts;

import javax.ejb.Stateless;

@Stateless
public class ProductsBean implements IProducts {

	@Override
	public void queryProducts(int kindId, int maxCount) {
		System.out.println("print nothing");
	}

	public void test(){
		
	}
}
