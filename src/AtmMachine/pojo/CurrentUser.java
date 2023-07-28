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
public class CurrentUser {

    public static String getSifc() {
        return sifc;
    }

    public static void setSifc(String sifc) {
        CurrentUser.sifc = sifc;
    }

    public static String getPin() {
        return pin;
    }

    public static void setPin(String pin) {
        CurrentUser.pin = pin;
    }

    public static double getDeposit() {
        return deposit;
    }

    public static void setDeposit(double deposit) {
        CurrentUser.deposit = deposit;
    }
    private static String sifc;
    private static String pin;
    private static double deposit;
}
