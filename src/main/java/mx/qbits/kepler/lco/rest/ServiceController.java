package mx.qbits.kepler.lco.rest;

import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.Logger;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import mx.qbits.kepler.lco.model.Response;

/**
 * Clase controlador que contiene el flujo de negocio para exponer la búsqueda de RFC en LCO
 * @author gustavo
 *
 */
public class ServiceController extends AbstractVerticle {
    private final static Logger logger = Logger.getLogger(ServiceController.class);

    /**
     * Inicializa el servicio de búsqueda
     */
    public void start(Future<Void> fut) {
        logger.info("Iniciando vertical");
        // Cargamos la pantalla de instrucciones
        InputStream inputStream = ServiceController.class.getClassLoader().getResourceAsStream("welcome.html");
        @SuppressWarnings("resource")
        Scanner s = new Scanner(inputStream).useDelimiter("\\A");
        String welcome = s.hasNext() ? s.next() : "";

        Router router = Router.router(vertx);
        router.route("/").handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.
                putHeader("content-type", "text/html").
                end(welcome);
        });
        router.route().handler(BodyHandler.create());
        router.get("/api/calc").handler(this::handleQueryRequest);

        // Create the HTTP server and pass the "accept" method to the request handler.
        vertx.createHttpServer().requestHandler(router::accept).listen(
                // Retrieve the port from the configuration. Default port is 8080.
                config().getInteger("http.port", 8080), result -> {
                    if (result.succeeded()) {
                        fut.complete();
                    } else {
                        fut.fail(result.cause());
                    }
                });
    }

    /**
     * Se dispara cuando ocurre una excepcion
     * throws Exception
     */
    @Override
    public void stop() throws Exception {
        // clean all resources and exit ...
    }

    /**
     * Recibe RFC y fecha para búsqueda en LCO, responde sobre el contexto un json o un error en base a los resultados de la busqueda
     * @param routingContext Contexto para el control de una solicitud
     */
    private void handleQueryRequest(RoutingContext routingContext) {
        HttpServerRequest request = routingContext.request();
        String num = request.getParam("num");
        try {
            //build response test 5
            Response response = new Response(request, num);
            Map<String, Object> resp = response.build();
            
            // route response
            routingContext.
            response().
            setStatusCode(200).
            putHeader("content-type", "application/json; charset=utf-8").
            end(Json.encodePrettily(resp));
        } catch (Exception e) {
            routingContext.response().setStatusCode(406).
            putHeader("content-type", "text/html").end("<h2>406: Bad Request</h2>");
        }
        // http://localhost:8080/api/search?rfc=aesg671022iz7&date=2017-05-23-14-24-12
    }

}
