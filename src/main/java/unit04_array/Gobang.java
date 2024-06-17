package unit04_array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Gobang {
    // 定义棋盘大小
    private static int BOARD_SIZE = 15;
    // 定义一个二维数组充当棋盘
    private String[][] board;
    public void initBoard(){
        // 初始化棋盘数组
        board = new String[BOARD_SIZE][BOARD_SIZE];
        // 把每个元素赋值为"+"，用于在控制台画出棋盘
        for (int i=0; i<BOARD_SIZE; i++){
            for(int j=0; j<BOARD_SIZE; j++){
                board[i][j] = "➕";
            }
        }
    }
    // 在控制台输出棋盘
    public void printBoard(){
        // 打印每个数组元素
        for(int i=0; i<BOARD_SIZE; i++){
            for(int j=0; j<BOARD_SIZE; j++){
                System.out.print(board[i][j]);
            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args) throws IOException {
        Gobang gb = new Gobang();
        gb.initBoard();
        gb.printBoard();
        // 获取键盘输入
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = null;
        while ((inputStr = br.readLine()) != null){
            String[] posStrArr = inputStr.split(",");
            int xPos = Integer.parseInt(posStrArr[0]);
            int yPos = Integer.parseInt(posStrArr[1]);
            // 把对应的数组元素赋值为"●"
            gb.board[yPos -1][xPos -1] = "●";
            /*
            电脑随机生成 2 个整数，作为电脑下棋的坐标
            1. 坐标有效性，只能是数字，不能超出棋盘范围
            2. 已经有棋子的点，不能重复下棋
            3. 每次下棋后，需要扫描谁赢了
             */
            gb.printBoard();
            System.out.println("请输入您下棋的坐标，应以x,y的格式：");
        }
    }
}
