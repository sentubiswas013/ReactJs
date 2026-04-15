import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Async;

import jakarta.persistence.*;
import java.util.*;
import java.util.concurrent.CompletableFuture;

// Resilience4j
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@EnableAsync
@SpringBootApplication
class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}


// ============================================================
// ENTITY: User (Parent)
// ============================================================



// ============================================================
// ENTITY: Order (Child)
// ============================================================



// ============================================================
// REPOSITORIES
// ============================================================



// ============================================================
// SERVICE: User Service (Transactional)
// ============================================================



// ============================================================
// SERVICE: Payment (Circuit Breaker)
// ============================================================



// ============================================================
// SERVICE: Async
// ============================================================



// ============================================================
// SAGA ORCHESTRATOR
// ============================================================



// ============================================================
// CONTROLLER
// ============================================================
