package hello.springmvc.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository //컴포넌트 스캔 대상
public class ItemRepository {
    private static final Map<Long, Item> store = new HashMap<>();
    private static long sequence = 0L;

    //아이탬 저장
    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    //아이템 전체 조회
    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    //아이템 단일 조회
    public Item findById(Long id) {
        return store.get(id);
    }

    //상품 수정
    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    //상품 전체 삭제 - 테스트 용도
    public void clearStore() {
        store.clear();
    }
}
