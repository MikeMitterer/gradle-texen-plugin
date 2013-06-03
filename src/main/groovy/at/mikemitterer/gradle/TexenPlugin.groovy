package at.mikemitterer.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class VelocityTexenPlugin implements Plugin<Project> {
	private static Logger logger = LoggerFactory.getLogger(VelocityTexenPlugin.class);

	void apply(Project project) {
		project.extensions.create("texenplugin", TexenPluginExtension)

		project.task('texen',type: TexenTask) {
			group = "Texen"
			description = "Is capable of producing almost any sort of text output"
		} << {
			logger.debug("From plugin (in plugin): ${project.texenplugin.outputDir}")
		}
	}
}

class TexenPluginExtension {
	String baseDir			= "build/database"

	String controlTemplate 	= "control.vm"
	String outputDir		= "${baseDir}/out"
	String templateDir		= "${baseDir}/templates"
	String reportFile		= "generation.report"

	Boolean deleteReport = true
}