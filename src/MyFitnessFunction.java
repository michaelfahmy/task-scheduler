import net.sourceforge.jswarm_pso.FitnessFunction;
import net.sourceforge.jswarm_pso.Particle;

public class MyFitnessFunction extends FitnessFunction {
    private double[][] costMatrix;

    MyFitnessFunction() {
        initCostMatrix();
        this.setMaximize(false);
    }

    @Override
    public double evaluate(double[] position) {
        return 0;
    }

    // returning the time when the last task has been executed.
    @Override
    public double evaluate(Particle particle) {
        MyParticle p = (MyParticle) particle;
        double makespan = 0;
        double[] dcWorkingTime = new double[Constants.NO_OF_DATA_CENTERS];

        for (int i = 0; i < Constants.NO_OF_TASKS; i++) {
            int dcId = p.getDataCenterIds()[i];
            dcWorkingTime[dcId] += costMatrix[i][dcId];
            makespan = Math.max(makespan, dcWorkingTime[dcId]);
        }
        p.setFitness(makespan, isMaximize());
        return makespan;
    }

    private void initCostMatrix() {
        costMatrix = new double[Constants.NO_OF_TASKS][Constants.NO_OF_DATA_CENTERS];
        for (int i = 0; i < Constants.NO_OF_TASKS; i++)
            for (int j = 0; j < Constants.NO_OF_DATA_CENTERS; j++)
                costMatrix[i][j] = Math.random() * 300 + 20;
    }
}