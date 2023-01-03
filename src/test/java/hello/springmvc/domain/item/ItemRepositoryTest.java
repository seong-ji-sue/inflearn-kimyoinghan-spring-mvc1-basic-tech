package hello.springmvc.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {
    ItemRepository repository = new ItemRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    void save() {
        //given
        Item item = new Item("item", 20000, 10);

        //when
        Item saveItem = repository.save(item);
        Item findItem = repository.findById(saveItem.getId());

        //then
        Assertions.assertThat(findItem).isEqualTo(saveItem);
    }

    @Test
    void findAll() {
        //given
        Item itemA = new Item("itemA", 2000, 10);
        Item itemB = new Item("itemB", 2000, 10);

        repository.save(itemA);
        repository.save(itemB);

        //when
        List<Item> result = repository.findAll();

        //then
        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result).contains(itemA, itemB);
    }

    @Test
    void update() {
        //given
        Item item = new Item("item", 20000, 20);
        Item saveItem = repository.save(item);
        Long itemId = saveItem.getId();



        //when
        Item itemUpdate = new Item("itemUpdate", 30000, 20);
        repository.update(itemId, itemUpdate);

        Item findItem = repository.findById(itemId);

        //then
        Assertions.assertThat(findItem.getItemName()).isEqualTo(itemUpdate.getItemName());
    }
}