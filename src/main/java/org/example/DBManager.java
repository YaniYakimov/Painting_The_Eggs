package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DBManager {
    private static DBManager outInstance = new DBManager();
    private static final String IP = "192.168.7.50";
    private static final String PORT = "3306";
    private static final String USER = "cadet";
    private static final String PASS = "cadet";
    private static final String DB_NAME = "s15_test3";
    private Connection connection;
    private DBManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://"+IP+":"+PORT+"/"+DB_NAME, USER, PASS);
        }catch (ClassNotFoundException e) {
            System.out.println("eeee");
        }catch (SQLException e) {
            System.out.println("ole");
        }
    }

    public static DBManager getInstance() {
        return outInstance;
    }

    public static void stat1() throws SQLException, IOException {
        String sql = "SELECT COUNT(*) AS total FROM eggs WHERE date < NOW()";
        Connection c = DBManager.getInstance().connection;
        PreparedStatement ps = c.prepareStatement(sql);
        ResultSet result = ps.executeQuery();
        result.next();
        File report1 = new File("How_many_painted_eggs_till_now.txt");
        if(!report1.exists()){
            report1.createNewFile();
        }
        Files.write(report1.toPath(), result.getBytes("total"), StandardOpenOption.APPEND);
    }

    public static void stat2() throws SQLException, IOException {
        String sql = "SELECT COUNT(e.*) AS e.total FROM eggs AS e JOIN egg_types AS t ON e.type_id = t.id WHERE t.name = 'PATTERNED'";
        Connection c = DBManager.getInstance().connection;
        PreparedStatement ps = c.prepareStatement(sql);
        ResultSet result = ps.executeQuery();
        result.next();
        File report1 = new File("How_many_patterned_eggs.txt");
        if(!report1.exists()){
            report1.createNewFile();
        }
        Files.write(report1.toPath(), result.getBytes("total"), StandardOpenOption.APPEND);
    }

    public static void stat3() throws SQLException, IOException {
        String sql = "SELECT COUNT(e.*) AS e.total FROM eggs AS e JOIN jars AS j ON e.type_id = j.id WHERE j.name = 'GREEN'";
        Connection c = DBManager.getInstance().connection;
        PreparedStatement ps = c.prepareStatement(sql);
        ResultSet result = ps.executeQuery();
        result.next();
        File report1 = new File("How_many_green_eggs.txt");
        if(!report1.exists()){
            report1.createNewFile();
        }
        Files.write(report1.toPath(), result.getBytes("total"), StandardOpenOption.APPEND);
    }

    public static void stat4() throws SQLException, IOException {
        String sql = "SELECT COUNT(e.*) AS e.total, j.color FROM eggs AS e JOIN jars AS j ON e.type_id = j.id GROUP BY e.total DESC LIMIT 1";
        Connection c = DBManager.getInstance().connection;
        PreparedStatement ps = c.prepareStatement(sql);
        ResultSet result = ps.executeQuery();
        result.next();
        File report1 = new File("Jar_with_most_painted_eggs.txt");
        if(!report1.exists()){
            report1.createNewFile();
        }
        Files.write(report1.toPath(), result.getBytes("j.color"), StandardOpenOption.APPEND);
    }

    public static void stat5() throws SQLException, IOException {
        String sql = "SELECT COUNT(e.*) AS e.total FROM eggs AS e JOIN kids AS k ON e.type_id = k.id GROUP BY e.total LIMIT 1";
        Connection c = DBManager.getInstance().connection;
        PreparedStatement ps = c.prepareStatement(sql);
        ResultSet result = ps.executeQuery();
        result.next();
        File report1 = new File("Kid_with_lest_painted_eggs.txt");
        if(!report1.exists()){
            report1.createNewFile();
        }
        Files.write(report1.toPath(), result.getBytes("total"), StandardOpenOption.APPEND);
    }

    public Connection getConnection() {
        return connection;
    }

    public void insertIntoDataBase(Egg egg) {
        String sql = "INSERT INTO eggs (type_id, is_partycolored, date, jar_id, kid_id) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, getTypeId(egg));
            boolean partyColored = false;
            if(egg.getPaint() == Paint.PATTERNED) {
                partyColored = true;
            }
            ps.setBoolean(2, partyColored);
            ps.setDate(3, java.sql.Date.valueOf(String.valueOf(egg.getGettingTime())));
            ps.setInt(4, getJarId(egg));
            ps.setInt(5, getKidId(egg));
            ps.executeQuery();
        }catch (SQLException e) {
            System.out.println("ele male");
        }
    }

    private int getKidId(Egg egg) throws SQLException {
        String sql = "SELECT id FROM kids WHERE name = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, egg.getKid().getName());
        ResultSet result = ps.executeQuery();
        result.next();
        return result.getInt("id");
    }

    private int getJarId(Egg egg) throws SQLException {
        String sql = "SELECT id FROM jars WHERE color = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, egg.getPaint().toString());
        ResultSet result = ps.executeQuery();
        result.next();
        return result.getInt("id");
    }

    public int getTypeId(Egg egg) throws SQLException {
        String sql = "SELECT id FROM egg_type WHERE name = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, egg.getType().toString());
        ResultSet result = ps.executeQuery();
        result.next();
        return result.getInt("id");
    }
}
