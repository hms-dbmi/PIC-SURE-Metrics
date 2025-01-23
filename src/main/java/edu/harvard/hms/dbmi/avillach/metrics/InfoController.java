package edu.harvard.hms.dbmi.avillach.metrics;

import edu.harvard.hms.dbmi.avillach.metrics.model.LogEntry;
import edu.harvard.hms.dbmi.avillach.metrics.service.LoggingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    @PostMapping("/info")
    public ResponseEntity<String> getInfo(@RequestBody Object ignored) {
        return ResponseEntity.ok("Metrics");
    }
}