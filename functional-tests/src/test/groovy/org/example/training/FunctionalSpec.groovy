package org.example.training

import spock.lang.Specification

class FunctionalSpec extends Specification {

    /**
     * <p>TODO: Write a feature method that creates a new todo by posting to the {@code api/todo} endpoint which results
     * in a 201 response and then fetches the created todo using the url returned as the value of the {@code Location}
     * header of the post response. Verify that the fields of the fetched todo are the same as the one that were posted,
     * it's a {@code 200} response and that the content type is {@code application/json}.</p>
     * <p>Consider using the various available classes with constants like
     * {@link io.netty.handler.codec.http.HttpHeaderNames}, {@link io.netty.handler.codec.http.HttpResponseStatus} and
     * {@link ratpack.http.MediaType}. Think about using {@link com.fasterxml.jackson.databind.ObjectMapper} to write and
     * parse json for {@link org.example.training.persistence.Todo} instances instead of using json string literals to
     * make your tests less brittle should the formatting of json change.</p>
     */

    /**
     * <p>TODO: Add a feature method that asserts that a get request to the {@code api/todos/:id} endpoint for an unknown
     * id results in a 404 response/p>
     */

    /**
     * <p>TODO: Write a feature method that creates a couple of todos by posting to the {@code api/todo} endpoint
     * and then verifies that they are listed in a response to a get request to {@code api/todos} endpoint. Verify
     * that the fields of the fetched todos are the same as the one that were posted, it's a {@code 200} response and that
     * the content type is {@code application/json}</p>
     */

    /**
     * <p>TODO: Write a feature method that checks todo updating functionality by sending a put request to the uri
     * returned as the value of the {@code Location} header of response to a post request creating a new todo. Fetch the
     * updated todo and verify its fields are of the the same values as the ones used to update it.</p>
     */

    /**
     * <p>TODO: Write a feature method that checks the ability to add a todo with a selected id by sending a put request
     * to the {@link api/todos/:id}. Verify that the response status is 201 and that the todo can be fetched by sending
     * a get request to the same uri that was used for sending the put request.</p>
     */

    /**
     * <p>TODO: Write a feature method that checks todo deleting functionality by sending a delete request to the uri
     * returned as the value of the {@code Location} header of response to a post request creating a new todo. Verify
     * that a subsequent get reuqest to the same uri results in a 404 response.</p>
     */

    /**
     * <p>TODO: Add a feature method that asserts that a delete to the {@code api/todos/:id} endpoint for an unknown
     * id results in a response with a 404 status</p>
     */

    /**
     * <p>TODO: Write a feature method that verifies that post requests to {@code api/todos} and put requests to the
     * {@code api/todos/:id} with {@code Content-Type} header not set to {@code application/json} result in
     * responses with a 415 status code</p>
     */

    /**
     * <p>TODO: Write a feature method that verifies that post requests to {@code api/todos} and put requests to the
     * {@code api/todos/:id} with content that is not valid json, or valid json but with properties that don't exist
     * on the {@link org.example.training.persistence.Todo} class result in responses with a 400 status code</p>
     */

}
