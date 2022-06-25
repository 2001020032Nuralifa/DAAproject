package SmartCourier;

import java.awt.Point;

public class History {
    private Point original = null;
    private Point destination = null;
    private Aksi gerakan = Aksi.DIAM;
    private Petunjuk arah = Petunjuk.NULL;

    public History(Point original, Point destination, Aksi gerakan, Petunjuk arah) {
        super();
        this.original = original;
        this.destination = destination;
        this.gerakan = gerakan;
        this.arah = arah;
    }

    public Point getDestination() {
        return destination;
    }

    public void setDestination(Point destination) {
        this.destination = destination;
    }
    
    public Point getOriginal() {
        return original;
    }

    public void setOriginal(Point original) {
        this.original = original;
    }
    
    public Petunjuk getArah() {
        return arah;
    }

    public void setArah(Petunjuk arah) {
        this.arah = arah;
    }

    public Aksi getGerakan() {
        return gerakan;
    }

    public void setGerakan(Aksi gerakan) {
        this.gerakan = gerakan;
    }


}
