import net.sourceforge.jswarm_pso.FitnessFunction;
import net.sourceforge.jswarm_pso.Particle;

public class MyFitnessFunction extends FitnessFunction {
    private double[][] costMatrix;

    public MyFitnessFunction() {
        costMatrix = new double[Constants.NO_OF_TASKS][Constants.NO_OF_DATA_CENTERS];
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
        double[] dcWorkingTime = new double[Constants.NO_OF_DATA_CENTERS];
        for(int i = 0; i < Constants.NO_OF_DATA_CENTERS; i++)
            dcWorkingTime[i] = 0;
        double totalTime = 0;
        for (int i = 0; i < Constants.NO_OF_TASKS; i++) {
            int dcId = p.getDataCenterIds()[i];
            dcWorkingTime[dcId] += costMatrix[i][dcId];
            totalTime = Math.max(totalTime, dcWorkingTime[dcId]);
        }
        return totalTime;
    }

    void setCostMatrix(double[][] cm) {
        for(int i = 0; i < Constants.NO_OF_TASKS; i++) {
            for(int j = 0; j < Constants.NO_OF_DATA_CENTERS; j++) {
                costMatrix[i][j] = Math.random() * 100;
            }
        }
    }
}