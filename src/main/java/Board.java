import java.util.*;

public class Board {
    private int[][] state;
    private int[][] nextState;

    public Board(int rows, int cols){

        state = new int[rows][cols];
        nextState = new int[rows][cols];
    }

    public void deadState(){
        for (int i = 0; i < state.length; i++){
            for (int k = 0; k < state[i].length; k++){
                state[i][k] = 0;
            }
        }
    }

    public void randomState(){
        for( int i = 0; i < state.length; i++){
            for (int k = 0; k < state[i].length;k++){
                int zeroOne = new Random().nextInt(2);
                state[i][k] = zeroOne;
            }
        }
    }
    public void printState(){
      for(int[] row: state){
          System.out.println(Arrays.toString(row));
        }
    }

    public void render(){
        for (int i = 0; i <state.length; i++) {
            System.out.print("|");
            for (int k = 0; k < state[i].length; k++) {
                String toPrint = state[i][k] == 1 ? "#" : " ";
                System.out.print(toPrint);
            }
            System.out.println("|");
        }
    }

    public void advance(){
        /*rules:
        * if cell alive and has 0 or 1 living neighbours: dies
        *cell alive has 2-3 living neighbours: lives
        * cell alive with >3 living neighbours: dies
        * dead cell with 3 living neighbours: lives
        * */

        /*
        handleTopRow()
        //for int i = 1; i < rows.length -2; i++
            handleLeftmost(i)
            for int k = 1; k < cols.length - 2; k++
               check all 8 neighbours
            handleRightMost(i)
        * */
        //handleBotRow()
        //state = nextState;
    }


    public void handleLeftMost(int rowNum){
        //cell has neighbours: above, above-right, right, below-right, below
    }
    public void handleRightMost(int rowNum){
        //cell has neighbours: above, above-left, left, below-left, below
    }

    public void handleTopRow(int[] row){
        //[0] has neighbours: right, below-right, below
        //[n-1] has neighbours: left, below-left, below
        //rest have neighbours: left, below-left, below, below-right, right
    }

    public void handleBotRow(int[] row){
        //[0] has neighbours: above, above-right, right
        //[n-1] has neighbours: above, above-left, left
        //rest have neighbours: above, above-left, left, right, above-right
    }

    }
