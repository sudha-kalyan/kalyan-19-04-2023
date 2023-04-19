package com.raithanna.dairy.RaithannaDairy.controller;

import com.raithanna.dairy.RaithannaDairy.models.supplier;
import com.raithanna.dairy.RaithannaDairy.repositories.PurchaseOrderRepository;
import com.raithanna.dairy.RaithannaDairy.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class purchaseExcelController {
     @Autowired
     private PurchaseOrderRepository purchaseOrderRepository;
     @Autowired
     private SupplierRepository supplierRepository;
     @GetMapping("/excel")
     public String purchaseExcelOrderForm(Model model, HttpSession session) {
          if (session.getAttribute("loggedIn").equals("yes")) {
               List<supplier> Suppliers = supplierRepository.findByOrderByIdDesc();
               purchaseExcelController pe = new purchaseExcelController();
               model.addAttribute("purchase", pe);
               model.addAttribute("supplier", Suppliers);
               return "purchaseExcel";
          }
          List messages = new ArrayList<>();
          messages.add("Login First");
          model.addAttribute("messages", messages);
          return "redirect:/loginPage";
     }

}
