import kotlin.reflect.KProperty
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.NamedDomainObjectProvider
import org.gradle.api.Task
import org.gradle.api.tasks.TaskContainer
import org.gradle.kotlin.dsl.support.illegalElementType

// reimplement kotlin DSL because the Gradle devs are brain dead morons and removed it
// https://github.com/gradle/gradle/issues/37555

fun <T : Any, C : NamedDomainObjectContainer<T>> C.registering2(action: T.() -> Unit): RegisteringDomainObjectDelegateProviderWithAction<out C, T> {
  return RegisteringDomainObjectDelegateProviderWithAction.of(this, action)
}

class RegisteringDomainObjectDelegateProviderWithAction<C, T>
private constructor(
  internal val delegateProvider: C,
  internal val action: T.() -> Unit,
) {
  companion object {
    fun <C, T> of(delegateProvider: C, action: T.() -> Unit) =
      RegisteringDomainObjectDelegateProviderWithAction(delegateProvider, action)
  }
}

operator fun RegisteringDomainObjectDelegateProviderWithAction<out TaskContainer, Task>.provideDelegate(
  receiver: Any?,
  property: KProperty<*>
) = ExistingDomainObjectDelegate.of(
  delegateProvider.register(property.name, action)
)


inline operator fun <T : Any, reified U : T> NamedDomainObjectProvider<out T>.getValue(
  thisRef: Any?,
  property: KProperty<*>
): U {
  return get().let {
    it as? U
      ?: throw illegalElementType(this, property.name, U::class, it::class)
  }
}

class ExistingDomainObjectDelegate<T>
private constructor(
  internal val delegate: T
) {
  companion object {
    @Suppress("DEPRECATION")
    fun <T> of(delegate: T) =
      ExistingDomainObjectDelegate(delegate)
  }
}

operator fun <T> ExistingDomainObjectDelegate<out T>.getValue(receiver: Any?, property: KProperty<*>): T =
  delegate
