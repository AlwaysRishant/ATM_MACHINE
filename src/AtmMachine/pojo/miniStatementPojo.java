/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AtmMachine.pojo;

/**
 *
 * @author HP
 */
public class miniStatementPojo {

    public String getSifcCode() {
        return sifcCode;
    }

    public void setSifcCode(String sifcCode) {
        this.sifcCode = sifcCode;
    }

    public double getMoneyDeducted() {
        return moneyDeducted;
    }

    public void setMoneyDeducted(double moneyDeducted) {
        this.moneyDeducted = moneyDeducted;
    }

    public String getDate_transact() {
        return date_transact;
    }

    public void setDate_transact(String date_transact) {
        this.date_transact = date_transact;
    }

    public String getTime_transact() {
        return time_transact;
    }

    public void setTime_transact(String time_transact) {
        this.time_transact = time_transact;
    }
    public double getMoneyDeposit() {
        return moneyDeposit;
    }

    public void setMoneyDeposit(double moneyDeposit) {
        this.moneyDeposit = moneyDeposit;
    }
    private String sifcCode;
    private double moneyDeducted;
    private double moneyDeposit;
    private String date_transact;
    private String time_transact;
}
