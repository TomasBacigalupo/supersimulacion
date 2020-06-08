package ar.edu.itba.sds.interfaces;
import java.util.List;

public interface Times {

    // Get all picking times for every item in shopping list all at once or one at a time (a charlar?)
    List<Double> getPickingTimes(int itemCount);
    // time to pick item
    Double getPickingTime();
    // time taken to check items and pay, depends on the amount of items in shopping cart
    Double getCashierWaitingTime(int itemCount);

}
