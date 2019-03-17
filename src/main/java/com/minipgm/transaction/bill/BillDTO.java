/**
 * Bill DTO
 *
 * @author Peiyuan
 * 2019-03-15
 */

package com.minipgm.transaction.bill;

import java.sql.Timestamp;

public class BillDTO extends Bill{
    private String name;
    private int numOfBed;

    public BillDTO(){
        super();
    }

    public BillDTO(String name, int numOfBed) {
        this.name = name;
        this.numOfBed = numOfBed;
    }

    public BillDTO(int resId, String item, double amount, Timestamp recordDate, String name, int numOfBed) {
        super(resId, item, amount, recordDate);
        this.name = name;
        this.numOfBed = numOfBed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfBed() {
        return numOfBed;
    }

    public void setNumOfBed(int numOfBed) {
        this.numOfBed = numOfBed;
    }
}
