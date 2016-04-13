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
			g.drawString("Pre", 20, this.getHeight()/8 + 5);
			g.drawString("Left", 20, this.getHeight()/8 + 15);
			g.drawString("Post", 20, this.getHeight()/8*3 + 5);
			g.drawString("Left", 20, this.getHeight()/8*3 + 15);
			g.drawString("Pre", 20, this.getHeight()/8*5 + 5);
			g.drawString("Right", 20, this.getHeight()/8*5 + 15);
			g.drawString("Post", 20, this.getHeight()/8*7 + 5);
			g.drawString("Right", 20, this.getHeight()/8*7 + 15);
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
			for(int i = -5; i <= 5; i++) {
				if(i == 0) {
					g.drawString(String.valueOf(i), 65, this.getHeight()/8 + 5);
					g.drawString(String.valueOf(i), 65, this.getHeight()/8*3 + 5);
					g.drawString(String.valueOf(i), 65, this.getHeight()/8*5 + 5);
					g.drawString(String.valueOf(i), 65, this.getHeight()/8*7 + 5);
				} else {
					g.drawString(String.valueOf(i), 65, this.getHeight()/8 - i*20 + 5);
					g.drawString(String.valueOf(i), 65, this.getHeight()/8*3 - i*20 + 5);
					g.drawString(String.valueOf(i), 65, this.getHeight()/8*5 - i*20 + 5);
					g.drawString(String.valueOf(i), 65, this.getHeight()/8*7 - i*20 + 5);
					g.drawLine(80, this.getHeight()/8 - i*20, this.getWidth(), this.getHeight()/8 - i*20);
					g.drawLine(80, this.getHeight()/8*3 - i*20, this.getWidth(), this.getHeight()/8*3 - i*20);
					g.drawLine(80, this.getHeight()/8*5 - i*20, this.getWidth(), this.getHeight()/8*5 - i*20);
					g.drawLine(80, this.getHeight()/8*7 - i*20, this.getWidth(), this.getHeight()/8*7 - i*20);
				}
			}
			
			for(int count = 1; count < Data.img_1_pupildata.size(); count++) {
				
					int x1 = Math.round((float)(count - 1) * 1810 /((float) Data.img_1_pupildata.size()) + 80);
					float y1 = this.getHeight()/8 - (Float.valueOf(Data.img_1_pupildata.get(count-1).left))*20;
					
					int x2 = Math.round(((float)count) * 1810 /((float) Data.img_1_pupildata.size()) + 80);
					float y2 = this.getHeight()/8 - (Float.valueOf(Data.img_1_pupildata.get(count).left))*20;
					
					g.setColor(Color.BLUE);
					g2.draw(new Line2D.Float(x1, y1, x2, y2));
					
					int x3 = Math.round((float)(count - 1) * 1810 /((float) Data.img_1_pupildata.size()) + 80);
					float y3 = this.getHeight()/8*5 - (Float.valueOf(Data.img_1_pupildata.get(count-1).right))*20;
					
					int x4 = Math.round(((float)count) * 1810 /((float) Data.img_1_pupildata.size()) + 80);
					float y4 = this.getHeight()/8*5 - (Float.valueOf(Data.img_1_pupildata.get(count).right))*20;
					
					g.setColor(Color.BLUE);
					g2.draw(new Line2D.Float(x3, y3, x4, y4));
					
					if(count == Data.img_1_pupildata.size()-1) {
						g.setColor(Color.BLACK);
						g.drawLine(x4, 0, x4, this.getHeight()/4);
						g.drawLine(x4, this.getHeight()/4*2, x4, this.getHeight()/4*3);
					}
			}
			
			for(int count = 1; count < Data.img_2_pupildata.size(); count++) {
				int x5 = Math.round((float)(count - 1) * 1810 /((float) Data.img_2_pupildata.size()) + 80);
				float y5 = this.getHeight()/8*3 - (Float.valueOf(Data.img_2_pupildata.get(count-1).left))*20;
				
				int x6 = Math.round(((float)count) * 1810 /((float) Data.img_2_pupildata.size()) + 80);
				float y6 = this.getHeight()/8*3 - (Float.valueOf(Data.img_2_pupildata.get(count).left))*20;
				
				g.setColor(Color.BLUE);
				g2.draw(new Line2D.Float(x5, y5, x6, y6));
				
				int x7 = Math.round((float)(count - 1) * 1810 /((float) Data.img_2_pupildata.size()) + 80);
				float y7 = this.getHeight()/8*7 - (Float.valueOf(Data.img_2_pupildata.get(count-1).right))*20;
				
				int x8 = Math.round(((float)count) * 1810 /((float) Data.img_2_pupildata.size()) + 80);
				float y8 = this.getHeight()/8*7 - (Float.valueOf(Data.img_2_pupildata.get(count).right))*20;
				
				g.setColor(Color.BLUE);
				g2.draw(new Line2D.Float(x7, y7, x8, y8));
				
				if(count == Data.img_2_pupildata.size()-1) {
					g.setColor(Color.BLACK);
					g.drawLine(x8, this.getHeight()/4, x8, this.getHeight()/4*2);
					g.drawLine(x8, this.getHeight()/4*3, x8, this.getHeight()/4*4);
				}
			}
		}
	}
}
