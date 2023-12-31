import java.util.*;

public class Board {
    private int[][] state;

    public Board(int rows, int cols){

        state = new int[rows][cols];
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

        /*edge cases: a) first cell in a row. ie state[i][0]. has no leftmost neighbour or top/bot left diagonals
                      b) last cell in a row, ie state[i][n-1]. has no rightmost neighbour or top/bot right diagonals
                      c) all cells in top row, ie state[0][k], no upper neighbours or top diagonals
                      d) all cells in bottom row, ie state[n-1][k], no lower neighbours or bot diagonals
                      so need special constraints for first row, first col of each row, last col of each row, last row

                      1) iterate [0][0] with respect to a) and c).
                      2) iterate [0][k] until k = n-1 with respect to c)
                      3) iterate [0][n-1] with respect to b) and c)
                      4) iterate [1][0] with respect to a)
                      5) iterate [1][k] until k = n-1 normally
                      6) iterate [1][n-1] with respect to b)
                      7) repeat 4-6 until reach last row
                      8) iterate last row like first, but replace c) with d)

                      iterate [0][0]
                      then loop through [0][1] -> [0][n-2]
                      iterate [0][n-1]
                      loop row [1] through row [n-2] // ex: 5x5 board, n = 5, row[3] would be 2nd to last row
                      loop should start by doing special iteration for [i][0], then inner loop for i[1] -> i[n-2] then i[n-1]
                      then iterate [n-1][0]
                      then loop through [n-1][1] -> [n-1][n-2]
                      then iterate [0][n-1]
        * */
    }


    public void handleLeftMost(int cell){
        //cell has neighbours: above, above-right, right, below-right, below
    }
    public void handleRightMost(int cell){
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
