package model;

import java.util.Objects;

public class Receipt {
    private String receiptId;

    public Receipt(String receiptId){
        this.receiptId = receiptId;
    }

    public String getReceiptId() {
        return receiptId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receipt receipt = (Receipt) o;
        return Objects.equals(receiptId, receipt.receiptId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(receiptId);
    }

}
