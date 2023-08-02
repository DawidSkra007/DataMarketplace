package com.example.tldr.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import com.example.tldr.Model.Dataset;

import com.example.tldr.SQLServer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    // @Autowired
    ArrayList<Dataset> datasets = new ArrayList<Dataset>();
    HashMap<Integer, Dataset> datasetsMap = new HashMap<Integer, Dataset>();

    SQLServer database = new SQLServer();

    public AdminController() throws SQLException {

    }

    @PostMapping("/admin")
    public String addDataset(Model model, Dataset dataset, RedirectAttributes redirAttrs, HttpSession session) throws SQLException, IOException
    {

        LocalDateTime time = LocalDateTime.now();
        dataset.setTimestamp(time);

        System.out.println("adding dataset " + dataset);

        // adding to database
        database.addDataset(dataset.getTitle(), dataset.getDescription(), dataset.getPrice_per_point(),
                dataset.getAvailable_dataPoints(), dataset.getMinQuantity(),
                dataset.getMinQuantity());

        redirAttrs.addFlashAttribute("success", "Dataset added: "+dataset);
        return "redirect:/admin";
    }

    @PostMapping("/hide_dataset")
    public String deleteDataset(String id, HttpServletResponse response,RedirectAttributes redirAttrs,HttpSession session) throws SQLException, IOException {
        database.hideDataset(id);
        redirAttrs.addFlashAttribute("successHide", "Dataset w/ID:"+id+" hidden");
        return "redirect:/admin";
    }

    @PostMapping("/edit_dataset")
    public String editDataset(Model model, Dataset dataset, HttpServletResponse response,HttpSession session, RedirectAttributes redirAttrs) throws IOException, SQLException {
        Dataset temp = dataset;
        database.editDataset(dataset);
        redirAttrs.addFlashAttribute("successEdit", "Dataset w/ID:"+dataset.getId()+" edited");
        return "redirect:/admin";
    }

    @GetMapping("/getDatasets")
    @ResponseBody
    public String getDatasets(Model model) {
        try {
            datasets = database.getDatasets(0);
            model.addAttribute("datasets", datasets);

            String out = "";
            for (int i = 0; i < Math.min(10, datasets.size()); i++) {
                out += "<h1>" + datasets.get(i).getTitle() + "</h1>";
            }
            return out;
        } catch (SQLException e) {
            System.err.println("Error while getting the datasets.\n " + e.getMessage());
            e.printStackTrace();
        }
        return "<h1> Nothing yet </h1>";

    }

}
