package ostro.veda.core;

import ostro.veda.util.GameStatus;

public interface Board {

    boolean hasError();

    GameStatus getStatus();

    void insertNumber(final int row, final int col, final Integer value);

    void clearNumber(final int row, final int col);

    void resetBoard();
}
