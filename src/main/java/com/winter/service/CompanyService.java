package com.winter.service;

import com.winter.model.Company;
import com.winter.request.WorkerRequest;
import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Traced
@Path("api/company")
@RegisterRestClient
public interface CompanyService {

    @POST
    @Path("/worker")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Company makeWorkerRequest(WorkerRequest request);
}
