package com.winter.service;

import com.winter.model.Worker;

import java.util.List;
import java.util.Optional;

public interface WorkerService {

    List<Worker> listAll();

    List<Worker> listCandidates();

    List<Worker> getByIds(List<Long> ids);

    Optional<Worker> getById(Long id);

    Optional<Worker> save(Worker worker);

    boolean hireWorker(Worker worker,Long companyId,String office);

    boolean fireWorker(Worker worker,Long companyId,String office);
}
