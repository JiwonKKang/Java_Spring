package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        Item item = new Item("itemA", 10000, 10);

        Item savedItem = itemRepository.save(item);

        Item findItem = itemRepository.findById(item.getId());

        assertThat(savedItem).isEqualTo(findItem);
    }

    @Test
    void findAll() {
        Item item = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 20000, 20);

        itemRepository.save(item);
        itemRepository.save(item2);

        List<Item> list = itemRepository.findAll();

        assertThat(list.size()).isEqualTo(2);
        assertThat(list).contains(item, item2);

    }

    @Test
    void updateItem() {
        Item item = new Item("item", 10000, 10);
        Item item2 = new Item("item2", 20000, 20);

        itemRepository.save(item);
        itemRepository.update(item.getId(), item2);

        assertThat(item.getItemName()).isEqualTo(item2.getItemName());


    }
}