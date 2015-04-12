/**
 * Created by Dibel on 15/4/11.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Main {

    CalcMine calc;
    JFrame frame;
    JPanel gamePanel;
    JPanel infoPanel;
    JLabel remainingMines;
    JButton restart;
    int size;
    int safeZone[][];

    public static void main(String[] args) {
        Main main = new Main();
        main.go();
    }

    public void go() {
        Initialize dialog = new Initialize();
        int[] tempResult = dialog.showDialog();
        size = tempResult[0];
        safeZone = new int[size][size];
        final int count = tempResult[1];
        calc = new CalcMine(size, count);

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Minesweeper");
        //frame.setSize(600, 650);
        frame.setResizable(false);

        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(size, size));
        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final MineButton button = new MineButton(i, j);
                button.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int col = button.getCol();
                        int row = button.getRow();
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            if (button.getText() != "⚑") {
                                if (calc.IsMine(col, row)) {
                                    button.setText("*");
                                    JOptionPane.showMessageDialog(frame, "Bang! You lose!");
                                    //GameOver
                                } else {
                                    int surroundMineCount = calc.getSurroundMine(col, row);
                                    button.setText(String.valueOf(surroundMineCount));
                                    if (surroundMineCount == 0) {
                                        button.setText("");
                                        FindSafeZone(col, row);
                                    }
                                    button.setEnabled(false);
                                }
                            }
                        } else if (e.getButton() == MouseEvent.BUTTON3) {
                            if (button.getText() != "⚑") {
                                button.setText("⚑");
                            } else {
                                button.setText("");
                            }
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }
                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }
                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }
                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });
//                button.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//
//                    }
//                });
                gamePanel.add(button);
                //zhuang yu long shi bug;
            }
        }


        infoPanel = new JPanel();
        remainingMines = new JLabel();
        remainingMines.setText("Remaining Mines: 25");
        restart = new JButton("Restart");
        infoPanel.add(BorderLayout.WEST, remainingMines);
        infoPanel.add(BorderLayout.EAST, restart);

        frame.getContentPane().add(BorderLayout.CENTER, gamePanel);
        frame.getContentPane().add(BorderLayout.NORTH, infoPanel);

        frame.pack();
        frame.setSize(400, 450);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public void FindSafeZone(int col, int row) {
        int surroundMineCount = calc.getSurroundMine(col ,row);
        MineButton button = (MineButton) gamePanel.getComponent(col + row * size);
        button.setText(String.valueOf(surroundMineCount));
        button.setEnabled(false);
        safeZone[col][row] = 1;
        if (surroundMineCount != 0) {
            return;
        } else {
            button.setText("");
        }
        if (col > 0 && safeZone[col - 1][row] == 0) FindSafeZone(col - 1, row);
        if (col > 0 && row > 0 && safeZone[col - 1][row - 1] == 0) FindSafeZone(col - 1, row - 1);
        if (col > 0 && row < size - 1 && safeZone[col - 1][row + 1] == 0) FindSafeZone(col - 1, row + 1);
        if (row > 0 && safeZone[col][row - 1] == 0) FindSafeZone(col, row - 1);
        if (col < size - 1 && safeZone[col + 1][row] == 0) FindSafeZone(col + 1, row);
        if (col < size - 1 && row > 0 && safeZone[col + 1][row - 1] == 0) FindSafeZone(col + 1, row - 1);
        if (col < size - 1 && row < size - 1 && safeZone[col + 1][row + 1] == 0) FindSafeZone(col + 1, row + 1);
        if (row < size - 1 && safeZone[col][row + 1] == 0) FindSafeZone(col, row + 1);

    }
}