import java.util.ArrayList;
import java.util.Stack;

public class Dynamic_Maze {

	///Maze Index class
	public  class MazeIndex {
		int row;
		int col;

		MazeIndex(int a,int b){
			this.row=a;
			this.col=b;
		}

		boolean compareIndex(MazeIndex index1,MazeIndex index2){
			if (index1.row==index2.row && index2.col==index1.col) {
				return true;
			}
			else return false;	
		}
	}
	///--class ends

	//--maze class Starts--
	public class Maze {

		int[][] inMaze;
		int[][] solMaze;
		MazeIndex ratIndex;
		boolean mazeSolved=false;
		MazeIndex startIndex;
		MazeIndex desIndex;
		int sizeN;
		Stack<MazeIndex> path =new Stack<MazeIndex>();

		Maze(int[][] inMaze,MazeIndex start,MazeIndex end){
			this.inMaze=inMaze;
			this.startIndex=start;
			this.desIndex=end;
			this.path.push(start);
			this.sizeN=this.inMaze.length;
			this.solMaze=new int[this.sizeN][this.sizeN];
			this.setTraversed(start);
			this.ratIndex=new MazeIndex(start.row,start.col);
		}

		void ratRun(){
			System.out.println("Rat is in :("+this.ratIndex.row+ " "+this.ratIndex.col+")");
			if (this.isDest(this.ratIndex)) {
				System.out.println("Found a way");
				this.mazeSolved=true;
			}else
				if (this.isStart(this.ratIndex) && this.noPossibleWay(this.ratIndex)) {
					System.out.println("NO possible way");
					this.mazeSolved=true;
				}else if (mazeSolved==false) {
					ArrayList<MazeIndex> validDir=this.getValidDirections(this.ratIndex);
					if (validDir.size()==0) {
						this.backtrack();
						ratRun();
					}else
						for (MazeIndex mazeIndex : validDir) {
							if (mazeSolved==false) {
								traverse(this.ratIndex, mazeIndex);
								this.ratRun();
							}else break;
						}
				}
		}




		///SMALL PRIVATE FUNCTIONS----
		private boolean  noPossibleWay(MazeIndex index){
			return (this.getValidDirections(index).size()==0);

		}
		//Get all Valid Directions
		private	ArrayList<MazeIndex> getValidDirections(MazeIndex index){
			index=this.ratIndex;
			ArrayList<MazeIndex> lst=this.getAllPossDir(index);
			ArrayList<MazeIndex> lst2=new ArrayList<MazeIndex>(lst);

			for (MazeIndex mazeIndex : lst) {

				if (IsPossibleMove(this.ratIndex, mazeIndex)==false) {
					lst2.remove(mazeIndex);
				}
			}

			return lst2;
		}
		//--Get All Possible Directions
		private ArrayList<MazeIndex> getAllPossDir(MazeIndex index){

			ArrayList<MazeIndex> indesis= new ArrayList<MazeIndex>();
			if (index.row<this.sizeN-1) {
				indesis.add(this.getBottomIndex(index));
			}
			if (index.row>0) {
				indesis.add(this.getTopIndex(index));					
			}
			if (index.col<this.sizeN-1) {
				indesis.add(this.getRightIndex(index));					
			}			 
			if (index.col>0) {
				indesis.add(this.getLeftIndex(index));
			}				 
			return indesis;
		}		 

		private boolean isStart(MazeIndex ratIndex){
			return (this.ratIndex.compareIndex(ratIndex, this.startIndex));	
		}


		private boolean isDest(MazeIndex ratIndex){
			return (this.ratIndex.compareIndex(ratIndex, this.desIndex));		
		}

		private boolean isTraversed(MazeIndex index){
			return (this.solMaze[index.row][index.col]==1);
		}

		private int[][] setTraversed(MazeIndex index){
			this.solMaze[index.row][index.col]=1;
			return this.solMaze;
		}

		private boolean IsPossibleMove(MazeIndex ratIndex,MazeIndex neighIndex){
			return (this.isTraversed(neighIndex)==false &&  this.inMaze[neighIndex.row][neighIndex.col]==1); 
		}
		private	void updateRatIndex(MazeIndex newIndex){
			this.ratIndex.row=newIndex.row;
			this.ratIndex.col=newIndex.col;
		}
		private int[][] traverse(MazeIndex ratIndex,MazeIndex neiIndex){
			System.out.println("Rat Moves to :["+neiIndex.row+" "+neiIndex.col+"]");
			this.setTraversed(neiIndex);
			this.updateRatIndex(neiIndex);
			this.path.add(neiIndex);
			this.displaySol();
			return solMaze;
		}

		private MazeIndex backtrack(){


			this.path.pop();
			this.ratIndex=this.path.lastElement();
			System.out.println("Backtracked to :["+this.ratIndex.row+" "+this.ratIndex.col+"]");
			return this.path.lastElement();

		}

		private MazeIndex getLeftIndex(MazeIndex index){
			MazeIndex newI=new MazeIndex(index.row, index.col-1);
			return newI;
		}
		private MazeIndex getRightIndex(MazeIndex index){
			MazeIndex newI=new MazeIndex(index.row, index.col+1);		
			return newI;
		}
		private MazeIndex getTopIndex(MazeIndex index){
			MazeIndex newI=new MazeIndex(index.row-1, index.col);		
			return newI;
		}
		private MazeIndex getBottomIndex(MazeIndex index){
			MazeIndex newI=new MazeIndex(index.row+1, index.col);
			return newI;
		}
		// PRIVVATE -----------

		void displaySol(){
			for (int i = 0; i < this.solMaze.length; i++) {
				for (int j = 0; j <this.solMaze.length; j++) {
					System.out.print("  "+this.solMaze[i][j]);
				}System.out.println(" ");
			}
			System.out.println("----------------------------------------------");
		}
	}	
	//--maze class ends--	
	///--------------------------------------------MAIN FUNC--------------

	public static void main(String[] args) {
		//			int maze[][] = { 
		//					{ 1, 0, 0, 0 }, 
		//					{ 1, 1, 0, 1 }, 
		//					{ 0, 0, 0, 1 }, 
		//					{ 1, 1, 1, 1 } }; 
		int maze[][] = { 
				{ 1, 0, 0, 0 ,1, 0, 0, 0 }, 
				{ 1, 1, 1, 0 ,1, 0, 1, 1 }, 
				{ 0, 0, 1, 1 ,1, 0, 1, 0 },  
				{ 0, 1, 1, 0 ,1, 0, 1, 0 },
				{ 1, 1, 0, 0 ,1, 1, 1, 0 }, 
				{ 1, 1, 0, 0 ,1, 0, 1, 0 }, 
				{ 1, 1, 0, 0 ,1, 0, 1, 0 }, 
				{ 1, 1, 1, 1 ,1, 1, 1, 1 }, 				

		};
		Dynamic_Maze Dm=new Dynamic_Maze();
		MazeIndex srt=Dm.new MazeIndex(0, 0);
		MazeIndex end=Dm.new MazeIndex(1,maze.length-1);
		Maze newMaze=Dm.new Maze(maze, srt, end);
		newMaze.ratRun();
		newMaze.displaySol();
	}

	//-------------------MAIN FUN ENDS -----------------------------


}// Dynamic_Maze class close-------
