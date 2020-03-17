import org.jsoup.Jsoup;
import org.junit.jupiter.api.Assertions;
import java.io.IOException;

class WebsiteTest {

    @org.junit.jupiter.api.Test
    void shouldReturnAListSizeBiggerThan1forJquery() throws IOException{
        String googleResult = Jsoup.connect("https://www.gov.uk/foreign-travel-advice/albania").userAgent("Mozilla/5.0").get().html();
        Assertions.assertNotEquals(0, Website.getJsLinks("jquery", googleResult));
    }

    @org.junit.jupiter.api.Test
    void shouldReturnAListSizeBiggerThan1forAngular() throws IOException{
        String googleResult = Jsoup.connect("https://www.gov.uk/foreign-travel-advice/albania").userAgent("Mozilla/5.0").get().html();
        Assertions.assertNotEquals(1, Website.getJsLinks("angular", googleResult));
    }

    @org.junit.jupiter.api.Test
    void shouldReturnAListSizeBiggerThan1forReact() throws IOException{
        String googleResult = Jsoup.connect("https://www.gov.uk/foreign-travel-advice/albania").userAgent("Mozilla/5.0").get().html();
        Assertions.assertNotEquals(1, Website.getJsLinks("react", googleResult));
    }


}
