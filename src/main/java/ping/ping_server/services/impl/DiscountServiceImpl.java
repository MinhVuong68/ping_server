package ping.ping_server.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ping.ping_server.exception.AppException;
import ping.ping_server.models.Discount;
import ping.ping_server.repositories.DiscountRepository;
import ping.ping_server.services.DiscountService;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;
    @Override
    public Object getDiscountByCode(String code) {
        Discount discount = discountRepository.findByCode(code);
        if(discount == null)
            return AppException.builder().message("Discount code does not exist!").code(200).build();
        else if(discount.isUsed())
            return AppException.builder().message("Discount code already used!").code(200).build();
        else {
            return discount;
        }
    }
}
