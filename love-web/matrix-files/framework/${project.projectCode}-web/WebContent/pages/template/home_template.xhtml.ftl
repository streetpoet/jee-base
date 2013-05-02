<?xml version='1.0' encoding='UTF-8' ?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
      "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<#assign el = "#">
<h:head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<h:outputStylesheet library="css/${el}{view.locale}" name="/bootstrap-responsive.css" />
	<h:outputStylesheet library="css/${el}{view.locale}" name="/bootstrap.min.css" />
	<h:outputStylesheet library="css/${el}{view.locale}" name="/docs.css" />
	<title><h:outputText value="${el}{sysBundle['website-name']}" escape="false" /></title>
</h:head>

<body>

	<!-- header navigator -->
	<div class="container-fluid">
		<div class="navbar navbar-fixed-top navbar-inverse">
			<div class="navbar-inner">
				<div class="container">
					<!-- Be sure to leave the brand out there if you want it shown -->
					<a class="brand strong" href="#"><h:outputText value="${el}{sysBundle['website-name']}" escape="false" /></a>

					<!-- Everything you want hidden at 940px or less, place within here -->
					<div class="nav-collapse collapse pull-right">
						<form class="form-inline navbar-form" method="post" action="j_security_check">
							<input id="j_username" name="j_username" type="text" class="input-small input-medium" placeholder="Username" />
							<input id="j_password" name="j_password" type="password" class="input-small input-medium" placeholder="Password" />
							<button type="submit" class="btn btn-success">Sign in</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<ui:insert name="body"></ui:insert>

	<!-- Footer -->
	<footer class="footer navbar-fixed-bottom">
	<div class="container">
		<p>Designed and built all base on open-source technologies, Power by William, NSV Co,Ltd.</p>
		<p>
			Code not licensed under <a href="http://www.apache.org/licenses/LICENSE-2.0" target="_blank">Apache License v2.0</a>
			<ul class="footer-links">
				<li><a href="#" target="_blank">About</a></li>
				<li class="muted">&middot;</li>
				<li><a href="#" target="_blank">Issues</a></li>
				<li class="muted">&middot;</li>
				<li><a href="#">Changelog</a></li>
			</ul>
		</p>
	</div>
	</footer>

	<h:outputScript library="js/${el}{view.locale}" name="/jquery.js"></h:outputScript>
	<h:outputScript library="js/${el}{view.locale}" name="/bootstrap.min.js"></h:outputScript>
</body>
</html>