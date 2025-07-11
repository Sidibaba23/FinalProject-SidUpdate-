Basic Functionality of TextBased Adventure Game - Lost Residence

The rooms file consists of 34 rooms.

Each Room contains:

Room ID
Room Description
Boolean value visited (pre-set to false)
value of the room being blocked
value of the room being locked
Number of directional movement choices and their corresponding room ID's
Direction 1
Direction 2
etc.

The items file consists of 5 items.

Each Item contains:

Item ID
Room ID (Location)
Item Name
Item Description

The monster file consists of 5 monsters

Each Monster contains:
Monster ID
Room ID
Monster Name
Monster Description
Hitpoints
value for if it's dead

Eact .txt file need to be located outside the src directory in the project folder.


------List of Valid Movement Commands------
--NORTH (N)      --SOUTH (S)    --NORTH WEST (NW) 
--EAST (E)       --WEST (W)     --NORTH EAST (NE)
--UP (U)         --DOWN (D)     --SOUTH WEST (SW)
--BACK (B)/(BP)  --FORWARD (F)  --SOUTH EAST (SE)
-------List of Valid Item Commands-------
--COLLECT (C)    --DROP (DR)
--INSPECT (I)    --BACKPACK/PACK (BP)/(P)
--USE     (USE)
-------List of Valid Challenge Commands-------
--RETREAT (R)    --ATTACK (A)
-------Other Noteworthy Commands-------
--LOOK (L) + [DIRECTION]: This will allow the player to see all the details about the current room they're in.--
--MAP (M): This will allow the player get a list of all the rooms within the game.--
--EXIT (X): This will allow the player to exit the game at any time.--
--HELP/HINT (H): This will allow the player to get a for a hint for the current challenge.--
--COMMAND (CMD): This will allow the player to get a list of all available commands in the game at any time.--
--STATS (HP): This will allow the player to see all the details about their stats (HP, Attack, Location, and Score).--
--The keyword command [PACK] is also used to look inside the player's backpack!--


