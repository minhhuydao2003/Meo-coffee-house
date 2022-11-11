package Map;

import GameState.IngameState;
import Entity.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*; // for reader

import javax.imageio.*;

public class Map {
    private int x;  
    private int y;
    //map instance
    private int[][] map;
    private int furnitureSize = 40;
    private int catSize = 32;
    private int drinkSize = 16;
    //map size
    private int numRows=6;
    private int numCols=8;
    //furiture
    private Furniture[][] furniture;
    public static final int Chair = 1;
    public static final int table =2;
    public static final int sink = 3;
    public static final int stove = 4;
    public static final int fridge = 5;
    //user instance
    private int food;
    private int money;
    private int toy;
    private int catNum;



    //constructor
    public Map(){
    }

    //getters
    public int getCatSize(){
        return catSize;
    }
    public int getCatNum(){
        return catNum;
    }
    public int getX(){
        return (int)x;
    }
    public int getY(){
        return (int)y;
    }
    //load map files into memory
    public void loadUserSavedGame(String address){
        try{
            InputStream in = getClass().getResourceAsStream(address);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            food = Integer.parseInt(br.readLine());
            money = Integer.parseInt(br.readLine());
            toy = Integer.parseInt(br.readLine());
            catNum = Integer.parseInt(br.readLine());
            //System.out.println("user info" + food + " " + money + " " + toy + " " + catNum);

            String delims = "\\s++";
            for(int i=0;i< catNum;i++){
                String curr = br.readLine();
                //System.out.println("address " + curr);

                Cat tmp = new Cat(IngameState.map, curr);
                String line = br.readLine();
                String[] tokens = line.split(delims);
                //System.out.println("Position " + Integer.parseInt(tokens[0]) +" " + Integer.parseInt(tokens[1]));
                tmp.setPosition(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
                
                IngameState.catList.add(tmp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //draw function
    public void draw(Graphics2D g){
        
    }
    
}
