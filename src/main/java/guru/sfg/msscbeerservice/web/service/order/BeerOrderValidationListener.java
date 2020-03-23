package guru.sfg.msscbeerservice.web.service.order;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import guru.sfg.brewery.model.events.ValidateOrderRequest;
import guru.sfg.brewery.model.events.ValidateOrderResult;
import guru.sfg.msscbeerservice.config.JmsConfig;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class BeerOrderValidationListener {

	private final BeerOrderValidator validator;

	private final JmsTemplate jmsTemplate;

	@JmsListener(destination = JmsConfig.VALIDATE_ORDER_QUEUE)
	public void listen(ValidateOrderRequest validateOrderRequest) {
		Boolean isValid = validator.validateOrder(validateOrderRequest.getBeerOrder());

		jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE, ValidateOrderResult.builder()
				.isValid(isValid).orderId(validateOrderRequest.getBeerOrder().getId()).build());

	}

}
