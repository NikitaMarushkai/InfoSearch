public interface Parser {

    void parse(int depth, String url, WhatToParse inOut, String fileName) throws Exception;

}
