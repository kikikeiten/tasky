package validators

import java.util
import models.Task

import java.util.{ArrayList, List}

object TaskValidator {
  def validate(t: Task): util.List[String] = {
    val errors = new util.ArrayList[String]
    val content_error = _validateContent(t.getContent)
    if (!(content_error == "")) errors.add(content_error)
    errors
  }

  private def _validateContent(content: String): String = {
    if (content == null || content == "") return "タスクを入力してください。"
    ""
  }
}
