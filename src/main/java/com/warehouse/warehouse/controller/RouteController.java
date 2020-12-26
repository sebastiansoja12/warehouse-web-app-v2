package com.warehouse.warehouse.controller;

import com.warehouse.warehouse.model.Route;
import com.warehouse.warehouse.service.RouteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
@RequestMapping("/api/routes")
public class RouteController {

    private final RouteService routeService;

    @PostMapping
    public Route saveRoute(@RequestBody Route route){
        return routeService.save(route);
    }

    @GetMapping("/all")
    public List<Route> findAllRoutes(){
        return routeService.findAllRoutes();
    }


    @GetMapping("/all/parcelId/{id}")
    public List<Route> findByParcelCode(@PathVariable UUID id){
        return routeService.findByParcelId(id);
    }
    @PostMapping("/all/parcelId/delete/{id}")
    public ResponseEntity<String> deleteRouteByParcelId(@Valid @PathVariable UUID id){
            routeService.deleteRouteByParcelId(id);
            return ResponseEntity.status(OK).body("Zarejestrowana paczka w systemie usunięta!");
    }
}
