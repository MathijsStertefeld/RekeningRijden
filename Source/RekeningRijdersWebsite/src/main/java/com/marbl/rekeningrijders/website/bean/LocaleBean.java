package com.marbl.rekeningrijders.website.bean;

//<editor-fold defaultstate="collapsed" desc="Imports">
import java.io.Serializable;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
//</editor-fold>

// This bean is used by template.xhtml.

@Named
@SessionScoped
@Stateful
public class LocaleBean implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    private Locale currentLocale;
    private Locale localeNL;
    private Locale localeEN;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public Locale getCurrentLocale() {
        return currentLocale;
    }

    public void setCurrentLocale(Locale currentLocale) {
        this.currentLocale = currentLocale;
        FacesContext.getCurrentInstance().getViewRoot().setLocale(currentLocale);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">
    @PostConstruct
    public void postConstruct() {
        localeNL = new Locale.Builder().setLanguage("nl").build();
        localeEN = new Locale.Builder().setLanguage("en").build();
        setCurrentLocale(localeNL);
    }

    public void changeLocaleToNL() {
        setCurrentLocale(localeNL);
    }

    public void changeLocaleToEN() {
        setCurrentLocale(localeEN);
    }
    //</editor-fold>
}
