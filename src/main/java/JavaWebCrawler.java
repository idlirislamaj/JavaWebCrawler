import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URLEncoder;
import java.util.*;

public class JavaWebCrawler {
    public static void main(String[] args) {
        Set<Elements> elementsSet = new HashSet<>();
        List<Website> websiteList = new ArrayList<>();

        String encoding = "UTF-8";
        String stringToSearch = "scalable";

        try {
            Document googleResult = Jsoup.connect("https://www.google.com/search?q="+ URLEncoder.encode(stringToSearch,encoding)).userAgent("Mozilla/5.0").get();

            Elements elements = googleResult.getElementsByClass("kCrYT");
            for (Element element: elements){
                Elements div = element.getElementsByTag("a");
                elementsSet.add(div);
            }

            for (Elements element : elementsSet) {
                String linkHref;
                String link = element.attr("href");
                int index = link.indexOf("&");
                if (index != -1){
                    linkHref = (link.substring(0 , index)).substring(7);
                }else {linkHref= element.attr("href");}

                String linkText = element.select("a > div:first-child").text();
                if (linkText.equals("") || linkHref.equals("")){
                    continue;
                }
                websiteList.add(new Website(linkText, linkHref));
            }


           for (Website website: websiteList){
               System.out.println(website);
               website.setLinksToMap();
/*                System.out.println("React links identified " + website.getJsLinks("react"));
                System.out.println("Angular links identified " + website.getJsLinks("ajax"));
                System.out.println("Jquery links identified " + website.getJsLinks("jquery"));
                System.out.println("Meteor links identified " + website.getJsLinks("meteor"));
                System.out.println("Vue links identified " + website.getJsLinks("vue"));
                System.out.println("Backbone links identified " + website.getJsLinks("backbone"));*/

                System.out.println();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
