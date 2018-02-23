import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class ParserImpl implements Parser {

    @Override
    public void parse(int depth, String url, WhatToParse inOut) throws Exception {
        Map<String, Set<String>> elements = new ConcurrentHashMap<>();
        elements.put(url, this.getInitialSet(url, inOut));
        Map<String, Set<String>> s = this.retrieveAllLinks(depth, 0, elements, url, inOut);
        Set<String> linksSet = new HashSet<>();
        for (Map.Entry<String, Set<String>> entry : s.entrySet()) {
            linksSet.add(entry.getKey());
        }
        // пройтись по сету с вершинами, если в мапе есть вершина - нужно ставить 0, иначе 1
        int[][] adjacency = new int[linksSet.size()][linksSet.size()];

        try (FileWriter writer = new FileWriter("C:\\Programming\\infosearch\\matrix.csv")) {
            int i = 0;
            for (String line : linksSet) {
                int j = 0;
                if (s.get(line) != null) {
                    for (String row : linksSet) {
                        if (s.get(line).contains(row)) {
                            adjacency[i][j] = 1;
                        } else {
                            adjacency[i][j] = 0;
                        }
                        writer.append(String.valueOf(adjacency[i][j]));
                        writer.append(",");
                        j++;
                    }
                }
                writer.append("\n");
                i++;
            }
        }
        System.out.println(adjacency.length);
        System.out.println(adjacency[0].length);
        int length = 0;
        for (int[] a : adjacency) {
            for (int b : a) {
                if (b == 1) {
                    length++;
                }
            }
        }
        System.out.println("ones: " + length);
        System.out.println(s);
        System.out.println(s.size());
    }

    private Document preparePage(String url) throws Exception {
        try {
            Connection.Response response = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) " +
                            "Chrome/63.0.3239.132 Safari/537.36")
                    .timeout(30000)
                    .followRedirects(true)
                    .execute();
            if (response.statusCode() == 200) {
                return response.parse();
            } else {
                throw new Exception("Wrong response code, check everything");
            }
        } catch (Exception e) {
            return null;
        }

    }


    private Map<String, Set<String>> retrieveAllLinks(int initialDepth, int depth, Map<String, Set<String>> links,
                                                      String url, WhatToParse inOut) throws RuntimeException {
        if (depth < initialDepth) {
            Map<String, Set<String>> local_links = new ConcurrentHashMap<>();
            Stream<String> linksStream = links.get(url).parallelStream();
            linksStream.forEach(link -> {
                try {
                    links.put(link, this.getInitialSet(link, inOut));
                    local_links.putAll(this.retrieveAllLinks(initialDepth, depth + 1, links, link, inOut));
                } catch (Exception e){
                    e.printStackTrace();
                }
            });
//            for (String link : links.get(url)) {
//                links.put(link, this.getInitialSet(link, inOut));
//                local_links.putAll(this.retrieveAllLinks(initialDepth, depth + 1, links, link, inOut));
//            }
            return local_links;
        } else {
            return links;
        }
    }

    private Set<String> getInitialSet(String url, WhatToParse inOut) throws Exception {
        Document doc = this.preparePage(url);
        Elements currentElements;
        Set<String> links = new HashSet<>();
        try {
            currentElements = doc.body().select("a[href]");
            for (Element element : currentElements) {
                switch (inOut) {
                    case ALL:
                        links.add(element.attr("abs:href"));
                        break;
                    case INNER:
                        if (element.attr("abs:href").contains(url)) {
                            links.add(element.attr("abs:href"));
                        }
                        break;
                    case OUTER:
                        if (!element.attr("abs:href").contains(url)) {
                            links.add(element.attr("abs:href"));
                        }
                        break;
                }
            }
        } catch (NullPointerException e) {
            return links;
        }
        return links;
    }
}
