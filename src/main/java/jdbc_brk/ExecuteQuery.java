package jdbc_brk;

import java.sql.*;

public class ExecuteQuery {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {


        Class.forName("org.postgresql.Driver");
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/burak","postgres","665532");
        Statement st= con.createStatement();

        //postgresql de olusturulan tablodan intellij'e bilgi getireceksek executeQuery() methodunu kullaniriz
        //ResulSet'e st.executeQuery() methodu ile atariz
        //while dongusuyle next() methduyla getiririz
        String sql="SELECT * FROM actors ";

        ResultSet result=st.executeQuery(sql);
        while(result.next()){
            System.out.println(result.getInt(1)+" "+result.getString(2)+" "+result.getInt(3));
        }
        System.out.println("-------------");
        String sql2="SELECT actor_name FROM actors WHERE id=1";
        ResultSet result2=st.executeQuery(sql2);
        while(result2.next()){
            System.out.println(result2.getString("actor_name"));
        }
        // -- SORU1: Iki Tabloda sirket_id’si ayni olanlarin; sirket_ismi, siparis_id ve
        // -- siparis_tarihleri listeleyen bir sorgu yaziniz.
        System.out.println("-------------");
        String sql3=" select B.sirket_isim,A.siparis_id,A.siparis_tarihi\n" +
                "  from siparisler AS A inner join sirketler AS B\n" +
                "  on A.sirket_id=B.sirket_id ";
        ResultSet result3=st.executeQuery(sql3);
        while(result3.next()){
            System.out.println(result3.getString("sirket_isim")+"  "+result3.getInt("siparis_id")+
                    "  "+result3.getDate("siparis_tarihi"));
        }

}
}