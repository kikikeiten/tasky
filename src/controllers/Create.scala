package controllers

import java.io.IOException
import java.sql.Timestamp
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
import validators.TaskValidator

import java.util.List

@WebServlet("/create")
@SerialVersionUID(1L)
class Create()

  extends Nothing {
  @throws[ServletException]
  @throws[IOException]
  protected def doPost(request: Nothing, response: Nothing): Unit = {
    val _token = request.getParameter("_token").asInstanceOf[String]
    if (_token != null && _token == request.getSession.getId) {
      val em = DBUtil.createEntityManager
      val t = new Task
      val content = request.getParameter("content")
      t.setContent(content)
      val currentTime = new Timestamp(System.currentTimeMillis)
      t.setCreated_at(currentTime)
      t.setUpdated_at(currentTime)
      val errors = TaskValidator.validate(t)
      if (errors.size > 0) {
        em.close
        request.setAttribute("_token", request.getSession.getId)
        request.setAttribute("task", t)
        request.setAttribute("errors", errors)
        val rd = request.getRequestDispatcher("/WEB-INF/views/tasks/new.jsp")
        rd.forward(request, response)
      }
      else {
        em.getTransaction.begin
        em.persist(t)
        em.getTransaction.commit
        request.getSession.setAttribute("flush", "登録が完了しました。")
        em.close
        response.sendRedirect(request.getContextPath + "/index")
      }
    }
  }
}
