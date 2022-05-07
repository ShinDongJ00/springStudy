package webpage.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//상품 저장소
@Repository
public class ItemRepository {
    private static final Map<Long, Item> store = new HashMap<>(); //static 사용
    private static long sequence = 0L; //static 사용

    //상품 저장
    public Item saveItem(Item item){
        item.setId(++sequence);
        store.put(item.getId(),item);
        return item;
    }

    //상품찾기 - id번호
    public Item findItemById(Long id){
        return store.get(id);
    }

    //모든상품 출력
    public List<Item> findAllItems(){
        return new ArrayList<>(store.values());
    }

    //상품 업데이트
    public void updateItem(Long itemId, Item updateItem){
        Item item = findItemById(itemId);
        item.setItemName(updateItem.getItemName());
        item.setPrice(updateItem.getPrice());
        item.setQuantity(updateItem.getQuantity());
    }

    //전체삭제
    //테스트 코드 위함
    public void clearStore() {
        store.clear();
    }
}
