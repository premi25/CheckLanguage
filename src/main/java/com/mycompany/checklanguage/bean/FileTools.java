/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.checklanguage.bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class FileTools {

    public static void addToJSON(Language language) {
        ObjectMapper mapper = new ObjectMapper();

        try {

            File f = new File(language.getLenguage() + ".json");

            if (f.exists()) {
                List<Map<String, Integer>> temp = mapper.readValue(f, ArrayList.class);
                temp.add(language.getAlphabet());
                mapper.writeValue(f, temp);

            } else {
                List<Map<String, Integer>> temp = new ArrayList<>();
                temp.add(language.getAlphabet());
                mapper.writeValue(f, temp);
            }
        } catch (Exception e) {
        }

    }

    public static Language readFromFile(String path) {
        FileInputStream f = null;
        try {
            f = new FileInputStream(path);
            InputStreamReader plikWe = new InputStreamReader(f, "UTF-8");
            boolean isCirilic = false;
            boolean isLatina = false;

            int c;
            // odczyt pliku znak po znaku i wyświetlenie na ekranie monitora
            while ((c = plikWe.read()) != -1 && !isCirilic && !isLatina) { // jeżeli c = -1 to koniec pliku

                for (int i = 0; i < Cirilic.CIRILIC_ALPHABET.length; i++) {

                    if (Cirilic.CIRILIC_ALPHABET[i] == (char) c) {
                        isCirilic = true;
                    }
                }

                for (int i = 0; i < Lacina.LATINA_ALPHABET.length; i++) {
                    if (Lacina.LATINA_ALPHABET[i] == (char) c) {
                        isLatina = true;
                    }
                }

                if (isCirilic) {
                    Cirilic ukrainian = new Cirilic(plikWe, (char) c);
                    ukrainian.readFile();
                    return ukrainian;

                } else if (isLatina) {
                    Lacina lacina = new Lacina(plikWe, (char) c);
                    lacina.readFile();
                    return lacina;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileTools.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileTools.class.getName()).log(Level.SEVERE, null, ex);
        } finally { // klauzula finally służy do wykonania instrukcji
            // niezależnie od tego kiedy i w jaki sposób (normalnie lub
            // przez wyjątek) zostało zakończone wykonywanie bloku try
            if (f != null) {
                try {
                    f.close(); // zamknięcie pliku
                } catch (IOException ex) {
                    Logger.getLogger(FileTools.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return null;
    }

}
