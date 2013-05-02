<?xml version='1.0' encoding='UTF-8' ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
      "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:ui="http://java.sun.com/jsf/facelets" xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html">
	
<h:head>
</h:head>
<h:body>
<#assign el = "#">
	<ui:composition template="../template/home_template.xhtml">
		<ui:define name="body">

			<div class="jumbotron masthead">
				<div class="container">
				<div class="">
				<div class="">
					<h1>
						<h:outputText value="${el}{sysBundle['website-name']}" escape="false" />
					</h1>
					<p>Terminator Of Night Programmers</p>
					<p>
						<a class="btn btn-primary btn-large" href="#registerModal" data-toggle="modal">Register</a>
					</p>
					<ul class="masthead-links">
						<li><a href="#">Rapid construction system for distributed JaveEE<sup>&#8482;</sup> architechture.
						</a></li>
						<li id="version">Version 1.0</li>
					</ul>
				</div>
				</div>
				</div>
			</div>
			
			<h:form class="form-horizontal" id="register-form">
			<div id="registerModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="title" aria-hidden="true">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h3 id="title">新用户注册</h3>
				</div>
				<div class="modal-body">
					<div class="form-horizontal">
						<h:panelGroup id="form-content">
						<div class="control-group">
							<h:outputLabel value="登陆ID" for="userid" styleClass="control-label" />
							<div class="controls">
								<h:inputText value="${el}{userInfo.loginId}" id="userid" required="true" label="Login ID">
									<f:validateLength minimum="6" maximum="20"></f:validateLength>
								</h:inputText>
								<br /><h:message for="userid" styleClass="text-error"/>
							</div>
						</div>
						<div class="control-group">
							<h:outputLabel value="真实姓名" for="nickname" styleClass="control-label" />
							<div class="controls">
								<h:inputText value="${el}{userInfo.nickName}" id="nickname" required="true" label="Real Name">
									<f:validateLength minimum="2" maximum="4"></f:validateLength>
								</h:inputText>
								<br /><h:message for="nickname" styleClass="text-error"/>
							</div>
						</div>
						<div class="control-group">
							<h:outputLabel value="密码" for="password" styleClass="control-label" />
							<div class="controls">
								<h:inputSecret value="${el}{userInfo.password}" id="password" required="true" label="Password">
									<f:validateLength minimum="6" maximum="12"></f:validateLength>
								</h:inputSecret>
								<br /><h:message for="password" styleClass="text-error"/>
							</div>
						</div>
						<div class="control-group">
							<h:outputLabel value="确认密码" for="password-retry" styleClass="control-label" />
							<div class="controls">
								<h:inputSecret value="${el}{userInfo.passwordRetry}" id="password-retry" required="true" label="Retry-Password">
									<f:validateLength minimum="6" maximum="12"></f:validateLength>
								</h:inputSecret>
								<br /><h:message for="password-retry" styleClass="text-error"/>
								<h:messages layout="table" styleClass="text-error" globalOnly="true"/>
							</div>
						</div>
						</h:panelGroup>
					</div>
				</div>
				<div class="modal-footer">
					<h:commandButton value="注册新用户" action="${el}{sysCore.register()}" styleClass="btn btn-large btn-block btn-primary">
						<f:ajax execute="@form" render="form-content" onevent="onAjaxEvent"></f:ajax>
					</h:commandButton>
					<button class="btn btn-large btn-block btn-danger" type="button" onclick="$('#registerModal').modal('hide');">取消</button>
				</div>
			</div>
			</h:form>
			
			<div id="alert" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="title" aria-hidden="true">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h3 id="title">注册成功</h3>
				</div>
				<div class="modal-body">
					<p>您已注册成功，请以新用户名和密码在页面右上角登陆。</p>
				</div>
				<div class="modal-footer">
					<button class="btn btn-large btn-block btn-success" type="button" onclick="$('#alert').modal('hide');">我知道了</button>
				</div>
			</div>
			
			<h:outputScript>
				function onAjaxEvent(data){
					if (data.status == 'success'){
						if ($(".text-error").length == 0){
							$('#registerModal').modal('hide');
							$('#alert').modal('show');
						}
					}
				}
			</h:outputScript>
		</ui:define>
	</ui:composition>

</h:body>


</html>