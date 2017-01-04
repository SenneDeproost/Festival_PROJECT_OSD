/**
  * Created by Senne Deproost on 1/01/2017.
  */
class Order {
  var bill: Double = 0
  var items: List[Article] = List()
  var tickets: List[Ticket] = List()

  def addItem(item: Item)= {
    bill += item.price
    items = item :: items

  }

  def addTicket(ticket: Ticket)= {
    bill += ticket.price
    tickets = ticket :: tickets

  }

  def confirm(account: Account): Unit ={

    if (account.budget - bill < 0){
        println("BUDGET TOO LOW.")
    }
    else {
      account.changeBudget(-bill)
    }

  }



}
