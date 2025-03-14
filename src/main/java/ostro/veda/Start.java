package ostro.veda;

import ostro.veda.core.BoardImpl;
import ostro.veda.core.Space;
import ostro.veda.model.BoardTemplate;

import java.util.*;
import java.util.stream.Collectors;

public class Start {

    private final static Scanner scanner = new Scanner(System.in);
    private static BoardImpl board;
    private final static int BOARD_SIZE = 9;

    public static void main(String[] args) {

        boolean flag = true;
        while (flag) {
            System.out.println("Sudoku CLI");
            System.out.println("Select one option:");
            if (board == null) {
                System.out.println("\t1 - Start game");
            } else {
                System.out.println("\t2 - Insert number");
                System.out.println("\t3 - Clear number");
                System.out.println("\t4 - Reset board");
                System.out.println("\t5 - Check board");
                System.out.println("\t6 - Print board");
            }
            System.out.println("\t7 - Quit game");

            String input = scanner.nextLine();
            switch (input) {
                case "1" -> start(args);
                case "2" -> insertNumber();
                case "3" -> clearNumber();
                case "4" -> resetBoard();
                case "5" -> checkBoard();
                case "6" -> printBoard();
                case "7" -> flag = false;
            }
        }
    }

    private static void start(String[] args) {

        Map<String, String> fixedSpaces = Arrays.stream(args).collect(Collectors.toMap(
                k -> k.split(";")[0],
                v -> v.split(";")[1]
        ));

        List<List<Space>> spaces = new ArrayList<>();

        for (int i = 0; i < BOARD_SIZE; i++) {
            spaces.add(new ArrayList<Space>());
            for (int j = 0; j < BOARD_SIZE; j++) {
                String[] config = fixedSpaces.get(i + "," + j).split(",");
                Integer expected = Integer.parseInt(config[0]);
                boolean fixed = Boolean.parseBoolean(config[1]);
                spaces.get(i).add(new Space(expected, fixed));
            }
        }

        board = new BoardImpl(spaces);
    }

    private static void insertNumber() {
        Integer[] input = getInput(true);
        board.insertNumber(input[0], input[1], input[2]);
    }

    private static void clearNumber() {
        Integer[] input = getInput(false);
        board.clearNumber(input[0], input[1]);
    }

    private static void resetBoard() {
        board.resetBoard();
    }

    private static void printBoard() {

        String[] array = new String[81];
        int index = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                Space space = board.getSpaces().get(i).get(j);
                array[index++] = String.valueOf(
                        space.isFixed() ?
                                space.getExpected() :
                                space.getInserted() == null ? " " : space.getInserted()
                );
            }
        }
        String boardState = BoardTemplate.TEMPLATE.formatted((Object[]) array);
        System.out.println(boardState);
    }

    private static void checkBoard() {
        System.out.println("Game: " + board.getStatus());
    }

    private static Integer[] getInput(boolean getValue) {

        System.out.println("Insert row number");
        String strRow = scanner.nextLine();
        System.out.println("Insert column number");
        String strCol = scanner.nextLine();

        try {
            int row = Integer.parseInt(strRow);
            int col = Integer.parseInt(strCol);
            int value = getValue ? getInputValue() : 0;

            return new Integer[]{row, col, value};

        } catch (NumberFormatException e) {
            System.out.println("Insert only whole numbers");
            return getInput(getValue);
        }
    }

    private static int getInputValue() {
        System.out.println("Insert value number");
        String strValue = scanner.nextLine();

        try {

            return Integer.parseInt(strValue);

        } catch (NumberFormatException e) {
            System.out.println("Insert only whole numbers");
            return getInputValue();
        }
    }
}
