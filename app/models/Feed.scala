package models

import java.util.Date

case class Feed(id: Long, label: String, date: Date)

object Feed {

  def all(): List[Feed] = Nil

  def create(label: String) {}

  def delete(id: Long) {}

}