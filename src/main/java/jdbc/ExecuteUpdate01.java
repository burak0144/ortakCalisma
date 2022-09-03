package jdbc;

import java.sql.*;

public class ExecuteUpdate01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/burak","postgres","665532");
        Statement st=con.createStatement();

        //1. Örnek: number_of_employees değeri
        //ortalama çalışan sayısından az olan number_of_employees değerlerini 16000 olarak UPDATE edin
        String sql="update companies set number_of_employees=16000\n" +
                "where number_of_employees < (select avg(number_of_employees) from companies)";
       int updateSatirSayisi=st.executeUpdate(sql);//Update edilen satir sayisini return eder
        System.out.println("updateSatirSayisi = " + updateSatirSayisi);
        String sql2="SELECT *FROM companies";
        ResultSet result=st.executeQuery(sql2);
        while (result.next()) {
            // System.out.println(result.getInt(1)+"  "+ result.getString(2)+"  "+ result.getInt(3));
            System.out.printf("%-4d %-10s %-7d\n", result.getInt(1), result.getString(2), result.getInt(3));
        }
       con.close();
        st.close();

        }}
