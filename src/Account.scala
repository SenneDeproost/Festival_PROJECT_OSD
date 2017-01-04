/**
  * Created by Senne Deproost on 1/01/2017.
  */
class Account(Name: String, Age: Int, Email: String, Budget: Double) {

  val name: String = Name
  val age: Int = Age
  val email: String = Email
  var budget: Double = Budget

  def changeBudget (amount: Double): Double = {
    if (budget + amount >= 0) {
      budget += amount
      budget
    }
    else -1
  }

}


