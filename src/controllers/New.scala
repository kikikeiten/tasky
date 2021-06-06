package controllers

import java.io.IOException
import javax.servlet.RequestDispatcher
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import models.Task

@WebServlet("/new")
@SerialVersionUID(1L)
class New()

  extends Nothing {
  @throws[ServletException]
  @throws[IOException]
  protected def doGet(request: Nothing, response: Nothing): Unit = {
    request.setAttribute("_token", request.getSession.getId)
    request.setAttribute("task", new Task)
    val rd = request.getRequestDispatcher("/WEB-INF/views/tasks/new.jsp")
    rd.forward(request, response)
  }
}
