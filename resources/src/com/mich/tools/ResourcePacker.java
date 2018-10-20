package com.mich.tools;


import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class ResourcePacker {

    public static void main(String[] args) {
        String inputDir = args[0];
        String outputDir = args[1];
        String packFileName = args[2];
        TexturePacker.process(inputDir, outputDir, packFileName);
    }

}
