package jarrdie.eolconverter.usecase;

enum EolConversion {
    CR, LF, CRLF
}

public class EolDataConverter {

    private EolConversion eolConversion;

    public EolDataConverter(EolConversion eolConversion) {
        this.eolConversion = eolConversion;
    }

    /*
     first time -> bom
     with bom -> enconding
       no bom -> infer -> encoding
     encoding -> cr lf
     convert
     write output + length
     */
    public void convert(byte[] data, int dataLength, byte[] outputData, Integer outputLength) {

    }

}
