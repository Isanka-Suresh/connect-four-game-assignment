package lk.ijse.dep.service;

public class BoardImpl implements Board {
    private final Piece[][] pieces;

    private final BoardUI boardUI;

    public BoardImpl(BoardUI boardUI) {
        this.boardUI=boardUI;
        pieces = new Piece[NUM_OF_COLS][NUM_OF_ROWS];
        for (int i = 0; i < NUM_OF_COLS; i++) {
            for (int j = 0; j < NUM_OF_ROWS; j++) {
                pieces[i][j] = Piece.EMPTY;
            }
        }
    }

    @Override
    public BoardUI getBoardUI() {
        return boardUI;
    }

    @Override
    public int findNextAvailableSpot(int col) {
        for (int i = 0; i < NUM_OF_ROWS; i++) {
            if (pieces[col][i].equals(Piece.EMPTY)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isLegalMove(int col) {
        return findNextAvailableSpot(col) != -1;
    }

    @Override
    public boolean existLegalMoves() {
        for (int i = 0; i < NUM_OF_COLS; i++) {
                if (isLegalMove(i)) {
                    return true;
                }
            }
        return false;
    }

    @Override
    public void updateMove(int col, Piece move) {
        int row = findNextAvailableSpot(col);
        pieces[col][row] = move;
    }

    @Override
    public void updateMove(int col, int row, Piece move) {
        pieces[col][row] = move;
    }

    @Override
    public Winner findWinner() {
        for (int i = 0; i <NUM_OF_ROWS; i++){
            for (int j = 0; j<3; j++){
                if(pieces[j][i].equals(Piece.BLUE) && pieces[j+1][i].equals(Piece.BLUE) && pieces[j+2][i].equals(Piece.BLUE) && pieces[j+3][i].equals(Piece.BLUE)){
                    return new Winner(Piece.BLUE,j,i,j+3,i);
                }else if(pieces[j][i].equals(Piece.GREEN) && pieces[j+1][i].equals(Piece.GREEN) && pieces[j+2][i].equals(Piece.GREEN) && pieces[j+3][i].equals(Piece.GREEN)){
                    return new Winner(Piece.GREEN,j,i,j+3,i);
                }
            }
        }

        for (int i = 0; i <NUM_OF_COLS; i++){
            for (int j = 0; j<2; j++){
                if(pieces[i][j].equals(Piece.BLUE) && pieces[i][j+1].equals(Piece.BLUE) && pieces[i][j+2].equals(Piece.BLUE) && pieces[i][j+3].equals(Piece.BLUE)){
                    return new Winner(Piece.BLUE,i,j,i,j+3);
                }else if(pieces[i][j].equals(Piece.GREEN) && pieces[i][j+1].equals(Piece.GREEN) && pieces[i][j+2].equals(Piece.GREEN) && pieces[i][j+3].equals(Piece.GREEN)){
                    return new Winner(Piece.GREEN,i,j,i,j+3);
                }
            }
        }
        return new Winner(Piece.EMPTY);
    }
}