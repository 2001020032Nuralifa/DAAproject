package SmartCourier;

import java.awt.Point;

public enum Petunjuk {
    UTARA, TIMUR, SELATAN, BARAT, NULL;

    public static Petunjuk getArah(Point original, Point destination) {
        Petunjuk arah = Petunjuk.NULL;
        if (original.x == destination.x) {
            if (original.y > destination.y) {
                arah = Petunjuk.BARAT;
            } else if (original.y < destination.y) {
                arah = Petunjuk.TIMUR;
            }
        } else if (original.y == destination.y) {
            if (original.x < destination.x) {
                arah = Petunjuk.SELATAN;
            } else if (original.x > destination.x) {
                arah = Petunjuk.UTARA;
            }
        }
        return arah;
    }
}
