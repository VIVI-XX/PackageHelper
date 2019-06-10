package com.example.tinkpad.packagehelper;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBManager {
    private static final String DBDRIVER = "com.mysql.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost/xx?useUnicode=true&characterEncoding=UTF-8";
    private static final String DBUSER = "root";
    private static final String DBPASSWORD = "9945";

    public static boolean linkMysql() {
        Connection conn=null;
        PreparedStatement stmt=null;
        try {
            Class.forName(DBDRIVER).newInstance();
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        try{
            conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
            String sql = "SELECT * from 表名";
            stmt= conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String sUser = rs.getString("userName");
                Log.i("temp", sUser);
            }
            rs.close();
            stmt.close();;
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        finally {
            if(conn!=null){
                try {
                    conn.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        }

    }
}
