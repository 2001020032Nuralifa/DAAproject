package SmartCourier;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ProsesGrid {
    double size, size1, t, T, gxo, gyo, I, J;
    Color color;
    Aksi gerakan = Aksi.DIAM;
    Petunjuk arah = Petunjuk.NULL;

    public ProsesGrid(int I, int J, double size, double gxo, double gyo, double T, Color color) {
        super();
        this.size = size;
        this.T = T;
        this.color = color;
        this.I = I;
        this.J = J;
        this.gxo = gxo;
        this.gyo = gyo;
    }

    public ProsesGrid(int I, int J, double T, Color color) {
        super();
        this.T = T;
        this.color = color;
        this.I = I;
        this.J = J;
    }

    public void aktifkan(Aksi gerakan, Petunjuk arah) {
        this.gerakan = gerakan;
        this.arah = arah;
        t = 0;
        size1 = 0;
    }

    private void update() {
        if (gerakan != Aksi.DIAM && t < T) {
            t++;
            size1 = size * (t / T);
            if (size1 > size) {
                size1 = size;
            }
        }
    }
        public void setSpeed(double speed) {
        T = speed;
    }

    public void setSize(double size, double gxo, double gyo) {
        this.size = size;
        this.gxo = gxo;
        this.gyo = gyo;
    }

    public void draw(GraphicsContext gc) {
        if (gerakan != Aksi.DIAM) {
            gc.setFill(color);
            double xo = gxo + J * size, yo = gyo + I * size, sizeX = 0, sizeY = 0;
            if (gerakan == Aksi.MAJU) {
                if (arah == Petunjuk.UTARA) {
                    yo += (size - size1);
                    sizeX = size;
                    sizeY = size1;
                } else if (arah == Petunjuk.TIMUR) {
                    sizeX = size1;
                    sizeY = size;
                } else if (arah == Petunjuk.SELATAN) {
                    sizeX = size;
                    sizeY = size1;
                } else if (arah == Petunjuk.BARAT) {
                    xo += (size - size1);
                    sizeX = size1;
                    sizeY = size;
                }
            } else if (gerakan == Aksi.MUNDUR) {
                if (arah == Petunjuk.UTARA) {
                    sizeX = size;
                    sizeY = size - size1;
                } else if (arah == Petunjuk.TIMUR) {
                    xo += (size1);
                    sizeX = size - size1;
                    sizeY = size;
                } else if (arah == Petunjuk.SELATAN) {
                    yo += (size1);
                    sizeX = size;
                    sizeY = size - size1;
                } else if (arah == Petunjuk.BARAT) {
                    sizeX = size - size1;
                    sizeY = size;
                }
            }
            gc.fillRect(xo, yo, sizeX, sizeY);
            update();
        }
    }
}
