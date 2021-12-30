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

    boolean print = true;

    public void run()
    {
        setValue(15);
        for(int j = 0; j < 10; j++)
        {

            print = false;
            for(int i = 0; i < size; i++)
            {
                checkX(i);
            }
            for(int i = 0; i < size; i++)
            {
                checkY(i);
            }
            print = true;
            showState();
        }
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
            ArrayList<ArrayList<Integer>> result = new ArrayList<>();
            Stack<ArrayList<Integer>> stack = new Stack<>(); // stack index 0 : 총 사용한 수 / index 1 : 추가한 블럭 갯수
            {
                ArrayList<Integer> revList = new ArrayList<>();
                revList.add(0);revList.add(0);
                stack.push(revList);
            } // 기본 값
            int cou = 0;
            while(!stack.isEmpty())
            {
                println("c " + cou++);
                ArrayList<Integer> l = stack.pop(); if(l.get(1) == x_guide_value[index].length) {result.add(l); println("R" + l.toString()); continue;}
                int i = 1; if(l.get(1) == 0) {i = 0;}
                println("        " + (n - l.get(0) - (x_guide_value[index].length - l.get(1) - 1)) + " " + n + " " + x_guide_value[index].length);
                for(; i < n - l.get(0) - (x_guide_value[index].length - l.get(1) - 2); i++)
                {
                    println("        " + i);
                    ArrayList<Integer> l_c = new ArrayList<>(l);
                    l_c.set(0, l.get(0) + i);
                    l_c.set(1, l.get(1) + 1);
                    l_c.add(i);
                    stack.push(l_c);
                    println("        " + l_c.toString());
                }
            }
            println("값");

            int[] li = new int[size];
            for(int j = 0; j < size; j++)
            {
                li[j] = 0;
            }

            int rejectCount = 0;
            outerLoop : for(int i = 0; i < result.size(); i++)
            {
                println(result.get(i).toString());
                int ind = 0;
                Boolean[] li_ = new Boolean[size];
                for(int j = 2; j < result.get(i).size(); j++)
                {
                    ind += result.get(i).get(j);
                    for(int r = 0; r < x_guide_value[index][j-2]; r++)
                    {
                        li_[ind++] = true;
                    }
                }
                for(int j = 0; j < size; j++)
                {
                    if(list[j] != null && list[j] == true && li_[j] == null)
                    {
                        rejectCount++;
                        continue outerLoop;
                    }
                }
                for(int j = 0; j < size; j++)
                {
                    if(list[j] != null && list[j] == false && li_[j] != null)
                    {
                        rejectCount++;
                        continue outerLoop;
                    }
                }
                for(int j = 0; j < size; j++)
                {
                    if(li_[j] != null && li_[j] == true)
                    {
                        li[j] += 1;
                    }
                }
            }
            println("결과 : ");
            for(int j = 0; j < li.length; j++)
            {
                print(li[j] + " ");
            }
            println("\nresult size " + result.size() + "  reject " + rejectCount);

            for(int j = 0; j < li.length; j++)
            {
                if(li[j] == result.size()-rejectCount) list[j] = true;
            }

        }
        //------------------------------------------------------------------

        for(int i = 0; i < size; i++)
        {
            board[index][i] = list[i];
        }
        return false;
    }
    public Boolean checkY(int index)
    {
        Boolean[] list = new Boolean[size];
        for(int i = 0; i < size; i++)
        {
            list[i] = board[i][index];
        }

        //------------------------------------------------------------------
        {
            int n = size;
            for(int i = 0; i < y_guide_value[index].length; i++)
            {
                n -= y_guide_value[index][i];
            }
            // ■■■■□■■□■■■□□□□ 4 2 3
            // ■■■■□□□□□■■□■■■ 4 2 3
            ArrayList<ArrayList<Integer>> result = new ArrayList<>();
            Stack<ArrayList<Integer>> stack = new Stack<>(); // stack index 0 : 총 사용한 수 / index 1 : 추가한 블럭 갯수
            {
                ArrayList<Integer> revList = new ArrayList<>();
                revList.add(0);revList.add(0);
                stack.push(revList);
            } // 기본 값
            int cou = 0;
            while(!stack.isEmpty())
            {
                println("c " + cou++);
                ArrayList<Integer> l = stack.pop(); if(l.get(1) == y_guide_value[index].length) {result.add(l); println("R" + l.toString()); continue;}
                int i = 1; if(l.get(1) == 0) {i = 0;}
                println("        " + (n - l.get(0) - (y_guide_value[index].length - l.get(1) - 1)) + " " + n + " " + y_guide_value[index].length);
                for(; i < n - l.get(0) - (y_guide_value[index].length - l.get(1) - 2); i++)
                {
                    println("        " + i);
                    ArrayList<Integer> l_c = new ArrayList<>(l);
                    l_c.set(0, l.get(0) + i);
                    l_c.set(1, l.get(1) + 1);
                    l_c.add(i);
                    stack.push(l_c);
                    println("        " + l_c.toString());
                }
            }
            println("값");

            int[] li = new int[size];
            for(int j = 0; j < size; j++)
            {
                li[j] = 0;
            }

            int rejectCount = 0;
            outerLoop : for(int i = 0; i < result.size(); i++)
            {
                println(result.get(i).toString());
                int ind = 0;
                Boolean[] li_ = new Boolean[size];
                for(int j = 2; j < result.get(i).size(); j++)
                {
                    ind += result.get(i).get(j);
                    for(int r = 0; r < y_guide_value[index][j-2]; r++)
                    {
                        li_[ind++] = true;
                    }
                }
                for(int j = 0; j < size; j++)
                {
                    if(list[j] != null && list[j] == true && li_[j] == null)
                    {
                        rejectCount++;
                        continue outerLoop;
                    }
                }
                for(int j = 0; j < size; j++)
                {
                    if(list[j] != null && list[j] == false && li_[j] != null)
                    {
                        rejectCount++;
                        continue outerLoop;
                    }
                }
                for(int j = 0; j < size; j++)
                {
                    if(li_[j] != null && li_[j] == true)
                    {
                        li[j] += 1;
                    }
                }
            }
            println("결과 : ");
            for(int j = 0; j < li.length; j++)
            {
                print(li[j] + " ");
            }
            println("\nresult size " + result.size() + "  reject " + rejectCount);

            for(int j = 0; j < li.length; j++)
            {
                if(li[j] == result.size()-rejectCount) list[j] = true;
            }

        }
        //------------------------------------------------------------------

        for(int i = 0; i < size; i++)
        {
            board[i][index] = list[i];
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
        if(print)
        System.out.print(message);
    }
    public void print(int message)
    {
        if(print)
        System.out.print(message);
    }
    public void println(String message)
    {
        if(print)
        System.out.println(message);
    }
    public void println(int message)
    {
        if(print)
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
