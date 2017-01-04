val hamburger = new Item("hamburger", 5.00)
val cola = new Item("cola", 1.00)
val bier = new Item("bier", 2.00)

/* SIMMULATION */
val A = new Account("Senne Deproost", 21, "sennedeproost@hotmail.com", 400)
val O = new Order()
val T = new Early_Bird(A)

// Order
O.addItem(cola)
O.addItem(hamburger)
O.addItem(bier)
O.addTicket(Early_Bird(A))
O.items

O.confirm(A)
A.budget
