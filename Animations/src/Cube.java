import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Cube extends JPanel {
	
	Image buffer;
	Graphics2D gbuffer;
	double p[][]=new double[8][3];
	int x=0,y=1,z=2,w=200;
	double angle_x=0.01, angle_y=0.01, angle_z=0.01;
	
	public Cube(String name) 
	{
		super();
	}

	public void init() 
	{
		p[0][x]=-100;
		p[1][x]=100;
		p[2][x]=100;
		p[3][x]=-100;
		p[4][x]=-100;
		p[5][x]=100;
		p[6][x]=100;
		p[7][x]=-100;
		
		p[0][y]=100;
		p[1][y]=100;
		p[2][y]=100;
		p[3][y]=100;
		p[4][y]=-100;
		p[5][y]=-100;
		p[6][y]=-100;
		p[7][y]=-100;
		
		p[0][z]=-100;
		p[1][z]=-100;
		p[2][z]=100;
		p[3][z]=100;
		p[4][z]=-100;
		p[5][z]=-100;
		p[6][z]=100;
		p[7][z]=100;
	}
	
	public void paint(Graphics g)
	{
		if(buffer==null)
		{
			buffer=createImage(this.getSize().width,this.getSize().height);
			gbuffer=(Graphics2D) buffer.getGraphics();
		}
		Color violet=new Color(199,21,133);
		gbuffer.setColor(violet);
		gbuffer.clearRect(0, 0, this.getSize().width, this.getSize().height);
		// pentru ca linia sa nu vibreze (sa nu fie blurata)
		gbuffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		gbuffer.drawLine((int)(p[0][x])+w, (int)(p[0][y])+w, (int)p[1][x]+w, (int)p[1][y]+w);
		gbuffer.drawLine((int)(p[1][x])+w, (int)(p[1][y])+w, (int)p[2][x]+w, (int)p[2][y]+w);
		gbuffer.drawLine((int)(p[2][x])+w, (int)(p[2][y])+w, (int)p[3][x]+w, (int)p[3][y]+w);
		gbuffer.drawLine((int)(p[3][x])+w, (int)(p[3][y])+w, (int)p[0][x]+w, (int)p[0][y]+w);
		gbuffer.drawLine((int)(p[4][x])+w, (int)(p[4][y])+w, (int)p[5][x]+w, (int)p[5][y]+w);
		gbuffer.drawLine((int)(p[5][x])+w, (int)(p[5][y])+w, (int)p[6][x]+w, (int)p[6][y]+w);
		gbuffer.drawLine((int)(p[6][x])+w, (int)(p[6][y])+w, (int)p[7][x]+w, (int)p[7][y]+w);
		gbuffer.drawLine((int)(p[7][x])+w, (int)(p[7][y])+w, (int)p[4][x]+w, (int)p[4][y]+w);
		gbuffer.drawLine((int)(p[0][x])+w, (int)(p[0][y])+w, (int)p[4][x]+w, (int)p[4][y]+w);
		gbuffer.drawLine((int)(p[1][x])+w, (int)(p[1][y])+w, (int)p[5][x]+w, (int)p[5][y]+w);
		gbuffer.drawLine((int)(p[2][x])+w, (int)(p[2][y])+w, (int)p[6][x]+w, (int)p[6][y]+w);
		gbuffer.drawLine((int)(p[3][x])+w, (int)(p[3][y])+w, (int)p[7][x]+w, (int)p[7][y]+w);
		
		g.drawImage(buffer, 0, 0, this);
		try {
			Thread.sleep(30);
		}
		catch(InterruptedException e)
		{
		}
		
		double px, py, pz;
		for(int i=0;i<8;i++)
		{
			//miscarea unui punct in spatiu este determinata de 3 rotatii
			px=p[i][x];
			py=p[i][y];
			pz=p[i][z];
			
			//rotatie in jurul axei Ox
			p[i][y]=py*Math.cos(angle_x)+pz*Math.sin(angle_x);
			p[i][z]=-py*Math.sin(angle_x)+pz*Math.cos(angle_x);
			py=p[i][y];
			pz=p[i][z];
			//rotatie in jurul axei Oy
			p[i][x]=px*Math.cos(angle_y)+pz*Math.sin(angle_y);
			p[i][z]=-px*Math.sin(angle_y)+pz*Math.cos(angle_y);
			px=p[i][x];
			//rotatie in jurul axei Oz
			p[i][x]=px*Math.cos(angle_z)+py*Math.sin(angle_z);
			p[i][y]=-px*Math.sin(angle_z)+py*Math.cos(angle_z);
		}
		//functie de refresh; apeleaza update(), care la randul ei, apeleaza paint()
		repaint();
	}
	
	public void update(Graphics g)
	{
		paint(g);
	}

	public static void main(String[] args) {
		
		JFrame c=new JFrame("CubeAnimation");
		c.setSize(400, 450);
		Cube cube=new Cube("Cube");
		c.setResizable(false);
				
		Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
		//unealta grafica pentru determoinarea dimensiunii ecranului in pixeli,
		//care e stocata intr-un obiect de tip Dimension
		int w=c.getSize().width;
		int h=c.getSize().width;
		int x=(d.width-w)/2;
		int y=(d.height-h)/2;
		
		c.setBackground(Color.white);
		c.setLocation(x, y);
		c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.add(cube);
		cube.init();
		c.setVisible(true);

	}
	

}
