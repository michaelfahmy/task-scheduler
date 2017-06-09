import net.sourceforge.jswarm_pso.Particle;


/* the solution is how the tasks are mapped to the data centers.
* so the solution is represented as a 1D array considering the
* selected data center for a specific task.
* SO the ith element is the selected DC for the ith task.
* */
public class MyParticle extends Particle {
    private int[] dataCenterIds;

    MyParticle() {
        super(Constants.NO_OF_TASKS);

        double[] position = new double[Constants.NO_OF_TASKS];
        double[] velocity = new double[Constants.NO_OF_TASKS];

        dataCenterIds = new int[Constants.NO_OF_TASKS];
        for (int i = 0; i < Constants.NO_OF_TASKS; i++) {
            dataCenterIds[i] = (int) (Math.random() * Constants.NO_OF_DATA_CENTERS);
            position[i] = (double) dataCenterIds[i];
            velocity[i] = Math.random();
        }

        setPosition(position);
        setVelocity(velocity);
    }

    public int[] getDataCenterIds() {
        return dataCenterIds;
    }

    public void setDataCenterIds(int[] dcis) {
        System.arraycopy(dcis, 0, dataCenterIds, 0, Constants.NO_OF_TASKS);
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < Constants.NO_OF_DATA_CENTERS; i++) {
            String tasks = "";
            for (int j = 0; j < Constants.NO_OF_TASKS; j++) {
                if (i == dataCenterIds[j])
                    tasks += (tasks.isEmpty() ? "" : " ") + j;
            }
            if (tasks.isEmpty()) output += "There is no tasks associated to Data Center " + i + "\n";
            else output += "The tasks associated to Data Center " + i + " is " + tasks + "\n";
        }
        return output;
    }
}