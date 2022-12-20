package com.example.todolist.config;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class Helpers {

    public static Optional<String> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return Optional.of(authentication.getName()) ;
        }
        return Optional.empty();
    }

    public static String getUserFolderName() {
        var folderPath = "./userFiles/" + getCurrentUser().orElseThrow(IllegalStateException::new);
        String s = folderPath + "/";
        if (!Files.exists(Path.of(folderPath))) {
            try {
                Files.createDirectory(Path.of(folderPath));
                return s;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return s;
    }
}
