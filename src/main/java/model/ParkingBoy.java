package model;

import java.util.List;

public class ParkingBoy {
    private List<ParkingLot> parkingLotList;

    public List<ParkingLot> getParkingLotList() {
        return parkingLotList;
    }

    public ParkingBoy(List<ParkingLot> parkingLotList){
        this.parkingLotList = parkingLotList;
    }

    public Receipt park (Car car) throws ParkingLotFullException{
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
        throw new ParkingLotFullException();
    }

    public boolean isFull(){
        for(int i=0;i<parkingLotList.size();i++){
            if(!parkingLotList.get(i).isFull()){
                return  false;
            }
        }
        return true;
    }
}
