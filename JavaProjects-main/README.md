A simple recreation of the classic **Flappy Bird game** using **Java Swing** and **AWT libraries**. This project demonstrates game development fundamentals like animation, collision detection, GUI design, and real-time event handling.

Game Overview:
Flappy Bird is an arcade-style game where the player controls a bird attempting to fly between columns of pipes without hitting them. Press the spacebar to make the bird flap and navigate through the gaps.

Project Structure:
FlappyBirdProject
1. App.java                # Main class to start the game window
2. FlappyBird.java         # Core game logic and rendering
3. flappybird.png          # Bird image
4. flappybirdbg.png        # Background image
5. toppipe.png             # Top pipe image
6. bottompipe.png          # Bottom pipe image

Setup:
1. Ensure you have Java JDK installed. Open a terminal (or cmd) and navigate to the project folder.
2. Compile the code:  javac FlappyBird.java
                   javac App.java
3. Run the game: java App

Controls:
Press Spacebar to flap and keep the bird airborne.
Game restarts automatically after a crash if you press space again.

Key Features:
1. Smooth real-time game loop using Timer
2. Basic gravity and jump physics
3. Randomized pipe generation
4. Collision detection
5. Scoring system
6. Game-over and restart mechanism
