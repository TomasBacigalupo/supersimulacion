package ar.edu.itba.sds.caja;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ar.edu.itba.sds.interfaces.Times;

// change to module:services when state machine dependency error is fixed
public class TimesService implements Times {

    private static final Random RANDOM = new Random();

    private static final double N_MEAN = 75;
    private static final double STD = 15;

    // according to research: shopping carts with less than 15 items have mean 3 min (180s)
    // while bigger than 15 have a mean of 5.5 min (330s)
    private static final double LAMBDA_SMALL = 1/180.0;
    private static final double LAMBDA_BIG = 1/330.0;

    // Normal distribution between 60 and 90 seconds
    @Override
    public List<Double> getPickingTimes(int itemCount) {
        List<Double> times = new ArrayList<>();
        for(int i = 0 ; i < itemCount ; i++) {
            times.add(getPickingTime());
        }
        return times;
    }

    // Normal distribution between 60 and 90 seconds
    @Override
    public Double getPickingTime() {
        return  RANDOM.nextGaussian() * STD + N_MEAN;
    }

    // Exponential distribution
    @Override
    public Double getCashierWaitingTime(int itemCount) {
        if(itemCount > 15) {
            return Math.log(1 - RANDOM.nextDouble()) / (-1 * LAMBDA_BIG);
        }
        return Math.log(1 - RANDOM.nextDouble()) / (-1 * LAMBDA_SMALL);

    }

}