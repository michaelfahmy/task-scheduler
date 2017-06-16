import net.sourceforge.jswarm_pso.Swarm;

public class Main {
    private static MyParticle particles[];

    public static void main(String[] args) {
        initParticles();

        Swarm swarm = new Swarm(Constants.POPULATION_SIZE, new MyParticle(), new MyFitnessFunction());

        swarm.setMinPosition(0);
        swarm.setMaxPosition(Constants.NO_OF_DATA_CENTERS - 1);
        swarm.setMaxMinVelocity(0.5);
        swarm.setParticles(particles);
        swarm.setParticleUpdate(new MyParticleUpdate(new MyParticle()));

        for (int i = 0; i < 500; i++) {
            swarm.evolve();
            if (i % 10 == 0) {
                System.out.println("Global best at iteration (" + i + "): " + swarm.getBestFitness());
            }
        }

        System.out.println("\nThe best fitness value: " + swarm.getBestFitness());
        System.out.println("The best solution is: ");
        MyParticle bestParticle = (MyParticle) swarm.getBestParticle();
        System.out.println(bestParticle.toString());
    }

    private static void initParticles() {
        particles = new MyParticle[Constants.POPULATION_SIZE];
        for (int i = 0; i < Constants.POPULATION_SIZE; ++i)
            particles[i] = new MyParticle();
    }
}