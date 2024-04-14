Use this file to record your reflection on this assignment.

- Tell me about your class! What does it represent?

    This class represents a player character in a D&D - style closed-world exploration / puzzle solving game. Within this class a player can move around, examine / take / drop objects, and observe their surroundings. They automatically tire when certain actions are taken (walk, grow, shrink, fly), and can be forced to rest when they get too tired (or can take a nap of their own volition). 

    Along with the Player() class, I also made the Place() and Item() classes. These are both very bare-bones classes becuase they are meant to be the parent classes for specific child classes in the future (i.e. a Boat() that is an extension of Item() or a BedRoom() that is an extension of Place()). 
- What additional methods (if any) did you implement alongside those listed in the interface?

To start, here are the things I didn't do, even though one could reasonably consider then within the scope of this program:
* I didn't make the grab() and drop() methods take in Item()s even though that makes the most sense, because I decided that was a scaling of this program that could wait until the next stage of building. 
Make the exploration stage that requires use of the grow(), shrink(), or fly() methods. They are location-specific, and my goal was to just make the skeleton of the program, not design the implementation. 

Ok, now for the additional methods! 
* An overloaded examine() that takes an Item as the argument instead of a string. This is so that in a more final implementation of this program, I can fetch descriptions from within the objects they belong to. 
* An overloaded use() that takes an Item. This connects to the Item.use() function, which is specific to each Item subclass. 
* lookAround(): prints the description of the current place for if the player needs reminding of where they are
* recordAction() and recordArgs(): For recordable actions (grab(), drop(), walk(), shirnk(), grow()) adds the action/corresponding argument to a list of 10 most recent actions. These are the actions that can be undone. If more than 10 actions are recorded, these functions boot the first item (aka least recent) out of the list, move everything over one slot, and add the new action to the end of the list (most recent action). 
* printActions(): prints the list of the last 10 recordable actions

- What worked, what didn't, what advice would you give someone taking this course in the future?

Worked: Setting up support classes was less complicated than I thought it would be! Big win there
Didn't work: I attempted to make the Player.undo() function literally destroy the player instance it was called from. AFter a decent amount of research + office hours, I have realized that is not possible/advisable to do. 
Advice: Start by planning out what each method will do before writing any of the code. I started by writing the code for the methods whose implementation was easiest to think of, then ended up getting a little stumped on how to apply the trickier methods to what I had already built. It ended up working out in the end, but was slightly more complicated than it probably needed to be. 
