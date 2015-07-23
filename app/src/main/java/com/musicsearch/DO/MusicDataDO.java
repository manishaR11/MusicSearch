package com.musicsearch.DO;

import java.net.URL;

/**
 * Created by Manisha on 7/21/2015.
 */
public class MusicDataDO {
    public     String wrapperType ="";
    public     String kind        ="";
    public    String collectionId="";
    public    long   trackId = 0;
    public     String  artistName ="";
    public    String collectionName = "";
    public     String trackName ="";
    public    String collectionCensoredName = "";
    public    String trackCensoredName= "";
    public     long  collectionArtistId = 0;
    public    URL collectionArtistViewUrl;
    public    URL       collectionViewUrl ;
    public    URL       trackViewUrl;
    public    URL       previewUrl;
    public    URL       artworkUrl30;
    public    URL       artworkUrl60;
    public    URL       artworkUrl100;
    public    float     collectionPrice;
    public    float     trackPrice;
    public    float     trackRentalPrice;
    public    float     collectionHdPrice;
    public    float     trackHdPrice;
    public    float     trackHdRentalPrice;
    public     String    releaseDate;
    public     String    collectionExplicitness;
    public     String    trackExplicitness;
    public     int       discCount;
    public     int      discNumber;
    public     int       trackCount;
    public     int       trackNumber;
    public     long      trackTimeMillis;
    public     String    country;
    public     String    currency;
    public     String   primaryGenreName;
    public      String   contentAdvisoryRating;
    public       String   longDescription="";
    public      URL     radioStationUrl;

        public String getWrapperType() {
            return wrapperType;
        }

        public void setWrapperType(String wrapperType) {
            this.wrapperType = wrapperType;
        }

        public String getKind() {
            return kind;
        }

        public void setKind(String kind) {
            this.kind = kind;
        }

        public String getCollectionId() {
            return collectionId;
        }

        public void setCollectionId(String collectionId) {
            this.collectionId = collectionId;
        }

        public String getArtistName() {
            return artistName;
        }

        public void setArtistName(String artistName) {
            this.artistName = artistName;
        }

        public long getTrackId() {
            return trackId;
        }

        public void setTrackId(long trackId) {
            this.trackId = trackId;
        }

        public String getCollectionName() {
            return collectionName;
        }

        public void setCollectionName(String collectionName) {
            this.collectionName = collectionName;
        }

        public String getTrackName() {
            return trackName;
        }

        public void setTrackName(String trackName) {
            this.trackName = trackName;
        }

        public String getCollectionCensoredName() {
            return collectionCensoredName;
        }

        public void setCollectionCensoredName(String collectionCensoredName) {
            this.collectionCensoredName = collectionCensoredName;
        }

        public String getTrackCensoredName() {
            return trackCensoredName;
        }

        public void setTrackCensoredName(String trackCensoredName) {
            this.trackCensoredName = trackCensoredName;
        }

        public long getCollectionArtistId() {
            return collectionArtistId;
        }

        public void setCollectionArtistId(long collectionArtistId) {
            this.collectionArtistId = collectionArtistId;
        }

        public URL getCollectionArtistViewUrl() {
            return collectionArtistViewUrl;
        }

        public void setCollectionArtistViewUrl(URL collectionArtistViewUrl) {
            this.collectionArtistViewUrl = collectionArtistViewUrl;
        }

        public URL getPreviewUrl() {
            return previewUrl;
        }

        public void setPreviewUrl(URL previewUrl) {
            this.previewUrl = previewUrl;
        }

        public float getTrackRentalPrice() {
            return trackRentalPrice;
        }

        public void setTrackRentalPrice(float trackRentalPrice) {
            this.trackRentalPrice = trackRentalPrice;
        }

        public URL getCollectionViewUrl() {
            return collectionViewUrl;
        }

        public void setCollectionViewUrl(URL collectionViewUrl) {
            this.collectionViewUrl = collectionViewUrl;
        }

        public URL getTrackViewUrl() {
            return trackViewUrl;
        }

        public void setTrackViewUrl(URL trackViewUrl) {
            this.trackViewUrl = trackViewUrl;
        }

        public URL getArtworkUrl30() {
            return artworkUrl30;
        }

        public void setArtworkUrl30(URL artworkUrl30) {
            this.artworkUrl30 = artworkUrl30;
        }

        public URL getArtworkUrl60() {
            return artworkUrl60;
        }

        public void setArtworkUrl60(URL artworkUrl60) {
            this.artworkUrl60 = artworkUrl60;
        }

        public URL getArtworkUrl100() {
            return artworkUrl100;
        }

        public void setArtworkUrl100(URL artworkUrl100) {
            this.artworkUrl100 = artworkUrl100;
        }

        public float getCollectionPrice() {
            return collectionPrice;
        }

        public void setCollectionPrice(float collectionPrice) {
            this.collectionPrice = collectionPrice;
        }

        public float getTrackPrice() {
            return trackPrice;
        }

        public void setTrackPrice(float trackPrice) {
            this.trackPrice = trackPrice;
        }

        public float getCollectionHdPrice() {
            return collectionHdPrice;
        }

        public void setCollectionHdPrice(float collectionHdPrice) {
            this.collectionHdPrice = collectionHdPrice;
        }

        public float getTrackHdPrice() {
            return trackHdPrice;
        }

        public void setTrackHdPrice(float trackHdPrice) {
            this.trackHdPrice = trackHdPrice;
        }

        public float getTrackHdRentalPrice() {
            return trackHdRentalPrice;
        }

        public void setTrackHdRentalPrice(float trackHdRentalPrice) {
            this.trackHdRentalPrice = trackHdRentalPrice;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
        }

        public String getCollectionExplicitness() {
            return collectionExplicitness;
        }

        public void setCollectionExplicitness(String collectionExplicitness) {
            this.collectionExplicitness = collectionExplicitness;
        }

        public String getTrackExplicitness() {
            return trackExplicitness;
        }

        public void setTrackExplicitness(String trackExplicitness) {
            this.trackExplicitness = trackExplicitness;
        }

        public int getTrackCount() {
            return trackCount;
        }

        public void setTrackCount(int trackCount) {
            this.trackCount = trackCount;
        }

        public int getDiscNumber() {
            return discNumber;
        }

        public void setDiscNumber(int discNumber) {
            this.discNumber = discNumber;
        }

        public int getDiscCount() {
            return discCount;
        }

        public void setDiscCount(int discCount) {
            this.discCount = discCount;
        }

        public long getTrackTimeMillis() {
            return trackTimeMillis;
        }

        public void setTrackTimeMillis(long trackTimeMillis) {
            this.trackTimeMillis = trackTimeMillis;
        }

        public int getTrackNumber() {
            return trackNumber;
        }

        public void setTrackNumber(int trackNumber) {
            this.trackNumber = trackNumber;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getPrimaryGenreName() {
            return primaryGenreName;
        }

        public void setPrimaryGenreName(String primaryGenreName) {
            this.primaryGenreName = primaryGenreName;
        }

        public String getContentAdvisoryRating() {
            return contentAdvisoryRating;
        }

        public void setContentAdvisoryRating(String contentAdvisoryRating) {
            this.contentAdvisoryRating = contentAdvisoryRating;
        }

        public String getLongDescription() {
            return longDescription;
        }

        public void setLongDescription(String longDescription) {
            this.longDescription = longDescription;
        }

        public URL getRadioStationUrl() {
            return radioStationUrl;
        }

        public void setRadioStationUrl(URL radioStationUrl) {
            this.radioStationUrl = radioStationUrl;
        }
}
