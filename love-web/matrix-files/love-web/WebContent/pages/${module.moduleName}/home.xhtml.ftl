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

				<div class="container-fluid" style="margin-top: 20px;">
					<div class="row-fluid">
						<div class="span8 offset2">
							<h:form>
								<div class="navbar">
									<div class="navbar-inner">
										<h:outputLink class="brand" href="#"><small><strong>当前位置</strong></small></h:outputLink>
										<ul class="nav">
											<li><h:commandLink action="HOME" value="Home" actionListener="${el}{${module.moduleName}ConversationManager.endTotalConversation()}" /></li>
											<li><h:commandLink action="${el}{${module.moduleName}Action.startConversation()}" value="XXX" actionListener="${el}{${module.moduleName}ConversationManager.endTotalConversation()}" /></li>
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

								<div class="form-actions">
									<h:commandButton type="submit" class="btn btn-primary btn-large" value="action" />
									&nbsp;
									<h:commandButton type="reset" class="btn btn-warning btn-large" value="reset" />
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