package controllers

import play.api.mvc._
import javax.inject.Inject
import play.api.i18n.{MessagesApi, I18nSupport}
import models.Product

class Products @Inject() (val messagesApi: MessagesApi) extends Controller with I18nSupport {
  def list = Action { implicit request =>
    val products = Product.findAll
    Ok(views.html.products.list(products))
  }
}
