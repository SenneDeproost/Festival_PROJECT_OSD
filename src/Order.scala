/**
  * Created by Senne Deproost on 1/01/2017.
  */

import java.text.SimpleDateFormat
import java.util.Calendar


class Order {
  var bill: Double = 0
  var items: List[Item] = List()
  var tickets: List[Ticket] = List()
  var time: String = "NOT CONFIRMED YET."
  private var log = new Log
  private var check: Double = 0

  /* Time */
  private val format = new SimpleDateFormat("d-M-y HH:mm:ss")
  val timestamp: String = format.format(Calendar.getInstance().getTime)

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
    log.addAction("Control budget", timestamp)
    if (account.budget - bill < 0) {
      println("BUDGET TOO LOW.")
    }
    else {check = check + 1}

      /* Is buyer old enough? */
    log.addAction("Control age", timestamp)
    if (non16.nonEmpty && account.age < 16){
      println("CUSTOMER TOO YOUNG TO BUY TICKETS.")
    }
    else {check = check + 1}

      /* Are there any tickets with same owner? */
   log.addAction("Control owners", timestamp)
    if (listOfNames.length > listOfUniqueNames.length){
      println("NAME WITH MORE THAN ONE TICKET DETECTED.")
    }
    else {check = check + 1}



    if (check == 3) {
      account.changeBudget(-bill)
      time = timestamp
      log.addAction("Order confirmed", timestamp)

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
    println("\n")
  }

}




