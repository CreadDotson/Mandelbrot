import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
public class Manderbrolt extends JPanel{
    int[] colors = new int[10000];
    public Manderbrolt(){
        JFrame frame = new JFrame("Manderbrolt Set");
        setPreferredSize(new Dimension(1750,1000));
        colors = getColors();
        BufferedImage img = fillImage();
        
        frame.getContentPane().add(new JLabel(new ImageIcon(img)));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    private int[] getColors(){
        int[] colors = new int[10000];
        for(int i = 0; i < 10000; i++){
            colors[i] = i*0xF1B30;
        }
        return colors;
    }
    private BufferedImage fillImage(){
        BufferedImage img = new BufferedImage(1750,1000,BufferedImage.TYPE_INT_RGB);
        for(int row = 0; row < 1750;row++){
            for(int column = 0; column < 1000; column++){
                double x0 = (row*0.00002)-.1;
                double y0 = (column*0.00002)-.86;
                double x = 0.0;
                double y = 0.0;
                int iteration = 0;
                int maxIteration = 10000;
                while(x*x + y*y < 2*2 && iteration < maxIteration){
                    double xtemp = x*x - y*y + x0;
                    y = 2*x*y + y0;
                    x = xtemp;
                    iteration = iteration + 1;
                }
                int color = colors[iteration-1];
                double nsmooth = iteration + 1 - Math.log(Math.log(Math.abs(x)))/Math.log(2);
                System.out.println(color + " : " + nsmooth);
                img.setRGB(row,column,color);
            }
        }
        return img;
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
    public static void main(String[] args){
        new Manderbrolt();
    }
}