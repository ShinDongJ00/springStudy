package webpage.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    void saveItem() {
        //given
        Item item = new Item("ItemA", 10000, 10);

        //when
        Item saveItem = itemRepository.saveItem(item);

        //then
        Item findItem = itemRepository.findItemById(saveItem.getId());
        Assertions.assertThat(saveItem).isEqualTo(findItem);
    }

    @Test
    void findAllItems() {
        //given
        Item item1 = new Item("Item1",1000,10);
        Item item2 = new Item("Item2", 2000,20);
        itemRepository.saveItem(item1);
        itemRepository.saveItem(item2);

        //when
        List<Item> allItems = itemRepository.findAllItems();

        //then
        Assertions.assertThat(allItems.size()).isEqualTo(2);
        Assertions.assertThat(allItems).contains(item1, item2);
    }

    @Test
    void updateItem() {
        //given
        Item item = new Item("item1",10000,10);
        Item saveItem = itemRepository.saveItem(item);
        long itemId = saveItem.getId();

        //when
        Item updateItem = new Item("newItem", 20000, 20);
        itemRepository.updateItem(itemId, updateItem);
        Item newItem = itemRepository.findItemById(itemId);

        //then
        Assertions.assertThat(updateItem.getItemName()).isEqualTo(newItem.getItemName());
        Assertions.assertThat(updateItem.getPrice()).isEqualTo(newItem.getPrice());
        Assertions.assertThat(updateItem.getQuantity()).isEqualTo(newItem.getQuantity());
    }
}