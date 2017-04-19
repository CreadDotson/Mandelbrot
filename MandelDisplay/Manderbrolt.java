import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;

public class Manderbrolt{
    int[] colors = new int[0];
    int width = 0;
    int height = (int)Math.round(width / 1.75);
    int max = 0;
    public Manderbrolt(int width){
        this.width = width;
        height = (int)Math.round(width / 1.75);
        colors = getColors(1000);
    }
    private int[] getColors(int length){
        int[] colors = new int[length];
        for(int i = 0; i < colors.length; i++){
            colors[i] = i*0xF1B30;
        }
        return colors;
    }
    public BufferedImage fillImage(int frame){
        int maxIteration = frame;
        BufferedImage img = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        for(int row = 0; row < height;row++){
            for(int column = 0; column < width; column++){
                double y0 = (row*3.5/width)-1;
                double x0 = (column*3.5/width)-2.5;
                double x = 0.0;
                double y = 0.0;
                int iteration = 0;
                while(x*x + y*y < 4 && iteration < maxIteration){
                    double xtemp = x*x - y*y + x0;
                    y = 2*x*y + y0;
                    x = xtemp;
                    iteration = iteration + 1;
                }
                
                int color = colors[iteration-1];//+0xff0000;
                if(max < iteration){
                    max = iteration;
                }
                img.setRGB(column,row,color);
            }
        }
        
        return img;
    }
}
