package com.spstudio.love.system.tag;

import java.io.IOException;

import javax.faces.context.FacesContext;


public class Html5InputText extends javax.faces.component.html.HtmlInputText {

	@Override
	public String getFamily() {
		System.out.println(super.getFamily());
		return super.getFamily();
	}

	@Override
	public void encodeBegin(FacesContext arg0) throws IOException {
		// TODO Auto-generated method stub
		super.encodeBegin(arg0);
	}

	@Override
	public void encodeChildren(FacesContext arg0) throws IOException {
		// TODO Auto-generated method stub
		super.encodeChildren(arg0);
	}

	@Override
	public void encodeEnd(FacesContext arg0) throws IOException {
		// TODO Auto-generated method stub
		super.encodeEnd(arg0);
	}
	
}
