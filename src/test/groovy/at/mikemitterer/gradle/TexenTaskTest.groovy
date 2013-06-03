package at.mikemitterer.gradle

import static org.junit.Assert.*

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

class TexenTaskTest {
	@Test
	public void canAddTaskToProject() {
		Project project = ProjectBuilder.builder().build()

		def task = project.task('texen', type: TexenTask)

		assertTrue(task instanceof TexenTask)
	}
}
