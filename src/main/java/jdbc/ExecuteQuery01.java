package jdbc;

import java.sql.*;

public class ExecuteQuery01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/burak","postgres","665532");
        Statement st=con.createStatement();

        //1. Örnek: companies tablosundan en yüksek
        // ikinci number_of_employees değeri olan company ve number_of_employees değerlerini çağırın.
        String sql="select company,number_of_employees\n" +
                "from companies\n" +
                "order by number_of_employees desc\n" +
                "offset 1 row\n" +
                "FETCH NEXT 1 row only";
        ResultSet result=st.executeQuery(sql);
        while (result.next()){
            System.out.println(result.getString("company")+"--"+result.getInt("number_of_employees"));
        }
        //2.yol subquerry kullanrak
        String sql2="SELECT company, number_of_employees\n" +
                "FROM companies\n" +
                "WHERE number_of_employees <(SELECT MAX(number_of_employees)\n" +
                "                             FROM companies) order by number_of_employees desc limit 1 ";
        ResultSet result2=st.executeQuery(sql2);
        while (result2.next()){
            System.out.println(result2.getString("company")+"--"+result2.getInt("number_of_employees"));
        con.close();
        st.close();
        result.close();
        result2.close();
        }
    }
}
