import net.sourceforge.jswarm_pso.FitnessFunction;

import java.io.*;

public class MyFitnessFunction extends FitnessFunction {
    protected double[][] costMatrix;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;

    private File file = new File("out.txt");

    MyFitnessFunction() {
        super(false);
        try {
            if (file.exists()) {
                readCostMatrix();
            } else {
                initCostMatrix();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * fitness function = alpha * totalCost(M) + (1 - alpha) * Makespan(M)
     * where 0 < alpha < 1
     */
    @Override
    public double evaluate(double[] position) {
        double alpha = 0.3;
        double totalCost = new MyFF_TotalCost().evaluate(position);
        double makespan = new MyFF_Makespan().evaluate(position);

        return alpha * totalCost + (1-alpha) * makespan;
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