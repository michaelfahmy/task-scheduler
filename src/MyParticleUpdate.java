import net.sourceforge.jswarm_pso.Particle;
import net.sourceforge.jswarm_pso.ParticleUpdate;
import net.sourceforge.jswarm_pso.Swarm;


public class MyParticleUpdate extends ParticleUpdate {
    private static final double W = 0.9; // Inertial weight
    private static final double C = 2.0; // acceleration coefficient

    MyParticleUpdate(Particle particle) {
        super(particle);
    }

    public void update(Swarm swarm, Particle particle) {

        double[] v = particle.getVelocity();
        double[] x = particle.getPosition();
        double[] pbest = particle.getBestPosition();
        double[] gbest = swarm.getBestPosition();

        for (int i = 0; i < Constants.NO_OF_TASKS; ++i) {
            v[i] = W * v[i] + C * rand() * (pbest[i] - x[i]) + C * rand() * (gbest[i] - x[i]);
            x[i] = Math.round(x[i] + v[i]);
        }
    }

    private double rand() {
        return Math.random();
    }
}
