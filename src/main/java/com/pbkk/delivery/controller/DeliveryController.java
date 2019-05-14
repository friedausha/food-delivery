package com.pbkk.delivery.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pbkk.delivery.HibernateProxyTypeAdapter;
import com.pbkk.delivery.model.Delivery;
import com.pbkk.delivery.model.Driver;
import com.pbkk.delivery.model.Order;
import com.pbkk.delivery.model.Restaurant;
import com.pbkk.delivery.repository.DeliveryRepository;
import com.pbkk.delivery.repository.DriverRepository;
import com.pbkk.delivery.repository.OrderRepository;
import com.pbkk.delivery.repository.RestaurantRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    private final DeliveryRepository deliveryRepository;
    private final DriverRepository driverRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderRepository orderRepository;


    public DeliveryController(DeliveryRepository deliveryRepository, DriverRepository driverRepository, RestaurantRepository restaurantRepository, OrderRepository orderRepository) {
        this.deliveryRepository = deliveryRepository;
        this.driverRepository = driverRepository;
        this.restaurantRepository = restaurantRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<String> show(@PathVariable Integer id) throws JsonProcessingException {
        Delivery delivery = deliveryRepository.getOne(id);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
        Gson gson = gsonBuilder.create();
        String json = gson.toJson(delivery);

        if (delivery.getDriver() != null) return ResponseEntity.ok(json);
        else return ResponseEntity.ok("Waiting for driver to accept order");
    }

    @PostMapping("/request")
    public @ResponseBody
    String create(@RequestParam Integer order_id,
                  @RequestParam Integer restaurant_id,
                  @RequestParam String delivery_address) {
        Delivery delivery = new Delivery();
        Restaurant restaurant = restaurantRepository.getOne(restaurant_id);
        Order order = orderRepository.getOne(order_id);


        delivery.setStatus(1);
        delivery.setRestaurant(restaurant);
        delivery.setOrder(order);
        delivery.setDelivery_address(delivery_address);
        deliveryRepository.save(delivery);

        return "Delivery requested";
    }

    @PutMapping("/accept/{id}")
    public ResponseEntity<String> accept(@PathVariable Integer id, @RequestParam Integer driver_id) {
        Delivery orderRealTime = deliveryRepository.getOne(id);
        if (orderRealTime.getStatus() != 1) {
            return ResponseEntity.ok("The order has already been picked up by other driver");
        } else {
            Driver driver = driverRepository.getOne(driver_id);

            if (driver != null) {
                orderRealTime.setDriver(driver);
                orderRealTime.setStatus(2);
                deliveryRepository.save(orderRealTime);
                return ResponseEntity.ok("You accept the order. Please go the the restaurant");
            } else {
                return ResponseEntity.status(404).body("Driver not found");
            }
        }
    }

    @PutMapping("/update/{order_id}")
    public ResponseEntity<String> update(@RequestParam Integer status,
                                         @RequestParam Integer driver_id,
                                         @PathVariable Integer order_id) {
        Delivery order = deliveryRepository.getOne(order_id);
        if (order.getDriver().getId() != driver_id) {
            return ResponseEntity.status(404).body("You are not authorized to update the order status");
        } else {
            order.setStatus(status);
            deliveryRepository.save(order);
            return ResponseEntity.ok("Status updated");
        }
    }
}

