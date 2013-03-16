package impl.matrix;

import interfaces.matrix.IMatrix;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.spstudio.love.system.helper.DatabaseHelper;

@Stateless
public class MatrixBean implements IMatrix {

	@Inject DatabaseHelper helper;
	
	private static final String SQL = "insert into table debug values(?)";
	
	@Override
	public String debug(String debug){
		Object[] params = new Object[]{
			"debug"
		};
		helper.doDMLOperation(SQL, params);
		return "success";
	}

}
