import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeFrame extends JFrame {
    JPanel board;
    JPanel buttonPanel;
    JPanel bottomPanel;

    TicTacToeButton[][] tiles = new TicTacToeButton[3][3];

    String currentPlayer = "X";
    int moveCount = 0;

    JButton quit = new  JButton("Quit");

    public TicTacToeFrame() {
        super("Tic-Tac-Toe");
        board = new JPanel();
        board.setLayout(new BorderLayout());
        createButtonPanel();
        createQuitButtonPanel();
        add(board);
    }

    public void createButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3,3));
        buttonPanel.setPreferredSize(new Dimension(300,300));
        tileListener listener = new tileListener();
        for( int row = 0; row < 3; row++)
            for(int col= 0; col < 3; col++)
            {
                tiles[row][col] = new TicTacToeButton(row, col, "");
                tiles[row][col].addActionListener(listener);
                buttonPanel.add(tiles[row][col]);

            }
        board.add(buttonPanel);

    }

    public void createQuitButtonPanel() {
        bottomPanel = new JPanel();
        quit.addActionListener((ActionEvent ae) -> System.exit(0));

        bottomPanel.add(quit);
        board.add(bottomPanel, BorderLayout.SOUTH);
    }

    private boolean isWin(String player)
    {
        if(isColWin(player) || isRowWin(player) || isDiagnalWin(player))
        {
            return true;
        }

        return false;
    }
    private boolean isColWin(String player)
    {
        // checks for a col win for specified player
        for(int col=0; col < 3; col++)
        {
            if(tiles[0][col].getState().equals(player) &&
                    tiles[1][col].getState().equals(player) &&
                    tiles[2][col].getState().equals(player))
            {
                return true;
            }
        }
        return false; // no col win
    }
    private boolean isRowWin(String player)
    {
        // checks for a row win for the specified player
        for(int row=0; row < 3; row++)
        {
            if(tiles[row][0].getState().equals(player) &&
                    tiles[row][1].getState().equals(player) &&
                    tiles[row][2].getState().equals(player))
            {
                return true;
            }
        }
        return false; // no row win
    }
    private boolean isDiagnalWin(String player)
    {
        // checks for a diagonal win for the specified player

        if(tiles[0][0].getState().equals(player) &&
                tiles[1][1].getState().equals(player) &&
                tiles[2][2].getState().equals(player) )
        {
            return true;
        }
        if(tiles[0][2].getState().equals(player) &&
                tiles[1][1].getState().equals(player) &&
                tiles[2][0].getState().equals(player) )
        {
            return true;
        }
        return false;
    }

    private boolean isTie()
    {
        boolean xFlag = false;
        boolean oFlag = false;
        // Check all 8 win vectors for an X and O so
        // no win is possible
        // Check for row ties
        for(int row=0; row < 3; row++)
        {
            if(tiles[row][0].getState().equals("X") ||
                    tiles[row][1].getState().equals("X") ||
                    tiles[row][2].getState().equals("X"))
            {
                xFlag = true; // there is an X in this row
            }
            if(tiles[row][0].getState().equals("O") ||
                    tiles[row][1].getState().equals("O") ||
                    tiles[row][2].getState().equals("O"))
            {
                oFlag = true; // there is an O in this row
            }

            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a row win
            }

            xFlag = oFlag = false;

        }
        // Now scan the columns
        for(int col=0; col < 3; col++)
        {
            if(tiles[0][col].getState().equals("X") ||
                    tiles[1][col].getState().equals("X") ||
                    tiles[2][col].getState().equals("X"))
            {
                xFlag = true; // there is an X in this col
            }
            if(tiles[0][col].getState().equals("O") ||
                    tiles[1][col].getState().equals("O") ||
                    tiles[2][col].getState().equals("O"))
            {
                oFlag = true; // there is an O in this col
            }

            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a col win
            }
        }
        // Now check for the diagonals
        xFlag = oFlag = false;

        if(tiles[0][0].getState().equals("X") ||
                tiles[1][1].getState().equals("X") ||
                tiles[2][2].getState().equals("X") )
        {
            xFlag = true;
        }
        if(tiles[0][0].getState().equals("O") ||
                tiles[1][1].getState().equals("O") ||
                tiles[2][2].getState().equals("O") )
        {
            oFlag = true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diag win
        }
        xFlag = oFlag = false;

        if(tiles[0][2].getState().equals("X") ||
                tiles[1][1].getState().equals("X") ||
                tiles[2][0].getState().equals("X") )
        {
            xFlag =  true;
        }
        if(tiles[0][2].getState().equals("O") ||
                tiles[1][1].getState().equals("O") ||
                tiles[2][0].getState().equals("O") )
        {
            oFlag =  true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diag win
        }

        // Checked every vector so I know I have a tie
        return true;
    }




    class tileListener implements ActionListener {
        public void  actionPerformed(ActionEvent e)
        {
            TicTacToeButton tile = (TicTacToeButton) e.getSource();

            if (!tile.isEmpty()) {
                JOptionPane.showMessageDialog(board, "Illegal Move! That spot is taken");
            }
            tile.setState(currentPlayer);
            tile.setText(currentPlayer);
            moveCount++;
            if (moveCount >= 5) {
                if (isWin(currentPlayer)) {
                    JOptionPane.showMessageDialog(board, "Player: " + currentPlayer + " Wins!");
                    int response = JOptionPane.showConfirmDialog(board, "Would you like to play again?");
                    if (response == JOptionPane.YES_OPTION) {
                        clearBoard();
                        moveCount = 0;
                        currentPlayer = "X";
                    } else {
                        System.exit(0);
                    }
                }
            }
            if (moveCount >= 7) {
                if (isTie()) {
                    JOptionPane.showMessageDialog(board, "Tie!");
                    int response = JOptionPane.showConfirmDialog(board, "Would you like to play again?");
                    if (response == JOptionPane.YES_OPTION) {
                        clearBoard();
                        moveCount = 0;
                        currentPlayer = "X";
                    } else {
                        System.exit(0);
                    }
                }
            }

            if (currentPlayer.equals("X")) {
                currentPlayer = "O";
            } else {
                currentPlayer = "X";
            }


        }

        private void clearBoard()
        {
            // sets all the board elements to a space
            for(int row=0; row < 3; row++)
            {
                for(int col=0; col < 3; col++)
                {
                    tiles[row][col].setText("");
                    tiles[row][col].setState("");
                }
            }
        }


    }


}
