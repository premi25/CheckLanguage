/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.checklanguage.bean;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Lacina implements Language {

    private LanguageEnum lenguage;

    public static final char[] LATINA_ALPHABET = new char[]{
        'W', 'X', 'w', 'x', 'J', 'K', 'Y', 'j', 'k', 'y', 'E', 'R', 'T', 'U', 'I', 'O', 'P', 'A', 'S',
        'D', 'F', 'G', 'H', 'L', 'Z', 'C', 'V', 'B', 'N', 'M',
        'e', 'r', 't', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f',
        'g', 'h', 'l', 'z', 'c', 'v', 'b', 'n', 'm', 'è', 'é', 'ì', 'ò', 'ù', 'à', 'ó', 'Ç', 'Ş', 'Ğ', 'Ü', 'ç', 'ğ', 'ş', 'ü', 'Å', 'å', 'Ä', 'ä', 'Ö', 'ö', 'Q', 'q', 'İ', 'ı'};

    public static final List<Character> ITALIAN_PREDYKTOR = Arrays.asList('è', 'é', 'ì', 'ò', 'ù', 'à', 'ó');

    public static final List<Character> TURKISH_PREDYKTOR = Arrays.asList('Ç', 'Ş', 'Ğ', 'Ü', 'ç', 'ğ', 'ş', 'ü', 'İ', 'ı');
    public static final List<Character> SHWEDISH_PREDYKTOR = Arrays.asList('Å', 'å', 'Ä', 'ä');
    public static final List<Character> SHWED_TURK_PREDYKTOR = Arrays.asList('Ö', 'ö');
    public static final List<Character> SHWED_ITAL_PREDYKTOR = Arrays.asList('Q', 'q');

    public static final List<String> SHWEDISH_DIGRAM_PREDYKTOR = Arrays.asList("Dj", "dj", "Hj", "hj", "Gj", "gj", "Lj", "lj", "Kj", "kj", "Sj", "sj");
    public static final List<String> COMMON_DIGRAMS = Arrays.asList("Dj", "dj", "Hj", "hj", "Gj", "gj", "Lj", "lj", "Kj", "kj", "Sj", "sj", "Ch", "ch", "Gh", "gh", "Gl", "gl", "Gn", "gn", "Sc", "sc", "Ng", "ng");

    private int number=0;

    private final Map<Character, Integer> lacinaAlphabet = new HashMap<>();
    private final Map<String, Integer> digramsMap = new HashMap<>();
    private final Map<String, Integer> alphabetMap = new HashMap<>();
    protected final InputStreamReader plik;

    public Lacina(InputStreamReader plik, char c) throws IOException {
        this.plik = plik;
        lenguage = null;
        number++;
        for (int i = 0; i < LATINA_ALPHABET.length; i++) {
            if (LATINA_ALPHABET[i] == c) {
                lacinaAlphabet.put(LATINA_ALPHABET[i], 1);
            } else {
                lacinaAlphabet.put(LATINA_ALPHABET[i], 0);
            }
        }
        for (String singleDigram : COMMON_DIGRAMS) {
            digramsMap.put(singleDigram, 0);
        }
        if (SHWED_ITAL_PREDYKTOR.contains(c)) {
            lenguage = LanguageEnum.SwedishItalian;
        }
        if (SHWED_TURK_PREDYKTOR.contains(c)) {
            lenguage = LanguageEnum.SwedishTurkish;
        }
        if (SHWEDISH_PREDYKTOR.contains(c)) {
            lenguage = LanguageEnum.Swedish;
        }
        if (ITALIAN_PREDYKTOR.contains(c)) {
            lenguage = LanguageEnum.Italian;
        }
        if (TURKISH_PREDYKTOR.contains(c)) {
            lenguage = LanguageEnum.Turkish;
        }

    }

    private void prepareMapsForItalian() {
        List<Character> italianAlphabet = Arrays.asList(
                'W', 'X', 'w', 'x', 'J', 'K', 'Y', 'j', 'k', 'y', 'E', 'R', 'T', 'U', 'I', 'O', 'P', 'A', 'S',
                'D', 'F', 'G', 'H', 'L', 'Z', 'C', 'V', 'B', 'N', 'M',
                'e', 'r', 't', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f',
                'g', 'h', 'l', 'z', 'c', 'v', 'b', 'n', 'm', 'è', 'é', 'ì', 'ò', 'ù', 'à', 'ó', 'Q', 'q');

        List<String> italianDigrams = Arrays.asList("Ch", "ch", "Gh", "gh", "Gl", "gl", "Gn", "gn", "Sc", "sc", "Ng", "ng");

        for (Character letter : italianAlphabet) {
            alphabetMap.put(String.valueOf(letter), lacinaAlphabet.get(letter));
        }
        for (String digram : italianDigrams) {
            alphabetMap.put(digram, digramsMap.get(digram));
        }
        alphabetMap.put("Quantity", number);

    }

    private void prepareMapsForSwedish() {
        List<Character> shwedskiAlphabet = Arrays.asList(
                'W', 'X', 'w', 'x', 'J', 'K', 'Y', 'j', 'k', 'y', 'E', 'R', 'T', 'U', 'I', 'O', 'P', 'A', 'S',
                'D', 'F', 'G', 'H', 'L', 'Z', 'C', 'V', 'B', 'N', 'M',
                'e', 'r', 't', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f',
                'g', 'h', 'l', 'z', 'c', 'v', 'b', 'n', 'm', 'Å', 'å', 'Ä', 'ä', 'Ö', 'ö', 'Q', 'q');

        for (Character letter : shwedskiAlphabet) {
            alphabetMap.put(String.valueOf(letter), lacinaAlphabet.get(letter));
        }
        for (String digram : COMMON_DIGRAMS) {
            alphabetMap.put(digram, digramsMap.get(digram));
        }
        alphabetMap.put("Quantity", number);

    }

    private void prepareMapsForTurkish() {
        List<Character> turkishAlphabet = Arrays.asList(
                'J', 'K', 'Y', 'j', 'k', 'y', 'E', 'R', 'T', 'U', 'I', 'O', 'P', 'A', 'S',
                'D', 'F', 'G', 'H', 'L', 'Z', 'C', 'V', 'B', 'N', 'M',
                'e', 'r', 't', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f',
                'g', 'h', 'l', 'z', 'c', 'v', 'b', 'n', 'm', 'Ç', 'Ş', 'Ğ', 'Ü', 'ç', 'ğ', 'ş', 'ü', 'Ö', 'ö', 'İ', 'ı');

        List<String> turkishDigrams = Arrays.asList("Ch", "ch", "Gh", "gh", "Gl", "gl", "Gn", "gn", "Sc", "sc", "Ng", "ng");

        for (Character letter : turkishAlphabet) {
            alphabetMap.put(String.valueOf(letter), lacinaAlphabet.get(letter));
        }
        for (String digram : turkishDigrams) {
            alphabetMap.put(digram, digramsMap.get(digram));
        }
        alphabetMap.put("Quantity", number);

    }

    public void readFile() {
        int c;
        try {

            String singleDigram = null;
            while ((c = plik.read()) != -1) {

                if (singleDigram == null) {
                    singleDigram = String.valueOf((char) c);
                } else {
                    singleDigram += String.valueOf((char) c);

                    if (SHWEDISH_DIGRAM_PREDYKTOR.contains(singleDigram) && (lenguage == null || lenguage.equals(LanguageEnum.SwedishItalian) || lenguage.equals(LanguageEnum.SwedishTurkish))) {
                        lenguage = LanguageEnum.Swedish;

                    }

                    if (COMMON_DIGRAMS.contains(singleDigram)) {
                        digramsMap.put(singleDigram, (digramsMap.get(singleDigram)) + 1);
                        singleDigram = null;

                    } else {
                        singleDigram = String.valueOf((char) c);

                    }

                }

                for (int i = 0; i < LATINA_ALPHABET.length; i++) {
                    if (LATINA_ALPHABET[i] == (char) c) {
                        lacinaAlphabet.put((char) c, (lacinaAlphabet.get((char) c)) + 1);

                        number++;

                    }

                    if (SHWED_ITAL_PREDYKTOR.contains((char) c)) {

                        if (lenguage == null) {
                            lenguage = LanguageEnum.SwedishItalian;
                        } else if (lenguage.equals(LanguageEnum.SwedishTurkish)) {
                            lenguage = LanguageEnum.Swedish;
                        }

                    }

                    if (SHWED_TURK_PREDYKTOR.contains((char) c)) {
                        if (lenguage == null) {
                            lenguage = LanguageEnum.SwedishTurkish;
                        } else if (lenguage.equals(LanguageEnum.SwedishItalian)) {
                            lenguage = LanguageEnum.Swedish;
                        }
                    }
                    if (SHWEDISH_PREDYKTOR.contains((char) c) && (lenguage == null || lenguage.equals(LanguageEnum.SwedishItalian) || lenguage.equals(LanguageEnum.SwedishTurkish))) {
                        lenguage = LanguageEnum.Swedish;
                    }
                    if (ITALIAN_PREDYKTOR.contains((char) c) && (lenguage == null || lenguage.equals(LanguageEnum.SwedishItalian))) {
                        lenguage = LanguageEnum.Italian;
                    }
                    if (TURKISH_PREDYKTOR.contains((char) c) && (lenguage == null || lenguage.equals(LanguageEnum.SwedishTurkish))) {
                        lenguage = LanguageEnum.Turkish;
                    }
                }

            }
        } catch (IOException ex) {
            Logger.getLogger(Lacina.class.getName()).log(Level.SEVERE, null, ex);
        }

        

        if (lenguage.equals(LanguageEnum.Italian)) {
            prepareMapsForItalian();
        } else if (lenguage.equals(LanguageEnum.Turkish)) {
            prepareMapsForTurkish();
        } else if (lenguage.equals(LanguageEnum.Swedish)) {
            prepareMapsForSwedish();
        }
        

    }

 

    @Override
    public LanguageEnum getLenguage() {
        return lenguage;
    }

    @Override
    public Map<String, Integer> getAlphabet() {
        return alphabetMap;
    }

}
