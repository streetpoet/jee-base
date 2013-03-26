<?xml version='1.0' encoding='UTF-8' ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
      "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions" xmlns:c="http://java.sun.com/jsp/jstl/core">
<#assign el = "#">
<h:body>

	<ui:composition template="../template/template.xhtml">
		<ui:define name="body">
			<f:view>

				<!-- Navigator -->

				<div class="container" style="margin-top: 20px;">
					<div class="row-fluid">
						<div class="span12">
							<h:form>
								<div class="navbar">
									<div class="navbar-inner">
										<h:outputLink class="brand" href="#">当前位置：</h:outputLink>
										<ul class="nav">
											<li><h:commandLink action="HOME" value="Home" actionListener="${el}{${module.moduleName}Action.endConversation}" /></li>
											<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">其他模块<b class="caret"></b>
											</a>
												<ul class="dropdown-menu">
													<li><h:outputLink href="#">模块1</h:outputLink></li>
													<li><h:outputLink href="#">模块2</h:outputLink></li>
													<li><h:outputLink href="#">模块3</h:outputLink></li>
													<li><h:outputLink href="#">模块4</h:outputLink></li>
												</ul></li>
											<li><h:outputLink styleClass="active" href="#">项目模块属性配置</h:outputLink></li>
											<li><h:outputLink href="#">功能2</h:outputLink></li>
										</ul>
									</div>
								</div>
							</h:form>
						</div>
					</div>
				</div>


				<div class="container-fluid">

					<!-- Form Title -->
					
					<div class="row-fluid">
						<div class="span8 offset2">
							<div class="page-header text-left">
								<h2>
									XXXXXXX <small>version 1.0</small>
								</h2>
							</div>
						</div>
					</div>

					<!-- Message Region -->

					<h:panelGroup rendered="${el}{fn:length(facesContext.messageList) ne 0}">
						<div class="row-fluid">
							<div class="span8 offset2 text-left">
								<h:panelGroup rendered="${el}{fn:length(facesContext.messageList) ne 0}">
									<c:forEach items="${el}{facesContext.messageList}" var="msg">
										<c:if test="${el}{msg.severity.ordinal ne 0}">
											<div class="alert alert-error">
												<button class="close" data-dismiss="alert" type="button">×</button>
												<span class="label label-important">Error</span>&nbsp; <strong><h:outputText value="${el}{msg.summary}" /></strong>
											</div>
										</c:if>
										<c:if test="${el}{msg.severity.ordinal eq 0}">
											<div class="alert alert-success">
												<button class="close" data-dismiss="alert" type="button">×</button>
												<span class="label label-success">Success</span>&nbsp; <strong><h:outputText value="${el}{msg.summary}" /></strong>
											</div>
										</c:if>
									</c:forEach>
								</h:panelGroup>
							</div>
						</div>
					</h:panelGroup>

					<!-- Form -->

					<div class="row-fluid">
						<div class="span8 offset2 text-left bs-docs-example">
							<h:form class="form-horizontal">

								<!-- selection item -->

								<div class="control-group">
									<h:outputLabel class="control-label" for="selectItem" value="select:" />
									<div class="controls">
										<h:selectOneMenu label="selectItem" id="selectItem" required="true"
											valueChangeListener="${el}{${module.moduleName}Action.onMatrixProjectListValueChange}">
											<f:selectItem itemValue="-1" itemLabel="" noSelectionOption="true"/>
											<f:selectItems value="${el}{${module.moduleName}Action.matrixProjectList}" />
											
											<f:ajax event="change" execute="project " render="module">
												
											</f:ajax>
										</h:selectOneMenu>
										&nbsp;
										<h:outputLink href="#myModal" class="btn btn-success" data-toggle="modal">新增项目</h:outputLink>
									</div>
								</div>

								<!-- 模块名称 -->

								<div class="control-group">
									<h:outputLabel class="control-label" for="moduleName" value="模块名称" />
									<div class="controls">
										<h:inputText id="moduleName" placeholder="模块名称" value="${el}{matrixModule.moduleName}" required="true"
											requiredMessage="模块名称必须输入">
										</h:inputText>
										<span class="label label-info">首字母小写，将作为包名出现，如：report</span>
									</div>
								</div>


								<div class="control-group">
									<h:outputLabel class="control-label" for="entityBeanName" value="实体Bean名称" />
									<div class="controls">
										<h:inputText id="entityBeanName" placeholder="实体Bean名称" value="${el}{matrixModule.entityBeanName}" />
										<span class="label label-info">首字母小写，将自动生成Session Bean的增删改查功能。</span>
									</div>
								</div>
								<div class="control-group">
									<h:outputLabel class="control-label" for="selectBeanName" value="Select元素对于Bean名称" />
									<div class="controls">
										<h:inputText id="selectBeanName" placeholder="Select元素对于Bean名称" value="${el}{matrixModule.selectBeanName}" />
										<span class="label label-info">首字母小写，将自动生成单例Bean的缓存查询功能</span>
									</div>
								</div>
								<div class="form-actions">
									<h:commandButton type="submit" class="btn btn-primary btn-large" value="生成方案(.zip)" action="${el}{${module.moduleName}Action.createMatrixProject}" />
									&nbsp;
									<h:commandButton type="reset" class="btn btn-warning btn-large" value="重置" />
								</div>
							</h:form>
						</div>
					</div>
				</div>
			</f:view>
		</ui:define>
	</ui:composition>
</h:body>
</html>