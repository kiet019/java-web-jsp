/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import sample.dto.Plant;
import sample.utils.DBUtils;

public class PlantDAO {

    public static ArrayList<Plant> getPlants(String keyword, String searchby) {
        ArrayList<Plant> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null && searchby != null) {
                String sql = "select PID, PName, price, imgPath, description, status, Plants.CateID as 'CateID', CateName\n"
                        + "from Plants join Categories on Plants.CateID = Categories.CateID\n";
                if (searchby.equalsIgnoreCase("byname")) {
                    sql = sql + "where  Plants.PName like ?";
                } else {
                    sql = sql + "where CateName like ?";
                }
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + keyword + "%");
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int id = rs.getInt("PID");
                        String name = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgpath = rs.getString("imgPath");
                        String description = rs.getString("description");
                        int status = rs.getInt("status");
                        int cateid = rs.getInt("CateID");
                        String catename = rs.getString("CateName");
                        Plant plant = new Plant(id, name, price, imgpath, description, status, cateid, catename);
                        list.add(plant);
                    }
                }
            }
        } catch (Exception e) {

        }
        return list;
    }

    public static Plant getPlant(int pID) {
        ArrayList<Plant> pList = PlantDAO.getPlants("", "");
        Plant result = null;
        for (Plant p : pList) {
            if (p.getId() == pID) {
                result = p;
            }
        }
        return result;
    }

    public static ArrayList<Plant> getPlants() {
        ArrayList<Plant> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select PID, PName, price, imgPath, description, status, CateID\n"
                        + "from dbo.Plants";
                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet table = pst.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        int pID = table.getInt("PID");
                        String name = table.getString("PName");
                        int price = table.getInt("price");
                        String imgPath = table.getString("imgPath");
                        String des = table.getString("description");
                        int status = table.getInt("status");
                        int cateID = table.getInt("CateID");
                        Plant plant = new Plant(pID, name, price, imgPath, des, status, cateID, null);
                        list.add(plant);
                    }
                }
            }
        } catch (Exception e) {

        }
        return list;
    }

    public static boolean updatePlantStatus(int plantID, int status) {
        boolean result = false;
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update dbo.Plants\n"
                        + "set status = ?\n"
                        + "where PID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, status);
                pst.setInt(2, plantID);
                pst.executeUpdate();
            }
            result = true;
        } catch (Exception e) {

        }
        return result;
    }
}
