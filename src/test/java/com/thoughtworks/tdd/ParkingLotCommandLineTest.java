package com.thoughtworks.tdd;

import control.Control;
import model.ParkingBoy;
import org.junit.Test;
import view.Input;
import view.Output;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ParkingLotCommandLineTest {
    @Test
    public void should_return_correct_parkingLotList_size_when_initParkingBoy(){
        Control control = new Control(new Input(),new Output());
        ParkingBoy parkingBoy = control.initParkingBoy(4);
        assertThat(parkingBoy.getParkingLotList().size(),is(4));;
    }


    @Test
    public void should_print_the_choice_to_user_at_first(){
        Output output = new Output();
        output.showExecutionMessage();
        assertThat(output.getString(),is("1. 停车\n" + "2. 取车 \n" + "请输入您要进行的操作："));;
    }


    @Test
    public void should_fail_when_the_choice_is_not_1_or_2(){
        Input input = mock(Input.class);
        when(input.inputOperationChoice()).thenReturn(3);
        assertThat(output.printAtFirst(choiceStr),is(choiceStr));;
    }
}
