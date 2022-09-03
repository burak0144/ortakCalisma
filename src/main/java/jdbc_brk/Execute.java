package jdbc_brk;

import java.sql.*;

public class Execute {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver"); //Driver'a kayit
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/burak","postgres"
                ,"665532"); //Connection yaptik
        Statement st= con.createStatement(); //Statement olusturduk

        //intellij'den postgresql'de  islem yapabilmek icin execute() methodunu kullaniriz


       // st.execute("CREATE TABLE ogretmenler( ogretmen_id VARCHAR(20), ogretmen_adi_soyadi VARCHAR(20), brans VARCHAR(10), telefon INT)");
     //yukardaki satirda bulunan islem tavsiye edilmez bunun icin String'e atayip islem yapariz
        String sql="CREATE TABLE ogretmenler( ogretmen_id VARCHAR(20), ogretmen_adi_soyadi VARCHAR(20), brans VARCHAR(10), telefon varchar(15))";
        st.execute(sql);

        String sql2="INSERT INTO ogretmenler values('10','Berk Karanfil','Kimya','5342324323')"; //int atadigimda hata aldm ?????
        st.execute(sql2);
        //sql de oldugu gibi string ifadeleri ' ' arasinda yazdik

        String sql4="select*from ogretmenler";
        ResultSet result=st.executeQuery(sql4);
        while (result.next()) {
            System.out.println(result.getString(1)+"  "+result.getString(2)+"  "+result.getString(3)+"  "+result.getString(4));
        }

        String sql3="DROP TABLE ogretmenler";
        st.execute(sql3);

        con.close();
        st.close(); // en son connection ile statementi kapatmamiz gerekir
    }
}
