package at.mikemitterer.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class TexenTask extends DefaultTask {
	private static Logger logger = LoggerFactory.getLogger(TexenTask.class);

	String baseDir			= project.texenplugin.baseDir

	String controlTemplate 	= project.texenplugin.controlTemplate
	String outputDir		= project.texenplugin.outputDir
	String templateDir		= project.texenplugin.templateDir
	String reportFile		= project.texenplugin.reportFile

	Boolean deleteReport 	= project.texenplugin.deleteReport

	@TaskAction
	def texen() {


		println "+--------------------------------------------------------------------------+"
		println "  Generating files in ${outputDir}...    "
		println "+--------------------------------------------------------------------------+"

		String classpath = project.configurations.getByName("velocityAntTask").asPath;

		logger.debug "Classpath for texenx-ant-task ${classpath}"
		logger.debug "From plugin (in task): ${project.texenplugin.outputDir}"
		logger.debug "From Task: ${outputDir}"


		ant.taskdef(name: 'texenx', classname: 'org.apache.velocity.texen.ant.TexenTask', classpath: classpath)
		ant.texenx(
				controlTemplate :	"${controlTemplate}",
				outputDirectory : 	"${outputDir}",
				templatePath : 		"${templateDir}",
				outputFile : 		"${reportFile}"
				)
		if( deleteReport) {
			ant.delete(file: "${outputDir}/${reportFile}")
		}
	}
}


