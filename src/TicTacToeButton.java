
import javax.swing.JButton;
import java.awt.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wulft
 */
public class TicTacToeButton extends JButton
{
    private int row;
    private int col;
    private String state;

    public TicTacToeButton(int row, int col, String state) {
        super();
        this.row = row;
        this.col = col;
        this.state = state;
        setFont(new Font("Arial", Font.BOLD, 60));
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    public boolean isEmpty() {
        return state.equals("");
    }




}
