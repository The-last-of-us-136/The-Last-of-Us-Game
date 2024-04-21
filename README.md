# The-Last-of-Us-Game
A Java-Based 2D version of the original combat game the last of us with backend and UI implemented

Roam the map of the last of us with your hero trying to attack zombies and gather other heroes to win.
The player starts off in a 15x15 grid map with just one hero and 10 zombies. The player can
only see the directly adjacent cells next to their pool of heroes. The player then keeps taking
his turn trying to collect vaccines, and cure or kill zombies. The game ends when the player
has collected and used all vaccines or when all heroes have been overwhelmed and defeated by
the zombies.

The player only wins if he has successfully collected and used all vaccines, and has 5 or more
heroes alive.

Each hero has a specified amount of health, attack damage,special trait and limited moves each turn, so choose wisely. Collect vaccines to cure a zombie unlocking another other controlable heroes. Collect supplies to use the hero's special trait. When multiple targets ar nearby, use the select target list to set your target. Beware of entering trap cells that cause damage to the hero.

There are 3 heroes types(3 different special traits):
  - Fighter : Special allows the fighter to attack zombies multiple times without counting these attacks as actions till the end of the turn.
  - Medic : Special allows the medic to restore his or other nearby heroes health.
  - Explorer : Special allows the explorer to be able to see the whole map till the end of the turn.

When a hero attacks a zombie, the zombie defends causing a 5HP damage to the hero.When no more heroes have moves use the end turn button to restore all moves back. At the end of each turn, zombies attack adjacent heroes and the cells that are not surrounded by a hero becomes unknown,finally a new zombie is spawned.

AI mode is a walkthrough for the game with equal chances of winning or losing.
