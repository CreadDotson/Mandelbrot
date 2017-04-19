import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
public class MandelDisplay extends JPanel implements ActionListener{
    BufferedImage img;//image to store the mandel set
    Manderbrolt mand;
    int frame = 1;
    long time;//for pausing the time
    public MandelDisplay(int width){
        time = System.currentTimeMillis();//set the time
        mand = new Manderbrolt(width);//manderbrolt calculator
        img = mand.fillImage(frame);//fill for the first frame
        
        JFrame frame = new JFrame("MANDELBROT");
        setPreferredSize(new Dimension(width,(int)Math.round(width * .571)));
        
        frame.getContentPane().add(this, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        
        Timer t = new Timer(5,this);
        t.start();
    }
    
    public void draw(Graphics g){
        if(img != null)
        g.drawImage(img,0,0,null);
    }
    
    public void actionPerformed(ActionEvent e){
        long currentTime = System.currentTimeMillis();
        if(currentTime - time > 10 && frame < 1000){//it's been long enough!
            frame++;
            time = currentTime;
        }
        img = mand.fillImage(frame);//draw a new mandel set
        repaint();
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    
    public static void main(String[] args){
        new MandelDisplay(1500);//change this number to change the size
        //screen will change size as well so don't make it too big
    }
}