package com.example.messmanagement;

public class Constant {
    private static  final String root_url = "http://messmanagementtool.000webhostapp.com/Mess_Management/";
    public  static  final  String url_register = root_url+"Connect.php";

    public static final String url_login = root_url+"userLogin.php";

    public static final String url_updatebazar = root_url+"updateBazar.php";

    public static final String url_dashboard = root_url+"fullDashboard.php";

    public static final String url_personalInfo = root_url+"Personal_info.php";
    public static final String url_updateList = root_url+"UpdateBazarList.php";
    public static final String url_showBazarList = root_url+"ShowBazarList.php";
    public static final String url_finalCalculation = root_url+"FinalCalculation.php";


    public static String html = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "<style>\n" +
            "table, th, td {\n" +
            "  border: 1px solid black;\n" +
            "  border-collapse: collapse;\n" +
            "}\n" +
            "th, td {\n" +
            "  padding: 0px;\n" +
            "  text-align: center;\n" +
            "}\n" +
            "</style>\n" +
            "</head>\n" +
            "<body>\n" +
            "\n" +
            "\n" +
            "<table style=\"width:100%\">\n" +
            "   <tr>\n" +
            "    <th>Name</th>\n" +
            "    <th>Date</th>\n" +
            "    <th>Amount</th>\n" +
            "    <th>Meal</th>\n" +
            "  </tr>\n" ;
            public static String htmlLast =
            " \n" +
            "</table>\n" +
            "\n" +
            "</body>\n" +
            "</html>\n";



    public static String htmlBazarlistFirst = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "<style>\n" +
            "table, th, td {\n" +
            "  border: 1px solid black;\n" +
            "  border-collapse: collapse;\n" +
            "}\n" +
            "th, td {\n" +
            "  padding: 0px;\n" +
            "  text-align: center;\n" +
            "}\n" +
            "</style>\n" +
            "</head>\n" +
            "<body>\n" +
            "\n" +
            "<table style=\"width:100%\">\n" +
            "   <tr>\n" +
            "    <th>Name</th>\n" +
            "    <th>Date</th>\n" +
            "    <th>State</th>\n" +
            "  </tr>\n" ;

    public static String htmlBazarlistLast =
            " \n" +
            "</table>\n" +
            "\n" +
            "</body>\n" +
            "</html>\n";


    public static String htmlFinalFirst = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "<style>\n" +
            "table, th, td {\n" +
            "  border: 1px solid black;\n" +
            "  border-collapse: collapse;\n" +
            "}\n" +
            "th, td {\n" +
            "  padding: 0px;\n" +
            "  text-align: center;\n" +
            "}\n" +
            "</style>\n" +
            "</head>\n" +
            "<body>\n" +
            "<table style=\"width:100%\">\n" +
            "   <tr>\n" +
            "    <th>Name</th>\n" +
            "    <th>Expense</th>\n" +
            "    <th>Meal</th>\n" +
            "    <th>M_Exp</th>\n" +
            "    <th>State</th>\n" +
            "  </tr>\n" ;

    public static String htmlFinalLast =
            "</table>\n" +
            "</body>\n" +
            "</html>\n";

}
