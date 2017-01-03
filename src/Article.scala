/**
  * Created by Senne Deproost on 1/01/2017.
  */
class Article {

  val price: Double = 0

}

/* TICKETS */
trait Ticket extends Article{
  val category: String = ""
  val name: String = ""
  val ID: Long = System.currentTimeMillis
}

class Early_Bird(Name: String) extends Ticket{
  override val price: Double =  50
  override val name: String = Name
  override val category: String = "Early_Bird"

}

class Normal(Name: String) extends Ticket{
  override val price: Double =  100
  override val name: String = Name
  override val category: String = "Normal"

}

class VIP(Name: String) extends Ticket{
  override val price: Double =  200
  override val name: String = Name
  override val category: String = "VIP"

}

class Item(Item: String, Price: Double) extends Article{
  val item: String = Item
  override val price: Double = Price
}