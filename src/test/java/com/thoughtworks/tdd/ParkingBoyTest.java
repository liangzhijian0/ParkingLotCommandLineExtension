package com.thoughtworks.tdd;

import model.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class ParkingBoyTest {
    @Test
    public void should_park_successfully_when_park_one_car(){
        Car car = new Car("a1");
        ParkingLot parkingLot1 = new ParkingLot(1);
        List<ParkingLot> parkingLotList = Arrays.asList(parkingLot1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        try {
            parkingBoy.park(car);
        }catch(ParkingLotFullException exception){
            fail("it should park successfully");
        }
    }


    @Test
    public void should_unPark_successfully_when_unPark_one_car(){
        Car car = new Car("a1");
        ParkingLot parkingLot = new ParkingLot(1);
        List<ParkingLot> parkingLotList = Arrays.asList(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        Receipt receipt = parkingBoy.park(car);

        assertThat(parkingBoy.unPark(receipt), is(car));
    }

    @Test
    public void should_park_successfully_when_park_more_car_in_many_parkingLot(){
        Car car1 = new Car("a1");
        Car car2 = new Car("b1");
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        List<ParkingLot> parkingLotList = Arrays.asList(parkingLot1,parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        try {
            parkingBoy.park(car1);
            parkingBoy.park(car2);
        }catch(ParkingLotFullException exception){
            fail("it should park successfully");
        }
    }

    @Test
    public void should_unPark_successfully_when_unPark_more_car_in_more_parkingLot(){
        Car car1 = new Car("a1");
        Car car2 = new Car("b1");
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        List<ParkingLot> parkingLotList = Arrays.asList(parkingLot1,parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        Receipt receipt1 = parkingBoy.park(car1);
        Receipt receipt2 = parkingBoy.park(car2);

        assertThat(parkingBoy.unPark(receipt1), is(car1));
        assertThat(parkingBoy.unPark(receipt2), is(car2));
    }

    @Test
    public void should_park_one_by_one_in_parkingLots(){
        Car car1 = new Car("a1");
        Car car2 = new Car("b1");
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        List<ParkingLot> parkingLotList = Arrays.asList(parkingLot1,parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        Receipt receipt1 = parkingBoy.park(car1);
        Receipt receipt2 = parkingBoy.park(car2);
        assertThat(parkingBoy.unPark(receipt1), is(car1));
        assertThat(parkingBoy.unPark(receipt2), is(car2));
    }


    @Test
    public void should_unpark_fail_when_given_wrong_receipt(){
        Car car1 = new Car("a1");
        Car car2 = new Car("b1");
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        List<ParkingLot> parkingLotList = Arrays.asList(parkingLot1,parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        Receipt receipt1 = parkingBoy.park(car1);
        Receipt receipt2 = parkingBoy.park(car2);

        assertThat(parkingBoy.unPark(receipt2), not(car1));
    }

    @Test
    public void should_unpark_fail_when_receipt_is_not_exist(){
        Car car1 = new Car("a1");
        ParkingLot parkingLot1 = new ParkingLot(1);
        List<ParkingLot> parkingLotList = Arrays.asList(parkingLot1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        Receipt receipt1 = parkingBoy.park(car1);
        Receipt receipt2 = new Receipt("121");

        try{
            parkingBoy.unPark(receipt2);
        }catch (ParkingLotFullException e){
            System.out.print("receipt is not exist");
        }
    }

}
