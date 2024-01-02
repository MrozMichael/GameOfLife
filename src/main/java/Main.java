import java.io.*;
import java.nio.file.Paths;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        /*
        int[][] initialState = stateFromFile("toad.txt");
        try {
            Board board = new Board(initialState.length, initialState[0].length);
            board.fixedState(initialState);
            board.eternalLife();
        } catch (Exception e){
            System.out.println(e.getMessage());
            return;
        }
        */
         Board board = new Board(20, 100);
         board.randomState();
         board.eternalLife(1000);
    }

    public static int[][] stateFromFile(String filePath){
        int[][] boardState;
        ArrayList<String> fileContent = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while((line = reader.readLine()) != null){
                fileContent.add(line);
            }
        boardState = new int[fileContent.size()][fileContent.get(0).length()];
            for (int i = 0; i < fileContent.size(); i++){
                String[] rowArr = fileContent.get(i).split("");
                for (int j = 0; j < rowArr.length; j++){
                    boardState[i][j] = Integer.parseInt(rowArr[j]);
                }
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            boardState = new int[0][0];
        }
        return boardState;
    }
}