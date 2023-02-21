/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import sample.dao.AccountDAO;
import sample.dao.CategoryDAO;
import sample.dao.DateDAO;
import sample.dao.OrderDAO;
import sample.dao.PlantDAO;

/**
 *
 * @author kietl
 */
public class testConnection {
    public static void main(String[] args) throws Exception {
        ArrayList<Category> list = CategoryDAO.getCategory(3);
        System.out.println(list.size());
        
    }
}

/*try {
            System.out.print("Test getAccount: ");
            Account acc = AccountDAO.getAccount("test@gmail.com", "test");
            if (acc != null) {
                if (acc.getRole() ==1) {
                    System.out.println("I am an admin");
                } else {
                    
                    System.out.println("I am a user");
                }
            } else {
                System.out.println("Login fail");
            }
            //1
            System.out.println("Test getAccounts: ");
            ArrayList<Account> list = new ArrayList<Account>();
            list = AccountDAO.getAccounts();
            for (Account account : list) {
                System.out.println(account.toString());
            }
            //2
            System.out.print("Test updateAccountStatus: ");
            boolean result = AccountDAO.updateAccountStatus("test@gmail.com", 1);
            System.out.println(result);                
            //3
            System.out.print("Test update account: ");
            result = AccountDAO.updateAccount("test@gmail.com", "test123", "123321");
            System.out.println(result);
            
            System.out.print("Test add account: ");
            result = AccountDAO.insertAccount("kiet1@gmail.com", "123654", "Kiet", "12354", 1, 0);
            System.out.println(result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }*/