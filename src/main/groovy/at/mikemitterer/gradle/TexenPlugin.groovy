package at.mikemitterer.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.slf4j.Logger
import org.slf4j.LoggerFactory


class TexenPluginExtension {
	final Project project;

	String baseDir

	String controlTemplate
	String outputDir
	String templateDir
	String reportFile

	Boolean deleteReport

	TexenPluginExtension(Project project) {
		this.project = project;

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

	protected Project project
	protected TexenPluginExtension pluginConvention

	void apply(Project project) {
		this.project = project;

		if (!project.convention.plugins.texenplugin) {
			final TexenPluginExtension pluginConvention = new TexenPluginExtension(project)
			project.convention.plugins.texenplugin = pluginConvention
		}
		this.pluginConvention = (TexenPluginExtension) project.convention.plugins.texenplugin

		configureDependencies(project);
		applyTasks(project)

		project.afterEvaluate { configure(project) }
	}

	//---------------------------------------------------------------------------------------------
	// private
	//---------------------------------------------------------------------------------------------
	private void applyTasks(final Project project) {
		project.task('texen', type: TexenTask, group: 'Texen', description: 'Is capable of producing almost any sort of text output') {}
	}

	private void configureDependencies(final Project project) {

		project.configurations { velocityAntTask }
		project.repositories { mavenCentral() }

		project.dependencies {
			velocityAntTask group: 'org.apache.velocity', 	name: 'velocity', 			version: '1.7'
			velocityAntTask group: 'commons-lang', 		name: 'commons-lang', 		version: '2.6'
			velocityAntTask group: 'commons-collections', 	name: 'commons-collections',version: '3.2.1'
		}
	}

	private void configure(final Project project) {
		logger.info "configure called in VelocityTexenPlugin"

		if(project.tasks.texen instanceof TexenTask) {
			final TexenTask task = project.tasks.texen
			task.configure(project)
		}
	}
}

