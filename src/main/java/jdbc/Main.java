package jdbc;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
       //DbWork objesini olustur
        DBWork db=new DBWork();
        //Connection fonksionumu cagir
       Connection con = db.connect_to_db("burak","postgres","665532");

       DBWork table=new DBWork();
       table.createTable(con,"employees");
    }
}
