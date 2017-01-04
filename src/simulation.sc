val hamburger = new Item("hamburger", 5.00)
val cola = new Item("cola", 1.00)
val bier = new Item("bier", 2.00)

/* SIMMULATION */
val A = new Account("Senne Deproost", 16, "sennedeproost@hotmail.com", 400)
val O = new Order()

// Order
O.addItem(cola)
O.addItem(hamburger)
O.addItem(bier)

O.addTicket(new VIP("Ann"))
O.addTicket(new Normal("Senne"))
O.items
O.tickets
O.confirm(A)

val T = new VIP("Senny")