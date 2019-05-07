/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.checklanguage.bean;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oleks
 */
public class Cirilic implements Language {

    private final LanguageEnum lenguage;
    public static final char[] CIRILIC_ALPHABET = new char[]{
        'А', 'Б', 'В', 'Г', 'Ґ', 'Д', 'Е', 'Є', 'Ж', 'З', 'К', 'Л',
        'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ',
        'Ь', 'Ю', 'Я', 'а', 'б', 'в', 'г', 'ґ', 'д', 'е', 'є', 'ж', 'з', 'и',
        'і', 'ї', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т',
        'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ь', 'ю', 'я','І','Ї'};

    protected final InputStreamReader plik;

    private final Map<Character, Integer> cirilicAlphabet = new HashMap<>();
    private int number;

    public Cirilic(InputStreamReader plik,char c) {
        this.plik = plik;
        number = 1;
        for (int i = 0; i < CIRILIC_ALPHABET.length; i++) {

            if(CIRILIC_ALPHABET[i]==c){
            cirilicAlphabet.put(CIRILIC_ALPHABET[i], 1);
            }else{
            cirilicAlphabet.put(CIRILIC_ALPHABET[i], 0);
            }

        }
        lenguage = LanguageEnum.Ukrainian;
    }

    public void readFile() {
        int c;
        try {
            while ((c = plik.read()) != -1) {

                for (int i = 0; i < CIRILIC_ALPHABET.length; i++) {
                    if (CIRILIC_ALPHABET[i] == (char) c) {
                        cirilicAlphabet.put((char) c, (cirilicAlphabet.get((char) c)) + 1);
                        number++;
                    }
                }

            }
        } catch (IOException ex) {
            Logger.getLogger(Cirilic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Map<String, Integer> getAlphabet() {
        Map<String, Integer> alphabetMap = new HashMap<>();
        for (Map.Entry<Character, Integer> entrySet : cirilicAlphabet.entrySet()) {
            alphabetMap.put(String.valueOf(entrySet.getKey()), entrySet.getValue());

        }
        alphabetMap.put("Quantity", number);

        return alphabetMap;
    }



    @Override
    public LanguageEnum getLenguage() {
        return lenguage;
    }

}
