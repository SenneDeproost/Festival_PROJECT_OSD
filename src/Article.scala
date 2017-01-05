/**
  * Created by Senne Deproost on 1/01/2017.
  */
/* ARTICLE */
/* Een article is de basis van alle aankopen die gedaan kunnen worden.
 * Deze wordt uitgebreid door items of door tickets. */
class Article {

  val price: Double = 0

}

/* TICKET */
/* Een ticket bevat de category, naam van de koper en de ID van het ticket.
 * Ticket wordt uitgebreid met de verchillende classes die staan voor de ticket soorten */
trait Ticket extends Article{
  val category: String = ""
  val owner: String = ""
  val ID: Long = System.currentTimeMillis
}

class Early_Bird(Owner: String) extends Ticket{
  override val price: Double =  50
  override val owner: String = Owner
  override val category: String = "Early_Bird"

}

class Normal(Owner: String) extends Ticket{
  override val price: Double =  100
  override val owner: String = Owner
  override val category: String = "Normal"

}

class VIP(Owner: String) extends Ticket{
  override val price: Double =  200
  override val owner: String = Owner
  override val category: String = "VIP"

}

/* ITEM */
/* Een item kan verschillende dingen voorstellen die door het festival geverifieerd moeten worden*/
class Item(Item: String, Price: Double) extends Article{
  val item: String = Item
  override val price: Double = Price
}