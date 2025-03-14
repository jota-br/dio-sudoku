package ostro.veda.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GameStatus {

    NON_STARTED("NOT STARTED"),
    STARTED("STARTED"),
    HAS_ERROR("HAS ERROR"),
    WON("WON");

    private final String status;
}
