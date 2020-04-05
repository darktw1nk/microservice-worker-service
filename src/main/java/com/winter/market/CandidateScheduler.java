package com.winter.market;

import com.winter.database.WorkerRepository;
import com.winter.database.WorkerRepositoryPanacheImpl;
import com.winter.resources.WorkerResource;
import com.winter.service.WorkerGenerator;
import io.quarkus.scheduler.Scheduled;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CandidateScheduler {
    private static final Logger logger = Logger.getLogger(CandidateScheduler.class);
    private static final Integer MARKET_LIMIT = 30;

    @Inject
    WorkerRepository workerRepository;
    @Inject
    WorkerGenerator workerGenerator;

    @Scheduled(every = "60s")
    public void addCandidatesToMarker() {
        try {
            long candidatesCount = workerRepository.countCandidates();

            if (candidatesCount < MARKET_LIMIT) {
                workerRepository.save(workerGenerator.generateDesigner());
                Thread.sleep(100);
                workerRepository.save(workerGenerator.generateProgrammer());
                Thread.sleep(100);
                workerRepository.save(workerGenerator.generateMarketer());
            }
        } catch (Exception e) {
            logger.error("", e);
        }
    }

}
