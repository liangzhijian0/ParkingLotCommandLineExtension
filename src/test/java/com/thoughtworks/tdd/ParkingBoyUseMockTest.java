package com.thoughtworks.tdd;

import model.Car;
import model.ParkingBoy;
import model.ParkingLot;
import model.Receipt;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

public class ParkingBoyUseMockTest {

    @Test
    public void should_park_successfully_when_park_one_car(){
        Car car = new Car("a1");

        ParkingLot parkingLot = mock(ParkingLot.class);

        List<ParkingLot> parkingLotList = Arrays.asList(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        parkingBoy.park(car);

        verify(parkingLot).park(car);
    }

    @Test
    public void should_unPark_successfully_when_unPark_one_car(){
        Car car = new Car("a1");
        Receipt receipt = new Receipt("111");

        ParkingLot parkingLot = mock(ParkingLot.class);
        when(parkingLot.park(car)).thenReturn(receipt);
        when(parkingLot.unPark(receipt)).thenReturn(car);

        List<ParkingLot> parkingLotList = Arrays.asList(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        parkingBoy.park(car);

        assertThat(parkingBoy.unPark(receipt), is(car));
        verify(parkingLot).unPark(receipt);
    }

    @Test
    public void should_park_successfully_when_park_more_car_in_many_parkingLot(){
        Car car1 = new Car("a1");
        Car car2 = new Car("b1");
        Receipt receipt = new Receipt("212");

        ParkingLot parkingLot1 = mock(ParkingLot.class);
        ParkingLot parkingLot2 = mock(ParkingLot.class);
        when(parkingLot1.isFull()).thenReturn(false,true);

        List<ParkingLot> parkingLotList = Arrays.asList(parkingLot1,parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        parkingBoy.park(car1);
        parkingBoy.park(car2);

        verify(parkingLot1).park(car1);
        verify(parkingLot2).park(car2);
    }

    @Test
    public void should_unPark_successfully_when_unPark_more_car_in_more_parkingLot(){
        Car car1 = new Car("a1");
        Car car2 = new Car("b1");
        Receipt receipt1 = new Receipt("a121");
        Receipt receipt2 = new Receipt("a3211");

        ParkingLot parkingLot1 = mock(ParkingLot.class);
        ParkingLot parkingLot2 = mock(ParkingLot.class);
        when(parkingLot1.isFull()).thenReturn(false,true);
        when(parkingLot1.park(car1)).thenReturn(receipt1);
        when(parkingLot2.park(car2)).thenReturn(receipt2);
        when(parkingLot1.unPark(receipt1)).thenReturn(car1);
        when(parkingLot2.unPark(receipt2)).thenReturn(car2);

        List<ParkingLot> parkingLotList = Arrays.asList(parkingLot1,parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        parkingBoy.park(car1);
        parkingBoy.park(car2);

        assertThat(parkingBoy.unPark(receipt1), is(car1));
        verify(parkingLot1).unPark(receipt1);

        assertThat(parkingBoy.unPark(receipt2), is(car2));
        verify(parkingLot2).unPark(receipt2);
    }

    @Test
    public void should_park_one_by_one_in_parkingLots(){
        Car car1 = new Car("a1");
        Car car2 = new Car("b1");

        ParkingLot parkingLot1 = mock(ParkingLot.class);
        ParkingLot parkingLot2 = mock(ParkingLot.class);
        when(parkingLot1.isFull()).thenReturn(false,true);
        when(parkingLot2.isFull()).thenReturn(false);

        List<ParkingLot> parkingLotList = Arrays.asList(parkingLot1,parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        parkingBoy.park(car1);
        parkingBoy.park(car2);

        verify(parkingLot1).park(car1);
        verify(parkingLot2).park(car2);
    }

//
    @Test
    public void should_unpark_fail_when_given_wrong_receipt(){
        Car car1 = new Car("a1");
        Car car2 = new Car("b1");
        Receipt receipt1 = new Receipt("a21121");
        Receipt receipt2 = new Receipt("a331");

        ParkingLot parkingLot1 = mock(ParkingLot.class);
        ParkingLot parkingLot2 = mock(ParkingLot.class);
        when(parkingLot1.isFull()).thenReturn(false,true);
        when(parkingLot1.park(car1)).thenReturn(receipt1);
        when(parkingLot2.park(car2)).thenReturn(receipt2);
        when(parkingLot1.unPark(receipt1)).thenReturn(car1);
        when(parkingLot2.unPark(receipt2)).thenReturn(car2);

        List<ParkingLot> parkingLotList = Arrays.asList(parkingLot1,parkingLot2);
        ParkingBoy ParkingBoy = new ParkingBoy(parkingLotList);

        ParkingBoy.park(car1);
        ParkingBoy.park(car2);
        assertThat(parkingLot1.unPark(receipt2), not(car1));
    }
}
