package model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ParkingLot {
    private String name;
    private String id;
    private int size;
    private Map<Receipt,Car> receiptCar = new HashMap<>();

    public ParkingLot(String id,String name,int size) {
        this.id = id;
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    public Map<Receipt, Car> getReceiptCar() {
        return receiptCar;
    }

    public Receipt park(Car car) {
        if(this.isFull()){
            throw new ParkingLotFullException();
        }
        Receipt receipt = new Receipt(UUID.randomUUID().toString());
        this.receiptCar.put(receipt,car);
        return receipt;
    }

    public Car unPark(Receipt receipt) {
        if(this.receiptCar.keySet().contains(receipt)){
            Car car = receiptCar.get(receipt);
            this.receiptCar.remove(receipt);
            return car;
        }
        return null;
    }

    public boolean isFull() {
        return receiptCar.size() == size;
    }

}
