package com.xinan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Test2 {


    @org.junit.Test
    public void test() throws  Exception{
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.220:1521:orcl", "test_db", "orcl");
        PreparedStatement ps= connection.prepareStatement("insert into CLOB_TEST (id, name,clob1) values(SEQDSJ.nextval,?,?)");

        ps.setString(1,"测试");
        String clob1 = "";
        for (int i=0;i<4500;i++){
            clob1+="a";
        }
        ps.setString(2,clob1);
        ps.execute();
        connection.close();

    }

    @org.junit.Test
    public void testSelectById() throws  Exception{
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.220:1521:orcl", "test_db", "orcl");
        PreparedStatement ps= connection.prepareStatement( "select id,name,clob1 from CLOB_TEST WHERE id=?");
        ps.setInt(1,28483);
        ResultSet resultSet =  ps.executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getInt("ID"));
            System.out.println(resultSet.getString("NAME"));
            System.out.println(resultSet.getString("CLOB1"));
        }
        connection.close();

    }

    @org.junit.Test
    public void testSelectByClob1() throws  Exception{
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.220:1521:orcl", "test_db", "orcl");
        PreparedStatement ps= connection.prepareStatement( "select id,name,clob1 from CLOB_TEST WHERE CLOB1=?");
        String clob1 = "";
        for (int i=0;i<4500;i++){
            clob1+="a";
        }
        ps.setString(1,clob1);
        ResultSet resultSet =  ps.executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getInt("ID"));
            System.out.println(resultSet.getString("NAME"));
            System.out.println(resultSet.getString("CLOB1"));
        }
        connection.close();

    }

    @org.junit.Test
    public void testSelectByClob2() throws  Exception{
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.220:1521:orcl", "test_db", "orcl");
        PreparedStatement ps= connection.prepareStatement( "select id,name,clob1 from CLOB_TEST WHERE DBMS_LOB.compare(CLOB1,to_clob(?))=0");
        String clob1 = "";
        for (int i=0;i<4500;i++){
            clob1+="b";
        }
       // ps.setString(1,clob1);
        ResultSet resultSet =  ps.executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getInt("ID"));
            System.out.println(resultSet.getString("NAME"));
            System.out.println(resultSet.getString("CLOB1"));
        }
        connection.close();

    }

    @org.junit.Test
    public void testUpdate() throws  Exception{
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.220:1521:orcl", "test_db", "orcl");
        PreparedStatement ps= connection.prepareStatement( "update CLOB_TEST set CLOB1=?");
        String clob1 = "";
        for (int i=0;i<4500;i++){
            clob1+="b";
        }
        ps.setString(1,clob1);
        ps.execute();
        connection.close();


        

    }


}
