package controllers

import play.api.mvc._
import javax.inject.Inject
import play.api.i18n.{Messages, MessagesApi, I18nSupport}
import play.api.data.Form
import play.api.data.Forms.{mapping, longNumber, nonEmptyText}
import models.Product

class Products @Inject() (val messagesApi: MessagesApi) extends Controller with I18nSupport {
  def list = Action { implicit request =>
    val products = Product.findAll
    Ok(views.html.products.list(products))
  }

  def show(ean: Long) = Action {
    implicit request => Product.findByEan(ean).map {
      // rendering product details
      product => Ok(views.html.products.details(product))
    }.getOrElse(NotFound)  // return page 404
  }

  private val productForm: Form[Product] = Form(
    mapping(
      "ean" -> longNumber.verifying("validation.ean.duplicate", Product.findByEan(_).isEmpty),  // form의 field와 제약사항
      "name" -> nonEmptyText,
      "description" -> nonEmptyText
    )(Product.apply)(Product.unapply) // form과 model 사이 mapping
  )
}
