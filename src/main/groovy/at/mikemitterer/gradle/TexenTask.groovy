package at.mikemitterer.gradle

import org.gradle.api.Project
import org.gradle.api.internal.ConventionTask
import org.gradle.api.tasks.TaskAction
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class TexenTask extends ConventionTask {
	private static Logger logger = LoggerFactory.getLogger(TexenTask.class);

	protected TexenPluginExtension pluginConvention

	String baseDir
	String controlTemplate
	String outputDir
	String templateDir
	String reportFile
	Boolean deleteReport

	public TexenTask() {
		logger.info "TexenTask created..."
		configure(project);
	}

	@TaskAction
	def texen() {
		println "+--------------------------------------------------------------------------+"
		println "  Generating files in ${baseDir}/${outputDir}...    "
		println "+--------------------------------------------------------------------------+"


		String classpath = project.configurations.getByName("velocityAntTask").asPath;
		//println "Classpath for texenx-ant-task ${classpath}"

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

	/**
	 * Defaultsettings über Konstruktor + über das Plugin nach afterEvaluate
	 *
	 * @param project
	 */
	void configure(final Project project) {
		pluginConvention = (TexenPluginExtension) project.convention.plugins.texenplugin

		logger.info "configure called in TexenTask"

		baseDir 		= pluginConvention.baseDir

		outputDir 		= pluginConvention.outputDir
		templateDir 	= pluginConvention.templateDir

		controlTemplate = pluginConvention.controlTemplate
		reportFile 		= pluginConvention.reportFile

		deleteReport 	= pluginConvention.deleteReport

		logger.info "Settings:"
		logger.info "---------------------------------------"
		logger.info "baseDir: ${baseDir}"

		logger.info "outputDir: ${baseDir}/${outputDir}"
		logger.info "templateDir: ${baseDir}/${templateDir}"

		logger.info "controlTemplate: ${controlTemplate}"
		logger.info "reportFile: ${reportFile}"
		logger.info "deleteReport: ${deleteReport}"
	}
}


