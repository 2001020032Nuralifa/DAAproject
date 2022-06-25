package SmartCourier;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Stack;

public class DFS {
    private Labirin labirin = null;
    private Point[] lintasan = null;
    private ArrayList<History> history = null;

    
    public DFS(Labirin labirin) {
        super();
        this.labirin = labirin;
        this.run();
    }
    
    public Point[] getLintasan() {
        return lintasan;
    }

    public ArrayList<History> getHistory() {
        return history;
    }
    
    public void run() {
        if (this.labirin != null) {
            this.history = new ArrayList<History>();

            int[][] grid = Salinan.clone(this.labirin.getGrid());
            Point start = this.labirin.getStart();
            Point end = this.labirin.getEnd();
            Stack<Point> path = new Stack<Point>();
            path.push(start);

            grid[start.x][start.y] = 1;

            Petunjuk arah = Petunjuk.UTARA;
            Boolean next = true;
            while (next) {
                Point center = path.peek();
                int[] status = this.getStatusSelTetangga(center, grid);
                int nextSel = -1;
                
                if (status[arah.ordinal()] == 1) {
                    nextSel = arah.ordinal();
                } else {
                    int kanan = (arah.ordinal() + 1) % 4;
                    while (kanan != arah.ordinal()) {
                        if (status[kanan] == 1) {
                            nextSel = kanan;
                            arah = hadapKanan(arah);
                        } else {
                            kanan = (kanan + 1) % 4;
                        }
                    }
                }
                if (nextSel != -1) {
                    Point selBaru = getTetangga(center, nextSel);
                    grid[selBaru.x][selBaru.y] = grid[center.x][center.y] + 1;;
                    path.push(selBaru);
                    this.history.add(new History(center, selBaru, Aksi.MAJU, Petunjuk.getArah(center, selBaru)));
                    if (selBaru.x == end.x && selBaru.y == end.y) {
                     
                        next = false;
                    }
                } else {
                    path.pop();
                    Point prevSel = path.peek();
                    this.history.add(new History(center, prevSel, Aksi.MUNDUR, Petunjuk.getArah(center, prevSel)));
                    if (path.isEmpty()) {
                        next = false;
                    }
                }

            }
            this.lintasan = new Point[path.size()];
            for (int i = 0; i < lintasan.length; i++) {
                lintasan[i] = path.get(i);
            }
            cetakArray(lintasan);
            cetakArray(grid);
        }
    }

    private int[] getStatusSelTetangga(Point selPusat, int[][] grid) {
        int x = selPusat.x;
        int y = selPusat.y;
        int[] status = new int[4];
        
        //TIMUR
        int xe = x;
        int ye = y + 1;
        if (xe >= 0 && xe < grid.length && ye >= 0 && ye < grid[0].length && grid[xe][ye] == 0) {
            status[1] = 1;
        }

        //UTARA
        int xn = x - 1;
        int yn = y;
        if (xn >= 0 && xn < grid.length && yn >= 0 && yn < grid[0].length && grid[xn][yn] == 0) {
            status[0] = 1;
        }
       
        //SELATAN
        int xs = x + 1;
        int ys = y;
        if (xs >= 0 && xs < grid.length && ys >= 0 && ys < grid[0].length && grid[xs][ys] == 0) {
            status[2] = 1;
        }

        //BARAT
        int xw = x;
        int yw = y - 1;
        if (xw >= 0 && xw < grid.length && yw >= 0 && yw < grid[0].length && grid[xw][yw] == 0) {
            status[3] = 1;
        }
        return status;
    }
    
    private Petunjuk hadapKanan(Petunjuk oldArah) {
        Petunjuk newArah = Petunjuk.NULL;
        if (oldArah == Petunjuk.UTARA) {
            newArah = Petunjuk.TIMUR;
        } else if (oldArah == Petunjuk.TIMUR) {
            newArah = Petunjuk.SELATAN;
        } else if (oldArah == Petunjuk.SELATAN) {
            newArah = Petunjuk.BARAT;
        } else if (oldArah == Petunjuk.BARAT) {
            newArah = Petunjuk.UTARA;
        }
        return newArah;
    }
    
    private Point getTetangga(Point center, int nextNode) {
        if (nextNode == 0) {
            return new Point(center.x - 1, center.y);
        } else if (nextNode == 1) {
            return new Point(center.x, center.y + 1);
        } else if (nextNode == 2) {
            return new Point(center.x + 1, center.y);
        } else if (nextNode == 3) {
            return new Point(center.x, center.y - 1);
        } else {
            return null;
        }
    }
    
    public void cetakArray(Point[] arrPoint) {
        for (int i = 0; i < arrPoint.length; i++) {
            System.out.print("[" + arrPoint[i].x + ", " + arrPoint[i].y + "] ");
        }
        System.out.println();
    }

    public void cetakArray(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
