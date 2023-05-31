package Map;

import java.awt.Graphics2D;
import java.util.Random;  

import Entity.Ball;

public class Board {
    private Ball[][] board = new Ball[9][9];
    
    //board[i][j].color: null -> không có, 1 -> màu đỏ, 2 -> màu xanh, 3 -> màu vàng, 4 -> màu tím, 5 -> màu xám, 6 -> màu cam, 7 -> màu đen
    //board[i][j].level: 0 -> nhỏ, 1 -> lớn

    public Board(){  
        Random random = new Random();
        // for (int i=0; i<3; i++){
        //     int x= random.nextInt(9);
        //     int y= random.nextInt(9);
        //     int color = random.nextInt(7) + 1;
        //     addBigBall(x, y, color);
        // }
        addBigBall(0,0, 1);
        addBigBall(0,1, 2);
        addBigBall(0,2, 3);
    }

    public void update(){
        //update all ball
        for (int i=0; i<9; i++){
            for (int j=0; j<9; j++){
                if (board[i][j]!=null){
                    board[i][j].update();
                }
            }
        }
    }
    
    //click board
    public void click(int x, int y){
        //System.out.println(x + " " + y); //checked
        if(board[x][y]!=null){
            board[x][y].click();
        }

    }

    //contains board
    public boolean contains(int x, int y){
        if (x>=199 && x<=762 && y>=114 && y<=678){
            //System.out.println("true");
            return true;
        }
        //System.out.println("false");
        return false;
    }

    //return position
    public int mousePositionX(int x,int y){
        int square = 62;
        if (x>=200 && x<=200+square){
            return 0;
        }
        if (x>=200+square && x<=200+2*square){
            return 1;
        }
        if (x>=200+2*square && x<=200+3*square){
            return 2;
        }
        if (x>=200+3*square && x<=200+4*square){
            return 3;
        }
        if (x>=200+4*square && x<=200+5*square){
            return 4;
        }
        if (x>=200+5*square && x<=200+6*square){
            return 5;
        }
        if (x>=200+6*square && x<=200+7*square){
            return 6;
        }
        if (x>=200+7*square && x<=200+8*square){
            return 7;
        }
        if (x>=200+8*square && x<=200+9*square){
            return 8;
        }        
        return 0;
    }
    public int mousePositionY(int x, int y){
        int square = 62;
        if (y>=115 && y<=115+square){
            return 0;
        }
        if (y>=115+square && y<=115+2*square){
            return 1;
        }
        if (y>=115+2*square && y<=115+3*square){
            return 2;
        }
        if (y>=115+3*square && y<=115+4*square){
            return 3;
        }
        if (y>=115+4*square && y<=115+5*square){
            return 4;
        }
        if (y>=115+5*square && y<=115+6*square){
            return 5;
        }
        if (y>=115+6*square && y<=115+7*square){
            return 6;
        }
        if (y>=115+7*square && y<=115+8*square){
            return 7;
        }
        if (y>=115+8*square && y<=115+9*square){
            return 8;
        }        
        return 0;
    }
    
    //draw board
    public void draw(Graphics2D g){
        for (int i=0; i<9; i++){
            for (int j=0; j<9; j++){
                if (board[i][j] != null){
                    board[i][j].draw(g);
                }
            }
        }
    }

    //xóa ball
    public void deleteBall(int x, int y){
        board[x][y] = null;
    }

    //thêm ball nhỏ
    public void addSmallBall(int x, int y, int color){
        board[x][y] = new Ball(x, y, color, 0);  
    }

    //thêm ball lớn
    public void addBigBall(int x, int y, int color){
        board[x][y] = new Ball(x, y, color, 1);
    }

    //kiểm tra xóa ball, khi nào: ball từ nhỏ -> lớn, ball mới di chuyển đến vị trí mới, hưng sẽ gọi
    public void checkDeleteBall(int x, int y){
        // count1 sẽ đếm số bóng hàng ngang
        int count1 = 1;

        // kiểm tra hàng ngang phải
        int i = x;
        int j = y;
        while (j>=0 && j<8){
            if (board[i][j].getColor() == board[i][j+1].getColor()){
                count1++;
                j++;
            }
            else break;
        }

        // kiểm tra hàng ngang trái
        j = y;
        while (j>0 && j<=8){
            if (board[i][j].getColor() == board[i][j-1].getColor()){
                count1++;
                j--;
            }
            else break;
        }

        //xóa bóng
        if (count1 >= 5){
            // xóa bóng hàng ngang
            j = y;
            while (j>=0 && j<8){
                if (board[i][j].getColor() == board[i][j+1].getColor()){
                    deleteBall(i, j);
                    j++;
                }
                else break;
            }

            // xóa bóng hàng ngang
            j = y;
            while (j>0 && j<=8){
                if (board[i][j].getColor() == board[i][j-1].getColor()){
                    deleteBall(i, j);
                    j--;
                }
                else break;
            }
        }

        // count2 sẽ đếm số bóng hàng dọc
        int count2 = 1;

        // kiểm tra hàng dọc trên
        i = x;
        j = y;
        while (i>=0 && i<8){
            if (board[i][j].getColor() == board[i+1][j].getColor()){
                count2++;
                i++;
            }
            else break;
        }

        // kiểm tra hàng dọc dưới
        i = x;
        while (i>0 && i<=8){
            if (board[i][j].getColor() == board[i-1][j].getColor()){
                count2++;
                i--;
            }
            else break;
        }

        //xóa bóng
        if (count2 >= 5){
            // xóa bóng hàng dọc trên
            i = x;
            while (i>=0 && i<8){
                if (board[i][j].getColor() == board[i+1][j].getColor()){
                    deleteBall(i, j);
                    i++;
                }
                else break;
            }

            // xóa bóng hàng dọc dưới
            i = x;
            while (i>0 && i<=8){
                if (board[i][j].getColor() == board[i-1][j].getColor()){
                    deleteBall(i, j);
                    i--;
                }
                else break;
            }
        }

        // count3 sẽ đếm số bóng hàng chéo xuôi
        int count3 = 1;

        // kiểm tra hàng chéo xuôi trên
        i = x;
        j = y;
        while (i>=0 && i<8 && j>=0 && j<8){
            if (board[i][j].getColor() == board[i+1][j+1].getColor()){
                count3++;
                i++;
                j++;
            }
            else break;
        }

        // kiểm tra hàng chéo xuôi dưới
        i = x;
        j = y;
        while (i>0 && i<=8 && j>0 && j<=8){
            if (board[i][j].getColor() == board[i-1][j-1].getColor()){
                count3++;
                i--;
                j--;
            }
            else break;
        }

        //xóa bóng
        if (count3 >= 5){
            // xóa bóng hàng chéo xuôi trên
            i = x;
            j = y;
            while (i>=0 && i<8 && j>=0 && j<8){
                if (board[i][j].getColor() == board[i+1][j+1].getColor()){
                    deleteBall(i, j);
                    i++;
                    j++;
                }
                else break;
            }

            // xóa bóng hàng chéo xuôi dưới
            i = x;
            j = y;
            while (i>0 && i<=8 && j>0 && j<=8){
                if (board[i][j].getColor() == board[i-1][j-1].getColor()){
                    deleteBall(i, j);
                    i--;
                    j--;
                }
                else break;
            }
        }

        // count4 sẽ đếm số bóng hàng chéo ngược
        int count4 = 1;

        // kiểm tra hàng chéo ngược trên
        i = x;
        j = y;
        while (i>=0 && i<8 && j>0 && j<=8){
            if (board[i][j].getColor() == board[i+1][j-1].getColor()){
                count4++;
                i++;
                j--;
            }
            else break;
        }

        // kiểm tra hàng chéo ngược dưới
        i = x;
        j = y;
        while (i>0 && i<=8 && j>=0 && j<8){
            if (board[i][j].getColor() == board[i-1][j+1].getColor()){
                count4++;
                i--;
                j++;
            }
            else break;
        }

        //xóa bóng
        if (count4 >= 5){
            // xóa bóng hàng chéo ngược trên
            i = x;
            j = y;
            while (i>=0 && i<8 && j>0 && j<=8){
                if (board[i][j].getColor() == board[i+1][j-1].getColor()){
                    deleteBall(i, j);
                    i++;
                    j--;
                }
                else break;
            }

            // xóa bóng hàng chéo ngược dưới
            i = x;
            j = y;
            while (i>0 && i<=8 && j>=0 && j<8){
                if (board[i][j].getColor() == board[i-1][j+1].getColor()){
                    deleteBall(i, j);
                    i--;
                    j++;
                }
                else break;
            }
        }

    }

}
