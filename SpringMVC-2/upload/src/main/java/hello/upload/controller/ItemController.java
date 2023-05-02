package hello.upload.controller;

import hello.upload.domain.Item;
import hello.upload.domain.ItemRepository;
import hello.upload.domain.UploadFile;
import hello.upload.file.FileStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final FileStore fileStore;

    @GetMapping("/items/new")
    public String newItem(@ModelAttribute ItemForm itemForm) {
        return "item-form";
    }

    @PostMapping("/items/new")
    public String saveItem(@ModelAttribute ItemForm itemForm, RedirectAttributes redirectAttributes) throws IOException {
        UploadFile uploadFile = fileStore.storeFile(itemForm.getAttachFile());
        List<UploadFile> imageFiles = fileStore.storeFiles(itemForm.getImageFiles());

        Item item = new Item();
        item.setItemName(itemForm.getItemName());
        item.setAttachFile(uploadFile);
        item.setImageFiles(imageFiles);
        log.info("item.imageFiles={}", item.getImageFiles());
        itemRepository.save(item);

        redirectAttributes.addAttribute("itemId", item.getId());

        return "redirect:/items/{itemId}";
    }

    @GetMapping("/items/{itemId}")
    public String items(@PathVariable Long itemId, Model model) {

        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "item-view";
    }

    @GetMapping("/images/{filename}")
    public ResponseEntity<Resource> uploadImage(@PathVariable String filename) throws MalformedURLException {

        UrlResource resource = new UrlResource("file:" + fileStore.getFullPath(filename));
        return ResponseEntity.ok().body(resource);//
    }

    @GetMapping("/attach/{itemId}")
    public ResponseEntity<Resource> uploadFile(@PathVariable Long itemId) throws MalformedURLException {
        Item item = itemRepository.findById(itemId);
        UploadFile attachFile = item.getAttachFile();
        String uploadFileName = attachFile.getUploadFileName();
        String storeFileName = attachFile.getStoreFileName();

        UrlResource resource = new UrlResource("file:" + fileStore.getFullPath(storeFileName));

        String encodedName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename:\"" + encodedName + "\"";
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }
}
