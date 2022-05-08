package webpage.itemservice.web.item.basic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import webpage.itemservice.domain.item.Item;
import webpage.itemservice.domain.item.ItemRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor // -> final이나 @NotNull이 붙은 곳에 생성자 주입을 해준다
public class BasicItemController {

    private final ItemRepository itemRepository;

    //상품목록
    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAllItems();
        model.addAttribute("items",items);
        return "basic/items";
    }

    //테스트용
    @PostConstruct
    public void postConstruct(){
        itemRepository.saveItem(new Item("testA", 10000, 10));
        itemRepository.saveItem(new Item("testB", 20000, 20));
    }

    //상품상세
    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findItemById(itemId);
        model.addAttribute("item",item);
        return "basic/item";
    }

    //상품 등록폼
    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

    //상품등록 처리
    @PostMapping("/add")
    public String addItem(@ModelAttribute("item")Item item, RedirectAttributes redirectAttributes){
        Item saveItem = itemRepository.saveItem(item);
        redirectAttributes.addAttribute("itemId",saveItem.getId());
        redirectAttributes.addAttribute("addStatus", true);
        return "redirect:/basic/items/"+item.getId();
    }

    //상품수정 폼
    @GetMapping("/{itemId}/edit")
    public String editItemForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findItemById(itemId);
        model.addAttribute("item",item);
        return "basic/editForm";
    }

    //상품수정 처리
    @PostMapping("/{itemId}/edit")
    public String editItem(@PathVariable Long itemId, @ModelAttribute("item")Item item, RedirectAttributes redirectAttributes){
        itemRepository.updateItem(itemId,item);
        redirectAttributes.addAttribute("itemId",itemId);
        redirectAttributes.addAttribute("updateStatus",true);
        return "redirect:/basic/items/{itemId}";
    }
}
