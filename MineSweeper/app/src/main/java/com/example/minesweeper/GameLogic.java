package com.example.minesweeper;



import java.util.ArrayList;
import java.util.Random;

public class GameLogic {

    public int[][] gameBoard;
    public int e,g,h,f,u,v,s,t;
    public int x[] , y[] ;
    public int i;
    public int count5 =0;
    public final int min = 1 , max = 6;

    GameLogic() {

        gameBoard = new int[8][8];
        x = new int[8];
        y = new int[8];
        i = 0;
        e = (int) Math.floor(Math.random() * (max - min + 1) + min);
        g = (int) Math.floor(Math.random() * (max - min + 1) + min);
        h = (int) Math.floor(Math.random() * (max - min + 1) + min);
        f = (int) Math.floor(Math.random() * (max - min + 1) + min);

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                gameBoard[r][c] = 0;
            }
        }


        gameBoard[e][h] = 100;
        gameBoard[e][f] = 100;
        gameBoard[g][h] = 100;
        gameBoard[g][f] = 100;

        x[i] = e;
        y[i] = h;
        i++;
        func2(e, f);
        func2(g, h);
        func2(g, f);

        for (int j = 0; j < i; j++) {
            func(getGameBoard(), x[j], y[j]);
        }
    }

    public void func4(){

        x = new int[8];
        y = new int[8];
        i = 0;
        e = (int)Math.floor(Math.random()*(max - min +1) + min);
        g = (int)Math.floor(Math.random()*(max - min +1) + min);
        h = (int)Math.floor(Math.random()*(max - min +1) + min);
        f = (int)Math.floor(Math.random()*(max - min +1) + min);

        for(int r=0 ; r<8 ; r++){
            for(int c=0 ; c<8 ; c++)
            { gameBoard[r][c] = 0; }
        }


        gameBoard[e][h] = 100;
        gameBoard[e][f] = 100;
        gameBoard[g][h] = 100;
        gameBoard[g][f] = 100;


        x[i] = e;
        y[i] = h;
        i++;
        func2(e,f);
        func2(g,h);
        func2(g,f);

        for(int j =0 ; j<i ; j++){
            func(getGameBoard(),x[j],y[j]);
        }
    }

    public void func5(){


        x = new int[50];
        y = new int[50];
        i = 0;

        e = (int)Math.floor(Math.random()*(max - min +1) + min);
        g = (int)Math.floor(Math.random()*(max - min +1) + min);
        u = (int)Math.floor(Math.random()*(max - min +1) + min);
        h = (int)Math.floor(Math.random()*(max - min +1) + min);
        f = (int)Math.floor(Math.random()*(max - min +1) + min);
        v = (int)Math.floor(Math.random()*(max - min +1) + min);

        for(int r=0 ; r<8 ; r++){
            for(int c=0 ; c<8 ; c++)
            { gameBoard[r][c] = 0; }
        }


        gameBoard[e][h] = 100;
        gameBoard[e][f] = 100;
        gameBoard[e][v] = 100;
        gameBoard[g][h] = 100;
        gameBoard[g][f] = 100;
        gameBoard[g][v] = 100;
        gameBoard[u][h] = 100;
        gameBoard[u][f] = 100;
        gameBoard[u][v] = 100;



        x[i] = e;
        y[i] = h;
        i++;
        func2(e,f);
        func2(e,v);
        func2(g,h);
        func2(g,f);
        func2(g,v);
        func2(u,h);
        func2(u,f);
        func2(u,v);

        for(int j =0 ; j<i ; j++){
            func(getGameBoard(),x[j],y[j]);
        }

    }


    public void func6(){

        x = new int[50];
        y = new int[50];
        i = 0;
        e = (int)Math.floor(Math.random()*(max - min +1) + min);
        g = (int)Math.floor(Math.random()*(max - min +1) + min);
        u = (int)Math.floor(Math.random()*(max - min +1) + min);
        s = (int)Math.floor(Math.random()*(max - min +1) + min);
        h = (int)Math.floor(Math.random()*(max - min +1) + min);
        f = (int)Math.floor(Math.random()*(max - min +1) + min);
        v = (int)Math.floor(Math.random()*(max - min +1) + min);
        t = (int)Math.floor(Math.random()*(max - min +1) + min);

        for(int r=0 ; r<8 ; r++){
            for(int c=0 ; c<8 ; c++)
            { gameBoard[r][c] = 0; }
        }


        gameBoard[e][h] = 100;
        gameBoard[e][f] = 100;
        gameBoard[e][v] = 100;
        gameBoard[e][t] = 100;
        gameBoard[g][h] = 100;
        gameBoard[g][f] = 100;
        gameBoard[g][v] = 100;
        gameBoard[g][t] = 100;
        gameBoard[u][h] = 100;
        gameBoard[u][f] = 100;
        gameBoard[u][v] = 100;
        gameBoard[u][t] = 100;
        gameBoard[s][h] = 100;
        gameBoard[s][f] = 100;
        gameBoard[s][v] = 100;
        gameBoard[s][t] = 100;

        x[i] = e;
        y[i] = h;
        i++;
        func2(e,f);
        func2(e,v);
        func2(e,t);
        func2(g,h);
        func2(g,f);
        func2(g,v);
        func2(g,t);
        func2(u,h);
        func2(u,f);
        func2(u,v);
        func2(u,t);
        func2(s,h);
        func2(s,f);
        func2(s,v);
        func2(s,t);


        for(int j =0 ; j<i ; j++){
            func(getGameBoard(),x[j],y[j]);
        }
    }



    public void func( int a[][] , int d , int b){


        if (a[d - 1][b - 1] != 100) {
            a[d - 1][b - 1] = a[d - 1][b - 1] + 1;
        }
        if (a[d][b - 1] != 100) {
            a[d][b - 1] = a[d][b - 1] + 1;
        }
        if (a[d + 1][b - 1] != 100) {
            a[d + 1][b - 1] = a[d + 1][b - 1] + 1;
        }
        if (a[d - 1][b] != 100) {
            a[d - 1][b] = a[d - 1][b] + 1;
        }
        if (a[d + 1][b] != 100) {
            a[d + 1][b] = a[d + 1][b] + 1;
        }
        if (a[d - 1][b + 1] != 100) {
            a[d - 1][b + 1] = a[d - 1][b + 1] + 1;
        }
        if (a[d][b + 1] != 100) {
            a[d][b + 1] = a[d][b + 1] + 1;
        }
        if (a[d + 1][b + 1] != 100) {
            a[d + 1][b + 1] = a[d + 1][b + 1] + 1;
        }

        return ;
    }

    public void func2(int a , int b){
        for(int j=0 ; j<i ; j++){
            if((x[j]==a)&&(y[j]==b))
                count5++;
        }
        if(count5==0){
            x[i] = a;
            y[i] = b;
            i++;
        }
        else
            count5 = 0;
    }


    public int[][] getGameBoard() {
        return gameBoard;
    }
}


