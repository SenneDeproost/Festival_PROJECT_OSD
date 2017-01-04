/**
  * Created by Senne Deproost on 1/01/2017.
  */
class Order {
  var bill: Double = 0
  var items: List[Item] = List()
  var tickets: List[Ticket] = List()

  // ADD

  def addItem(item: Item)= {
    bill += item.price
    items = item :: items

  }


  def addTicket(ticket: Ticket)= {
    bill += ticket.price
    tickets = ticket :: tickets

  }

  // CONFIRM

  def confirm(account: Account): Unit = {

    var non16: List[Ticket] = tickets.filter(_.category != "Early_bird")
    var listOfNames: List[String] = tickets.map(_.name)
    var listOfUniqueNames: List[String] = listOfNames.distinct

    /* Has the account enough budget? */
    if (account.budget - bill < 0) {
      println("BUDGET TOO LOW.")
    }
      /* Is buyer old enough? */
    else if (non16.nonEmpty && account.age < 16){
      println("CUSTOMER TOO YOUNG TO BUY TICKETS.")
    }
      /* Are there any tickets with same owner? */
    else if (listOfNames.length > listOfUniqueNames.length){
      println("NAME WITH MORE THAN ONE TICKET DETECTED.")
    }



    else {
      account.changeBudget(-bill)

      // Email

      items = items.sortWith(_.price > _.price)
      tickets = tickets.sortWith(_.name < _.name)

      println("Email to " + account.email + "\n")
      println("TICKETS:\n")
      tickets.foreach(i => printTicket(i))
      println("\n")
      println("ITEMS:\n")
      items.foreach(i => printItem(i))
      println("\n")
      println("REMAINING BUDGET: €" + account.budget)


    }
  }

  // SORTING

  def sortList(a_list: Any)= {
    if (a_list == items){
      items.sortWith(_.price > _.price)
    }
    else if (a_list == tickets){
      tickets.sortWith(_.name > _.name)
    }
  }

  // PRINTING

  def printItem(element: Item): Unit = {
    println(element.item + "  " + "€" + element.price)
  }

  def printTicket(element: Ticket): Unit = {
    println(element.category + "  " + "€" + element.price)
    println("Owner: " + element.name)
  }

}




