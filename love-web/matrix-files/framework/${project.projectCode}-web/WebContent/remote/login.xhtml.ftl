<?xml version='1.0' encoding='UTF-8' ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
      "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:h="http://java.sun.com/jsf/html" xmlns:f="http://java.sun.com/jsf/core">

<h:head>
	<f:phaseListener type="${project.packageString}.system.listener.${project.projectCode?cap_first}RemoteLoginLifeCycleListener"></f:phaseListener>
</h:head>

<h:body>
</h:body>


</html>