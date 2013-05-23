package com.marbl.rekeningrijders.website.bean;

//<editor-fold defaultstate="collapsed" desc="Imports">
import java.io.Serializable;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
//</editor-fold>

@Named
@RequestScoped
public class LanguageBean implements Serializable {

    private String languageCode;
    private Locale dutchLocale;
    private Locale englishLocale;

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;

        if (languageCode.equals("nl")) {
            FacesContext.getCurrentInstance().getViewRoot().setLocale(dutchLocale);
        } else if (languageCode.equals("en")) {
            FacesContext.getCurrentInstance().getViewRoot().setLocale(englishLocale);
        }
    }

    @PostConstruct
    public void postConstruct() {
        dutchLocale = new Locale.Builder().setLanguage("nl").build();
        englishLocale = new Locale.Builder().setLanguage("en").build();
        setLanguageCode("nl");
    }

    public void changeLanguageToNL() {
        setLanguageCode("nl");
    }

    public void changeLanguageToEN() {
        setLanguageCode("en");
    }
}
