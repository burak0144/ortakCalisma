package jdbc_brk;

import java.sql.*;

public class ExecuteQuery02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/burak","postgres","665532");
        Statement st=con.createStatement();
        //1. Örnek: companies tablosundan en yüksek
        // ikinci number_of_employees değeri olan company ve number_of_employees değerlerini çağırın.
        String sql="SELECT company,number_of_employees from companies  order by number_of_employees desc limit 1 offset 1";
        ResultSet result=st.executeQuery(sql);
        while (result.next()) {
            System.out.println(result.getString("company")+"--"+result.getInt("number_of_employees"));
        }
        //2.yol subquerry kullanrak
        String sql2="Select company,number_of_employees from companies" +
                " where number_of_employees<(select max(number_of_employees) from companies) order by number_of_employees desc limit 1";
         ResultSet result2=st.executeQuery(sql2);
         while (result2.next()){
             System.out.println(result2.getString("company")+"--"+result2.getInt("number_of_employees"));
         }
         con.close();
         st.close();
         result.close();
         result2.close();
    }

}
