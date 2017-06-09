public class MyFF_TotalCost extends MyFitnessFunction {

    MyFF_TotalCost() {
        super();
    }

    /*
     * fitness function = sum ( total_cost(Ri) )
     * this fitness function doesn't support load balancing
     */
    @Override
    public double evaluate(double[] position) {
        double totalCost = 0;

        for (int i = 0; i < Constants.NO_OF_TASKS; i++) {
            int dcId = (int) position[i];
            totalCost += super.costMatrix[i][dcId];
        }
        return totalCost;
    }
}
