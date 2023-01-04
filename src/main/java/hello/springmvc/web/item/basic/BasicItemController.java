package hello.springmvc.web.item.basic;

import hello.springmvc.domain.item.Item;
import hello.springmvc.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor //생성자 자동 주입
public class BasicItemController {
    private final ItemRepository itemRepository; //자동주입

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "html/items";
    }

    //상품 상세 폼
    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "html/item";
    }

    @PostConstruct
    public void init() {
        itemRepository.save(new Item("testA",10000,20));
    }
}
