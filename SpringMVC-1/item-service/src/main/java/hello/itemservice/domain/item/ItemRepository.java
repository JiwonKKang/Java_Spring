package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>();

    private static Long sequence = 1L;

    public Item save(Item item) {
        item.setId(sequence++);
        store.put(item.getId(), item);
        return item;
    }

    public ArrayList<Item> findAll() {
        ArrayList<Item> items = new ArrayList<>(store.values());
        return items;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public void update(Long id, Item updateParam) {
        Item item = store.get(id);
        item.setItemName(updateParam.getItemName());
        item.setPrice(updateParam.getPrice());
        item.setQuantity(updateParam.getQuantity());
    }
}
