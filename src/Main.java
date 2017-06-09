import net.sourceforge.jswarm_pso.Swarm;

public class Main {
    private static double timeMatrix[][] = new double[Constants.NO_OF_TASKS][Constants.NO_OF_DATA_CENTERS];
    private static MyParticle particles[] = new MyParticle[Constants.POPULATION_SIZE];
    private static MyFitnessFunction ff = new MyFitnessFunction();

    public static void main(String[] args) {
        genMatrix();
        initParticles();
//        for(int i = 0; i < Constants.POPULATION_SIZE; i++)
//            System.out.println(particles[i].toString());
        Swarm swarm = new Swarm(Constants.POPULATION_SIZE, new MyParticle(), new MyFitnessFunction());

        swarm.setMinPosition(0);
        swarm.setMaxPosition(1);
        swarm.setParticles(particles);
        swarm.setFitnessFunction(ff);

        for (int i = 0; i < 100; i++) {
            swarm.evolve();
            if(i < 10)
                System.out.println(swarm.getBestParticle().toString());
        }

        MyParticle bestParticle = (MyParticle) swarm.getBestParticle();
        System.out.println(bestParticle.toString());
    }

    private static void genMatrix() {
        for (int i = 0; i < Constants.NO_OF_TASKS; i++)
            for (int j = 0; j < Constants.NO_OF_DATA_CENTERS; j++)
                timeMatrix[i][j] = Math.random() * 300 + 20;
        ff.setCostMatrix(timeMatrix);

    }

    private static void initParticles() {
        for (int i = 0; i < Constants.POPULATION_SIZE; ++i)
            particles[i] = new MyParticle();
    }
}