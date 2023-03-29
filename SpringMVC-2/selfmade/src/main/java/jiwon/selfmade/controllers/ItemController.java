package jiwon.selfmade.controllers;

import jiwon.selfmade.dto.ItemEditDTO;
import jiwon.selfmade.dto.ItemSaveDTO;
import jiwon.selfmade.entity.Item;
import jiwon.selfmade.entity.Member;
import jiwon.selfmade.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();

        model.addAttribute("items", items);
        return "items/items";
    }

    @GetMapping("/add")
    public String addForm(@ModelAttribute("item") ItemSaveDTO itemSaveDTO) {
        return "items/addForm";
    }


    @PostMapping("/add")
    public String add(@Validated @ModelAttribute("item") ItemSaveDTO itemSaveDTO, BindingResult bindingResult,
                      RedirectAttributes redirectAttributes) {

        if (itemSaveDTO.getPrice() != null && itemSaveDTO.getQuantity() != null) {
            long result = (long) itemSaveDTO.getPrice() * itemSaveDTO.getQuantity();

            if (result < 10000) {
                bindingResult.reject("totalPriceMin", new Object[]{10000, result}, null);
            }

        }

        if (bindingResult.hasErrors()) {
            return "items/addForm";
        }

        Item item = new Item(itemSaveDTO.getItemName(), itemSaveDTO.getPrice(), itemSaveDTO.getQuantity());
        Item saveItem = itemRepository.save(item);

        redirectAttributes.addAttribute("itemId", saveItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/items/{itemId}";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item findItem = itemRepository.findById(itemId);
        model.addAttribute("item", findItem);
        return "items/item";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item findItem = itemRepository.findById(itemId);
        model.addAttribute("item", findItem);
        return "items/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@Validated @ModelAttribute("item") ItemEditDTO itemEditDTO, BindingResult bindingResult,
                       @PathVariable Long itemId) {

        if (itemEditDTO.getPrice() != null && itemEditDTO.getQuantity() != null) {
            long result = (long) itemEditDTO.getPrice() * itemEditDTO.getQuantity();

            if (result < 10000) {
                bindingResult.reject("totalPriceMin", new Object[]{10000, result}, null);
            }

        }

        if (bindingResult.hasErrors()) {
            return "items/editForm";
        }

        itemRepository.update(itemId, itemEditDTO);
        return "redirect:/items/{itemId}";
    }
}
