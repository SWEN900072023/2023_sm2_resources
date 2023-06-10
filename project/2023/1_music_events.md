## Project Overview

An implementation of an online event booking application that allows Customers to book tickets to events.
The application is used by one Administrator who operates a number of musical venues across Australia that host live 
music events. Similar to Ticketek, Event Planners list their events on the application and Customers use the site to 
book tickets.

## Application Domain

In this project, you and your team are tasked with implementing an online application for booking tickets to live 
music events across Australia.

There is only one Administrator of the application: the Administrator operates venues across Australia but does not
itself organise any musical events. For example, they would not be responsible for creating musical events but 
Administrators are responsible for creating venues in the system.
To reflect the real world, venues have different sections (mosh, standing, seated, VIP, etc.), and each section 
must have a capacity. The price of the tickets themselves are dependent upon the musical artist.

The Administrator is responsible for managing the application and can view all users of the system.
They can also view all events across Australia, as well as all Customers who have purchased tickets to events and the
ticket details.

Event Planners use the system to create their events in the application. An event must be proposed with an associated 
venue and date - if a venue does not exist, then the event cannot be created against it. The Administrator is 
responsible for reviewing the proposals and accepting or rejecting them. More than one event cannot take place at the 
same venue at the same time.

The application must support multiple different authenticated users acting as the one Event Planner. That is to say, an
event can have one or more associated Event Planners who have the ability to manage the event.

Customers should be able to search for music acts by name or through a calendar view of all upcoming acts up to 6 
months from the current date.
Customers can use the application to book tickets for one, or many people (for example, a family of 4 could book
4 separate tickets to the same event in the same transaction).

Event Planners have the ability to modify events by changing the date or cancelling the event entirely. They can
also change the price of seats.

Both Customers and Event Planners have the ability to view or cancel their bookings, as well as modify the number of 
people on the booking.

> To simplify the application's implementation, you do not need to implement payment. A customer simply reserving a
> hotel is sufficient.
