package ping.ping_server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ping.ping_server.exception.AppException;
import ping.ping_server.models.Order;
import ping.ping_server.models.OrderStatus;
import ping.ping_server.models.dto.AddOrderDTO;
import ping.ping_server.models.dto.OrderDTO.OrderDTO;
import ping.ping_server.repositories.OrderRepository;
import ping.ping_server.services.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<?> getOrders(
            @RequestParam(name = "status",required = false) String status,
            @RequestParam(name = "customerId", required = false) String customerId
    ) {
        List<OrderDTO> completedOrders = orderService.getOrders(status,customerId);
        return ResponseEntity.ok(completedOrders);
        //return null;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrder(@PathVariable("orderId") Long id){
        OrderDTO orderDTO = orderService.getOrderById(id);
        return ResponseEntity.ok(orderDTO);
    }

    @PostMapping("")
    public ResponseEntity<?> addOrder(@RequestBody AddOrderDTO addOrderDTO) {
        Object response = orderService.addOrder(addOrderDTO);
        if(response instanceof AppException)
            return ResponseEntity.status(((AppException) response).getCode()).body(response);
        return ResponseEntity.ok().body(response);
    }

}
