package hello.springmvc.web.item.basic;

import hello.springmvc.domain.item.Item;
import hello.springmvc.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    //상품 등록 폼
    @GetMapping("/add")
    public String addForm() {
        return "html/addForm";
    }

    //상품 등록 Post 처리
//    @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                            @RequestParam int price,
                            @RequestParam Integer quantity,
                            Model model
                            ) {
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);
        itemRepository.save(item);
        model.addAttribute("item",item);
        return "html/item";
    }

    @PostMapping("/add")
    public String addItemV2(@ModelAttribute Item item,
                            Model model
    ) {
        itemRepository.save(item);
//        model.addAttribute("item",item); //생략가능
        return "html/item";
    }

    //상품 수정 폼 매핑
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "html/editForm";
    }

    //상품 수정 매핑
    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }







    @PostConstruct
    public void init() {
        itemRepository.save(new Item("testA",10000,20));
    }
}
