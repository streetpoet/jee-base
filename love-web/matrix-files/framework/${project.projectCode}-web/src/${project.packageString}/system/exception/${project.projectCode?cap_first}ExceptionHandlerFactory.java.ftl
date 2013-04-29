package ${project.packageString}.system.exception;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

public class ${project.projectCode?cap_first}ExceptionHandlerFactory extends
		ExceptionHandlerFactory {

	private ExceptionHandlerFactory parent;
	
	public ${project.projectCode?cap_first}ExceptionHandlerFactory(ExceptionHandlerFactory parent) {
		this.parent = parent;
	}

	@Override
	public ExceptionHandler getExceptionHandler() {
		ExceptionHandler result = parent.getExceptionHandler();
		return new ${project.projectCode?cap_first}ExceptionHandler(result); 
	}

}
