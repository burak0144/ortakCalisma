package jdbc_brk;

import java.sql.*;

public class ExecuteUpdate {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/burak","postgres","665532");
        Statement st=con.createStatement();

        //1. Örnek: number_of_employees değeri
        //ortalama çalışan sayısından az olan number_of_employees değerlerini 16000 olarak UPDATE edin
        String sql="Update companies Set number_of_employees = 16000 where number_of_employees < (select avg(number_of_employees) from companies) ";
        int degisenRowSayisi= st.executeUpdate(sql);
        System.out.println("degisenRowSayisi = " + degisenRowSayisi);
        String sql2="Select*from companies";
        ResultSet result=st.executeQuery(sql2);
        while(result.next()){
            System.out.printf("%-11S %-9S \n", result.getString(2),result.getInt(3));
        }
        System.out.println("-------------------------------");
        String sql3="Update companies Set company='Elma' where company='APPLE' ";
        st.executeUpdate(sql3);
        String sql4="SELECT*FROM companies";
        ResultSet result2=st.executeQuery(sql4);
        while (result2.next()){
            System.out.printf("%-11S %-7d \n",result2.getString(2),result2.getInt(3));
        }//%-11S buyuk S den oturu kucuk yazdigim Elma'yi buyuk yazdirdi

    }
}
