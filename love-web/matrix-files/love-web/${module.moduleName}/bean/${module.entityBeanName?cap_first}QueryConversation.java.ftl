package ${project.packageString}.${module.moduleName}.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;

import ${project.packageString}.${module.moduleName}.entity.${module.entityBeanName?cap_first};
import ${project.packageString}.${module.moduleName}.helper.${module.entityBeanName?cap_first}Condition;
import ${project.packageString}.system.bean.PageObject;
import ${project.packageString}.system.constant.Configuration;
import ${project.packageString}.system.qualifier.${project.projectCode?cap_first}Logged;

@Named
@ConversationScoped
public class ${module.entityBeanName?cap_first}QueryConversation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -${uid()}L;
	
	@Inject Conversation conversation;
	@Inject @${project.projectCode?cap_first}Logged Logger log;
	${module.entityBeanName?cap_first}Condition ${module.entityBeanName}Condition;
	PageObject pageObject;
	List<${module.entityBeanName?cap_first}> list${module.entityBeanName?cap_first};
	

	@PostConstruct
	public void postConstruct(){
		${module.entityBeanName}Condition = new ${module.entityBeanName?cap_first}Condition();
		pageObject = new PageObject();
		log.trace("### ${module.entityBeanName?cap_first}QueryConversation#postConstruct");
	}
	
	@PreDestroy
	public void preDestroy(){
		log.trace("### ${module.entityBeanName?cap_first}QueryConversation#preDestroy");
	}

	public void beginConversation(){
		if (conversation.isTransient()){
			conversation.begin();
			conversation.setTimeout(Configuration.CONVERSATION_TIMEOUT);
		}
	}
	
	public void endConversation(){
		if (!conversation.isTransient()){
			conversation.end();
		}
	}

	public ${module.entityBeanName?cap_first}Condition get${module.entityBeanName?cap_first}Condition() {
		return ${module.entityBeanName}Condition;
	}

	public void set${module.entityBeanName?cap_first}Condition(${module.entityBeanName?cap_first}Condition ${module.entityBeanName}Condition) {
		this.${module.entityBeanName}Condition = ${module.entityBeanName}Condition;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public PageObject getPageObject() {
		return pageObject;
	}

	public void setPageObject(PageObject pageObject) {
		this.pageObject = pageObject;
	}

	public List<${module.entityBeanName?cap_first}> getList${module.entityBeanName?cap_first}() {
		return list${module.entityBeanName?cap_first};
	}

	public void setList${module.entityBeanName?cap_first}(List<${module.entityBeanName?cap_first}> list${module.entityBeanName?cap_first}) {
		this.list${module.entityBeanName?cap_first} = list${module.entityBeanName?cap_first};
	}
	
}
