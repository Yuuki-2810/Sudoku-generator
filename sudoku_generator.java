import java.util.*;
public class sudoku_generator{
    private final int SIZE = 9;
    private final int BLANK = 0;
    private int[][] panel;
    public int[][] player_panel;
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

    private int[][] completedSudoku() {
        fillGrid();
        return panel;
    }

    public int[][] genPlayerGrid(){
        player_panel = completedSudoku();
        for (int sub_grid_col = 0; sub_grid_col < Math.sqrt(SIZE); sub_grid_col++){
            for (int sub_grid_row = 0; sub_grid_row < Math.sqrt(SIZE); sub_grid_row++){
                int rand = 3 + (int)(Math.random()*(8-3)+1);
                Random tmp = new Random();
                for (int i = sub_grid_col*3; i < sub_grid_col*3+3; i++){
                    for (int j = sub_grid_row*3; j < sub_grid_row*3+3; j++){
                        if ((int)(tmp.nextInt(2)) == 0 && rand >0){
                            rand--;
                            player_panel[i][j] = BLANK;
                        };
                    }
                }
            }
        }
        return player_panel;
    }

    private void printCompletedGrid(){
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                System.out.print(panel[i][j] + " ");;
            }
            System.out.println();
        }
    }
    public void printPlayerGrid(){
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                System.out.print(player_panel[i][j] + " ");;
            }
            System.out.println();
        }
    }
}
