package tobyspring.hellospring.order;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.List;

@Service("proxyOrderService")
public class OrderServiceTxProxy implements OrderService {
    private final OrderService target;
    private final PlatformTransactionManager transactionManager;

    public OrderServiceTxProxy(@Qualifier("targetOrderService") OrderService target, PlatformTransactionManager transactionManager) {
        this.target = target;
        this.transactionManager = transactionManager;
    }

    @Override
    public Order createOrder(String no, BigDecimal total) {
        return target.createOrder(no, total);
    }

    @Override
    public List<Order> createOrders(List<OrderReq> reqs) {
        return new TransactionTemplate(transactionManager).execute(status -> target.createOrders(reqs));
    }
}
