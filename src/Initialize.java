import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Initialize extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextField sizeTextField;
    private JTextField mineCountTextField;

    private int mineSize;
    private int mineCount;

    public Initialize() {
        setTitle("Setting");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }

    private void onOK() {
        int size = validateSize(sizeTextField.getText());
        if (size == -1) {
            JOptionPane.showMessageDialog(this, "Size must be an integer between 3 and 100!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        mineSize = size;
        int count = validateCount(mineCountTextField.getText(), size);
        if (count == -1) {
            JOptionPane.showMessageDialog(this, "Count must be an integer between 1 and the square of size!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        mineCount = count;
        setVisible(false);
        dispose();
    }

    private int validateSize(String str) {
        int tempNum = Integer.valueOf(str);
        if (tempNum > 3 && tempNum <= 100) {
            return tempNum;
        } else {
            return -1;
        }
    }

    private int validateCount(String str, int size) {
        int tempCount = Integer.valueOf(str);
        if(tempCount > 1 && tempCount < size * size) {
            return tempCount;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        Initialize dialog = new Initialize();
        dialog.pack();
        //dialog.setVisible(true);
        //System.exit(0);
    }

    public int[] showDialog() {
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        return new int[]{mineSize, mineCount};
    }

    public int getMineCount() {
        return mineCount;
    }

    public int getMineSize() {
        return mineSize;
    }
}
