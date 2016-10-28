package models

// model class
case class Product(ean: Long, name: String, description: String)

// data access object
object Product {
    var products = Set( Product(5010255079763L, "Paperclips Large", "large Plain Pack of 1000"),
      Product(5018206244666L, "Giant Paperclips", "Giant Plain Pack of 1000"),
      Product(5018306312913L, "No Tear Paper Clip", "No Tear Extra Large Pack of 1000"),
      Product(5018206244611L, "Zebra Paperclips", "Zebra Length 28mm Assorted 150 Pack")
    )

    def findAll = products.toList.sortBy(_.ean)
}
