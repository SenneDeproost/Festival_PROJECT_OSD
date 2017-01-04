/**
  * Created by Senne Deproost on 4/01/2017.
  */
class Log {

  var timeList: List[String] = List()
  var actionList: List[String] = List()

  def addAction(action: String, time: String): Unit ={
    timeList = time :: timeList
    actionList = action :: actionList
    showAction()

  }

  def showAction(): Unit ={
      println(timeList(0) + " --- " + actionList(0))
    }

}
