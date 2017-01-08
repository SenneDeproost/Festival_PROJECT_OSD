/**
  * Created by Senne Deproost on 1/01/2017.
  */

import java.text.SimpleDateFormat
import java.util.Calendar


class Order {
  var bill: Double = 0 /* Het totale bedrag van de bestelling. */
  var items: List[Item] = List() /* Houdt een lijst van items bij. */
  var tickets: List[Ticket] = List() /* Houdt een lijst van tickets bij. */
  var time: String = "NOT CONFIRMED YET."
  private var log = new Log
  private var check: Double = 0 /* Check wordt gebruikt om te controleren aan hoeveel voorwaarden er zijn voldaan om een
                                 * order te confirmen. Als er aan een bepaalde voorwaarde wordt voldaan, wordt check met
                                 * 1 verhoogt. Op het einde moet check gelijk zijn aan het aantal voorwaarden die gehaald
                                 * moeten worden. Anders zal de transactie niet doorgaan en krijgt de gebruiker een bericht*/

  /* Time */

  def TimeStamp(): String ={
    val format = new SimpleDateFormat("dd-MM-y HH:mm:ss")
    format.format(Calendar.getInstance().getTime)
  }

  // ADD
  /* Hier wordt verschil gemaakt tusen het toevoegen van een ticket en het toevoegen van een item. */

  def addItem(item: Item): Unit = {
    bill += item.price
    items = item :: items

  }


  def addTicket(ticket: Ticket): Unit = {
    bill += ticket.price
    tickets = ticket :: tickets

  }

  // CONFIRM
  var non16: List[Ticket] = tickets.filter(_.category != "Early_bird")

  def confirm(account: Account): Unit = {
    check = 0 /* Reset de check. */
    /* Hulplijsten */
    var non16: List[Ticket] = tickets.filter(_.category != "Early_Bird")
    var listOfOwners: List[String] = tickets.map(_.owner)
    var listOfUniqueOwners: List[String] = listOfOwners.distinct


    // CONDITIONS
    /* Een voorwaarde kan makkelijk verwijderd of toegevoegd worden door een if clause met bijhorende else clase te veranderen.
     * Let wel op dat er steeds een log.addAction aan voorafgaat en dat het x aantal voorwaarden gelijk is aan de conditie
     * (check == x) bij de confirmatie van de order.*/

    /* Has the account enough budget? */
    log.addAction("Control budget", TimeStamp())
    if (account.budget - bill < 0) {
      println("BUDGET TOO LOW")
    }
    else {check = check + 1}

      /* Is buyer old enough? */
    log.addAction("Control age", TimeStamp())
    if (non16.nonEmpty && account.age <= 16){
      println("CUSTOMER TOO YOUNG TO BUY TICKETS")
    }
    else {check = check + 1}

      /* Are there any tickets with same owner? */
   log.addAction("Control owners", TimeStamp())
    if (listOfOwners.length > listOfUniqueOwners.length){
      println("OWNER WITH MORE THAN ONE TICKET DETECTED")
    }
    else {check = check + 1}


    // CONFIRMATION

    if (check == 3) {
      account.changeBudget(-bill)
      time = TimeStamp()
      log.addAction("Order confirmed", TimeStamp())

      // Email
      printMail(account)

    }
    else {
      println("TRANSACTION HAS BEEN CANCELED")
    }
  }


  // SORTING

  def sortList(a_list: Any): Unit = {
    if (a_list == items){
      items.sortWith(_.price > _.price)
    }
    else if (a_list == tickets){
      tickets.sortWith(_.owner > _.owner)
    }
  }


  // PRINTING

  private def printItem(element: Item): Unit = {
    println(element.item + "  " + "€" + element.price)
  }

  private def printTicket(element: Ticket): Unit = {
    print("ID: " + element.ID + "\n")
    println(element.category + "  " + "€" + element.price)
    println("Owner: " + element.owner)
    println("\n")
  }

  private def printMail(account: Account): Unit ={
    items = items.sortWith(_.price > _.price)
    tickets = tickets.sortWith(_.owner < _.owner)

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




