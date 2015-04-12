/**
 * Created by Dibel on 15/4/11.
 */

import java.util.Random;

public class CalcMine {

    private int mine[][];
    private int surroundMine[][];
    private int mineSize;
    private int mineCount;
    public CalcMine(int size, int count) {
        mine = new int[size][size];
        surroundMine = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                mine[i][j] = 0;
            }
        }
        this.mineSize = size;
        this.mineCount = count;

        //Generate mines
        Random randomNum = new Random();
        for (int i = 0; i < count; i++) {
            int x = randomNum.nextInt(size);
            int y = randomNum.nextInt(size);
            if (mine[x][y] == 1) {
                i--;
            } else {
                mine[x][y] = 1;
            }
        }

        //Build surrounding mine count matrix
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                surroundMine[i][j] = surroundMines(j, i);
                System.out.print(surroundMine[i][j]);
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

    public int getMineCount() {
        return mineCount;
    }

    public boolean IsMine(int col, int row) {
        if(mine[row][col] == 1) {
            return true;
        } else {
            return false;
        }
    }

    protected int surroundMines(int col, int row) {
        int count = 0;

        //8 directions

        //Left-Up
        if (row > 0 && col > 0 && mine[row - 1][col - 1] == 1) count++;
        //Left
        if (row > 0 && mine[row - 1][col] == 1) count++;
        //Left-Down
        if (row > 0 && col < mineSize - 1 && mine[row - 1][col + 1] == 1) count++;
        //Up
        if (col > 0 && mine[row][col - 1] == 1) count++;
        //Down
        if (col < mineSize - 1 && mine[row][col + 1] == 1) count++;
        //Right-Up
        if (row < mineSize - 1 && col > 0 && mine[row + 1][col - 1] == 1) count++;
        //Right
        if (row < mineSize - 1 && mine[row + 1][col] == 1) count++;
        //Right-Down
        if (row < mineSize - 1 && col < mineSize - 1 && mine[row + 1][col + 1] == 1) count++;

        return count;
    }

    public int getSurroundMine(int col, int row) {
        return surroundMine[row][col];
    }

}
