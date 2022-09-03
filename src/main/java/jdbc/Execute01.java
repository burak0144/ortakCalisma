package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.Adim: Drivar'a kaydol
        Class.forName("org.postgresql.Driver");

        //2.Adim: Database'e baglan
       Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/burak","postgres","665532");

        //3.Adim: Statement olustur
        Statement st =con.createStatement();

        //4.Adim: Query calistir
        //1.Example: "workers" adında bir table oluşturup "worker_id,worker_name, worker_salary" sütunlarını ekleyin.
      String sql1="CREATE TABLE workers ( worker_id VARCHAR(50), worker_name VARCHAR(50), worker_salary INT )";
      boolean result=st.execute(sql1);
      System.out.println(result); //False return yapar,cunku data cagrilmadi

     //2.Örnek: Alter table by adding worker_address column into the workers table
      String sql2="Alter TABLE workers add worker_adress VARCHAR(50)";
       st.execute(sql2);

     //  //3.Example: Drop workers table
      String sql3="DROP TABLE workers";
        st.execute(sql3);

        //5.Adim: Baglanti ve Statement'i kapat
       con.close();
       st.close();


    }

}
