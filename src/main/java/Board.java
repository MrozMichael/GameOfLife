import java.util.Arrays;

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
    public void printState(){
      for(int[] row: state){
          System.out.println(Arrays.toString(row));
        }
    }
}
