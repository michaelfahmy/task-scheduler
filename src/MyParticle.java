import net.sourceforge.jswarm_pso.Particle;

import java.util.LinkedList;

public class MyParticle extends Particle {
    @SuppressWarnings("unchecked")
    public LinkedList<Double>[] datacenters = new LinkedList[Constants.NO_OF_DATACENTERS];

    public MyParticle() {
        super(Constants.NO_OF_TASKS);
        for (int i = 0; i < Constants.NO_OF_DATACENTERS; ++i)
            datacenters[i] = new LinkedList<>();
    }

    public MyParticle(Particle sampleParticle) {
        super(sampleParticle);
    }
}