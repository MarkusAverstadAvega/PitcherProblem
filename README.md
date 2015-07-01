# PitcherProblem
A solution for the pitcher problem seen, for example, in Die Hard 3: With a Vengeance.

To package and run:
1. Ensure that the following are installed:
- Java 8 JDK
- Maven
2. Execute the following command from the terminal/console in the project root: 
- mvn assembly:assembly
3. To run a demo, execute the following command in the /target directory: 
- java -jar PitcherProblem-jar-with-dependencies.jar 
4. To define your own run case, execute the command below:
- java -jar PitcherProblem-jar-with-dependencies.jar 5 3 1
- Where 5 3 1 is Pitcher 1 volume, Pitcher 2 volume and target volume in that order.