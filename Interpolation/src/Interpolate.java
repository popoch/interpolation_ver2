import java.awt.Component;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Interpolate {
	public JFrame frame;
	public Thread t;
	int count2 = 0;
	float y = 0f;
	public JTextField File_Location;
	public JTextField Window_Size_TextField;
	int windowflag = 0;
	
	public Interpolate() {
		run();
	}
	
	public void run() {
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		
		
		frame = new JFrame();
		frame.setBounds(width/2-250, height/2-150, 600, 365);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
//----------------------------------------------------------------------------------------//		
		JButton importer = new JButton("File Import");
		importer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFileChooser JFC = new JFileChooser("C:/Users/Administrator/Desktop");
				JFC.setFileFilter(new FileNameExtensionFilter("CSV", "csv"));
				JFC.setMultiSelectionEnabled(false);
				JFC.showOpenDialog(frame);
				analyze(JFC.getSelectedFile());
				
				String temp_location = String.valueOf(JFC.getSelectedFile());
				Data.location = temp_location.split("\\\\", -1);
				
				File_Location = new JTextField();
				String temp = Data.location[0]+"\\"+Data.location[1]+"\\"+Data.location[2]+"\\"
							  +Data.location[3]+"\\"+Data.location[4]+"\\"+Data.location[5]+"\\"+Data.location[6]+"\\"+"Systemlog"+"\\"+"systemlog.csv";
				File_Location.setText(temp);
				File_Location.setBounds(15, 55, 570, 25);
				frame.getContentPane().add(File_Location);
				File_Location.setColumns(10);
				File file = new File(temp);
				analyze2(file);
			}
		});
		importer.setBounds(15, 15, 150, 25);
		frame.getContentPane().add(importer);
//----------------------------------------------------------------------------------------//		
//		JButton importer2 = new JButton("Log Import");
//		importer2.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO Auto-generated method stub
//				JFileChooser JFC2 = new JFileChooser("C:/Users/Administrator/Desktop");
//				JFC2.setFileFilter(new FileNameExtensionFilter("CSV", "csv"));
//				JFC2.setMultiSelectionEnabled(false);
//				JFC2.showOpenDialog(frame);
//				analyze2(JFC2.getSelectedFile());
//			}
//		});
//		importer2.setBounds(15, 55, 150, 25);
//		frame.getContentPane().add(importer2);
//----------------------------------------------------------------------------------------//		
		JButton graph = new JButton("Graph");
		graph.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Data.currentPoint2 = 0;
				Data.DF.Input_data(Data.currentPoint2);
				Data.DF.repaint();
				Data.DF.setVisible(true);
			}
		});
		graph.setBounds(180, 175, 150, 25);
		frame.getContentPane().add(graph);
//----------------------------------------------------------------------------------------//
		JButton normalize = new JButton("Normalize");
		normalize.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				normalize();
			}
		});
		normalize.setBounds(15, 135, 150, 25);
		frame.getContentPane().add(normalize);
//----------------------------------------------------------------------------------------//		
		JButton interpolate = new JButton("Interpolate");
		interpolate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				interpolate();
			}
		});
		interpolate.setBounds(15, 95, 150, 25);
		frame.getContentPane().add(interpolate);
//----------------------------------------------------------------------------------------//
		JLabel lblWindowSize = new JLabel("Window Size");
		lblWindowSize.setBounds(180, 15, 80, 25);
		lblWindowSize.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblWindowSize);
//----------------------------------------------------------------------------------------//		
		Window_Size_TextField = new JTextField();
		Window_Size_TextField.setText("0");
		Window_Size_TextField.setBounds(275, 15, 55, 25);
		frame.getContentPane().add(Window_Size_TextField);
		Window_Size_TextField.setColumns(10);
//----------------------------------------------------------------------------------------//	
//		JButton method3 = new JButton("Interpolate  >  Normalize  >  Window Size");
//		method3.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				interpolate();
//				normalize();
//				windowflag = 3;
//				windowsize();
//			}			
//		});
//		
//		method3.setBounds(15, 215, 315, 25);
//		frame.getContentPane().add(method3);
//----------------------------------------------------------------------------------------//
//		JButton method2 = new JButton("Interpolate  >  Window Size  >  Normalize");
//		method2.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				interpolate();
//				windowflag = 2;
//				windowsize();
//				normalize();
//			}			
//		});
//		
//		method2.setBounds(15, 255, 315, 25);
//		frame.getContentPane().add(method2);
//----------------------------------------------------------------------------------------//
//		JButton method1 = new JButton("Window Size  >  Interpolate  >  Normalize");
//		method1.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				windowflag = 1;
//				windowsize();
//				interpolate();
//				normalize();
//			}			
//		});
//		
//		method1.setBounds(15, 295, 315, 25);
//		frame.getContentPane().add(method1);
//----------------------------------------------------------------------------------------//
		JButton graph_w = new JButton("Window Graph");
		graph_w.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Data.currentPoint2 = 0;
				Data.DF2.Input_data(Data.currentPoint2);
				Data.DF2.repaint();
				Data.DF2.setVisible(true);
			}
		});
		graph_w.setBounds(345, 295, 150, 25);
		frame.getContentPane().add(graph_w);
//----------------------------------------------------------------------------------------//
		JButton organize = new JButton("Organize");
		organize.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
				// TODO Auto-generated method stub
				JFileChooser JFC = new JFileChooser("/Users/Administrator/Desktop");
				JFC.setSelectedFile(new File("RMK_" + Data.location[5]));
				int interval = JFC.showSaveDialog(null);
				int dif_min = 50;
				int dif_min2 = 50;
				int dif_min3 = 50;
				int dif_min4 = 50;
				
				//get exact time of start/end point of video
				for(int count = 0; count < Data.pupildata.size(); count++) {
					String tmp1 = String.valueOf(Data.pupildata.get(count).timestamp);
					String tmp3 = String.valueOf(Data.log_video_time.get(1));
					String tmp4 = String.valueOf(Data.log_video_recording_start_time);
					String tmp5 = String.valueOf(Data.log_video_recording_stop_time);
					String tmp2 = Data.log_timestamp;
					String[] temps4 = tmp1.split(" ", -1);
					String[] dtime = temps4[1].split(":|\\.", -1);
					String[] temps5 = tmp2.split(":", -1);
					String[] log_video_end = tmp3.split(":", -1);
					String[] log_video_rec_start = tmp4.split(":", -1);
					String[] log_video_rec_stop = tmp5.split(":", -1);
					
					//save original time stamp
					if(count == 0) {
						Data.overall_time_start = String.valueOf(temps4[1]);
					} else if(count == Data.pupildata.size()-1) {
						Data.overall_time_end = String.valueOf(temps4[1]);
					}
					
					Time t = new Time();
					t.hour = dtime[0];
					t.minute = dtime[1];
					t.second = dtime[2];
					t.pointsec = dtime[3];
					Data.time.add(t);
					
					if(dtime[0].equals(temps5[0]) == true && dtime[1].equals(temps5[1]) == true && dtime[2].equals(temps5[2]) == true) {
						
						int result = Math.abs(Integer.valueOf(dtime[3]) - Integer.valueOf(temps5[3]));
						if(result <= dif_min) {
							dif_min = result;
							Data.log_video_time_start = Data.pupildata.get(count).timestamp;
						}
					} else if(dtime[0].equals(log_video_end[0]) == true && dtime[1].equals(log_video_end[1]) == true && dtime[2].equals(log_video_end[2]) == true){
						
						int result2 = Math.abs(Integer.valueOf(dtime[3]) - Integer.valueOf(log_video_end[3]));
						if(result2 <= dif_min2) {
							dif_min2 = result2;
							Data.log_video_time_end = Data.pupildata.get(count).timestamp;
						}
					} else if(dtime[0].equals(log_video_rec_start[0]) == true && dtime[1].equals(log_video_rec_start[1]) == true && dtime[2].equals(log_video_rec_start[2]) == true){
						
						int result3 = Math.abs(Integer.valueOf(dtime[3]) - Integer.valueOf(log_video_rec_start[3]));
						if(result3 <= dif_min3) {
							dif_min3 = result3;
							Data.ac_log_video_recording_start_time = Data.pupildata.get(count).timestamp;
						}
					} else if(dtime[0].equals(log_video_rec_stop[0]) == true && dtime[1].equals(log_video_rec_stop[1]) == true && dtime[2].equals(log_video_rec_stop[2]) == true){
						
						int result4 = Math.abs(Integer.valueOf(dtime[3]) - Integer.valueOf(log_video_rec_stop[3]));
						if(result4 <= dif_min4) {
							dif_min4 = result4;
							Data.ac_log_video_recording_stop_time = Data.pupildata.get(count).timestamp;
						}
					}
				}
				int flag = 0;
				int count4 = 0;
				int count5 = 0;
				int count6 = 0;
				//calculate the data loss rate during video
				for(int count = 0; count < Data.pupildata_temp.size(); count++) {
					if(String.valueOf(Data.pupildata_temp.get(count).timestamp).equalsIgnoreCase(Data.log_video_time_start)) {
						flag = 1;
					}
					if(String.valueOf(Data.pupildata_temp.get(count).timestamp).equalsIgnoreCase(Data.log_video_time_end)) {
						flag = 0;
					}
					if(flag == 1 && Float.valueOf(Data.pupildata_temp.get(count).left) == 0) {
						count5++;
					}
					if(flag == 1 && Float.valueOf(Data.pupildata_temp.get(count).right) == 0) {
						count6++;
					}
					if(flag == 1) {
						count4++;
					}
				}
				
				Data.left_data_loss_rate_during_video_play = ((float)count5/(float)count4)*100;
				Data.right_data_loss_rate_during_video_play = ((float)count6/(float)count4)*100;
				//data writing
					if (interval == JFileChooser.APPROVE_OPTION) {
						FileWriter fw = new FileWriter(JFC.getSelectedFile() + ".csv");
						
						if(Integer.valueOf(Window_Size_TextField.getText()) == 0) {
							
							for(int count = 0; count <= Data.pupildata.size(); count++) {
								if(count == 0) {
									fw.write("Left Pupil Size" + ","
											+ "Right Pupil Size" + ","
											+ "Timestamp" + ","
											+ "Left Data Loss Rate" + ","
											+ "Right Data Loss Rate" + ","
											+ "Normalized Left" + ","
											+ "Normalized Right" + ","
											+ "Data Loss During Video_Left" + ","
											+ "Data Loss During Video_Right" + "\n");
								} else if(count == 1) {
									fw.write(String.valueOf(Data.pupildata.get(count-1).left) + ","
											+ String.valueOf(Data.pupildata.get(count-1).right) + ","
											+ String.valueOf(Data.pupildata.get(count-1).timestamp) + ","
											+ Data.left_loss_rate + "%" + ","
											+ Data.right_loss_rate + "%" + ","
											+ String.valueOf(Data.nor_pupildata.get(count-1).left) + ","
											+ String.valueOf(Data.nor_pupildata.get(count-1).right) + ","
											+ Data.left_data_loss_rate_during_video_play + "%" + ","
											+ Data.right_data_loss_rate_during_video_play + "%" + "\n");
								} else if(count == 2) {
									fw.write(String.valueOf(Data.pupildata.get(count-1).left) + ","
											+ String.valueOf(Data.pupildata.get(count-1).right) + ","
											+ String.valueOf(Data.pupildata.get(count-1).timestamp) + ","
											+ "Left Mean" + ","
											+ "Right Mean" + ","
											+ String.valueOf(Data.nor_pupildata.get(count-1).left) + ","
											+ String.valueOf(Data.nor_pupildata.get(count-1).right) + ","
											+ "Left Nor Mean" + "," 
											+ "Right Nor Mean" + "\n");
								} else if(count == 3) {
									fw.write(String.valueOf(Data.pupildata.get(count-1).left) + ","
											+ String.valueOf(Data.pupildata.get(count-1).right) + ","
											+ String.valueOf(Data.pupildata.get(count-1).timestamp) + ","
											+ Data.pupil_mean_left + ","
											+ Data.pupil_mean_right + ","
											+ String.valueOf(Data.nor_pupildata.get(count-1).left) + ","
											+ String.valueOf(Data.nor_pupildata.get(count-1).right) + ","
											+ String.valueOf(Data.left_nor_data_mean) + "," 
											+ String.valueOf(Data.right_nor_data_mean) + "\n");
								} else if(count == 4) {
									fw.write(String.valueOf(Data.pupildata.get(count-1).left) + ","
											+ String.valueOf(Data.pupildata.get(count-1).right) + ","
											+ String.valueOf(Data.pupildata.get(count-1).timestamp) + ","
											+ "Left SD" + ","
											+ "Right SD" + ","
											+ String.valueOf(Data.nor_pupildata.get(count-1).left) + ","
											+ String.valueOf(Data.nor_pupildata.get(count-1).right) + "\n");
								} else if(count == 5) {
									fw.write(String.valueOf(Data.pupildata.get(count-1).left) + ","
											+ String.valueOf(Data.pupildata.get(count-1).right) + ","
											+ String.valueOf(Data.pupildata.get(count-1).timestamp) + ","
											+ Data.pupil_sd_left + ","
											+ Data.pupil_sd_right + ","
											+ String.valueOf(Data.nor_pupildata.get(count-1).left) + ","
											+ String.valueOf(Data.nor_pupildata.get(count-1).right) + "\n");
								} else if(Data.pupildata.get(count-1).timestamp.equalsIgnoreCase(Data.log_video_time_start)) {
									fw.write(String.valueOf(Data.pupildata.get(count-1).left) + ","
											+ String.valueOf(Data.pupildata.get(count-1).right) + ","
											+ String.valueOf(Data.pupildata.get(count-1).timestamp) + ","
											+ "video start" + "," + ","
											+ String.valueOf(Data.nor_pupildata.get(count-1).left) + ","
											+ String.valueOf(Data.nor_pupildata.get(count-1).right) + "\n");
								} else if(Data.pupildata.get(count-1).timestamp.equalsIgnoreCase(Data.log_video_time_end)) {
									fw.write(String.valueOf(Data.pupildata.get(count-1).left) + ","
											+ String.valueOf(Data.pupildata.get(count-1).right) + ","
											+ String.valueOf(Data.pupildata.get(count-1).timestamp) + ","
											+ "video end" + "," + ","
											+ String.valueOf(Data.nor_pupildata.get(count-1).left) + ","
											+ String.valueOf(Data.nor_pupildata.get(count-1).right) + "\n");
								} else {
										fw.write(String.valueOf(Data.pupildata.get(count-1).left) + ","
												+ String.valueOf(Data.pupildata.get(count-1).right) + ","
												+ String.valueOf(Data.pupildata.get(count-1).timestamp) + ","
												+ "," + ","
												+ String.valueOf(Data.nor_pupildata.get(count-1).left) + ","
												+ String.valueOf(Data.nor_pupildata.get(count-1).right) + "\n");
								}
							}
						} else if(Integer.valueOf(Window_Size_TextField.getText()) != 0) {
							
							for(int count = 0; count <= Data.pupildata.size(); count++) {
								
								if(count < Data.window_normal_inter_data.size()) {
									if(count == 0) {
										fw.write("Left Pupil Size" + ","
												+ "Right Pupil Size" + ","
												+ "Timestamp" + ","
												+ "Left Data Loss Rate" + ","
												+ "Right Data Loss Rate" + ","
												+ "Normalized Left" + ","
												+ "Normalized Right" + ","
												+ "Data Loss During Video_Left" + ","
												+ "Data Loss During Video_Right" + ","
												+ "Windowed left data" + ","
												+ "Windowed right data" + "\n");
									} else if(count == 1) {
										fw.write(String.valueOf(Data.pupildata.get(count-1).left) + ","
												+ String.valueOf(Data.pupildata.get(count-1).right) + ","
												+ String.valueOf(Data.pupildata.get(count-1).timestamp) + ","
												+ Data.left_loss_rate + "%" + ","
												+ Data.right_loss_rate + "%" + ","
												+ String.valueOf(Data.nor_pupildata.get(count-1).left) + ","
												+ String.valueOf(Data.nor_pupildata.get(count-1).right) + ","
												+ Data.left_data_loss_rate_during_video_play + "%" + ","
												+ Data.right_data_loss_rate_during_video_play + "%" + "," 
												+ String.valueOf(Data.window_normal_inter_data.get(count-1).left) + ","
												+ String.valueOf(Data.window_normal_inter_data.get(count-1).right) + "\n");
									} else if(count == 2) {
										fw.write(String.valueOf(Data.pupildata.get(count-1).left) + ","
												+ String.valueOf(Data.pupildata.get(count-1).right) + ","
												+ String.valueOf(Data.pupildata.get(count-1).timestamp) + ","
												+ "Left Mean" + ","
												+ "Right Mean" + ","
												+ String.valueOf(Data.nor_pupildata.get(count-1).left) + ","
												+ String.valueOf(Data.nor_pupildata.get(count-1).right) + ","
												+ "Left Nor Mean" + "," 
												+ "Right Nor Mean" + ","
												+ String.valueOf(Data.window_normal_inter_data.get(count-1).left) + ","
												+ String.valueOf(Data.window_normal_inter_data.get(count-1).right) + "\n");
									} else if(count == 3) {
										fw.write(String.valueOf(Data.pupildata.get(count-1).left) + ","
												+ String.valueOf(Data.pupildata.get(count-1).right) + ","
												+ String.valueOf(Data.pupildata.get(count-1).timestamp) + ","
												+ Data.pupil_mean_left + ","
												+ Data.pupil_mean_right + ","
												+ String.valueOf(Data.nor_pupildata.get(count-1).left) + ","
												+ String.valueOf(Data.nor_pupildata.get(count-1).right) + ","
												+ String.valueOf(Data.left_nor_data_mean) + "," 
												+ String.valueOf(Data.right_nor_data_mean) + ","
												+ String.valueOf(Data.window_normal_inter_data.get(count-1).left) + ","
												+ String.valueOf(Data.window_normal_inter_data.get(count-1).right) + "\n");
									} else if(count == 4) {
										fw.write(String.valueOf(Data.pupildata.get(count-1).left) + ","
												+ String.valueOf(Data.pupildata.get(count-1).right) + ","
												+ String.valueOf(Data.pupildata.get(count-1).timestamp) + ","
												+ "Left SD" + ","
												+ "Right SD" + ","
												+ String.valueOf(Data.nor_pupildata.get(count-1).left) + ","
												+ String.valueOf(Data.nor_pupildata.get(count-1).right) + "," + "," + ","
												+ String.valueOf(Data.window_normal_inter_data.get(count-1).left) + ","
												+ String.valueOf(Data.window_normal_inter_data.get(count-1).right) + "\n");
									} else if(count == 5) {
										fw.write(String.valueOf(Data.pupildata.get(count-1).left) + ","
												+ String.valueOf(Data.pupildata.get(count-1).right) + ","
												+ String.valueOf(Data.pupildata.get(count-1).timestamp) + ","
												+ Data.pupil_sd_left + ","
												+ Data.pupil_sd_right + ","
												+ String.valueOf(Data.nor_pupildata.get(count-1).left) + ","
												+ String.valueOf(Data.nor_pupildata.get(count-1).right) + "," + "," + ","
												+ String.valueOf(Data.window_normal_inter_data.get(count-1).left) + ","
												+ String.valueOf(Data.window_normal_inter_data.get(count-1).right) + "\n");
									} else if(Data.pupildata.get(count-1).timestamp.equalsIgnoreCase(Data.log_video_time_start)) {
										fw.write(String.valueOf(Data.pupildata.get(count-1).left) + ","
												+ String.valueOf(Data.pupildata.get(count-1).right) + ","
												+ String.valueOf(Data.pupildata.get(count-1).timestamp) + ","
												+ "video start" + "," + ","
												+ String.valueOf(Data.nor_pupildata.get(count-1).left) + ","
												+ String.valueOf(Data.nor_pupildata.get(count-1).right) + "," + "," + ","
												+ String.valueOf(Data.window_normal_inter_data.get(count-1).left) + ","
												+ String.valueOf(Data.window_normal_inter_data.get(count-1).right) + "\n");
									} else if(Data.pupildata.get(count-1).timestamp.equalsIgnoreCase(Data.log_video_time_end)) {
										fw.write(String.valueOf(Data.pupildata.get(count-1).left) + ","
												+ String.valueOf(Data.pupildata.get(count-1).right) + ","
												+ String.valueOf(Data.pupildata.get(count-1).timestamp) + ","
												+ "video end" + "," + ","
												+ String.valueOf(Data.nor_pupildata.get(count-1).left) + ","
												+ String.valueOf(Data.nor_pupildata.get(count-1).right) + "," + "," + ","
												+ String.valueOf(Data.window_normal_inter_data.get(count-1).left) + ","
												+ String.valueOf(Data.window_normal_inter_data.get(count-1).right) + "\n");
									} else {
											fw.write(String.valueOf(Data.pupildata.get(count-1).left) + ","
													+ String.valueOf(Data.pupildata.get(count-1).right) + ","
													+ String.valueOf(Data.pupildata.get(count-1).timestamp) + ","
													+ "," + ","
													+ String.valueOf(Data.nor_pupildata.get(count-1).left) + ","
													+ String.valueOf(Data.nor_pupildata.get(count-1).right) + "," + "," + ","
													+ String.valueOf(Data.window_normal_inter_data.get(count-1).left) + ","
													+ String.valueOf(Data.window_normal_inter_data.get(count-1).right) + "\n");
									}
								} else {
									if(count == 0) {
										fw.write("Left Pupil Size" + ","
												+ "Right Pupil Size" + ","
												+ "Timestamp" + ","
												+ "Left Data Loss Rate" + ","
												+ "Right Data Loss Rate" + ","
												+ "Normalized Left" + ","
												+ "Normalized Right" + ","
												+ "Data Loss During Video_Left" + ","
												+ "Data Loss During Video_Right" + "\n");
									} else if(count == 1) {
										fw.write(String.valueOf(Data.pupildata.get(count-1).left) + ","
												+ String.valueOf(Data.pupildata.get(count-1).right) + ","
												+ String.valueOf(Data.pupildata.get(count-1).timestamp) + ","
												+ Data.left_loss_rate + "%" + ","
												+ Data.right_loss_rate + "%" + ","
												+ String.valueOf(Data.nor_pupildata.get(count-1).left) + ","
												+ String.valueOf(Data.nor_pupildata.get(count-1).right) + ","
												+ Data.left_data_loss_rate_during_video_play + "%" + ","
												+ Data.right_data_loss_rate_during_video_play + "%" + "\n");
									} else if(count == 2) {
										fw.write(String.valueOf(Data.pupildata.get(count-1).left) + ","
												+ String.valueOf(Data.pupildata.get(count-1).right) + ","
												+ String.valueOf(Data.pupildata.get(count-1).timestamp) + ","
												+ "Left Mean" + ","
												+ "Right Mean" + ","
												+ String.valueOf(Data.nor_pupildata.get(count-1).left) + ","
												+ String.valueOf(Data.nor_pupildata.get(count-1).right) + ","
												+ "Left Nor Mean" + "," 
												+ "Right Nor Mean" + "\n");
									} else if(count == 3) {
										fw.write(String.valueOf(Data.pupildata.get(count-1).left) + ","
												+ String.valueOf(Data.pupildata.get(count-1).right) + ","
												+ String.valueOf(Data.pupildata.get(count-1).timestamp) + ","
												+ Data.pupil_mean_left + ","
												+ Data.pupil_mean_right + ","
												+ String.valueOf(Data.nor_pupildata.get(count-1).left) + ","
												+ String.valueOf(Data.nor_pupildata.get(count-1).right) + ","
												+ String.valueOf(Data.left_nor_data_mean) + "," 
												+ String.valueOf(Data.right_nor_data_mean) + "\n");
									} else if(count == 4) {
										fw.write(String.valueOf(Data.pupildata.get(count-1).left) + ","
												+ String.valueOf(Data.pupildata.get(count-1).right) + ","
												+ String.valueOf(Data.pupildata.get(count-1).timestamp) + ","
												+ "Left SD" + ","
												+ "Right SD" + ","
												+ String.valueOf(Data.nor_pupildata.get(count-1).left) + ","
												+ String.valueOf(Data.nor_pupildata.get(count-1).right) + "\n");
									} else if(count == 5) {
										fw.write(String.valueOf(Data.pupildata.get(count-1).left) + ","
												+ String.valueOf(Data.pupildata.get(count-1).right) + ","
												+ String.valueOf(Data.pupildata.get(count-1).timestamp) + ","
												+ Data.pupil_sd_left + ","
												+ Data.pupil_sd_right + ","
												+ String.valueOf(Data.nor_pupildata.get(count-1).left) + ","
												+ String.valueOf(Data.nor_pupildata.get(count-1).right) + "\n");
									} else if(Data.pupildata.get(count-1).timestamp.equalsIgnoreCase(Data.log_video_time_start)) {
										fw.write(String.valueOf(Data.pupildata.get(count-1).left) + ","
												+ String.valueOf(Data.pupildata.get(count-1).right) + ","
												+ String.valueOf(Data.pupildata.get(count-1).timestamp) + ","
												+ "video start" + "," + ","
												+ String.valueOf(Data.nor_pupildata.get(count-1).left) + ","
												+ String.valueOf(Data.nor_pupildata.get(count-1).right) + "\n");
									} else if(Data.pupildata.get(count-1).timestamp.equalsIgnoreCase(Data.log_video_time_end)) {
										fw.write(String.valueOf(Data.pupildata.get(count-1).left) + ","
												+ String.valueOf(Data.pupildata.get(count-1).right) + ","
												+ String.valueOf(Data.pupildata.get(count-1).timestamp) + ","
												+ "video end" + "," + ","
												+ String.valueOf(Data.nor_pupildata.get(count-1).left) + ","
												+ String.valueOf(Data.nor_pupildata.get(count-1).right) + "\n");
									} else {
											fw.write(String.valueOf(Data.pupildata.get(count-1).left) + ","
													+ String.valueOf(Data.pupildata.get(count-1).right) + ","
													+ String.valueOf(Data.pupildata.get(count-1).timestamp) + ","
													+ "," + ","
													+ String.valueOf(Data.nor_pupildata.get(count-1).left) + ","
													+ String.valueOf(Data.nor_pupildata.get(count-1).right) + "\n");
									}
								}
							}
						}
						fw.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		organize.setBounds(15, 175, 150, 25);
		frame.getContentPane().add(organize);
//----------------------------------------------------------------------------------------//	
		JButton winorganize = new JButton("Window Organize");
		winorganize.addActionListener(new ActionListener() {

			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				interpolate();
				normalize();
				windowflag = 3;
				windowsize();
				
				try {
				// TODO Auto-generated method stub
				JFileChooser JFC = new JFileChooser("/Users/Administrator/Desktop");
				JFC.setSelectedFile(new File("RMK_WIN_" + Data.location[5]));
				int interval = JFC.showSaveDialog(null);
				
				//data writing
					if (interval == JFileChooser.APPROVE_OPTION) {
						FileWriter fw = new FileWriter(JFC.getSelectedFile() + ".csv");
						
							for(int count = 0; count <= Data.original_for_write.size(); count++) {
								if(count == 0) {
									fw.write("avgX" + ","
											+ "avgY" + ","
											+ "timestamp" + "\n");
									} else {
										fw.write(String.valueOf(Data.original_for_write.get(count-1).avgX) + ","
												+ String.valueOf(Data.original_for_write.get(count-1).avgY) + ","
												+ String.valueOf(Data.original_for_write.get(count-1).time) + "\n");
									}
							}
						
//						for(int count = 0; count <= Data.window_normal_inter_data.size(); count++) {
//							if(count == 0) {
//								fw.write("Left" + ","
//										+ "Right" + "\n");
//								} else {
//									fw.write(String.valueOf(Data.window_normal_inter_data.get(count-1).left) + ","
//											+ String.valueOf(Data.window_normal_inter_data.get(count-1).right) + "\n");
//								}
//						}
						fw.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		winorganize.setBounds(15, 255, 150, 25);
		frame.getContentPane().add(winorganize);
	}
//----------------------------------------------------------------------------------------//
	private void analyze(File inputFile) {
		//copy specific data into memory
		try{
			Data.original.clear();
			Data.pupildata.clear();
			Data.pupildata_temp.clear();
			Data.left_loss = 0;
			Data.right_loss = 0;
			Data.original = (ArrayList<String>) Files.readAllLines(inputFile.toPath(), Charset.forName("UTF-8"));
			Data.original.remove(0);
			Data.file_name = inputFile.getName();
			int count = 0;
			
			for(String temp : Data.original) {
				String[] temps = temp.split(",", -1);
				//(value - mean) / sd)
				try {
					if(temps[0].equalsIgnoreCase("tracker") == true && temps[1].equalsIgnoreCase("get") == true) {
						if(Float.valueOf(temps[12]) >= 0.0f && Float.valueOf(temps[19]) >= 0.0f) {
							Pupil p = new Pupil();
							p.left = Float.valueOf(temps[12]);
							p.right = Float.valueOf(temps[19]);
							p.timestamp = temps[24];
							p.avgX = Float.valueOf(temps[3]);
							p.avgY = Float.valueOf(temps[4]);
							p.time = Long.valueOf(temps[23]);
							Data.pupildata.add(p);

							Pupil pt = new Pupil();
							pt.left = Float.valueOf(temps[12]);
							pt.right = Float.valueOf(temps[19]);
							pt.timestamp = temps[24];
							Data.pupildata_temp.add(pt);
							
							if(Float.valueOf(temps[12]) == 0) {
								Data.left_loss += 1;
							}
							if(Float.valueOf(temps[19]) == 0) {
								Data.right_loss += 1;
							}
							count++;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			Data.left_loss_rate = (Float.valueOf(Data.left_loss) / count) * 100;
			Data.right_loss_rate = (Float.valueOf(Data.right_loss) / count) * 100;

			JOptionPane.showMessageDialog(frame, "Data Importing Complete");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//----------------------------------------------------------------------------------------//	
	private void analyze2(File inputFile2) {
		String log_timestamp = null;
		String log_video_recording_start = null;
		String log_video_recording_stop = null;
		//analyze log data
		try {
			Data.log_original.clear();
			Data.log_video_time.clear();
			
			Data.log_original = (ArrayList<String>) Files.readAllLines(inputFile2.toPath(), Charset.forName("UTF-8"));
			
			for(String temp2 : Data.log_original) {
				String[] temps2 = temp2.split(",", -1);
				
				try {
					if(temps2[2].contains("Video Showing") == true) {
						String[] temps3 = temps2[0].split("-", -1);
						log_timestamp = (Integer.valueOf(temps3[3])+12) +":"+ temps3[4] +":"+ temps3[5] +":"+ temps3[6]; 
					}
					if(temps2[2].contains("Introduction Showing") == true) {
						String[] temps4 = temps2[0].split("-", -1);
						Data.log_video_time.add((Integer.valueOf(temps4[3])+12) +":"+ temps4[4] +":"+ temps4[5] +":"+ temps4[6]);
					}
					
					String[] temp_split = temps2[2].split(" ", -1);
					
					if(temp_split[0].equalsIgnoreCase("Video") == true && temp_split[1].equalsIgnoreCase("Recording") == true && temp_split[2].equalsIgnoreCase("Start") == true) {
						String[] temps5 = temps2[0].split("-", -1);
						log_video_recording_start = (Integer.valueOf(temps5[3])+12) +":"+ temps5[4] +":"+ temps5[5] +":"+ temps5[6]; 
						Data.log_video_recording_start_time = log_video_recording_start;
					}
					if(temp_split[0].equalsIgnoreCase("Video") == true && temp_split[1].equalsIgnoreCase("Recording") == true && temp_split[2].equalsIgnoreCase("Stop") == true) {
						String[] temps6 = temps2[0].split("-", -1);
						log_video_recording_stop = (Integer.valueOf(temps6[3])+12) +":"+ temps6[4] +":"+ temps6[5] +":"+ temps6[6]; 
						Data.log_video_recording_stop_time = log_video_recording_stop;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			Data.log_timestamp = log_timestamp;
			JOptionPane.showMessageDialog(frame, "Log Data Importing Complete");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//----------------------------------------------------------------------------------------//
	private void interpolate() {
		Data.inter_calcul_L.clear();
		Data.inter_result_L.clear();
		Data.interpolate_L.clear();
		Data.inter_calcul_R.clear();
		Data.inter_result_R.clear();
		Data.interpolate_R.clear();
		
		int flag1 = 0;
				
		//copy and organize the data (LEFT)
		outerloop1:
		for(int count = 0; count < Data.pupildata.size(); count++) {
			if(count+1 >= Data.pupildata.size()) {
				break outerloop1;
			}
			if(Float.valueOf(Data.pupildata.get(count).left) != 0 && Float.valueOf(Data.pupildata.get(count+1).left) == 0) {
				flag1 = 1;
				Pupil ip = new Pupil();
				
				ip.left = Data.pupildata.get(count).left;
				ip.timestamp = Data.pupildata.get(count).timestamp;
				Data.interpolate_L.add(ip);
			} else if(Float.valueOf(Data.pupildata.get(count).left) == 0 && flag1 == 1) {
				Pupil ip = new Pupil();
									
				ip.left = Data.pupildata.get(count).left;
				ip.timestamp = Data.pupildata.get(count).timestamp;
				Data.interpolate_L.add(ip);
			} else if(Float.valueOf(Data.pupildata.get(count).left) != 0 && flag1 == 1 && Float.valueOf(Data.pupildata.get(count-1).left) == 0) {
				if(Float.valueOf(Data.pupildata.get(count+1).left) == 0) {
					flag1 = 1;
				} else {
					flag1 = 0;
				}
				Pupil ip = new Pupil();
				
				ip.left = Data.pupildata.get(count).left;
				ip.timestamp = Data.pupildata.get(count).timestamp;
				Data.interpolate_L.add(ip);
			}
		}
		
		int flag2 = 0;
		//copy and organize the data (RIGHT)
		outerloop2:
		for(int count = 0; count < Data.pupildata.size(); count++) {
			if(count+1 >= Data.pupildata.size()) {
				break outerloop2;
			}
			if(Float.valueOf(Data.pupildata.get(count).right) != 0 && Float.valueOf(Data.pupildata.get(count+1).right) == 0) {
				flag2 = 1;
				Pupil ip = new Pupil();
				
				ip.right = Data.pupildata.get(count).right;
				ip.timestamp = Data.pupildata.get(count).timestamp;
				Data.interpolate_R.add(ip);
			} else if(Float.valueOf(Data.pupildata.get(count).right) == 0 && flag2 == 1) {
				Pupil ip = new Pupil();
									
				ip.right = Data.pupildata.get(count).right;
				ip.timestamp = Data.pupildata.get(count).timestamp;
				Data.interpolate_R.add(ip);
			} else if(Float.valueOf(Data.pupildata.get(count).right) != 0 && flag2 == 1 && Float.valueOf(Data.pupildata.get(count-1).right) == 0) {
				if(Float.valueOf(Data.pupildata.get(count+1).right) == 0) {
					flag2 = 1;
				} else {
					flag2 = 0;
				}
				Pupil ip = new Pupil();
				
				ip.right = Data.pupildata.get(count).right;
				ip.timestamp = Data.pupildata.get(count).timestamp;
				Data.interpolate_R.add(ip);
			}
		}
		
		float xL1 = 0f;
		float yL1 = 0f;
		float yL2 = 0f;
		int flagL = 0;
		//calculation
		outerloop3:
		for(int count = 0; count < Data.interpolate_L.size(); count++) {
			if(count+1 >= Data.interpolate_L.size()) {
				break outerloop3;
			}
			if(Float.valueOf(Data.interpolate_L.get(count).left) == 0) {
				flagL = 1;
				if(Float.valueOf(Data.interpolate_L.get(count-1).left) != 0 && Float.valueOf(Data.interpolate_L.get(count+1).left) == 0) {
					Data.inter_calcul_L.clear();
					
					yL1 = Float.valueOf(Data.interpolate_L.get(count-1).left);
					
					PointL pl = new PointL();
					pl.left = Float.valueOf(Data.interpolate_L.get(count).left);
					pl.timestamp = String.valueOf(Data.interpolate_L.get(count).timestamp);
					Data.inter_calcul_L.add(pl);

				} else if(Float.valueOf(Data.interpolate_L.get(count-1).left) == 0 && Float.valueOf(Data.interpolate_L.get(count+1).left) == 0) {
					
					PointL py = new PointL();
					py.left = Float.valueOf(Data.interpolate_L.get(count).left);
					py.timestamp = String.valueOf(Data.interpolate_L.get(count).timestamp);
					Data.inter_calcul_L.add(py);

				} else if(Float.valueOf(Data.interpolate_L.get(count-1).left) == 0 && Float.valueOf(Data.interpolate_L.get(count+1).left) != 0) {
					
					yL2 = Float.valueOf(Data.interpolate_L.get(count+1).left);
					
					PointL pl = new PointL();
					pl.left = Float.valueOf(Data.interpolate_L.get(count).left);
					pl.timestamp = String.valueOf(Data.interpolate_L.get(count).timestamp);
					Data.inter_calcul_L.add(pl);

					calcul(xL1,yL1,Float.valueOf(Data.inter_calcul_L.size())+2,yL2,flagL);

				} else if(Float.valueOf(Data.interpolate_L.get(count-1).left) != 0 && Float.valueOf(Data.interpolate_L.get(count+1).left) != 0) {
					Data.inter_calcul_L.clear();
					
					yL1 = Float.valueOf(Data.interpolate_L.get(count-1).left);
					yL2 = Float.valueOf(Data.interpolate_L.get(count+1).left);
					
					PointL pl = new PointL();
					pl.left = Float.valueOf(Data.interpolate_L.get(count).left);
					pl.timestamp = String.valueOf(Data.interpolate_L.get(count).timestamp);
					Data.inter_calcul_L.add(pl);
					
					calcul(xL1,yL1,Float.valueOf(Data.inter_calcul_L.size())+2,yL2,flagL);
				}
			}
		}
		
		float xR1 = 0f;
		float yR1 = 0f;
		float yR2 = 0f;
		int flagR = 0;
		//calculation
		outerloop4:
		for(int count = 0; count < Data.interpolate_R.size(); count++) {
			if(count+1 >= Data.interpolate_R.size()) {
				break outerloop4;
			}
			if(Float.valueOf(Data.interpolate_R.get(count).right) == 0) {
				flagR = 0;
				if(Float.valueOf(Data.interpolate_R.get(count-1).right) != 0 && Float.valueOf(Data.interpolate_R.get(count+1).right) == 0) {
					Data.inter_calcul_R.clear();
					
					yR1 = Float.valueOf(Data.interpolate_R.get(count-1).right);
					
					PointR pr = new PointR();
					pr.right = Float.valueOf(Data.interpolate_R.get(count).right);
					pr.timestamp = String.valueOf(Data.interpolate_R.get(count).timestamp);
					Data.inter_calcul_R.add(pr);

				} else if(Float.valueOf(Data.interpolate_R.get(count-1).right) == 0 && Float.valueOf(Data.interpolate_R.get(count+1).right) == 0) {
					
					PointR pr = new PointR();
					pr.right = Float.valueOf(Data.interpolate_R.get(count).right);
					pr.timestamp = String.valueOf(Data.interpolate_R.get(count).timestamp);
					Data.inter_calcul_R.add(pr);

				} else if(Float.valueOf(Data.interpolate_R.get(count-1).right) == 0 && Float.valueOf(Data.interpolate_R.get(count+1).right) != 0) {
					
					yR2 = Float.valueOf(Data.interpolate_R.get(count+1).right);
					
					PointR pr = new PointR();
					pr.right = Float.valueOf(Data.interpolate_R.get(count).right);
					pr.timestamp = String.valueOf(Data.interpolate_R.get(count).timestamp);
					Data.inter_calcul_R.add(pr);

					calcul(xR1,yR1,Float.valueOf(Data.inter_calcul_R.size())+2,yR2,flagR);

				} else if(Float.valueOf(Data.interpolate_R.get(count-1).right) != 0 && Float.valueOf(Data.interpolate_R.get(count+1).right) != 0) {
					Data.inter_calcul_R.clear();
					
					yR1 = Float.valueOf(Data.interpolate_R.get(count-1).right);
					yR2 = Float.valueOf(Data.interpolate_R.get(count+1).right);
					
					PointR pr = new PointR();
					pr.right = Float.valueOf(Data.interpolate_R.get(count).right);
					pr.timestamp = String.valueOf(Data.interpolate_R.get(count).timestamp);
					Data.inter_calcul_R.add(pr);
					
					calcul(xR1,yR1,Float.valueOf(Data.inter_calcul_R.size())+2,yR2,flagR);
				}
			}
		}
		
		for(int count = 0; count < Data.pupildata.size(); count++) {
			if(Float.valueOf(Data.pupildata.get(count).left) == 0) {
				String time = String.valueOf(Data.pupildata.get(count).timestamp);
				for(int count2 = 0; count2 < Data.inter_result_L.size(); count2++) {
					if(time.equalsIgnoreCase(String.valueOf(Data.inter_result_L.get(count2).timestamp))) {
						Data.pupildata.get(count).left = Data.inter_result_L.get(count2).left;
					}
				}
			}
		}
		
		for(int count = 0; count < Data.pupildata.size(); count++) {
			if(Float.valueOf(Data.pupildata.get(count).right) == 0) {
				String time = String.valueOf(Data.pupildata.get(count).timestamp);
				for(int count2 = 0; count2 < Data.inter_result_R.size(); count2++) {
					if(time.equalsIgnoreCase(String.valueOf(Data.inter_result_R.get(count2).timestamp))) {
						Data.pupildata.get(count).right = Data.inter_result_R.get(count2).right;
					}
				}
			}
		}
		JOptionPane.showMessageDialog(frame, "Data Interpolation Complete");
	}
	
	private void calcul(float x1, float y1, float x2, float y2, int flag) {
		float z = Math.round(((y2 - y1) / (x2 - x1))*10000f) / 10000f;

		if(flag == 1) {
			for(int x = 1; x < x2-1; x++) {
				Data.inter_calcul_L.get(x-1).left = z*(x - x1) + (y1);
				
				PointL pl2 = new PointL();
				pl2.left = Float.valueOf(Data.inter_calcul_L.get(x-1).left);
				pl2.timestamp = String.valueOf(Data.inter_calcul_L.get(x-1).timestamp);
				Data.inter_result_L.add(pl2);			
			}
		} else if(flag == 0) {
			for(int x = 1; x < x2-1; x++) {
				Data.inter_calcul_R.get(x-1).right = z*(x - x1) + (y1);
				
				PointR pr2 = new PointR();
				pr2.right = Float.valueOf(Data.inter_calcul_R.get(x-1).right);
				pr2.timestamp = String.valueOf(Data.inter_calcul_R.get(x-1).timestamp);
				Data.inter_result_R.add(pr2);			
			}
		}
	}
//----------------------------------------------------------------------------------------//
	private void normalize() {
		float pupil_mean_left = 0.4f;
		float pupil_sd_left = 0.4f;
		float pupil_mean_right = 0.4f;
		float pupil_sd_right = 0.4f;
		int size_count = 0;
		
		Data.nor_pupildata.clear();
		//calculate the mean
		try {
			for(int count = 0; count < Data.pupildata.size(); count++) {
				pupil_mean_left += Float.valueOf(Data.pupildata.get(count).left);
				pupil_mean_right += Float.valueOf(Data.pupildata.get(count).right);
				size_count++;
			}
			
			Data.pupil_mean_left = Math.round((pupil_mean_left / (float)size_count)*10000f) / 10000f;
			Data.pupil_mean_right = Math.round((pupil_mean_right / (float)size_count)*10000f) / 10000f;
			//calculate the SD
			for(int count2 = 0; count2 < Data.pupildata.size(); count2++) {
				pupil_sd_left += (float) Math.pow((Float.valueOf(Data.pupildata.get(count2).left) - Data.pupil_mean_left), 2);
				pupil_sd_right += (float) Math.pow((Float.valueOf(Data.pupildata.get(count2).right) - Data.pupil_mean_right), 2);
			}
			pupil_sd_left = pupil_sd_left / (float)size_count;
			pupil_sd_right = pupil_sd_right / (float)size_count;
			Data.pupil_sd_left = (float) Math.sqrt(pupil_sd_left);
			Data.pupil_sd_right = (float) Math.sqrt(pupil_sd_right);
			//normalization
			for(int count2 = 0; count2 < Data.pupildata.size(); count2++) {
				Npupil np = new Npupil();
				np.left = (Float.valueOf(Data.pupildata.get(count2).left) - Data.pupil_mean_left) / Data.pupil_sd_left;
				np.right = (Float.valueOf(Data.pupildata.get(count2).right) - Data.pupil_mean_right) / Data.pupil_sd_right;
				np.timestamp = String.valueOf(Data.pupildata.get(count2).timestamp);
				np.time = Float.valueOf(Data.pupildata.get(count2).time);
				Data.nor_pupildata.add(np);
			}
			
			float left_cal = 0f;
			float right_cal = 0f;
			//nor mean
			for(int count3 = 0; count3 < Data.nor_pupildata.size(); count3++) {
				left_cal += Float.valueOf(Data.nor_pupildata.get(count3).left);
				right_cal += Float.valueOf(Data.nor_pupildata.get(count3).right);
				
				Data.left_nor_data_mean = left_cal / Float.valueOf(Data.nor_pupildata.size()) * 100;
				Data.right_nor_data_mean = right_cal / Float.valueOf(Data.nor_pupildata.size()) * 100;
			}
			JOptionPane.showMessageDialog(frame, "Data Normalization Complete");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//----------------------------------------------------------------------------------------//
	public void windowsize(){
		int dif_min = 50;
		int dif_min2 = 50;
		int dif_min3 = 50;
		int dif_min4 = 50;
		
		//get exact time of start/end point of video
		for(int count = 0; count < Data.pupildata.size(); count++) {
			String tmp1 = String.valueOf(Data.pupildata.get(count).timestamp);
			String tmp3 = String.valueOf(Data.log_video_time.get(1));
			String tmp4 = String.valueOf(Data.log_video_recording_start_time);
			String tmp5 = String.valueOf(Data.log_video_recording_stop_time);
			String tmp2 = Data.log_timestamp;
			String[] temps4 = tmp1.split(" ", -1);
			String[] dtime = temps4[1].split(":|\\.", -1);
			String[] temps5 = tmp2.split(":", -1);
			String[] log_video_end = tmp3.split(":", -1);
			String[] log_video_rec_start = tmp4.split(":", -1);
			String[] log_video_rec_stop = tmp5.split(":", -1);
			
			//save original time stamp
			if(count == 0) {
				Data.overall_time_start = String.valueOf(temps4[1]);
			} else if(count == Data.pupildata.size()-1) {
				Data.overall_time_end = String.valueOf(temps4[1]);
			}
			
			Time t = new Time();
			t.hour = dtime[0];
			t.minute = dtime[1];
			t.second = dtime[2];
			t.pointsec = dtime[3];
			Data.time.add(t);
			
			if(dtime[0].equals(temps5[0]) == true && dtime[1].equals(temps5[1]) == true && dtime[2].equals(temps5[2]) == true) {
				
				int result = Math.abs(Integer.valueOf(dtime[3]) - Integer.valueOf(temps5[3]));
				if(result <= dif_min) {
					dif_min = result;
					Data.log_video_time_start = Data.pupildata.get(count).timestamp;
				}
			} else if(dtime[0].equals(log_video_end[0]) == true && dtime[1].equals(log_video_end[1]) == true && dtime[2].equals(log_video_end[2]) == true){
				
				int result2 = Math.abs(Integer.valueOf(dtime[3]) - Integer.valueOf(log_video_end[3]));
				if(result2 <= dif_min2) {
					dif_min2 = result2;
					Data.log_video_time_end = Data.pupildata.get(count).timestamp;
				}
			} else if(dtime[0].equals(log_video_rec_start[0]) == true && dtime[1].equals(log_video_rec_start[1]) == true && dtime[2].equals(log_video_rec_start[2]) == true){
				
				int result3 = Math.abs(Integer.valueOf(dtime[3]) - Integer.valueOf(log_video_rec_start[3]));
				if(result3 <= dif_min3) {
					dif_min3 = result3;
					Data.ac_log_video_recording_start_time = Data.pupildata.get(count).timestamp;
				}
			} else if(dtime[0].equals(log_video_rec_stop[0]) == true && dtime[1].equals(log_video_rec_stop[1]) == true && dtime[2].equals(log_video_rec_stop[2]) == true){
				
				int result4 = Math.abs(Integer.valueOf(dtime[3]) - Integer.valueOf(log_video_rec_stop[3]));
				if(result4 <= dif_min4) {
					dif_min4 = result4;
					Data.ac_log_video_recording_stop_time = Data.pupildata.get(count).timestamp;
				}
			}
		}
		int flag = 0;
		for(int i = 0; i < Data.nor_pupildata.size(); i++){
			if(Data.nor_pupildata.get(i).timestamp.equalsIgnoreCase(Data.log_video_time_start)){
				flag = 1;
			}
			if(Data.nor_pupildata.get(i).timestamp.equalsIgnoreCase(Data.log_video_time_end)){
				flag = 2;
			}
			if(flag == 1) {
				Npupil temp = new Npupil();
				temp.left = Data.nor_pupildata.get(i).left;
				temp.right = Data.nor_pupildata.get(i).right;
				temp.timestamp = Data.nor_pupildata.get(i).timestamp;
				Data.normal_inter_data_for_write.add(temp);
				
				Pupil temp2 = new Pupil();
				temp2.avgX = Data.pupildata.get(i).avgX;
				temp2.avgY = Data.pupildata.get(i).avgY;
				temp2.timestamp = Data.pupildata.get(i).timestamp;
				temp2.time = Data.pupildata.get(i).time;
				Data.original_for_write.add(temp2);
			} 
			if(flag == 2) {
				Npupil temp = new Npupil();
				temp.left = Data.nor_pupildata.get(i).left;
				temp.right = Data.nor_pupildata.get(i).right;
				temp.timestamp = Data.nor_pupildata.get(i).timestamp;
				Data.normal_inter_data_for_write.add(temp);
				
				Pupil temp2 = new Pupil();
				temp2.avgX = Data.pupildata.get(i).avgX;
				temp2.avgY = Data.pupildata.get(i).avgY;
				temp2.timestamp = Data.pupildata.get(i).timestamp;
				temp2.time = Data.pupildata.get(i).time;
				Data.original_for_write.add(temp2);
				
				flag = 3;
			}
		}
		
		if(windowflag == 3) {
			float temp_window_pupil_mean_left = 0;
			float temp_window_pupil_mean_right = 0;
			int window_size_input = Integer.valueOf(Window_Size_TextField.getText()) * 30;
			int array_size = ((int)(Data.normal_inter_data_for_write.size()/window_size_input)) + 1;
			
			float[][] temp2 = new float[array_size][window_size_input];
			float[][] temp3 = new float[array_size][window_size_input];
			
			int i = 0;
			int j = 0;
			
			for(Npupil temp : Data.normal_inter_data_for_write) {	
				temp2[i][j] = temp.left;
				temp3[i][j] = temp.right;
				j++;
				if(j == window_size_input) {
					i++;
					j = 0;
				}
			}
			
			for(int k = 0; k < array_size; k++) {
				
				Npupil temp_data = new Npupil();
				float window_left = 0;
				float window_right = 0;
				
				for(int z = 0; z < window_size_input; z++) {
					if(temp2[k][z] == 0 && temp3[k][z] == 0) {
						window_size_input = z;
					} else {
						window_left += temp2[k][z];
						window_right += temp3[k][z];
					}
				}
			
				temp_data.left = window_left / (float)window_size_input;
				temp_data.right = window_right / (float)window_size_input;
				
				Data.window_normal_inter_data.add(temp_data);
			}
			
			Data.window_size = Integer.valueOf(Window_Size_TextField.getText());
			
			for(int z = 0; z < Data.window_normal_inter_data.size(); z++) {
				temp_window_pupil_mean_left += Data.window_normal_inter_data.get(z).left;
				temp_window_pupil_mean_right += Data.window_normal_inter_data.get(z).right;
			}
			
			Data.window_pupil_mean_left = temp_window_pupil_mean_left/(float)Data.window_normal_inter_data.size();
			Data.window_pupil_mean_right = temp_window_pupil_mean_right/(float)Data.window_normal_inter_data.size();;

		}
	}
}
