package controllers

import java.io.IOException
import java.util
import javax.persistence.EntityManager
import javax.servlet.RequestDispatcher
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import models.Task
import utils.DBUtil

import java.util.List

@WebServlet("/index")
@SerialVersionUID(1L)
class Index()

  extends Nothing {
  @throws[ServletException]
  @throws[IOException]
  protected def doGet(request: Nothing, response: Nothing): Unit = {
    val em = DBUtil.createEntityManager
    var page = 1
    try page = request.getParameter("page").toInt
    catch {
      case e: NumberFormatException =>

    }
    val tasks = em.createNamedQuery("getAllTasks", classOf[Task]).setFirstResult(10 * (page - 1)).setMaxResults(10).getResultList
    val tasks_count = em.createNamedQuery("getTasksCount", classOf[Long]).getSingleResult.asInstanceOf[Long]
    em.close
    request.setAttribute("tasks", tasks)
    request.setAttribute("tasks_count", tasks_count)
    request.setAttribute("page", page)
    val rd = request.getRequestDispatcher("/WEB-INF/views/tasks/index.jsp")
    rd.forward(request, response)
  }
}
