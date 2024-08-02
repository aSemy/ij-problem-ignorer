package dev.adamko.intellij.problemignorer

import com.intellij.diagnostic.MessagePool
import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity

internal class StartUp : ProjectActivity {
  override suspend fun execute(project: Project) {
    thisLogger().warn("Starting...")
    val settings: ProblemIgnorerSettingsService = project.service()

    MessagePool.getInstance().addListener {
      settings.tryClear()
    }
  }
}
