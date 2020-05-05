
---------------//---Maze Algorithm---//--------


Specifications:-
-------------------
1.Can solve matrix of any nXn Size.
2.Can Traverse in all four directions.
3.Can Backtrack
4.Uses Depth First Search Algorithm for traversing
5.Each small functionalities of a Maze is simplify as a seperate functions
6.ObjectOriented approach ie.capability of handling two or more different Maze as different Object

Drawbacks:-
-------------------
1.Cannot Traverse diagonally.
2.Actual code seems to look complex(but actually not)
3.Lenghty code
4.0(n) time complexity
5.~0(n) space complexity



Programatic Algorithm
-------------------
1-Create a Class Maze Index with to states/properties row and col
	1.1-with parametrezied contructor to intialize both the properties
	1.2-Class behavior 
		-compare two index 
2-Create a class named Maze with;
    States
	-StartIndex
	-DestinationIndex
	-RatCurrentIndex
	-inPut MazeMatrix(with zeros and ones)
	-solution MazeMatrix(initialy filled with zeros)
	-mazeSolved[boolean]
	-SizeOfMaze
	-pathStack(Index)

    Main Behaviours
	-ratRun
	-display the outPut Maze

    Private MainBehaviours
	-Traverse
	-backtrack
	-UpdateRatIndex
	-setTraversed

    Private Behaviors
	-checkReached [boolean]
	-getAllPossibleDirections
	-checkForNopossible way [boolean]
	-getValidDirections 
	-compare with StartingIndex [boolean]
	-compare with DestinationIndex [boolean]
	-Possiblity Of a Move [boolean]
	-get Left/right/Top/Bottom Index
	
Main funcntionality of Maze(Actual Algorithm)
1-	Get InputMaze,StartIndex,DestinationIndex and Set values.
2-	Set StartIndex and update in solution matrixMaze.
3-	RatRuns
		3.1 If Rat current is the Destination stop and print the output Matrix then set variable MazeSolved as true
		3.2 else	check if CurrentIndex is startIndex and no possible Ways to traverse
			 	print no possible ways and stop	then set variable MazeSolved as true(there is no solution of this maze is a solution and hence its mazeSolved is true)		
		3.3 else	
				3.3.1 if not a startIndex but has no possible way
				3.3.2 BACKTRACK
		3.4 Else	Get all valid directions and for each Direction if Maze not Solved
				3.4.1	traverse/update RatIndex
				3.4.2	Mark updated RatIndex as travered in output Maze
				3.4.3	RunRat from updated RatIndex
`		3.5 Reapeat the steps from 3.1 to 3.4 until a variable mazeSolved is set to true


	[warning : the Actual code seems to look complex but it is very much simplyfied as small small functions]
---------------------------------------THE END------------------------------------------------------------------------------------	


 