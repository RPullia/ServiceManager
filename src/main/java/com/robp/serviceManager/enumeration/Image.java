package com.robp.serviceManager.enumeration;

import lombok.Getter;

@Getter
public enum Image {

    IMAGE_LEAF("leaf.png");

    private final String image;

    Image(String image) {
        this.image = image;
    }

}
