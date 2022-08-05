package com.example.cardealer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ImageUploadController {

    //Save the uploaded file to this folder
    private static final String UPLOADED_FOLDER = "target/classes/static/uploaded/";

    @PostMapping("/upload") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("img") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

            redirectAttributes.addFlashAttribute("img",
                    "/uploaded/" + file.getOriginalFilename());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/uploadStatus";
    }

}
