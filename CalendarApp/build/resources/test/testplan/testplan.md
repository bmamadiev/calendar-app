#Use Case: Create Conference Link
##Preconditions:
- The user selects the conference link menu.
  Invariants:
- The terminal is not closed
##Postconditions:
- A new conference link is created.
##Steps:
1. The user enters CONFERENCELINKS.
2. The user enters CREATE
3. The user enters an event id
4. The software creates and displays a conference link

#Use Case: Return A Conference Link That Was Already Created
##Preconditions:
- The user selects the conference link menu.
##Postconditions:
- Already existing link is returned.
##Steps:
1. The user enters CONFERENCELINKS.
2. The user enters CREATE
3. The user enters an event id
4. The software returns already existing conference link

#Use Case: Delete A Conference Link That Was Already Created
##Preconditions:
- The user selects the conference link menu.
##Postconditions:
- Link deleted.
##Steps:
1. The user enters CONFERENCELINKS.
2. The user enters DELETE
3. The user enters an event id
4. Link deleted.

#Use Case: Create A Conference Link for a Non-Existent Event
##Preconditions:
- The user selects the conference link menu.
##Postconditions:
- Throws exception
##Steps:
1. The user enters CONFERENCELINKS.
2. The user enters CREATE
3. The user enters an event id
4. Error message throws

#Use Case: Get A Conference Link for a Non-Existent Event
##Preconditions:
- The user selects the conference link menu.
##Postconditions:
- Error message throws
##Steps:
1. The user enters CONFERENCELINKS.
2. The user enters GET
3. The user enters an event id
4. Error message throws

#Use Case: Join A Conference Link for a Non-Existent Event
##Preconditions:
- The user selects the conference link menu.
##Postconditions:
- Error message throws
##Steps:
1. The user enters CONFERENCELINKS.
2. The user enters JOIN
3. The user enters an event id
4. Error message throws

#Use Case: Get A Conference Link for a Non-Existent Conference Link
##Preconditions:
- The user selects the conference link menu.
##Postconditions:
- Error message throws
##Steps:
1. The user enters CONFERENCELINKS.
2. The user enters GET
3. The user enters an event id
4. Error message throws

#Use Case: Join A Conference Link for a Non-Existent Conference Link
##Preconditions:
- TODO: The user selects the conference link menu.
##Postconditions:
- Error message throws
##Steps:
1. The user enters CONFERENCELINKS.
2. The user enters JOIN
3. The user enters an event id
4. Error message throws
