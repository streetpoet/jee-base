
=================================
Deployment GuideLine
=================================

1, config jboss-logging.xml, change 'custemer log level', default is 'TRACE', after change, merge it with jboss's original file.
2, package ${project.packageString}.system.interceptor.MySQL*.java to *.jar, and put jboss's lib folder