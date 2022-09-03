package jdbc;

import java.sql.*;

public class ExecuteQuery {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/burak","postgres","665532");
        Statement st=con.createStatement();

        //1. Example:  region id'si 1 olan "country name" değerlerini çağırın
        String sql1="select country_name from countries where region_id=1";
    //    boolean r1=st.execute(sql1);
    //    System.out.println(r1); //true yada false verir.Cunku data cagirma islemi yaptik
//
    //  System.out.println(st.execute(sql1));

        //Recordlari gormek icin executeQuery() methodu kullanmaliriz
        ResultSet result1=st.executeQuery(sql1);

         while(result1.next()){

           System.out.println(result1.getString("country_name"));

        }
       //2.Örnek: "region_id"nin 2'den büyük olduğu "country_id" ve "country_name" değerlerini çağırın.
       String sql2="select country_id,country_name from countries where region_id>2";
      //  st.executeQuery(sql2);
       ResultSet result2=st.executeQuery(sql2);
       while (result2.next()){
//
           System.out.println(result2.getString("country_id")+"-->"+result2.getString("country_name"));
//
       }
     //3.Example: "number_of_employees" değeri en düşük olan satırın tüm değerlerini çağırın.
    String sql3="select*from companies  order by number_of_employees limit 1 ";
      ResultSet result3=st.executeQuery(sql3);
       while (result3.next()){
          System.out.println(result3.getInt(1)+"--"+result3.getString(2)+"--"+
                  result3.getInt(3));
        }


      con.close();
       st.close();

    }
}
