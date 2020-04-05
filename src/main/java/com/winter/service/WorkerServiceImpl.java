package com.winter.service;

import com.winter.database.WorkerRepository;
import com.winter.model.Company;
import com.winter.model.Worker;
import com.winter.request.WorkerRequest;
import com.winter.request.WorkerRequestType;
import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Traced
@ApplicationScoped
public class WorkerServiceImpl implements WorkerService {
    private static final Logger logger = Logger.getLogger(WorkerServiceImpl.class);

    @Inject
    WorkerRepository workerRepository;

    @Inject
    @RestClient
    CompanyService companyService;

    @Override
    public List<Worker> listAll() {
        return workerRepository.listAll();
    }

    @Override
    public Optional<Worker> getById(Long id) {
        Worker worker = null;
        try {
            worker = workerRepository.getById(id);
        } catch (NoResultException e) {
            logger.error("", e);
        }
        return Optional.ofNullable(worker);
    }

    @Override
    public Optional<Worker> save(Worker worker) {
        return Optional.ofNullable(workerRepository.save(worker));
    }

    @Transactional
    @Override
    public boolean hireWorker(Worker worker, Long companyId, String office) {
        if (worker.getCompanyId() != null) {
            return false;
        } else {
            try {
                worker.setCompanyId(companyId);
                WorkerRequest request = createWorkerRequest(worker.getId(), companyId, office, WorkerRequestType.HIRE);
                Company company = companyService.makeWorkerRequest(request);
                if (company != null) {
                    save(worker);
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                logger.error("", e);
                return false;
            }
        }
    }

    @Override
    public boolean fireWorker(Worker worker, Long companyId, String office) {
        if (worker.getCompanyId() == null) {
            logger.error("company id is null");
            return false;
        } else {
            try {
                worker.setCompanyId(null);
                WorkerRequest request = createWorkerRequest(worker.getId(), companyId, office, WorkerRequestType.FIRE);
                Company company = companyService.makeWorkerRequest(request);
                if (company != null) {
                    logger.error("company is not null");
                    save(worker);
                    return true;
                } else {
                    logger.error("company is null");
                    return false;
                }
            } catch (Exception e) {
                logger.error("", e);
                return false;
            }
        }
    }

    private WorkerRequest createWorkerRequest(Long workerId, Long companyId, String office, WorkerRequestType type) {
        WorkerRequest request = new WorkerRequest();
        request.setCompanyId(companyId);
        request.setWorkerId(workerId);
        request.setOffice(office);
        request.setType(type.getType());
        return request;
    }

    @Override
    public List<Worker> listCandidates() {
        return workerRepository.listCandidates();
    }

    @Override
    public List<Worker> getByIds(List<Long> ids) {
        return workerRepository.getByIds(ids);
    }
}
