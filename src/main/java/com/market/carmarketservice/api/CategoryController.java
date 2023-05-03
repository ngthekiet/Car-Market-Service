package com.market.carmarketservice.api;

import com.market.carmarketservice.model.category.Category;
import com.market.carmarketservice.service.category.CategoryService;
import com.market.carmarketservice.service.message.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "/api/pri")
public class CategoryController {
    private final CategoryService categoryService;
    private final MessageService messageService;

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllCategory() {
        try {
            return new ResponseEntity<>(categoryService.getCategories(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(messageService.notFound(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getCategory(@PathVariable("id") int id) {
        if (categoryService.getCategory(id) != null)
            return new ResponseEntity<>(categoryService.getCategory(id), HttpStatus.OK);
        return new ResponseEntity<>(messageService.notFound(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/category", method = RequestMethod.POST)
    public ResponseEntity<Object> createCategory(@RequestBody Category category) {
        if (categoryService.createCategory(category))
            return new ResponseEntity<>(messageService.successes(), HttpStatus.OK);
        return new ResponseEntity<>(messageService.fail(), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateCategory(@RequestBody Category category, @PathVariable("id") int id) {
        if (categoryService.updateCategory(category, id))
            return new ResponseEntity<>(messageService.successes(), HttpStatus.OK);
        return new ResponseEntity<>(messageService.fail(), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteCategory(@PathVariable("id") int id) {
        if (categoryService.deleteCategory(id))
            return new ResponseEntity<>(messageService.successes(), HttpStatus.OK);
        return new ResponseEntity<>(messageService.fail(), HttpStatus.BAD_REQUEST);
    }
}
