import java.util.ArrayList;


public class Data {
	public static ArrayList<String> original = new ArrayList<String>();
	public static ArrayList<String> log_original = new ArrayList<String>();
	public static ArrayList<Pupil> pupildata = new ArrayList<Pupil>();
	public static ArrayList<Pupil> pupildata_temp = new ArrayList<Pupil>();
	public static ArrayList<Npupil> nor_pupildata = new ArrayList<Npupil>();
	public static ArrayList<String> log_video_time = new ArrayList<String>();
	public static ArrayList<Pupil> interpolate_L = new ArrayList<Pupil>();
	public static ArrayList<PointL> inter_calcul_L = new ArrayList<PointL>();
	public static ArrayList<PointL> inter_result_L = new ArrayList<PointL>();
	public static ArrayList<Npupil> window_normal_inter_data = new ArrayList<Npupil>();
	public static ArrayList<Time> time = new ArrayList<Time>();
	public static ArrayList<Pupil> interpolate_R = new ArrayList<Pupil>();
	public static ArrayList<PointR> inter_result_R = new ArrayList<PointR>();
	public static ArrayList<PointR> inter_calcul_R = new ArrayList<PointR>();
	public static ArrayList<Pupil> original_for_write = new ArrayList<Pupil>();
	public static ArrayList<Npupil> normal_inter_data_for_write = new ArrayList<Npupil>();
	
	public static String file_name;
	public static int left_loss;
	public static int right_loss;
	public static String log_timestamp;
	
	public static float left_data_loss_rate_during_video_play;
	public static float right_data_loss_rate_during_video_play;
	public static float left_loss_rate;
	public static float right_loss_rate;
	
	public static float left_nor_data_mean;
	public static float right_nor_data_mean;
	
	public static int window_size;
	public static float window_pupil_mean_left;
	public static float window_pupil_mean_right;
	public static float left_window_nor_data_mean;
	public static float right_window_nor_data_mean;
	
	public static float pupil_mean_left;
	public static float pupil_sd_left;
	public static float pupil_mean_right;
	public static float pupil_sd_right;
	
	public static String log_video_time_start;
	public static String log_video_time_end;
	public static String overall_time_start;
	public static String overall_time_end;
	public static String log_video_recording_start_time;
	public static String log_video_recording_stop_time;
	public static String ac_log_video_recording_start_time;
	public static String ac_log_video_recording_stop_time;
	
	public static int currentPoint2 = 0;
	
	public static Drawing DF = new Drawing();
	public static Drawing2 DF2 = new Drawing2();
	
	public static String[] location;
	public static String[] time_check;
	public static String[] temp_time_check;
	public static String min;
	public static String sec;
	public static String point_sec;
}