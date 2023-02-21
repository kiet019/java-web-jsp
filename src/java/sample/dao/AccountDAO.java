/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import jdk.nashorn.internal.parser.Token;
import sample.dto.Account;
import sample.utils.DBUtils;

/**
 *
 * @author kietl
 */
public class AccountDAO {

    public static Account getAccount(String emailIn, String passwordIn) throws Exception {
        Connection cn = DBUtils.makeConnection();
        Account acc = null;
        try {
            if (cn != null) {
                String sql = "select accID, email, password, fullname, phone, status, role\n"
                        + "from dbo.Accounts where email=? and password = ? and status = 1";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, emailIn);
                pst.setString(2, passwordIn);
                ResultSet table = pst.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        acc = new Account();
                        acc.setAccid(table.getInt("accID"));
                        acc.setEmail(table.getString("email"));
                        acc.setPassword(table.getString("password"));
                        acc.setFullname(table.getString("fullname"));
                        acc.setPhone(table.getString("phone"));
                        acc.setStatus(table.getInt("status"));
                        acc.setRole(table.getInt("role"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return acc;
    }

    public static ArrayList<Account> getAccounts() throws Exception {
        ArrayList<Account> list = new ArrayList<Account>();
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select accID, email, password, fullname, phone, status, role\n"
                        + "from dbo.Accounts";
                Statement st = cn.createStatement();
                ResultSet table = st.executeQuery(sql);
                if (table != null) {
                    while (table.next()) {
                        int accid = table.getInt("accID");
                        String email = table.getString("email");
                        String password = table.getString("password");
                        String fullname = table.getString("fullname");
                        String phone = table.getString("phone");
                        int status = table.getInt("status");
                        int role = table.getInt("role");
                        Account acc = new Account(accid, email, password, fullname, phone, status, role);
                        list.add(acc);
                    }
                }
            }
        } catch (Exception e) {

        }
        return list;
    }

    public static boolean updateAccountStatus(String email, int status) {
        boolean result = false;
        try {
            Connection cn = DBUtils.makeConnection();
            String sql = "update dbo.Accounts\n"
                    + "set status = ?\n"
                    + "where email = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, status);
            pst.setString(2, email);
            pst.executeUpdate();
            result = true;
        } catch (Exception e) {

        }
        return result;
    }

    public static boolean updateAccount(String email, String newFullname, String newPhone) {
        boolean result = false;

        try {
            Connection cn = DBUtils.makeConnection();
            String sql = "update dbo.Accounts\n"
                    + "set fullname = ?, phone = ?\n"
                    + "where email = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, newFullname);
            pst.setString(2, newPhone);
            pst.setString(3, email);
            pst.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean insertAccount(String newEmail, String newPassword,
            String newFullname, String newPhone, int newStatus, int newRole) {
        boolean result = false;
        try {
            Connection cn = DBUtils.makeConnection();
            String sql = "insert into Accounts(email, password, fullname, phone, status, role)\n"
                    + "values(?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, newEmail);
            pst.setString(2, newPassword);
            pst.setString(3, newFullname);
            pst.setString(4, newPhone);
            pst.setInt(5, newStatus);
            pst.setInt(6, newRole);
            pst.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean updateToken(String token, String email) {
        boolean result = false;
        try {
            Connection cn = DBUtils.makeConnection();
            String sql = "update dbo.Accounts\n"
                    + "set token = ?\n"
                    + "where email = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, token);
            pst.setString(2, email);
            pst.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static Account getAccount(String token) throws Exception {
        Connection cn = DBUtils.makeConnection();
        Account acc = null;
        try {
            if (cn != null) {
                String sql = "select accID, email, password, fullname, phone, status, role\n"
                        + "from dbo.Accounts where token = ? and status = 1";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, token);
                ResultSet table = pst.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        acc = new Account();
                        acc.setAccid(table.getInt("accID"));
                        acc.setEmail(table.getString("email"));
                        acc.setPassword(table.getString("password"));
                        acc.setFullname(table.getString("fullname"));
                        acc.setPhone(table.getString("phone"));
                        acc.setStatus(table.getInt("status"));
                        acc.setRole(table.getInt("role"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return acc;
    }

    public static ArrayList<Account> getAccounts(String emailIn) {
        ArrayList<Account> list = new ArrayList<Account>();
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select accID, email, password, fullname, phone, status, role\n"
                        + "from dbo.Accounts\n"
                        + "where email = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, emailIn);
                ResultSet table = pst.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        int accid = table.getInt("accID");
                        String email = table.getString("email");
                        String password = table.getString("password");
                        String fullname = table.getString("fullname");
                        String phone = table.getString("phone");
                        int status = table.getInt("status");
                        int role = table.getInt("role");
                        Account acc = new Account(accid, email, password, fullname, phone, status, role);
                        list.add(acc);
                    }
                }
            }
        } catch (Exception e) {

        }
        return list;
    }
}
