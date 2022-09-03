package jdbc_brk;

import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/burak", "postgres", "665532");
        Statement st = con.createStatement();
        //1. Örnek: Prepared statement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.
        String sql = "update companies set number_of_employees = ? where company = ? ";
        PreparedStatement pst = con.prepareStatement(sql);//prepareStatement ile hazir komutlar olusturuyoruz
        pst.setInt(1, 9999);  //? olanlari set ile atiyoruz
        pst.setString(2, "IBM");
        pst.executeUpdate();//update ile atadigimiz degerleri update ediyoruz
        //System.out.println("degisenRowSayisi = " + degisenRowSayisi);//pst'yi update edelim
        //update ettigimizi cagiralim
        String sql2 = "select*from companies";
        ResultSet result = st.executeQuery(sql2);
        while (result.next()) {
            System.out.println(result.getString(2) + " " + result.getInt(3));
        }

        System.out.println("-------------");
        //GOOGLE ile degisiklik
        pst.setInt(1, 18000);
        pst.setString(2, "GOOGLE");
        pst.executeUpdate();
        String sql3 = "SELECT * FROM companies";
        ResultSet result2 = st.executeQuery(sql3);
        while (result2.next()) {
            System.out.println(result2.getString(2) + " " + result2.getInt(3));
        }
        System.out.println("------------");
        //2. Örnek: "SELECT * FROM <table name>" query'sini prepared statement ile kullanın.
        tabloCagirma(con,"companies");


    }
    public static void tabloCagirma(Connection con,String tableName) {

        try {
            String sql=String.format("select * from %s",tableName); //string format methoduyla kullanicidan alinan
            //2. isim %s yerine gecer
            Statement st=con.createStatement();
            ResultSet result=st.executeQuery(sql);
            while (result.next()) {
                System.out.println(result.getString(1)+" "+result.getString(2)+" "+
                        result.getInt(3));
            }
        }catch (Exception e){
            System.out.println("Tablo olusturulamadi");
        }



    }









}
