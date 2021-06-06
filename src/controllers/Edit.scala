package controllers

import java.io.IOException
import javax.persistence.EntityManager
import javax.servlet.RequestDispatcher
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import models.Task
import utils.DBUtil

@WebServlet("/edit")
@SerialVersionUID(1L)
class Edit()

  extends Nothing {
  @throws[ServletException]
  @throws[IOException]
  protected def doGet(request: Nothing, response: Nothing): Unit = {
    val em = DBUtil.createEntityManager
    val t = em.find(classOf[Task], request.getParameter("id").toInt)
    em.close
    request.setAttribute("task", t)
    request.setAttribute("_token", request.getSession.getId)
    if (t != null) request.getSession.setAttribute("task_id", t.getId)
    val rd = request.getRequestDispatcher("/WEB-INF/views/tasks/edit.jsp")
    rd.forward(request, response)
  }
}
