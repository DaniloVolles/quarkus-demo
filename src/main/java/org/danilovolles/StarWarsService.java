package org.danilovolles;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.time.temporal.ChronoUnit;

@RegisterRestClient(baseUri = "https://swapi.info/api/")
public interface StarWarsService {

    String MSG_ERROR = "Fallback ";

    @GET
    @Path("starships")
    @Produces(MediaType.APPLICATION_JSON)
    @Timeout(value = 3000L)
    @Fallback(fallbackMethod = "starshipsFallback")
    @CircuitBreaker(
            requestVolumeThreshold = 2, // Avalia o estado após pelo menos 2 chamadas.
            failureRatio = .5,          // Se 50% ou mais dessas chamadas falharem, o circuito abre.
            delay = 3000L,              // Após abrir, espera 3s antes de testar novamente.
            successThreshold = 2        // Precisa de 2 sucessos seguidos para fechar o circuito.
    )
    String getStarships();

    default String starshipsFallback() {
        return MSG_ERROR;
    }
}
