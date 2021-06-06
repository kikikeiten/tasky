package models

import com.sun.tools.doclint.Entity
import com.sun.tools.javac.util.Name.Table

import java.sql.Timestamp
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.NamedQueries
import javax.persistence.NamedQuery
import javax.persistence.Table
import scala.io.Codec.fallbackSystemCodec.name
import scala.reflect.internal.NoPhase.Id
import scala.reflect.internal.util.TableDef.Column

@Entity
@NamedQueries(Array(new NamedQuery(name = "getAllTasks", query = "SELECT t FROM Task AS t ORDER BY t.id DESC"), new NamedQuery(name = "getTasksCount", query = "SELECT COUNT(t) FROM Task AS t")))
@Table(name = "tasks") class Task {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY) private var id = null
  @Column(name = "content", length = 255, nullable = false) private var content = null
  @Column(name = "created_at", nullable = false) private var created_at = null
  @Column(name = "updated_at", nullable = false) private var updated_at = null

  def getId: Integer = id

  def setId(id: Integer): Unit = {
    this.id = id
  }

  def getContent: String = content

  def setContent(content: String): Unit = {
    this.content = content
  }

  def getCreated_at: Timestamp = created_at

  def setCreated_at(created_at: Timestamp): Unit = {
    this.created_at = created_at
  }

  def getUpdated_at: Timestamp = updated_at

  def setUpdated_at(updated_at: Timestamp): Unit = {
    this.updated_at = updated_at
  }
}

