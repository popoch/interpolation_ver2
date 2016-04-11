import javax.swing.JPanel;
import javax.swing.JFrame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Drawing3 extends JFrame {

	private DrawingPanel drawingpanel;
	private int current_point;
	
	public Drawing3() {
		setResizable(false);
		setBounds(0,0,1920,1080);
		drawingpanel = new DrawingPanel();
		setContentPane(drawingpanel);
		drawingpanel.repaint();
	}
	
	public void Input_data(int input_start_point) {
		current_point = input_start_point;
	}
	
	public class DrawingPanel extends JPanel {
		int flag = 0;
		int mdraw = 0;
		int sdraw = 0;
		int pdraw = 0;
		protected void paintComponent(Graphics g) {

			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			
			g.setColor(Color.BLACK);
			g.drawString("Source File 1 : " + Data.location[5] + "_" + Data.file_name, 50, this.getHeight()-30);
			g.drawString("Source File 2 : " + Data.location[5] + "_" + "systemlog.csv", 50, this.getHeight()-15);
			g.drawString("Arbitrary Unit", this.getWidth()-200, this.getHeight()/4 - 240);
			g.drawString("Name : " + Data.location[5], this.getWidth()-200, this.getHeight()/4 - 228);
			g.drawString("Gender : " + Data.location[4], this.getWidth()-200, this.getHeight()/4 - 216);
			g.drawString("Pre X", 20, this.getHeight()/8 + 5);
			g.drawString("Post X", 20, this.getHeight()/8*3 + 5);
			g.drawString("Pre Y", 20, this.getHeight()/8*5 + 5);
			g.drawString("Post Y", 20, this.getHeight()/8*7 + 5);
			g.drawLine(0, this.getHeight()/4*2, this.getWidth(), this.getHeight()/4*2);
			
			//mid lane
			g.setColor(Color.ORANGE);
			g.drawLine(0, this.getHeight()/4, this.getWidth(), this.getHeight()/4);
			g.drawLine(0, this.getHeight()/4*3, this.getWidth(), this.getHeight()/4*3);
			
			g.setColor(Color.RED);
			g.drawLine(60, 0, 60, this.getHeight());
			g.drawLine(80, 0, 80, this.getHeight());
			g.drawLine(80, this.getHeight()/8, this.getWidth(), this.getHeight()/8);
			g.drawLine(80, this.getHeight()/8*3, this.getWidth(), this.getHeight()/8*3);
			g.drawLine(80, this.getHeight()/8*5, this.getWidth(), this.getHeight()/8*5);
			g.drawLine(80, this.getHeight()/8*7, this.getWidth(), this.getHeight()/8*7);
			
			g.setColor(Color.LIGHT_GRAY);
			for(int i = -10; i <= 10; i++) {
				if(i == 0) {
					g.drawString(String.valueOf(i), 60, this.getHeight()/8 + 5);
					g.drawString(String.valueOf(i), 60, this.getHeight()/8*3 + 5);
					g.drawString(String.valueOf(i), 60, this.getHeight()/8*5 + 5);
					g.drawString(String.valueOf(i), 60, this.getHeight()/8*7 + 5);
				} else {
					g.drawString(String.valueOf(i), 60, this.getHeight()/8 - i*12 + 5);
					g.drawString(String.valueOf(i), 60, this.getHeight()/8*3 - i*12 + 5);
					g.drawString(String.valueOf(i), 60, this.getHeight()/8*5 - i*12 + 5);
					g.drawString(String.valueOf(i), 60, this.getHeight()/8*7 - i*12 + 5);
					g.drawLine(80, this.getHeight()/8 - i*12, this.getWidth(), this.getHeight()/8 - i*12);
					g.drawLine(80, this.getHeight()/8*3 - i*12, this.getWidth(), this.getHeight()/8*3 - i*12);
					g.drawLine(80, this.getHeight()/8*5 - i*12, this.getWidth(), this.getHeight()/8*5 - i*12);
					g.drawLine(80, this.getHeight()/8*7 - i*12, this.getWidth(), this.getHeight()/8*7 - i*12);
				}
			}
		}
	}
}
