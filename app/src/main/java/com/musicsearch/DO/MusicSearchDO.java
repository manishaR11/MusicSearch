package com.musicsearch.DO;

/**
 * Created by Manisha on 7/21/2015.
 */
public class MusicSearchDO {
    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    private int resultCount=0;
    public MusicDataDO[] results=null;
    public MusicDataDO[] getResults() {
        return results;
    }

    public void setResults(MusicDataDO[] results) {
        this.results = results;
    }
}

