/**
 * Created by Dibel on 15/4/11.
 */

import javax.swing.*;

public class MineButton extends JButton {
    private int col;
    private int row;

    public MineButton(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}
