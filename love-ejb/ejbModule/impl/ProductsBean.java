package impl;

import interfaces.IProducts;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote
public class ProductsBean implements IProducts {

	@PostConstruct
	public void postConstruct(){
		System.out.println("ProductsBean#postConstruct");
	}
	
	@PreDestroy
	public void preDestroy(){
		System.out.println("ProductsBean#preDestroy");
	}
	
	@Override
	public void queryProducts(int kindId, int maxCount) {
		System.out.println("print nothing");
	}

}
