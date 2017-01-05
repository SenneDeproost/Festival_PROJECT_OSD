val hamburger = new Item("hamburger", 5.00)
val cola = new Item("cola", 1.00)
val bier = new Item("bier", 2.00)

/* SIMMULATION */
// Accounts
val A = new Account("Senne Deproost", 55, "sennedeproost@hotmail.com", 400)
val A1 = new Account("Tom", 45, "tom@outlook.com", 478)
val A2 = new Account("Tim Trekvogel", 23, "trek@vogel.com", 332)
val A3 = new Account("Kleine Jan", 16, "Jan@mussenland.com", 9000 + 1)

// Order
val O = new Order
val O1 = new Order()
val O2 = new Order()
val O3 = new Order()
val O4 = new Order()


// Order voor A
O.addItem(cola)
O.addItem(hamburger)
O.addItem(bier)
O.addItem(hamburger)

O.addTicket(new VIP("Ann"))
O.addTicket(new Normal("Senne Deproost"))
O.addTicket(new Early_Bird("Gerard Trekvogel"))

O.confirm(A)

// Order1 voor A1
O1.addItem(cola)
O1.addTicket(new VIP("Tim Trekvogel"))

O1.confirm(A1)

//  Order 2
O2.addItem(cola)
O2.addItem(new Item("privé_springkasteel", 100))
O2.addItem(bier)

O2.confirm(A) // Test of A nog kan bestellen.
O2.confirm(A2)

// Order 3
O3.addTicket(new Early_Bird("Tom"))
A2.changeBudget(100)
O3.addTicket(new Normal("Tom"))
O3.confirm(A2) // Test of ticket met zelfde naam kan besteld worden

// Order 4
O4.addTicket(new Early_Bird("Timmy"))
O4.addTicket(new VIP("Neo"))
O4.confirm(A3) // Test of account de juiste leeftijd heeft voor tickets.
