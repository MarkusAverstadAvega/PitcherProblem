# PitcherProblem
A solution for the pitcher problem seen, for example, in Die Hard 3: With a Vengeance.

##To package and run:
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
  
##Disclaimer:
This solution uses a very simple algorithm at present due to time constraints imposed by my current assignment and 
upcoming vacations. As such I will outline the next steps I would take to improve this application given the time.

**Implement a tree search algorithm**
At present the application tries a single solution to the problem given and reports it. This may or may not be the
best solution possible. In order to find the best solution possible we would need to test all possible next steps
each step of the way. 

As such, given that we have two empty pitchers of 5 and 3 liters respectively we would have to create the following sets:
- Filled large pitcher, empty small pitcher
  - \[5 0]
- Filled small pitcher, empty large pitcher
  - \[0 3]

At the next step we would have to:
- Pour large pitcher into small pitcher
  - \[2 3]
- Pour small pitcher into large pitcher
  - \[3 0]
  
And on the step after that we would have to:
- Empty the large pitcher
  - \[0 3]
  - \[0 0] (This branch stops here, because both pitchers are now empty)
- Empty the small pitcher
  - \[2 0]
  - \[3 0] (This branch stops here, because emptying an empty pitcher would be pointless)
- Fill the large pitcher
  - \[5 3]
  - \[5 0] (This branch stops here, because filling a partially filled bottle would stop our progress)
- Fill the small pitcher
  - \[2 3] (This branch would go nowhere, but will be stopped next step by the logic above)
  - \[3 3]
  
... And so on. Each branch would be stopped when it becomes meaningless or when it has hit its target volume.

**Delegate to services**
Currently the main app has a lot of responsibility. I would delegate much of it to service classes, specifically all
work that was done and probably object creation as well.

**Implement AKKA**
When we have very large pitchers and very small target volumes this program would become progressively slower. This
could be offset greatly by implementing AKKA, which would delegate each unit of work to a separate worker, allowing
the overall work to be completed much faster.