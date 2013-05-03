package ${project.packageString}.${module.moduleName}.event;

import java.io.Serializable;

public class ${module.moduleName?cap_first}QueryEvent implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -${uid()}L;

	public ${module.moduleName?cap_first}QueryEvent(QueryMode mode){
		queryMode = mode;
	}
	
	private QueryMode queryMode;

	public QueryMode getQueryMode() {
		return queryMode;
	}

	public void setQueryMode(QueryMode queryMode) {
		this.queryMode = queryMode;
	}

	public static enum QueryMode{
		LOAD_ALL_RECORD, LOAD_SINGLE_RECORD;
	}

}
