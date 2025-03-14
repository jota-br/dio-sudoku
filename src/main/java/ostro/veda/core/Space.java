package ostro.veda.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class Space {

    @Setter
    private Integer inserted;
    private final Integer expected;
    private final boolean isFixed;

    public Space(Integer expected, boolean isFixed) {
        this.expected = expected;
        this.isFixed = isFixed;
    }
}
