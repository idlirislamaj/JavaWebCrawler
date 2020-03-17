import org.jsoup.Jsoup;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Website{
    private String  title;
    private String  link;
    private String html;
    Map<String, Integer> frequencyMap;


    public Website(String title, String link) throws IOException {
        this.title = title;
        this.link = link;
        this.html = Jsoup.connect(link).userAgent("Mozilla/5.0").get().html();
        this.frequencyMap = new HashMap<>();
    }

    @Override
    public String toString() {
        return "Website link found{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                '}';
    }

    //Used for searching in script links for a source string to identify libraries (Not called, just for testing)
    public static int getJsLinks(String src, String html){
        List<String> linksList = Jsoup.parse(html)
                .select("script")
                .parallelStream()
                .map(element -> element.attr("src"))
                .filter(source -> source.matches("(?i).*"+src+".*"))
                .collect(Collectors.toList());
        return linksList.size();
    }

    public List<String> getScriptLinks(){
        List<String> linksList = Jsoup.parse(html)
                .select("script")
                .parallelStream()
                .map(element -> element.attr("src"))
                .collect(Collectors.toList());
        return linksList;
    }

    public void setLinksToMap(){
        for (String link: getScriptLinks()){
            if (!link.equals("")){
                frequencyMap.put(link,Collections.frequency(getScriptLinks(),link));
                //System.out.println("Script-> "+ link + " is on this site "+);
        }}

        for (Map.Entry<String,Integer> entry : frequencyMap.entrySet()){
            System.out.println("Script => " + entry.getKey() +
                    ", Times found => " + entry.getValue());
        }
    }

}
