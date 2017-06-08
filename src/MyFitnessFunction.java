import net.sourceforge.jswarm_pso.FitnessFunction;
import net.sourceforge.jswarm_pso.Particle;

public class MyFitnessFunction extends FitnessFunction {
    @Override
    public double evaluate(double[] position) {
        return 0;
    }

    @Override
    public double evaluate(Particle particle) {
        MyParticle p = (MyParticle) particle;
        double fit = 0;
        for (int i = 0; i < Constants.NO_OF_DATACENTERS; i++) {
            double totalTime = 0;
            for (double t : p.datacenters[i])
                totalTime += t;
            if (totalTime > fit)
                fit = totalTime;
        }
        return fit;
    }
}