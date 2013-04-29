package ${project.packageString}.system.interceptor;

import java.io.Serializable;
import java.lang.reflect.Method;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;

import org.jboss.logging.Logger;

import ${project.packageString}.system.qualifier.${project.projectCode?cap_first}Trace;

@Interceptor
@${project.projectCode?cap_first}Trace
public class ${project.projectCode?cap_first}TraceInteceptor implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -${uid()}L;
	
	Logger log = Logger.getLogger(${project.projectCode?cap_first}TraceInteceptor.class);
	
	@AroundInvoke
	public java.lang.Object invoke(javax.interceptor.InvocationContext context) throws Exception{
		Method m = context.getMethod();
		log.trace("Entering method: " + m.getDeclaringClass().getName() + "#" + m.getName());
		return context.proceed();
	}
	
}
