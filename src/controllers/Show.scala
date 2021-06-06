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

@WebServlet("/show")
@SerialVersionUID(1L)
class Show()

  extends Nothing {
  @throws[ServletException]
  @throws[IOException]
  protected def doGet(request: Nothing, response: Nothing): Unit = {
    val em = DBUtil.createEntityManager
    val t = em.find(classOf[Task], request.getParameter("id").toInt)
    em.close
    request.setAttribute("task", t)
    val rd = request.getRequestDispatcher("/WEB-INF/views/tasks/show.jsp")
    rd.forward(request, response)
  }
}
