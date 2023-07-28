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
public class UserPojo {

    public String getSifc() {
        return sifc;
    }

    public void setSifc(String sifc) {
        this.sifc = sifc;
    }

    public String getAcc_name() {
        return acc_name;
    }

    public void setAcc_name(String acc_name) {
        this.acc_name = acc_name;
    }

    public double getIni_deposit() {
        return ini_deposit;
    }

    public void setIni_deposit(double ini_deposit) {
        this.ini_deposit = ini_deposit;
    }
    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
     public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    private String sifc;
    private String acc_name;
    private double ini_deposit;
    private String pin;
    private String email;
}
