<?xml version='1.0' encoding='UTF-8' ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
      "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">

<h:body>
<#assign el = "#">
	<ui:composition template="../template/template.xhtml">
		<ui:define name="body">
			<div class="container" style="margin-top: 60px;">
				<div class="row-fluid">
					<div class="span12">
						<blockquote class="pull-right text-warning">
							<p>
								<h:outputText value="${el}{sysBundle['website-name']}" escape="false" />
								极具智能的权限系统，已为您标记出所有可用模块
							</p>
							<small>要使用没有权限的功能，请咨询管理员</small>
						</blockquote>
					</div>
				</div>
				<div class="row-fluid">
					<ul class="thumbnails">
						<li class="span4">
							<h:form >
								<h:commandLink action="HOME" styleClass="thumbnail" >
									<h:graphicImage value="/resources/img/${el}{view.locale}/sample.jpg" styleClass="img-rounded" />
									<h3>Module A</h3>
									<p>
										Description A
									</p>
								</h:commandLink>
							</h:form>
						</li>
						<li class="span4">
							<h:form>
							<h:commandLink action="HOME" styleClass="thumbnail" >
								<h:graphicImage value="/resources/img/${el}{view.locale}/sample.jpg" styleClass="img-rounded" />
								<h3>Module B</h3>
								<p>
									Description B
								</p>
							</h:commandLink>
							</h:form>
						</li>
						<li class="span4">
							<h:form>
							<h:commandLink action="HOME" styleClass="thumbnail" >
								<h:graphicImage value="/resources/img/${el}{view.locale}/sample.jpg" styleClass="img-rounded" />
								<h3>Module C</h3>
								<p>
									Description C
								</p>
							</h:commandLink>
							</h:form>
						</li>
					</ul>
				</div>
				
			</div>
		</ui:define>
	</ui:composition>
</h:body>
</html>