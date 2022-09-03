package jdbc;

import java.sql.*;
import java.util.Scanner;

public class PreparedStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/burak","postgres","665532");
        Statement st=con.createStatement();

        //1. Örnek: Prepared statement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.

        //1.Adim:Prepared statement query'sini olustur
        String sql="UPDATE companies SET number_of_employees = ? WHERE company = ?";
        //2.Adim:PreparedStatement objesini olustur.
       PreparedStatement pst= con.prepareStatement(sql);
        //3.Adim: set()... methodlari ile soru isaretleri icin deger gir
        pst.setInt(1,9999);
        pst.setString(2,"IBM");
        //4.Adim:Execute query
        int updateRowSayisi=pst.executeUpdate();
        System.out.println(updateRowSayisi+" satir guncellendi");

        String sql2="select*from companies";
        ResultSet result2=st.executeQuery(sql2);
        while (result2.next()){
            System.out.println(result2.getInt(1)+" "+result2.getString(2)+" "+result2.getInt(3));
        }

        System.out.println("-------------");

       //GOOGLE ile degisiklik
       pst.setInt(1,15000);
       pst.setString(2,"GOOGLE");
      int updateRowSayisi2=pst.executeUpdate();
       System.out.println(updateRowSayisi2+" satir guncellendi");

        String sql3="select*from companies";
       ResultSet result3=st.executeQuery(sql3);
       while (result3.next()){
            System.out.println(result3.getInt(1)+" "+result3.getString(2)+" "+result3.getInt(3));
        }
        System.out.println("-----------");

        //2. Örnek: "SELECT * FROM <table name>" query'sini prepared statement ile kullanın.
         read_data(con,"companies");
        }


    //Bir tablonun istenilen datasini prepared statement ile cagirmak icin kullanilan method
    public static void read_data(Connection con,String tableName){
        try {
            String query=String.format("Select*from %s",tableName); //Format methodu dinamik String olusturmak icin kullanilir
           Statement statement=con.createStatement();
           //SQL query'yi calistirir
           ResultSet rs=statement.executeQuery(query);//Datayi cagirip ResultSet konteynirina koyuyoruz
            while (rs.next()){
                System.out.println(rs.getString(1)+"  "+rs.getString(2)+" "+rs.getInt(3));
            }
        }
        catch (Exception e){
            System.out.println("tablo uygun degil");
        }

    }
}
