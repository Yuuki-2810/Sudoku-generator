import java.util.ArrayList;
import java.util.Collections;
public class sudoku_generator{
    private final int SIZE = 9;
    private final int BLANK = 0;
    private int[][] panel;
    public sudoku_generator(){
        panel = new int[9][9];
    }

    private boolean isValid(int x, int y, int k){
        for (int i = 0; i < SIZE; i++){
            if (panel[x][i] == k) return false;
        }
        for (int i = 0; i < SIZE; i++){
            if (panel[i][y] == k) return false;
        }
        int a = x/3, b = y/3;
        for (int i = a*3; i < a*3+3; i++){
            for (int j = b*3; j < b*3+3; j++){
                if (panel[i][j] == k) return false;
            }
        }
        return true;
    }
    
    private boolean fillGrid() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (panel[i][j] == BLANK) {
                    ArrayList<Integer> numbers = new ArrayList<>();
                    for (int num = 1; num <= SIZE; num++) {
                        numbers.add(num);
                    }
                    Collections.shuffle(numbers);
                    
                    for (int num : numbers) {
                        if (isValid(i, j, num)) {
                            panel[i][j] = num;
                            if (fillGrid()) {
                                return true;
                            }
                            panel[i][j] = BLANK;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public int[][] completedSudoku() {
        fillGrid();
        return panel;
    }

    public void print(){
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                System.out.print(panel[i][j] + " ");;
            }
            System.out.println();
        }
    }
}
