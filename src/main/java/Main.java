
public class Main {
    public static void main(String[] args) {
       Board board = new Board(3, 3);
        int[][] initialState = {
                {0, 0, 1},
                {0, 1, 1},
                {0, 0, 0}
        };
        board.fixedState(initialState);
        board.advance();
    }
}