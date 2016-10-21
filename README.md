# Pong
Java implementation of the Pong game, made for fun.  
  
The game is composed of a table, a ball, and two paddles on the left and right.  
Table: The "world" containing the ball and paddles.  
Ball: The object to be hit back and forth.  
Paddle: The object used to bounce the ball. Controled by player.  
  
Event dispatch thread contruct new Table and invoke Table.setUp().  
setUp() set up JFrame for display. Add KeyHandler implements KeyListener to JFrame. Start Timer thread with 17 milliseconds delay. Start SwingWorker thread GameClock for game internal updates.  
KeyHandler starts 4 SwingWorkers in the contructor, each handling the 'W', 'S', up arrow, and down arrow keys.  
Timer notifies Table, causes invocation of actionPerformed(), which invokes this.repaint(). Behavior of repaint() refers to Table.paintComponent().  
paintComponent() paints, in order: ball, left paddle, right paddle, and scores.  
GameClock invoke Table.updateAll() with 16 milliseconds intervals. updateAll() invoke Ball.update().  
update() checks for and updates, in order: player scoring, border collision and corresponding vector changes, paddle collision and corresponding vector changes, and coordinates changes.  
  
Game finished. Future features include AI, more complex physics for ball, etc.
