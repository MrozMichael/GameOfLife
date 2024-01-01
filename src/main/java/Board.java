import java.util.*;

public class Board {
    private int[][] state;
    private int[][] nextState;
    private final int rows;
    private final int cols;

    private boolean hasState;
    public Board(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        state = new int[rows][cols];
        nextState = new int[rows][cols];
        hasState = false;
    }

    public void deadState(){
        hasState = true;
        for (int i = 0; i < state.length; i++){
            for (int k = 0; k < state[i].length; k++){
                state[i][k] = 0;
            }
        }
    }

    public void fixedState(int[][] givenState){
        if (givenState.length == rows && givenState[0].length == cols) {
            state = givenState;
            hasState = true;
        }
        else {
            System.out.println("Error, grid must be " + rows +"x"+ cols);
        }
    }

    public void randomState(){
        hasState = true;
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
        String bars = "==";
        for (int i = 0; i < cols; i++){
            bars += "=";
        }
        System.out.println(bars);
        for (int i = 0; i <state.length; i++) {
            System.out.print("|");
            for (int k = 0; k < state[i].length; k++) {
                String toPrint = state[i][k] == 1 ? "#" : " ";
                System.out.print(toPrint);
            }
            System.out.println("|");
        }
        System.out.println(bars);
    }

    public void advance(){
        int lastCol = state[0].length -1;

        if (state.length == 1) {
            handleSingleRow(lastCol);
            updateState();
            return;
        }

        if (lastCol == 0){
            handleSingleCol();
            updateState();
            return;
        }

        handleEdgeRow("top");
        for (int i = 1; i < state.length -1; i++){
            handleLeftMost(i);
            for (int k = 1; k <= lastCol - 1; k++) {

                //each cell has neighbours: above-left, above, above-right, right, below-right, below, below-left, left
                int[] neighbours = {
                        state[i-1][k-1], state[i-1][k], state[i-1][k+1], state[i][k+1],
                        state[i+1][k+1],state[i+1][k], state[i+1][k-1], state[i][k-1]
                };
                nextState[i][k] = nextStatus(state[i][k], neighbours);
            }
            handleRightMost(i);
          }
        handleEdgeRow("bottom");
        updateState();
    }


    public void handleLeftMost(int rowIndex){
        int i = rowIndex;
        //cell has neighbours: above, above-right, right, below-right, below
        int[] neighbours = {state[i -1][0], state[i-1][1], state[i][1], state[i+1][1], state[i+1][0]};
        nextState[i][0] = nextStatus(state[i][0], neighbours);
    }
    public void handleRightMost(int rowIndex){
        int i = rowIndex;
        int k = state[i].length -1;
        //cell has neighbours: above, above-left, left, below-left, below
        int[] neighbours = {state[i-1][k], state[i-1][k-1], state[i][k - 1], state[i+1][k -1], state[i+1][k]};
        nextState[i][k] = nextStatus(state[i][k], neighbours);
    }

    public void handleEdgeRow(String topOrBottom){
        int currentRow = topOrBottom.equals("top") ? 0 : state.length - 1; //index of current row
        int verticalRow = topOrBottom.equals("top") ? 1 : state.length - 2; //index of vertical neighbour
        int last = state[0].length - 1;


        //[0] has neighbours: right, below-right, below
        //last has neighbours: left, below-left, below
        //rest have neighbours: left, below-left, below, below-right, right
            int[] neighboursOfFarLeft = {state[currentRow][1], state[verticalRow][1], state[verticalRow][0]};
            nextState[currentRow][0] = nextStatus(state[currentRow][0], neighboursOfFarLeft);
            for (int i = 1; i < last; i++){
                int[] neighbours = {state[currentRow][i-1], state[verticalRow][i-1], state[verticalRow][i], state[verticalRow][i+1], state[currentRow][i+1]};
                nextState[currentRow][i] = nextStatus(state[currentRow][i], neighbours);
            }
            int[] neighboursOfFarRight = {state[currentRow][last -1],state[verticalRow][last -1], state[verticalRow][last]};
            nextState[currentRow][last] = nextStatus(state[currentRow][last], neighboursOfFarRight);
        }

        public void handleSingleRow(int lastCol){
            nextState[0][0] = 0;
            nextState[0][lastCol] = 0;
            for (int i = 1; i < lastCol; i++) {
                int[] neighbours = {state[0][i - 1], state[0][i + 1]};
                nextState[0][i] = nextStatus(state[0][i], neighbours);
            }
        }

        public void handleSingleCol(){
            nextState[0][0] = 0;
            nextState[state.length -1][0] = 0;
            for (int i = 1; i < state.length -1; i++){
                //rest of rows have neighbour above, and below
                int[] neighbours = {state[i-1][0], state[i+1][0]};
                nextState[i][0] = nextStatus(state[i][0], neighbours);
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
            default:
                break;
        }
        return nextStatus;
    }

    public int[][] getState(){
        return state;
    }

    public void updateState(){
        for (int i = 0; i < state.length; i++){
            for (int k = 0; k < state[i].length; k++){
                state[i][k] = nextState[i][k];
            }
        }
    }

    public void eternalLife(){
        if (!hasState){
            randomState();
        }
        while(true){
            render();
            advance();
        }
    }

    }
