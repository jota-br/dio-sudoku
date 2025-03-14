package ostro.veda.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ostro.veda.util.GameStatus;

import java.util.List;

@Getter
@AllArgsConstructor
public class BoardImpl implements Board {

    private List<List<Space>> spaces;

    @Override
    public boolean hasError() {
        return this.spaces.stream().anyMatch(spaceList -> spaceList.stream().anyMatch(s -> !s.getExpected().equals(s.getInserted())));
    }

    @Override
    public GameStatus getStatus() {

        GameStatus gameStatus = null;

        if (spaces == null) gameStatus = GameStatus.NON_STARTED;
        else if (hasError()) gameStatus = GameStatus.HAS_ERROR;
        else gameStatus = GameStatus.WON;

        return gameStatus;
    }

    @Override
    public boolean insertNumber(int row, int col, Integer value) {
        if (spaces.get(row).get(col).isFixed()) return false;
        spaces.get(row).get(col).setInserted(value);
        return true;
    }

    @Override
    public boolean clearNumber(int row, int col) {
        if (spaces == null) return false;
        spaces.get(row).get(col).setInserted(null);
        return true;
    }

    @Override
    public void resetBoard() {
        spaces.forEach(
                spaceList -> spaceList.stream()
                        .filter(s -> !s.isFixed())
                        .forEach(space -> space.setInserted(null))
        );
    }
}
