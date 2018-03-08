package ru.marushkai.infosearch.parser.interfaces;

import ru.marushkai.infosearch.parser.enums.WhatToParse;

public interface Parser {

    void parse(int matrixSize, String url, WhatToParse inOut, String fileName) throws Exception;

}
