package chapter8;

/**
 * Created by cli on 6/26/16.
 */
public class EightTenPaintFill {

    public enum Color {
        Black, White, Red, Yellow, Green
    }

    public static int randomInt(int n) {
        return (int) (Math.random() * n);
    }

    public static void PrintScreen(Color[][] screen) {
        for (int r = 0; r < screen.length; r++) {
            for (int c = 0; c < screen[0].length; c++) {
                System.out.print(PrintColor(screen[r][c]));
            }
            System.out.println();
        }
    }

    public static String PrintColor(Color c) {
        switch(c) {
            case Black:
                return "B";
            case White:
                return "W";
            case Red:
                return "R";
            case Yellow:
                return "Y";
            case Green:
                return "G";
        }
        return "X";
    }

    public static void fillSpace(Color[][] screen, int x, int y, Color color) {
        int max = screen.length; // screen must be a box
        if (x >= max || y >= max || x < 0 || y < 0 || screen[x][y] == color) {
            return;
        }
        colourSpace(screen, x, y, screen[x][y], color);
    }

    public static void colourSpace(Color[][] screen, int x, int y, Color boundaryColor, Color colorToThis) {
        int max = screen.length;
        Color currColor = screen[x][y];
        if (x >= max || y >= max || x < 0 || y < 0 || currColor != boundaryColor || currColor == colorToThis) {
            return;
        }
        screen[x][y] = colorToThis;
        colourSpace(screen, x + 1, y, boundaryColor, colorToThis);
        colourSpace(screen, x - 1, y, boundaryColor, colorToThis);
        colourSpace(screen, x, y + 1, boundaryColor, colorToThis);
        colourSpace(screen, x, y - 1, boundaryColor, colorToThis);
    }

    public static void main(String[] args) {
        int N = 10;
        Color[][] screen = new Color[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                screen[i][j] = Color.Black;
            }
        }
        for (int i = 0; i < 100; i++) {
            screen[randomInt(N)][randomInt(N)] = Color.Green;
        }
        PrintScreen(screen);
        fillSpace(screen, 2, 2, Color.White);
        System.out.println();
        PrintScreen(screen);
    }
}
