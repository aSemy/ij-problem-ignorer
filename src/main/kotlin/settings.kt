package dev.adamko.intellij.problemignorer

import com.intellij.diagnostic.MessagePool
import com.intellij.openapi.components.*
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.options.BoundSearchableConfigurable
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.dsl.builder.bindSelected
import com.intellij.ui.dsl.builder.panel


internal class ProblemIgnorerSettingsConfigurable(
  private val project: Project
) : BoundSearchableConfigurable(
  displayName = Bundle.message("settings.name"),
  helpTopic = Bundle.message("settings.name"),
  _id = ID,
) {
  private val settings: ProblemIgnorerSettingsService get() = project.service()

  override fun createPanel(): DialogPanel = panel {
    row {
      checkBox(Bundle.message("settings.enable")).bindSelected(settings::enabled)
    }
  }

  override fun apply() {
    settings.tryClear()
    super.apply()
  }

  companion object {
    const val ID = "Settings.ProblemIgnorer"
  }
}

@Service(Service.Level.PROJECT)
@State(
  name = "dev.adamko.intellij.problemignorer.ProblemIgnorerSettingsService",
  storages = [Storage("problem-ignorer.xml")],
)
internal class ProblemIgnorerSettingsService : SimplePersistentStateComponent<SettingsState>(SettingsState()) {

  var enabled by state::enabled

  fun tryClear() {
    if (enabled) {
      thisLogger().warn("Yay, clearing errors!")
      MessagePool.getInstance().clearErrors()
    } else {
      thisLogger().warn("Clearing errors is disabled")
    }
  }

  override fun noStateLoaded() {
    super.noStateLoaded()
    loadState(SettingsState())
  }
}

internal class SettingsState : BaseState() {
  var enabled: Boolean by property(defaultValue = true)
}
