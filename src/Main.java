import java.util.ArrayList;
import java.util.Stack;

/*
1, 3,
1, 4,
1, 5,
1, 4,
1, 5,
1, 6,
3, 1, 2, 3,
3, 5, 2, 2,
3, 6, 2, 4,
3, 3, 2, 8,
4, 1, 1, 2, 8,
4, 1, 2, 6, 2,
2, 6, 4,
2, 4, 2,
1, 1
*/
/*
1, 6,
2, 2, 3,
1, 10,
2, 8, 2,
3, 5, 4, 2,
3, 7, 3, 2,
3, 3, 3, 3,
2, 1, 8,
1, 5,
1, 3,
1, 5,
1, 7,
2, 4, 3,
1, 5,
1, 3
*/
public class Main
{
    int[][] x_guide_value;
    int[][] y_guide_value;
    Boolean[][] board;
    int size;
    // [0][n] -> 가로  [1][n] -> 세로

    public void run()
    {
        setValue(15);
        board[1][2] = true;
        showState();
    }
    public Boolean checkX(int index)
    {
        Boolean[] list = new Boolean[size];
        for(int i = 0; i < size; i++)
        {
            list[i] = board[index][i];
        }

        //------------------------------------------------------------------
        {
            int n = size;
            for(int i = 0; i < x_guide_value[index].length; i++)
            {
                n -= x_guide_value[index][i];
            }
            // ■■■■□■■□■■■□□□□ 4 2 3
            // ■■■■□□□□□■■□■■■ 4 2 3
            Stack<ArrayList<Integer>> stack = new Stack<>();
            {
                ArrayList<Integer> revList = new ArrayList<>();
                revList.add(0);
                stack.push(revList);
            } // 기본 값
            while(!stack.isEmpty())
            {
                ArrayList<Integer> l = stack.pop();
                for(int i = 0; i < x_guide_value.length - l.get(0) - 1; i++)
                {

                }
            }
        }
        //------------------------------------------------------------------

        for(int i = 0; i < size; i++)
        {
            board[index][i] = list[i];
        }
        return false;
    }
    public void setValue(int size)
    {
        this.size = size;
        x_guide_value = new int[size][];
        y_guide_value = new int[size][];
        board = new Boolean[size][size];
        {
            int[] val = {1, 3,
                    1, 4,
                    1, 5,
                    1, 4,
                    1, 5,
                    1, 6,
                    3, 1, 2, 3,
                    3, 5, 2, 2,
                    3, 6, 2, 4,
                    3, 3, 2, 8,
                    4, 1, 1, 2, 8,
                    4, 1, 2, 6, 2,
                    2, 6, 4,
                    2, 4, 2,
                    1, 1};
            int index = 0;
            for(int i = 0; i < val.length; i++)
            {
                int s = val[i];
                x_guide_value[index] = new int[s];
                for(int j = 0; j < s; j++)
                {
                    i++;
                    x_guide_value[index][j] = val[i];
                }
                index++;
            }
        }
        {
            int[] val = {1, 6,
                    2, 2, 3,
                    1, 10,
                    2, 8, 2,
                    3, 5, 4, 2,
                    3, 7, 3, 2,
                    3, 3, 3, 3,
                    2, 1, 8,
                    1, 5,
                    1, 3,
                    1, 5,
                    1, 7,
                    2, 4, 3,
                    1, 5,
                    1, 3};
            int index = 0;
            for(int i = 0; i < val.length; i++)
            {
                int s = val[i];
                y_guide_value[index] = new int[s];
                for(int j = 0; j < s; j++)
                {
                    i++;
                    y_guide_value[index][j] = val[i];
                }
                index++;
            }
        }
    }
    public void showState()
    {
        int xMaxLength = -1;
        int yMaxStringLength = -1;
        {
            for(int i = 0; i < x_guide_value.length; i++)
            {
                if(xMaxLength < x_guide_value[i].length)
                {
                    xMaxLength = x_guide_value[i].length;
                }
            }
            for(int i = 0; i < y_guide_value.length; i++)
            {
                int length = 0;
                for(int j = 0; j < y_guide_value[i].length; j++)
                {
                    length += String.valueOf(y_guide_value[i][j]).length();
                }
                length += y_guide_value[i].length-1;
                if(yMaxStringLength < length) yMaxStringLength = length;
            }
        }
        // System.out.println(xMaxLength + " " + yMaxStringLength);
        yMaxStringLength++;
        for(int i = xMaxLength-1; i >= 0; i--)
        {
            for(int j = 0; j < yMaxStringLength; j++)
            {
                System.out.print(" ");
            }
            for(int j = 0; j < x_guide_value.length; j++)
            {
                if(x_guide_value[j].length-1 >= i)
                {
                    print(x_guide_value[j][i]);
                    if(String.valueOf(x_guide_value[j][i]).length() == 1) print(" ");
                }
                else print("  ");
                print(" ");
            }
            println("");
        }
        for(int i = y_guide_value.length-1; i > -1 ; i--)
        {
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < y_guide_value[i].length; j++)
            {
                sb.append(y_guide_value[i][j]);
                sb.append(' ');
            }
            int c = yMaxStringLength - sb.toString().length();
            for(int j = 0; j < c; j++)
            {
                sb.append(' ');
            }
            print(sb.toString());

            for(int j = 0; j < size; j++)
            {
                if(board[j][i] == null) print("□  ");
                else if(!board[j][i]) print("X  ");
                else print("■  ");
            }

            println("");
        }
    }

    public void print(String message)
    {
        System.out.print(message);
    }
    public void print(int message)
    {
        System.out.print(message);
    }
    public void println(String message)
    {
        System.out.println(message);
    }
    public void println(int message)
    {
        System.out.println(message);
    }
    public static void main(String[] args)
    {
        new Main().run();
    }
}

/*

예를들어
□□□□□□□□□□□□□
인 배열에
■■■■ ■■ ■■■ (4, 2, 3)
막대기를 순서대로 넣는다고 할때 (모든 막대기는 최소 한칸씩 떨어져 있어야함) 예(■■■■□□□■■□■■■)
모든 경우의 배열을 구하려고 하는데 어떻게 해야할까요?

스도쿠 비슷한 게임을 자동으로 정답 나오게 만들려고 하고 있는데 어떤식으로 구현해야할지 모르겠어서 질문올려봐요

*/
