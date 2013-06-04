package at.mikemitterer.gradle

import org.gradle.api.Task
import org.gradle.api.internal.ConventionTask
import org.gradle.api.tasks.TaskAction
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class TexenTask extends ConventionTask {
	private static Logger logger = LoggerFactory.getLogger(TexenTask.class);


	String baseDir
	String controlTemplate
	String outputDir
	String templateDir
	String reportFile
	Boolean deleteReport

	@TaskAction
	def texen() {
		baseDir 		= baseDir 		?: project.convention.plugins.texenplugin.baseDir

		outputDir 		= outputDir 	?: project.convention.plugins.texenplugin.outputDir
		templateDir 	= templateDir 	?: project.convention.plugins.texenplugin.templateDir

		controlTemplate = controlTemplate ?: project.convention.plugins.texenplugin.controlTemplate
		reportFile 		= reportFile 	?: project.convention.plugins.texenplugin.reportFile

		deleteReport 	= deleteReport 	?: project.convention.plugins.texenplugin.deleteReport

		println "+--------------------------------------------------------------------------+"
		println "  Generating files in ${baseDir}/${outputDir}...    "
		println "+--------------------------------------------------------------------------+"


		String classpath = project.configurations.getByName("velocityAntTask").asPath;
		//println "Classpath for texenx-ant-task ${classpath}"

		logger.info "Settings:"
		logger.info "---------------------------------------"
		logger.info "baseDir: ${baseDir}"

		logger.info "outputDir: ${baseDir}/${outputDir}"
		logger.info "templateDir: ${baseDir}/${templateDir}"

		logger.info "controlTemplate: ${controlTemplate}"
		logger.info "reportFile: ${reportFile}"
		logger.info "deleteReport: ${deleteReport}"

		ant.taskdef(name: 'texenx', classname: 'org.apache.velocity.texen.ant.TexenTask', classpath: classpath)

		ant.texenx(
				controlTemplate :	"${controlTemplate}",
				outputDirectory : 	"${baseDir}/${outputDir}",
				templatePath : 		"${baseDir}/${templateDir}",
				outputFile : 		"${reportFile}"
				)

		if( deleteReport) {
			ant.delete(file: "${baseDir}/${outputDir}/${reportFile}")
		}

	}
}


