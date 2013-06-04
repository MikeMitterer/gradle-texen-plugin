package at.mikemitterer.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.slf4j.Logger
import org.slf4j.LoggerFactory

//For passing arguments from gradle script.
class TexenPluginExtension {
	String baseDir

	String controlTemplate
	String outputDir
	String templateDir
	String reportFile

	Boolean deleteReport

	TexenPluginExtension() {
		//this.project = project;

		baseDir			= "build/database"

		outputDir		= "out"
		templateDir		= "templates"

		controlTemplate = "control.vm"
		reportFile		= "generation.report"

		deleteReport 	= false
	}

	def texenplugin(Closure closure) {
		closure.delegate = this
		closure()
	}
}

class VelocityTexenPlugin implements Plugin<Project> {
	private static Logger logger = LoggerFactory.getLogger(VelocityTexenPlugin.class);

	void apply(Project project) {
		final TexenPluginExtension pluginextension = project.extensions.create("texenplugin", TexenPluginExtension)
		project.convention.plugins.texenplugin = pluginextension

		project.task('texen',type: TexenTask) {
			group = "Texen"
			description = "Is capable of producing almost any sort of text output"

			logger.info "Configuration in plugin: ${project.convention.plugins.texenplugin.baseDir}"

		} << { logger.info "Execution in plugin: ${project.convention.plugins.texenplugin.baseDir}" }
	}
}

