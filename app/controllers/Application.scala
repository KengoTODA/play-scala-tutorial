package controllers

import java.util._

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

import models.Feed

import org.slf4j.Logger
import org.slf4j.LoggerFactory

object Application extends Controller {
  val log = LoggerFactory.getLogger(getClass())

  def index = Action {
    Redirect(routes.Application.feeds)
  }

  val feeds = Action {
    Ok(views.html.index(Feed.all(), feedForm))
  }

  val newFeed = Action {
    implicit request =>
      log.info("reached!")
      feedForm.bindFromRequest.fold(
        errors => BadRequest(views.html.index(Feed.all(), feedForm)),
        label => {
          log.info(label)
          Feed.create(label)
          log.trace("feed created: label={}", label)
          Redirect(routes.Application.feeds)
        }
      )
  }

  val feedForm = Form(
    "label" -> nonEmptyText
  )
}