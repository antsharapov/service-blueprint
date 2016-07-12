package aero.basel.aaq.serviceblueprint;


import android.os.Environment;

public class GlobalVariables {
    public static String airport;
    public static String service;
    public static String commission;
    public static String person_name;
    public static String person_flight;
    public static String person_flight_date;
    public static String commission_name;
    public static String agent_name;
    public static String agent_photo = "";
    public static long[] timer_base = new long[4];
    public static String[] results_array = new String[70];
    public static int results_array_index = 0;
    public static int triple_test_counter =1;
    public static String results_csv = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_DOCUMENTS) + "/basel_results.csv";

}
