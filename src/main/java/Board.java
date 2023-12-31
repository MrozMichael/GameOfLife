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

        /*
        handleEdgeRow("top")
        //for int i = 1; i < rows.length -2; i++
            handleLeftmost(i)
            for int k = 1; k < cols.length - 2; k++
               check all 8 neighbours
            handleRightMost(i)
        * */
        //handleEdgeRow("bottom")
        //state = nextState;
    }


    public void handleLeftMost(int rowNum){
        //cell has neighbours: above, above-right, right, below-right, below
    }
    public void handleRightMost(int rowNum){
        //cell has neighbours: above, above-left, left, below-left, below
    }

    public void handleEdgeRow(String topOrBottom){
        int currentRow = topOrBottom.equals("top") ? 0 : state.length - 1; //index of current row
        int verticalRow = topOrBottom.equals("top") ? 1 : state.length - 2; //index of vertical neighbour
        int last = state[0].length - 1;
        // if last == 0 {
        //  handleOneColumn();
        //  return;
        //if 1 column, top/bot row die, rest stay as they are, then all die (i think?) handle that edge case
        // }


        //[0] has neighbours: right, below-right, below
        //last has neighbours: left, below-left, below
        //rest have neighbours: left, below-left, below, below-right, right
        if (state.length == 1) {
            //if only 1 row, [0] dies always. [1] -> [last-1] have 2 neighbours, last dies
            nextState[0][0] = 0;
            nextState[0][last] = 0;
            for (int i = 1; i < last; i++){
                int[] neighbours = {state[0][i-1], state[0][i+1]};
                nextState[0][i] = nextStatus( state[0][i], neighbours);
            }

        } else { //more than 1 row
            int[] leftMostsNeighbours = {state[currentRow][1], state[verticalRow][1], state[verticalRow][0]};
            nextState[currentRow][0] = nextStatus(state[currentRow][0], leftMostsNeighbours);
            for (int i = 1; i < last; i++){
                //left, below-left, below, below-right, right
                int[] neighbours = {state[currentRow][i-1], state[verticalRow][i-1], state[verticalRow][i], state[verticalRow][i+1], state[currentRow][i+1]};
                nextState[currentRow][i] = nextStatus(state[currentRow][i], neighbours);
            }
            int[] rightMostNeighbours = {state[currentRow][last -1],state[verticalRow][last -1], state[verticalRow][last]};
            nextState[currentRow][last] = nextStatus(state[currentRow][last], rightMostNeighbours);
        }

    }
    
    public int nextStatus(int currentStatus, int[] neighbours){
        int living = 0;
        int nextStatus = 0;
        for(int neighbour: neighbours){
            living += neighbour;
        }

        /*rules:
         * if cell alive and has 0 or 1 living neighbours: dies
         *cell alive has 2-3 living neighbours: lives
         * cell alive with >3 living neighbours: dies
         * dead cell with 3 living neighbours: lives
         * */
        switch (living){
            case 2:
                nextStatus = currentStatus;
                break;
            case 3:
                nextStatus = 1;
                break;
        }
        return nextStatus;
    }


    }
