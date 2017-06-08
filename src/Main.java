import net.sourceforge.jswarm_pso.Particle;
import net.sourceforge.jswarm_pso.ParticleUpdate;
import net.sourceforge.jswarm_pso.ParticleUpdateSimple;
import net.sourceforge.jswarm_pso.Swarm;

public class Main {

    private static double timeMatrix[][] = new double[Constants.NO_OF_TASKS][Constants.NO_OF_DATACENTERS];
    private static MyParticle particles[] = new MyParticle[Constants.POPULATION_SIZE];

    public static void main(String[] args) {
        genMatrix();
        initParticles();

        Swarm swarm = new Swarm(Constants.POPULATION_SIZE, new MyParticle(), new MyFitnessFunction());

        swarm.setMinPosition(0);
        swarm.setMaxPosition(1);
        for (int i = 0; i < 100; i++) swarm.evolve();

        MyParticle bestParticle = (MyParticle) swarm.getBestParticle();
        for (int i = 0; i < Constants.NO_OF_DATACENTERS; ++i)
            for (int j = 0; j < bestParticle.datacenters.length; ++j)
                System.out.println(bestParticle.datacenters[i].get(j));
    }

    private static void genMatrix() {
        for (int i = 0; i < Constants.NO_OF_TASKS; i++)
            for (int j = 0; j < Constants.NO_OF_DATACENTERS; j++)
                timeMatrix[i][j] = Math.random() * 300 + 20;
    }

    private static void initParticles() {
        for (int i = 0; i < Constants.POPULATION_SIZE; ++i)
            particles[i] = new MyParticle();
        for (int i = 0; i < Constants.POPULATION_SIZE; i++) {
            for (int j = 0; j < Constants.NO_OF_TASKS; j++) {
                int dcIndex = (int) (Math.random() * Constants.NO_OF_DATACENTERS);
                particles[i].datacenters[dcIndex].add(timeMatrix[j][dcIndex]);
            }
            System.out.println(particles[0]);
        }
    }
}
