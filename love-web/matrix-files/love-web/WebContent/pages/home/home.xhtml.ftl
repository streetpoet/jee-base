<?xml version='1.0' encoding='UTF-8' ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
      "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<#assign el = "#">
<h:body>

	<ui:composition template="../template/template.xhtml">
		<ui:define name="body">
			<div class="container" style="margin-top: 60px;">

				<div class="row-fluid">
					<ul class="thumbnails">

						<li class="span3"><h:form>
								<h:commandLink action="${module.moduleName?upper_case}_HOME" styleClass="thumbnail" actionListener="${el}{${module.moduleName}Action.startConversation()}">
									<h:graphicImage value="/resources/img/${el}{view.locale}/2.jpg" styleClass="img-rounded" />
									<h3>XXXXXX</h3>
									<p>xxxxxx</p>
								</h:commandLink>
							</h:form></li>
					</ul>
				</div>
			</div>
		</ui:define>
	</ui:composition>
</h:body>
</html>