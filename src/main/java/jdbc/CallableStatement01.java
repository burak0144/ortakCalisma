package jdbc;

import java.sql.*;

public class CallableStatement01 {

    /*
Java'da methodlar return type sahibi olsa da, void olsa da method olarak adlandırılır.
SQL'de ise data return ediyorsa "function" denir. Return yapmıyorsa "procedure" diye adlandırılır.
 */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/burak","postgres","665532");
        Statement st=con.createStatement();

        //1. Örnek: İki parametre ile çalışıp bu parametreleri toplayarak return yapan bir fonksiyon oluşturun.
        //1.Adim: Fonksiyon kodunu yaz
        String sql1="CREATE OR REPLACE FUNCTION toplamaF(x NUMERIC,y NUMERIC)\n" +
                "RETURNS NUMERIC \n" +
                "LANGUAGE plpgsql\n" +
                "AS \n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "RETURN x+y;\n" +
                "\n" +
                "END\n" +
                "$$\n";
        //2.Adim:Fonksionu calistir
        st.execute(sql1);

        //3.Adim:Fonksionu cagir
        CallableStatement cst1 =con.prepareCall("{? =call toplamaF(?,?)}");

        //4.Adim:Return icin registerOutParameter() methodunu,parametreler icin set() methodlarindan uygun olanlari kullanin
        cst1.registerOutParameter(1,Types.NUMERIC);
        cst1.setInt(2,15);
        cst1.setInt(3,25);

        //5.Adim:Calistirmak icin execute() methodunu kullan
        cst1.execute();

        //6.Adim:Sonucu cagirmak icin return data tipine gore "get" methodlarindan uygun olani kullan
        System.out.println(cst1.getBigDecimal(1));

        //2. Örnek: Koninin hacmini hesaplayan bir function yazın.
        String sql2="CREATE OR REPLACE FUNCTION koniHacmiF(r NUMERIC,h NUMERIC)\n" +
                "RETURNS NUMERIC \n" +
                "LANGUAGE plpgsql\n" +
                "AS \n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "RETURN 3.14*r*r*h/3;\n" +
                "\n" +
                "END\n" +
                "$$\n";
        st.execute(sql2);
        CallableStatement cst2 =con.prepareCall("{? =call koniHacmiF(?,?)}");
        cst2.registerOutParameter(1,Types.NUMERIC);
        cst2.setInt(2,3);
        cst2.setInt(3,5);
        cst2.execute();
        System.out.println(cst2.getBigDecimal(1));
    }
}
