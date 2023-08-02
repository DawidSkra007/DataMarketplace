package com.example.tldr.Controller;

import com.example.tldr.Model.*;
import com.example.tldr.SQLServer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@Controller
public class NavigationController {
    SQLServer database = new SQLServer();

    public NavigationController() throws SQLException {

    }

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        if (session.getAttribute("loggedIn") == null) {
            session.setAttribute("loggedIn", false);
        }
        if (session.getAttribute("account") != null) {
            Account acc = (Account) session.getAttribute("account");
            model.addAttribute("account", acc);
        }
        boolean loggedIn = (boolean) session.getAttribute("loggedIn");

        model.addAttribute("loggedIn", loggedIn);
        return "index.html";
    }

    @GetMapping("/payment")
    public String payment() {
        return "payment.html";
    }

    @GetMapping("/catalogue")
    public String catalogue(Model model, HttpSession session) throws SQLException {
        if (session.getAttribute("cart") == null) {
            session.setAttribute("cart", new ShoppingCart());
        }
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        boolean loggedIn = (boolean) session.getAttribute("loggedIn");
        model.addAttribute("loggedIn", loggedIn);
        model.addAttribute("cart", cart);
        model.addAttribute("datasets", database.getDatasets(1));
        return "catalogue.html";
    }

    @GetMapping("/account")
    public String account() {
        return "account.html";
    }

    @GetMapping("/user")
    public String user(Model model, HttpSession session) throws SQLException {
        Account user = (Account) session.getAttribute("account");
        boolean loggedIn = (boolean) session.getAttribute("loggedIn");
        model.addAttribute("loggedIn", loggedIn);
        model.addAttribute("user", user);
        model.addAttribute("purchases", database.getAllShoppingCarts(String.valueOf(user.getId())));
        return "user.html";
    }

    @GetMapping("/shoppingcart")
    public String shoppingCart(Model model, HttpSession session) {
        if (session.getAttribute("cart") == null) {
            session.setAttribute("cart", new ShoppingCart());
        }
        boolean loggedIn = (boolean) session.getAttribute("loggedIn");
        model.addAttribute("loggedIn", loggedIn);
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        model.addAttribute("cart", cart);
        model.addAttribute("products", cart.getCart());
        return "shoppingcart.html";
    }

    @PostMapping("/shoppingcart")
    public String removeFromCart(Model model, HttpSession session, Dataset dataset,
            @RequestParam(defaultValue = "-1") int quantity, RedirectAttributes redirAttrs) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        int index = cart.getCart().indexOf(dataset);
        if (quantity == -1) {
            cart.removeDataSet(index);
            redirAttrs.addFlashAttribute("success", "Dataset removed.");
        } else {
            redirAttrs.addFlashAttribute("success", "Quantity successfully updated from "
                    + cart.getCart().get(index).getQuantity() + " to " + quantity + ".");
            cart.updateQuantityAt(index, quantity);
        }
        boolean loggedIn = (boolean) session.getAttribute("loggedIn");
        model.addAttribute("loggedIn", loggedIn);
        session.setAttribute("cart", cart);
        model.addAttribute("cart", cart);
        model.addAttribute("products", cart.getCart());
        return "redirect:/shoppingcart";
    }

    @PostMapping("/catalogue")
    public String addToCart(Dataset dataset, int quantity, Model model, HttpSession session,
            RedirectAttributes redirAttrs) throws SQLException {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        ProductDataset pd = new ProductDataset(dataset, quantity);
        if (cart.getCart().contains(pd)) {
            int index = cart.getCart().indexOf(pd);
            cart.updateQuantityAt(index, (quantity + cart.getCart().get(index).getQuantity()));
        } else {
            cart.addDataSet(pd);
        }
        boolean loggedIn = (boolean) session.getAttribute("loggedIn");
        model.addAttribute("loggedIn", loggedIn);
        redirAttrs.addFlashAttribute("success", "Product added to cart.");
        session.setAttribute("cart", cart);
        model.addAttribute("cart", cart);
        model.addAttribute("datasets", database.getDatasets(1));
        return "redirect:/catalogue";
    }

    @GetMapping("/create_account")
    public String createAccount() {
        return "create_account.html";
    }

    @GetMapping("/signIn")
    public String signIn() {
        return "signin.html";
    }

    @GetMapping("/payment_succ")
    public String payment_succ(HttpSession session) throws SQLException {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        Account user = (Account) session.getAttribute("account");
        database.addPurchases(cart, user.getId());
        session.setAttribute("cart", new ShoppingCart());
        return "payment_succ.html";
    }

    @GetMapping("/admin")
    public String admin(Model model, HttpServletResponse response, HttpSession session) throws SQLException, IOException {
        Account acc = (Account) session.getAttribute("account");

        if (acc.getId() == 0) {
            model.addAttribute("purchases", database.getAllShoppingCarts("0"));
            model.addAttribute("datasets", database.getDatasets(0));
            model.addAttribute("orderStats", database.getOrderStats());
            return "admin.html";
        }

        return "index.html";
    }

    @PostMapping("/addAccount")
    public @ResponseBody void addAccount(Model model, HttpServletResponse response,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password) {
        try {
            Account acc = new Account(name, email, password);
            database.addAccount(acc.getName(), acc.getEmail(), acc.getPassword(), acc.getDt());// adding to database
            response.sendRedirect("/");// send user straight to index after making an account
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/Signin")
    public @ResponseBody void signIn(Model model, HttpServletResponse response,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "password") String password,
            HttpSession session) {

        try {
            Account userAcc = database.signIn(name, password);

            if (userAcc != null) {
                session.setAttribute("account", userAcc);
                session.setAttribute("loggedIn", true);
                System.out.println("USER " + userAcc.getName() + " SIGNED IN");
                response.sendRedirect("/image");
            } else {
                System.out.println("INVALID USER NAME OR PASSWORD");
                response.sendRedirect("/signIn");
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/userCheck")
    public String userCheck(HttpSession session, Model model, HttpServletResponse response) throws IOException {
        Account acc = (Account) session.getAttribute("account");
        if (acc != null) {// if user signed in navigate to index page
            if (acc.getId() == 0) {
                response.sendRedirect("/admin");
            } else {
                response.sendRedirect("/user");
            }
        } else {
            return "account.html";
        }
        return "index.html";
    }

    @GetMapping("/logout")
    public String logoout(HttpSession session, HttpServletResponse response, RedirectAttributes redirAttrs) throws IOException {
        session.setAttribute("loggedIn", false);
        session.setAttribute("account", null);
        redirAttrs.addFlashAttribute("logout", "You have logged out.");
        return "redirect:/";
    }

    @GetMapping("/image")
    public String image(Model model, HttpSession session) {
        Account user = (Account) session.getAttribute("account");
        model.addAttribute("account", user);
        return "Image.html";
    }

    @GetMapping("/dataset/{id}")
    public String view(Model model, @PathVariable int id, HttpSession session) throws SQLException {
        boolean loggedIn = (boolean) session.getAttribute("loggedIn");
        model.addAttribute("loggedIn", loggedIn);
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        model.addAttribute("cart", cart);
        model.addAttribute("dataset", database.getDataset(id));
        return "dataset.html";
    }

    @GetMapping("/order/{id}")
    public String order(Model model, @PathVariable int id, HttpSession session) throws SQLException {
        boolean loggedIn = (boolean) session.getAttribute("loggedIn");
        Account user = (Account) session.getAttribute("account");
        model.addAttribute("loggedIn", loggedIn);
        model.addAttribute("account", user);
        ShoppingCart cart = database.getShoppingCart(id);
        model.addAttribute("cart", cart);
        model.addAttribute("products", cart.getCart());
        return "order.html";
    }

    @GetMapping("/product/{id}")
    public String viewQuantity(Model model, @PathVariable int id, HttpSession session, Dataset dataset) {
        boolean loggedIn = (boolean) session.getAttribute("loggedIn");
        model.addAttribute("loggedIn", loggedIn);
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        int index = cart.getCart().indexOf(dataset);
        model.addAttribute("cart", cart);
        model.addAttribute("product", cart.getCart().get(index));
        return "product.html";
    }

    @PostMapping("/approve")
    public void approve(@RequestParam(value = "id") String id, HttpServletResponse response)
            throws SQLException, IOException {
        database.approveOrder(id);
        response.sendRedirect("/admin");
    }

}
