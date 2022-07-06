package com.madfooat.api;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Launcher;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;

import java.util.HashSet;
import java.util.Set;

public class MainVerticle extends AbstractVerticle {


  public static void main(String[] args) {
    Launcher.executeCommand("run", MainVerticle.class.getName());
  }
//S
  public MainVerticle() {

  }

  @Override
  public void start() {
    Router router = routesInitialize();
    vertx.createHttpServer(new HttpServerOptions().setCompressionSupported(true).setTcpKeepAlive(!true))
      .requestHandler(router)
      .listen(8878);//sd
  }

  private Router routesInitialize() {
    Set<String> allowedHeaders = new HashSet<>();
    allowedHeaders.add("x-requested-with");
    allowedHeaders.add("Access-Control-Allow-Origin");
    allowedHeaders.add("origin");
    allowedHeaders.add("Content-Type");
    allowedHeaders.add("accept");
    allowedHeaders.add("X-PINGARUNER");

    Set<HttpMethod> allowedMethods = new HashSet<>();
    allowedMethods.add(HttpMethod.GET);
    allowedMethods.add(HttpMethod.POST);
    Router baseRouter = Router.router(vertx);
    Router publicApiRouter = Router.router(vertx);
    baseRouter.route("/").handler(this::indexHandler);
    baseRouter.route().handler(CorsHandler.create("*").allowedHeaders(allowedHeaders).allowedMethods(allowedMethods));
    publicApiRouter.route("/*").handler(BodyHandler.create());
    baseRouter.mountSubRouter("/", publicApiRouter);
    return baseRouter;
  }

  private void indexHandler(RoutingContext routingContext) {
    HttpServerResponse response = routingContext.response();
    response.putHeader("Content-Type", "text/html").end("Hello, This is MadfooatCom deployment test API for git Actions!");
  }

}


