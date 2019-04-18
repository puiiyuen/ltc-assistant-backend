/**
 * Utility operation status code
 *
 * @author Peiyuan
 * 2019-02-17
 */

package com.minipgm.utils;

public class operationStatus {

    public static final int SUCCESSFUL = 100;
    public static final int FAILED = 200;
    public static final int TIMEOUT = 210;
    public static final int BILL = 220;// check the balance of account
    public static final int IS_EXIST = 300;
    public static final int NOT_EXIST = 400;
    public static final int INACTIVATED = 500;
    public static final int SERVERERROR = -999;

}
