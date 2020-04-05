package com.winter.database;

import com.winter.model.Worker;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.util.List;

public interface WorkerRepository extends PanacheRepository<Worker> {

    Worker save(Worker worker);

    long countCandidates();

    Worker getById(Long id);

    List<Worker> listCandidates();

    List<Worker> getByIds(List<Long> ids);
}
