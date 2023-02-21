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
import sample.dto.Category;
import sample.utils.DBUtils;

/**
 *
 * @author kietl
 */
public class CategoryDAO {

    public static ArrayList<Category> getCategories() {
        ArrayList<Category> list = new ArrayList<>();
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select CateID, CateName\n"
                        + "from dbo.Categories";
                Statement st = cn.createStatement();
                ResultSet table = st.executeQuery(sql);
                if (table != null) {
                    while (table.next()) {
                        int cateID = table.getInt("CateID");
                        String name = table.getString("CateName");
                        Category cate = new Category(cateID, name);
                        list.add(cate);
                    }
                }
            }
        } catch (Exception e) {

        }
        return list;
    }
    
    public static ArrayList<Category> getCategory(int cateID) {
        ArrayList<Category> list = new ArrayList<>();
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select CateID, CateName\n"
                        + "from dbo.Categories\n"
                        + "where CateID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, cateID);
                ResultSet table = pst.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        String name = table.getString("CateName");
                        Category cate = new Category(cateID, name);
                        list.add(cate);
                    }
                }
            }
        } catch (Exception e) {

        }
        return list;
    }
}
