package com.winter.database;

import com.winter.model.Worker;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.runtime.JpaOperations;
import io.quarkus.panache.common.Parameters;
import org.eclipse.microprofile.opentracing.Traced;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;

@Traced
@ApplicationScoped
public class WorkerRepositoryPanacheImpl implements WorkerRepository{

    @Transactional
    public Worker save(Worker worker){
        EntityManager em = JpaOperations.getEntityManager();
        if (worker.getId() == null) {
            em.persist(worker);
            return worker;
        } else {
            return em.merge(worker);

        }
    }

    public long countCandidates(){
        return count("companyId is null");
    }

    public Worker getById(Long id) throws NoResultException {
        return find("id=:id", Parameters.with("id", id)).singleResult();
    }

    @Override
    public List<Worker> listCandidates() {
        return find("companyId is null").list();
    }

    @Override
    public List<Worker> getByIds(List<Long> ids) {
        return list("id in :ids",Parameters.with("ids",ids));
    }
}
