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
}
