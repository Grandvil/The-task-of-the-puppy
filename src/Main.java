public class Main {

    public static void main(String[] args) {
        String[][] field = {
                {"Щ", "-", "-", "*", "*", "-", "-", "-", "-", "-"},
                {"-", "-", "-", "-", "*", "-", "*", "*", "-", "-"},
                {"-", "-", "-", "*", "-", "*", "-", "-", "-", "*"},
                {"-", "*", "-", "-", "-", "-", "-", "-", "-", "-"},
                {"-", "-", "-", "-", "-", "-", "*", "-", "-", "-"},
                {"-", "-", "*", "-", "-", "*", "-", "-", "-", "-"},
                {"-", "-", "-", "*", "-", "-", "*", "*", "*", "-"},
                {"-", "-", "-", "-", "-", "-", "-", "*", "-", "-"},
                {"-", "-", "-", "-", "-", "-", "-", "*", "-", "-"},
                {"-", "-", "-", "-", "-", "*", "*", "-", "-", "-"},
        };

        findPath(field, 8, 3);
    }

    public static void findPath(String[][] field, int x0, int y0) {
        int n = 10;
        boolean[][] path = new boolean[n][n];
        String[][] memory = new String[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                memory[i][j] = "O";
            }
        }

        int x = x0;
        int y = y0;

        while (x != 0 || y != 0) {
            String direction = whereFrom(field, x, y, memory);

            if (direction.equals("N")) {
                System.out.println("Нет такого пути :(");
                break;
            } else if (direction.equals("U")) {
                path[x][y] = true;
                y -= 1;
            } else if (direction.equals("L")) {
                path[x][y] = true;
                x -= 1;
            }
        }
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                if (i == x0 && j == y0) {
                    System.out.print("Ч ");
                } else if (path[i][j]) {
                    System.out.print("x ");
                } else {
                    System.out.print(field[j][i] + " ");
                }
            }
            System.out.println();
        }
    }

    public static String whereFrom(String[][] field, int x, int y, String[][] memory) {
        if (!memory[x][y].equals("O")) {
            return memory[x][y];
        }

        if (x > 0) {
            int left_x = x - 1;
            int left_y = y;

            if (left_x == 0 && left_y == 0) {
                memory[x][y] = "L";
                return "L";
            }

            if (!field[left_y][left_x].equals("*")) {
                if (!whereFrom(field, left_x, left_y, memory).equals("N")) {
                    memory[x][y] = "L";
                    return "L";
                }
            }
        }

        if (y > 0) {
            int up_x = x;
            int up_y = y - 1;

            if (up_x == 0 && up_y == 0) {
                memory[x][y] = "U";
                return "U";
            }

            if (!field[up_y][up_x].equals("*")) {
                if (!whereFrom(field, up_x, up_y, memory).equals("N")) {
                    memory[x][y] = "U";
                    return "U";
                }
            }
        }
        memory[x][y] = "N";
        return "N";
    }
}
