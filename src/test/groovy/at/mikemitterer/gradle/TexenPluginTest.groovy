package at.mikemitterer.gradle

import static org.junit.Assert.*

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test


class TexenPluginTest {
	@Test
	public void texenPluginAddsTexenTaskToProject() {
		Project project = ProjectBuilder.builder().build()
		project.apply plugin: 'texen'

		assertTrue(project.tasks.texen instanceof TexenTask)
	}
}
