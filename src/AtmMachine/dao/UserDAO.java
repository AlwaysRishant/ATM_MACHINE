/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AtmMachine.dao;
import AtmMachine.dbutil.dbConnection;
import AtmMachine.pojo.CurrentUser;
import AtmMachine.pojo.UserPojo;
import AtmMachine.pojo.miniStatementPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class UserDAO {
    public static int userInfo(UserPojo pj)throws SQLException
    {
        Connection conn=dbConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("insert into accholder values(?,?,?,?,?)");
        ps.setString(1,pj.getSifc());
        ps.setString(2,pj.getAcc_name().toUpperCase());
        ps.setDouble(3,pj.getIni_deposit());
        ps.setString(4,pj.getEmail().toUpperCase());
        ps.setString(5, pj.getPin());
        int update=ps.executeUpdate();
        if (update==1) {
            return 1;
        }
        else{
            return -1;
        }
        
    }
    public static String generateSifc()throws SQLException{
        Connection conn=dbConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select max(sifc) from accholder");
        rs.next();
        String sifc=rs.getString(1);
        String sifccode="SATI201000";
        if(sifc!=null)
        {  
            int  code=Integer.parseInt(sifc.substring(4,10));
            int total=code+1;
            sifccode="SATI"+total;
        }
        else
        {  
            int  code=Integer.parseInt(sifccode.substring(4,10));
            int total=code+1;
            sifccode="SATI"+total;
        }
        return sifccode;
    }
    public static String getNameByPin(String sifc,String pin)throws SQLException
    {
        Connection conn=dbConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select name from accholder where sifc=? and pin=?");
        ps.setString(1, sifc.toUpperCase());
        ps.setString(2, pin);
        ResultSet rs=ps.executeQuery();
        if (rs.next()) {
            String name=rs.getString(1);
            return name;
        }
        return null;
    }
    public static double MoneyCheck(String pin)throws SQLException
    {
        Connection conn=dbConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select deposit from accholder where pin=?");
        ps.setString(1, pin);
        ResultSet rs=ps.executeQuery();
        rs.next();
        return rs.getDouble(1);
    }
    public static boolean WithdrawUpdate(double amount,String pin)throws SQLException
    {
        Connection conn=dbConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("update accholder set deposit=? where pin=?");
        ps.setDouble(1, amount);
        ps.setString(2, pin);
        return 1==ps.executeUpdate();
    }
    public static boolean ChangePin(String pin,String oldpin)throws SQLException
    {
        Connection conn=dbConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("update accholder set pin=? where pin=?");
        ps.setString(1, pin);
        ps.setString(2, oldpin);
        return 1==ps.executeUpdate();
    }
    public static boolean moneyDeposit(String money,String pin)throws SQLException
    {
        Connection conn=dbConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("update accholder set deposit=? where pin=?");
        ps.setString(1, money);
        ps.setString(2, pin);
        return 1==ps.executeUpdate();
    }
    public static void setTimeDateWithdraw(miniStatementPojo ms)throws SQLException
    {
        Connection conn=dbConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("insert into ministatement values(?,?,?,?,?)");
        ps.setString(1,ms.getSifcCode());
        ps.setDouble(2, ms.getMoneyDeducted());
        ps.setString(3, ms.getDate_transact());
        ps.setString(4, ms.getTime_transact());
        ps.setDouble(5, ms.getMoneyDeposit());
        ps.executeUpdate();
    }
     public static void setTimeDateDeposit(miniStatementPojo ms)throws SQLException
    {
        Connection conn=dbConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("insert into ministatement values(?,?,?,?,?)");
        ps.setString(1,ms.getSifcCode());
        ps.setDouble(2, ms.getMoneyDeducted());
        ps.setString(3, ms.getDate_transact());
        ps.setString(4, ms.getTime_transact());
        ps.setDouble(5, ms.getMoneyDeposit());
        ps.executeUpdate();
    }
    public static List<miniStatementPojo> gettransHistoryWithdraw(String sifc)throws SQLException{
        Connection conn=dbConnection.getConnection();
         PreparedStatement ps=conn.prepareStatement("select sifc,moneydeducted,date_transact,time_transact from ministatement where sifc=? and moneydeducted!=0");
         ps.setString(1, sifc);
         ResultSet rs=ps.executeQuery();
         List<miniStatementPojo> detail=new ArrayList<>();
         while(rs.next())
         {
             miniStatementPojo mst=new miniStatementPojo();
             mst.setSifcCode(rs.getString(1));
             mst.setMoneyDeducted(rs.getDouble(2));
             mst.setDate_transact(rs.getString(3));
             mst.setTime_transact(rs.getString(4));
             detail.add(mst);
         }
         return detail;
    }
    public static List<miniStatementPojo> gettransHistoryDeposit(String sifc)throws SQLException{
         Connection conn=dbConnection.getConnection();
         PreparedStatement ps=conn.prepareStatement("select sifc,moneydeposit,date_transact,time_transact from ministatement where sifc=? and moneydeposit!=0");
         ps.setString(1, sifc);
         ResultSet rs=ps.executeQuery();
         List<miniStatementPojo> detail=new ArrayList<>();
         while(rs.next())
         {
             miniStatementPojo mst=new miniStatementPojo();
             mst.setSifcCode(rs.getString(1));
             mst.setMoneyDeposit(rs.getDouble(2));
             mst.setDate_transact(rs.getString(3));
             mst.setTime_transact(rs.getString(4));
             detail.add(mst);
         }
         return detail;
    }
    public static boolean deleteWithdrawHistory(String sifc)throws SQLException
    {
         Connection conn=dbConnection.getConnection();
         PreparedStatement ps=conn.prepareStatement("delete from ministatement where sifc=? and moneydeposit=0");
         ps.setString(1, sifc);
         return ps.executeUpdate()==1;
    }
    public static boolean deleteDepositHistory(String sifc)throws SQLException
    {
         Connection conn=dbConnection.getConnection();
         PreparedStatement ps=conn.prepareStatement("delete from ministatement where sifc=? and moneydeducted=0");
         ps.setString(1, sifc);
         return ps.executeUpdate()==1;
    }
    public static boolean deleteAccountfromAcc(String sifc)throws SQLException
    {
         Connection conn=dbConnection.getConnection();
         PreparedStatement ps1;
         ps1=conn.prepareStatement("delete from accholder where sifc=?");
         ps1.setString(1, sifc);
         return ps1.executeUpdate()==1;
    }
    public static boolean deleteAccountfromMini(String sifc)throws SQLException
    {
         Connection conn=dbConnection.getConnection();
         PreparedStatement ps1;
         ps1=conn.prepareStatement("delete from ministatement where sifc=?");
         ps1.setString(1, sifc);
         return ps1.executeUpdate()==1;
    }
     public static boolean UpdatePin(String pin,String sifc)throws SQLException
     {
         Connection conn=dbConnection.getConnection();
         PreparedStatement ps1;
         ps1=conn.prepareStatement("update accholder set pin=? where sifc=?");
         ps1.setString(1, pin);
         ps1.setString(2, sifc);
         return ps1.executeUpdate()==1;
     }
     public static boolean ValidateSifc(String sifc,String emailid)throws SQLException
     {
         Connection conn=dbConnection.getConnection();
         PreparedStatement ps1;
         ps1=conn.prepareStatement("select emailid from accholder where sifc=?");
         ps1.setString(1, sifc);
         ResultSet rs=ps1.executeQuery();
         rs.next();
         if (rs.getString(1).equals(emailid)) {
             return true;
         }
        return false;
     }
     public static boolean ValidateSifc(String sifc)throws SQLException
     {
         Connection conn=dbConnection.getConnection();
         PreparedStatement ps1;
         ps1=conn.prepareStatement("select sifc from accholder where sifc=?");
         ps1.setString(1, sifc);
         ResultSet rs=ps1.executeQuery();
         while(rs.next())
         {
             if (rs.getString(1).equals(sifc)) {
                 return true;
             }
         }
         return false;
     }
      public static boolean setMiliSecInAttempts(String ms,String sifc)throws SQLException{
           Connection conn=dbConnection.getConnection();
           PreparedStatement ps1=conn.prepareStatement("insert into attempts values(?,?)");
           ps1.setString(1, ms);
           ps1.setString(2, sifc);
           return 1==ps1.executeUpdate();
      }
      public static String getTime(String sifc)throws SQLException{
           Connection conn=dbConnection.getConnection();
           PreparedStatement ps1=conn.prepareStatement("select lastattempt from attempts where sifc=?");
           ps1.setString(1, sifc);
           ResultSet rs=ps1.executeQuery();
           rs.next();
           String time=rs.getString(1);
           if (time.length()>1) {
              return time;
           }
           return "0";
      }
       public static boolean delAttempt(String sifc)throws SQLException{
           Connection conn=dbConnection.getConnection();
           PreparedStatement ps1=conn.prepareStatement("delete from attempts where sifc=?");
           ps1.setString(1, sifc);
           return ps1.executeUpdate()==1;
       }
       public static boolean noRecordInAttempts(String sifc)throws SQLException
       {
         Connection conn=dbConnection.getConnection();
         PreparedStatement ps1;
         ps1=conn.prepareStatement("select sifc from attempts where sifc=?");
         ps1.setString(1, sifc);
         ResultSet rs=ps1.executeQuery();
         while(rs.next())
         {
             if (rs.getString(1).equals(sifc)) {
                 return true;
             }
         }
         return false;
       }
       public static boolean checkPin(String pin)throws SQLException
       {
         Connection conn=dbConnection.getConnection();
         PreparedStatement ps1;
         ps1=conn.prepareStatement("select pin from accholder where pin=?");
         ps1.setString(1,pin);
         ResultSet rs=ps1.executeQuery();
         while(rs.next())
         {
             if (rs.getString(1).equals(pin)) {
                 return false;
             }
         }
         return true;
       }
       public static void setDetailInWithdraw(String sifc,int count,long ms,long ms1)throws SQLException
       {
           Connection conn=dbConnection.getConnection();
           PreparedStatement ps1=conn.prepareStatement("insert into limitwithdraw values(?,?,?,?)");
           ps1.setString(1, sifc);
           ps1.setInt(2, count);
           ps1.setString(3,String.valueOf(ms));
           ps1.setString(4,String.valueOf(ms1));
           ps1.executeUpdate();
      }
      public static int getCount(String sifc)throws SQLException
      {
         Connection conn=dbConnection.getConnection();
         PreparedStatement ps1;
         ps1=conn.prepareStatement("select count from limitwithdraw where sifc=?");
         ps1.setString(1, sifc);
         ResultSet rs=ps1.executeQuery();
          if (rs.next()) {
              return rs.getInt(1);
          }
          else{
              return -1;   
          }
      }
      public static long getMiliseconds(String sifc)throws SQLException
      {
         Connection conn=dbConnection.getConnection();
         PreparedStatement ps1;
         ps1=conn.prepareStatement("select lastwithdraw from limitwithdraw where sifc=?");
         ps1.setString(1, sifc);
         ResultSet rs=ps1.executeQuery();
          if (rs.next()) {
              return rs.getLong(1);
          }
          return 0;
      }
      public static long getMilisecondsFirst(String sifc)throws SQLException
      {
         Connection conn=dbConnection.getConnection();
         PreparedStatement ps1;
         ps1=conn.prepareStatement("select firstwithdraw from limitwithdraw where sifc=?");
         ps1.setString(1, sifc);
         ResultSet rs=ps1.executeQuery();
          if (rs.next()) {
              return rs.getLong(1);
          }
          return 0;
      }
      public static void setCount(String sifc,int count,long ms)throws SQLException{
          Connection conn=dbConnection.getConnection();
           PreparedStatement ps1=conn.prepareStatement("update limitwithdraw set count=?,lastwithdraw=? where sifc=?");
           ps1.setInt(1, count);
           ps1.setString(2, String.valueOf(ms));
           ps1.setString(3, sifc);
           ps1.executeUpdate();
      }
       public static void delAlllimit(String sifc)throws SQLException
       {
           Connection conn=dbConnection.getConnection();
           PreparedStatement ps1=conn.prepareStatement("delete from limitwithdraw where sifc=?");
           ps1.setString(1,sifc);
           ps1.executeUpdate();
       }
}