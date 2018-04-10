# Online bill payment application
The application provides a register and a log in interface. In the log in section the user can log in to the account as a client, or as a provider. The client can pay a bill, select a new provider, remove a provider, add money to the account, and log out. The provider can view its clients, create a bill for a client and log out. This application has a visual interface created with JavaFX using Scene Builder. For this project I learned to use a Relational Database Management System (MySQL), and JavaFX.

· Clients can register with service providers.
· Service providers send the invoice for services consumed by a particular customer and due date at the end of the month in the database.
· A customer can log into the app and can choose the service providers for which they want to pay their bills online.
· A customer can only disable their choice for a particular provider if they have all their bills paid.
· Virtually a customer can add money to his account and can only make payments if the amount in the account is greater than or equal to the invoice issued.
· If a customer does not pay his bills for 3 consecutive bills, the supplier's services will be canceled after all debts have been paid and the equivalent of a fine paid.
 
- providers are hard-coded in the database
- actors (suppliers, customers (choose providers)
- log in - sign in
- create account - sign up
- add money to your account
- pay bills / fines
- select / deselect suppliers
- change the status of a paid bill

# T-shirt shop project
Implemented a project that deals with a T-shirt shop. There is an initial stock in the store.
The initial stock must contain unique items. If it is with unique elements, then there can be done
some selling-buying T-shirts. For every such transaction we know the number of pieces
traded (sold or bought). Show separate reports in different files that put in
tracking the current stock.
A T-shirt is characterized by:
- code
- color
- manufacturer
- price
- size

The original stock is read from a text file.
