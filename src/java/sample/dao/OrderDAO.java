/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import sample.dto.DateMyself;
import sample.dto.Order;
import sample.dto.OrderDetail;
import sample.utils.DBUtils;

/**
 *
 * @author kietl
 */
public class OrderDAO {

    public static ArrayList<Order> getOrders(String email) {
        ArrayList<Order> list = new ArrayList<>();
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select OrderID, OrdDate, shipdate, status, AccID\n"
                        + "from dbo.Orders\n"
                        + "where AccID = ( select AccID\n"
                        + "                 from dbo.Accounts\n"
                        + "                 where email = ?\n"
                        + "     		)";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                ResultSet table = pst.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        int orderID = table.getInt("OrderID");
                        String orderDate = table.getString("OrdDate");
                        String shipDate = table.getString("shipdate");
                        int status = table.getInt("status");
                        int accID = table.getInt("AccID");
                        Order order = new Order(orderID, orderDate, shipDate, status, accID);
                        list.add(order);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<OrderDetail> getOrderDetail(int orderID) {
        ArrayList<OrderDetail> list = new ArrayList<>();
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select DetailId, OrderID, PID, PName, price, imgPath, quantity\n"
                        + "from dbo.Plants p, dbo.OrderDetails b\n"
                        + "where b.FID = p.PID and b.OrderID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, orderID);
                ResultSet table = pst.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        int detailID = table.getInt("DetailId");
                        int pID = table.getInt("PID");
                        String pName = table.getString("PName");
                        int price = table.getInt("price");
                        String img = table.getString("imgPath");
                        int quantity = table.getInt("quantity");
                        OrderDetail odd = new OrderDetail(detailID, orderID, pID, pName, price, img, quantity);
                        list.add(odd);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean addOrderDetail(int orderID, int fID, int quantity) {
        boolean result = false;
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "insert into OrderDetails(OrderID, FID, quantity)\n"
                        + "values(?, ?, ?)";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, orderID);
                pst.setInt(2, fID);
                pst.setInt(3, quantity);
                pst.executeUpdate();
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ArrayList<Integer> getOrderID(String orderDate, int accID) {
        ArrayList<Integer> list = new ArrayList<>();
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select orderID\n"
                        + "from dbo.Orders\n"
                        + "where OrdDate = ? and status = 1 and AccID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, DateDAO.getDate());
                pst.setInt(2, accID);
                ResultSet table = pst.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        int orderID = table.getInt("orderID");
                        list.add(orderID);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean updateOrderAgain(int accID) {
        boolean result = false;
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "insert into Orders(OrdDate, shipdate, status, AccID)\n"
                        + "values(?, null, 1, ?)";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, DateDAO.getDate());
                pst.setInt(2, accID);
                pst.executeUpdate();
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean updateOrderStatus(int orderID, int status) {
        boolean result = false;
        try {
            Connection cn = DBUtils.makeConnection();
            String sql = "update dbo.Orders\n"
                    + "set status = ?\n"
                    + "where orderID = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, status);
            pst.setInt(2, orderID);
            pst.executeUpdate();
            result = true;
        } catch (Exception e) {

        }
        return result;
    }

    public static boolean insertOrder(String email, HashMap<String, Integer> cart) {
        boolean result = false;
        int accid = 0;
        int orderid = 0;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                cn.setAutoCommit(false); // mo rollback
                String sql = "select accID from Accounts where Accounts.email = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                ResultSet table = pst.executeQuery();
                System.out.println("email: " + email);
                if (table != null) {
                    while (table.next()) {
                        accid = table.getInt("accID");
                    }
                }
                System.out.println("accid: " + accid);// ACCID
                Date d = new Date(System.currentTimeMillis());
                sql = "insert dbo.Orders(OrdDate, shipdate, status, AccID)"
                        + "values(?, NULL, 1, ?)";
                pst = cn.prepareStatement(sql);
                pst.setDate(1, d);
                pst.setInt(2, accid);
                pst.executeUpdate();
                /*OrderDAO.updateOrderAgain(accid);*/
                sql = "select top 1 orderID from Orders order by orderId desc";
                pst = cn.prepareStatement(sql);
                table = pst.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        orderid = table.getInt("orderID");
                    }
                } else {
                    System.out.println("empty orderID");
                }
                System.out.println("orderID: " + orderid);// ORDERID
                Set<String> pids = cart.keySet();
                for (String pid : pids) {
                    sql = "insert OrderDetails "
                            + "values(?, ?, ?)";
                    pst = cn.prepareStatement(sql);
                    pst.setInt(1, orderid);
                    pst.setInt(2, Integer.parseInt(pid));
                    pst.setInt(3, cart.get(pid));
                    pst.executeUpdate();
                    cn.commit();// dong rollback
                    cn.setAutoCommit(true);
                }
                return true;
            } else {

            }
        } catch (Exception e) {
            try {
                if (cn != null) {
                    cn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static ArrayList<Order> getOrders(String email, DateMyself dateFrom, DateMyself dateTo) {
        ArrayList<Order> list = new ArrayList<>();
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select OrderID, OrdDate, shipdate, status, AccID\n"
                        + "from dbo.Orders\n"
                        + "where AccID = ( select AccID\n"
                        + "                 from dbo.Accounts\n"
                        + "                 where email = ?\n"
                        + "     		)";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                ResultSet table = pst.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        int orderID = table.getInt("OrderID");
                        String orderDate = table.getString("OrdDate");
                        String shipDate = table.getString("shipdate");
                        int status = table.getInt("status");
                        int accID = table.getInt("AccID");
                        DateMyself date = new DateMyself();
                        date.setDate(orderDate);
                        if (date.checks(dateFrom) >= 0 && date.checks(dateTo) <= 0) {
                            Order order = new Order(orderID, orderDate, shipDate, status, accID);
                            list.add(order);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<Order> getOrders() {
        ArrayList<Order> list = new ArrayList<>();
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select OrderID, OrdDate, shipdate, status, AccID\n"
                        + "from dbo.Orders";
                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet table = pst.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        int orderID = table.getInt("OrderID");
                        String orderDate = table.getString("OrdDate");
                        String shipdate = table.getString("shipdate");
                        int status = table.getInt("status");
                        int accID = table.getInt("AccID");
                        Order order = new Order(orderID, orderDate, shipdate, status, accID);
                        list.add(order);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean updateShipDate(DateMyself date, int orderID) {
        boolean result = false;
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update dbo.Orders\n"
                        + "set shipdate = ?, status = 2\n"
                        + "where OrderID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, date.toString());
                pst.setInt(2, orderID);
                pst.executeUpdate();
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public static ArrayList<Order> getOrder(int orderID) {
        ArrayList<Order> list = new ArrayList<>();
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select OrderID, OrdDate, shipdate, status, AccID\n"
                        + "from dbo.Orders\n"
                        + "where OrderID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, orderID);
                ResultSet table = pst.executeQuery();
                if (table != null) {
                    while(table.next()) {
                        String orderDate = table.getString("OrdDate");
                        String shipDate = table.getString("shipdate");
                        int status = table.getInt("status");
                        int accID = table.getInt("AccID");
                        Order order = new Order(orderID, orderDate, shipDate, status, accID);
                        list.add(order);
                    }
                }
            }
        } catch (Exception e) {
            
        }
        return list;
    }
}
