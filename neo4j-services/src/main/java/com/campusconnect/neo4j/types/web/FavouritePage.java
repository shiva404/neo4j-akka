package com.campusconnect.neo4j.types.web;

import java.util.ArrayList;
import java.util.List;

public class FavouritePage {

    List<Favourite> favourites;
    int offset;
    int size;

    public FavouritePage() {

    }

    public FavouritePage(int size, int offset, List<Favourite> favourite) {
        this.size = size;
        this.offset = offset;
        this.favourites = favourite;
    }

    public List<Favourite> getFavourite() {
        return favourites;
    }

    public List<Favourite> getFavourites() {
        if (favourites == null)
            favourites = new ArrayList<>();
        return favourites;
    }

    public int getOffset() {
        return offset;
    }

    public int getSize() {
        return size;
    }

    public void setFavourite(List<Favourite> favourite) {
        this.favourites = favourite;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
