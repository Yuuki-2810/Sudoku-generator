public class Main {
    public static void main(String[] args) {
        sudoku_generator generator = new sudoku_generator();
        int[][] completedBoard = generator.completedSudoku();
        generator.print();
    }
}
