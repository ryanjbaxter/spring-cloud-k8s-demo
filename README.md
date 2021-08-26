# chaos-monkey-spring-boot-demo
Demo of Chaos Monkey for Spring Boot Using Spring Cloud Kubernetes

# Building The Containers

Each project contains a Dockerfile that will create a container for the app.  You can do this easily
by running `./mvn clean package -Pdocker`.

**NOTE** You should edit the parent POM file and change the value of `docker.image.prefix` to
your own prefix.

After building the container images you will need to push them to a repository that your Kubernetes
deployment has access to, for example Docker Hub.

# Deploying To Kubernetes

## Testing The Apps

Each app is exposes a `nodeport`, you will need to find the `nodeport` for each application in your deployment.
If using MiniKube you can easily open the service by running `minikube service <servicename>`.  For example

```
$ minikube service gateway
```

### Toys Bestseller

This app exposes one endpoint at `/toys/bestseller` which accepts a `GET` request and returns JSON 
representing the best selling toys.

In addition all actuator endpoints are exposed, this is useful for demonstrationg the Spring Cloud Kubernetes
integration with ConfigMaps.

The app uses Chaos Monkey for Spring Boot to inject random latency into `RestController`s.  It is disabled by
default but can be enabled by changing the value of the ConfigMap you created above.

If you hit `/actuator/env` you should see the ConfigMap in the list of `PropertySources` for the app.  Edit the
ConfigMap in Kubernetes changing `chaos.monkey.enabled` from `false` to `true`.  Now hit `/actuator/env` again 
and you should see the property change.

Now when you hit `/toys/bestseller` you should randomly see the response take a bit to come back.  This will
be useful when demoing Hystrix via the gateway.

In addition since the Actuator endpoints are enabled it is useful to hit `/actuator/health` in this app
to show the health endpoint integration with Kubernetes.

### Fashion Bestseller

This app exposes one endpoint at `/fashion/bestseller` which accepts a `GET` request and returns JSON
representing the best selling fashion items.

### Hot Deals

This app exposes one endpoint at `/hotdeals` which accepts a `GET` request and returns JSON representing
deals from the fashion and toys service.

### Gateway

An API gateway to the toys, fashion, and hotdeals services.  There are routes that point to eat `/toys/**`,
`/fashion/**` and `/hotdeals**`.  Each of these endpoints is protected by a Hystrix circuitbreaker tha when tripped
returns an empty array and the response contains a header called `fallback` with the value of `true`.

In addition there is another endpoint at `/startpage` which aggregates the responses from all the services.

When Chaos Monkey for Spring Boot is enabled on the toys service occationally the Hystrix circuit breaker will
be tripped when the latency is injected.
