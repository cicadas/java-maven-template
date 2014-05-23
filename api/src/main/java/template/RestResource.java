package template;

import template.core.Id;
import template.exception.ExceptionManager;
import template.util.SecurityHelper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.Callable;

/**
 * Segments related operations, normally: CRUD <br/>
 * This resource need YCA header.
 */
public class RestResource {
    static {
        SecurityHelper.setTrust();
    }

    /**
     * Store a generic entry list to persistence storage
     * <h3>Request example (Genome segment)</h3>
     * {
     * "name":"genome/US/Test Segment",
     * "expiration":"2013-12-31",
     * "type":"custom",
     * "description":"This is a fake Genome segment for testing purpose",
     * "created_by":"cnaras",
     * "segment_properties":{
     * "referrer_id":"123434",
     * "referrer_name":"genome"
     * }
     * }
     * <h3>Response example</h3>
     * {"id":"20000941"}
     *
     * @param raw a JSON array including generic segments
     * @return id tuple list of created segments.  For example, if the generic segment is Head+Bow+Rmx+Apt you will toLong
     * the tuple like [{"segment":"123", "pixel":"123", "apt":"123"},{"segment":"124", "pixel":"124", "apt":"124"}]
     * @throws Exception Web related exceptions
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response store(final String raw) throws Exception {
        return ExceptionManager._try(new Callable<Response>() {
            /**
             * Computes a result, or throws an exception if unable to do so.
             *
             * @return computed result
             * @throws Exception if unable to compute a result
             */
            @Override
            public Response call() throws Exception {
                return Response.ok().build();
            }
        });
    }


    /**
     * Get a full information.
     * <h3>Response example</h3>
     * {"id": "20000573",
     * "name": "zhouzh002",
     * "expiration": "2013-12-31",
     * "type": "bow",
     * "description": "This is a fake BOW segment for testing purpose",
     * "created_by": "apimeta",
     * "modified_by": "abc",
     * "created_on": "2013-03-17",
     * "modified_on": "2013-03-17",
     * "status": "active",
     * "segment_definition": {
     * "left": null,
     * "right": null,
     * "segdef": {
     * "left": null,
     * "right": null,
     * "featuredef": {
     * "left": null,
     * "right": null,
     * "featureName": "email",
     * "attrName": "domain",
     * "operator": "IN",
     * "value": ["zhouzh002.com"]
     * },
     * "featuredefprop": [
     * {
     * "name": "freq",
     * "op": ">=",
     * "value": "1"
     * },
     * {
     * "name": "rec",
     * "op": "=",
     * "value": "7"
     * }
     * ]
     * }
     * }, "exchanges": [
     * {
     * "name": "rmx",
     * "segment_id": "2346499",
     * "segment_owner": "23351",
     * "segment_seat": "23351",
     * "status": "active"
     * }
     * ]}
     *
     * @param id input
     * @return Generic JSON
     * @throws Exception Web related exceptions
     */
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response represent(
            @PathParam("id") final Id id) throws Exception {
        return ExceptionManager._try(new Callable<Response>() {
            @Override
            public Response call() throws Exception {
                return Response.ok().build();
            }
        });
    }


    /**
     * <ul>
     * <li>status</li>
     * <li>description</li>
     * <li>last_modified_by</li>
     * <li>last_modified_time</li>
     * <li>expiration</li>
     * </ul>
     *
     * @param raw Generic input JSON
     * @return Generic segment id
     * @throws Exception Web related exceptions
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(
            final String raw,
            @PathParam("id") final Id id) throws Exception {
        return ExceptionManager._try(new Callable<Response>() {
            @Override
            public Response call() throws Exception {
                return Response.ok().build();
            }
        });
    }

    /**
     * Remove a entry
     *
     * @param id category id
     * @throws Exception
     */
    @DELETE
    @Path("/{id}")
    public Response delete(
            @PathParam("id") final Id id
    ) throws Exception {
        return ExceptionManager._try(new Callable<Response>() {
            @Override
            public Response call() throws Exception {
                return Response.noContent().build();
            }
        });
    }
}
