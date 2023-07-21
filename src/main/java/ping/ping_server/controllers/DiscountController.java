package ping.ping_server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ping.ping_server.exception.AppException;
import ping.ping_server.services.DiscountService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/discount")
public class DiscountController {
    private final DiscountService discountService;
    @GetMapping("/{discount_code}")
    public ResponseEntity<?> getDiscountByCode(@PathVariable("discount_code") String code) {
        Object response = discountService.getDiscountByCode(code);
        if(response instanceof AppException)
            return ResponseEntity.status(((AppException) response).getCode()).body(response);
        return ResponseEntity.ok().body(response);
    }
}
