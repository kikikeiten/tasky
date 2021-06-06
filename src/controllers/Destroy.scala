package controllers

import java.io.IOException
import javax.persistence.EntityManager
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import models.Task
import utils.DBUtil

@WebServlet("/destroy")
@SerialVersionUID(1L)
class Destroy()

  extends Nothing {
  @throws[ServletException]
  @throws[IOException]
  protected def doPost(request: Nothing, response: Nothing): Unit = {
    val _token = request.getParameter("_token").asInstanceOf[String]
    if (_token != null && _token == request.getSession.getId) {
      val em = DBUtil.createEntityManager
      val t = em.find(classOf[Task], request.getSession.getAttribute("task_id").asInstanceOf[Integer])
      em.getTransaction.begin
      em.remove(t)
      em.getTransaction.commit
      request.getSession.setAttribute("flush", "削除が完了しました。")
      em.close
      request.getSession.removeAttribute("task_id")
      response.sendRedirect(request.getContextPath + "/index")
    }
  }
}
