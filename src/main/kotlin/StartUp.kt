package dev.adamko.intellij.problemignorer

import com.intellij.diagnostic.MessagePool
import com.intellij.diagnostic.MessagePoolAdvisor
import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity

internal class StartUp : ProjectActivity {
  override suspend fun execute(project: Project) {
    thisLogger().warn("Starting...")
    val settings: ProblemIgnorerSettingsService = project.service()
    val advisor = MessageIgnorerAdvisor(settings)
    MessagePool.getInstance().addAdvisor(advisor)
  }
}

private class MessageIgnorerAdvisor(
  private val settings: ProblemIgnorerSettingsService,
) : MessagePoolAdvisor {
  override suspend fun beforeEntryAdded(e: MessagePoolAdvisor.BeforeEntryAddedEvent): Boolean {
    settings.tryClear()
    return false
  }
  override suspend fun afterEntryAdded(e: MessagePoolAdvisor.AfterEntryAddedEvent) {
    settings.tryClear()
  }
}
