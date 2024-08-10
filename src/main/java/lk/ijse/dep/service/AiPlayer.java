package lk.ijse.dep.service;

public class AiPlayer extends Player{
    public AiPlayer(Board board) {super(board);}

    public void movePiece(int col) {
            int row = 0;
            int max=(int) Double.NEGATIVE_INFINITY;
            for (int i = 0; i <Board.NUM_OF_COLS; i++) {
                if (this.board.isLegalMove(i)) {
                    int tRow = this.board.findNextAvailableSpot(i);
                    this.board.updateMove(i, tRow, Piece.GREEN);
                    int evaluvate = minimax(0, false);
                    this.board.updateMove(i, tRow, Piece.EMPTY);
                    if (max < evaluvate) {
                        max = evaluvate;
                        col = i;
                        row = tRow;
                    }
                }
            }
                board.updateMove(col,row,Piece.GREEN);
                board.getBoardUI().update(col,false);
                if(!board.findWinner().getWinningPiece().equals(Piece.EMPTY)){
                    board.getBoardUI().notifyWinner(board.findWinner());
                }else if(!board.existLegalMoves()){
                    board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
                }


    }


    private int minimax(int depth,boolean maximizingPlayer) {
        Winner winner= board.findWinner();
        if ((!winner.getWinningPiece().equals(Piece.EMPTY)) || depth==4) {
            if ( winner.getWinningPiece().equals(Piece.GREEN)) {
                return 1;
            } else if (winner.getWinningPiece().equals(Piece.BLUE)) {
                return -1;
            }
            return 0;
        }
        if (maximizingPlayer) {
            for (int i = 0; i <Board.NUM_OF_COLS; i++){
                if (this.board.isLegalMove(i)) {
                    int row = this.board.findNextAvailableSpot(i);
                    this.board.updateMove(i, Piece.GREEN);
                    int eval = minimax(depth + 1, false);
                    this.board.updateMove(i, row, Piece.EMPTY);
                    if (eval == 1) {
                        return eval;
                }
            }
        }
        }else {
            for (int i = 0; i <Board.NUM_OF_COLS; i++) {
                    if (this.board.isLegalMove(i)) {
                        int row = this.board.findNextAvailableSpot(i);
                        this.board.updateMove(i, Piece.BLUE);
                        int eval = minimax(depth + 1, true);
                        this.board.updateMove(i, row, Piece.EMPTY);
                        if (eval == -1) {
                            return eval;
                    }
                }
            }
        }
        return 0;
    }
}