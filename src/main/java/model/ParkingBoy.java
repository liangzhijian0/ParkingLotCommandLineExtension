package model;

import Exception.ParkingLotFullException;
import Exception.ParkingLotNotEmptyException;
import Exception.ParkingLotNotExistException;
import Exception.WrongReceiptException;

import java.util.List;
import java.util.UUID;

public class ParkingBoy {
    private List<ParkingLot> parkingLotList;

    public List<ParkingLot> getParkingLotList() {
        return parkingLotList;
    }

    public ParkingBoy(List<ParkingLot> parkingLotList){
        this.parkingLotList = parkingLotList;
    }

    public Receipt park (Car car) throws ParkingLotFullException {
        for(int i=0;i<parkingLotList.size();i++){
            if(!parkingLotList.get(i).isFull()){
                return  parkingLotList.get(i).park(car);
            }
        }

        throw new ParkingLotFullException();
    }

    public Car unPark(Receipt receipt) {
        for(int i=0;i<parkingLotList.size();i++){
            Car car = parkingLotList.get(i).unPark(receipt);
            if(car != null){
                return car;
            }
        }
        throw new WrongReceiptException();
    }

    public boolean isFull(){
        for(int i=0;i<parkingLotList.size();i++){
            if(!parkingLotList.get(i).isFull()){
                return  false;
            }
        }
        return true;
    }

    public void addParkingLot(String name, int size) {
        ParkingLot parkingLot = new ParkingLot(UUID.randomUUID().toString(),name,size);
        this.getParkingLotList().add(parkingLot);
    }

    public boolean deleteParkingLotById(String id) {
        ParkingLot parkingLot = this.getParkingLotById(id);
        if (parkingLot != null) {
            if (parkingLot.getReceiptCar().size() == 0) {
                parkingLotList.remove(parkingLot);
                return true;
            }
            throw new ParkingLotNotEmptyException();
        }
        throw new ParkingLotNotExistException();
    }

    public ParkingLot getParkingLotById(String id) {
        for(ParkingLot parkingLot:parkingLotList){
            if(parkingLot.getId().equals(id))
                return parkingLot;
        }
        return null;
    }
}
