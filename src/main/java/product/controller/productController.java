package product.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import product.entity.Product;
import product.service.productService;

@Controller
@RequestMapping("/aaa")
public class productController {
	@Autowired
	private productService productservice;
	
	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("list",productservice.getAll());
		return "add";
	}
	@PostMapping("/pan")
	@ResponseBody
	public HashMap<String,Object> pan(HttpServletRequest request) {
		HashMap<String,Object> map=new HashMap<String, Object>();
		Integer quantity=Integer.parseInt(request.getParameter("quantity"));
		String productName=request.getParameter("productName");
		System.out.println(productName);
		System.out.println(quantity);
		Integer aInteger=productservice.selectOne(productName).getQuantity();
		Boolean pan = true;
		if(quantity>aInteger) {
			pan=false;
		}
		
		map.put("aInteger", aInteger);
		map.put("pan",pan);
		return map;
	}
}
