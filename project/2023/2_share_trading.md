## Project Overview

An implementation of an online share trading application that allows Traders and Companies to list shares for sale and 
for other Traders to purchase shares and track their personal investments. 

## Application Domain

In this project, you and your team are tasked with implementing an online share trading application. Using the
application, Customers can invest in shares of all types of Companies and manage their portfolio of investments.

There is only one Administrator who is responsible for managing the application. To ensure the smooth operation of the 
application, they are responsible for screening all listings of new shares from Companies and approving or rejecting 
them.
They must also be able to view the current portfolio of all users of the system as well as all Company listings.

A Company can create a new listing in the application by specifying how many shares they want to sell. The listing 
to sell shares are then sent to the Administrator to approve or decline a share listing.
Shares of a Company are listed in the central exchange which can be viewed by all Customers under a unique code given 
to Companies to identify them (you do not need to follow this naming convention, but an example is the Australian Stock 
Exchange: [here](https://www.asx.com.au/asx/research/codeLookup.do?by=searchByName&returnToFormIndex=quicksearch&codeFormElement=ASXCodes&nameFormElement=undefined&nameToSearch=a)).

Customers can use the application to search for shares by industry (e.g. finance, manufacturing, banking, etc.), price,
or Company name.
Customers use the application to purchase shares of Companies - they can specify the number of shares to purchase (so
long as this is equal to or less than the shares listed for that Company).
Customers also have the ability to sell their shares after purchase.

The price of the shares of a Company are set by the Administrator and can be changed at any moment.
Where the price of a Company's shares has been changed, any attempts to purchase at the old price will be rejected by 
the system.

A Customer can use the system to log in to see the number of shares of each Company they have invested in.
They can also use the application to view their current profit or loss - the application can compare the current value
of their shares with the value at the time they purchased it to show the Customer the amount of money they have made 
(or lost).
