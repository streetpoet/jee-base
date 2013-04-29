<project name="SourceMatrix" default="dist" basedir=".">
	<description>
		compile ejb interfaces files to web-project's library.
		Written by William.
	</description>
	<#assign el = "$">
	<!-- set global properties for this build -->

	<property name="input" location="../../${project.projectCode}-ejb/build/classes" />
	<property name="output" location="../../${project.projectCode}-web/WebContent/WEB-INF/lib" />

	<target name="dist">
		<available file="${el}{input}" property="isInputExist"></available>
		<fail message="can't find folder: ${el}{input}" unless="${el}{isInputExist}"></fail>
		<available file="${el}{output}" property="isOutputExist"></available>
		<fail message="can't find folder: ${el}{output}" unless="${el}{isOutputExist}"></fail>
		<delete file="${el}{output}/${project.projectCode}-ejb-interfaces.jar" quiet="true"/>
		<jar destfile="${el}{output}/${project.projectCode}-ejb-interfaces.jar">
			<fileset dir="${el}{input}" casesensitive="yes">
				<include name="interfaces/**/*" />
			</fileset>
			<fileset dir="${el}{input}" includes="/interfaces/**/*.class" excludes="/com/**/interfaces/**/*" />
		</jar>
	</target>
</project>