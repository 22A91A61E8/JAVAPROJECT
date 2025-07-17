A simple recreation of the classic Flappy Bird game using Java Swing and AWT libraries. This project demonstrates game development fundamentals like animation, collision detection, GUI design, and real-time event handling.

Game Overview: Flappy Bird is an arcade-style game where the player controls a bird attempting to fly between columns of pipes without hitting them. Press the spacebar to make the bird flap and navigate through the gaps.

Project Structure: FlappyBirdProject

App.java # Main class to start the game window
FlappyBird.java # Core game logic and rendering
flappybird.png # Bird image
flappybirdbg.png # Background image
toppipe.png # Top pipe image
bottompipe.png # Bottom pipe image
Setup:

Ensure you have Java JDK installed. Open a terminal (or cmd) and navigate to the project folder.
Compile the code: javac FlappyBird.java javac App.java
Run the game: java App
Controls: Press Spacebar to flap and keep the bird airborne. Game restarts automatically after a crash if you press space again.

Key Features:

Smooth real-time game loop using Timer
Basic gravity and jump physics
Randomized pipe generation
Collision detection
Scoring system
Game-over and restart mechanism
