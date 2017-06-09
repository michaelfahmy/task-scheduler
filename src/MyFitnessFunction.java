import net.sourceforge.jswarm_pso.FitnessFunction;
import net.sourceforge.jswarm_pso.Particle;

import java.io.*;

public class MyFitnessFunction extends FitnessFunction {
    private double[][] costMatrix;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;

    private File file = new File("out.txt");

    MyFitnessFunction() throws IOException {
        if (file.exists()) {
            readCostMatrix();
        } else {
            initCostMatrix();
        }
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
            int dcId = (int) p.getPosition()[i];
            dcWorkingTime[dcId] += costMatrix[i][dcId];
            makespan = Math.max(makespan, dcWorkingTime[dcId]);
        }
        p.setFitness(makespan, isMaximize());
        return makespan;
    }

    private void initCostMatrix() throws IOException {
        bufferedWriter = new BufferedWriter(new FileWriter(file));
        costMatrix = new double[Constants.NO_OF_TASKS][Constants.NO_OF_DATA_CENTERS];

        for (int i = 0; i < Constants.NO_OF_TASKS; i++) {
            for (int j = 0; j < Constants.NO_OF_DATA_CENTERS; j++) {
                costMatrix[i][j] = Math.random() * 600 + 20;
                bufferedWriter.write(String.valueOf(costMatrix[i][j]) + ' ');
            }
            bufferedWriter.write('\n');
        }
        bufferedWriter.close();
    }


    private void readCostMatrix() throws IOException {
        bufferedReader = new BufferedReader(new FileReader(file));
        costMatrix = new double[Constants.NO_OF_TASKS][Constants.NO_OF_DATA_CENTERS];

        int i = 0, j = 0;
        do {
            String line = bufferedReader.readLine();
            for (String num : line.split(" ")) {
                costMatrix[i][j++] = new Double(num);
            }
            ++i;
            j = 0;
        } while (bufferedReader.ready());
    }
}