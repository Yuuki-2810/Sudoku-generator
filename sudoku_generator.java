import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
public class sudoku_generator{
    private final int SIZE = 9;
    private final int BLANK = 0;
    private int[][] panel;
    public sudoku_generator(){
        panel = new int[9][9];
    }
    public int[][] completed_sudoku(){
        ArrayList<Integer> num = new ArrayList<>();
        for (int i = 1 ; i <= 9 ;i++){
            num.add(i);
        }
        for (int i = 0; i < SIZE; i++){
            for (int key = 1 ; key < 9 ;key++){
                num.add(key);
            }
            for (int j = 0; j < SIZE; j++){
                while (true){
                    Collections.shuffle(num);
                    int rand = num.get(0);
                    if (isValid(i, j, rand)){
                        panel[i][j] = rand;
                        num.remove(rand);
                        break;
                    }
                }
            }
        }
        return panel;
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

    public void print(){
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                System.out.print(panel[i][j] + " ");;
            }
            System.out.println();
        }
    }
}
