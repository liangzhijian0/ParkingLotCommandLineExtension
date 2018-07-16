package com.thoughtworks.tdd;

import model.Car;
import model.ParkingLot;
import model.ParkingLotFullException;
import model.Receipt;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class ParkingLotTest {
    @Test
    public void should_park_successfully_when_parkingLot_is_not_full(){
        Car car = new Car("a1");
        ParkingLot parkingLot = new ParkingLot(1);
        try {
            parkingLot.park(car);
        }catch(ParkingLotFullException exception){
            fail("it should park successfully");
        }
    }

    @Test
    public void should_park_fail_when_parkingLot_is_full(){
        ParkingLot parkingLot = new ParkingLot(0);

        try {
            parkingLot.park(new Car("a1"));
            fail("should park successfully");
        } catch (ParkingLotFullException e) {

        }
    }

    @Test
    public void should_get_specific_car_when_call_unPark_given_receipt_is_right(){
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car("a1");
        Receipt receipt = parkingLot.park(car);
        assertThat(parkingLot.unPark(receipt), is(car));
    }

    @Test
    public void should_not_get_specific_car_when_call_unPark_given_receipt_is_wrong(){
        ParkingLot parkingLot = new ParkingLot(1);

        Car theCar = new Car("a1");
        Receipt receipt = parkingLot.park(theCar);

        Receipt anotherReceipt = new Receipt("a121");

        assertThat(parkingLot.unPark(anotherReceipt), not(theCar));
    }

    @Test
    public void should_be_true_when_call_isFull_given_parking_lot_is_full(){
        ParkingLot parkingLot = new ParkingLot(0);

        assertThat(parkingLot.isFull(), is(true));
    }

    @Test
    public void should_be_false_when_call_isFull_given_a_full_parking_lot_take_out_a_car(){
        ParkingLot parkingLot = new ParkingLot(1);

        Car theCar = new Car("a1");
        Receipt receipt = parkingLot.park(theCar);
        parkingLot.unPark(receipt);

        assertThat(parkingLot.isFull(), is(false));
    }

    @Test
    public void should_park_successfullly_when_call_park_again_given_a_full_parking_lot_take_out_a_car(){
        ParkingLot parkingLot = new ParkingLot(1);

        Car theCar = new Car("a1");
        Receipt receipt = parkingLot.park(theCar);
        parkingLot.unPark(receipt);

        try {
            parkingLot.park(new Car("a1"));
        } catch (ParkingLotFullException exception) {
            fail("should park successfully");
        }
    }

}
