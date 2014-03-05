// S. Matthew English
// University Number: 2013950101

import robocode.*;
import java.util.ArrayList;
import comp7307.*;

public class StudentRobot extends TeamRobot {
	static final int TILE_SIZE = 48;
	char[][] battleMap = null; // map
	
	public static int end = 0;
	public static int endII = 0;
	
	char[] oneDim = null;

	int col=0;
	int row=0;
	 
	int mapSize;
	
	int placeHolder = 0;
	
	boolean foundSolution = false;
	boolean inside = false;
	
	ArrayList<Integer> myCoords = new ArrayList<Integer>(); 
	ArrayList<Integer> moves = new ArrayList<Integer>();
	ArrayList<Integer> treadX = new ArrayList<Integer>();
	
	public void onCustomEvent(CustomEvent e) 
	{
		if (e.getCondition().getName().equals("MapDistributionEvent")) 
		{
			MapDistributionEvent event = (MapDistributionEvent) e
					.getCondition();

			battleMap = event.getMap();
			

			oneDim = twoDimToOneDim(battleMap);

				
			row = battleMap.length; // number of rows    
			col = battleMap[0].length;
			
			end = endOne(oneDim);
			endII = endTwo(oneDim);
			
			moves.add(-1);
			moves.add(-1*col);
			moves.add(1);
			moves.add(col);
			
			int result = start(oneDim, row, col);
			
			
			System.out.println("(value of result)start is:"+oneDim[result]);
		
			System.out.print("result (before it goes to make move) is:"+result);
			makeMove(result);
			onAndOn(myCoords);
			
			
			
			
			
			
			
			
		}
	}
	
	public void onAndOn(ArrayList<Integer> myCoords)
		{
			for(int x=0;x<myCoords.size()-1;x++)
			{
			int heading = (int)getHeading();
				if((heading == 0 )||(heading == 360))
				{
					if((myCoords.get(x+1).intValue())==((myCoords.get(x).intValue()+col)))
					{
						turnRight(180);
						ahead(TILE_SIZE);
					}
					if((myCoords.get(x+1).intValue())==((myCoords.get(x).intValue()-col)))
					{
						ahead(TILE_SIZE);
					}
					if((myCoords.get(x+1).intValue())==((myCoords.get(x).intValue()-1)))
					{
						turnLeft(90);
						ahead(TILE_SIZE);
					}
					if((myCoords.get(x+1).intValue())==((myCoords.get(x).intValue()+1)))
					{
						turnRight(90);
						ahead(TILE_SIZE);
					}
				}
				if(heading == 90 )
				{
					if((myCoords.get(x+1).intValue())==((myCoords.get(x).intValue()+col)))
					{
						turnRight(90);
						ahead(TILE_SIZE);
					}
					if((myCoords.get(x+1).intValue())==((myCoords.get(x).intValue()-col)))
					{
						turnLeft(90);
						ahead(TILE_SIZE);
					}
					if((myCoords.get(x+1).intValue())==((myCoords.get(x).intValue()-1)))
					{
						turnLeft(180);  //this would NEVER HAPPEN!
						ahead(TILE_SIZE);
					}
					if((myCoords.get(x+1).intValue())==((myCoords.get(x).intValue()+1)))
					{
						ahead(TILE_SIZE);
					}
				}
				if(heading == 180 )
				{
					if((myCoords.get(x+1).intValue())==((myCoords.get(x).intValue()+col)))
					{
						ahead(TILE_SIZE);
					}
					if((myCoords.get(x+1).intValue())==((myCoords.get(x).intValue()-col)))
					{
						turnRight(180);
						ahead(TILE_SIZE);
					}
					if((myCoords.get(x+1).intValue())==((myCoords.get(x).intValue()-1)))
					{
						turnRight(90);
						ahead(TILE_SIZE);
					}
					if((myCoords.get(x+1).intValue())==((myCoords.get(x).intValue()+1)))
					{
						turnLeft(90);
						ahead(TILE_SIZE);
					}
				}
				if(heading == 270 )
				{
					if((myCoords.get(x+1).intValue())==((myCoords.get(x).intValue()+col)))
					{
						turnLeft(90);
						ahead(TILE_SIZE);
					}
					if((myCoords.get(x+1).intValue())==((myCoords.get(x).intValue()-col)))
					{
						turnRight(90);
						ahead(TILE_SIZE);
					}
					if((myCoords.get(x+1).intValue())==((myCoords.get(x).intValue()-1)))
					{
						ahead(TILE_SIZE);
					}
					if((myCoords.get(x+1).intValue())==((myCoords.get(x).intValue()+1)))
					{
						turnRight(180);
						ahead(TILE_SIZE);
					}
				}
			
			}
		}
			
	public int endOne(char[] oneDim)
	{
	for (int i = 0; i < oneDim.length; ++i)
			{
				if(oneDim[i] == '3')
				{
				end = i;
				System.out.println(" end: "+end);
				}
			}
			return end;
	}
	
	public int endTwo(char[] oneDim)
	{
	for (int i = 0; i < oneDim.length; ++i)
			{
				if(oneDim[i] == '3')
				{
				if(i != end)
					{
						endII = i;
						System.out.println(" endII: "+endII);
						
					}
				}
			}
			return endII;
	}
	
	public int start(char[] oneDim, int row, int col)
	{
	mapSize = row * col;
			
			double x = getX();
			double y = getY();
			
			int position_x = (int)Math.floor(x/TILE_SIZE);
			int position_y = (int)Math.floor(y/TILE_SIZE);
			
			
			
					
			int pos_y = position_y * col;
			
			int pos_x = col - position_x;
			
			int yes = pos_y + pos_x;
			
			int result = mapSize - yes;
			System.out.println(" result(in the start method is): "+result);
			return result;
	}
	
	public static char[] twoDimToOneDim(char[][] twoDim) 
			{
        	char[] oneDim = new char[numberOfElements(twoDim)];
        	// Loop through the 2d array and place each element in the 1d array
			int number = 0;
			
        	for (int i = 0; i < twoDim.length; ++i)
			{
				for (int j = 0; j < twoDim[i].length; ++j) 
				{
					oneDim[number] = twoDim[i][j];
					number++;
				}
			}
        	return oneDim;
    		}
			
	private static int numberOfElements(char[][] twoDim) 
    		{
    		
       		int number = 0;
        	// Loop through the 2d array and count how many element it contains.
        	for (int i = 0; i < twoDim.length; ++i)
			{
				for (int jj = 0; jj < twoDim[i].length; ++jj) 
				{
				number++;
				}
			}
        	return number;
    		}
			
			
			
	public void makeMove(int current)
    		{
    		System.out.println(current+", ");
    		
    		if(myCoords.contains(end) || myCoords.contains(endII))
    		{
    			System.out.print("destination found");
    			foundSolution = true;
    			return;
    		}

    		for(int x=0; x<4; x++)
    		{
			if(foundSolution)
				return;
			if(myCoords.contains(end) || myCoords.contains(endII))
    		{
    			System.out.print("destination found");
    			foundSolution = true;
    			return;
    		}
    				if (x == 3 && oneDim[current+moves.get(0).intValue()] == '0' || x == 3 && myCoords.contains(current+moves.get(0).intValue()))
    				{
    				
    				
    				if (x == 3 && oneDim[current+moves.get(1).intValue()] == '0' || x == 3 && myCoords.contains(current+moves.get(1).intValue()))
    				{
    				

    				if (x == 3 && oneDim[current+moves.get(2).intValue()] == '0' || x == 3 && myCoords.contains(current+moves.get(2).intValue()))
    				{
    				

    				if (x == 3 && oneDim[current+moves.get(3).intValue()] == '0' || x == 3 && myCoords.contains(current+moves.get(3).intValue()))
    				{
					
    				System.out.println("crux"+current);
    				
    				if(current == end || current == endII){
    				myCoords.add(current);
    				System.out.print("destination found");
    				foundSolution = true;
    				return;
    				}
    				
    				System.out.println("the value of the crux"+oneDim[current]);
    				
    				System.out.println("considering to remove ORIGINAL GET"+myCoords.get(myCoords.size()-1));
    				
    				
    				System.out.println("performing remove"+myCoords.remove(myCoords.size()-1));
    				
    				oneDim[current] = '0';
    				
    				int manchurian = myCoords.get(myCoords.size()-1);
    				
    				makeMove(manchurian);
					}
    				
    				}
    				}
    				}
    					
    				
    		
    			
    		int next = current+moves.get(x).intValue();
			if((oneDim[next] != '0')&&(!myCoords.contains(next)))
			{
			

			if(foundSolution)
				return;
			
			
			

    		myCoords.add(current);
    		makeMove(next);
			}
    		
    		
    			
    		
    		
    		}
    		
    		}	
    		
	}
			
	
	