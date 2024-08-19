package io.Petrov_Todor.exceptions.files;

import io.Petrov_Todor.User;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class filesMain {
    public static void main(String[] args) {

        File file = new File("scr/info.txt"); //--> Creo il riferimento dove sarà il file
        try {
            User aldo = new User("Aldo", "Stanzoni", 35, "Roma");
            FileUtils.writeStringToFile(file, aldo.getNome() + "-" + aldo.getCognome() + "-" + aldo.getEta() + "-" + aldo.getCitta() + System.lineSeparator(), StandardCharsets.UTF_8, true);

            String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            System.out.println(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
