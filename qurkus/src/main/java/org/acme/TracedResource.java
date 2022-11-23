package org.acme;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.db.Expert;
import org.acme.db.repository.ExpertRepo;
import org.acme.db.repository.ExpertRepository;
import org.jboss.logging.Logger;

import java.util.List;

@Path("/qurkusApi")
public class TracedResource {
    private final ExpertRepository repository;
    private final ExpertRepo expertRepo;

    private static final Logger LOG = Logger.getLogger(TracedResource.class);
    @Inject
    public TracedResource(ExpertRepository repository, ExpertRepo expertRepo) {
        this.repository = repository;
        this.expertRepo = expertRepo;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        LOG.info("hello");
        return "hello";
    }

    @GET
    @Path("/getExpert")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Expert> getMessage()  {
        return expertRepo.findAll();
    }
}