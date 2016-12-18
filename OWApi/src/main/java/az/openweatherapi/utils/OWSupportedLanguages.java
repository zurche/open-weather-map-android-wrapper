package az.openweatherapi.utils;

/**
 * Created by az on 13/10/16.
 * Open Weather API supports the following languages: English - en, Russian - ru, Italian - it,
 * Spanish - es (or sp), Ukrainian - uk (or ua), German - de, Portuguese - pt,
 * Romanian - ro, Polish - pl, Finnish - fi, Dutch - nl, French - fr,
 * Bulgarian - bg, Swedish - sv (or se), Chinese Traditional - zh_tw,
 * Chinese Simplified - zh (or zh_cn), Turkish - tr, Croatian - hr, Catalan - ca
 */

public enum OWSupportedLanguages {
    ENGLISH("en"),
    RUSSIAN("ru"),
    ITALIAN("it"),
    SPANISH("es"),
    ROMANIAN("ro"),
    POLISH("pl"),
    FINNISH("fi"),
    DUTCH("nl"),
    FRENCH("fr"),
    BULGARIAN("bg"),
    SWEDISH("sv"),
    CHINESE_T("zh_tw"),
    CHINESE_S("zh"),
    TURKISH("tr"),
    CROATIAN("hr"),
    CATALAN("ca");

    String language;

    OWSupportedLanguages(String languageLocale) {
        this.language = languageLocale;
    }

    public String getLanguageLocale() {
        return language;
    }
}
