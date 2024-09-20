import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer
import org.intellij.lang.annotations.Language

fun convertMarkdownToHtml(
  @Language("markdown")
  input: String,
): String {
  val document = Parser.builder().build()
    .parse(input)
  val renderer = HtmlRenderer.builder().build()
  return renderer.render(document) // "<p>This is <em>Markdown</em></p>\n"
}
