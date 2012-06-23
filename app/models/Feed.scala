package models

import java.util.Date
import play.api.db._
import play.api.Play.current
import anorm._
import anorm.SqlParser._

case class Feed(id: Long, label: String, date: Date)

object Feed {

  def all(): List[Feed] = DB.withConnection { implicit c =>
    SQL("select * from feed order by date").as(feed *)
  }

  def create(label: String) {
    DB.withConnection { implicit c =>
      SQL("insert into feed (label) values ({label})").on(
        'label -> label).executeUpdate()
    }
  }

  def delete(id: Long) {
    DB.withConnection { implicit c =>
      SQL("delete from feed where id = {id}").on(
        'id -> id).executeUpdate()
    }
  }

  val feed = {
    get[Long]("id") ~
      get[String]("label") ~
      get[Date]("date") map {
        case id ~ label ~ date => Feed(id, label, date)
      }
  }
}