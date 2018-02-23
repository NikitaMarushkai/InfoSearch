import org.jsoup.nodes.Document;

import java.io.IOException;

public interface Parser {

    void parse(int depth, String url, WhatToParse inOut) throws Exception;

}
