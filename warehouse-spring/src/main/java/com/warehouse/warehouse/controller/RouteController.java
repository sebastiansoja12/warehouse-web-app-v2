package com.warehouse.warehouse.controller;

import com.warehouse.warehouse.model.Route;
import com.warehouse.warehouse.service.RouteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/tmp")
    public List<Route> getTemporaryRoutes(){
        return routeService.findAllTemporaryRoutesByUsername();
    }

    @PostMapping("/tmp")
    public List<Route> saveTemporaryRoutes(@RequestBody Route route) {
        return routeService.temporarySave(route);
    }

    @PostMapping
    public void saveRoute() {
        routeService.save();
    }

    @GetMapping("/all/users")
    public List<Route> findAllRoutes() {
        List<Route> allRoutes = routeService.findAllRoutes();
        return allRoutes.stream()
                .sorted(Comparator.comparing(Route::getCreated).reversed())
                .limit(20)
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<Route> findAllByUsername() {
        List<Route> routesByUsername = routeService.findAllByUsername();
        return routesByUsername.stream()
                .sorted(Comparator.comparing(Route::getCreated).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/parcelId")
    public ResponseEntity<List<Route>> findByParcelId(@PathVariable UUID id) throws Exception {
        List<Route> route = routeService.findByParcelId(id);
        return new ResponseEntity<>(route, HttpStatus.OK);
    }

    @PostMapping("/{id}/parcelId")
    @Transactional
    public ResponseEntity<String> deleteRouteByParcelId(@Valid @PathVariable UUID id) {
        routeService.deleteRouteByParcelId(id);
        return ResponseEntity.status(OK).body("Zarejestrowana paczka usunięta");
    }

    @GetMapping("/{username}/user")
    public List<Route> findRoutesByUsername(@PathVariable String username) {
        List<Route> byUsername = routeService.findAllRoutes();
        return byUsername.stream()
                .filter(p -> p.getUser().getUsername().equals(username))
                .collect(Collectors.toList());
    }
}
