package tobyspring.hellospring.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tobyspring.hellospring.order.Order;
import tobyspring.hellospring.order.OrderRepository;

@Repository
public interface JpaOrderRepository extends JpaRepository<Order, Long>, OrderRepository {

}
