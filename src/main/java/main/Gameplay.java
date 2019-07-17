package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.util.Random;

public class Gameplay extends JPanel implements KeyListener, ActionListener {



    private int [] snakexlenght = new int [750];
    private int [] snakeylenght = new int [750];

    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    private ImageIcon leftmouth;
    private ImageIcon rightmouth;
    private ImageIcon upmouth;
    private ImageIcon downmouth;

    private int lenghtofsnake = 3;    //?

    private Timer timer;
    private int delay = 100;
    private ImageIcon snakeimage;

    private  int [] enemyxpos = {25,50,75,100,125,150,175,200,225,250,275,300,325,
                                350,375,400,425,450,475,500,525,550,575,600,625,650,
                                675,700,725,750,775,800,825,850};
    private  int [] enemyypos = {75,100,125,150,175,200,225,250,275,300,325,
                                350,375,400,425,450,475,500,525,550,575,600,625};

    private ImageIcon enemyimage;

    private Random random = new Random();

    private int xpos = random.nextInt(34);
    private int ypos = random.nextInt(23);

    private int score = 0;


    private int moves = 0;

    private ImageIcon titleImage;

    public Gameplay()
    {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer (delay, this);
        timer.start();


    }
    public void paint(Graphics g)
    {
        if(moves == 0)
        {
            snakexlenght[2] = 50;
            snakexlenght[1] = 75;
            snakexlenght[0] = 100;

            snakeylenght[2] = 100;
            snakeylenght[1] = 100;
            snakeylenght[0] = 100;



        }
        //draw title image border
        g.setColor(Color.WHITE);
        g.drawRect(24,10,851,55);

        //draw title image

        titleImage = new ImageIcon("target/assets/snaketitle.jpg");
        titleImage.paintIcon(this, g,25,11);

        //draw border for c gameplay

        g.setColor(Color.WHITE);
        g.drawRect(24,74,851,577);

        //draw background for th gameplay

        g.setColor(Color.black);
        g.fillRect(25,75,850,575);

        //draw score
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Scores: "+ score, 780, 30);

        //draw lenght of snake
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Lenght: " +lenghtofsnake, 780, 50);

        rightmouth = new ImageIcon("target/assets/rightmouth.png");
        rightmouth.paintIcon(this, g, snakexlenght[0], snakeylenght[0] );

      //  leftmouth = new ImageIcon("target/assets/leftmouth.png");
       // upmouth = new ImageIcon("target/assets/upmouth.png");
      //  downmouth = new ImageIcon("target/assets/downmouth.png");

        for(int a = 0; a<lenghtofsnake; a++)
        {
             if(a==0 && right)
             {
                 rightmouth = new ImageIcon("target/assets/rightmouth.png");
                 rightmouth.paintIcon(this, g, snakexlenght[a], snakeylenght[a] );
             }
            if(a==0 && left)
            {
                leftmouth = new ImageIcon("target/assets/leftmouth.png");
                leftmouth.paintIcon(this, g, snakexlenght[a], snakeylenght[a] );
            }
            if(a==0 && up)
            {
                upmouth = new ImageIcon("target/assets/upmouth.png");
                upmouth.paintIcon(this, g, snakexlenght[a], snakeylenght[a] );
            }
            if(a==0 && down)
            {
                downmouth = new ImageIcon("target/assets/downmouth.png");
                downmouth.paintIcon(this, g, snakexlenght[a], snakeylenght[a] );
            }

             if (a!=0)
             {
                 snakeimage = new ImageIcon("target/assets/snakeimage.png");
                 snakeimage.paintIcon(this, g, snakexlenght[a], snakeylenght[a] );

             }
        }

        enemyimage = new ImageIcon("target/assets/enemy.png");

        if((enemyxpos[xpos] == snakexlenght[0] && enemyypos[ypos] == snakeylenght[0]))
        {
            score++;
            lenghtofsnake++;
            xpos = random.nextInt(34);
            ypos = random.nextInt(23);
        }

        enemyimage.paintIcon(this, g, enemyxpos[xpos], enemyypos[ypos]);


        for(int b = 1; b< lenghtofsnake; b++)
        {
            if(snakexlenght[b] == snakexlenght[0] && snakeylenght[b] ==snakeylenght[0])
            {
                right = false;
                left = false;
                up = false;
                down = false;

                g.setColor(Color.RED);
                g.setFont(new Font("new times roman", Font.BOLD,50 ));
                g.drawString("YOU DIED BOBECZKU ", 300,300);

                g.setColor(Color.WHITE);
                g.setFont(new Font("new times roman", Font.BOLD,20 ));
                g.drawString("Space to RESTART", 350,340);
            }
        }

        g.dispose();


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(right)
        {
            for (int r = lenghtofsnake-1; r>=0; r--)
            {
                snakeylenght[r+1] = snakeylenght[r];
            }
            for (int r = lenghtofsnake; r>=0;r--)
            {
                if (r==0)
                {
                    snakexlenght[r] = snakexlenght [r] + 25;
                }
                else
                {
                    snakexlenght[r]=snakexlenght[r-1];
                }
                if(snakexlenght[r] > 850)
                {
                    snakexlenght[r] = 25;
                }
            }

            repaint();
        }
        if(left)
        {
            for (int r = lenghtofsnake-1; r>=0; r--)
            {
                snakeylenght[r+1] = snakeylenght[r];
            }
            for (int r = lenghtofsnake; r>=0;   r--)
            {
                if (r==0)
                {
                    snakexlenght[r] = snakexlenght [r] - 25;
                }
                else
                {
                    snakexlenght[r]=snakexlenght[r-1];
                }
                if(snakexlenght[r] < 25)
                {
                    snakexlenght[r] = 850;
                }
            }

            repaint();
        }
        if(up)
        {
            for (int r = lenghtofsnake-1; r>=0; r--)
            {
                snakexlenght[r+1] = snakexlenght[r];
            }
            for (int r = lenghtofsnake; r>=0; r--)
            {
                if (r==0)
                {
                    snakeylenght[r] = snakeylenght [r] - 25;
                }
                else
                {
                    snakeylenght[r]=snakeylenght[r-1];
                }
                if(snakeylenght[r] < 75)
                {
                    snakeylenght[r] = 625;
                }
            }

            repaint();
        }
        if(down)
        {
            for (int r = lenghtofsnake-1; r>=0; r--)
            {
                snakexlenght[r+1] = snakexlenght[r];
            }
            for (int r = lenghtofsnake; r>=0; r--)
            {
                if (r==0)
                {
                    snakeylenght[r] = snakeylenght [r] + 25;
                }
                else
                {
                    snakeylenght[r]=snakeylenght[r-1];
                }
                if(snakeylenght[r] > 625)
                {
                    snakeylenght[r] = 75 ;
                }
            }

            repaint();
        }

    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            moves = 0;
            score = 0;
            lenghtofsnake = 3;
            repaint();
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            moves++;
            right = true;
            if(!left)
            {
                right = true;
            }
            else
            {
                right = false;
                left = true;
            }
            up = false;
            down = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            moves++;
            left = true;
            if(!right)
            {
                left = true;
            }
            else
            {
                right = true;
                left = false;
            }
            up = false;
            down = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP)
        {
            moves++;
            up = true;
            if(!down)
            {
                up = true;
            }
            else
            {
                up = false;
                right  = true;
            }
            left = false;
            right = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            moves++;
            down = true;
            if(!up)
            {
                down = true;
            }
            else
            {
                down = false;
                up = true;
            }
            left = false;
            right = false;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
