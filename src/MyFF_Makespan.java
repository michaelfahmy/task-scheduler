public class MyFF_Makespan extends MyFitnessFunction {

    MyFF_Makespan() {
        super();
    }

    /*
     * fitness function = Makespan(M)
     * this fitness function support load balancing
     */
    @Override
    public double evaluate(double[] position) {
        double makespan = 0;
        double[] dcWorkingTime = new double[Constants.NO_OF_DATA_CENTERS];

        for (int i = 0; i < Constants.NO_OF_TASKS; i++) {
            int dcId = (int) position[i];
            dcWorkingTime[dcId] += costMatrix[i][dcId];
            makespan = Math.max(makespan, dcWorkingTime[dcId]);
        }
        return makespan;
    }
}

