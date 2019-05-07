/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.checklanguage.bean;

/**
 *
 * @author Admin
 */
public enum LanguageEnum {

    Swedish("Swedish"),
    Italian("Italian"),
    Turkish("Turkish"),
    Ukrainian("Ukrainian"),
    SwedishItalian("Swedish or Italian"),
    SwedishTurkish("Swedish or Turkish");

    private final String languageName;

    LanguageEnum(String languageName) {
        this.languageName = languageName;

    }

    public String getLanguageName() {
        return languageName;
    }

}
