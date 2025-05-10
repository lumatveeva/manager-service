package ru.lumat.managerservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.lumat.managerservice.clients.ProductsRestClient;
import ru.lumat.managerservice.entity.Product;
import ru.lumat.managerservice.exception.BadRequestException;
import ru.lumat.managerservice.payload.NewProductPayload;

@Controller
@RequestMapping("catalogue/products")
@RequiredArgsConstructor
public class ProductsController {

    private final ProductsRestClient client;

    @GetMapping("list")
    public String getProductsList(Model model) {
        model.addAttribute("products", client.findAllProducts());
        return "catalogue/products/list";
    }

    @GetMapping("/create")
    public String getNewProductPage() {
        return "catalogue/products/new_product";
    }

    @PostMapping("/create")
    public String createProduct(NewProductPayload payload,
                                Model model) {
        try {
            Product product = client.createProduct(payload.title(), payload.details());
            return "redirect:/catalogue/products/%d".formatted(product.id());
        } catch (BadRequestException exception) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", exception.getErrors());
            return "catalogue/products/new_product";
        }
    }
}
