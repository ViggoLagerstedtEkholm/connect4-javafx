package com.example.connect4;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class GameValidator {
    public static boolean hasWinner(Color[][] grid, Color color){
        //check for 4 across
        for(int row = 0; row<grid.length; row++){
            for (int col = 0;col < grid[0].length - 3;col++){
                if (grid[row][col] == color   &&
                        grid[row][col+1] == color &&
                        grid[row][col+2] == color &&
                        grid[row][col+3] == color){
                    return true;
                }
            }
        }
        //check for 4 up and down
        for(int row = 0; row < grid.length - 3; row++){
            for(int col = 0; col < grid[0].length; col++){
                if (grid[row][col] == color   &&
                        grid[row+1][col] == color &&
                        grid[row+2][col] == color &&
                        grid[row+3][col] == color){
                    return true;
                }
            }
        }
        //check upward diagonal
        for(int row = 3; row < grid.length; row++){
            for(int col = 0; col < grid[0].length - 3; col++){
                if (grid[row][col] == color   &&
                        grid[row-1][col+1] == color &&
                        grid[row-2][col+2] == color &&
                        grid[row-3][col+3] == color){
                    return true;
                }
            }
        }
        //check downward diagonal
        for(int row = 0; row < grid.length - 3; row++){
            for(int col = 0; col < grid[0].length - 3; col++){
                if (grid[row][col] == color   &&
                        grid[row+1][col+1] == color &&
                        grid[row+2][col+2] == color &&
                        grid[row+3][col+3] == color){
                    return true;
                }
            }
        }

        return false;
    }
}
