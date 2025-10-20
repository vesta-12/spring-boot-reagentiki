package com.example.springbootdemo.controller;
import com.example.springbootdemo.model.Reagent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Arrays;
import java.util.List;

@Controller
public class ChemicalController {
    private List<Reagent> getItemsList() {
        return Arrays.asList(
                new Reagent(1L, "Hydrochloric Acid", "HCl, 37% solution, high purity", 45.99, "https://image.made-in-china.com/2f0j00giRokElaFuqt/Mining-Industrial-Grade-Uses-HCl-Hydrochloric-Acid-31-Prices.jpg"),
                new Reagent(2L, "Sulfuric Acid", "H₂SO₄, 98% concentration", 67.50, "https://sc04.alicdn.com/kf/Hd008a0c052eb4eaca2587a508f43936dQ.jpg"),
                new Reagent(3L, "Sodium Hydroxide", "NaOH, pellets, reagent grade", 32.75, "https://www.masiyelabs.co.za/wp-content/uploads/2020/02/Sodium-Hydroxide-AR-500g.jpeg"),
                new Reagent(4L, "Silver Nitrate", "AgNO₃, crystalline, 99.8% purity", 89.99, "https://sc04.alicdn.com/kf/Hcb3fa8fd0fdb48e784759023f615cc44F/251083825/Hcb3fa8fd0fdb48e784759023f615cc44F.jpg"),
                new Reagent(5L, "Potassium Permanganate", "KMnO₄, oxidizing agent", 28.45, "https://cdn.shopify.com/s/files/1/0003/3406/9819/products/C6560-25g_8216435a-4f69-40d9-aa93-b95dbe269ebd_1024x.jpg?v=1619698092")
        );
    }

    @GetMapping("/lab2")
    public String lab2Page(Model model) {
        model.addAttribute("itemsList", getItemsList());
        return "items";
    }

}