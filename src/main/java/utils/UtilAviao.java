package utils;

import java.util.List;
import modelos.Aviao;

public final class UtilAviao {

    public static Integer getNextId(List<Aviao> avioes) {
        return avioes.stream().mapToInt(av -> av.getId()).max().orElse(0) + 1;
    }

}
