package com.winter.resources;

import com.winter.model.Worker;
import com.winter.request.WorkerRequest;
import com.winter.request.WorkerRequestType;
import com.winter.service.WorkerService;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.Optional;

@Path("/api/worker")
public class WorkerResource {
    private static final Logger logger = Logger.getLogger(WorkerResource.class);

    @Context
    UriInfo uriInfo;
    @Inject
    WorkerService workerService;

    @Counted(name = "countGetWorkers", description = "Counts how many times the getWorkers method has been invoked")
    @Timed(name = "timeGetWorkers", description = "Times how long it takes to invoke getWorkers method", unit = MetricUnits.MILLISECONDS)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        logger.debug("get all");
        List<Worker> workers = workerService.listAll();
        return Response.ok(workers).build();
    }

    @Counted(name = "countGetWorker", description = "Counts how many times the getWorker method has been invoked")
    @Timed(name = "timeGetWorker", description = "Times how long it takes to invoke getWorker method", unit = MetricUnits.MILLISECONDS)
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") Long id) {
        return workerService.getById(id)
                .map(worker -> Response.ok(worker).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());
    }


    @Counted(name = "countGetWorkerList", description = "Counts how many times the getWorker method has been invoked")
    @Timed(name = "timeGetWorkerList", description = "Times how long it takes to invoke getWorker method", unit = MetricUnits.MILLISECONDS)
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMultipleWorkers(@QueryParam("ids") List<Long> ids) {
        return Response.ok(workerService.getByIds(ids)).build();
    }

    @Counted(name = "countGetCandidates", description = "Counts how many times the getCandidates method has been invoked")
    @Timed(name = "timeGetCandidates", description = "Times how long it takes to invoke getCandidates method", unit = MetricUnits.MILLISECONDS)
    @GET
    @Path("/candidates")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCandidates() {
        List<Worker> workers = workerService.listCandidates();
        return Response.ok(workers).build();
    }

    @Counted(name = "countHireWorker", description = "Counts how many times the countHire method has been invoked")
    @Timed(name = "timeHireWorker", description = "Times how long it takes to invoke timeHireWorker method", unit = MetricUnits.MILLISECONDS)
    @Path("/action")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response makeWorkerAction(WorkerRequest request) {
        Optional<Worker> dbWorker = workerService.getById(request.getWorkerId());
        if (dbWorker.isPresent()) {
            boolean result = false;
            if (request.getType().equals(WorkerRequestType.HIRE.getType())) {
                result = workerService.hireWorker(dbWorker.get(), request.getCompanyId(), request.getOffice());
            } else {
                logger.debug("fire request");
                result = workerService.fireWorker(dbWorker.get(), request.getCompanyId(), request.getOffice());
            }

            if (result) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Counted(name = "countUpdateWorker", description = "Counts how many times the countUpdateWorker method has been invoked")
    @Timed(name = "timeUpdateWorker", description = "Times how long it takes to invoke timeUpdateWorker method", unit = MetricUnits.MILLISECONDS)
    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateWorker(@PathParam("id") Long id, Worker worker) {
        if (worker == null) return Response.status(Response.Status.BAD_REQUEST).build();
        Optional<Worker> existed = workerService.getById(id);
        if (existed.isPresent()) {
            logger.debug("existed");
            worker.setId(id);
            return workerService.save(worker)
                    .map(savedWorker -> Response.ok(savedWorker).build())
                    .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());
        } else {
            logger.debug("not existed");
            worker.setId(id);
            Optional<Worker> saved = workerService.save(worker);
            return saved.map(savedWorker ->
                    Response.created(uriInfo.getBaseUriBuilder()
                            .path("/api/worker/{id}")
                            .build(savedWorker.getId())).build()

            ).orElseGet(() -> Response.status(Response.Status.BAD_REQUEST).build());
        }
    }
}
